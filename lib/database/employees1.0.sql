-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Hostiteľ: 127.0.0.1
-- Čas generovania: Št 14.Apr 2016, 06:07
-- Verzia serveru: 5.7.9
-- Verzia PHP: 5.6.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Databáza: `employees`
--

-- --------------------------------------------------------

--
-- Štruktúra tabuľky pre tabuľku `address`
--

DROP TABLE IF EXISTS `address`;
CREATE TABLE IF NOT EXISTS `address` (
  `Employee_id` int(20) NOT NULL,
  `Country` varchar(25) NOT NULL,
  `City` varchar(25) NOT NULL,
  `Street` varchar(20) NOT NULL,
  `Number` int(10) NOT NULL,
  `Postcode` int(10) NOT NULL,
  PRIMARY KEY (`Employee_id`),
  KEY `Employee_id` (`Employee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Štruktúra tabuľky pre tabuľku `contact`
--

DROP TABLE IF EXISTS `contact`;
CREATE TABLE IF NOT EXISTS `contact` (
  `Employee_id` int(20) NOT NULL,
  `Phone` int(15) NOT NULL,
  `Email` varchar(40) NOT NULL,
  PRIMARY KEY (`Employee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Sťahujem dáta pre tabuľku `contact`
--

INSERT INTO `contact` (`Employee_id`, `Phone`, `Email`) VALUES
(1, 917555111, 'Email@email.sk'),
(2, 9858851, 'email1@email.sk'),
(3, 917123456, 'EMIAL3@EMAIL.SK');

-- --------------------------------------------------------

--
-- Štruktúra tabuľky pre tabuľku `employee`
--

DROP TABLE IF EXISTS `employee`;
CREATE TABLE IF NOT EXISTS `employee` (
  `ID` int(20) NOT NULL AUTO_INCREMENT,
  `Name` varchar(25) NOT NULL,
  `Surname` varchar(25) NOT NULL,
  `Sex` enum('Female','Male') NOT NULL,
  `Birth_date` date NOT NULL,
  `Start_date` date DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `ID` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

--
-- Sťahujem dáta pre tabuľku `employee`
--

INSERT INTO `employee` (`ID`, `Name`, `Surname`, `Sex`, `Birth_date`, `Start_date`) VALUES
(1, 'dominik', 'kole', 'Female', '1994-04-09', NULL),
(2, 'Patrik', 'Paty', 'Male', '2016-04-04', NULL),
(3, 'pater', 'varo', 'Male', '2016-04-04', NULL),
(9, 'Patrik', 'Paty', 'Male', '2016-04-04', NULL),
(10, 'Patrik', 'Paty', 'Male', '2016-04-04', NULL),
(11, 'Patrik', 'Paty', 'Male', '2016-04-04', NULL),
(12, 'Patrik', 'Paty', 'Male', '2016-04-04', NULL);

-- --------------------------------------------------------

--
-- Štruktúra tabuľky pre tabuľku `salary`
--

DROP TABLE IF EXISTS `salary`;
CREATE TABLE IF NOT EXISTS `salary` (
  `Employee_id` int(20) NOT NULL,
  `Hours` int(20) NOT NULL,
  `Salary` float NOT NULL,
  `PerHour_Salary` float NOT NULL,
  PRIMARY KEY (`Employee_id`),
  KEY `Employee_id` (`Employee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Obmedzenie pre exportované tabuľky
--

--
-- Obmedzenie pre tabuľku `address`
--
ALTER TABLE `address`
  ADD CONSTRAINT `address_ibfk_1` FOREIGN KEY (`Employee_id`) REFERENCES `employee` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Obmedzenie pre tabuľku `contact`
--
ALTER TABLE `contact`
  ADD CONSTRAINT `contact_ibfk_1` FOREIGN KEY (`Employee_id`) REFERENCES `employee` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Obmedzenie pre tabuľku `salary`
--
ALTER TABLE `salary`
  ADD CONSTRAINT `salary_ibfk_1` FOREIGN KEY (`Employee_id`) REFERENCES `employee` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
