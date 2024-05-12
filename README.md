COURIER TRACKING

It is a rest API application that performs various operations according to the information received as parameters.

Tech Stack
- Spring Boot
- H2 Database
- JPA
- Lombok

Run command: ./mvnw spring-boot:run

Request: curl --location 'localhost:8085/courierTracking' \
--header 'Content-Type: application/json' \
--data '{
    "time":"2024-05-11T14:39:57",
    "courier":1,
    "latitude": 40.986000,
    "longitude":29.1161000
}'

Calculate Total Distance Method: com.rest.courier.dao.CourierTrackingDao#getTotalTravelDistance
