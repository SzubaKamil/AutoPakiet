package com.company.autopakiet.controller;

import com.company.autopakiet.dao.IServiceRepairDAO;
import com.company.autopakiet.entity.*;
import com.company.autopakiet.service.ICarService;
import com.company.autopakiet.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Controller
public class CarController {

    @Autowired
    ICarService carService;

    @Autowired
    IServiceRepairDAO serviceRepairDAO;

    @Autowired
    IUserService userService;

    @RequestMapping ("/carHistory")
    public String showCarHistory (@ModelAttribute("car") Car car, Model model) {
        // get Car from DB by serviceCode
        car = carService.getByServiceCode(car.getServiceCode());

        model.addAttribute("car", car);
        return "car/car-info";
    }

    @RequestMapping(value="/nowa_naprawa_serwisowa/{carId}", method = RequestMethod.POST)
    public String showAddServiceRepair (@PathVariable int carId, Model model) {
        ServiceRepair serviceRepair = new ServiceRepair();

        //get serviceRepairs from db
        List<ServiceRepair> serviceRepairs = serviceRepairDAO.getAll();

        model.addAttribute("carId", carId);
        model.addAttribute("serviceRepairs", serviceRepairs);
        model.addAttribute("serviceRepair", serviceRepair);

        return "/car/new-service";
    }

    @RequestMapping("/nowa_naprawa_serwisowa/result/{carId}")
    public String addServiceRepair(@ModelAttribute("serviceRepair") ServiceRepair serviceRepair, @PathVariable int carId, Model model){
        // GET  service repair  by  ID
        ServiceRepair serviceRepairDb = serviceRepairDAO.getById(serviceRepair.getId());

        // Get car by ID
        Car car = carService.getById(carId);

        // new Repair history
        RepairHistory repairHistory = new RepairHistory( LocalDate.now(), serviceRepair);

        // Add repair history to car repair history list
        car.addRepairHistory (repairHistory);

        // update car in db return true if success
        boolean result = carService.addOrUpdate(car);

        String description = " dodanie naprawy serwisowej ";

        model.addAttribute("car", car);
        model.addAttribute("result", result);
        model.addAttribute("description" , description);

        return "/car/result";
    }

    @RequestMapping(value = "/nowy_przeglad/{carId}", method = RequestMethod.POST)
    public String showNewCarInspection (@PathVariable int carId, Model model) {
        CarInspection carInspection= new CarInspection();

        // get car last mileage from DB by car Id
        int lastMileage = carService.getById(carId).getLastMileage().getValue();

        model.addAttribute("lastCarMileage", lastMileage);
        model.addAttribute("carInspection", carInspection);
        model.addAttribute("carId", carId);

        return "/car/new-inspection";
    }

    @RequestMapping("/nowy_przeglad/result/{carId}")
    public String addNewCarInspection (@ModelAttribute("carInspection") CarInspection carInspection, @PathVariable int carId, Model model){
        // car get car by id from db
        Car car = carService.getById(carId);

        // set expiration dare to carInspection (NOW + ONE YEAR )
        carInspection.setExpirationDate(LocalDate.now().plusYears(1));

        // get carMileage and set car mileage date to car inspection (NOW )
        CarMileage carMileage = carInspection.getCarMileage();
        carMileage.setDate(LocalDate.now());

        // add car mileage to car
        car.addCarMileage(carMileage);

        // add car inspection to car
        car.addCarInspection(carInspection);

        // update car to database -> if success result equals true
        boolean result = carService.addOrUpdate(car);

        String description = " dodanie przeglądu ";

        model.addAttribute("car", car);
        model.addAttribute("result", result);
        model.addAttribute("description" , description);

        return "/car/result";
    }

    @RequestMapping(value = "/wymiana_opon/{carId}", method = RequestMethod.POST)
    public String showChangeTire (@PathVariable int carId, Model model) {
        // get car tires list from db
        List<Tire> tireList = carService.getById(carId).getTires();

        // add empty tires list to the model
        Tire tire = new Tire();

        model.addAttribute("tireList", tireList);
        model.addAttribute("tire", tire);

        return "/car/change-tire";
    }

    @RequestMapping("/wymiana_opon/result/{carId}")
    public String addChangeTire (@ModelAttribute("tire") Tire tire, @PathVariable int carId, Model model){
        // get car from DB  by id
        Car car = carService.getById(carId);

        // set active tire
        car.setActiveTire(car.getTires().stream().filter(p->p.getId() == tire.getId()).findAny().get());

        // update car to database -> if success result equals true
        boolean result = carService.addOrUpdate(car);

        String description = " wymiana opon ";

        model.addAttribute("car", car);
        model.addAttribute("result", result);
        model.addAttribute("description" , description);

        return "/car/result";
    }

    @RequestMapping("/moje_auta")
    public String showMyCars (Model model){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        // get car list from db by user id
        List<Car> cars = carService.getByUserId(username);

        model.addAttribute("cars", cars);
        return "car/role-user/car-list";
    }

    @RequestMapping("dodaj_auto")
    public String showAddCar (Model model){
        Car car = new Car();

        model.addAttribute("car", car);
        model.addAttribute("fuelTypes", Car.FuelType.values());
        model.addAttribute("tireSeason", Tire.TireSeason.values());
        return "/car/role-user/new-car";
    }

