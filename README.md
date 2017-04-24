# TRIDent-CD

This project defines the code for generating the PDF for Loan Estimate 

This service runs on port :9014

To run the server ,enter into project folder and run

mvn spring-boot:run (or) java -jar *location of the jar file*

The above line will start the server at port 9014

If you want to change the port .Please start th server as mentioned below 

syntax : java -jar *location of the jar file* --server.port= *server port number*
 
example: java -jar target/TRIDentClosingDisclosure.jar --server.port=9090

API to generate Closing Disclosure JSON response(/actualize/trident/closingdisclosure/convertXmlToJson) with input as Closing Disclosure XML 

syntax : *server address with port*/actualize/trident/closingdisclosure/convertXmlToJson; method :POST; Header: Content-Type:application/xml

example: http://localhost:9014/actualize/trident/closingdisclosure/convertXmlToJson ; method: POST; Header: Content-Type:application/xml