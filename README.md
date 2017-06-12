# TRIDent-CD

This project defines the code for generating JSON response from MISMO XML and vice versa for Closing Disclosure

This service runs on port :9014

To run the server ,enter into project folder and run

mvn spring-boot:run (or) java -jar *location of the jar file*

The above line will start the server at port 9014

If you want to change the port .Please start th server as mentioned below 

syntax : java -jar *location of the jar file* --server.port= *server port number*
 
example: java -jar target/TRIDentClosingDisclosure.jar --server.port=9090

API to generate Closing Disclosure JSON response(/actualize/transformx/trident/cd/{version}/ucdtojson) with input as Closing Disclosure XML 

syntax : *server address with port*/actualize/transformx/trident/cd/{version}/ucdtojson; method :POST; Header: Content-Type:application/xml

example: http://localhost:9014/actualize/transformx/trident/cd/v1/ucdtojson ; method: POST; Header: Content-Type:application/xml

API to generate Closing Disclosure XML response(/actualize/transformx/trident/cd/{version}/jsontoucd) with input as Closing Disclosure JSON Object 

syntax : *server address with port*/actualize/transformx/trident/cd/{version}/jsontoucd; method :POST; Header: Content-Type:application/json

example: http://localhost:9014/actualize/transformx/trident/cd/v1/jsontoucd; method: POST; Header: Content-Type:application/json

API to check the status of service(/actualize/transformx/trident/cd/{version}/ping) 

syntax : *server address with port*/actualize/transformx/trident/cd/{version}/ping; method: GET;

example: http://localhost:9014/actualize/trident/closingdisclosure/v1/ping ; method: GET;