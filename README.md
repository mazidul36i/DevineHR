# DevineHR
Divine HR is a project that is meant to bring people together and help them connect on a deeper level. It is a console-based "Human Resource Management" project. 

## Tech Stacks
    Java
    MySQL
   
## MySQL Tables
  
    Employee
    Department
  
  #### Employee Table
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

  #### Department Table
    create table Department(
      id int primary key auto_increment,
      name varchar(30),
      location varchar(30)
    );
