# DevineHR
DevineHR is a Hibernet project developed using MySQL database. 

## ER Diagram
![Mediamodifier-Design-Template](https://user-images.githubusercontent.com/71971788/209420570-3813e5b3-98aa-4bf0-9d99-ec52b6e26085.jpg)

## Tech Stacks
```
Java
MySQL
```
   
## Features
Login as Admin/Employee<br>
Admin can add employee or department<br>
Admin can update user profile<br>
Admin can see list of employees and departments<br>
Admin can approave a leave request<br>
Employee can update their profile<br>
Employee can ask for a leave<br>
Logout

## MySQL Tables
```  
Employee
Department
```

#### Employee Table
```
create table Employee (
    id int primary key AUTO_INCREMENT,
    name varchar(30) NOT NULL,
    email varchar(30),
    password varchar(20),
    address varchar(30),
    salary int,
    role varchar(20),
    deptId int
);
```
#### Department Table
```
create table Department(
    id int primary key auto_increment,
    name varchar(30),
    location varchar(30)
);
```

## Prequiresits
It is required to have at least one admin registered(hardcoded) on the system. 
```
First Admin
-----------------------------------
Email: admin@divinehr.com
Password: adminpass
```

Demo MySQL command to insert the first admin
```
INSERT INTO Employee 
(name, address, email, password, salary, role)
VALUES
('Admin', 'Barpeta, Assam', 'admin@divinehr.com', 'adminpass', 500000, 'Admin');
```
