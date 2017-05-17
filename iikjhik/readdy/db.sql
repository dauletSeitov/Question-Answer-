-- MySQL dump 10.13  Distrib 5.7.12, for Win32 (AMD64)
--
-- Host: localhost    Database: qa
-- ------------------------------------------------------
-- Server version	5.7.16-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `anwere`
--

DROP TABLE IF EXISTS `anwere`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `anwere` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `anwere` varchar(512) NOT NULL,
  `time` date NOT NULL,
  `questionRef` int(11) NOT NULL,
  `userRef` int(11) NOT NULL,
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `questionRef_idx` (`questionRef`),
  KEY `userRef_idx` (`userRef`),
  CONSTRAINT `questionRef` FOREIGN KEY (`questionRef`) REFERENCES `questions` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `userRef` FOREIGN KEY (`userRef`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='table were will be asnweres';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `anwere`
--

LOCK TABLES `anwere` WRITE;
/*!40000 ALTER TABLE `anwere` DISABLE KEYS */;
INSERT INTO `anwere` VALUES (1,'i know where yours ','2016-12-31',2,1),(2,'i know to','2016-12-29',2,1),(3,'i know wher is your title','2016-11-24',23,4),(4,'you forgot tetle?\r\n','2016-11-24',24,4),(5,'now you dont know','2016-11-24',23,4),(6,'Ololo','2016-11-24',23,4),(7,'Fuck you','2016-11-24',23,5),(8,'fuck your self','2016-11-24',23,5),(9,'login says he cnow','2016-11-26',3,4),(10,'Shat the fuck up\r\n','2016-11-26',3,5),(11,'do not buye iphone =)','2016-11-27',27,4),(12,'i stel yor phone','2016-11-27',27,4);
/*!40000 ALTER TABLE `anwere` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categories` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category` varchar(64) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idcategories_UNIQUE` (`id`),
  UNIQUE KEY `category_UNIQUE` (`category`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (20,'aksesuari'),(1,'books'),(18,'clothes'),(7,'default'),(2,'electric'),(4,'olo'),(19,'Peoples'),(3,'pets');
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `questions`
--

DROP TABLE IF EXISTS `questions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `questions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `question` varchar(512) NOT NULL,
  `date` date NOT NULL,
  `title` varchar(64) DEFAULT NULL,
  `userRef_` int(11) DEFAULT NULL,
  `categoryRef` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `xcc_idx` (`id`),
  KEY `dgfyg_idx` (`userRef_`),
  KEY `wrwer_idx` (`categoryRef`),
  CONSTRAINT `categoryRef` FOREIGN KEY (`categoryRef`) REFERENCES `categories` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `userRef_` FOREIGN KEY (`userRef_`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8 COMMENT='table where will save users questions ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `questions`
--

LOCK TABLES `questions` WRITE;
/*!40000 ALTER TABLE `questions` DISABLE KEYS */;
INSERT INTO `questions` VALUES (1,'where is justice','2016-11-13','where',1,3),(2,'where is justice','2001-01-20','where',1,2),(3,'where is justice','2016-12-31','where',2,1),(4,'i forgot my cat','2016-11-14','my cat is simka',1,3),(5,'the new question','2012-12-12','this is test where is my brain',2,NULL),(11,'question','2016-11-21','title',1,1),(12,'question','2016-11-21','title',1,1),(13,'question','2016-11-21','title',1,1),(14,'question','2016-11-21','title',1,1),(15,'question','2016-11-21','title',1,1),(16,'question','2016-11-21','title',1,1),(17,'question','2016-11-21','title',1,1),(18,'question','2016-11-21','title',1,1),(19,'question','2016-11-21','title',1,1),(20,'question','2016-11-23','title',1,1),(21,'question','2016-11-23','title',1,1),(22,'question','2016-11-23','title',1,1),(23,'question','2016-11-23','title',1,1),(24,'question','2016-11-24','title',1,1),(26,'i dont cnow where ','2016-11-24','i lost perchatki',2,18),(27,'but i dont now where','2016-11-24','i forgot my iphone',2,2),(28,'Ol zholda kelatr\r\n','2016-11-27','Where is my girlfriend\r\n',6,19),(29,'kjjkjk','2016-11-27','mnnmj',4,18),(30,'kkkk','2016-11-27','jjjjjjjjjjjjjjjjjjjjjjjjkjjjjjjjjjjjjjjjjjjjjjj',4,2),(31,'poteryala sumku ot nouta','2016-11-27','sumka',7,20);
/*!40000 ALTER TABLE `questions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `email` varchar(64) DEFAULT NULL,
  `login` varchar(64) DEFAULT NULL,
  `password` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_UNIQUE` (`login`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='table where will be users ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'simka','qeferqerferf3432w98eqw8e','simka@qa','200393a'),(2,'ololosh','sdsfs89f89sdf89sd98f','lsimkak@qa','somepaassword'),(3,'daulet','seilov.skynet@gmail.com','hoockerHunter@42','200393a'),(4,'login','login','login','login'),(5,'slmka','slmka','slmka','slmka'),(6,'@@@','@@@','@@@','@@@'),(7,'dinara','dinara','dinara','dinara');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-03-24 19:44:38
