-- MySQL dump 10.13  Distrib 8.0.36, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: DATN_SUM24_SD01
-- ------------------------------------------------------
-- Server version	8.0.39-0ubuntu0.20.04.1

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
-- Table structure for table `anh`
--

DROP TABLE IF EXISTS `anh`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `anh` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `url` varchar(255) DEFAULT NULL,
  `id_san_pham` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2dp9xg8gpf8t1jfw4wxb35o7y` (`id_san_pham`),
  CONSTRAINT `FK2dp9xg8gpf8t1jfw4wxb35o7y` FOREIGN KEY (`id_san_pham`) REFERENCES `san_pham` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `anh`
--

LOCK TABLES `anh` WRITE;
/*!40000 ALTER TABLE `anh` DISABLE KEYS */;
INSERT INTO `anh` VALUES (3,'vn-11134201-7r98o-lloufk5z5bz37b.jpg',1),(4,'vn-11134201-7r98o-lloufkdgewv3ef.jpg',1),(5,'vn-11134201-7r98o-lloufkccgjj300.jpg',1),(6,'vn-11134201-7r98o-lloufk1svyzj78.jpg',1),(7,'vn-11134201-7r98o-lloufk9kklpba5.jpg',2),(8,'vn-11134201-7r98o-lkjebzhdcn80e1.jpg',2),(9,'vn-11134201-7r98o-lloufjy718tb92.jpg',2),(10,'vn-11134201-7r98o-lloufk3gtj3394.jpg',2),(11,'vn-11134207-7r98o-lofgcn05sp9vf6.jpg',3),(12,'vn-11134207-7r98o-lofgcn04xss3a3.jpg',3),(13,'vn-11134207-7r98o-lofgcn05d90zaf.jpg',3),(14,'vn-11134207-7r98o-lofgcn04xsqf8a.jpg',3),(15,'vn-11134201-7r98o-lloufjy718tb92.jpg',4),(16,'vn-11134201-7r98o-lloufk3gtj3394.jpg',4),(17,'vn-11134201-7r98o-lloufkdgewv3ef (1).jpg',4),(18,'vn-11134201-7r98o-lloufk5z5bz37b.jpg',4),(23,'vn-11134201-7r98o-lty0oiia4ayfdf.jpg',5),(24,'vn-11134201-7r98o-lty0oi8ui3v337.jpg',5),(25,'vn-11134201-7r98o-lty0oi5imz3d31.jpg',5),(26,'vn-11134201-7r98o-lty0ojr8aihm63.jpg',5),(27,'vn-11134201-7r98o-lty0oiia4ayfdf.jpg',6),(28,'vn-11134201-7r98o-lty0oi8ui3v337.jpg',6),(29,'vn-11134201-7r98o-lty0oi5imz3d31.jpg',6),(30,'vn-11134201-7r98o-lkjebzhdcn80e1.jpg',7);
/*!40000 ALTER TABLE `anh` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chi_tiet_san_pham`
--

DROP TABLE IF EXISTS `chi_tiet_san_pham`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chi_tiet_san_pham` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `anh_chinh` varchar(255) DEFAULT NULL,
  `gia_ban` decimal(19,2) DEFAULT NULL,
  `gia_mac_dinh` decimal(19,2) DEFAULT NULL,
  `ngay_sua` datetime(6) DEFAULT NULL,
  `ngay_tao` date DEFAULT NULL,
  `qr` varchar(255) DEFAULT NULL,
  `so_luong_ton` int DEFAULT NULL,
  `trang_thai` tinyint DEFAULT NULL,
  `id_de_giay` bigint DEFAULT NULL,
  `id_kich_thuoc` bigint DEFAULT NULL,
  `id_mau_sac` bigint DEFAULT NULL,
  `id_san_pham` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKtj2f11b2f5l0l8rh9wfnyena2` (`id_de_giay`),
  KEY `FKbfcsbyjh36fo547ik10d8ejc4` (`id_kich_thuoc`),
  KEY `FKhrc41nqmp3jsh42ikergp7qsd` (`id_mau_sac`),
  KEY `FKhry1oewlwwhwhuqhr1tinw6l6` (`id_san_pham`),
  CONSTRAINT `FKbfcsbyjh36fo547ik10d8ejc4` FOREIGN KEY (`id_kich_thuoc`) REFERENCES `kich_thuoc` (`id`),
  CONSTRAINT `FKhrc41nqmp3jsh42ikergp7qsd` FOREIGN KEY (`id_mau_sac`) REFERENCES `mau_sac` (`id`),
  CONSTRAINT `FKhry1oewlwwhwhuqhr1tinw6l6` FOREIGN KEY (`id_san_pham`) REFERENCES `san_pham` (`id`),
  CONSTRAINT `FKtj2f11b2f5l0l8rh9wfnyena2` FOREIGN KEY (`id_de_giay`) REFERENCES `de_giay` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chi_tiet_san_pham`
--

LOCK TABLES `chi_tiet_san_pham` WRITE;
/*!40000 ALTER TABLE `chi_tiet_san_pham` DISABLE KEYS */;
INSERT INTO `chi_tiet_san_pham` VALUES (1,NULL,5000000.00,5000000000.00,'2024-08-15 04:43:14.876682','2024-07-22',NULL,992,0,3,11,1,1),(2,NULL,850.00,1150.00,'2024-07-22 00:24:53.331130','2024-07-22',NULL,40,0,1,12,4,2),(3,NULL,550.00,750.00,'2024-07-22 00:08:39.930296','2024-07-22',NULL,53,0,1,11,3,1),(4,NULL,950.00,2250.00,'2024-07-22 00:09:09.899414','2024-07-22',NULL,40,0,1,1,6,5),(5,NULL,20000000.00,555550.00,'2024-07-22 12:42:09.544550','2024-07-22',NULL,2,0,1,1,4,4),(6,NULL,100000.00,20000.00,'2024-07-22 12:42:24.071500','2024-07-22',NULL,100,0,1,1,3,1),(7,NULL,100000.00,50000000.00,'2024-07-22 12:42:17.906813','2024-07-22',NULL,100,0,1,1,6,4),(8,NULL,650.00,750.00,'2024-07-30 17:55:19.030679','2024-07-30',NULL,100,0,1,1,6,3);
/*!40000 ALTER TABLE `chi_tiet_san_pham` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `danh_muc`
--

DROP TABLE IF EXISTS `danh_muc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `danh_muc` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `ma` varchar(255) DEFAULT NULL,
  `ngay_sua` varchar(255) DEFAULT NULL,
  `ngay_tao` varchar(255) DEFAULT NULL,
  `ten` varchar(255) DEFAULT NULL,
  `trang_thai` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `danh_muc`
--

