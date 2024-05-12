![Screen Shot 2024-05-12 at 14 20 47](https://github.com/mrtgyk/courier-tracking/assets/15198451/688d6525-366f-46c0-b9ac-0bbaf2a5e0b3)

* Run command option 1: mvn spring-boot:run 
* Run command option 2: ./mvnw spring-boot:run
* curl --location 'localhost:8085/courierTracking' \
--header 'Content-Type: application/json' \
--data '{
    "time":"2024-05-11T14:39:57",
    "courier":1,
    "latitude": 40.986000,
    "longitude":29.1161000
}'
* Calculate total distance method: com.rest.courier.dao.CourierTrackingDao#getTotalTravelDistance
 
