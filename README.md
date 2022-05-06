# individual-project-slahari5

Name : SAILAHARI SEETHAMRAJU
Student ID: 016037112

#Problem Statement

Developed a java application which has details about the inventory stock where the user can buy the items among three different categories. They are:
* Essentials
* Luxury
* Misc

Every category has a quantity cap limit on items that are purchased in single order. The items ordered should be validated whether they exceeds the existing stock count of inventory items. If the provided card details for payment doesnt exist, then the card value must be added to the database. When the user's request satisfies all the validations, a CSV file is generated with the details of successful order's invoice. 


# Design Patterns

* Singleton Design Pattern: This Design Pattern is categorized into creational design pattern and it restricts instantiation of a class to one object. This pattern helps in developing the database in application. A singleton InMemoryDB object is created and is stored as a static member which is accessed by all other classes without instantiating it everytime.



* Factory Design Pattern: This Design Pattern is categorized into creational design pattern which allows the subclasses to choose the type of objects to create. This design pattern says that just define an interface or an abstract class and let the subclasses decide which object to instantiate. it is used in generating the output after processing the order. 


<img width="548" alt="Screen Shot 2022-05-05 at 10 36 53 PM" src="https://user-images.githubusercontent.com/99698941/167073395-8eb10fd5-85e6-4855-b3f7-3f1c57b042a9.png">




Run Instructions:

Running using jar file
1. Install latest java version
2. Open terminal in the project root directory
3. Run the following command
`java -jar InventoryStockManagement.jar <"Input file path">`

For example
`java -jar InventoryStockManagement.jar ./resources/InputSheet.csv`


------------------------

Alternative Eclipse run
1. Go to repo individual-project-slahari5 and clone the repository or download the zip file.
2. You need Eclipse IDE to run the code.
3. Open the zipped folder or the entire folder in Eclipse using Open Project from FileSystem.
4. After opening the project, select Run Configuration from the Run drop down menu.
5. Create New Configuration in Java Application 

<img width="827" alt="image" src="https://user-images.githubusercontent.com/99698941/167069381-6d06c1b0-f746-48b4-94ea-939b07d0dd92.png">

6. Select arguments in the Run Conguration. Provide the Input file path and apply.
7. Build the project and run Billing.java

Output file path is displayed in the terminal to the location where the error file or output CSV file is stored
