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

