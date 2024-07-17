
create database  seoulcarrental;
use seoulcarrental;

CREATE TABLE users (
    userId VARCHAR(20) PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    password  VARCHAR(20)
  
);

INSERT INTO users (userId, name ,password ) VALUES
('user1', 'chathuranga','123'),
('user2', 'iduwara','1234'),
('user3', 'chamod','12345');




CREATE TABLE employees (
    EmployeeId VARCHAR(20) PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    employeeType VARCHAR(50) NOT NULL,
    salary DOUBLE NOT NULL,
    userId VARCHAR(20),
    FOREIGN KEY (userId) REFERENCES users(userId)ON UPDATE CASCADE ON DELETE CASCADE
);



CREATE TABLE Vehicles (
    vehicleId VARCHAR(20) PRIMARY KEY,
    modelName VARCHAR(50) NOT NULL,
    bookingCost DOUBLE NOT NULL,
    numberPlate VARCHAR(20),
    currentMilage DOUBLE NOT NULL,
    first_100Km_1km_charge  DOUBLE NOT NULL,
    after_100Km_1km_charge DOUBLE NOT NULL,
    userId VARCHAR(20),
    FOREIGN KEY (userId) REFERENCES Users(userId)ON UPDATE CASCADE ON DELETE CASCADE
   
);

CREATE TABLE Suppliers (
    supplierId VARCHAR(20) PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    contact VARCHAR(50) NOT NULL,
    specialCar VARCHAR(20) NOT NULL,
    specialCarCost DOUBLE NOT NULL,
    vehicleId VARCHAR(20),
    FOREIGN KEY (vehicleId) REFERENCES Vehicles(vehicleId)ON UPDATE CASCADE ON DELETE CASCADE
);


CREATE TABLE customer (
    customerld VARCHAR(20) PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    contact VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    address VARCHAR(100) NOT NULL,
    nic VARCHAR(100) NOT NULL,
    bailVehicleNm VARCHAR(100) NOT NULL
);


CREATE TABLE Reservations (
    reservationId VARCHAR(20)  PRIMARY KEY,
    reservationDate DATE  NOT NULL,
    returnDate DATE NOT NULL,
    customerld VARCHAR(20),
    FOREIGN KEY (customerld) REFERENCES customer(customerld)ON UPDATE CASCADE ON DELETE CASCADE

);

CREATE TABLE ReservationDetails (
    reservationId VARCHAR(20),
    vehicleId VARCHAR(20),
    FOREIGN KEY (reservationId) REFERENCES Reservations(reservationId)ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (vehicleId) REFERENCES Vehicles(vehicleId)ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Returns (
    returnId VARCHAR(20)  PRIMARY KEY,
    afterRideMilage DOUBLE NOT NULL,
    lateReturnCharge DOUBLE NOT NULL,
    TotalCost DOUBLE NOT NULL,
    customerld VARCHAR(20),
    reservationId VARCHAR(20),
    FOREIGN KEY (customerld) REFERENCES customer(customerld)ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (reservationId) REFERENCES Reservations(reservationId)ON UPDATE CASCADE ON DELETE CASCADE

);
CREATE TABLE returnDetails (
    returnId VARCHAR(20),
    vehicleId VARCHAR(20),
    FOREIGN KEY (returnId) REFERENCES Returns(returnId) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (vehicleId) REFERENCES Vehicles (vehicleId) ON UPDATE CASCADE ON DELETE CASCADE

);

CREATE TABLE payments (
    paymentsId VARCHAR(20) PRIMARY KEY,
    PaymentType VARCHAR(10) NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    date DATE NOT NULL,
    returnId VARCHAR(20),
    reservationId VARCHAR(20),
    FOREIGN KEY (returnId) REFERENCES Returns(returnId)ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (reservationId) REFERENCES Reservations (reservationId)ON UPDATE CASCADE ON DELETE CASCADE
);









INSERT INTO Employees (EmployeeId, name, employeeType, salary, userId) VALUES
('emp1', 'Tom Brown', 'Manager', 50000.00, 'user1'),
('emp2', 'Emily Davis', 'Agent', 35000.00, 'user2'),
('emp3', 'Chris Evans', 'cleaner', 15000.00, 'user3');

INSERT INTO Vehicles (vehicleId, modelName, bookingCost, available, userId) VALUES
('v1', 'Toyota Camry', '8000.00', 'Yes', 'user1'),
('v2', 'Honda Civic', '9000.00', 'Yes', 'user2'),
('v3', 'Ford Mustang', '12000.00', 'No', 'user1'),
('v4', 'vezal 2018', '8200.00', 'Yes', 'user2'),
('v5', 'BMW 2022', '15000.00', 'No', 'user2'),
('v6', 'KDH', '11000.00', 'Yes', 'user2'),
('v7', 'Aqua', '3000.00', 'Yes', 'user2'),
('v8', 'Vaganar', '2200.00', 'No', 'user3'),
('v9', 'Audi', '12000.00', 'Yes', 'user3'),
('v10', 'swift', '2000.00', 'No', 'user3');

INSERT INTO Suppliers (supplierId, name, contact, specialCar, specialCarCost, vehicleId) VALUES
('sup1', 'ABC Suppliers', '123-456-7890', 'Toyota Camry', '2100.00', 'v1'),
('sup2', 'XYZ Suppliers', '987-654-3210', 'Ford Mustang', '4200.00', 'v3');

INSERT INTO Customers (customerld, name, contact, address, nic, bailVehicleNm) VALUES
('cus1', 'Michael Johnson', '555-555-5555', '123 Main St', '123456789', 'Honda Civic'),
('cus2', 'Sarah Wilson', '777-777-7777', '456 Oak St', '987654321', 'Toyota Camry');

INSERT INTO Returns (returnId, returnDate, customerld) VALUES
('ret1', '2024-03-01', 'cus1'),
('ret2', '2024-03-15', 'cus2');

INSERT INTO Reservations (reservationId, reStartDate, reEndDate, milageCost, customerld) VALUES
('res1', '2024-03-01', '2024-03-05', 50.00, 'cus1'),
('res2', '2024-03-10', '2024-03-15', 60.00, 'cus2');

INSERT INTO Payments (paymentsId, PaymentType, amount, date, returnId, reservationId) VALUES
('pay1', 'Cash', 100.00, '2024-03-05', 'ret1', NULL),
('pay2', 'Credit Card', 120.00, '2024-03-15', NULL, 'res2');

INSERT INTO RegisterationDetails (currentMilage, afterRideMilage, OneHundredsKmCharge, AfterOneHundredsKmCharge, vehicleId, reservationId) VALUES
(5000.00, 5200.00, 50.00, 0.50, 'v1', 'res1'),
(3000.00, 3100.00, 60.00, 0.70, 'v2', 'res2');

INSERT INTO ReturnDetails (returnId, vehicleId) VALUES
('ret1', 'v1'),
('ret2', 'v2');



























