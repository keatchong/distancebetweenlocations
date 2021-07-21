# distancebetweenlocations

## Deployment Steps

1) Download package from git, copy distancebetweenlocations-master to a folder, rename to distancebetweenlocations
2) Make sure you have setup MySQL or have a working MySql Server before go to step 3
3) Please run the included db script in (at distancebetweenlocations\sql-scripts) first to create database and tables and insert seed data (Note : Not full UK postcodes records)  
4) Edit application.properties at distancebetweenlocations\src\main\resources follow your own mysql setup for the below parameters
	* spring.datasource.username=*user*
	* spring.datasource.password=*password*
5) cd into distancebetweenlocations where pom.xml resides
6) mvn package
7) jar file of distancebetweenlocations-0.0.1-SNAPSHOT.jar will be generated at folder distancebetweenlocations\target
8) Run java -jar distancebetweenlocations-0.0.1-SNAPSHOT.jar to launch spring boot application ( must use java 11)
9) Check the log in console untill you see  *Started DistancebetweenlocationsApplication in 5.51 seconds (JVM running for 6.015)*

## My Development/Testing Environment
- Apache Maven 3.5.3
- Java version: openjdk version "11.0.8" 2020-07-14
- Spring Boot : 2.5.2 
- MySQL 6.3

## Tools Used
- ChromBrowser 
- Postman
- Eclipse IDE


## RESTFUL API

**API is protected using basic authentication**

- username : admin
- password : qwerty0908&

1) Get Distance

```
GET http://localhost:8080/api/v1/getDistance?loc1=AB10 1XG&loc2=AB16 5ST

{
    "firstlocation": {
        "postCode": "AB10 1XG",
        "latitude": "57°8'39\"N",
        "longitude": "2°6'53\"S"
    },
    "secondlocation": {
        "postCode": "AB16 5ST",
        "latitude": "57°9'48\"N",
        "longitude": "2°9'34\"S"
    },
    "distance": 3.436,
    "unit": "km"
}

```

2) Query a location

```

GET http://localhost:8080/api/v1/getLatLng/AB12 4NA

{
    "postCode": "AB12 4NA",
    "latitude": 57.064273000,
    "longitude": -2.130011000
}

```

3) Update a location

```

POST  http://localhost:8080/api/v1/updateLatLng/AB12 4NA

Request body

{
    "latitude": 57.064271000,
    "longitude": -2.130021000
}

```

## Features Supported
1) Rest API as illustrated above
2) Request Logging into table requestlog for successful distance request
3) Basic Authentication for protecting API using in-memory configuration for illustration

