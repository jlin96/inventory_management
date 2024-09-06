<h1>Project 2 </h1>
<p>Updated on 9/6/24</p>

<h1>Testing Frameworks</h1>
TestNG used for all testing including unit testing and cucumberRunner </br>
Mockito used for all unit testing inlcludes our Controllers and Service classes </br>
MeanBean used for model testing </br>
Cucumber used for behavior driven development -> report link posted in test console </br>
Selenium Web Driver used for testing the website   </br>

<h1>Tools</h1> 
Jacoco Plugin - Gets code coverage for our backed and utilized in SonarCloud -> found in target/jacoco/index.html </br>
Jenkins - Contious Integration/ Continous Development tool </br>
SonarCloud - Static Analysis tool integrated with Jenkins </br>

<h1>Deployment</h1>
AWS Aurora RDS utilized Postgres SQL</br>
AWS S3 bucket host the front end as a static website : http://team-6-frontend-jenkins.s3-website-us-east-1.amazonaws.com/</br>
AWS S3 bucket host backend code </br>
EBS utilized the backend S3 bucket and an ec2 instance that is connected to the RDS </br>

<h1>TODO</h1>
FrontEnd testing with Jest was not covered. </br>
Jmeter was not implemented in the Jenkins Pipeline. </br>
BurbSuite Reporting was not utilized.</br>
Some issues showing up in the jenkins piepline that do not show up on local test</br>
Some cucumber test temporarily commented to diagnose the bug </br>

<h1>Reports</h1>
SonarCloud https://sonarcloud.io/organizations/jlin96/projects </br>
#Only front end was tested </br>

<hr>
Project 1: Inventory Management:

This project aims to mimic an inventory management system. 
I have created 2 tables (Product and Warehouse) that are linked to each other 
and are displayed on a table format in the frontend.
You are able to add/edit/delete products/warehouses as well.

Product Table:\
int id - Primary Key\
String name - Name of Product\
String description - Description of Product\
int stockAmount - Amount of Product in stock\
Warehouse warehouse - Reference key to Warehouse\

Warehouse Table:\
int id - Primary key\
String name - Name of Warehouse\
String address - Warehouse address\

Both tables have controllers with the corresponding routes: GET/POST/PUT/DELETE

TODO:\
Create up extra routes/services not needed\
More granular breakdown of Warehouse table\
Categories for products\
Add max compacity to Warehouse/keep track of stock amount.\
Add in user authorization (swt)\
Split up reducers (product/warehouse)\
Create modular components (repeat components: modal, editmodal, table, primary tab of component)\
Add error handling on backend and show them on frontend\
See specific Warehouse products\
Separate user login/splash page\
Clean up Table component\
Clean up file management\

