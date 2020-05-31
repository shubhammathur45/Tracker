-- phpMyAdmin SQL Dump
-- version 4.0.10.7
-- http://www.phpmyadmin.net
--
-- Host: localhost:3306
-- Generation Time: Feb 24, 2017 at 02:30 AM
-- Server version: 5.5.39-36.0-log
-- PHP Version: 5.4.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `emplyee_task`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE IF NOT EXISTS `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`id`, `name`, `email`, `password`) VALUES
(1, 'Admin', 'deepakbajaj79@gmail.com', '96e79218965eb72c92a549dd5a330112');

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE IF NOT EXISTS `employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `emp_id` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `phone` varchar(25) NOT NULL,
  `created_at` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=24 ;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`id`, `name`, `emp_id`, `email`, `password`, `phone`, `created_at`) VALUES
(16, 'deepak', '101', 'dk@gmail.com', '202cb962ac59075b964b07152d234b70', '9999999999', '2017-02-08 16:16:20'),
(22, 'Shubham', '110011', 'sm@gmail.com', '0f04b04b025522a3c702c947f357a9c9', '666444', '2017-02-15 00:27:24'),
(23, 'poonam', '1011', 'pm@gmail.com', '45226bae7da31547fbdca1c62d24a8dd', '6655443322', '2017-02-19 18:07:30');

-- --------------------------------------------------------

--
-- Table structure for table `employee_login_location`
--

