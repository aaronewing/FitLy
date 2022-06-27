-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.7.3-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- Dumping data for table capstone.exercises: ~5 rows (approximately)
/*!40000 ALTER TABLE `exercises` DISABLE KEYS */;
INSERT IGNORE INTO `exercises` (`id`, `name`) VALUES
	(1, 'Deadlift'),
	(2, 'Skullcrushers'),
	(3, 'Bench Press'),
	(4, 'Calf Raisers'),
	(5, 'Leg Press');
/*!40000 ALTER TABLE `exercises` ENABLE KEYS */;

-- Dumping data for table capstone.food: ~8 rows (approximately)
/*!40000 ALTER TABLE `food` DISABLE KEYS */;
INSERT IGNORE INTO `food` (`id`, `name`) VALUES
	(1, 'Chicken Wings'),
	(2, 'Sushi'),
	(3, 'Salad'),
	(4, 'Smoothie'),
	(5, 'Burger'),
	(6, 'Tuna Sandwich'),
	(7, 'Tilapia'),
	(8, 'French Fries');
/*!40000 ALTER TABLE `food` ENABLE KEYS */;

-- Dumping data for table capstone.goals: ~0 rows (approximately)
/*!40000 ALTER TABLE `goals` DISABLE KEYS */;
/*!40000 ALTER TABLE `goals` ENABLE KEYS */;

-- Dumping data for table capstone.user: ~0 rows (approximately)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

-- Dumping data for table capstone.user_exercises: ~0 rows (approximately)
/*!40000 ALTER TABLE `user_exercises` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_exercises` ENABLE KEYS */;

-- Dumping data for table capstone.user_food: ~0 rows (approximately)
/*!40000 ALTER TABLE `user_food` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_food` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
