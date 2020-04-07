-- phpMyAdmin SQL Dump
-- version 4.8.2
-- https://www.phpmyadmin.net/
--
-- Värd: localhost
-- Tid vid skapande: 07 apr 2020 kl 13:02
-- Serverversion: 5.6.38
-- PHP-version: 7.1.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Databas: `memedb`
--

-- --------------------------------------------------------

--
-- Tabellstruktur `accounts`
--

CREATE TABLE `accounts` (
  `id` int(11) NOT NULL,
  `firstname` varchar(20) NOT NULL,
  `lastname` varchar(20) NOT NULL,
  `username` varchar(30) NOT NULL,
  `password` varchar(60) NOT NULL,
  `email` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumpning av Data i tabell `accounts`
--

INSERT INTO `accounts` (`id`, `firstname`, `lastname`, `username`, `password`, `email`) VALUES
(1, 'pepe', 'hands', 'pepe', '$2y$10$G4UuSJv5Mbg9M3DCAmrf1eBovVKQh29y/opT39QDyyVibgPhlHV0e', 'pepe@peppo.com'),
(2, 'pepe', 'knob', 'usrnm', 'pwdf', 'email'),
(4, 'Firstname', 'Lastname', 'Username001', '$2a$10$BGkTuDLjjw3TP.a9YX8f1OvPyhHiY3Xj/C.8PaDQy6tASTpXhch4e', 'email@abc.xyz'),
(5, 'Firstname', 'Lastname', 'Username', '$2a$10$z9yxsp3u7pYNHT9ffLW1uOtlceOrwPT31k1tBGCAtkJV0Ncoy16eq', 'email@abc.xyzz'),
(6, 'Firstname', 'Lastname', 'Username12332', '$2a$10$DxUSsuUuYwWN/sZoWjNBaumApOW.JnVjFtntsK1vslpsk.fr99Vjy', 'email@abc.xyzzssss'),
(7, '[]', '[]', '[]', '[]', '[]'),
(8, 'Michael', 'Jackson', 'username123012', 'Password010203', 'email@michael.jackson'),
(9, 'Michael', 'Jackson', 'username1230122', '$2a$10$6oo9yi0zmAwNWnMFevq.puKMtMVefb8NWXTl5QqaMc1//IrHNX3gq', 'email@michael.jacksons'),
(10, 'firstname', 'lastname', 'username0123', '$2a$10$D4A/5mvhfJh7Lj84jtTXIOb46S9qRn5W055mAZUOzXBUjp2H/V5kC', 'email@kek.cm'),
(11, 'firstname', 'lastname', 'username01234', '$2a$10$NlucSVHyp98xP7VrQkXwjup3VY7O/8Rt4V3Wbp1yJg9IkWPr.1z5q', 'email@kek.cmd'),
(12, 'firstname', 'lastname', 'username012345', '$2a$10$abppevQaMNp0kOMP3ppK/e.7xkvo.siXIfgs0P2lnmPjfsiSpKKxu', 'email@kek.cmdd'),
(13, 'firstname', 'lastname', 'username01239', '$2a$10$zPEKdIK5zQre6G2t8tzIrOtToqB3A8SRFxrAWdm3WhnfaaApeJpgC', 'email@kek.cmz'),
(14, 'firstname', 'lastname', 'username9123', '$2a$10$AyRipfEUiBZpdtL9UJotY.Kt19oCf8oyDGJxVxDMdSmZCZiJHJ3Bm', 'email@password.com'),
(15, 'firstname', 'lastname', 'username912h', '$2a$10$rEVL7tnarYz1GAtRBRLmsOorjj5RPq6Z5CXnX1VD7uskmBIdFYR6.', 'email@pascword.com'),
(16, 'Lord', 'Pepe', 'lordpepe0123', '$2a$10$vTKvPWPR8B8h.60lujTbeuDQU9KpoZxowfoViQmzI2NP1/rLdZ5JG', 'lord.pepe@email.com');

-- --------------------------------------------------------

--
-- Tabellstruktur `posts`
--

CREATE TABLE `posts` (
  `id` int(11) NOT NULL,
  `author_id` int(11) NOT NULL,
  `title` varchar(30) NOT NULL,
  `content` text NOT NULL,
  `category` tinyint(2) NOT NULL DEFAULT '0',
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumpning av Data i tabell `posts`
--

INSERT INTO `posts` (`id`, `author_id`, `title`, `content`, `category`, `time`) VALUES
(3, 1, 'When pepe sad', '4bSeji', 4, '2020-04-02 17:04:45'),
(4, 1, 'very sad pepe', 'sTYNao', 2, '2020-04-02 17:04:45'),
(5, 1, 'cat cörrect', '2rLRhG', 0, '2020-04-02 17:04:45'),
(6, 1, 'bzzzzzzzt car yes', 'EmRJCK', 2, '2020-04-02 17:06:34'),
(7, 1, 'Korna meymey', 'OrA379', 3, '2020-04-02 17:11:07');

--
-- Index för dumpade tabeller
--

--
-- Index för tabell `accounts`
--
ALTER TABLE `accounts`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Index för tabell `posts`
--
ALTER TABLE `posts`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT för dumpade tabeller
--

--
-- AUTO_INCREMENT för tabell `accounts`
--
ALTER TABLE `accounts`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT för tabell `posts`
--
ALTER TABLE `posts`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
