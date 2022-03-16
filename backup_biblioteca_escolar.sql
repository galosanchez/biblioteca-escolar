CREATE DATABASE  IF NOT EXISTS `biblioteca_escolar` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `biblioteca_escolar`;
-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: biblioteca_escolar
-- ------------------------------------------------------
-- Server version	5.7.37-log

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
-- Table structure for table `authors`
--

DROP TABLE IF EXISTS `authors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `authors` (
  `id_author` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET latin1 NOT NULL,
  PRIMARY KEY (`id_author`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authors`
--

LOCK TABLES `authors` WRITE;
/*!40000 ALTER TABLE `authors` DISABLE KEYS */;
INSERT INTO `authors` VALUES (1,'Robert Jordan'),(2,'Taran Matharu'),(3,'Claudio Peña'),(4,'Juan Moisés De La Serna'),(5,'Stephen King'),(6,'Gabriel García Márquez'),(7,'Franz Kafka'),(8,'Paloma Sánchez'),(9,'Alice  Kellen'),(10,'Carlos  Fuentes'),(11,'Jorge Luis Tirado Lizarraga');
/*!40000 ALTER TABLE `authors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `books`
--

DROP TABLE IF EXISTS `books`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `books` (
  `id_book` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `editorial` varchar(100) DEFAULT NULL,
  `title` varchar(150) NOT NULL,
  `year` varchar(4) DEFAULT NULL,
  `id_author` int(11) unsigned NOT NULL,
  PRIMARY KEY (`id_book`),
  KEY `fk_books_authors_id_idx` (`id_author`),
  CONSTRAINT `fk_books_author_id` FOREIGN KEY (`id_author`) REFERENCES `authors` (`id_author`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `books`
--

LOCK TABLES `books` WRITE;
/*!40000 ALTER TABLE `books` DISABLE KEYS */;
INSERT INTO `books` VALUES (1,'Ediciones de la Torre','Las novelas rojas','1994',4),(2,'Penguin','La llama llama rojo pijama','2017',4),(3,'Prh Grupo Editorial','Después / Later','2021',5),(4,'Penguin Random House','El Instituto','2019',5),(5,'DEBOLSILLO','Posesión','2003',5),(6,'Ediciones Paraninfo, S.A','Instalación y mantenimiento de redes para transmisión de datos','2014',3),(7,'','La torres mas altas','',1),(8,'','La doncella de las Almas','2020',1),(9,'Novelas Ligeras Méxicanas','Romancero de la Muerte','2021',8),(10,'Fernando Barba','Doppelganger','2007',7),(11,'Elmer Ruddenskjrik','Revista Historias Pulp #5 El Exorcista','2021',2),(12,'Jorge Luis Tirado Lizarraga','En el Corazón de la Oscuridad','2021',11);
/*!40000 ALTER TABLE `books` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `copy_books`
--

DROP TABLE IF EXISTS `copy_books`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `copy_books` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `code` varchar(50) NOT NULL,
  `state` text NOT NULL,
  `active` enum('F','B') NOT NULL DEFAULT 'F',
  `id_book` int(11) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code_UNIQUE` (`code`),
  KEY `fk_copy_books_id_idx` (`id_book`),
  CONSTRAINT `fk_copybooks_books_id` FOREIGN KEY (`id_book`) REFERENCES `books` (`id_book`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `copy_books`
--

LOCK TABLES `copy_books` WRITE;
/*!40000 ALTER TABLE `copy_books` DISABLE KEYS */;
INSERT INTO `copy_books` VALUES (1,'MT-325','Deteriorado','F',1),(2,'MT-457','Bueno','F',1),(3,'LY-546','Excelente estado','B',2),(4,'LM-24','OK','B',3),(5,'DS-435','Bueno','B',4),(6,'BG-456','OK','B',4),(7,'MD34','OK','B',5),(8,'DE54-8','OK','B',5),(9,'AS23','MAL ESTADO','B',5),(10,'CD34','Buen estado','B',6),(11,'GL-65','Nuevo','B',5),(12,'TL-43','OK','B',7),(13,'GFBV-65','ok','F',8),(14,'FE87','OK','F',8),(15,'AS-87','Deteriorado','F',9),(16,'AS-899','OK','F',9),(17,'EW-876','OK','F',9),(18,'DDW65','OK','F',10),(19,'GF-67','Deteriorado','F',10),(20,'SA23','OK','F',10),(21,'AS-34','OK','F',10),(22,'CDDF-3','OK','F',11),(23,'WQ-213','Deteriorado','B',11),(24,'DS-4232','OK','F',11),(25,'SDF-45','OK','B',12),(26,'PM-67','OK','F',12),(27,'VBV-6','OK','F',12),(28,'VB-986','OK','F',12);
/*!40000 ALTER TABLE `copy_books` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `loans`
--

DROP TABLE IF EXISTS `loans`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `loans` (
  `id_loans` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `date_prestamo` date NOT NULL,
  `date_devolucion` date NOT NULL,
  `state` enum('L','D') NOT NULL DEFAULT 'L',
  `detail` text,
  `id_user` int(11) unsigned NOT NULL,
  `id_student` int(10) unsigned NOT NULL,
  `id_copy_book` int(11) unsigned NOT NULL,
  PRIMARY KEY (`id_loans`),
  KEY `id_student` (`id_student`),
  KEY `loans_ibfk_3` (`id_copy_book`),
  KEY `loans_ibfk_3_idx` (`id_user`),
  CONSTRAINT `loans_ibfk_2` FOREIGN KEY (`id_student`) REFERENCES `students` (`id_student`),
  CONSTRAINT `loans_ibfk_3` FOREIGN KEY (`id_copy_book`) REFERENCES `copy_books` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loans`
--

LOCK TABLES `loans` WRITE;
/*!40000 ALTER TABLE `loans` DISABLE KEYS */;
INSERT INTO `loans` VALUES (3,'2022-02-09','2022-02-10','D','',1,24,3),(4,'2022-02-10','2022-02-12','D','El libro tiene la pasta manchada',1,18,7),(5,'2022-03-01','2022-03-02','D','El libro tiene manchas en la pg.56',1,44,9),(6,'2022-03-02','2022-03-06','D','',1,60,6),(7,'2022-03-07','2022-03-15','D','',1,53,10),(8,'2022-03-09','2022-03-11','D','',1,54,12),(9,'2022-03-11','2022-03-11','L','',1,76,8),(10,'2022-03-11','2022-03-13','L','',1,93,5),(11,'2022-03-14','2022-03-17','L','',1,102,4),(14,'2022-03-01','2022-03-02','D','',1,100,11),(15,'2022-03-24','2022-03-26','L','',3,23,23),(16,'2022-03-20','2022-03-23','L','',3,25,25);
/*!40000 ALTER TABLE `loans` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `students`
--

DROP TABLE IF EXISTS `students`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `students` (
  `id_student` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `cedula` varchar(10) NOT NULL,
  `name` varchar(40) NOT NULL,
  `last_name` varchar(40) NOT NULL,
  `phone` varchar(15) DEFAULT NULL,
  `email` varchar(45) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_student`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  UNIQUE KEY `cedula_UNIQUE` (`cedula`)
) ENGINE=InnoDB AUTO_INCREMENT=104 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `students`
--

LOCK TABLES `students` WRITE;
/*!40000 ALTER TABLE `students` DISABLE KEYS */;
INSERT INTO `students` VALUES (1,'9872975726','Aloin','Martelll','8984892443','amartel28@gs.edu.ec','2013-03-17 04:15:30'),(2,'3749724484','Linell','Adamczyk','3479795387','ladamczyk6@gs.edu.ec','2010-08-19 02:22:09'),(3,'8864473466','Rik','MacDuffie','5346500066','rmacduffie5@gs.edu.ec','2015-10-12 09:37:02'),(4,'8608326707','Neill','Upjohn','1818859626','nupjohn56@gs.edu.ec','2012-11-14 21:59:49'),(5,'2454512197','Bord','Charlewood','2009240473','bcharlewood19@gs.edu.ec','2013-09-05 08:21:27'),(6,'6396665987','Barn','Sturges','4483345299','bsturges80@gs.edu.ec','2021-04-20 03:01:22'),(7,'4074101535','Haley','Borg','5418365581','hborg16@gs.edu.ec','2020-06-04 21:24:18'),(8,'5121580584','Manda','Torns','3683849642','mtorns64@gs.edu.ec','2010-06-03 05:43:16'),(9,'1738302862','Adele','Ridde','1122630137','aridde59@gs.edu.ec','2019-09-08 00:52:47'),(10,'8909809054','Nevil','Hedlestone','5629624184','nhedlestone16@gs.edu.ec','2019-01-29 12:27:00'),(11,'4714918977','Nessy','Forcade','7056161513','nforcade82@gs.edu.ec','2015-09-17 17:39:54'),(12,'8894939943','Lamont','Handy','2562296904','lhandy49@gs.edu.ec','2020-12-01 20:25:39'),(13,'2347620178','Boyce','Colum','1571141282','bcolum59@gs.edu.ec','2020-07-24 00:56:09'),(14,'2650360465','Garth','Coare','1879726280','gcoare13@gs.edu.ec','2014-03-17 10:11:57'),(15,'8332444839','Berkeley','Pacher','8503671474','bpacher24@gs.edu.ec','2021-05-21 21:27:13'),(16,'1673565882','Weston','Lamasna','3874713054','wlamasna61@gs.edu.ec','2018-03-16 20:00:05'),(17,'3050467531','Lamont','Crighton','7162953106','lcrighton67@gs.edu.ec','2015-02-04 06:17:12'),(18,'6217576319','Adelle','Hovard','3046234259','ahovard99@gs.edu.ec','2010-10-21 07:44:50'),(19,'2945837965','Agnola','Quiddinton','1286804104','aquiddinton90@gs.edu.ec','2010-07-24 06:25:59'),(20,'7554636416','Hannah','Clingan','2117183306','hclingan84@gs.edu.ec','2016-06-03 13:53:56'),(21,'8780917538','Bengt','Bonnesen','6669149350','bbonnesen53@gs.edu.ec','2021-09-23 06:52:06'),(22,'2651966982','Gertie','Mathias','8771342354','gmathias88@gs.edu.ec','2010-09-24 16:15:50'),(23,'7123879306','Judah','Gaymar','4388438011','jgaymar94@gs.edu.ec','2015-07-25 04:44:55'),(24,'1928693252','Gil','Cowcha','5108235486','gcowcha2@gs.edu.ec','2010-11-05 02:15:59'),(25,'4734771021','Nert','Sallis','5861279521','nsallis47@gs.edu.ec','2010-05-09 00:23:02'),(26,'7882283068','Noemi','Inston','7543646760','ninston25@gs.edu.ec','2014-03-21 12:56:22'),(27,'1216831469','Dieter','Stratford','5927588235','dstratford86@gs.edu.ec','2016-05-21 22:24:03'),(28,'4791574755','Janine','Scotsbrook','8115233587','jscotsbrook80@gs.edu.ec','2013-08-10 17:32:38'),(29,'7417722847','Waldo','Thominga','3911260692','wthominga31@gs.edu.ec','2020-02-09 02:50:18'),(30,'6731541489','Ddene','Hurler','8013898066','dhurler9@gs.edu.ec','2016-09-13 16:06:32'),(31,'3199255640','Gratiana','Cortin','9806514204','gcortin2@gs.edu.ec','2010-12-24 00:37:58'),(32,'9791979604','Ayn','Leipold','7861101821','aleipold28@gs.edu.ec','2020-08-15 12:53:41'),(33,'2358879185','Nels','Adamkiewicz','1501330861','nadamkiewicz56@gs.edu.ec','2012-03-29 00:32:24'),(34,'4145925775','Vida','Aloigi','1166125140','valoigi85@gs.edu.ec','2021-06-05 01:59:38'),(35,'8656461271','Adey','Kitchenside','2695577770','akitchenside78@gs.edu.ec','2016-08-21 22:32:06'),(36,'1801409813','Edgard','Brind','4387338815','ebrind14@gs.edu.ec','2021-09-21 04:12:35'),(37,'1106829369','Jere','Osband','2815760669','josband91@gs.edu.ec','2011-05-15 12:32:30'),(38,'7591113820','Phyllida','Manass','1891677381','pmanass61@gs.edu.ec','2011-05-13 14:01:57'),(39,'3455474934','Dreddy','McElhinney','3614333411','dmcelhinney7@gs.edu.ec','2015-07-09 16:46:37'),(40,'8858552856','Tann','Blase','3338600380','tblase26@gs.edu.ec','2012-03-03 03:32:56'),(41,'1280087338','Torrie','Smooth','5516126845','tsmooth56@gs.edu.ec','2016-01-26 21:04:30'),(42,'4043869908','Adolf','Ruddell','7592063134','aruddell15@gs.edu.ec','2011-07-12 20:31:13'),(43,'3064206158','Nickolai','Tome','2272253666','ntome56@gs.edu.ec','2016-06-18 05:37:06'),(44,'3792615770','Gui','Brauner','4524943924','gbrauner37@gs.edu.ec','2014-09-17 13:13:17'),(45,'7811736861','Brina','Heighton','9996463481','bheighton15@gs.edu.ec','2012-06-04 23:22:46'),(46,'4104667152','Nathaniel','Giacomi','3569759882','ngiacomi22@gs.edu.ec','2014-09-24 05:44:44'),(47,'3736410329','Fraze','Sissens','1922669040','fsissens45@gs.edu.ec','2010-01-09 02:59:45'),(48,'6214553394','Lurleen','Hubber','5183250601','lhubber32@gs.edu.ec','2019-06-24 06:20:42'),(49,'8136041348','Emylee','Prichard','7265259234','eprichard21@gs.edu.ec','2014-04-24 13:33:25'),(50,'6431057663','Burr','Wooler','9131608406','bwooler82@gs.edu.ec','2020-05-07 14:48:57'),(51,'3008429796','Jenna','Loblie','5822052361','jloblie99@gs.edu.ec','2012-05-13 22:19:35'),(52,'7068153532','Yulma','Imeson','9387289619','yimeson39@gs.edu.ec','2016-08-11 10:15:59'),(53,'6110164560','Gloriane','Freathy','2167923263','gfreathy8@gs.edu.ec','2019-11-18 21:04:58'),(54,'7004398481','Verena','Burghall','9661179018','vburghall3@gs.edu.ec','2017-02-06 16:46:13'),(55,'6871363499','Terese','Ree','1725069197','tree57@gs.edu.ec','2012-08-12 13:29:04'),(56,'8160440298','Cassi','Bauer','6139154568','cbauer95@gs.edu.ec','2011-02-14 10:25:58'),(57,'7262843935','Ibby','Grebner','3891897543','igrebner73@gs.edu.ec','2017-10-02 22:16:55'),(58,'8490113856','Willis','Bailiss','4748601271','wbailiss87@gs.edu.ec','2018-02-04 01:25:06'),(59,'6378925694','Christine','Sayles','1739310869','csayles13@gs.edu.ec','2020-10-29 06:54:42'),(60,'6140978072','Luise','Demageard','7546105079','ldemageard39@gs.edu.ec','2015-10-16 20:29:12'),(61,'2736964727','Alfie','Churchard','9872240799','achurchard3@gs.edu.ec','2020-07-13 06:43:04'),(62,'9969913653','Claire','McTiernan','6791815335','cmctiernan12@gs.edu.ec','2020-12-29 19:44:20'),(63,'8806791806','Trixi','Deppen','6553131701','tdeppen82@gs.edu.ec','2013-11-29 08:59:11'),(64,'4966349438','Tanitansy','Cornish','4762753596','tcornish90@gs.edu.ec','2013-03-28 18:51:52'),(65,'4455601941','Engelbert','Ryder','5209403243','eryder2@gs.edu.ec','2013-11-12 14:40:58'),(66,'2612582199','Beatrisa','Brangan','1714346543','bbrangan3@gs.edu.ec','2010-11-03 04:43:41'),(67,'3622308118','Zebulen','Pennells','5889250658','zpennells27@gs.edu.ec','2014-06-27 15:43:47'),(68,'8112312152','Terry','Rumin','6808100008','trumin17@gs.edu.ec','2016-12-23 22:59:01'),(69,'4395577613','Lauraine','MacGeaney','8151293464','lmacgeaney92@gs.edu.ec','2013-01-09 10:49:46'),(70,'9518239732','Duncan','Grocock','6851128684','dgrocock30@gs.edu.ec','2017-02-09 08:31:33'),(71,'3188359622','Debra','Shelford','6061226409','dshelford83@gs.edu.ec','2017-05-11 00:51:05'),(72,'4864871288','Randy','Pyett','9218588824','rpyett13@gs.edu.ec','2015-01-07 14:05:21'),(73,'5017352318','Lisetta','Gambles','8736940796','lgambles63@gs.edu.ec','2017-05-09 17:41:09'),(74,'7988236236','Hale','Goodswen','7023570307','hgoodswen73@gs.edu.ec','2015-04-12 01:19:15'),(75,'6027935370','Lurline','Ernshaw','5962100086','lernshaw48@gs.edu.ec','2020-06-23 05:40:06'),(76,'4810857840','Eb','Turfus','4095704461','eturfus2@gs.edu.ec','2020-07-05 06:54:04'),(77,'3515699152','Andris','Beldham','6541352786','abeldham33@gs.edu.ec','2019-09-16 09:13:52'),(78,'5567896782','Daniel','Thackston','2847807730','dthackston97@gs.edu.ec','2017-02-17 13:50:45'),(79,'8500121919','Anna','Swane','8423003266','aswane65@gs.edu.ec','2021-02-06 06:07:14'),(80,'3452421020','Ivan','Luciani','4738560832','iluciani95@gs.edu.ec','2013-05-14 13:04:58'),(81,'1804956497','Mabelle','Dedney','2201650924','mdedney49@gs.edu.ec','2013-04-01 19:30:18'),(82,'6121425099','Shandeigh','Harsnep','5657378907','sharsnep59@gs.edu.ec','2012-01-02 05:39:55'),(83,'2245719165','Esteban','Calton','3766637826','ecalton76@gs.edu.ec','2019-05-25 17:32:58'),(84,'2940184680','Davita','Owenson','7793475380','dowenson84@gs.edu.ec','2013-06-14 03:19:39'),(85,'3967140143','Amerigo','Neller','8503981840','aneller8@gs.edu.ec','2011-04-24 05:00:52'),(86,'6178010841','Eben','Yankishin','9777941600','eyankishin43@gs.edu.ec','2019-01-19 09:38:04'),(87,'9102972870','Elysee','Joselson','2173548615','ejoselson42@gs.edu.ec','2016-11-17 06:33:19'),(88,'4782574695','Burl','Bugbird','3651125031','bbugbird10@gs.edu.ec','2019-10-16 10:18:10'),(89,'2395372347','Ilyssa','Grime','9161104989','igrime9@gs.edu.ec','2016-01-17 06:40:24'),(90,'4586835443','Ardys','Ayshford','8443737803','aayshford3@gs.edu.ec','2015-12-22 19:47:21'),(91,'4062096539','Adolph','Sellick','8883718296','asellick34@gs.edu.ec','2021-03-27 18:50:05'),(92,'3613675116','Bert','Housden','8967100667','bhousden81@gs.edu.ec','2010-12-10 06:22:56'),(93,'1191012605','Adella','Robardley','3515916522','arobardley40@gs.edu.ec','2019-01-16 12:58:48'),(94,'3204951428','Tann','Uc','5499123238','tuc33@gs.edu.ec','2019-09-29 23:08:48'),(95,'8219063738','Diarmid','De Hooge','2459072686','dde hooge43@gs.edu.ec','2016-08-09 23:39:00'),(96,'8564677420','Forrest','Snook','2697113096','fsnook53@gs.edu.ec','2014-03-08 20:29:58'),(97,'9216414121','Ainslee','Jodlowski','7553678678','ajodlowski21@gs.edu.ec','2017-12-01 10:24:45'),(98,'1917904946','Denyse','MacScherie','9284974738','dmacscherie94@gs.edu.ec','2019-10-17 22:35:14'),(99,'2736262026','Mylo','Wibrew','6283293176','mwibrew46@gs.edu.ec','2020-05-04 15:45:56'),(100,'9580745330','Charin','Spink','2885321780','cspink97@gs.edu.ec','2013-11-10 20:56:54'),(101,'1467835633','Galo','Sanchez','0983060783','gsanchez23@gs.edu.ec','2021-11-16 23:19:12'),(102,'3456781234','Migue','Del','412345','migue_dell@gmail.com','2022-01-10 21:37:46'),(103,'9126587124','Juan','Ester','','juanest@gmail.com','2022-01-11 00:00:30');
/*!40000 ALTER TABLE `students` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id_user` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user` varchar(40) NOT NULL,
  `password` varchar(45) NOT NULL,
  `name` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `type` enum('A','G') NOT NULL DEFAULT 'G',
  PRIMARY KEY (`id_user`),
  UNIQUE KEY `user` (`user`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'admin','d033e22ae348aeb5660fc2140aec35850c4da997','Administrador','admin@gs.edu.com','2021-09-28 04:19:56','A'),(2,'miguealarcon','a7918958b26ffce4c2acd087c96f708e1d43c0ad','Migual Alarcon','miguealarcon@gs.edu.com','2021-09-28 04:19:56','G'),(3,'chissantos','7d52b7af8168e126a1d1e59a380c3faf029a7ac0','Cris Santos','crissantos@gs.edu.com','2021-09-28 04:19:56','G'),(4,'monicaestrada','a20bd768e1fbe001b472110f3134552a39b315b1','Monica Estrada','monicaestrada@gs.udu.ec','2021-09-28 04:19:56','G');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'biblioteca_escolar'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-03-15 19:11:02