CREATE TABLE IF NOT EXISTS `employee_login_location` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `emp_id` int(11) NOT NULL,
  `location` text NOT NULL,
  `image` varchar(255) NOT NULL,
  `login_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=55 ;

--
-- Dumping data for table `employee_login_location`
--

INSERT INTO `employee_login_location` (`id`, `emp_id`, `location`, `image`, `login_time`) VALUES
(1, 1, 'U-46/12, Galli No 46, U Block, DLF Phase 3, Sector 24, Gurugram, Haryana 122022, India', 'login_1484988519.jpeg', '2017-01-21 08:48:39'),
(2, 1, 'U-46/12, Galli No 46, U Block, DLF Phase 3, Sector 24, Gurugram, Haryana 122022, India', 'login_1485478010.jpeg', '2017-01-27 00:46:50'),
(3, 11, 'U-46/12, Galli No 46, U Block, DLF Phase 3, Sector 24, Gurugram, Haryana 122022, India', 'login_1485887364.jpeg', '2017-01-31 18:29:24'),
(4, 11, 'U-46/12, Galli No 46, U Block, DLF Phase 3, Sector 24, Gurugram, Haryana 122022, India', 'login_1485887530.jpeg', '2017-01-31 18:32:10'),
(5, 11, '255, Block VP, Poorvi Pitampura, Pitampura, Delhi, 110034, India', 'login_1485887833.9j', '2017-01-31 18:37:13'),
(6, 11, '255, Block VP, Poorvi Pitampura, Pitampura, Delhi, 110034, India', 'login_1486199062.9j', '2017-02-04 09:04:22'),
(7, 11, '255, Block VP, Poorvi Pitampura, Pitampura, Delhi, 110034, India', 'login_1486199229.9j', '2017-02-04 09:07:09'),
(8, 11, 'U-46/12, Galli No 46, U Block, DLF Phase 3, Sector 24, Gurugram, Haryana 122022, India', 'login_1486395628.jpeg', '2017-02-06 15:40:28'),
(9, 11, '255, Block VP, Poorvi Pitampura, Pitampura, Delhi, 110034, India', 'login_1486396530.9j', '2017-02-06 15:55:30'),
(10, 11, '297, Block VP, Poorvi Pitampura, Pitampura, Delhi, 110034, India', 'login_1486397182.9j', '2017-02-06 16:06:22'),
(11, 11, '255, Block VP, Poorvi Pitampura, Pitampura, Delhi, 110034, India', 'login_1486397581.9j', '2017-02-06 16:13:01'),
(12, 11, '255, Block VP, Poorvi Pitampura, Pitampura, Delhi, 110034, India', 'login_1486399469.9j', '2017-02-06 16:44:29'),
(13, 11, '256, Block VP, Poorvi Pitampura, Pitampura, Delhi, 110034, India', 'login_1486405203.9j', '2017-02-06 18:20:03'),
(14, 11, '257, Block VP, Poorvi Pitampura, Pitampura, Delhi, 110088, India', 'login_1486406006.9j', '2017-02-06 18:33:26'),
(15, 11, '260, Block VP, Poorvi Pitampura, Pitampura, Delhi, 110034, India', 'login_1486407622.jpg', '2017-02-06 19:00:22'),
(16, 12, 'Unnamed Road, Raja Bhoj Airport Area, Bairagarh, Bhopal, Madhya Pradesh 462030, India', 'login_1486526007.jpg', '2017-02-08 03:53:27'),
(17, 13, '128/815, Kidwainagar Ave, Kidwai Nagar, Kanpur, Uttar Pradesh 208011, India', 'login_1486533117.jpg', '2017-02-08 05:51:57'),
(18, 13, 'NH27, Yashoda Nagar, Kanpur, Uttar Pradesh 208013, India', 'login_1486533191.jpg', '2017-02-08 05:53:11'),
(19, 13, '128/815, Kidwainagar Ave, Kidwai Nagar, Kanpur, Uttar Pradesh 208011, India', 'login_1486533394.jpg', '2017-02-08 05:56:34'),
(20, 14, 'Unnamed Road, Raja Bhoj Airport Area, Bairagarh, Bhopal, Madhya Pradesh 462030, India', 'login_1486534070.jpg', '2017-02-08 06:07:50'),
(21, 15, 'Unnamed Road, Raja Bhoj Airport Area, Bairagarh, Bhopal, Madhya Pradesh 462030, India', 'login_1486535593.jpg', '2017-02-08 06:33:13'),
(22, 15, 'Unnamed Road, Raja Bhoj Airport Area, Bairagarh, Bhopal, Madhya Pradesh 462030, India', 'login_1486543324.jpg', '2017-02-08 08:42:04'),
(23, 15, '27/2, CTO Rd, Panchwati Colony, CTO Colony, Bairagarh, Bhopal, Madhya Pradesh 462030, India', 'login_1486570589.jpg', '2017-02-08 16:16:29'),
(24, 16, '27/2, CTO Rd, Panchwati Colony, CTO Colony, Bairagarh, Bhopal, Madhya Pradesh 462030, India', 'login_1486570707.jpg', '2017-02-08 16:18:27'),
(25, 16, 'Unnamed Road, Raja Bhoj Airport Area, Bairagarh, Bhopal, Madhya Pradesh 462030, India', 'login_1486571399.jpg', '2017-02-08 16:29:59'),
(26, 16, 'Unnamed Road, Raja Bhoj Airport Area, Bairagarh, Bhopal, Madhya Pradesh 462030, India', 'login_1486571409.jpg', '2017-02-08 16:30:09'),
(27, 17, '128/1083, Kidwainagar Ave, Y Block, Dhansinghpur, Kanpur, Uttar Pradesh 208011, India', 'login_1486572778.jpg', '2017-02-08 16:52:58'),
(28, 17, 'Unnamed Road, Raja Bhoj Airport Area, Bairagarh, Bhopal, Madhya Pradesh 462030, India', 'login_1486572957.jpg', '2017-02-08 16:55:57'),
(29, 18, 'Unnamed Road, Raja Bhoj Airport Area, Bairagarh, Bhopal, Madhya Pradesh 462030, India', 'login_1486573407.jpg', '2017-02-08 17:03:27'),
(30, 18, 'Unnamed Road, Raja Bhoj Airport Area, Bairagarh, Bhopal, Madhya Pradesh 462030, India', 'login_1486573424.jpg', '2017-02-08 17:03:44'),
(31, 18, 'Unnamed Road, Raja Bhoj Airport Area, Bairagarh, Bhopal, Madhya Pradesh 462030, India', 'login_1486573486.jpg', '2017-02-08 17:04:46'),
(32, 18, 'Unnamed Road, Raja Bhoj Airport Area, Bairagarh, Bhopal, Madhya Pradesh 462030, India', 'login_1486573495.jpg', '2017-02-08 17:04:55'),
(33, 16, '53, Block LP, Poorvi Pitampura, Pitampura, Delhi, 110034, India', 'login_1486577717.jpg', '2017-02-08 18:15:17'),
(34, 16, '222, Block VP, Poorvi Pitampura, Pitampura, Delhi, 110034, India', 'login_1486665395.jpg', '2017-02-09 18:36:35'),
(35, 16, '222, Block VP, Poorvi Pitampura, Pitampura, Delhi, 110034, India', 'login_1486665446.jpg', '2017-02-09 18:37:26'),
(36, 16, '222, Block VP, Poorvi Pitampura, Pitampura, Delhi, 110034, India', 'login_1486665454.jpg', '2017-02-09 18:37:34'),
(37, 16, '222, Block VP, Poorvi Pitampura, Pitampura, Delhi, 110034, India', 'login_1486665460.jpg', '2017-02-09 18:37:40'),
(38, 19, 'Unnamed Road, Raja Bhoj Airport Area, Bairagarh, Bhopal, Madhya Pradesh 462030, India', 'login_1486790048.jpg', '2017-02-11 05:14:08'),
(39, 19, 'Unnamed Road, Raja Bhoj Airport Area, Bairagarh, Bhopal, Madhya Pradesh 462030, India', 'login_1486904161.jpg', '2017-02-12 12:56:01'),
(40, 19, '27/2, CTO Rd, Panchwati Colony, CTO Colony, Bairagarh, Bhopal, Madhya Pradesh 462030, India', 'login_1486904427.jpg', '2017-02-12 13:00:27'),
(41, 19, 'Unnamed Road, Raja Bhoj Airport Area, Bairagarh, Bhopal, Madhya Pradesh 462030, India', 'login_1486916711.jpg', '2017-02-12 16:25:11'),
(42, 19, '27/2, CTO Rd, Panchwati Colony, CTO Colony, Bairagarh, Bhopal, Madhya Pradesh 462030, India', 'login_1486929623.jpg', '2017-02-12 20:00:23'),
(43, 19, 'Unnamed Road, Raja Bhoj Airport Area, Bairagarh, Bhopal, Madhya Pradesh 462030, India', 'login_1486930109.jpg', '2017-02-12 20:08:29'),
(44, 16, '255, Block VP, Poorvi Pitampura, Pitampura, Delhi, 110034, India', 'login_1486997866.jpg', '2017-02-13 14:57:46'),
(45, 19, 'Unnamed Road, Raja Bhoj Airport Area, Bairagarh, Bhopal, Madhya Pradesh 462030, India', 'login_1487097614.jpg', '2017-02-14 18:40:14'),
(46, 19, 'Unnamed Road, Raja Bhoj Airport Area, Bairagarh, Bhopal, Madhya Pradesh 462030, India', 'login_1487098029.jpg', '2017-02-14 18:47:09'),
(47, 21, '128/815, Kidwainagar Ave, Kidwai Nagar, Kanpur, Uttar Pradesh 208011, India', 'login_1487098461.jpg', '2017-02-14 18:54:21'),
(48, 21, '128/815, Kidwainagar Ave, Kidwai Nagar, Kanpur, Uttar Pradesh 208011, India', 'login_1487098636.jpg', '2017-02-14 18:57:16'),
(49, 22, 'Unnamed Road, Raja Bhoj Airport Area, Bairagarh, Bhopal, Madhya Pradesh 462030, India', 'login_1487098729.jpg', '2017-02-14 18:58:49'),
(50, 22, 'Unnamed Road, Raja Bhoj Airport Area, Bairagarh, Bhopal, Madhya Pradesh 462030, India', 'login_1487269702.jpg', '2017-02-16 18:28:22'),
(51, 22, 'Unnamed Road, Raja Bhoj Airport Area, Bairagarh, Bhopal, Madhya Pradesh 462030, India', 'login_1487269853.jpg', '2017-02-16 18:30:53'),
(52, 22, 'Unnamed Road, Raja Bhoj Airport Area, Bairagarh, Bhopal, Madhya Pradesh 462030, India', 'login_1487495276.jpg', '2017-02-19 09:07:56'),
(53, 23, 'Unnamed Road, Raja Bhoj Airport Area, Bairagarh, Bhopal, Madhya Pradesh 462030, India', 'login_1487508292.jpg', '2017-02-19 12:44:52'),
(54, 23, 'Alark Shopping Mall, LG-5, Airport Rd, Lalghati Chouraha, Nayapura, Lalghati, Bhopal, Madhya Pradesh 462001, India', 'login_1487509257.jpg', '2017-02-19 13:00:57');

-- --------------------------------------------------------

--
-- Table structure for table `employee_task`
--

CREATE TABLE IF NOT EXISTS `employee_task` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `task` varchar(255) NOT NULL,
  `task_location` varchar(255) NOT NULL,
  `emp_id` int(11) NOT NULL,
  `status` enum('0','1') NOT NULL DEFAULT '0',
  `last_date` date NOT NULL,
  `created_at` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=28 ;

--
-- Dumping data for table `employee_task`
--

INSERT INTO `employee_task` (`id`, `task`, `task_location`, `emp_id`, `status`, `last_date`, `created_at`) VALUES
(3, 'Task3', 'Gurgaon', 1, '0', '2016-12-30', '2017-01-05 16:35:09'),
(5, 'Delivery', 'Delhi', 7, '0', '0000-00-00', '2017-01-07 05:32:41'),
(10, 'pickup', 'delhi', 4, '0', '0000-00-00', '2017-01-18 07:52:04'),
(13, 'fgvbbbn', 'delbi', 11, '0', '2017-02-14', '2017-02-06 18:58:27'),
(14, 'DELIVER', 'mayur vihar', 12, '0', '2017-02-16', '2017-02-08 05:12:03'),
(16, 'pickup', 'delhi', 15, '1', '2017-02-18', '2017-02-08 06:35:08'),
(17, 'testing', 'delhi', 16, '1', '2017-02-21', '2017-02-08 16:17:01'),
(19, 'shush delivery', 'kanpur', 17, '1', '2017-02-24', '2017-02-08 16:51:30'),
(22, 'pickup', 'gurgaon', 19, '1', '2017-02-24', '2017-02-12 12:59:48'),
(25, '10111', 'harinagar', 22, '1', '2017-02-23', '2017-02-14 18:58:06'),
(27, '1033', 'bhopal', 23, '0', '2017-02-28', '2017-02-19 12:38:58');

-- --------------------------------------------------------

--
-- Table structure for table `resetpasswordtoken`
--

CREATE TABLE IF NOT EXISTS `resetpasswordtoken` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `token` varchar(255) NOT NULL,
  `user_id` int(11) NOT NULL,
  `type` enum('admin','emp') NOT NULL,
  `created_at` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=39 ;

-- --------------------------------------------------------

--
-- Table structure for table `track_employee_location`
--

CREATE TABLE IF NOT EXISTS `track_employee_location` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `emp_id` int(11) NOT NULL,
  `location` text NOT NULL,
  `latitude` varchar(255) NOT NULL,
  `longitude` varchar(255) NOT NULL,
  `tracking_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=31 ;

