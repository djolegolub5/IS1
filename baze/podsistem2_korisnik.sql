-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: podsistem2
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
-- Table structure for table `korisnik`
--

DROP TABLE IF EXISTS `korisnik`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `korisnik` (
  `IdKor` int NOT NULL AUTO_INCREMENT,
  `KorisnickoIme` varchar(45) NOT NULL,
  `Sifra` varchar(45) NOT NULL,
  `Ime` varchar(45) NOT NULL,
  `Prezime` varchar(45) NOT NULL,
  `Adresa` varchar(45) NOT NULL,
  `IdGrad` int NOT NULL,
  `Novac` double NOT NULL,
  PRIMARY KEY (`IdKor`),
  UNIQUE KEY `IdKor_UNIQUE` (`IdKor`),
  UNIQUE KEY `KorisnickoIme_UNIQUE` (`KorisnickoIme`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `korisnik`
--

LOCK TABLES `korisnik` WRITE;
/*!40000 ALTER TABLE `korisnik` DISABLE KEYS */;
INSERT INTO `korisnik` VALUES (1,'djolegolub5','123','Djordje','Golubovic','Medakoviceva 88',3,15965.5),(2,'peraperic123','pp321','Pera','Peric','Medakoviceva 88',3,10000),(3,'mikamikic01','mikic01','Mika','Mikic','Umljenoviceva',4,500),(4,'bocamosur8','banms1901','Bogdan','Mosurovic','Vojvode Vlahovica 37b',3,4000),(5,'petar.langovic02','peroLango20','Petar','Langovic','Njegoseva 4',4,2000),(6,'memovic02','adMem123','Adisa','Memovic','Valterova 19',7,1000),(7,'marija.gojak','maja_kraljica02','Marija','Gojak','Dalmatinska 3',6,7000),(8,'nikola','nikola111','Nikola','Vipic','Juzni bulevar 3',3,700),(9,'jelica','jelica123','Jelica','Ruzic','Svrakino selo',7,7000),(10,'paja','paki123','Pavle','Jovanovic','Zicka 5',12,5000),(11,'ivan','ivanivanivan333','Ivan','Ivanovic','Svrakino selo 16',7,85000.5),(12,'mare12345','12345','Marko','Markovic','30. februar',15,8500);
/*!40000 ALTER TABLE `korisnik` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-02-16 21:52:44