    @RequestMapping("dodaj_auto/nowe/")
    public String addCar (@ModelAttribute("car") Car car, Model model){
        boolean lpg = false;

        if (car.getFuelType().equals(Car.FuelType.LPG)){
            // return ADD LPG DETAILS PAGE
            System.out.println("LPG FULE TYPE");
            lpg = true;
        }

        // get username
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        // set car "owner" username
        car.setUserName(username);

        //  save new car to database -> if success result equals true
        boolean result = carService.addOrUpdate(car);

        String description = " dodanie nowego auta ";

        model.addAttribute("car", car);
        model.addAttribute("result", result);
        model.addAttribute("description" , description);
        model.addAttribute("lpg", lpg);

        return "/car/result";
    }

    @RequestMapping("dodaj_auto_kodem")
    public String showAddCarByCode (Model model){
        Car car = new Car();

        model.addAttribute("car", car);
        return "/car/role-user/new-car-code";
    }

    @RequestMapping("/dodaj_auto_kodem/nowe/")
    public String addCarByCode (@ModelAttribute("car") Car car, Model model){
        boolean result;
        Car carFromDb = null;

        // get car from database
        if (car.getSaleCode() != 0){
            carFromDb = carService.getCarBySalesCode(car.getSaleCode());
        }

        // if null incorrect code or 0
        if (carFromDb == null){
            result = false;
            carFromDb = new Car();
            carFromDb.setProducer("Nowe ");
            carFromDb.setModel("auto");
        } else {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            // set new user ID
            carFromDb.setUserName(username);

            // set empty sales code
            carFromDb.setSaleCode(0);

            // set result true
            result = carService.addOrUpdate(carFromDb);
        }
        String description = " dodanie auta ";

        model.addAttribute("result", result);
        model.addAttribute("car", carFromDb);
        model.addAttribute("description", description );

        return "/car/result";
    }

    @RequestMapping(value = "/nowa_opona/{carId}", method = RequestMethod.POST)
    public String showNewTire (@PathVariable int carId, Model model) {
        Tire tire = new Tire();
        model.addAttribute("tire", tire);
        model.addAttribute("tireSeason", Tire.TireSeason.values());
        model.addAttribute("carId", carId);

        return "/car/role-user/new-tire";
    }

    @RequestMapping(value = "/nowa_opona/nowa/{carId}", method = RequestMethod.POST)
    public String addNewTire (@PathVariable int carId, @ModelAttribute("tire") Tire tire, Model model) {
        // get car from db by id
        Car car = carService.getById(carId);

        // add tire to car list
        car.addTire(tire);

        // update car to database -> if success result equals true
        boolean result = carService.addOrUpdate(car);

        String description = " dodanie nowej opony " + tire.getProducer() + " " + tire.getSeason() + " ";

        model.addAttribute("result", result);
        model.addAttribute("car", car);
        model.addAttribute("description", description );
        return "/car/result";
    }

    @RequestMapping(value = "nowe_ubezpieczenie/{carId}", method = RequestMethod.POST)
    public String showNewInsurance (@PathVariable int carId, Model model) {
        Insurance insurance = new Insurance();
        model.addAttribute("insurance", insurance);
        model.addAttribute("insuranceTypes", Insurance.InsuranceType.values());
        model.addAttribute("carId", carId);

        return "/car/role-user/new-insurance";
    }

    @RequestMapping(value = "/nowe_ubezpieczenie/nowe/{carId}", method = RequestMethod.POST)
    public String addNewInsurance (@PathVariable int carId, @ModelAttribute("insurance") Insurance insurance, Model model) {
        // get car from db by id
        Car car = carService.getById(carId);

        // add insurance to car list
        car.addCarInsurance(insurance);

        // update car  set result
        boolean result = carService.addOrUpdate(car);

        String description = " dodanie ubezpieczenia " + insurance.getType() + " ważne do " + insurance.getEndDate() + " ";

        model.addAttribute("result", result);
        model.addAttribute("car", car);
        model.addAttribute("description", description );
        return "/car/result";
    }

    @RequestMapping(value = "/nowy_przebieg/{carId}", method = RequestMethod.POST)
    public String showNewCarMileage (@PathVariable int carId, Model model) {
        CarMileage carMileage = new CarMileage();

        // get last car mileage
        int lastMileage = carService.getById(carId).getLastMileage().getValue();

        model.addAttribute("lastMileage",lastMileage );
        model.addAttribute("carMileage", carMileage);
        model.addAttribute("carId", carId);

        return "/car/role-user/new-mileage";
    }

    @RequestMapping(value = "/nowy_przebieg/nowy/{carId}", method = RequestMethod.POST)
    public String addNewInsurance (@PathVariable int carId, @ModelAttribute("carMileage") CarMileage carMileage, Model model) {
        // get car by id from db
        Car car = carService.getById(carId);

        // add mileage to car
        car.addCarMileage(carMileage);

        // update car to database -> if success result equals true
        boolean result = carService.addOrUpdate(car);

        String description = " dodanie przebiegu data: " + carMileage.getDate() + " wartość " + carMileage.getValue() + " km. ";

        model.addAttribute("result", result);
        model.addAttribute("car", car);
        model.addAttribute("description", description );
        return "/car/result";
    }

    @RequestMapping(value = "/sprzedarz/{carId}/{confirmation}", method = RequestMethod.POST)
    public String showSellCar (@PathVariable int carId, @PathVariable boolean confirmation , Model model) {
        if (confirmation){
            // generate  random sell code
            int salesCode = new Random().nextInt(Integer.MAX_VALUE);

            // get car from db by id
            Car car = carService.getById(carId);

            // set random sales code
            car.setSaleCode(salesCode);

            // update car in database
            carService.addOrUpdate(car);

            // put salesCode to model
            model.addAttribute("salesCode", salesCode);
        }
        model.addAttribute("carId", carId);
        model.addAttribute("confirmation", confirmation);

        return "/car/role-user/sell";
    }
}
