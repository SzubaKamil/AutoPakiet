# AutoPakiet
- <a href="https://github.com/SzubaKamil/AutoPakiet/blob/main/databae_script/model_DB_02.pdf">Database design</a>
- <a href="https://github.com/SzubaKamil/AutoPakiet/blob/main/screen.png">Frontend screen</a>
# 
# Basic Overview:

Fully functional system for managing cars informations.

The system stores information such as: user cars.
Car history: repairs, tires, mileages, service history, car inspections, insurances.

# Features:
- Login panel
- User Role:
    - ADMIN: new service account, new service type, check car history
    - SERVICE: check car history, change tires, add car inspections, add car repairs
    - USER: My cars, add car, add car by code, ADD: mileages, car inspections, car insurances, repairs, change tires, generate sales code
    - NO LOGGED USERS: login, registration
- Supports many users
- Send notification by email when car insurance or inspection is expired 
- When user sell car can generate "sales code". 
- New car owner can add car by "sales code" with all car history

# Tech Stack:
	- Java 11
	- Spring-boot 2.6.0
	- Hibernate 5.6.1.Final
	- mysql-connector-java 8.0.27
	- c3p0 0.9.5.2
 	- jaxb-api 2.30
