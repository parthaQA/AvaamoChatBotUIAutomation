
Framework Components :
Java 11.
Selenium Web Driver Version 4.
TestNG test framework.
Page class
Page Factory design pattern (Page Object Model)
Test class
Utility class/Helper class
Pom.xml
TestNG reporter
Log4j2 property file.
Logs folder to save the logs.
Screenshot folder to add the screenshots of failed test cases (Day wise)
Resources folder for config.properties
Target folder for report. Report will generate under Surfire reports folder under Target folder.


To run the testcases use command "mvn clean" it removes the previous reports under Target folder.
then run "mvn test"
it will run the testNG.xml file. The testNG.xml file has one test class.
One test case is intentionally failed.
