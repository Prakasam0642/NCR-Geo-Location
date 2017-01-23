# NCR Geo Location API

This is a sample application to locate the address using the latitude and longitude. This application is built on Spring(Spring Boot, Spring MVC, Spring TestNG, Oracle Coherence Caching).
The user's latest 10 search result will be cached and user can view the cached result.



#Build and Execute Steps


1. Download this project from https://github.com/Prakasam0642/ncr-google-ms-location to the local system.
2. Unzip the project and go to the pom.xml file location in the <unzipped dir path>.
3. Execute the below command to install Oracle Cohernece Jar into maven repository 
"mvn install:install-file -Dfile="<project.basedir>\coherence-jar\coherence-3.6.0.jar" -DgroupId=com.tangosol -DartifactId=coherence -Dversion=3.6.0 -Dpackaging=jar"
4. Enter "mvn package" command to build the application jar file.
5. Enter "java -jar target/ncr-google-ms-location-1.0.0.jar" to start the application.
6. To test the application from browser, enter the below requests URLs
	a) http://localhost:8080/v1/location/angle/{latitude}/{longitude} - to fetch the location address
	b) http://localhost:8080/v1/location/history - to fetch the search history from cache

#Sample URLs
http://localhost:8080/v1/location/angle/33.969601/-84.100033
http://localhost:8080/v1/location/history

