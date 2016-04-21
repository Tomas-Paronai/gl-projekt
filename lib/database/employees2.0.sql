CREATE DATABASE employees;
USE employees;

CREATE TABLE Employee(
	EmployeeID int(15) PRIMARY KEY auto_increment,
	FirstName varchar(40),
	SurName varchar(40),
	Gender enum('Female', 'Male'),
	BirthDate Date
);

CREATE TABLE `Position`(
	PositionID int(15) auto_increment,
	PositionName varchar(40),
	primary key(PositionID)
);

CREATE TABLE Contract(
	ContractID int(15) PRIMARY KEY auto_increment,
	Contract_type varchar(40)
);

CREATE TABLE Contact(
	EmployeeID int(15),
	Phone int(15),
	Email varchar(40),
	foreign key(EmployeeID) references Employee(EmployeeID) on update cascade on delete cascade
);

CREATE TABLE Address(
	EmployeeID int(15),
	Country varchar(40),
	City varchar(40),
	Street varchar(40),
	`Number` int(10),
	PostCode int(10),
	foreign key(EmployeeID) references Employee(EmployeeID) on update cascade on delete cascade
);

CREATE TABLE Salary(
	EmployeeID int(15),
	Salary_date DATE,
	Hours float(15),
	Per_hour_salary float(15),
	foreign key(EmployeeID) references Employee(EmployeeID) on update cascade on delete cascade
);



CREATE TABLE Employment_detail(
	EmployeeID int(15),
	PositionID int(15),
	ContractID int(15),
	Salary float(15),
	Start_work timestamp,
	foreign key(EmployeeID) references Employee(EmployeeID) on update cascade on delete cascade,
	foreign key(PositionID) references `Position`(PositionID) on update cascade on delete cascade,
	foreign key(ContractID) references Contract(ContractID) on update cascade on delete cascade
);

CREATE TABLE Work_shift (
	EmployeeID int(15),
	Enter_time TIME,
	Exit_time TIME,
	foreign key(EmployeeID) references Employee(EmployeeID) on update cascade on delete cascade
);





