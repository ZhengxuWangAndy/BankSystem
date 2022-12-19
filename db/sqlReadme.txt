//START your mysql with

export PATH=${PATH}:/usr/local/mysql/bin

mysql -u root -p

// show all data

SHOW DATABASES;

USE BANK

SHOW TABLES;

SELECT * FROM Currencies;


//use the Bank.sql to Create db. -> to add the dump in to mysql

mysql -u root -p BANK1 < ./Bank.sql

// use the former sentences to check it is success.



// to create mysql dump

/usr/local/mysql/bin/mysqldump -u root -p Bank > Bank.sql