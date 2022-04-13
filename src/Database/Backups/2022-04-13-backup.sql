-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: 176.58.124.119    Database: GARITS
-- ------------------------------------------------------
-- Server version	8.0.28-0ubuntu0.21.10.3

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Addresses`
--

DROP TABLE IF EXISTS `Addresses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Addresses` (
  `addressID` int NOT NULL AUTO_INCREMENT,
  `addressLine1` varchar(255) NOT NULL,
  `addressLine2` varchar(255) DEFAULT NULL,
  `city` varchar(255) NOT NULL,
  `postcode` varchar(7) NOT NULL,
  PRIMARY KEY (`addressID`)
) ENGINE=InnoDB AUTO_INCREMENT=1231231232 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Addresses`
--

LOCK TABLES `Addresses` WRITE;
/*!40000 ALTER TABLE `Addresses` DISABLE KEYS */;
INSERT INTO `Addresses` VALUES (1231231231,'Oval Road 12',NULL,'Camden Town','NW1 7EE');
/*!40000 ALTER TABLE `Addresses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Customer_Addresses`
--

DROP TABLE IF EXISTS `Customer_Addresses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Customer_Addresses` (
  `CustomercustomerID` int NOT NULL,
  `AddressesaddressID` int NOT NULL,
  PRIMARY KEY (`CustomercustomerID`,`AddressesaddressID`),
  KEY `FKCustomer_A344860` (`AddressesaddressID`),
  CONSTRAINT `FKCustomer_A344860` FOREIGN KEY (`AddressesaddressID`) REFERENCES `Addresses` (`addressID`),
  CONSTRAINT `FKCustomer_A798687` FOREIGN KEY (`CustomercustomerID`) REFERENCES `Customers` (`customerID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Customer_Addresses`
--

LOCK TABLES `Customer_Addresses` WRITE;
/*!40000 ALTER TABLE `Customer_Addresses` DISABLE KEYS */;
/*!40000 ALTER TABLE `Customer_Addresses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Customers`
--

DROP TABLE IF EXISTS `Customers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Customers` (
  `customerID` int NOT NULL AUTO_INCREMENT,
  `firstName` varchar(50) NOT NULL,
  `lastName` varchar(50) NOT NULL,
  `address` varchar(255) NOT NULL,
  `telephone` varchar(16) NOT NULL,
  `email` varchar(255) NOT NULL,
  `fax` varchar(12) NOT NULL,
  `discount` int DEFAULT NULL,
  PRIMARY KEY (`customerID`)
) ENGINE=InnoDB AUTO_INCREMENT=1234567896 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Customers`
--

LOCK TABLES `Customers` WRITE;
/*!40000 ALTER TABLE `Customers` DISABLE KEYS */;
INSERT INTO `Customers` VALUES (1234567891,'Bobby','Marley','Oval Road 12, Camden Town','071723123','bob.marley@hoax.com','123121324',NULL),(1234567893,'Johnny','Knoxville','11 Utter street, London','+44 4444444444','jay@knox.com','1234567891',123),(1234567894,'Jimmy','Slim','Fatlane 33, EC1V 3AQ, London, United Kingdom','+22 43445444443','Jay@slim.com','1234566780',123),(1234567895,'Adam','Smith','1 Marzook Close','08974651','asdasd@gmail.com','',0);
/*!40000 ALTER TABLE `Customers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Invoice_Reminder`
--

DROP TABLE IF EXISTS `Invoice_Reminder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Invoice_Reminder` (
  `invoice_reminderID` int NOT NULL AUTO_INCREMENT,
  `amount` double NOT NULL,
  `date` date DEFAULT NULL,
  `InvoicesinvoiceNo` int NOT NULL,
  `CustomercustomerID` int NOT NULL,
  PRIMARY KEY (`invoice_reminderID`),
  KEY `FKInvoice_Re719985` (`InvoicesinvoiceNo`),
  KEY `FKInvoice_Re929615` (`CustomercustomerID`),
  CONSTRAINT `FKInvoice_Re719985` FOREIGN KEY (`InvoicesinvoiceNo`) REFERENCES `Invoices` (`invoiceNo`),
  CONSTRAINT `FKInvoice_Re929615` FOREIGN KEY (`CustomercustomerID`) REFERENCES `Customers` (`customerID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Invoice_Reminder`
--

LOCK TABLES `Invoice_Reminder` WRITE;
/*!40000 ALTER TABLE `Invoice_Reminder` DISABLE KEYS */;
/*!40000 ALTER TABLE `Invoice_Reminder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Invoices`
--

DROP TABLE IF EXISTS `Invoices`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Invoices` (
  `invoiceNo` int NOT NULL AUTO_INCREMENT,
  `labourRate` double NOT NULL,
  `vat` double NOT NULL DEFAULT '0',
  `total` double NOT NULL,
  `CustomercustomerID` int NOT NULL,
  `VehiclesregistrationNo` varchar(255) NOT NULL,
  PRIMARY KEY (`invoiceNo`),
  KEY `FKInvoices366939` (`VehiclesregistrationNo`),
  KEY `FKInvoices906218` (`CustomercustomerID`),
  CONSTRAINT `FKInvoices366939` FOREIGN KEY (`VehiclesregistrationNo`) REFERENCES `Vehicles` (`registrationNo`),
  CONSTRAINT `FKInvoices906218` FOREIGN KEY (`CustomercustomerID`) REFERENCES `Customers` (`customerID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Invoices`
--

LOCK TABLES `Invoices` WRITE;
/*!40000 ALTER TABLE `Invoices` DISABLE KEYS */;
/*!40000 ALTER TABLE `Invoices` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `JobSheets`
--

DROP TABLE IF EXISTS `JobSheets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `JobSheets` (
  `jobNo` int NOT NULL AUTO_INCREMENT,
  `jobDetails` varchar(255) NOT NULL,
  `dateBooked` date NOT NULL,
  `estimatedTime` varchar(255) NOT NULL,
  `completed` varchar(5) NOT NULL DEFAULT 'False',
  `completedBy` varchar(50) DEFAULT NULL,
  `CustomercustomerID` int NOT NULL,
  `VehiclesregistrationNo` varchar(255) NOT NULL,
  `AddressesaddressID` int NOT NULL,
  PRIMARY KEY (`jobNo`),
  KEY `FKJobSheets991412` (`CustomercustomerID`),
  KEY `FKJobSheets707019` (`VehiclesregistrationNo`),
  KEY `FKJobSheets434005` (`AddressesaddressID`),
  CONSTRAINT `FKJobSheets434005` FOREIGN KEY (`AddressesaddressID`) REFERENCES `Addresses` (`addressID`),
  CONSTRAINT `FKJobSheets707019` FOREIGN KEY (`VehiclesregistrationNo`) REFERENCES `Vehicles` (`registrationNo`),
  CONSTRAINT `FKJobSheets991412` FOREIGN KEY (`CustomercustomerID`) REFERENCES `Customers` (`customerID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `JobSheets`
--

LOCK TABLES `JobSheets` WRITE;
/*!40000 ALTER TABLE `JobSheets` DISABLE KEYS */;
/*!40000 ALTER TABLE `JobSheets` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Job_SpareParts`
--

DROP TABLE IF EXISTS `Job_SpareParts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Job_SpareParts` (
  `jobID` int NOT NULL,
  `partCode` varchar(255) NOT NULL,
  `ID` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`ID`),
  KEY `partCode` (`partCode`),
  KEY `jobID` (`jobID`),
  CONSTRAINT `Job_SpareParts_ibfk_2` FOREIGN KEY (`partCode`) REFERENCES `SpareParts` (`code`),
  CONSTRAINT `Job_SpareParts_ibfk_3` FOREIGN KEY (`jobID`) REFERENCES `Jobs` (`jobID`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Job_SpareParts`
--

LOCK TABLES `Job_SpareParts` WRITE;
/*!40000 ALTER TABLE `Job_SpareParts` DISABLE KEYS */;
INSERT INTO `Job_SpareParts` VALUES (27,'Y76432-89T5',16),(27,'Y76432-89T5',17),(27,'X66745877',18);
/*!40000 ALTER TABLE `Job_SpareParts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Jobs`
--

DROP TABLE IF EXISTS `Jobs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Jobs` (
  `jobID` int NOT NULL AUTO_INCREMENT,
  `jobType` varchar(500) DEFAULT NULL,
  `duration` float DEFAULT NULL,
  `dates` varchar(255) DEFAULT NULL,
  `parts` varchar(255) DEFAULT NULL,
  `motNo` varchar(255) DEFAULT NULL,
  `mileage` int DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `additionalInfo` varchar(500) DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL,
  `registrationNo` varchar(255) DEFAULT NULL,
  `mechanicID` int DEFAULT NULL,
  PRIMARY KEY (`jobID`),
  KEY `FKJB_regNo` (`registrationNo`),
  KEY `mechanicID` (`mechanicID`),
  CONSTRAINT `FKJB_regNo` FOREIGN KEY (`registrationNo`) REFERENCES `Vehicles` (`registrationNo`),
  CONSTRAINT `Jobs_ibfk_1` FOREIGN KEY (`mechanicID`) REFERENCES `Staff` (`staffID`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Jobs`
--

LOCK TABLES `Jobs` WRITE;
/*!40000 ALTER TABLE `Jobs` DISABLE KEYS */;
INSERT INTO `Jobs` VALUES (26,'MOT',123,'Tue Apr 19 23:01:32 BST 2022','','123456',567562,456.00,'',0,'EC1V3GG',1574527214),(27,'MOT',5.5,'Thu Apr 21 23:38:38 BST 2022','','',545421,1234.00,'',1,'EC1V3GG',1574527214),(29,'MOT',542,'Fri Apr 15 00:00:00 BST 2022','','6512651',65263,62556.00,'',0,'fe33 jmm',1574527214);
/*!40000 ALTER TABLE `Jobs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MOT_Reminder`
--

DROP TABLE IF EXISTS `MOT_Reminder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `MOT_Reminder` (
  `mot_reminderID` int NOT NULL AUTO_INCREMENT,
  `dateSent` date NOT NULL,
  `renewalTestDate` date NOT NULL,
  `CustomercustomerID` int NOT NULL,
  `VehiclesregistrationNo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`mot_reminderID`),
  KEY `FKMOT_Remind682965` (`CustomercustomerID`),
  KEY `FKMOT_Remind381398` (`VehiclesregistrationNo`),
  CONSTRAINT `FKMOT_Remind381398` FOREIGN KEY (`VehiclesregistrationNo`) REFERENCES `Vehicles` (`registrationNo`),
  CONSTRAINT `FKMOT_Remind682965` FOREIGN KEY (`CustomercustomerID`) REFERENCES `Customers` (`customerID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MOT_Reminder`
--

LOCK TABLES `MOT_Reminder` WRITE;
/*!40000 ALTER TABLE `MOT_Reminder` DISABLE KEYS */;
/*!40000 ALTER TABLE `MOT_Reminder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Orders`
--

DROP TABLE IF EXISTS `Orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Orders` (
  `orderNo` varchar(255) NOT NULL,
  `partName` varchar(255) NOT NULL,
  `quantity` int NOT NULL,
  `price` double NOT NULL,
  PRIMARY KEY (`orderNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Orders`
--

LOCK TABLES `Orders` WRITE;
/*!40000 ALTER TABLE `Orders` DISABLE KEYS */;
INSERT INTO `Orders` VALUES ('3214152122','Exaust Pipe',10,1.9);
/*!40000 ALTER TABLE `Orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Report`
--

DROP TABLE IF EXISTS `Report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Report` (
  `reportID` int NOT NULL AUTO_INCREMENT,
  `reportDate` date NOT NULL,
  PRIMARY KEY (`reportID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Report`
--

LOCK TABLES `Report` WRITE;
/*!40000 ALTER TABLE `Report` DISABLE KEYS */;
/*!40000 ALTER TABLE `Report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SpareParts`
--

DROP TABLE IF EXISTS `SpareParts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `SpareParts` (
  `code` varchar(255) NOT NULL,
  `partName` varchar(255) NOT NULL,
  `manufacturer` varchar(255) NOT NULL,
  `vehicleType` varchar(255) NOT NULL,
  `year` int NOT NULL,
  `stock` int NOT NULL DEFAULT '0',
  `price` double NOT NULL,
  `threshold` int NOT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SpareParts`
--

LOCK TABLES `SpareParts` WRITE;
/*!40000 ALTER TABLE `SpareParts` DISABLE KEYS */;
INSERT INTO `SpareParts` VALUES ('D43-78','Grommet','Vauxhill','Ofcorsa',2010,1,1.3,0),('G457','Water Pump','Fjord','Krapa',2014,6,56.7,0),('H456-9UI','Water Pump','Volva','S34',2008,0,124.34,0),('TY-R3','Heavy tread tyre','Fjord','Transit Van',2011,8,45,6),('X66745877','Grommet','Fjord','Krapa',2014,15,1.9,0),('X784/6352J','Exhaust Pipe','Ford','Focus',2014,6,1.9,0),('Y76432-89T5','Head Gasket','Nissan','Elgrand',2012,4,69.99,0);
/*!40000 ALTER TABLE `SpareParts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Staff`
--

DROP TABLE IF EXISTS `Staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Staff` (
  `staffID` int NOT NULL AUTO_INCREMENT,
  `firstName` varchar(255) NOT NULL,
  `lastName` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `hourlyRate` int DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`staffID`)
) ENGINE=InnoDB AUTO_INCREMENT=1574527233 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Staff`
--

LOCK TABLES `Staff` WRITE;
/*!40000 ALTER TABLE `Staff` DISABLE KEYS */;
INSERT INTO `Staff` VALUES (1574527208,'Penelope','Carr','Receptionist','Penelope','56DFFC22BD487CF69C74DA553ACC60DE6C60CAB2AB41A0BCCC4AD1AE6E614583',NULL,'penelopecarr@garits.org.uk'),(1574527210,'Glynne','Lancaster','Franchisee','Glynne','C134547585F40BF1516F5F1D01039F7E275DD10DB503D891F292F9CD246B0263',NULL,'glynnelancaster@garits.org.uk'),(1574527211,'Administrator','Role','Administrator','SYSDBA','1100FD026BF3B5B6EBD6B4B37D67F3D44B22A11ACFB0F506DBA2AAB159708F56',200,NULL),(1574527214,'Anthony','Wild','Mechanic','Anthony','522D20AAA1FE0A723459DB33367378D30ECA34E727CF0AF50372F478CB285329',105,'anthonywild@garits.org.uk'),(1574527223,'Changes','Work','','admin','8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918',123,'asdf@delete.mepliz'),(1574527231,'Gavin','Ross','Mechanic','Gavin','b26c4976aadc25dbf5c57d708a5567d83f40c1260c34c2676e749c52bb5427f9',105,'gavinross@garits.org.uk'),(1574527232,'Sunny','Evans','Foreperson','Sunny','b43ccb6b3ea7dcb81d90c7510aa44d3e7782f77cabddf02b30c44edaeff708c2',105,'sunnyevans@garits.org.uk');
/*!40000 ALTER TABLE `Staff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Staff_JobSheets`
--

DROP TABLE IF EXISTS `Staff_JobSheets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Staff_JobSheets` (
  `JobSheetsjobNo` int NOT NULL,
  `StaffstaffID` int NOT NULL,
  PRIMARY KEY (`JobSheetsjobNo`,`StaffstaffID`),
  KEY `FKStaff_JobS70168` (`StaffstaffID`),
  CONSTRAINT `FKStaff_JobS224605` FOREIGN KEY (`JobSheetsjobNo`) REFERENCES `JobSheets` (`jobNo`),
  CONSTRAINT `FKStaff_JobS70168` FOREIGN KEY (`StaffstaffID`) REFERENCES `Staff` (`staffID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Staff_JobSheets`
--

LOCK TABLES `Staff_JobSheets` WRITE;
/*!40000 ALTER TABLE `Staff_JobSheets` DISABLE KEYS */;
/*!40000 ALTER TABLE `Staff_JobSheets` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Staff_Orders`
--

DROP TABLE IF EXISTS `Staff_Orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Staff_Orders` (
  `StaffstaffID` int NOT NULL,
  `OrdersorderNo` varchar(255) NOT NULL,
  PRIMARY KEY (`StaffstaffID`,`OrdersorderNo`),
  KEY `FKStaff_Orde46831` (`OrdersorderNo`),
  CONSTRAINT `FKStaff_Orde219722` FOREIGN KEY (`StaffstaffID`) REFERENCES `Staff` (`staffID`),
  CONSTRAINT `FKStaff_Orde46831` FOREIGN KEY (`OrdersorderNo`) REFERENCES `Orders` (`orderNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Staff_Orders`
--

LOCK TABLES `Staff_Orders` WRITE;
/*!40000 ALTER TABLE `Staff_Orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `Staff_Orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Staff_Report`
--

DROP TABLE IF EXISTS `Staff_Report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Staff_Report` (
  `StaffstaffID` int NOT NULL,
  `ReportreportID` int NOT NULL,
  PRIMARY KEY (`StaffstaffID`,`ReportreportID`),
  KEY `FKStaff_Repo455414` (`ReportreportID`),
  CONSTRAINT `FKStaff_Repo455414` FOREIGN KEY (`ReportreportID`) REFERENCES `Report` (`reportID`),
  CONSTRAINT `FKStaff_Repo468579` FOREIGN KEY (`StaffstaffID`) REFERENCES `Staff` (`staffID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Staff_Report`
--

LOCK TABLES `Staff_Report` WRITE;
/*!40000 ALTER TABLE `Staff_Report` DISABLE KEYS */;
/*!40000 ALTER TABLE `Staff_Report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Task`
--

DROP TABLE IF EXISTS `Task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Task` (
  `taskNo` int NOT NULL AUTO_INCREMENT,
  `taskDescription` varchar(255) NOT NULL,
  `startDate` date NOT NULL,
  `endDate` date NOT NULL,
  `mechanic` varchar(255) NOT NULL,
  `status` varchar(255) NOT NULL,
  `SparePartscode` varchar(255) DEFAULT NULL,
  `JobSheetsjobNo` int NOT NULL,
  PRIMARY KEY (`taskNo`),
  KEY `FKTask83651` (`SparePartscode`),
  KEY `FKTask799594` (`JobSheetsjobNo`),
  CONSTRAINT `FKTask799594` FOREIGN KEY (`JobSheetsjobNo`) REFERENCES `JobSheets` (`jobNo`),
  CONSTRAINT `FKTask83651` FOREIGN KEY (`SparePartscode`) REFERENCES `SpareParts` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Task`
--

LOCK TABLES `Task` WRITE;
/*!40000 ALTER TABLE `Task` DISABLE KEYS */;
/*!40000 ALTER TABLE `Task` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Vehicles`
--

DROP TABLE IF EXISTS `Vehicles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Vehicles` (
  `registrationNo` varchar(255) NOT NULL,
  `make` varchar(255) NOT NULL,
  `model` varchar(255) NOT NULL,
  `engSerial` varchar(255) NOT NULL,
  `chassisNo` varchar(255) NOT NULL,
  `colour` varchar(255) NOT NULL,
  `MoTDate` date NOT NULL,
  `CustomercustomerID` int NOT NULL,
  PRIMARY KEY (`registrationNo`),
  UNIQUE KEY `engSerial` (`engSerial`),
  UNIQUE KEY `chassisNo` (`chassisNo`),
  KEY `FKVehicles675159` (`CustomercustomerID`),
  CONSTRAINT `FKVehicles675159` FOREIGN KEY (`CustomercustomerID`) REFERENCES `Customers` (`customerID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Vehicles`
--

LOCK TABLES `Vehicles` WRITE;
/*!40000 ALTER TABLE `Vehicles` DISABLE KEYS */;
INSERT INTO `Vehicles` VALUES ('ASDHNJASKHDASJDHAJS','Ford','Mondeo','234234233','465225','Purple','2022-05-14',1234567891),('AX68 ZJG','Ford','Focus','812381283123J','X323124','Pink','2020-03-19',1234567891),('EC1V3GG','sMercedes','AMG','E123123','E123123','Black','2020-01-11',1234567894),('EX68 ZJG','Ford','Focus','819765237J','X3246751','Pink','2022-01-01',1234567891),('fe33 jmm','Toyota','Avensus','sd2651','sd1651','Black','2022-04-12',1234567895),('ff60 knf','Mercedes','AMG','1321EASE','EASED123123','Black','2002-11-11',1234567893),('KN09 KLJ','Ford','Mondeo','457863806','23517867','Red','2020-03-30',1234567891),('LS62BAE','Peugeot','280','48020123','02173219','White','2022-12-10',1234567891),('TT11 OPI','Ford','Mondeo','567437965','34672876','Red','2022-01-02',1234567891),('WR68 SAY','Ford','Mondeo','769342653','76354248','Red','2020-02-10',1234567891);
/*!40000 ALTER TABLE `Vehicles` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-04-13 10:54:21
