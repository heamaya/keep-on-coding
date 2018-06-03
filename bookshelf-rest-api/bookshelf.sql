-- MySQL dump 10.13  Distrib 5.7.22, for Linux (x86_64)
--
-- Host: localhost    Database: bookshelf
-- ------------------------------------------------------
-- Server version	5.7.22-0ubuntu0.16.04.1

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
-- Table structure for table `author`
--
DROP TABLE IF EXISTS `bookshelf`.`author`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bookshelf`.`author` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `author`
--

LOCK TABLES `bookshelf`.`author` WRITE;
/*!40000 ALTER TABLE `author` DISABLE KEYS */;
INSERT INTO `bookshelf`.`author` VALUES (3,'Raoul-Gabriel Urma'),(4,'Mario Fusco'),(5,'Alan Mycroft'),(6,'Robert C. Martin'),(7,'Aditya Bhargava');
/*!40000 ALTER TABLE `author` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `bookshelf`.`book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bookshelf`.`book` (
  `isbn` varchar(100) NOT NULL,
  `title` varchar(200) DEFAULT NULL,
  `subtitle` varchar(200) DEFAULT NULL,
  `published` timestamp NULL DEFAULT NULL,
  `publisher` varchar(100) DEFAULT NULL,
  `pages` int(11) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  `in_stock` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`isbn`),
  UNIQUE KEY `isbn_UNIQUE` (`isbn`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `bookshelf`.`book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `bookshelf`.`book` VALUES ('978-0134494166','Clean Architecture','A Craftsman\'s Guide to Software Structure and Design','2017-09-20 03:00:00','Prentice Hall',432,'By applying universal rules of software architecture, you can\ndramatically improve developer productivity throughout the life of any software system. Now,\nbuilding upon the success of his best-selling books Clean Code and The Clean Coder, legendary\nsoftware craftsman Robert C. Martin (\"Uncle Bob\") reveals those rules and helps you apply\nthem.',1),('978-1617291999','Java 8 in Action','Lambdas, Streams, and functional-style programming','2014-08-28 03:00:00','Manning Publications',424,'Java 8 in Action is a clearly written guide to the new features of Java\n8. The book covers lambdas, streams, and functional-style programming. With Java 8\'s functional\nfeatures you can now write more concise code in less time, and also automatically benefit from\nmulticore architectures. It\'s time to dig in!',1),('978-1617292231','Grokking Algorithms','An illustrated guide for programmers and other curious people','2016-05-01 03:00:00','Manning Publications',256,'Grokking Algorithms is a fully illustrated, friendly guide that teaches\nyou how to apply common algorithms to the practical problems you face every day as a programmer.\nYou\'ll start with sorting and searching and, as you build up your skills in thinking\nalgorithmically, you\'ll tackle more complex concerns such as data compression and artificial\nintelligence. Each carefully presented example includes helpful diagrams and fully annotated code\nsamples in Python.',1);
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book_author`
--

DROP TABLE IF EXISTS `bookshelf`.`book_author`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bookshelf`.`book_author` (
  `isbn` varchar(100) NOT NULL,
  `author_id` bigint(20) NOT NULL,
  PRIMARY KEY (`isbn`,`author_id`),
  KEY `fk_author_id_idx` (`author_id`),
  CONSTRAINT `fk_author_id` FOREIGN KEY (`author_id`) REFERENCES `author` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_book_isbn` FOREIGN KEY (`isbn`) REFERENCES `book` (`isbn`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book_author`
--

LOCK TABLES `bookshelf`.`book_author` WRITE;
/*!40000 ALTER TABLE `book_author` DISABLE KEYS */;
INSERT INTO `bookshelf`.`book_author` VALUES ('978-1617291999',3),('978-1617291999',4),('978-1617291999',5),('978-1617292231',7);
/*!40000 ALTER TABLE `book_author` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-06-03 11:52:16