LOCK TABLES `danh_muc` WRITE;
/*!40000 ALTER TABLE `danh_muc` DISABLE KEYS */;
/*!40000 ALTER TABLE `danh_muc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `de_giay`
--

DROP TABLE IF EXISTS `de_giay`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `de_giay` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `ma` varchar(255) NOT NULL,
  `ngay_sua` datetime(6) DEFAULT NULL,
  `ngay_tao` datetime(6) DEFAULT NULL,
  `ten` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_3r13k0l32dgevyhvhn3rt6pfx` (`ma`),
  UNIQUE KEY `UK_a977rwuc1r779ie7ftap12b3j` (`ten`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `de_giay`
--

LOCK TABLES `de_giay` WRITE;
/*!40000 ALTER TABLE `de_giay` DISABLE KEYS */;
INSERT INTO `de_giay` VALUES (1,'DG1','2024-07-21 23:47:02.189935','2024-07-21 23:47:02.189935','Đế Cao Su'),(2,'DG2','2024-07-21 23:48:08.440268','2024-07-21 23:47:08.940366','Đế giày Rubber'),(3,'DG3','2024-07-21 23:48:18.791924','2024-07-21 23:47:14.768866','Đế giày Ridgeway'),(5,'DG5','2024-07-21 23:48:00.357718','2024-07-21 23:47:25.006508','Đế giày Commando'),(6,'DG6','2024-07-21 23:48:28.525708','2024-07-21 23:48:28.525708','Đế giày Polyurethane'),(7,'DG7','2024-07-21 23:48:35.768171','2024-07-21 23:48:35.768171','Đế giày Leather');
/*!40000 ALTER TABLE `de_giay` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dia_chi`
--

DROP TABLE IF EXISTS `dia_chi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dia_chi` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `dia_chi` varchar(255) DEFAULT NULL,
  `ghi_chu` varchar(255) DEFAULT NULL,
  `ngay_sua` date DEFAULT NULL,
  `ngay_tao` date DEFAULT NULL,
  `phuong_xa` varchar(50) DEFAULT NULL,
  `quan_huyen` varchar(50) DEFAULT NULL,
  `sdt` varchar(255) DEFAULT NULL,
  `ten_nguoi_nhan` varchar(255) DEFAULT NULL,
  `thanh_pho` varchar(50) DEFAULT NULL,
  `trang_thai` tinyint DEFAULT NULL,
  `id_khach_hang` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKrk60uo19d67v1wpbp5a1rmao5` (`id_khach_hang`),
  CONSTRAINT `FKrk60uo19d67v1wpbp5a1rmao5` FOREIGN KEY (`id_khach_hang`) REFERENCES `khach_hang` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dia_chi`
--

LOCK TABLES `dia_chi` WRITE;
/*!40000 ALTER TABLE `dia_chi` DISABLE KEYS */;
INSERT INTO `dia_chi` VALUES (1,'Hà Nội','khum','2024-07-22','2024-07-22','70706','2017','0398194211','Phạm Văn Dương','264',0,1),(2,'Hà Nội','giao hnag tai cuwa','2024-07-29','2024-07-29','190506','1730','0398194211','Phạm Văn Dương','249',0,3),(3,'văn trì','giao hang tai cua','2024-07-29','2024-07-29','1A1210','1703','0398194233','hehee','201',0,3),(7,'số 2 nghách 70/67','không','2024-08-03','2024-07-30','22407','2090','0856560030','hehee','202',0,5),(8,'số 2 nghách 70/67','khumm','2024-08-03','2024-08-03','610304','2042','0856560039','Mai Anh2','252',0,5),(9,'số 2 nghách 70/67','ngon xinh yeu','2024-08-12','2024-08-12','470707','1779','0123455678','tuan an','258',0,5),(10,'Hà Nội','không','2024-08-17','2024-08-17','471006','1778','0398194210','Phạm Văn Dương2','258',0,1),(11,'fffff','fffff','2024-08-30','2024-08-30','230709','1968','0232525412','abc','267',0,8),(12,'So 14','hehe','2024-08-30','2024-08-30','13010','3440','0326475093','An','201',0,5),(13,'So 14','hehe','2024-08-30','2024-08-30','13010','3440','0326475093','An','201',0,5),(14,'So 14','hehe','2024-08-30','2024-08-30','13010','3440','0326475093','An','201',0,5),(15,'So 14','hehe','2024-08-30','2024-08-30','13010','3440','0326475093','An','201',0,5),(16,'So 14','hehe','2024-08-30','2024-08-30','13010','3440','0326475093','An','201',0,5),(17,'So 14','hehe','2024-08-30','2024-08-30','13010','3440','0326475093','An','201',0,5),(18,'So 14','hehe','2024-08-30','2024-08-30','13010','3440','0326475093','An','201',0,5),(19,'So 14','hehe','2024-08-30','2024-08-30','13010','3440','0326475093','An','201',0,5),(20,'So 14','hehe','2024-08-30','2024-08-30','13010','3440','0326475093','An','201',0,5),(21,'So 14','hehe','2024-08-30','2024-08-30','13010','3440','0326475093','An','201',0,5),(22,'So 14','hehe','2024-08-30','2024-08-30','13010','3440','0326475093','An','201',0,5),(23,'So 14','hehe','2024-08-30','2024-08-30','13010','3440','0326475093','An','201',0,5),(24,'So 14','hehe','2024-08-30','2024-08-30','13010','3440','0326475093','An','201',0,5),(25,'So 14','hehe','2024-08-30','2024-08-30','13010','3440','0326475093','An','201',0,5),(26,'So 14','hehe','2024-08-30','2024-08-30','13010','3440','0326475093','An','201',0,5),(27,'So 14','hehe','2024-08-30','2024-08-30','13010','3440','0326475093','An','201',0,5),(28,'So 14','hehe','2024-08-30','2024-08-30','13010','3440','0326475093','An','201',0,5),(29,'6666','66666','2024-08-30','2024-08-30','140811','2267','0225856666','ffffffghhhhhhhh','266',0,5);
/*!40000 ALTER TABLE `dia_chi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dong_san_pham`
--

DROP TABLE IF EXISTS `dong_san_pham`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dong_san_pham` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `ma` varchar(255) NOT NULL,
  `ngay_sua` date DEFAULT NULL,
  `ngay_tao` date DEFAULT NULL,
  `ten` varchar(255) NOT NULL,
  `trang_thai` tinyint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_28w4x0up9146ir61vu5nj6gs4` (`ma`),
  UNIQUE KEY `UK_ti2brqn4tp0othp3iwu97jwtp` (`ten`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dong_san_pham`
--

LOCK TABLES `dong_san_pham` WRITE;
/*!40000 ALTER TABLE `dong_san_pham` DISABLE KEYS */;
INSERT INTO `dong_san_pham` VALUES (1,'DSP1','2024-07-21','2024-07-21','Giày đá bóng',0),(2,'DSP2','2024-07-21','2024-07-21','Giày thể thao',0),(3,'DSP3','2024-07-21','2024-07-21','Giày chạy bộ',0),(4,'DSP4','2024-07-21','2024-07-21','Giày Thời Trang',0);
/*!40000 ALTER TABLE `dong_san_pham` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gio_hang`
--

DROP TABLE IF EXISTS `gio_hang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gio_hang` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `ma` varchar(255) DEFAULT NULL,
  `ngay_sua` date DEFAULT NULL,
  `ngay_tao` date DEFAULT NULL,
  `trang_thai` tinyint DEFAULT NULL,
  `id_khach_hang` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_s20okvj8cy9ux6taewckmju9` (`ma`),
  UNIQUE KEY `UK_q7fhxt1ya2dvmjcrrkxisvpl5` (`id_khach_hang`),
  CONSTRAINT `FK6c8iirgeg8qcwpq1oa9noxshw` FOREIGN KEY (`id_khach_hang`) REFERENCES `khach_hang` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gio_hang`
--

LOCK TABLES `gio_hang` WRITE;
/*!40000 ALTER TABLE `gio_hang` DISABLE KEYS */;
INSERT INTO `gio_hang` VALUES (1,NULL,NULL,NULL,NULL,3),(2,NULL,NULL,NULL,NULL,5),(3,NULL,NULL,NULL,NULL,4),(4,NULL,NULL,NULL,NULL,6),(5,NULL,NULL,NULL,NULL,8);
/*!40000 ALTER TABLE `gio_hang` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gio_hang_chi_tiet`
--

DROP TABLE IF EXISTS `gio_hang_chi_tiet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gio_hang_chi_tiet` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `don_gia` decimal(19,2) DEFAULT NULL,
  `ghi_chu` varchar(255) DEFAULT NULL,
  `ngay_sua` date DEFAULT NULL,
  `ngay_tao` date DEFAULT NULL,
  `so_luong` int DEFAULT NULL,
  `trang_thai` tinyint DEFAULT NULL,
  `id_chi_tiet_san_pham` bigint DEFAULT NULL,
  `id_gio_hang` bigint DEFAULT NULL,
  `id_hoa_don` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKlcvoteetgysdpfavfevmyh3en` (`id_chi_tiet_san_pham`),
  KEY `FKhkle2qtnnet5fq60x6tdhekql` (`id_gio_hang`),
  KEY `FKr3utb9x7j00p1ghfj4mkhjyct` (`id_hoa_don`),
  CONSTRAINT `FKhkle2qtnnet5fq60x6tdhekql` FOREIGN KEY (`id_gio_hang`) REFERENCES `gio_hang` (`id`),
  CONSTRAINT `FKlcvoteetgysdpfavfevmyh3en` FOREIGN KEY (`id_chi_tiet_san_pham`) REFERENCES `chi_tiet_san_pham` (`id`),
  CONSTRAINT `FKr3utb9x7j00p1ghfj4mkhjyct` FOREIGN KEY (`id_hoa_don`) REFERENCES `hoa_don` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=103 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gio_hang_chi_tiet`
--

LOCK TABLES `gio_hang_chi_tiet` WRITE;
/*!40000 ALTER TABLE `gio_hang_chi_tiet` DISABLE KEYS */;
INSERT INTO `gio_hang_chi_tiet` VALUES (13,5000000.00,NULL,NULL,'2024-07-30',90,0,1,2,63),(15,850.00,NULL,NULL,'2024-07-30',10,0,2,2,73),(16,550.00,NULL,NULL,'2024-07-30',1,0,3,2,71),(18,850.00,'Bị rách',NULL,'2024-07-30',7,4,2,2,72),(19,850.00,'sản phẩm hoàn trả',NULL,'2024-07-30',10,3,2,2,73),(23,850.00,NULL,NULL,'2024-08-03',12,0,2,2,74),(24,850.00,'sản phẩm hoàn trả',NULL,'2024-08-03',1,3,2,2,75),(25,850.00,NULL,NULL,'2024-08-03',4,0,2,2,76),(27,850.00,NULL,NULL,'2024-08-03',2,0,2,2,77),(28,5000000.00,NULL,NULL,'2024-08-12',10,0,1,2,78),(29,5000000.00,'sản phẩm hoàn trả',NULL,'2024-08-12',10,3,1,2,78),(30,550.00,NULL,NULL,'2024-08-12',5,0,3,2,79),(31,850.00,NULL,NULL,'2024-08-12',40,0,2,2,84),(32,20000000.00,NULL,NULL,'2024-08-12',1,0,5,2,86),(33,5000000.00,NULL,NULL,'2024-08-12',5,0,1,2,87),(34,5000000.00,'sản phẩm hoàn trả',NULL,'2024-08-12',5,3,1,2,87),(35,550.00,NULL,NULL,'2024-08-12',1,0,3,2,88),(36,850.00,NULL,NULL,'2024-08-12',1,0,2,2,89),(37,5000000.00,NULL,NULL,'2024-08-12',5,0,1,2,90),(38,5000000.00,'sản phẩm hoàn trả',NULL,'2024-08-12',5,3,1,2,90),(40,5000000.00,NULL,NULL,'2024-08-12',3,0,1,2,96),(41,850.00,NULL,NULL,'2024-08-15',8,0,2,1,95),(42,5000000.00,'sản phẩm hoàn trả',NULL,'2024-08-16',1,3,1,2,96),(43,5000000.00,NULL,NULL,'2024-08-16',4,0,1,2,130),(44,550.00,NULL,NULL,'2024-08-16',10,0,3,2,97),(45,550.00,NULL,NULL,'2024-08-16',10,0,3,2,98),(46,550.00,NULL,NULL,'2024-08-16',10,0,3,2,99),(47,550.00,NULL,NULL,'2024-08-16',10,0,3,2,100),(48,550.00,NULL,NULL,'2024-08-16',10,0,3,2,101),(49,550.00,NULL,NULL,'2024-08-17',5,0,3,2,108),(50,550.00,'sản phẩm hoàn trả',NULL,'2024-08-17',5,3,3,2,108),(51,550.00,NULL,NULL,'2024-08-17',10,0,3,3,NULL),(52,550.00,NULL,NULL,'2024-08-24',1,0,3,2,131),(53,5000000.00,NULL,NULL,'2024-08-26',2,0,1,2,144),(54,5000000.00,NULL,NULL,'2024-08-27',12,0,1,2,145),(55,5000000.00,NULL,NULL,'2024-08-27',22,0,1,4,NULL),(56,20000000.00,NULL,NULL,'2024-08-27',1,0,5,2,146),(57,5000000.00,NULL,NULL,'2024-08-27',3,0,1,2,147),(58,20000000.00,NULL,NULL,'2024-08-29',1,0,5,2,148),(59,550.00,NULL,NULL,'2024-08-29',1,0,3,2,150),(60,5000000.00,NULL,NULL,'2024-08-29',1,0,1,2,149),(77,850.00,NULL,NULL,'2024-08-29',2,0,2,2,152),(78,5000000.00,NULL,NULL,'2024-08-29',1,0,1,2,151),(79,5000000.00,NULL,NULL,'2024-08-29',2,0,1,2,154),(80,100000.00,NULL,NULL,'2024-08-29',4,0,6,2,153),(81,5000000.00,NULL,NULL,'2024-08-29',1,0,1,2,155),(82,100000.00,NULL,NULL,'2024-08-29',1,0,6,2,156),(83,5000000.00,NULL,NULL,'2024-08-29',2,0,1,2,159),(84,100000.00,NULL,NULL,'2024-08-29',2,0,6,2,157),(85,100000.00,NULL,NULL,'2024-08-29',1,0,6,2,158),(87,850.00,NULL,NULL,'2024-08-29',1,0,2,2,160),(88,5000000.00,NULL,NULL,'2024-08-29',1,0,1,2,162),(91,5000000.00,NULL,NULL,'2024-08-30',1,0,1,5,163),(96,850.00,NULL,NULL,'2024-08-30',20,0,2,5,165),(97,100000.00,NULL,NULL,'2024-08-30',7,0,6,5,166),(98,950.00,NULL,NULL,'2024-08-30',3,0,4,5,NULL),(99,5000000.00,NULL,NULL,'2024-08-30',1,0,1,2,167),(100,850.00,NULL,NULL,'2024-08-30',39,0,2,2,167),(101,5000000.00,NULL,NULL,'2024-08-30',200,0,1,2,168),(102,100000.00,NULL,NULL,'2024-08-30',1,0,6,2,169);
/*!40000 ALTER TABLE `gio_hang_chi_tiet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hoa_don`
--

DROP TABLE IF EXISTS `hoa_don`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hoa_don` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `dia_chi` varchar(255) DEFAULT NULL,
  `ghi_chu` varchar(255) DEFAULT NULL,
  `loai_hoa_don` tinyint DEFAULT NULL,
  `ma` varchar(255) DEFAULT NULL,
  `ngay_giao` date DEFAULT NULL,
  `ngay_sua` date DEFAULT NULL,
  `ngay_tao` date DEFAULT NULL,
  `ngay_thanh_toan` date DEFAULT NULL,
  `phi_van_chuyen` decimal(38,2) DEFAULT NULL,
  `sdt` varchar(255) DEFAULT NULL,
  `ten_khach_hang` varchar(255) DEFAULT NULL,
  `thanh_toan` decimal(19,2) DEFAULT NULL,
  `tien_giam_gia` decimal(19,2) DEFAULT NULL,
  `tong_tien` decimal(19,2) DEFAULT NULL,
  `trang_thai` tinyint DEFAULT NULL,
  `xu` decimal(19,2) DEFAULT NULL,
  `id_khach_hang` bigint DEFAULT NULL,
  `id_phieu_giam_gia` bigint DEFAULT NULL,
  `id_nhan_vien` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_qc99tpq7eqghgm22o8e06gqyf` (`ma`),
  KEY `FKrygimdf5nr1g2t6u03gvtr1te` (`id_khach_hang`),
  KEY `FKmueylgcm7h1gb4f9nbnp3j5c6` (`id_phieu_giam_gia`),
  KEY `FKkuxkrkgq8yftm4d8d7o0w6nbv` (`id_nhan_vien`),
  CONSTRAINT `FKkuxkrkgq8yftm4d8d7o0w6nbv` FOREIGN KEY (`id_nhan_vien`) REFERENCES `nhan_vien` (`id`),
  CONSTRAINT `FKmueylgcm7h1gb4f9nbnp3j5c6` FOREIGN KEY (`id_phieu_giam_gia`) REFERENCES `phieu_giam_gia` (`id`),
  CONSTRAINT `FKrygimdf5nr1g2t6u03gvtr1te` FOREIGN KEY (`id_khach_hang`) REFERENCES `khach_hang` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=170 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hoa_don`
--

LOCK TABLES `hoa_don` WRITE;
/*!40000 ALTER TABLE `hoa_don` DISABLE KEYS */;
INSERT INTO `hoa_don` VALUES (1,NULL,NULL,0,'Hóa Đơn 2472203626',NULL,NULL,'2024-07-22','2024-07-22',0.00,'0000000000','Khách lẻ',11000000.00,0.00,11000000.00,5,NULL,2,NULL,2),(2,NULL,NULL,0,'Hóa Đơn 2472203628',NULL,NULL,'2024-07-22','2024-07-22',0.00,'0000000000','Khách lẻ',1000000.00,0.00,1000000.00,5,NULL,2,NULL,2),(3,NULL,NULL,0,'Hóa Đơn 2472215535',NULL,NULL,'2024-07-22','2024-07-22',0.00,'0000000000','Khách lẻ',100000.00,0.00,100000.00,5,NULL,2,NULL,4),(4,NULL,NULL,0,'Hóa Đơn 247221576',NULL,NULL,'2024-07-22','2024-07-22',0.00,'0000000000','Khách lẻ',100000.00,0.00,100000.00,5,NULL,2,NULL,4),(5,NULL,NULL,0,'Hóa Đơn 2472215753',NULL,NULL,'2024-07-22','2024-07-22',0.00,'0000000000','Khách lẻ',10100000.00,0.00,10100000.00,5,NULL,2,NULL,4),(6,NULL,NULL,0,'Hóa Đơn 247223856',NULL,NULL,'2024-07-22','2024-07-22',0.00,'0398194212','Dương Quý Dương',100000.00,0.00,100000.00,5,NULL,3,NULL,4),(7,NULL,NULL,0,'Hóa Đơn 2472233351',NULL,NULL,'2024-07-22','2024-07-22',0.00,'0398194212','Dương Quý Dương',7700000.00,0.00,7700000.00,5,NULL,3,NULL,4),(18,NULL,NULL,0,'Hóa Đơn 2472213456',NULL,NULL,'2024-07-22','2024-07-22',0.00,'0398194212','Dương Quý Dương',950000.00,0.00,1000000.00,5,50000.00,3,NULL,4),(19,'Hà Nội,Phường Tân Hồng,Thị xã Từ Sơn,Bắc Ninh','xong',1,'HD2472914011',NULL,NULL,'2024-07-29','2024-07-29',54003.00,'0398194211','Phạm Văn Dương',15063353.00,NULL,15009350.00,5,0.00,3,NULL,NULL),(20,'Hà Nội,Phường Tân Hồng,Thị xã Từ Sơn,Bắc Ninh','done5',1,'HD24729142042',NULL,NULL,'2024-07-29','2024-07-29',54003.00,'0398194211','Phạm Văn Dương',15653353.00,10000.00,15609350.00,5,0.00,3,1,NULL),(21,'Hà Nội,Phường Tân Hồng,Thị xã Từ Sơn,Bắc Ninh','không thích nữa',1,'HD24729145527',NULL,NULL,'2024-07-29',NULL,54003.00,'0398194211','Phạm Văn Dương',15663353.00,NULL,15609350.00,6,0.00,3,NULL,NULL),(22,'Hà Nội,Phường Tân Hồng,Thị xã Từ Sơn,Bắc Ninh','không chấp nhận chả hàng',1,'HD24729152336',NULL,NULL,'2024-07-29','2024-07-29',54003.00,'0398194211','Phạm Văn Dương',654003.00,NULL,600000.00,5,0.00,3,NULL,NULL),(23,'văn trì,Xã Dương Quang,Huyện Gia Lâm,Hà Nội','ok',1,'HD24729163326',NULL,NULL,'2024-07-29',NULL,41502.00,'0398194233','hehee',481502.00,10000.00,500000.00,2,50000.00,3,1,NULL),(24,NULL,NULL,0,'Hóa Đơn 2472916342',NULL,NULL,'2024-07-29','2024-07-29',0.00,'0398194215','Đinh Tuấn An',1000000.00,0.00,1000000.00,5,NULL,4,NULL,4),(26,NULL,NULL,0,'Hóa Đơn 2472916345',NULL,NULL,'2024-07-29','2024-07-30',0.00,'0000000000','Khách lẻ',1000000.00,0.00,1000000.00,5,NULL,2,NULL,4),(28,NULL,NULL,0,'Hóa Đơn 2472916349',NULL,NULL,'2024-07-29','2024-07-30',0.00,'0000000000','Khách lẻ',1000000.00,0.00,1000000.00,5,NULL,2,NULL,4),(30,'Hà Nội,Phường Tân Hồng,Thị xã Từ Sơn,Bắc Ninh','ok',1,'HD2472916401',NULL,NULL,'2024-07-29',NULL,54003.00,'0398194211','Phạm Văn Dương',754003.00,NULL,700000.00,2,0.00,3,NULL,NULL),(31,'Hà Nội,Phường Tân Hồng,Thị xã Từ Sơn,Bắc Ninh','xong',1,'HD2472916525',NULL,NULL,'2024-07-29','2024-07-30',54003.00,'0398194211','Phạm Văn Dương',154003.00,NULL,100000.00,5,0.00,3,NULL,NULL),(32,'Hà Nội,Phường Tân Hồng,Thị xã Từ Sơn,Bắc Ninh','hủy',1,'HD24729165635',NULL,NULL,'2024-07-29',NULL,54003.00,'0398194211','Phạm Văn Dương',63353.00,NULL,9350.00,6,0.00,3,NULL,NULL),(33,'số 2 nghách 70/67,Phường Thượng Cát,Quận Bắc Từ Liêm,Hà Nội','',1,'HD24729185515',NULL,NULL,'2024-07-29','2024-07-29',33502.00,'0856560039','Mai Anh',523502.00,500000.00,1000000.00,5,10000.00,5,4,NULL),(34,'số 2 nghách 70/67,Phường Thượng Cát,Quận Bắc Từ Liêm,Hà Nội','ok',1,'HD24729185920',NULL,NULL,'2024-07-29','2024-07-29',33502.00,'0856560039','Mai Anh',199533502.00,500000.00,200000000.00,5,0.00,5,4,NULL),(35,'số 2 nghách 70/67,Phường Thượng Cát,Quận Bắc Từ Liêm,Hà Nội','ok',1,'HD2472919122',NULL,NULL,'2024-07-29','2024-07-29',33502.00,'0856560039','Mai Anh',533502.00,NULL,700000.00,5,0.00,5,NULL,NULL),(36,NULL,NULL,0,'Hóa Đơn 2472919717',NULL,NULL,'2024-07-29','2024-07-30',0.00,'0000000000','Khách lẻ',100000.00,0.00,100000.00,5,NULL,2,NULL,1),(37,NULL,NULL,0,'Hóa Đơn 2472919718',NULL,NULL,'2024-07-29','2024-07-30',0.00,'0000000000','Khách lẻ',100000.00,0.00,100000.00,5,NULL,2,NULL,1),(38,NULL,NULL,0,'Hóa Đơn 2472919719',NULL,NULL,'2024-07-29','2024-07-30',0.00,'0000000000','Khách lẻ',30000000.00,0.00,30000000.00,5,NULL,2,NULL,1),(39,'số 2 nghách 70/67,Phường Thượng Cát,Quận Bắc Từ Liêm,Hà Nội','ok',1,'HD24729234813',NULL,NULL,'2024-07-29','2024-07-30',33502.00,'0856560039','Mai Anh',233502.00,NULL,200000.00,3,0.00,5,NULL,NULL),(40,'số 2 nghách 70/67,Phường Thượng Cát,Quận Bắc Từ Liêm,Hà Nội','không',1,'HD247300956',NULL,NULL,'2024-07-30',NULL,33502.00,'0856560039','Mai Anh',5233502.00,NULL,5200000.00,1,0.00,5,NULL,NULL),(41,'số 2 nghách 70/67,Phường Thượng Cát,Quận Bắc Từ Liêm,Hà Nội','xong',1,'HD247300194',NULL,NULL,'2024-07-30','2024-07-30',33502.00,'0856560039','Mai Anh',99802.00,NULL,66300.00,5,0.00,5,NULL,NULL),(42,'số 2 nghách 70/67,Phường Thượng Cát,Quận Bắc Từ Liêm,Hà Nội','xong',1,'HD2473001922',NULL,NULL,'2024-07-30','2024-07-30',33502.00,'0856560039','Mai Anh',99802.00,NULL,66300.00,5,0.00,5,NULL,NULL),(43,NULL,NULL,0,'Hóa Đơn 2473004021',NULL,NULL,'2024-07-30','2024-07-30',0.00,'0000000000','Khách lẻ',100000.00,0.00,100000.00,5,NULL,2,NULL,4),(44,NULL,NULL,0,'Hóa Đơn 2473004112',NULL,NULL,'2024-07-30','2024-07-30',0.00,'0000000000','Khách lẻ',100000.00,0.00,100000.00,5,NULL,2,NULL,4),(45,NULL,NULL,0,'Hóa Đơn 2473004325',NULL,NULL,'2024-07-30','2024-07-30',0.00,'0000000000','Khách lẻ',100000.00,0.00,100000.00,5,NULL,2,NULL,4),(46,NULL,NULL,0,'Hóa Đơn 247300443',NULL,NULL,'2024-07-30','2024-07-30',0.00,'0398194219','Nguyễn Hoàng Anh',100000.00,0.00,100000.00,5,NULL,5,NULL,4),(47,NULL,NULL,0,'Hóa Đơn 2473004848',NULL,NULL,'2024-07-30','2024-07-30',0.00,'0398194212','Dương Quý Dương',3350000.00,0.00,3400000.00,5,50000.00,3,NULL,4),(48,NULL,NULL,0,'Hóa Đơn 2473004855',NULL,NULL,'2024-07-30','2024-07-30',0.00,'0398194212','Dương Quý Dương',200000000.00,0.00,200000000.00,5,NULL,3,NULL,4),(49,'số 2 nghách 70/67,Phường Thượng Cát,Quận Bắc Từ Liêm,Hà Nội','ok',1,'Hóa Đơn247302425',NULL,NULL,'2024-07-30','2024-08-17',33502.00,'0856560039','Mai Anh',87952.00,NULL,54450.00,5,0.00,5,NULL,NULL),(50,'số 2 nghách 70/67,Phường Thượng Cát,Quận Bắc Từ Liêm,Hà Nội','ngon xinh yeu',1,'Hóa Đơn247302619',NULL,NULL,'2024-07-30',NULL,33502.00,'0856560039','Mai Anh',33887.00,165.00,550.00,1,0.00,5,2,NULL),(51,'số 2 nghách 70/67,Phường Thượng Cát,Quận Bắc Từ Liêm,Hà Nội','',1,'Hóa Đơn247303314',NULL,NULL,'2024-07-30',NULL,33502.00,'0856560039','Mai Anh',34052.00,NULL,550.00,1,0.00,5,NULL,NULL),(52,'số 2 nghách 70/67,Phường Thượng Cát,Quận Bắc Từ Liêm,Hà Nội','',1,'Hóa Đơn247303318',NULL,NULL,'2024-07-30',NULL,33502.00,'0856560039','Mai Anh',34052.00,NULL,550.00,1,0.00,5,NULL,NULL),(53,'số 2 nghách 70/67,Phường Thượng Cát,Quận Bắc Từ Liêm,Hà Nội','',1,'Hóa Đơn247303328',NULL,NULL,'2024-07-30',NULL,33502.00,'0856560039','Mai Anh',34052.00,NULL,550.00,1,0.00,5,NULL,NULL),(54,'số 2 nghách 70/67,Phường Thượng Cát,Quận Bắc Từ Liêm,Hà Nội','',1,'Hóa Đơn2473033257',NULL,NULL,'2024-07-30',NULL,33502.00,'0856560039','Mai Anh',34052.00,NULL,550.00,1,0.00,5,NULL,NULL),(55,'số 2 nghách 70/67,Phường Thượng Cát,Quận Bắc Từ Liêm,Hà Nội','',1,'Hóa Đơn2473035646',NULL,NULL,'2024-07-30',NULL,33502.00,'0856560039','Mai Anh',43002.00,NULL,9500.00,1,0.00,5,NULL,NULL),(56,NULL,NULL,0,'Hóa Đơn 247304237',NULL,NULL,'2024-07-30','2024-07-30',0.00,'0000000000','Khách lẻ',1195000.00,0.00,1195000.00,5,NULL,2,NULL,4),(57,'số 2 nghách 70/67,Phường Thượng Cát,Quận Bắc Từ Liêm,Hà Nội','',1,'Hóa Đơn2473042241',NULL,NULL,'2024-07-30',NULL,33502.00,'0856560039','Mai Anh',43002.00,NULL,9500.00,1,0.00,5,NULL,NULL),(61,NULL,NULL,0,'Hóa Đơn 24730135447',NULL,NULL,'2024-07-30','2024-07-30',0.00,'0398194212','Dương Quý Dương',8850000.00,0.00,8900000.00,5,50000.00,3,NULL,4),(63,'số 2 nghách 70/67,Phường Thượng Cát,Quận Bắc Từ Liêm,Hà Nội','',1,'Hóa Đơn24730145140',NULL,NULL,'2024-07-30',NULL,33502.00,'0856560039','Mai Anh',5033502.00,NULL,5000000.00,1,0.00,5,NULL,NULL),(64,NULL,NULL,0,'Hóa Đơn 24730173434',NULL,NULL,'2024-07-30','2024-08-12',0.00,'0398194215','Đinh Tuấn An',55000.00,0.00,65000.00,5,10000.00,4,NULL,4),(69,NULL,NULL,0,'Hóa Đơn 24730173441',NULL,NULL,'2024-07-30','2024-07-30',0.00,'0000000000','Khách lẻ',9800000.00,0.00,9800000.00,5,NULL,2,NULL,4),(70,'số 2 nghách 70/67,Phường Thượng Cát,Quận Bắc Từ Liêm,Hà Nội','',1,'Hóa Đơn24730173834',NULL,NULL,'2024-07-30',NULL,33502.00,'0856560039','Mai Anh',9823502.00,10000.00,9800000.00,1,0.00,5,1,NULL),(71,'số 2 nghách 70/67,Phường Thượng Cát,Quận Bắc Từ Liêm,Hà Nội','',1,'Hóa Đơn24730174937',NULL,NULL,'2024-07-30',NULL,88996.00,'0856560030','hehee',89546.00,NULL,550.00,1,0.00,5,NULL,NULL),(72,'số 2 nghách 70/67,Phường Thượng Cát,Quận Bắc Từ Liêm,Hà Nội','không ok',1,'Hóa Đơn2473017563',NULL,NULL,'2024-07-30','2024-07-30',33502.00,'0856560039','Mai Anh',56452.00,NULL,22950.00,5,0.00,5,NULL,NULL),(73,'số 2 nghách 70/67,Phường Thượng Cát,1482,201','ok hoàn',1,'Hóa Đơn2473018135',NULL,NULL,'2024-07-30','2024-07-30',33502.00,'0856560039','Mai Anh',42002.00,NULL,17000.00,5,0.00,5,NULL,NULL),(74,'số 2 nghách 70/67,Phường Thượng Cát,Quận Bắc Từ Liêm,Hà Nội','ok',1,'Hóa Đơn248363750',NULL,NULL,'2024-08-03','2024-08-03',33502.00,'0856560039','Mai Anh',43702.00,NULL,10200.00,5,0.00,5,NULL,NULL),(75,'số 2 nghách 70/67,Phường Thượng Cát,Quận Bắc Từ Liêm,Hà Nội','xong',1,'Hóa Đơn248364011',NULL,NULL,'2024-08-03','2024-08-03',33502.00,'0856560039','Mai Anh',33502.00,NULL,850.00,5,0.00,5,NULL,NULL),(76,'số 2 nghách 70/67,Phường Thượng Cát,Quận Bắc Từ Liêm,Hà Nội','',1,'Hóa Đơn248365832',NULL,NULL,'2024-08-03',NULL,33502.00,'0856560039','Mai Anh',36902.00,NULL,3400.00,1,0.00,5,NULL,NULL),(77,'số 2 nghách 70/67,Xã Thạnh An,Huyện Cần Giờ,Hồ Chí Minh','',1,'Hóa Đơn248518152',NULL,NULL,'2024-08-05',NULL,88996.00,'0856560030','hehee',90186.00,510.00,1700.00,1,0.00,5,2,NULL),(78,'số 2 nghách 70/67,Xã Thạnh An,Huyện Đức Linh,Hồ Chí Minh','olk',1,'Hóa Đơn2481211451',NULL,NULL,'2024-08-12','2024-08-12',88996.00,'0123455678','tuan an',100087996.00,NULL,100000000.00,5,1000.00,5,NULL,NULL),(79,'số 2 nghách 70/67,Xã Khánh Hội,Huyện U Minh,Cà Mau','',1,'Hóa Đơn2481211502',NULL,NULL,'2024-08-12','2024-08-12',88996.00,'0856560039','Mai Anh2',90921.00,825.00,2750.00,5,0.00,5,2,NULL),(84,'số 2 nghách 70/67,Xã Thạnh An,Huyện Cần Giờ,Hồ Chí Minh','xon',1,'Hóa Đơn24812123447',NULL,NULL,'2024-08-12','2024-08-12',88996.00,'0856560030','hehee',112996.00,10000.00,34000.00,5,0.00,5,2,NULL),(86,'số 2 nghách 70/67,Xã Khánh Hội,Huyện U Minh,Cà Mau','ok',1,'Hóa Đơn2481213142',NULL,NULL,'2024-08-12','2024-08-12',88996.00,'0856560039','Mai Anh2',20088996.00,NULL,20000000.00,5,0.00,5,NULL,NULL),(87,'số 2 nghách 70/67,Xã Thạnh An,Huyện Cần Giờ,Hồ Chí Minh','ok hoanf',1,'Hóa Đơn24812131037',NULL,NULL,'2024-08-12','2024-08-12',88996.00,'0856560030','hehee',25078996.00,10000.00,50000000.00,5,0.00,5,2,NULL),(88,'số 2 nghách 70/67,Xã Thạnh An,Huyện Cần Giờ,Hồ Chí Minh','',1,'Hóa Đơn2481213176',NULL,NULL,'2024-08-12','2024-08-12',88996.00,'0856560030','hehee',89546.00,NULL,550.00,5,0.00,5,NULL,NULL),(89,'số 2 nghách 70/67,Xã Thạnh An,Huyện Cần Giờ,Hồ Chí Minh','ok',1,'Hóa Đơn24812132059',NULL,NULL,'2024-08-12','2024-08-12',88996.00,'0856560030','hehee',89846.00,NULL,850.00,5,0.00,5,NULL,NULL),(90,'số 2 nghách 70/67,22407,2090,202','cho hoanf',1,'Hóa Đơn24812132225',NULL,NULL,'2024-08-12','2024-08-12',88996.00,'0856560030','hehee',24988996.00,100000.00,50000000.00,5,0.00,5,3,NULL),(95,'Hà Nội,Phường Tân Hồng,Thị xã Từ Sơn,Bắc Ninh','done',1,'Hóa Đơn248155027',NULL,NULL,'2024-08-15','2024-08-15',54003.00,'0398194211','Phạm Văn Dương',60803.00,NULL,6800.00,4,0.00,3,NULL,NULL),(96,'số 2 nghách 70/67,Xã Thạnh An,Huyện Cần Giờ,Hồ Chí Minh','ok',1,'Hóa Đơn24816174632',NULL,NULL,'2024-08-16','2024-08-16',88996.00,'0856560030','hehee',15088996.00,NULL,20000000.00,5,0.00,5,NULL,NULL),(97,'số 2 nghách 70/67,Xã Thạnh An,Huyện Cần Giờ,Hồ Chí Minh','',1,'Hóa Đơn2481618826',NULL,NULL,'2024-08-16',NULL,88996.00,'0856560030','hehee',44496.00,NULL,5500.00,1,50000.00,5,NULL,NULL),(98,'số 2 nghách 70/67,Xã Thạnh An,Huyện Cần Giờ,Hồ Chí Minh','',1,'Hóa Đơn2481618955',NULL,NULL,'2024-08-16',NULL,88996.00,'0856560030','hehee',44496.00,NULL,5500.00,1,50000.00,5,NULL,NULL),(99,'số 2 nghách 70/67,Xã Thạnh An,Huyện Cần Giờ,Hồ Chí Minh','đã giao',1,'Hóa Đơn2481618110',NULL,NULL,'2024-08-16','2024-08-21',88996.00,'0856560030','hehee',64496.00,NULL,5500.00,4,30000.00,5,NULL,NULL),(100,'số 2 nghách 70/67,Xã Thạnh An,Huyện Cần Giờ,Hồ Chí Minh','',1,'Hóa Đơn24816181239',NULL,NULL,'2024-08-16',NULL,88996.00,'0856560030','hehee',42846.00,1650.00,5500.00,1,50000.00,5,2,NULL),(101,'số 2 nghách 70/67,Xã Thạnh An,2090,Hồ Chí Minh','ok',1,'Hóa Đơn24816181350',NULL,NULL,'2024-08-16',NULL,88996.00,'0856560030','hehee',79496.00,NULL,5500.00,2,15000.00,5,NULL,NULL),(102,NULL,NULL,0,'Hóa Đơn 24817191311',NULL,NULL,'2024-08-17','2024-08-17',0.00,'0398194210','Dương',200000000.00,0.00,200000000.00,5,NULL,6,NULL,4),(108,'số 2 nghách 70/67,Xã Đức Tín,Huyện Đức Linh,Bình Thuận','cho hoàn',1,'Hóa Đơn24817191741',NULL,NULL,'2024-08-17','2024-08-17',88996.00,'0123455678','tuan an',90096.00,1650.00,5500.00,5,0.00,5,2,NULL),(116,NULL,NULL,0,'Hóa Đơn 2481931110',NULL,NULL,'2024-08-19','2024-08-19',0.00,'0000000000','Khách lẻ',20000000.00,0.00,20000000.00,5,NULL,2,NULL,4),(117,NULL,NULL,0,'Hóa Đơn 2481931111',NULL,NULL,'2024-08-19','2024-08-19',0.00,'0000000000','Khách lẻ',20000000.00,0.00,20000000.00,5,NULL,2,NULL,4),(118,NULL,NULL,0,'Hóa Đơn 2481931112',NULL,NULL,'2024-08-19','2024-08-19',0.00,'0000000000','Khách lẻ',20000000.00,0.00,20000000.00,5,NULL,2,NULL,4),(121,NULL,NULL,0,'Hóa Đơn 2481933050',NULL,NULL,'2024-08-19','2024-08-19',0.00,'0000000000','Khách lẻ',20000000.00,0.00,20000000.00,5,NULL,2,NULL,4),(122,NULL,NULL,0,'Hóa Đơn 2481933110',NULL,NULL,'2024-08-19','2024-08-19',0.00,'0000000000','Khách lẻ',200000000.00,0.00,200000000.00,5,NULL,2,NULL,4),(123,NULL,NULL,0,'Hóa Đơn 248193382',NULL,NULL,'2024-08-19','2024-08-19',0.00,'0398194212','Dương Quý Dương',20000000.00,0.00,20000000.00,5,NULL,3,NULL,4),(124,NULL,NULL,0,'Hóa Đơn 2481935459',NULL,NULL,'2024-08-19','2024-08-19',0.00,'0000000000','Khách lẻ',20000000.00,0.00,20000000.00,5,NULL,2,NULL,4),(125,NULL,NULL,0,'Hóa Đơn 248194345',NULL,NULL,'2024-08-19','2024-08-19',0.00,'0000000000','Khách lẻ',20000000.00,0.00,20000000.00,5,NULL,2,NULL,4),(126,NULL,NULL,0,'Hóa Đơn 248194638',NULL,NULL,'2024-08-19','2024-08-19',0.00,'0000000000','Khách lẻ',20000000.00,0.00,20000000.00,5,NULL,2,NULL,4),(127,NULL,NULL,0,'Hóa Đơn 248194825',NULL,NULL,'2024-08-19','2024-08-26',0.00,'0000000000','Khách lẻ',20000000.00,0.00,20000000.00,5,NULL,2,NULL,4),(128,NULL,NULL,0,'Hóa Đơn 248194108',NULL,NULL,'2024-08-19','2024-08-19',0.00,'0000000000','Khách lẻ',20000000.00,0.00,20000000.00,5,NULL,2,NULL,4),(129,NULL,NULL,0,'Hóa Đơn 2482115218',NULL,NULL,'2024-08-21','2024-08-26',0.00,'0000000000','Khách lẻ',200000000.00,0.00,200000000.00,5,NULL,2,NULL,4),(130,'số 2 nghách 70/67,Xã Thạnh An,Huyện Cần Giờ,Hồ Chí Minh','OK',1,'Hóa Đơn2482414553',NULL,NULL,'2024-08-24','2024-08-24',88996.00,'0856560030','hehee',20087216.00,NULL,20000000.00,5,1780.48,5,NULL,NULL),(131,'số 2 nghách 70/67,Xã Thạnh An,Huyện Cần Giờ,Hồ Chí Minh','',1,'Hóa Đơn2482414834',NULL,NULL,'2024-08-24',NULL,88996.00,'0856560030','hehee',39546.00,NULL,550.00,1,50000.00,5,NULL,NULL),(132,NULL,NULL,0,'Hóa Đơn 2482415027',NULL,NULL,'2024-08-24','2024-08-26',0.00,'0000000000','Khách lẻ',20000000.00,0.00,20000000.00,5,NULL,2,NULL,4),(133,NULL,NULL,0,'Hóa Đơn 2482415029',NULL,NULL,'2024-08-24','2024-08-26',0.00,'0000000000','Khách lẻ',200000000.00,0.00,200000000.00,5,NULL,2,NULL,4),(134,NULL,NULL,0,'Hóa Đơn 248262539',NULL,NULL,'2024-08-26','2024-08-26',0.00,'0398194212','Dương Quý Dương',20000000.00,0.00,20000000.00,5,NULL,3,NULL,4),(135,NULL,NULL,0,'Hóa Đơn 2482625529',NULL,NULL,'2024-08-26','2024-08-26',0.00,'0000000000','Khách lẻ',40000000.00,0.00,40000000.00,5,NULL,2,NULL,4),(136,NULL,NULL,0,'Hóa Đơn 248263358',NULL,NULL,'2024-08-26','2024-08-26',0.00,'0000000000','Khách lẻ',200000000.00,0.00,200000000.00,5,NULL,2,NULL,4),(137,NULL,NULL,0,'Hóa Đơn 248263359',NULL,NULL,'2024-08-26','2024-08-26',0.00,'0000000000','Khách lẻ',200000000.00,0.00,200000000.00,5,NULL,2,NULL,4),(138,NULL,NULL,0,'Hóa Đơn 24826340',NULL,NULL,'2024-08-26','2024-08-26',0.00,'0000000000','Khách lẻ',20000000.00,0.00,20000000.00,5,NULL,2,NULL,4),(139,NULL,NULL,0,'Hóa Đơn 24826191628',NULL,NULL,'2024-08-26',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,5,NULL,4),(140,NULL,NULL,0,'Hóa Đơn 24826191630',NULL,NULL,'2024-08-26',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,6,NULL,4),(142,NULL,NULL,0,'Hóa Đơn 24826191634',NULL,NULL,'2024-08-26',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,2,NULL,4),(143,NULL,NULL,0,'Hóa Đơn 2482722324',NULL,NULL,'2024-08-27',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,2,NULL,4),(144,'số 2 nghách 70/67,Xã Thạnh An,Huyện Cần Giờ,Hồ Chí Minh','',1,'Hóa Đơn2482792146',NULL,NULL,'2024-08-27',NULL,88996.00,'0856560030','hehee',10088996.00,NULL,10000000.00,1,0.00,5,NULL,NULL),(145,'số 2 nghách 70/67,Xã Thạnh An,2090,Hồ Chí Minh','',1,'Hóa Đơn24827232544',NULL,NULL,'2024-08-27',NULL,88996.00,'0856560030','hehee',60088996.00,NULL,60000000.00,1,0.00,5,NULL,NULL),(146,'số 2 nghách 70/67,Xã Khánh Hội,Huyện U Minh,Cà Mau','',1,'Hóa Đơn2482911236',NULL,NULL,'2024-08-29',NULL,88996.00,'0856560039','Mai Anh2',20088996.00,NULL,20000000.00,1,0.00,5,NULL,NULL),(147,'số 2 nghách 70/67,Xã Thạnh An,Huyện Cần Giờ,Hồ Chí Minh','',1,'Hóa Đơn2482911550',NULL,NULL,'2024-08-29',NULL,88996.00,'0856560030','hehee',15088996.00,NULL,15000000.00,1,0.00,5,NULL,NULL),(148,'số 2 nghách 70/67,Xã Thạnh An,Huyện Cần Giờ,Hồ Chí Minh','',1,'Hóa Đơn248291195',NULL,NULL,'2024-08-29',NULL,88996.00,'0856560030','hehee',20088996.00,NULL,20000000.00,1,0.00,5,NULL,NULL),(149,'số 2 nghách 70/67,Xã Thạnh An,Huyện Cần Giờ,Hồ Chí Minh','',1,'Hóa Đơn2482911935',NULL,NULL,'2024-08-29',NULL,88996.00,'0856560030','hehee',5088996.00,NULL,5000000.00,1,0.00,5,NULL,NULL),(150,'số 2 nghách 70/67,22407,Huyện Cần Giờ,Hồ Chí Minh','',1,'Hóa Đơn2482912941',NULL,NULL,'2024-08-29',NULL,88996.00,'0856560030','hehee',89546.00,NULL,550.00,1,0.00,5,NULL,NULL),(151,'số 2 nghách 70/67,Xã Thạnh An,Huyện Cần Giờ,Hồ Chí Minh','',1,'Hóa Đơn24829133123',NULL,NULL,'2024-08-29',NULL,88996.00,'0856560030','hehee',5088996.00,NULL,5000000.00,1,0.00,5,NULL,NULL),(152,'số 2 nghách 70/67,Xã Thạnh An,Huyện Cần Giờ,Hồ Chí Minh','',1,'Hóa Đơn2482914051',NULL,NULL,'2024-08-29',NULL,88996.00,'0856560030','hehee',90696.00,NULL,1700.00,1,0.00,5,NULL,NULL),(153,'số 2 nghách 70/67,22407,Huyện Cần Giờ,Hồ Chí Minh','',1,'Hóa Đơn24829193526',NULL,NULL,'2024-08-29',NULL,88996.00,'0856560030','hehee',488996.00,NULL,400000.00,1,0.00,5,NULL,NULL),(154,'số 2 nghách 70/67,Xã Thạnh An,Huyện Cần Giờ,Hồ Chí Minh','',1,'Hóa Đơn2482919574',NULL,NULL,'2024-08-29',NULL,88996.00,'0856560030','hehee',10088996.00,NULL,10000000.00,1,0.00,5,NULL,NULL),(155,'số 2 nghách 70/67,Xã Thạnh An,2090,Hồ Chí Minh','',1,'Hóa Đơn2482920334',NULL,NULL,'2024-08-29',NULL,88996.00,'0856560030','hehee',5088996.00,NULL,5000000.00,1,0.00,5,NULL,NULL),(156,'số 2 nghách 70/67,22407,2090,Hồ Chí Minh','',1,'Hóa Đơn2482920417',NULL,NULL,'2024-08-29',NULL,88996.00,'0856560030','hehee',188996.00,NULL,100000.00,1,0.00,5,NULL,NULL),(157,'số 2 nghách 70/67,Xã Thạnh An,Huyện Cần Giờ,Hồ Chí Minh','',1,'Hóa Đơn2482921816',NULL,NULL,'2024-08-29',NULL,88996.00,'0856560030','hehee',288996.00,NULL,200000.00,1,0.00,5,NULL,NULL),(158,'số 2 nghách 70/67,Xã Thạnh An,Huyện Cần Giờ,Hồ Chí Minh','',1,'Hóa Đơn24829212152',NULL,NULL,'2024-08-29',NULL,88996.00,'0856560030','hehee',188996.00,NULL,100000.00,1,0.00,5,NULL,NULL),(159,'số 2 nghách 70/67,Xã Thạnh An,Huyện Cần Giờ,Hồ Chí Minh','',1,'Hóa Đơn24829212237',NULL,NULL,'2024-08-29',NULL,88996.00,'0856560030','hehee',10088996.00,NULL,10000000.00,1,0.00,5,NULL,NULL),(160,'số 2 nghách 70/67,22407,Huyện Cần Giờ,202','',1,'Hóa Đơn2482923458',NULL,NULL,'2024-08-29',NULL,88996.00,'0856560030','hehee',89846.00,NULL,850.00,1,0.00,5,NULL,NULL),(161,'số 2 nghách 70/67,Xã Thạnh An,Huyện Cần Giờ,Hồ Chí Minh','',1,'Hóa Đơn2482923827',NULL,NULL,'2024-08-29',NULL,88996.00,'0856560030','hehee',5088996.00,NULL,5000000.00,1,0.00,5,NULL,NULL),(162,'số 2 nghách 70/67,Xã Thạnh An,Huyện Cần Giờ,Hồ Chí Minh','',1,'Hóa Đơn2482923849',NULL,NULL,'2024-08-29',NULL,88996.00,'0856560030','hehee',5088996.00,NULL,5000000.00,1,0.00,5,NULL,NULL),(163,'fffff,Xã Hợp Thanh,Huyện Lương Sơn,Hòa Bình','',1,'Hóa Đơn248301217',NULL,NULL,'2024-08-30',NULL,59003.00,'0232525412','abc',5059003.00,NULL,5000000.00,1,0.00,8,NULL,NULL),(164,NULL,NULL,0,'Hóa Đơn 2483022658',NULL,NULL,'2024-08-30',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,2,NULL,6),(165,'fffff,Xã Hợp Thanh,Huyện Lương Sơn,Hòa Bình','',1,'Hóa Đơn2483082353',NULL,NULL,'2024-08-30',NULL,59003.00,'0232525412','abc',76003.00,NULL,17000.00,1,0.00,8,NULL,NULL),(166,'fffff,Xã Hợp Thanh,Huyện Lương Sơn,Hòa Bình','',1,'Hóa Đơn2483082550',NULL,NULL,'2024-08-30',NULL,59003.00,'0232525412','abc',759003.00,NULL,700000.00,1,0.00,8,NULL,NULL),(167,'số 2 nghách 70/67,Xã Thạnh An,2090,Hồ Chí Minh','',1,'Hóa Đơn2483085619',NULL,NULL,'2024-08-30',NULL,88996.00,'0856560030','hehee',5122146.00,NULL,5033150.00,1,0.00,5,NULL,NULL),(168,'6666,Xã Phiêng Khoài,Huyện Yên Châu,Sơn La','',1,'Hóa Đơn2483091014',NULL,NULL,'2024-08-30',NULL,59003.00,'0225856666','ffffffghhhhhhhh',1000049003.00,10000.00,1000000000.00,1,0.00,5,2,NULL),(169,'số 2 nghách 70/67,Xã Thạnh An,Huyện Cần Giờ,Hồ Chí Minh','',1,'Hóa Đơn248309140',NULL,NULL,'2024-08-30',NULL,88996.00,'0856560030','hehee',188996.00,NULL,100000.00,1,0.00,5,NULL,NULL);
/*!40000 ALTER TABLE `hoa_don` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hoa_don_chi_tiet`
--

DROP TABLE IF EXISTS `hoa_don_chi_tiet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hoa_don_chi_tiet` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `de_giay` varchar(255) DEFAULT NULL,
  `gia_ban` decimal(19,2) DEFAULT NULL,
  `kich_thuoc` varchar(255) DEFAULT NULL,
  `mau_sac` varchar(255) DEFAULT NULL,
  `ngay_sua` date DEFAULT NULL,
  `ngay_tao` date DEFAULT NULL,
  `so_luong` int DEFAULT NULL,
  `ten_san_pham` varchar(255) DEFAULT NULL,
  `thuong_hieu` varchar(255) DEFAULT NULL,
  `trang_thai` tinyint DEFAULT NULL,
  `id_chi_tiet_san_pham` bigint DEFAULT NULL,
  `id_hoa_don` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4672qsis193xo4polycwrcwb8` (`id_chi_tiet_san_pham`),
  KEY `FK5adopt864mjisuy5xmgmy8iun` (`id_hoa_don`),
  CONSTRAINT `FK4672qsis193xo4polycwrcwb8` FOREIGN KEY (`id_chi_tiet_san_pham`) REFERENCES `chi_tiet_san_pham` (`id`),
  CONSTRAINT `FK5adopt864mjisuy5xmgmy8iun` FOREIGN KEY (`id_hoa_don`) REFERENCES `hoa_don` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hoa_don_chi_tiet`
--

LOCK TABLES `hoa_don_chi_tiet` WRITE;
/*!40000 ALTER TABLE `hoa_don_chi_tiet` DISABLE KEYS */;
INSERT INTO `hoa_don_chi_tiet` VALUES (1,'Đế Cao Su',100000.00,'35','#000000',NULL,'2024-07-22',10,'Giày Vans trắng xám sọc đen nam nữ',NULL,1,7,2),(2,'Đế Cao Su',100000.00,'35','#000000',NULL,'2024-07-22',110,'Giày Vans trắng xám sọc đen nam nữ',NULL,1,7,1),(3,'Đế Cao Su',100000.00,'35','#000000',NULL,'2024-07-22',1,'Giày Vans trắng xám sọc đen nam nữ',NULL,1,7,3),(4,'Đế Cao Su',100000.00,'35','#000000',NULL,'2024-07-22',1,'Giày Vans trắng xám sọc đen nam nữ',NULL,1,7,4),(5,'Đế Cao Su',100000.00,'35','#000000',NULL,'2024-07-22',101,'Giày Vans trắng xám sọc đen nam nữ',NULL,1,7,5),(6,'Đế Cao Su',100000.00,'35','#000000',NULL,'2024-07-22',1,'Giày Fila Oakmont Tr',NULL,1,6,6),(7,'Đế Cao Su',100000.00,'35','#000000',NULL,'2024-07-22',77,'Giày Vans trắng xám sọc đen nam nữ',NULL,1,7,7),(8,'Đế Cao Su',100000.00,'35','#f7f7f7',NULL,'2024-07-22',10,'Giày Vans trắng xám sọc đen nam nữ',NULL,1,7,18),(11,'Đế Cao Su',100000.00,'35','#f7f7f7',NULL,'2024-07-29',10,'Giày Vans trắng xám sọc đen nam nữ',NULL,1,7,24),(12,'Đế Cao Su',100000.00,'35','#f7f7f7',NULL,'2024-07-30',300,'Giày Vans trắng xám sọc đen nam nữ',NULL,1,7,38),(15,'Đế Cao Su',100000.00,'35','#0d1ce7',NULL,'2024-07-30',1,'Giày Fila Oakmont Tr',NULL,1,6,37),(16,'Đế Cao Su',100000.00,'35','#0d1ce7',NULL,'2024-07-30',1,'Giày Fila Oakmont Tr',NULL,1,6,36),(17,'Đế Cao Su',100000.00,'35','#0d1ce7',NULL,'2024-07-30',10,'Giày Fila Oakmont Tr',NULL,1,6,28),(18,'Đế Cao Su',100000.00,'35','#0d1ce7',NULL,'2024-07-30',10,'Giày Fila Oakmont Tr',NULL,1,6,26),(19,'Đế Cao Su',100000.00,'35','#0d1ce7',NULL,'2024-07-30',1,'Giày Fila Oakmont Tr',NULL,1,6,43),(20,'Đế Cao Su',100000.00,'35','#0d1ce7',NULL,'2024-07-30',1,'Giày Fila Oakmont Tr',NULL,1,6,44),(21,'Đế Cao Su',100000.00,'35','#0d1ce7',NULL,'2024-07-30',1,'Giày Fila Oakmont Tr',NULL,1,6,45),(22,'Đế Cao Su',100000.00,'35','#0d1ce7',NULL,'2024-07-30',1,'Giày Fila Oakmont Tr',NULL,1,6,46),(24,'Đế Cao Su',100000.00,'35','#0d1ce7',NULL,'2024-07-30',34,'Giày Fila Oakmont Tr',NULL,1,6,47),(25,'Đế Cao Su',20000000.00,'35','#edf104',NULL,'2024-07-30',10,'Giày Vans trắng xám sọc đen nam nữ',NULL,1,5,48),(32,'Đế Cao Su',100000.00,'35','#0d1ce7',NULL,'2024-07-30',1,'Giày Fila Oakmont Tr',NULL,1,6,56),(33,'Đế Cao Su',100000.00,'35','#f7f7f7',NULL,'2024-07-30',10,'Giày Vans trắng xám sọc đen nam nữ',NULL,1,7,56),(34,'Đế Cao Su',950.00,'35','#f7f7f7',NULL,'2024-07-30',100,'Giày Sneaker Sb Dunk Low Disrupt 2 Pale Ivory Black',NULL,1,4,56),(38,'Đế Cao Su',100000.00,'35','#f7f7f7',NULL,'2024-07-30',89,'Giày Vans trắng xám sọc đen nam nữ',NULL,1,7,61),(40,'Đế Cao Su',100000.00,'35','#0d1ce7',NULL,'2024-07-30',98,'Giày Fila Oakmont Tr',NULL,1,6,69),(43,'Đế Cao Su',650.00,'35','#f7f7f7',NULL,'2024-08-12',100,'.Giày Adidas Samba Classic White ',NULL,1,8,64),(45,'Đế Cao Su',20000000.00,'35','#edf104',NULL,'2024-08-17',10,'Giày Vans trắng xám sọc đen nam nữ',NULL,1,5,102),(46,'Đế Cao Su',20000000.00,'35','#edf104',NULL,'2024-08-19',1,'Giày Vans trắng xám sọc đen nam nữ',NULL,1,5,116),(47,'Đế Cao Su',20000000.00,'35','#edf104',NULL,'2024-08-19',1,'Giày Vans trắng xám sọc đen nam nữ',NULL,1,5,117),(48,'Đế Cao Su',20000000.00,'35','#edf104',NULL,'2024-08-19',1,'Giày Vans trắng xám sọc đen nam nữ',NULL,1,5,118),(49,'Đế Cao Su',20000000.00,'35','#edf104',NULL,'2024-08-19',10,'Giày Vans trắng xám sọc đen nam nữ',NULL,1,5,122),(50,'Đế Cao Su',20000000.00,'35','#edf104',NULL,'2024-08-19',1,'Giày Vans trắng xám sọc đen nam nữ',NULL,1,5,121),(51,'Đế Cao Su',20000000.00,'35','#edf104',NULL,'2024-08-19',1,'Giày Vans trắng xám sọc đen nam nữ',NULL,1,5,123),(52,'Đế Cao Su',20000000.00,'35','#edf104',NULL,'2024-08-19',1,'Giày Vans trắng xám sọc đen nam nữ',NULL,1,5,124),(53,'Đế Cao Su',20000000.00,'35','#edf104',NULL,'2024-08-19',1,'Giày Vans trắng xám sọc đen nam nữ',NULL,1,5,125),(54,'Đế Cao Su',20000000.00,'35','#edf104',NULL,'2024-08-19',1,'Giày Vans trắng xám sọc đen nam nữ',NULL,1,5,126),(55,'Đế Cao Su',20000000.00,'35','#edf104',NULL,'2024-08-19',1,'Giày Vans trắng xám sọc đen nam nữ',NULL,1,5,127),(56,'Đế Cao Su',20000000.00,'35','#edf104',NULL,'2024-08-19',1,'Giày Vans trắng xám sọc đen nam nữ',NULL,1,5,128),(57,'Đế Cao Su',20000000.00,'35','#edf104',NULL,'2024-08-26',10,'Giày Vans trắng xám sọc đen nam nữ',NULL,1,5,129),(58,'Đế Cao Su',20000000.00,'35','#edf104',NULL,'2024-08-26',1,'Giày Vans trắng xám sọc đen nam nữ',NULL,1,5,132),(59,'Đế Cao Su',20000000.00,'35','#edf104',NULL,'2024-08-26',10,'Giày Vans trắng xám sọc đen nam nữ',NULL,1,5,133),(60,'Đế Cao Su',20000000.00,'35','#edf104',NULL,'2024-08-26',1,'Giày Vans trắng xám sọc đen nam nữ',NULL,1,5,134),(61,'Đế Cao Su',20000000.00,'35','#edf104',NULL,'2024-08-26',2,'Giày Vans trắng xám sọc đen nam nữ',NULL,1,5,135),(62,'Đế Cao Su',20000000.00,'35','#edf104',NULL,'2024-08-26',1,'Giày Vans trắng xám sọc đen nam nữ',NULL,1,5,138),(63,'Đế Cao Su',20000000.00,'35','#edf104',NULL,'2024-08-26',10,'Giày Vans trắng xám sọc đen nam nữ',NULL,1,5,137),(64,'Đế Cao Su',20000000.00,'35','#edf104',NULL,'2024-08-26',10,'Giày Vans trắng xám sọc đen nam nữ',NULL,1,5,136),(65,'Đế Cao Su',20000000.00,'35','#edf104',NULL,'2024-08-26',1,'Giày Vans trắng xám sọc đen nam nữ',NULL,1,5,139),(66,'Đế Cao Su',20000000.00,'35','#edf104',NULL,'2024-08-27',1,'Giày Vans trắng xám sọc đen nam nữ',NULL,1,5,140);
/*!40000 ALTER TABLE `hoa_don_chi_tiet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `khach_hang`
--

DROP TABLE IF EXISTS `khach_hang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `khach_hang` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `gioi_tinh` tinyint DEFAULT NULL,
  `ma` varchar(255) DEFAULT NULL,
  `mat_khau` varchar(255) DEFAULT NULL,
  `ngay_sinh` datetime(6) DEFAULT NULL,
  `ngay_sua` date DEFAULT NULL,
  `ngay_tao` date DEFAULT NULL,
  `sdt` varchar(255) DEFAULT NULL,
  `ten` varchar(255) DEFAULT NULL,
  `tich_diem` decimal(65,2) DEFAULT NULL,
  `trang_thai` tinyint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_1lmmis0qdveete6l4prc9xlad` (`ma`),
  UNIQUE KEY `UK_6gn74xxiy11yxkbb2xmldnlld` (`sdt`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `khach_hang`
--

LOCK TABLES `khach_hang` WRITE;
/*!40000 ALTER TABLE `khach_hang` DISABLE KEYS */;
INSERT INTO `khach_hang` VALUES (1,'duongpvph20350@fpt.edu.vn',0,'KH1','5SPe9lR9HH','2003-08-09 00:00:00.000000','2024-07-22','2024-07-15','0398194211','Phạm Văn Dương',1000.00,1),(2,'khachle@gmail.com',0,'KH000','61a90d2e-5','2003-08-09 00:00:00.000000',NULL,'2024-07-22','0000000000','Khách lẻ',0.00,1),(3,'duongpvph20351@fpt.edu.vn',0,'KH3','5664940d-1','2003-08-09 00:00:00.000000',NULL,'2024-07-22','0398194212','Dương Quý Dương',2726247.12,1),(4,'duongthik38@gmail.com',0,'KH4','DKMUvtKPWb','2003-08-09 00:00:00.000000',NULL,'2024-07-22','0398194215','Đinh Tuấn An',650.00,1),(5,'maianh29hn@gmail.com',1,NULL,'duong1234','2003-08-09 00:00:00.000000','2024-08-03','2024-07-22','0398194219','Nguyễn Thị Mai Anh',150872.16,NULL),(6,'duong@gmail.com',0,'KH6','8SP7Ovb8dL','2003-08-09 00:00:00.000000','2024-08-12','2024-08-12','0398194210','Dương',2000000.00,1),(7,'maianh20hn@gmail.com',NULL,'KH69f073','a4936b',NULL,NULL,NULL,'0277943103','phạm văn chương',0.00,NULL),(8,'hacker@gmail.com',NULL,'KH7be6e1','e39201',NULL,NULL,NULL,'0973254141','abc',0.00,NULL);
/*!40000 ALTER TABLE `khach_hang` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kich_thuoc`
--

DROP TABLE IF EXISTS `kich_thuoc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `kich_thuoc` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `ma` varchar(255) NOT NULL,
  `ngay_sua` datetime(6) DEFAULT NULL,
  `ngay_tao` datetime(6) DEFAULT NULL,
  `ten` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_qtq2dqyq8ut9lu7xir4c01y0u` (`ma`),
  UNIQUE KEY `UK_gkywxpxndarpnx0eo0efvw653` (`ten`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kich_thuoc`
--

LOCK TABLES `kich_thuoc` WRITE;
/*!40000 ALTER TABLE `kich_thuoc` DISABLE KEYS */;
INSERT INTO `kich_thuoc` VALUES (1,'KT1','2024-07-21 23:45:45.408031','2024-07-21 23:45:45.408031','35'),(2,'KT2','2024-07-21 23:45:48.568727','2024-07-21 23:45:48.568727','36'),(3,'KT3','2024-07-21 23:45:53.540761','2024-07-21 23:45:53.540761','37'),(4,'KT4','2024-07-21 23:46:05.999209','2024-07-21 23:46:05.999209','38'),(5,'KT5','2024-07-21 23:46:09.389373','2024-07-21 23:46:09.389373','39'),(6,'KT6','2024-07-21 23:46:13.906522','2024-07-21 23:46:13.906522','40'),(7,'KT7','2024-07-21 23:46:18.383958','2024-07-21 23:46:18.383958','41'),(9,'KT9','2024-07-21 23:46:25.555847','2024-07-21 23:46:25.555847','42'),(11,'KT11','2024-07-21 23:46:36.677555','2024-07-21 23:46:36.677555','43'),(12,'KT12','2024-07-21 23:46:39.823316','2024-07-21 23:46:39.823316','44'),(13,'KT13','2024-07-21 23:46:42.952848','2024-07-21 23:46:42.952848','45'),(14,'KT14','2024-07-21 23:46:52.774817','2024-07-21 23:46:52.774817','46');
/*!40000 ALTER TABLE `kich_thuoc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mau_sac`
--

DROP TABLE IF EXISTS `mau_sac`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mau_sac` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `ma` varchar(255) NOT NULL,
  `ngay_sua` datetime(6) DEFAULT NULL,
  `ngay_tao` datetime(6) DEFAULT NULL,
  `ten` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_i8g6p3221tdj7b0i007uyx4uo` (`ma`),
  UNIQUE KEY `UK_obo04bgmvelnnhd3k64hgixj4` (`ten`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mau_sac`
--

LOCK TABLES `mau_sac` WRITE;
/*!40000 ALTER TABLE `mau_sac` DISABLE KEYS */;
INSERT INTO `mau_sac` VALUES (1,'MS1','2024-07-21 23:44:47.174213','2024-07-21 23:44:47.174213','xanh'),(2,'MS2','2024-07-21 23:44:51.894235','2024-07-21 23:44:51.894235','vàng'),(3,'MS3','2024-07-21 23:44:59.824814','2024-07-21 23:44:59.824814','tím '),(4,'MS4','2024-07-21 23:45:10.213279','2024-07-21 23:45:10.213279','nâu'),(6,'MS6','2024-07-21 23:45:31.330284','2024-07-21 23:45:31.330284','au'),(7,'MS7','2024-08-12 13:38:09.227871','2024-08-12 13:38:09.227871','đỏ');
/*!40000 ALTER TABLE `mau_sac` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nha_cung_cap`
--

DROP TABLE IF EXISTS `nha_cung_cap`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nha_cung_cap` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `ma` varchar(255) NOT NULL,
  `ngay_sua` date DEFAULT NULL,
  `ngay_tao` date DEFAULT NULL,
  `ten` varchar(255) NOT NULL,
  `trang_thai` tinyint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_7xxf4e5dnlq4c7mrsa337gv5p` (`ma`),
  UNIQUE KEY `UK_q1kg3v3hdy6m39h50s0iggbev` (`ten`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nha_cung_cap`
--

LOCK TABLES `nha_cung_cap` WRITE;
/*!40000 ALTER TABLE `nha_cung_cap` DISABLE KEYS */;
INSERT INTO `nha_cung_cap` VALUES (1,'NCC1','2024-07-21','2024-07-21','Công ty CNHH A',0),(2,'NCC2','2024-07-21','2024-07-21','Công ty CNHH B',0),(3,'NCC3','2024-07-21','2024-07-21','Công ty CNHH C',0),(4,'NCC4','2024-07-21','2024-07-21','Công ty CNHH D',0),(5,'NCC5','2024-07-21','2024-07-21','Công ty CNHH E',0),(6,'NCC6','2024-07-21','2024-07-21','Công ty CNHH F',0),(7,'NCC7','2024-07-21','2024-07-21','Công ty CNHH G',0);
/*!40000 ALTER TABLE `nha_cung_cap` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nhan_vien`
--

DROP TABLE IF EXISTS `nhan_vien`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nhan_vien` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cccd` varchar(255) DEFAULT NULL,
  `dia_chi` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `gioi_tinh` bit(1) DEFAULT NULL,
  `ma` varchar(255) DEFAULT NULL,
  `mat_khau` varchar(255) DEFAULT NULL,
  `ngay_sua` datetime(6) DEFAULT NULL,
  `ngay_tao` datetime(6) DEFAULT NULL,
  `sdt` varchar(255) DEFAULT NULL,
  `ten` varchar(255) DEFAULT NULL,
  `trang_thai` int DEFAULT NULL,
  `avata` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nhan_vien`
--

LOCK TABLES `nhan_vien` WRITE;
/*!40000 ALTER TABLE `nhan_vien` DISABLE KEYS */;
INSERT INTO `nhan_vien` VALUES (1,'035203002177',NULL,'phamvanduong754@gmail.com',_binary '','NV1','KRRGBJ6FLY','2024-07-22 00:33:58.944529','2024-07-15 14:20:31.939406','0398194211','HoangAnh',0,NULL),(2,'035203002179','Hà Nội','duongpvph20350@fpt.edu.vn',_binary '','NV2','OaxS2alxcF',NULL,'2024-07-22 00:33:27.714318','0398194215','Phạm Văn Dương',0,NULL),(3,'123456789000','ha nam','maianh29hn@gmail.com',_binary '','NV3','OaxS2alxcF',NULL,'2024-07-22 00:34:30.153300','0398194219','DuongQuyDuong',0,NULL),(4,'035203002188','sdfs','phamvanduong755@gmail.com',_binary '\0','NV4','OaxS2alxcF',NULL,'2024-07-22 00:36:02.355189','0398194200','Đinh Tuấn An',0,NULL),(5,'035203002166','Hà Nội','duongpvph20350@gmail.com',_binary '\0',NULL,'5Ls3KOk6Pd',NULL,'2024-08-12 11:54:57.393280','0398194216','tuấn an',0,NULL),(6,'035203002194','sdfgsgf','duongpvph20354@fpt.edu.vn',_binary '\0','NV6','6oGEpGmOVl',NULL,'2024-08-12 12:19:25.586452','0398194210','fdsds',0,NULL);
/*!40000 ALTER TABLE `nhan_vien` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phieu_giam_gia`
--

DROP TABLE IF EXISTS `phieu_giam_gia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `phieu_giam_gia` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `gia_tri_don_hang` decimal(38,2) DEFAULT NULL,
  `ma` varchar(255) DEFAULT NULL,
  `mo_ta` varchar(255) DEFAULT NULL,
  `muc_giam_gia` int DEFAULT NULL,
  `muc_giam_toi_da` decimal(38,2) DEFAULT NULL,
  `ngay_bat_dau` date DEFAULT NULL,
  `ngay_ket_thuc` date DEFAULT NULL,
  `ngay_sua` datetime(6) DEFAULT NULL,
  `ngay_tao` datetime(6) DEFAULT NULL,
  `so_luong` int DEFAULT NULL,
  `ten` varchar(255) DEFAULT NULL,
  `trang_thai` tinyint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_97c9sf127fuwh3tpnyn3wnwxh` (`ma`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phieu_giam_gia`
--

LOCK TABLES `phieu_giam_gia` WRITE;
/*!40000 ALTER TABLE `phieu_giam_gia` DISABLE KEYS */;
INSERT INTO `phieu_giam_gia` VALUES (1,100000.00,'PGG001','Giảm giá khi khách hàng mua đơn từ ...',10,10000.00,'2024-07-22','2024-07-26',NULL,'2024-07-22 00:38:26.048054',1997,'Giảm giá giày mới 1 ',1),(2,100.00,'PGG002','Giảm giá khi khách hàng mua đơn từ ...',30,10000.00,'2024-07-29','2024-08-01','2024-07-29 23:31:41.397000','2024-07-22 00:39:07.439472',92,'Giảm giá giày mới 2',0),(3,5000000.00,'PGG003','Giảm giá khi khách hàng mua đơn từ ...',100,100000.00,'2024-07-22','2024-07-24',NULL,'2024-07-22 00:39:40.329908',0,'Giảm giá giày mới 3',0),(4,600000.00,'PGG004','Gioi Han',50,500000.00,'2024-07-29','2024-07-31',NULL,'2024-07-29 18:53:02.765287',0,'ế thì giảm',0);
/*!40000 ALTER TABLE `phieu_giam_gia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `san_pham`
--

DROP TABLE IF EXISTS `san_pham`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `san_pham` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `anh_chinh` varchar(255) DEFAULT NULL,
  `ma` varchar(255) NOT NULL,
  `mo_ta` varchar(255) DEFAULT NULL,
  `ngay_sua` date DEFAULT NULL,
  `ngay_tao` date DEFAULT NULL,
  `ten` varchar(255) DEFAULT NULL,
  `trang_thai` tinyint DEFAULT NULL,
  `id_dong_san_pham` bigint DEFAULT NULL,
  `id_nha_cung_cap` bigint DEFAULT NULL,
  `id_thuong_hieu` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_131y1wu003wtcyqns9naaabhr` (`ma`),
  KEY `FKlds313o255x6s8a6cj58lo5hj` (`id_dong_san_pham`),
  KEY `FKp4cxtr4rl6xngwppqcjrorirl` (`id_nha_cung_cap`),
  KEY `FK5266y3xb83fch2ygdg6wf58qu` (`id_thuong_hieu`),
  CONSTRAINT `FK5266y3xb83fch2ygdg6wf58qu` FOREIGN KEY (`id_thuong_hieu`) REFERENCES `thuong_hieu` (`id`),
  CONSTRAINT `FKlds313o255x6s8a6cj58lo5hj` FOREIGN KEY (`id_dong_san_pham`) REFERENCES `dong_san_pham` (`id`),
  CONSTRAINT `FKp4cxtr4rl6xngwppqcjrorirl` FOREIGN KEY (`id_nha_cung_cap`) REFERENCES `nha_cung_cap` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `san_pham`
--

LOCK TABLES `san_pham` WRITE;
/*!40000 ALTER TABLE `san_pham` DISABLE KEYS */;
INSERT INTO `san_pham` VALUES (1,'vn-11134201-7r98o-lloufk5z5bz37b.jpg','SPP001','Fila Oakmont Tr là môt đôi giày  được làm từ 43% chất liệu tổng hợp, 25% dệt may và 32% từ da, những vật liệu có tính bền cao. Ngoài ra, đế giày được cấu tạo từ cao su và EVA giúp đảm bảo độ bám và sự êm ái cho người mang.','2024-07-22','2024-07-21','Giày Fila Oakmont Tr',0,2,1,3),(2,'vn-11134201-7r98o-lloufk9kklpba5.jpg','SP002','Fila Ray Tracer hiện đang là một trong những đôi giày sneaker gây sốt thị trường trong thời gian gần đây. Thân giày được gia công từ chất liệu da/vải cao cấp với độ ôm chân vừa phải. Giúp đôi giày cố định và nâng đỡ tốt bàn chân trong quá trình di chuyển.','2024-07-22','2024-07-22','Giày Fila Ray Tracer',0,4,6,6),(3,'vn-11134207-7r98o-lofgcn05sp9vf6.jpg','SP003','Giày Fila Raid Low 2000 là một đôi giày thể thao mang tính biểu tượng của thương hiệu Fila. Với thiết kế cổ thấp, đây là sự kết hợp giữa phong cách retro và hiện đại, tạo nên một cái nhìn độc đáo, trẻ trung và ấn tượng.','2024-07-22','2024-07-22','.Giày Adidas Samba Classic White ',0,2,1,4),(4,'vn-11134201-7r98o-lloufjy718tb92.jpg','SP004','Giày adidas Stan Smith chinh phục người dùng với thân giày được gia công hoàn toàn từ các thành phần thân thiện với môi trường, thân giày nhẹ tênh với vải dệt chứa đến 50% thành phần tái chế hiệu năng cao.','2024-07-22','2024-07-22','Giày Vans trắng xám sọc đen nam nữ',0,3,7,4),(5,'vn-11134201-7r98o-lty0oiia4ayfdf.jpg','SP005','Giày New Balance Shifted 90/60 là một đôi giày mới trong dòng giày thể thao của hãng NB. Được thiết kế để mang lại một cảm giác cân bằng khi đi lại, cung cấp sự thoải mái tối đa trong từng bước di chuyển.','2024-07-22','2024-07-22','Giày Sneaker Sb Dunk Low Disrupt 2 Pale Ivory Black',0,2,1,3),(6,'vn-11134201-7r98o-lty0oiia4ayfdf.jpg','SP007','HHH','2024-07-22','2024-07-22','thichthigiam',0,2,3,3),(7,'vn-11134201-7r98o-lkjebzhdcn80e1.jpg','PGG001',' khá \"gồ ghề\", được làm từ 43% chất liệu tổng hợp, 25% dệt may và 32% từ da, những vật liệu có tính bền cao. Ngoài ra, đế giày cảu Oakmont Tr được cấu tạo từ cao su và EVA giúp đảm bảo độ bám và sự êm ái cho người mang.','2024-07-30','2024-07-30','Phạm Văn Dương',0,1,1,1);
/*!40000 ALTER TABLE `san_pham` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `thuong_hieu`
--

DROP TABLE IF EXISTS `thuong_hieu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `thuong_hieu` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `ma` varchar(255) NOT NULL,
  `ngay_sua` date DEFAULT NULL,
  `ngay_tao` date DEFAULT NULL,
  `ten` varchar(255) NOT NULL,
  `trang_thai` tinyint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_123sssxf1x7ax6jyo4wdcvk36` (`ma`),
  UNIQUE KEY `UK_53w31rjwmistgvxj1lk2gnus9` (`ten`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `thuong_hieu`
--

LOCK TABLES `thuong_hieu` WRITE;
/*!40000 ALTER TABLE `thuong_hieu` DISABLE KEYS */;
INSERT INTO `thuong_hieu` VALUES (1,'TH1','2024-07-21','2024-07-21','NIKE',0),(2,'TH2','2024-07-21','2024-07-21','PUMA',0),(3,'TH3','2024-07-21','2024-07-21','ADIDAS',0),(4,'TH4','2024-07-21','2024-07-21','BALENCIAGA',0),(5,'TH5','2024-07-21','2024-07-21','GUCCI',0),(6,'TH6','2024-07-21','2024-07-21','BITISS',0);
/*!40000 ALTER TABLE `thuong_hieu` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-08-30  9:33:06
