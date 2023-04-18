-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: podsistem3
-- ------------------------------------------------------
-- Server version	8.0.31

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `narudzbina`
--

DROP TABLE IF EXISTS `narudzbina`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `narudzbina` (
  `IdNar` int NOT NULL AUTO_INCREMENT,
  `IdKor` int NOT NULL,
  `Iznos` double NOT NULL,
  `DatumVreme` varchar(45) NOT NULL,
  `Adresa` varchar(45) NOT NULL,
  `IdGrad` int NOT NULL,
  PRIMARY KEY (`IdNar`),
  UNIQUE KEY `IdNar_UNIQUE` (`IdNar`),
  KEY `IdKorNar_idx` (`IdKor`),
  CONSTRAINT `IdKorNar` FOREIGN KEY (`IdKor`) REFERENCES `korisnik` (`IdKor`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `narudzbina`
--

LOCK TABLES `narudzbina` WRITE;
/*!40000 ALTER TABLE `narudzbina` DISABLE KEYS */;
INSERT INTO `narudzbina` VALUES (6,1,1000,'2023-02-10 02:26:23','Medakoviceva88',3),(7,1,1000,'2023-02-10 02:30:58','Medakoviceva88',3),(8,1,1000,'2023-02-10 02:40:52','Medakoviceva88',3),(9,1,1000,'2023-02-10 02:44:30','Medakoviceva88',3),(10,1,1000,'2023-02-10 02:44:45','Medakoviceva88',3),(11,1,1000,'2023-02-10 02:45:43','Medakoviceva88',3),(12,1,1000,'2023-02-10 02:47:01','Medakoviceva88',3),(13,1,1000,'2023-02-10 02:50:34','Medakoviceva88',3),(14,5,2000,'2023-02-10 03:40:41','Medakoviceva88',3),(15,1,1500,'2023-02-10 03:48:38','Vojvode Vlahovica 37b',3),(16,1,160,'2023-02-11 00:54:17','4. decembar bb',1),(17,1,4375,'2023-02-11 01:13:47','4. decembar bb',1);
/*!40000 ALTER TABLE `narudzbina` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-02-16 21:52:43