--
-- Dumping data for table `track_employee_location`
--

INSERT INTO `track_employee_location` (`id`, `emp_id`, `location`, `latitude`, `longitude`, `tracking_time`) VALUES
(1, 1, 'U-46/12, Galli No 46, U Block, DLF Phase 3, Sector 24, Gurugram, Haryana 122022, India', '', '', '2017-01-21 08:50:56'),
(2, 1, 'U-46/12, Galli No 46, U Block, DLF Phase 3, Sector 24, Gurugram, Haryana 122022, India', '', '', '2017-01-21 08:51:16'),
(3, 1, 'U-46/12, Galli No 46, U Block, DLF Phase 3, Sector 24, Gurugram, Haryana 122022, India', '', '', '2017-01-25 00:41:14'),
(4, 1, 'U-46/12, Galli No 46, U Block, DLF Phase 3, Sector 24, Gurugram, Haryana 122022, India', '', '', '2017-01-25 00:41:26'),
(5, 1, 'U-46/12, Galli No 46, U Block, DLF Phase 3, Sector 24, Gurugram, Haryana 122022, India', '28.4917', '77.0949', '2017-01-27 00:59:43'),
(6, 11, 'U-46/12, Galli No 46, U Block, DLF Phase 3, Sector 24, Gurugram, Haryana 122022, India', '28.4917', '77.0949', '2017-02-06 17:13:59'),
(7, 11, 'U-46/12, Galli No 46, U Block, DLF Phase 3, Sector 24, Gurugram, Haryana 122022, India', '28.4917', '77.0949', '2017-02-06 17:14:11'),
(8, 11, 'U-46/12, Galli No 46, U Block, DLF Phase 3, Sector 24, Gurugram, Haryana 122022, India', '28.4917', '77.0949', '2017-02-06 17:14:14'),
(9, 16, '222, Block VP, Poorvi Pitampura, Pitampura, Delhi, 110034, India', '28.7060287', '77.1472367', '2017-02-09 18:36:38'),
(10, 16, '222, Block VP, Poorvi Pitampura, Pitampura, Delhi, 110034, India', '28.7060285', '77.1472348', '2017-02-09 18:37:27'),
(11, 16, '222, Block VP, Poorvi Pitampura, Pitampura, Delhi, 110034, India', '28.7060285', '77.1472348', '2017-02-09 18:37:35'),
(12, 16, '222, Block VP, Poorvi Pitampura, Pitampura, Delhi, 110034, India', '28.7060285', '77.1472348', '2017-02-09 18:37:41'),
(13, 19, 'Unnamed Road, Raja Bhoj Airport Area, Bairagarh, Bhopal, Madhya Pradesh 462030, India', '23.2845713', '77.3439605', '2017-02-11 05:14:09'),
(14, 19, 'Unnamed Road, Raja Bhoj Airport Area, Bairagarh, Bhopal, Madhya Pradesh 462030, India', '23.2845713', '77.3439605', '2017-02-12 12:56:02'),
(15, 19, 'Unnamed Road, Raja Bhoj Airport Area, Bairagarh, Bhopal, Madhya Pradesh 462030, India', '23.2845713', '77.3439605', '2017-02-12 13:00:29'),
(16, 19, 'Unnamed Road, Raja Bhoj Airport Area, Bairagarh, Bhopal, Madhya Pradesh 462030, India', '23.2845713', '77.3439605', '2017-02-12 16:25:13'),
(17, 19, '27/2, CTO Rd, Panchwati Colony, CTO Colony, Bairagarh, Bhopal, Madhya Pradesh 462030, India', '23.2798213', '77.3480557', '2017-02-12 20:00:25'),
(18, 19, 'Unnamed Road, Raja Bhoj Airport Area, Bairagarh, Bhopal, Madhya Pradesh 462030, India', '23.2845713', '77.3439605', '2017-02-12 20:08:30'),
(19, 16, '255, Block VP, Poorvi Pitampura, Pitampura, Delhi, 110034, India', '28.7058948', '77.1471232', '2017-02-13 14:57:47'),
(20, 19, 'Unnamed Road, Raja Bhoj Airport Area, Bairagarh, Bhopal, Madhya Pradesh 462030, India', '23.2845713', '77.3439605', '2017-02-14 18:40:16'),
(21, 19, 'Unnamed Road, Raja Bhoj Airport Area, Bairagarh, Bhopal, Madhya Pradesh 462030, India', '23.2845713', '77.3439605', '2017-02-14 18:47:10'),
(22, 21, '128/815, Kidwainagar Ave, Kidwai Nagar, Kanpur, Uttar Pradesh 208011, India', '26.4188248', '80.3308848', '2017-02-14 18:54:22'),
(23, 21, '128/815, Kidwainagar Ave, Kidwai Nagar, Kanpur, Uttar Pradesh 208011, India', '26.4189193', '80.3322773', '2017-02-14 18:57:17'),
(24, 22, 'Unnamed Road, Raja Bhoj Airport Area, Bairagarh, Bhopal, Madhya Pradesh 462030, India', '23.2845713', '77.3439605', '2017-02-14 18:58:50'),
(25, 22, 'Unnamed Road, Raja Bhoj Airport Area, Bairagarh, Bhopal, Madhya Pradesh 462030, India', '23.2845687', '77.3439633', '2017-02-16 18:28:24'),
(26, 22, 'Unnamed Road, Raja Bhoj Airport Area, Bairagarh, Bhopal, Madhya Pradesh 462030, India', '23.2845687', '77.3439633', '2017-02-16 18:30:54'),
(27, 22, 'Unnamed Road, Raja Bhoj Airport Area, Bairagarh, Bhopal, Madhya Pradesh 462030, India', '23.2845687', '77.3439633', '2017-02-19 09:07:57'),
(28, 23, 'Unnamed Road, Raja Bhoj Airport Area, Bairagarh, Bhopal, Madhya Pradesh 462030, India', '23.2845687', '77.3439633', '2017-02-19 12:44:53'),
(29, 23, '132, CTO Rd, Ayyappa Temple, CTO Colony, Bairagarh, Bhopal, Madhya Pradesh 462030, India', '23.276618', '77.3401596', '2017-02-19 12:47:51'),
(30, 23, 'Alark Shopping Mall, LG-5, Airport Rd, Lalghati Chouraha, Nayapura, Lalghati, Bhopal, Madhya Pradesh 462001, India', '23.2744525', '77.3702606', '2017-02-19 13:00:59');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
