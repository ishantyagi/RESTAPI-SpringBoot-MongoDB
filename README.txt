Follow the following instructions to run the web service:

1. Run the MongoDB server on your machine using the following commands:
   // In new command prompt window // "C:\Program Files\MongoDB\Server\3.2\bin\mongod.exe" --dbpath d:\test\mongodb\data
   // I have a 32-bit machine, please change the MongoDB version as per your machine; latest is 3.6 //
   // In new command prompt //  "C:\Program Files\MongoDB\Server\3.2\bin\mongo.exe" and close it.
2. Open the Spring IDE and import the code as a Maven project.
3. In Run Configurations, select SpringBootApp from the left hand side column. Then in Spring Boot heading, select Project as user-service, Main Type as com.user.UserServiceApplication
   and Profile as Local. In Arguments heading, put "-Dspring.profiles.active=LOCAL" (without quotes) in Program arguments dialogue box. Then click Apply and Run.
4. Hit http://localhost:8080/swagger-ui.html from browser.
5. Click on user-notes-controller. 4 options will be there corresponding to various endpoints.
6. While using POST, mention the username you want to add and then click on the yellow box on right side to populate box on left. 

