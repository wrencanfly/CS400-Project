run:compile
	java Frontend

compile:BankDataReader Backend Frontend
	
#DataReader
BankDataReader:BankDataInterface.class BankDataReader.class BankData.class

BankDataInterface.class:BankDataInterface.java
	javac BankDataInterface.java

BankDataReader.class:BankDataReader.java
	javac BankDataReader.java

BankData.class:BankData.java
	javac BankData.java

#Backend
Backend:BackendInterface.class SortedCollectionInterface.class Backend.class MapADT.class HashTableMap.class

BackendInterface.class:BackendInterface.java
	javac BackendInterface.java

MapADT.class:MapADT.java
	javac MapADT.java

HashTableMap.class:HashTableMap.java
	        javac HashTableMap.java

SortedCollectionInterface.class:SortedCollectionInterface.java
	javac SortedCollectionInterface.java

RedBlackTree:RedBlackTree.class

RedBlackTree.class:RedBlackTree.java
	javac RedBlackTree.java

Backend.class:Backend.java
	javac Backend.java

#Frontend
Frontend:Frontend.class

Frontend.class:Frontend.java
	javac Frontend.java

#test

test: testData testBackend testFrontend

testFrontend:FrontEndDeveloperTests.class
	java -jar junit5.jar -cp . --scan-classpath -n FrontEndDeveloperTests

FrontEndDeveloperTests.class:FrontEndDeveloperTests.java
	javac -cp .:junit5.jar FrontEndDeveloperTests.java

testBackend:BackEndDeveloperTests.class
	java -jar junit5.jar -cp . --scan-classpath -n BackEndDeveloperTests

BackEndDeveloperTests.class:BackendInterface.class
	javac -cp .:junit5.jar BackEndDeveloperTests.java
	
testData:DataWranglerTests.class
	java -jar junit5.jar -cp . --scan-classpath -n DataWranglerTests

DataWranglerTests.class:DataWranglerTests.java
	javac -cp .:junit5.jar DataWranglerTests.java

clean:
	$(RM) *.class
