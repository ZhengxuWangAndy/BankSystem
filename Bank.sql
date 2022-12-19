-- MySQL dump 10.13  Distrib 8.0.31, for macos12 (arm64)
--
-- Host: localhost    Database: Bank
-- ------------------------------------------------------
-- Server version	8.0.28

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
-- Table structure for table `Currencies`
--

DROP TABLE IF EXISTS `Currencies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Currencies` (
  `cid` varchar(45) NOT NULL,
  `currencyName` varchar(45) NOT NULL,
  `exchangeRate` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Currencies`
--

LOCK TABLES `Currencies` WRITE;
/*!40000 ALTER TABLE `Currencies` DISABLE KEYS */;
INSERT INTO `Currencies` VALUES ('2cbb794f-64be-40e6-8b59-f2adea1ae002','USD','1'),('4223369d-048e-41ea-b2b7-3d479eb45ea9','EUR','0.94'),('acbcf47f-480d-4a74-906a-affcb891dfb8','CNY','7.02');
/*!40000 ALTER TABLE `Currencies` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CustomerInfo`
--

DROP TABLE IF EXISTS `CustomerInfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `CustomerInfo` (
  `userID` varchar(45) NOT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  PRIMARY KEY (`userID`),
  UNIQUE KEY `userID_UNIQUE` (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CustomerInfo`
--

LOCK TABLES `CustomerInfo` WRITE;
/*!40000 ALTER TABLE `CustomerInfo` DISABLE KEYS */;
INSERT INTO `CustomerInfo` VALUES ('43648221-c3c7-4993-a2ef-76e689fa6638','test1','test1'),('e8723158-6569-4606-8c2e-6c482efa6417','test2','test2');
/*!40000 ALTER TABLE `CustomerInfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CustomerStockInfo`
--

DROP TABLE IF EXISTS `CustomerStockInfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `CustomerStockInfo` (
  `userID` varchar(45) NOT NULL,
  `stockID` varchar(45) NOT NULL,
  `stockCount` int NOT NULL,
  `purchasePrice` decimal(10,0) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CustomerStockInfo`
--

LOCK TABLES `CustomerStockInfo` WRITE;
/*!40000 ALTER TABLE `CustomerStockInfo` DISABLE KEYS */;
INSERT INTO `CustomerStockInfo` VALUES ('43648221-c3c7-4993-a2ef-76e689fa6638','0224b0a9-fcfd-4b40-8582-69d9db3614cf',10,8),('43648221-c3c7-4993-a2ef-76e689fa6638','4385b515-c6f8-4e51-9233-ee0de23aa1cb',20,6),('e8723158-6569-4606-8c2e-6c482efa6417','0224b0a9-fcfd-4b40-8582-69d9db3614cf',15,5),('e8723158-6569-4606-8c2e-6c482efa6417','4385b515-c6f8-4e51-9233-ee0de23aa1cb',5,7);
/*!40000 ALTER TABLE `CustomerStockInfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `DepositAccounts`
--

DROP TABLE IF EXISTS `DepositAccounts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `DepositAccounts` (
  `uid` varchar(45) NOT NULL,
  `cid` varchar(45) NOT NULL,
  `aid` varchar(45) NOT NULL,
  `amount` decimal(20,0) NOT NULL,
  `accNum` int NOT NULL,
  PRIMARY KEY (`aid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DepositAccounts`
--

LOCK TABLES `DepositAccounts` WRITE;
/*!40000 ALTER TABLE `DepositAccounts` DISABLE KEYS */;
INSERT INTO `DepositAccounts` VALUES ('43648221-c3c7-4993-a2ef-76e689fa6638','2cbb794f-64be-40e6-8b59-f2adea1ae002','8a5c826f-e313-484c-93fb-aa75efea26da',100,33333333),('e8723158-6569-4606-8c2e-6c482efa6417','2cbb794f-64be-40e6-8b59-f2adea1ae002','a1516cc4-cc43-44c0-9030-6f9eb491ad2b',200,22222222);
/*!40000 ALTER TABLE `DepositAccounts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `LoanAccounts`
--

DROP TABLE IF EXISTS `LoanAccounts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `LoanAccounts` (
  `uid` varchar(45) NOT NULL,
  `aid` varchar(45) NOT NULL,
  `amount` decimal(20,0) NOT NULL,
  `accNum` int DEFAULT NULL,
  PRIMARY KEY (`aid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `LoanAccounts`
--

LOCK TABLES `LoanAccounts` WRITE;
/*!40000 ALTER TABLE `LoanAccounts` DISABLE KEYS */;
INSERT INTO `LoanAccounts` VALUES ('43648221-c3c7-4993-a2ef-76e689fa6638','b05576c3-0168-4f1b-90ba-6862d2fd75bc',100,88888888);
/*!40000 ALTER TABLE `LoanAccounts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ManagerInfo`
--

DROP TABLE IF EXISTS `ManagerInfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ManagerInfo` (
  `userID` varchar(45) NOT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  PRIMARY KEY (`userID`),
  UNIQUE KEY `userID_UNIQUE` (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ManagerInfo`
--

LOCK TABLES `ManagerInfo` WRITE;
/*!40000 ALTER TABLE `ManagerInfo` DISABLE KEYS */;
INSERT INTO `ManagerInfo` VALUES ('b480b4fc-88e3-4cc7-bee4-6a61857fbb71','admin1','admin1'),('e7a73b75-c76a-4958-9c6c-382479bf0da6','admin2','admin2');
/*!40000 ALTER TABLE `ManagerInfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SecurityAccounts`
--

DROP TABLE IF EXISTS `SecurityAccounts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `SecurityAccounts` (
  `uid` varchar(45) NOT NULL,
  `aid` varchar(45) NOT NULL,
  `amount` decimal(20,0) NOT NULL,
  `accNum` int NOT NULL,
  PRIMARY KEY (`aid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SecurityAccounts`
--

LOCK TABLES `SecurityAccounts` WRITE;
/*!40000 ALTER TABLE `SecurityAccounts` DISABLE KEYS */;
INSERT INTO `SecurityAccounts` VALUES ('43648221-c3c7-4993-a2ef-76e689fa6638','32fbf25d-411f-4315-8ded-58f60459329b',100,55555555);
/*!40000 ALTER TABLE `SecurityAccounts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `StockMarket`
--

DROP TABLE IF EXISTS `StockMarket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `StockMarket` (
  `stockID` varchar(45) NOT NULL,
  `stockName` varchar(45) NOT NULL,
  `stockPrice` decimal(20,0) NOT NULL,
  `stockHighPrice` decimal(20,0) DEFAULT NULL,
  `date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`stockID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `StockMarket`
--

LOCK TABLES `StockMarket` WRITE;
/*!40000 ALTER TABLE `StockMarket` DISABLE KEYS */;
INSERT INTO `StockMarket` VALUES ('0224b0a9-fcfd-4b40-8582-69d9db3614cf','APPL',11,14,'2022-12-04 22:27:03'),('4385b515-c6f8-4e51-9233-ee0de23aa1cb','TESLA',10,10,'2022-12-04 22:27:54'),('7a17c335-8a5e-4e03-8930-e1adbf092a56','AMZN',9,9,'2022-12-04 22:30:58');
/*!40000 ALTER TABLE `StockMarket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Transactions`
--

DROP TABLE IF EXISTS `Transactions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Transactions` (
  `tid` varchar(45) NOT NULL,
  `From_aid` varchar(45) DEFAULT NULL,
  `To_aid` varchar(45) DEFAULT NULL,
  `amount` decimal(20,0) NOT NULL,
  `currencyId` varchar(45) NOT NULL,
  `fee` decimal(20,0) NOT NULL,
  `date` timestamp NOT NULL,
  `From_accNum` varchar(45) DEFAULT NULL,
  `To_accNum` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`tid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Transactions`
--

LOCK TABLES `Transactions` WRITE;
/*!40000 ALTER TABLE `Transactions` DISABLE KEYS */;
/*!40000 ALTER TABLE `Transactions` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-12-04 19:11:41