# DevineHR
DevineHR is develpoed to help Admins and Employees providing basic facilities to maintain an environement between Admin and Employee.<br>
Here, an employee can request for a leave, see their profile, update profile, change password, etc.<br>
Admin can see all the employee/departments, create new department, add new employee, transfer an employee to diffrent department, etc.

## Features
Login as Admin/Employee<br>
Admin can add employee or department<br>
Admin can update user profile<br>
Admin can see list of employees and departments<br>
Admin can approave a leave request<br>
Employee can update their profile<br>
Employee can ask for a leave<br>
Logout

## Tech Stacks Used
```
Java
MySQL
Hibernate
Git & GitHub
```

## ER Diagram
![Mediamodifier-Design-Template](https://user-images.githubusercontent.com/71971788/209420570-3813e5b3-98aa-4bf0-9d99-ec52b6e26085.jpg)


## MySQL Tables
```  
Employee
Department
```

#### Employee Table Structure
```
id: Int (primary key AUTO_INCREMENT)
name: String
email: String
password: String
address: String
salary: Int
role: String
deptId: Int
```
#### Department Table
```
id: Int (primary key auto_increment)
name: String
location: String
```

## Prequiresits
It is required to have at least one admin registered(hardcoded) on the system. 
```
Demo Admin
-----------------------------------
Email: admin@divinehr.com
Password: adminpass

// SQL command
INSERT INTO Employee 
(name, address, email, password, salary, role)
VALUES
('Admin', 'Barpeta, Assam', 'admin@divinehr.com', 'adminpass', 500000, 'Admin');
```
