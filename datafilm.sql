-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 30, 2020 at 06:15 PM
-- Server version: 10.4.6-MariaDB
-- PHP Version: 7.2.22

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dbfilm`
--

-- --------------------------------------------------------

--
-- Table structure for table `datafilm`
--

CREATE TABLE `datafilm` (
  `judul` varchar(50) NOT NULL,
  `tipe` varchar(50) NOT NULL,
  `episode` varchar(50) NOT NULL,
  `genre` varchar(50) NOT NULL,
  `status` varchar(20) NOT NULL,
  `rating` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `datafilm`
--

INSERT INTO `datafilm` (`judul`, `tipe`, `episode`, `genre`, `status`, `rating`) VALUES
('Suicide Squad', 'Film ', '0', 'Action', 'Selesai', '5'),
('Twilight Saga', 'Film', '100', 'Romance', 'Belum', '4.5'),
('The Doll', 'FTV', '430', 'Thriller,Horror', 'Selesai', '3.2');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
