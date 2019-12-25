create database DBmidtermExam;
use DBmidtermExam;

CREATE TABLE loginDetails(
ID INT PRIMARY KEY AUTO_INCREMENT,
username VARCHAR(255) NOT NULL,
password VARCHAR(255) NOT NULL,
lastname VARCHAR(255) NOT NULL,
firstname VARCHAR(255) NOT NULL,
middlename VARCHAR(255) NOT NULL,
address VARCHAR(255) NOT NULL,
age INT  NOT NULL,
Userstatus varchar(225)not null);



INSERT INTO loginDetails(username,password,lastname,firstname,middlename,address,age, Userstatus)
VALUES
("admin","1234","b","b","b","bwa","23","active");

/*view all user*/
DELIMITER \\
create procedure getalluser()
Begin
	
    SELECT * from loginDetails;
        
END \\

DELIMITER ;



/*INSERT*/
DELIMITER \\
CREATE PROCEDURE insertdits(IN f1 VARCHAR(255), f2 VARCHAR(255), f3 VARCHAR(255), f4 VARCHAR(255), f5 VARCHAR(255), f6 VARCHAR(255), f7 INT(3), f8 VARCHAR(255))
BEGIN 
INSERT INTO loginDetails(username,password,lastname,firstname,middlename,address,age,Userstatus) 
VALUES(f1,f2,f3,f4,f5,f6,f7,f8);
END \\
DELIMITER ;

/*check*/
DELIMITER \\
CREATE PROCEDURE checkUser()
BEGIN
SELECT username, password  FROM loginDetails;
END \\
DELIMITER ;

/*UPDATE*/
DELIMITER \\
CREATE PROCEDURE Updatedits(in f1 VARCHAR(255),f2 VARCHAR(255), f3 VARCHAR(255), f4 VARCHAR(255), f5 VARCHAR(255))
BEGIN
UPDATE loginDetails
SET 
firstname = f1,
lastname = f2,
middlename = f3,
address = f4

WHERE password = f5;
END \\
DELIMITER ;

-- status change
DELIMITER \\
CREATE PROCEDURE UpdateStat(in f1 VARCHAR(255),f2 VARCHAR(255))
BEGIN
UPDATE loginDetails
SET 
Userstatus = f1

WHERE password = f2;
END \\
DELIMITER ;
drop procedure UpdateStat;
-- drop table loginDetails;

-- drop procedure insertdits;


create table orderz(
ID INT PRIMARY KEY AUTO_INCREMENT,
product_sku int not null,
order_Product varchar(225) not null,
orderPrice varchar(225) not null,
orderQuantity int not null);

create table Inventory(
ID INT PRIMARY KEY AUTO_INCREMENT,
itemName varchar(255) not null,
stock int not null,
price varchar(255) not null
);

insert into Inventory(itemName, stock, price)
values
("phone","25","$499"),
("computer","25","$499"),
("Television","25","$499"),
("radio","25","$499");

/*view all user*/
DELIMITER \\
create procedure getallinv()
Begin
	
    SELECT * from Inventory;
        
END \\

DELIMITER ;

/*INSERT*/
DELIMITER \\
CREATE PROCEDURE insertstock(IN f1 VARCHAR(255), f2 VARCHAR(255), f3 VARCHAR(255))
BEGIN 
INSERT INTO Inventory(itemName, stock, price) 
VALUES(f1,f2,f3);
END \\
DELIMITER ;