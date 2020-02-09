-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 09, 2020 at 07:46 AM
-- Server version: 10.1.40-MariaDB
-- PHP Version: 7.3.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `interstellar`
--
CREATE DATABASE IF NOT EXISTS `interstellar` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `interstellar`;

-- --------------------------------------------------------

--
-- Table structure for table `comments`
--

CREATE TABLE `comments` (
  `id` int(255) NOT NULL,
  `Content` varchar(255) NOT NULL,
  `user_id` int(255) NOT NULL,
  `post_id` int(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `comments`
--

INSERT INTO `comments` (`id`, `Content`, `user_id`, `post_id`) VALUES
(2, 'I commented on it.', 4, 12),
(3, 'i do not like it', 4, 11),
(4, 'I commented on it.', 16, 11),
(5, 'I will comment on it', 6, 15),
(6, 'Final test.', 16, 16),
(7, 'I commented on it.', 16, 17);

-- --------------------------------------------------------

--
-- Table structure for table `contributions`
--

CREATE TABLE `contributions` (
  `id` int(11) NOT NULL,
  `provider_id` int(11) NOT NULL,
  `Amount` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `follows`
--

CREATE TABLE `follows` (
  `id` int(255) NOT NULL,
  `friend_id` int(255) NOT NULL,
  `follower_id` int(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `follows`
--

INSERT INTO `follows` (`id`, `friend_id`, `follower_id`) VALUES
(16, 5, 4);

-- --------------------------------------------------------

--
-- Table structure for table `fund_providers`
--

CREATE TABLE `fund_providers` (
  `id` int(255) NOT NULL,
  `Name` varchar(255) NOT NULL,
  `Email` varchar(255) NOT NULL,
  `Password` varchar(255) NOT NULL,
  `ImageSource` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `fund_providers`
--

INSERT INTO `fund_providers` (`id`, `Name`, `Email`, `Password`, `ImageSource`) VALUES
(1, 'Justice League', 'justiceleague@gmail.com', 'd8578edf8458ce06fbc5bb76a58c5ca4', 'user_image/DSC00391.JPG'),
(2, 'Siddhartha Addy', 'sbdk12@gmail.com', '81dc9bdb52d04dc20036dbd8313ed055', 'user_image/DSC00341.JPG');

-- --------------------------------------------------------

--
-- Table structure for table `likes`
--

CREATE TABLE `likes` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `post_id` int(11) NOT NULL,
  `type` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `likes`
--

INSERT INTO `likes` (`id`, `user_id`, `post_id`, `type`) VALUES
(2, 16, 12, 0),
(7, 5, 12, 0),
(8, 6, 12, 0),
(9, 10, 12, 0),
(10, 14, 12, 0),
(13, 4, 10, 0),
(15, 4, 13, 0),
(59, 4, 4, 0),
(63, 4, 0, 0),
(64, 16, 14, 0),
(67, 10, 15, 0),
(68, 5, 15, 0),
(69, 4, 15, 0),
(73, 16, 11, 0),
(75, 4, 17, 0),
(76, 4, 16, 0),
(78, 16, 17, 0);

-- --------------------------------------------------------

--
-- Table structure for table `posts`
--

CREATE TABLE `posts` (
  `id` int(255) NOT NULL,
  `Name` varchar(255) NOT NULL,
  `user_id` int(255) NOT NULL,
  `Content` longtext NOT NULL,
  `like` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `posts`
--

INSERT INTO `posts` (`id`, `Name`, `user_id`, `Content`, `like`) VALUES
(11, 'Posting Test', 16, 'Posting Test', 0),
(12, 'Another test', 10, 'Here is the content of the post.', 0),
(13, 'Last post', 4, 'The content is not important.', 0),
(14, 'Latest Post', 16, 'Here is the content of the post.', 0),
(15, 'Short term memory loss', 17, 'The film Memento is directed by Christopher Nolan.', 0),
(16, 'Another test', 4, 'It is going high and high in India.', 0),
(17, 'Another test 2', 16, 'Here is the content of the post.', 0);

-- --------------------------------------------------------

--
-- Table structure for table `requests`
--

CREATE TABLE `requests` (
  `id` int(255) NOT NULL,
  `Cause` varchar(255) NOT NULL,
  `user_id` int(255) NOT NULL,
  `Amount` int(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(255) NOT NULL,
  `Name` varchar(255) NOT NULL,
  `Email` varchar(255) NOT NULL,
  `Password` varchar(255) NOT NULL,
  `ImageSource` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `Name`, `Email`, `Password`, `ImageSource`) VALUES
(16, 'Soumyadev Sanyal', 'soumya.sp35@gmail.com', '5d41402abc4b2a76b9719d911017c592', ''),
(20, 'Siddhartha Addy', 'sbdk12@gmail.com', '8546bc8950acc8c382623e7a1fcf4b8b', 'user_image/DSC00391.JPG'),
(23, 'Provider_1', 'provider_1@gmail.com', 'cdb82d56473901641525fbbd1d5dab56', 'user_image/DSC00391.JPG'),
(24, 'Sujan Kumar Mitra', 'mitrakumarsujan@gmail.com', '81dc9bdb52d04dc20036dbd8313ed055', 'user_image/DSC00391.JPG');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `comments`
--
ALTER TABLE `comments`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `contributions`
--
ALTER TABLE `contributions`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `follows`
--
ALTER TABLE `follows`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `fund_providers`
--
ALTER TABLE `fund_providers`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `Name` (`Name`,`Email`);

--
-- Indexes for table `likes`
--
ALTER TABLE `likes`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `posts`
--
ALTER TABLE `posts`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `requests`
--
ALTER TABLE `requests`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `Email` (`Email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `comments`
--
ALTER TABLE `comments`
  MODIFY `id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `contributions`
--
ALTER TABLE `contributions`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `follows`
--
ALTER TABLE `follows`
  MODIFY `id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `fund_providers`
--
ALTER TABLE `fund_providers`
  MODIFY `id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `likes`
--
ALTER TABLE `likes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=79;

--
-- AUTO_INCREMENT for table `posts`
--
ALTER TABLE `posts`
  MODIFY `id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `requests`
--
ALTER TABLE `requests`
  MODIFY `id` int(255) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
