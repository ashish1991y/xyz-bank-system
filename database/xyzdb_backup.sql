-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: xyzbank
-- ------------------------------------------------------
-- Server version	8.0.36

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
-- Table structure for table `account_document_mapping`
--

DROP TABLE IF EXISTS `account_document_mapping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account_document_mapping` (
  `account_id` bigint NOT NULL,
  `document_id` bigint NOT NULL,
  PRIMARY KEY (`account_id`,`document_id`),
  KEY `FK62gx18kdw3tyl8krirbnfq0g3` (`document_id`),
  CONSTRAINT `FK280slkk2cg963mg9fp48ii6ot` FOREIGN KEY (`account_id`) REFERENCES `account_master` (`id`),
  CONSTRAINT `FK62gx18kdw3tyl8krirbnfq0g3` FOREIGN KEY (`document_id`) REFERENCES `document` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_document_mapping`
--

LOCK TABLES `account_document_mapping` WRITE;
/*!40000 ALTER TABLE `account_document_mapping` DISABLE KEYS */;
/*!40000 ALTER TABLE `account_document_mapping` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `account_master`
--

DROP TABLE IF EXISTS `account_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account_master` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_date` datetime(6) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `update_date` datetime(6) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `balance` decimal(38,2) DEFAULT NULL,
  `iban_number` varchar(255) DEFAULT NULL,
  `status_id` bigint DEFAULT NULL,
  `account_type_id` bigint DEFAULT NULL,
  `currency_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK20k0pih9a78i89qusujaqhxnd` (`status_id`),
  KEY `FKkwf7cth1xq8gdp3octhc4ldcl` (`account_type_id`),
  KEY `FKnvx9uui5yldccvuwiaux7ggh7` (`currency_id`),
  CONSTRAINT `FK20k0pih9a78i89qusujaqhxnd` FOREIGN KEY (`status_id`) REFERENCES `status_master` (`id`),
  CONSTRAINT `FKkwf7cth1xq8gdp3octhc4ldcl` FOREIGN KEY (`account_type_id`) REFERENCES `account_type_master` (`id`),
  CONSTRAINT `FKnvx9uui5yldccvuwiaux7ggh7` FOREIGN KEY (`currency_id`) REFERENCES `currency_master` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_master`
--

LOCK TABLES `account_master` WRITE;
/*!40000 ALTER TABLE `account_master` DISABLE KEYS */;
/*!40000 ALTER TABLE `account_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `account_status_master`
--

DROP TABLE IF EXISTS `account_status_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account_status_master` (
  `create_date` datetime(6) DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `status_id` bigint DEFAULT NULL,
  `update_date` datetime(6) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKheiwbsves7kcdfdbj9j96f7i3` (`status_id`),
  CONSTRAINT `FKheiwbsves7kcdfdbj9j96f7i3` FOREIGN KEY (`status_id`) REFERENCES `status_master` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_status_master`
--

LOCK TABLES `account_status_master` WRITE;
/*!40000 ALTER TABLE `account_status_master` DISABLE KEYS */;
INSERT INTO `account_status_master` VALUES ('2024-05-01 21:18:37.000000',1,1,'2024-05-01 21:18:37.000000','system','OPEN','system'),('2024-05-01 21:18:37.000000',2,1,'2024-05-01 21:18:37.000000','system','CLOSE','system'),('2024-05-01 21:18:37.000000',3,1,'2024-05-01 21:18:37.000000','system','PENDING','system');
/*!40000 ALTER TABLE `account_status_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `account_type_master`
--

DROP TABLE IF EXISTS `account_type_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account_type_master` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_date` datetime(6) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `update_date` datetime(6) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `status_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8s3108q7srlidutusi9ex4g7c` (`status_id`),
  CONSTRAINT `FK8s3108q7srlidutusi9ex4g7c` FOREIGN KEY (`status_id`) REFERENCES `status_master` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_type_master`
--

LOCK TABLES `account_type_master` WRITE;
/*!40000 ALTER TABLE `account_type_master` DISABLE KEYS */;
INSERT INTO `account_type_master` VALUES (1,'2024-04-29 14:00:55.000000','system','2024-04-29 14:00:55.000000','system','SAVING',1),(2,'2024-04-29 14:00:55.000000','system','2024-04-29 14:00:55.000000','system','CREDIT',1),(3,'2024-04-29 14:00:55.000000','system','2024-04-29 14:00:55.000000','system','LOAN',1);
/*!40000 ALTER TABLE `account_type_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `address` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_date` datetime(6) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `update_date` datetime(6) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `street_address` varchar(255) DEFAULT NULL,
  `status_id` bigint DEFAULT NULL,
  `country_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKr5btncvcyy48oqt6hxfsbft3q` (`status_id`),
  KEY `FKe54x81nmccsk5569hsjg1a6ka` (`country_id`),
  CONSTRAINT `FKe54x81nmccsk5569hsjg1a6ka` FOREIGN KEY (`country_id`) REFERENCES `country` (`id`),
  CONSTRAINT `FKr5btncvcyy48oqt6hxfsbft3q` FOREIGN KEY (`status_id`) REFERENCES `status_master` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `country`
--

DROP TABLE IF EXISTS `country`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `country` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_date` datetime(6) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `update_date` datetime(6) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `country_code` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `status_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKoce3ihq208w5la3a24nnchast` (`status_id`),
  CONSTRAINT `FKoce3ihq208w5la3a24nnchast` FOREIGN KEY (`status_id`) REFERENCES `status_master` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `country`
--

LOCK TABLES `country` WRITE;
/*!40000 ALTER TABLE `country` DISABLE KEYS */;
INSERT INTO `country` VALUES (1,'2024-04-29 14:09:07.000000','system','2024-04-29 14:09:07.000000','system','NL','NETHERLANDS',1),(2,'2024-04-29 14:09:07.000000','system','2024-04-29 14:09:07.000000','system','BE','BELGIUM',1),(3,'2024-04-29 20:21:22.401000','system','2024-04-29 20:21:22.401000','system','BE','India',3),(4,'2024-05-01 17:56:04.163000',NULL,'2024-05-01 17:56:04.163000',NULL,'DE','Germany',3);
/*!40000 ALTER TABLE `country` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `currency_master`
--

DROP TABLE IF EXISTS `currency_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `currency_master` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_date` datetime(6) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `update_date` datetime(6) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `status_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKounsl2bk5ej8duvh76a198k5r` (`status_id`),
  CONSTRAINT `FKounsl2bk5ej8duvh76a198k5r` FOREIGN KEY (`status_id`) REFERENCES `status_master` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `currency_master`
--

LOCK TABLES `currency_master` WRITE;
/*!40000 ALTER TABLE `currency_master` DISABLE KEYS */;
INSERT INTO `currency_master` VALUES (1,'2024-04-29 14:01:56.000000','system','2024-04-29 14:01:56.000000','system','DOLLAR',1),(2,'2024-04-29 14:01:56.000000','system','2024-04-29 14:01:56.000000','system','EURO',1);
/*!40000 ALTER TABLE `currency_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `document`
--

DROP TABLE IF EXISTS `document`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `document` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_date` datetime(6) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `update_date` datetime(6) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `document_path` varchar(255) DEFAULT NULL,
  `status_id` bigint DEFAULT NULL,
  `type_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhrv4utjaiswsn6449if17vq2l` (`status_id`),
  KEY `FKnrrk2k6kh8eo7hkua45oya48n` (`type_id`),
  CONSTRAINT `FKhrv4utjaiswsn6449if17vq2l` FOREIGN KEY (`status_id`) REFERENCES `status_master` (`id`),
  CONSTRAINT `FKnrrk2k6kh8eo7hkua45oya48n` FOREIGN KEY (`type_id`) REFERENCES `document_type_master` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `document`
--

LOCK TABLES `document` WRITE;
/*!40000 ALTER TABLE `document` DISABLE KEYS */;
/*!40000 ALTER TABLE `document` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `document_type_master`
--

DROP TABLE IF EXISTS `document_type_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `document_type_master` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_date` datetime(6) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `update_date` datetime(6) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `status_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKffk2aumc4sm02l46s3npml0xv` (`status_id`),
  CONSTRAINT `FKffk2aumc4sm02l46s3npml0xv` FOREIGN KEY (`status_id`) REFERENCES `status_master` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `document_type_master`
--

LOCK TABLES `document_type_master` WRITE;
/*!40000 ALTER TABLE `document_type_master` DISABLE KEYS */;
INSERT INTO `document_type_master` VALUES (1,'2024-04-29 14:02:40.000000','system','2024-04-29 14:02:40.000000','system','PASSPORT',1),(2,'2024-04-29 14:02:40.000000','system','2024-04-29 14:02:40.000000','system','DRIVING LICENSE',1),(3,'2024-04-29 14:02:40.000000','system','2024-04-29 14:02:40.000000','system','RP CARD',1);
/*!40000 ALTER TABLE `document_type_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_master`
--

DROP TABLE IF EXISTS `role_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role_master` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_date` datetime(6) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `update_date` datetime(6) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `status_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKnblkl7yqqh1huxy3oy4qdkqcl` (`status_id`),
  CONSTRAINT `FKnblkl7yqqh1huxy3oy4qdkqcl` FOREIGN KEY (`status_id`) REFERENCES `status_master` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_master`
--

LOCK TABLES `role_master` WRITE;
/*!40000 ALTER TABLE `role_master` DISABLE KEYS */;
INSERT INTO `role_master` VALUES (1,'2024-04-29 14:01:08.000000','system','2024-04-29 14:01:08.000000','system','USER',1),(2,'2024-04-29 14:01:08.000000','system','2024-04-29 14:01:08.000000','system','ADMIN',1);
/*!40000 ALTER TABLE `role_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `status_master`
--

DROP TABLE IF EXISTS `status_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `status_master` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_date` datetime(6) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `update_date` datetime(6) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status_master`
--

LOCK TABLES `status_master` WRITE;
/*!40000 ALTER TABLE `status_master` DISABLE KEYS */;
INSERT INTO `status_master` VALUES (1,'2024-04-29 13:59:58.000000','system','ACTIVE','2024-04-29 13:59:58.000000','system'),(2,'2024-04-29 13:59:58.000000','system','DELETE','2024-04-29 13:59:58.000000','system'),(3,'2024-04-29 13:59:58.000000','system','INACTIVE','2024-04-29 13:59:58.000000','system');
/*!40000 ALTER TABLE `status_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_account_mapping`
--

DROP TABLE IF EXISTS `user_account_mapping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_account_mapping` (
  `user_id` bigint NOT NULL,
  `account_id` bigint NOT NULL,
  PRIMARY KEY (`user_id`,`account_id`),
  KEY `FKsl3jpxt9phto2ssg3kmgoxpp1` (`account_id`),
  CONSTRAINT `FKk357sdpkbqve0e4nnudm3an73` FOREIGN KEY (`user_id`) REFERENCES `user_master` (`id`),
  CONSTRAINT `FKsl3jpxt9phto2ssg3kmgoxpp1` FOREIGN KEY (`account_id`) REFERENCES `account_master` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_account_mapping`
--

LOCK TABLES `user_account_mapping` WRITE;
/*!40000 ALTER TABLE `user_account_mapping` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_account_mapping` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_address`
--

DROP TABLE IF EXISTS `user_address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_address` (
  `address_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`address_id`,`user_id`),
  KEY `FKn08ga635gryunqos6onptg5kp` (`user_id`),
  CONSTRAINT `FKdaaxogn1ss81gkcsdn05wi6jp` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`),
  CONSTRAINT `FKn08ga635gryunqos6onptg5kp` FOREIGN KEY (`user_id`) REFERENCES `user_master` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_address`
--

LOCK TABLES `user_address` WRITE;
/*!40000 ALTER TABLE `user_address` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_address_mapping`
--

DROP TABLE IF EXISTS `user_address_mapping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_address_mapping` (
  `user_id` bigint NOT NULL,
  `address_id` bigint NOT NULL,
  PRIMARY KEY (`user_id`,`address_id`),
  KEY `FKrunkro0c5b7werudk5cktp77u` (`address_id`),
  CONSTRAINT `FKiy4bhluf3msxtlgs825eyrxj4` FOREIGN KEY (`user_id`) REFERENCES `user_master` (`id`),
  CONSTRAINT `FKrunkro0c5b7werudk5cktp77u` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_address_mapping`
--

LOCK TABLES `user_address_mapping` WRITE;
/*!40000 ALTER TABLE `user_address_mapping` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_address_mapping` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_login_history`
--

DROP TABLE IF EXISTS `user_login_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_login_history` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_date` datetime(6) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `update_date` datetime(6) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `login_status` varchar(255) DEFAULT NULL,
  `status_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhd4uls1lfohu44wie7minn66v` (`status_id`),
  KEY `FK5f3l7y8hef8d1nyyxuoe2v2wp` (`user_id`),
  CONSTRAINT `FK5f3l7y8hef8d1nyyxuoe2v2wp` FOREIGN KEY (`user_id`) REFERENCES `user_master` (`id`),
  CONSTRAINT `FKhd4uls1lfohu44wie7minn66v` FOREIGN KEY (`status_id`) REFERENCES `status_master` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_login_history`
--

LOCK TABLES `user_login_history` WRITE;
/*!40000 ALTER TABLE `user_login_history` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_login_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_master`
--

DROP TABLE IF EXISTS `user_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_master` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_date` datetime(6) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `update_date` datetime(6) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `date_of_birth` date DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `status_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKp8956t85fr113kos1dqe813ot` (`status_id`),
  CONSTRAINT `FKp8956t85fr113kos1dqe813ot` FOREIGN KEY (`status_id`) REFERENCES `status_master` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_master`
--

LOCK TABLES `user_master` WRITE;
/*!40000 ALTER TABLE `user_master` DISABLE KEYS */;
INSERT INTO `user_master` VALUES (1,'2024-04-29 14:09:28.368000',NULL,'2024-04-29 14:09:28.368000',NULL,'2001-10-26','ashish','#7-9gt[[uH@y','ashish10',NULL),(2,'2024-04-29 14:15:05.109000',NULL,'2024-04-29 14:15:05.109000',NULL,'2001-10-26','ashish','m[9L&xcfctAr','ashish',NULL),(3,'2024-04-29 14:16:31.817000',NULL,'2024-04-29 14:16:31.817000',NULL,'2001-10-26','ashish','AZG78EIOu_4n','ashish1',NULL),(4,'2024-04-29 14:17:17.498000',NULL,'2024-04-29 14:17:17.498000',NULL,'2001-10-26','ashish','Bg7j@@[2U1Iv','ashish2',NULL),(5,'2024-04-29 14:31:51.836000',NULL,'2024-04-29 14:31:51.836000',NULL,'2001-10-26','ashish','TpgUo-2L7_Ji','ashish3',1),(6,'2024-04-29 14:44:32.281000',NULL,'2024-04-29 14:44:32.281000',NULL,'2001-10-26','Nadeem','R]kvPbXMsczM','Nadeem1',1),(7,'2024-04-29 16:29:18.361000',NULL,'2024-04-29 16:29:18.361000',NULL,'2001-10-26','Nadeem','UXajFko8iH1&','Nadeem2',1),(8,'2024-04-29 16:52:15.244000',NULL,'2024-04-29 16:52:15.244000',NULL,'2001-10-26','Nadeem','_Jecu&#L8+If','Nadeem3',1),(9,'2024-04-29 17:09:27.061000',NULL,'2024-04-29 17:09:27.061000',NULL,'2001-10-26','Nadeem','d8i+DxP[OtTx','Nadeem4',1),(10,'2024-04-29 17:10:56.658000',NULL,'2024-04-29 17:10:56.658000',NULL,'2001-10-26','Nadeem','FXF*Ty9WC(H?','Nadeem5',1),(11,'2024-04-29 18:18:55.479000',NULL,'2024-04-29 18:18:55.479000',NULL,'2001-10-26','Nadeem','h#ohde]JzqwD','Nadeem6',1),(12,'2024-04-29 18:23:45.101000',NULL,'2024-04-29 18:23:45.101000',NULL,'2001-10-26','Nadeem','HwtarPMQfbqT','Nadeem7',1),(13,'2024-04-29 18:25:25.104000',NULL,'2024-04-29 18:25:25.104000',NULL,'2001-10-26','Nadeem','#]flneqAVFmt','Nadeem8',1),(14,'2024-04-29 18:26:45.451000',NULL,'2024-04-29 18:26:45.451000',NULL,'2001-10-26','Nadeem','7LT-bpvk+vOg','Nadeem9',1),(15,'2024-04-30 23:00:38.389000',NULL,'2024-04-30 23:00:38.389000',NULL,'2001-10-26','ashish','OJ_oqPJkccsP','ashish112',1),(16,'2024-04-30 23:03:26.575000',NULL,'2024-04-30 23:03:26.575000',NULL,'2001-10-26','ashish','cJYF5L-YXble','ashish113',1),(17,'2024-04-30 23:05:54.484000',NULL,'2024-04-30 23:05:54.484000',NULL,'2001-10-26','ashish','8ExKBWJUG()i','ashish114',1),(18,'2024-04-30 23:14:26.910000',NULL,'2024-04-30 23:14:26.910000',NULL,'2001-10-26','ashish','1EuLEYKUfQ*D','ashish115',1),(19,'2024-04-30 23:16:44.526000',NULL,'2024-04-30 23:16:44.526000',NULL,'2001-10-26','ashish','K!C%(*VUSKOk','ashish116',1),(20,'2024-04-30 23:20:08.382000',NULL,'2024-04-30 23:20:08.382000',NULL,'2001-10-26','ashish','RV=tIUumC4i1','ashish117',1),(25,'2024-05-01 18:15:57.023000',NULL,'2024-05-01 18:15:57.023000',NULL,'2001-10-26','ashish','B*jq77!XtrvO','ashish22',1),(26,'2024-05-01 18:17:44.185000',NULL,'2024-05-01 18:17:44.185000',NULL,'2001-10-26','ashish','to_kiFstpc2s','ashish23',1),(27,'2024-05-01 18:24:17.005000',NULL,'2024-05-01 18:24:17.005000',NULL,'2001-10-26','ashish','O@DcsM[kds#e','ashish24',1),(31,'2024-05-01 18:30:34.431000',NULL,'2024-05-01 18:30:34.431000',NULL,'2001-10-26','ashish','S?KK@JLsy0Jg','ashish26',1),(32,'2024-05-01 19:05:49.979000',NULL,'2024-05-01 19:05:49.979000',NULL,'2001-10-26','ashish','[BqbWeN9$i[a','ashish27',1),(33,'2024-05-01 20:22:41.670000',NULL,'2024-05-01 20:22:41.670000',NULL,'2001-10-26','ashish','%(G5j&hBIUzw','ashish28',1);
/*!40000 ALTER TABLE `user_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role_mapping`
--

DROP TABLE IF EXISTS `user_role_mapping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role_mapping` (
  `user_id` bigint NOT NULL,
  `role_id` bigint NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKoa0pd4y1ith4vcpfffiim0vj` (`role_id`),
  CONSTRAINT `FKkn33ikyihiruu32t191cvt3k0` FOREIGN KEY (`user_id`) REFERENCES `user_master` (`id`),
  CONSTRAINT `FKoa0pd4y1ith4vcpfffiim0vj` FOREIGN KEY (`role_id`) REFERENCES `role_master` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role_mapping`
--

LOCK TABLES `user_role_mapping` WRITE;
/*!40000 ALTER TABLE `user_role_mapping` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_role_mapping` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-01 21:34:19
