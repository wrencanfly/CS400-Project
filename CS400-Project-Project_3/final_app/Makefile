run:compile
	java FrontEnd

compile:CityDataReader Backend FrontEnd

#DataReader
CityDataReader:CityDataInterface.class CityDataReader.class CityData.class

CityDataInterface.class:CityDataInterface.java
	javac CityDataInterface.java

CityDataReader.class:CityDataReader.java
	javac CityDataReader.java

CityData.class:CityData.java
	javac CityData.java

#Backend
Backend:BackendInterface.class GraphADT.class CS400Graph.class Backend.class

BackendInterface.class:BackendInterface.java
	javac BackendInterface.java

GraphADT.class:GraphADT.java
	javac GraphADT.java

CS400Graph.class:CS400Graph.java
	        javac CS400Graph.java

Backend.class:Backend.java
	javac Backend.java


#Frontend
FrontEnd:FrontEnd.class

FrontEnd.class:FrontEnd.java
	javac FrontEnd.java

#test

test: testData testBackend testFrontend

testFrontend:FrontEndTest.class
	java -jar junit5.jar -cp . --scan-classpath -n FrontEndTest

FrontEndTest.class:FrontEndTest.java
	javac -cp .:junit5.jar FrontEndTest.java

testBackend:BackendTest.class
	java -jar junit5.jar -cp . --scan-classpath -n BackendTest

BackendTest.class:BackendInterface.class
	javac -cp .:junit5.jar BackendTest.java

testData:DataWranglerTest.class
	java -jar junit5.jar -cp . --scan-classpath -n DataWranglerTest

DataWranglerTest.class:DataWranglerTest.java
	javac -cp .:junit5.jar DataWranglerTest.java

clean:
	$(RM) *.class
