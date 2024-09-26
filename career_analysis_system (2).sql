-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sep 26, 2024 at 01:56 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `career_analysis_system`
--

-- --------------------------------------------------------

--
-- Table structure for table `careers`
--

CREATE TABLE `careers` (
  `id` int(20) NOT NULL,
  `name` varchar(30) NOT NULL,
  `description` text NOT NULL,
  `required_skills` text NOT NULL,
  `educational_requirements` text NOT NULL,
  `industry_insights` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `job_listings1`
--

CREATE TABLE `job_listings1` (
  `id` int(11) NOT NULL,
  `rec_id` int(30) DEFAULT NULL,
  `company_name` varchar(100) DEFAULT NULL,
  `name` varchar(30) DEFAULT NULL,
  `description` text DEFAULT NULL,
  `educational_requirements` text DEFAULT NULL,
  `industry_insights` text DEFAULT NULL,
  `is_active` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `job_listings1`
--

INSERT INTO `job_listings1` (`id`, `rec_id`, `company_name`, `name`, `description`, `educational_requirements`, `industry_insights`, `is_active`) VALUES
(3, 1, 'JP Morgan', 'dsvg tr tr e f erwg', 'th t gre fr', 'gtr g trg tr', 'gt gtr gtr gtr', 0),
(4, 1, 'JP Morgan', 'f erf ger g eruyiyk', 'DGREGRTHTR HTR H TR', 'RGREGTHT G R', 'trhtrhtrhtrhtr h trh', 0),
(5, 1, 'JP Morgan', 'efew fe g erg', 'g erg erg er', 'ger g erg er', 'er ger g ', 0),
(6, 1, 'JP Morgan', 'SDE', 'g erg rg trh tr', 'htyh tyh tyh t', 'ththh tyh th ', 1),
(7, 2, 'Amazon', 'Sde', 'dsfer gh gtr', 'g re ', 'g trgh tr g', 1),
(8, 1, 'JP Morgan', 'jij dfesf', 'efewv eve  es dbgf gf ', 'brb  btt ', 'wdwdw', 1),
(9, 1, 'JP Morgan', 'vrv ', 'nyuyu', 'hy6h', 'h6', 0),
(10, 1, 'JP Morgan', 'q', 'q', 'q', 'q', 0),
(11, 2, 'Amazon', 'sde-2', 'dveeg er ger g erg eg', ' myuyu  tyh trg rf', 'wa dewg trh tyj yum ', 1),
(12, 1, 'JP Morgan', 'A.I', 'erg jyu k il   few ', ' rey t kiu k gd r', 'efergjyujyjty hrew f eg trhtyj', 0),
(13, 2, 'Amazon', 'sde-3', 'rb t bty ty ', 'erg', 'f ber  rt', 0);

-- --------------------------------------------------------

--
-- Table structure for table `job_skills1`
--

CREATE TABLE `job_skills1` (
  `id` int(11) NOT NULL,
  `job_id` int(11) DEFAULT NULL,
  `skill_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `job_skills1`
--

INSERT INTO `job_skills1` (`id`, `job_id`, `skill_id`) VALUES
(1, 3, 28),
(2, 3, 29),
(3, 3, 30),
(4, 4, 7),
(5, 4, 8),
(6, 5, 7),
(7, 5, 7),
(8, 6, 20),
(9, 6, 26),
(10, 6, 27),
(11, 7, 15),
(12, 7, 16),
(13, 7, 17),
(14, 7, 20),
(15, 8, 7),
(16, 8, 8),
(17, 9, 9),
(18, 9, 10),
(19, 10, 7),
(20, 10, 8),
(22, 11, 7),
(23, 11, 12),
(24, 11, 14),
(25, 13, 7),
(26, 13, 8),
(27, 13, 9);

-- --------------------------------------------------------

--
-- Table structure for table `personalityresultlog`
--

CREATE TABLE `personalityresultlog` (
  `id` int(20) NOT NULL,
  `user_id` int(20) NOT NULL,
  `date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `personality_traits` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `personalityresultlog`
--

INSERT INTO `personalityresultlog` (`id`, `user_id`, `date`, `personality_traits`) VALUES
(11, 1, '2024-07-23 11:10:33', 'Innovation'),
(12, 1, '2024-07-23 11:12:51', 'Analytical Thinking'),
(13, 1, '2024-07-23 11:15:10', 'Analytical Thinking'),
(14, 2, '2024-07-23 11:16:49', 'Innovation'),
(15, 3, '2024-07-23 17:07:16', 'Problem Solving'),
(16, 4, '2024-07-24 16:38:48', 'Innovation'),
(17, 5, '2024-07-24 16:46:16', 'Attention to Detail'),
(18, 6, '2024-08-17 07:26:09', 'Problem Solving'),
(19, 7, '2024-08-17 07:36:19', 'Innovation'),
(20, 8, '2024-08-17 08:03:05', 'Teamwork'),
(21, 0, '2024-08-23 20:27:36', 'Innovation'),
(22, 9, '2024-08-23 20:31:42', 'Innovation'),
(23, 10, '2024-08-23 20:49:02', 'Attention to Detail'),
(24, 11, '2024-08-23 20:55:16', 'Persistence'),
(25, 1, '2024-08-29 18:54:50', 'Teamwork');

-- --------------------------------------------------------

--
-- Table structure for table `recruiters`
--

CREATE TABLE `recruiters` (
  `id` int(20) NOT NULL,
  `name` varchar(30) NOT NULL,
  `location` varchar(30) NOT NULL,
  `email` varchar(30) NOT NULL,
  `password` varchar(260) NOT NULL,
  `phonenumber` bigint(20) NOT NULL,
  `companyname` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `recruiters`
--

INSERT INTO `recruiters` (`id`, `name`, `location`, `email`, `password`, `phonenumber`, `companyname`) VALUES
(1, 'swarna', 'banglore', 'somya@gmail.com', 'somya811', 7890123456, 'JP Morgan'),
(2, 'shivam shukla', 'sef', 'shiv@gmail.com', '0d51c4608e68b7e9da62b2233714374cfe14118d2fbfd4de109eb84c0230a74a', 7890123456, 'Amazon');

-- --------------------------------------------------------

--
-- Table structure for table `skillresultlog`
--

CREATE TABLE `skillresultlog` (
  `id` int(20) NOT NULL,
  `user_id` int(20) NOT NULL,
  `date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `programming_skills_out_of_5` int(20) NOT NULL,
  `systems_and_networking_out_of_5` int(20) NOT NULL,
  `software_development_out_of_5` int(20) NOT NULL,
  `hardware_and_embedded_systems_out_of_5` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `skillresultlog`
--

INSERT INTO `skillresultlog` (`id`, `user_id`, `date`, `programming_skills_out_of_5`, `systems_and_networking_out_of_5`, `software_development_out_of_5`, `hardware_and_embedded_systems_out_of_5`) VALUES
(5, 2, '2024-07-23 11:17:50', 1, 2, 2, 1),
(6, 3, '2024-07-23 17:15:24', 0, 0, 0, 0),
(7, 1, '2024-08-29 18:53:05', 1, 2, 3, 1);

-- --------------------------------------------------------

--
-- Table structure for table `skills`
--

CREATE TABLE `skills` (
  `id` int(11) NOT NULL,
  `skill_name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `skills`
--

INSERT INTO `skills` (`id`, `skill_name`) VALUES
(7, 'Problem Solving'),
(8, 'Critical Thinking'),
(9, 'Communication'),
(10, 'Teamwork'),
(11, 'Time Management'),
(12, 'Project Management'),
(13, 'Creativity'),
(14, 'Adaptability'),
(15, 'C'),
(16, 'C++'),
(17, 'Java'),
(18, 'Python'),
(19, 'JavaScript'),
(20, 'Data Structures and Algorithms'),
(21, 'Software Development'),
(22, 'SQL'),
(23, 'MySQL'),
(24, 'PostgreSQL'),
(25, 'Google Cloud'),
(26, 'HTML'),
(27, 'CSS'),
(28, 'React'),
(29, 'Angular'),
(30, 'Git'),
(31, 'GitHub');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(20) NOT NULL,
  `name` varchar(30) NOT NULL,
  `email` varchar(30) NOT NULL,
  `phonenumber` bigint(20) NOT NULL,
  `password` varchar(260) NOT NULL,
  `location` varchar(30) NOT NULL,
  `education` text NOT NULL,
  `experience` varchar(40) NOT NULL,
  `personality_traits` text NOT NULL,
  `resume` longtext NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `name`, `email`, `phonenumber`, `password`, `location`, `education`, `experience`, `personality_traits`, `resume`) VALUES
(1, 'shivam sss', 'shiv@gmail.com', 7572908034, '0d51c4608e68b7e9da62b2233714374cfe14118d2fbfd4de109eb84c0230a74a', 'surat', 'jjj', 'sfew fewfewf ew', 'Teamwork', ''),
(2, 'krunal ahir', 'krunal@gmail.com', 7874228036, '0d51c4608e68b7e9da62b2233714374cfe14118d2fbfd4de109eb84c0230a74a', 'surat', 'g Re', 'es sgfewgf ew', 'Innovation', '========================================== Resume =======================================\r\n\r\n------------------------------------------ Personal Details ------------------------------------\r\n\r\nName: krunal ahir\r\n\r\nLocation: surat\r\n\r\nEmail: krunal@gmail.com\r\n\r\nContact Number: 7874228036\r\n\r\n-------------------------------------------- Career Summary ------------------------------------\r\n\r\ndhtrh tyh ty hty j ty\r\n\r\n------------------------------------------- Educational Details ---------------------------------\r\n\r\ng Re\r\n\r\n-------------------------------------------- Experience --------------------------------------\r\n\r\nes sgfewgf ew\r\n\r\n---------------------------------------------- Skills -------------------------------------\r\n\r\n1. Java\r\n2. Python\r\n3. JavaScript\r\n'),
(3, 'jayesh prajapati', 'champak@gmail.com', 7123456890, 'jayesh811', 'teredre', 'dfewfcs', 'ewfer', 'Problem Solving', ''),
(4, 'leonel messi', 'messi@gmail.com', 7890123456, 'mesii811', 'qwf', 'wqqf', 'qwf', 'Innovation', ''),
(5, 'shivam shukla', 'shivam811@gmail.com', 8901234567, '0d51c4608e68b7e9da62b2233714374cfe14118d2fbfd4de109eb84c0230a74a', 'erg', 'jdrt', 'dt', 'Attention to Detail', ''),
(6, 's', 'm@gmail.com', 7890123456, '0d51c4608e68b7e9da62b2233714374cfe14118d2fbfd4de109eb84c0230a74a', 'fgre', 'erg ', 'rg', 'Problem Solving', ''),
(7, 'p', 'm1@gmail.com', 7890123456, 'shivam811', 't', 'tutr', '5y', 'Innovation', ''),
(8, 'hntynty jny', 'k@gmail.com', 7890123456, 'jakas811', 'h trg tr ', 'gthth th ty ', 'g g 5g 5', 'Teamwork', '========================================== Resume =======================================\r\n\r\n------------------------------------------ Personal Details ------------------------------------\r\n\r\nName: krunal ahir\r\n\r\nLocation: surat\r\n\r\nEmail: krunal@gmail.com\r\n\r\nContact Number: 7874228036\r\n\r\n-------------------------------------------- Career Summary ------------------------------------\r\n\r\ndhtrh tyh ty hty j ty\r\n\r\n------------------------------------------- Educational Details ---------------------------------\r\n\r\ng Re\r\n\r\n-------------------------------------------- Experience --------------------------------------\r\n\r\nes sgfewgf ew\r\n\r\n---------------------------------------------- Skills -------------------------------------\r\n\r\n1. Java\r\n2. Python\r\n3. JavaScript\r\n'),
(9, 'yash p', 'yash@gmail.com', 7890123456, 'shivam811', ' e rf', 'sfes fe ', 'e fer ', 'Innovation', ''),
(10, 'train esf e', 'train@gmail.com', 7890123456, 'shivam811', ' h tr', 'g er gre g', ' erg h ty', 'Attention to Detail', ''),
(11, 'few fe ', 'aa@gmail.com', 7890123456, 'shivam811', ' th t', ' er gtr ', ' bt h ', 'Persistence', '');

-- --------------------------------------------------------

--
-- Table structure for table `user_jobs1`
--

CREATE TABLE `user_jobs1` (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `job_id` int(11) DEFAULT NULL,
  `hired` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user_jobs1`
--

INSERT INTO `user_jobs1` (`id`, `user_id`, `job_id`, `hired`) VALUES
(1, 2, 7, 0),
(2, 4, 7, 0),
(3, 4, 6, 0),
(4, 4, 4, 0),
(5, 4, 9, 0),
(6, 4, 10, 0),
(7, 2, 6, 0),
(8, 1, 8, 1),
(10, 1, 6, 1),
(11, 1, 7, 1),
(12, 11, 7, 0),
(13, 1, 10, 0),
(14, 6, 11, 1);

-- --------------------------------------------------------

--
-- Table structure for table `user_resume`
--

CREATE TABLE `user_resume` (
  `resume_id` bigint(20) NOT NULL,
  `user_id` int(20) DEFAULT NULL,
  `resume_name` varchar(30) NOT NULL,
  `resume_text` longtext NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user_resume`
--

INSERT INTO `user_resume` (`resume_id`, `user_id`, `resume_name`, `resume_text`) VALUES
(2, 2, 'kresume', '========================================== Resume =======================================\r\n\r\n------------------------------------------ Personal Details ------------------------------------\r\n\r\nName: krunal ahir\r\n\r\nLocation: surat\r\n\r\nEmail: krunal@gmail.com\r\n\r\nContact Number: 7874228036\r\n\r\n-------------------------------------------- Career Summary ------------------------------------\r\n\r\nrgrtg trg er ger gtr gtr \r\n\r\n------------------------------------------- Educational Details ---------------------------------\r\n\r\nef esfer er\r\n\r\n-------------------------------------------- Experience --------------------------------------\r\n\r\nes sgfewgf ew\r\n\r\n---------------------------------------------- Skills -------------------------------------\r\n\r\n1. Java\r\n2. Python\r\n3. JavaScript\r\n'),
(3, 2, 'vvv', '========================================== Resume =======================================\r\n\r\n------------------------------------------ Personal Details ------------------------------------\r\n\r\nName: shivam shukla\r\n\r\nLocation: ahmedabad\r\n\r\nEmail: shivam@gmail.com\r\n\r\nContact Number: 7572908056\r\n\r\n-------------------------------------------- Career Summary ------------------------------------\r\n\r\nfewfew few f ewf ew fwe few \r\n\r\n------------------------------------------- Educational Details ---------------------------------\r\n\r\neeeeee	ew fwef\r\n\r\n-------------------------------------------- Experience --------------------------------------\r\n\r\nefewf ewf\r\n\r\n---------------------------------------------- Skills -------------------------------------========================================== Resume =======================================\r\n\r\n------------------------------------------ Personal Details ------------------------------------\r\n\r\nName: ege\r\n\r\nLocation: erg\r\n\r\nEmail: reger\r\n\r\nContact Number: 2433543543\r\n\r\n-------------------------------------------- Career Summary ------------------------------------\r\n\r\nrger gre retert\r\n\r\n------------------------------------------- Educational Details ---------------------------------\r\n\r\ngergt4\r\n\r\n-------------------------------------------- Experience --------------------------------------\r\n\r\nrgreg\r\n\r\n---------------------------------------------- Skills -------------------------------------========================================== Resume =======================================\r\n\r\n------------------------------------------ Personal Details ------------------------------------\r\n\r\nName: ferg\r\n\r\nLocation: regre\r\n\r\nEmail: greg\r\n\r\nContact Number: dfgerg\r\n\r\n-------------------------------------------- Career Summary ------------------------------------\r\n\r\nregerg erg erg r\r\n\r\n------------------------------------------- Educational Details ---------------------------------\r\n\r\nerger\r\n\r\n-------------------------------------------- Experience --------------------------------------\r\n\r\ngergreg\r\n\r\n---------------------------------------------- Skills -------------------------------------========================================== Resume =======================================\r\n\r\n------------------------------------------ Personal Details ------------------------------------\r\n\r\nName: efwe fewf\r\n\r\nLocation: fewf\r\n\r\nEmail: efe\r\n\r\nContact Number: fe\r\n\r\n-------------------------------------------- Career Summary ------------------------------------\r\n\r\nefew\r\n\r\n------------------------------------------- Educational Details ---------------------------------\r\n\r\nefe\r\n\r\n-------------------------------------------- Experience --------------------------------------\r\n\r\nfef\r\n\r\n---------------------------------------------- Skills -------------------------------------========================================== Resume =======================================\r\n\r\n------------------------------------------ Personal Details ------------------------------------\r\n\r\nName: uyki\r\n\r\nLocation: uyk\r\n\r\nEmail: uyj\r\n\r\nContact Number: yjy\r\n\r\n-------------------------------------------- Career Summary ------------------------------------\r\n\r\njyuj\r\n\r\n------------------------------------------- Educational Details ---------------------------------\r\n\r\njyuj\r\n\r\n-------------------------------------------- Experience --------------------------------------\r\n\r\nyuj\r\n\r\n---------------------------------------------- Skills -------------------------------------========================================== Resume =======================================\r\n\r\n------------------------------------------ Personal Details ------------------------------------\r\n\r\nName: jty\r\n\r\nLocation: ergrh\r\n\r\nEmail: trh\r\n\r\nContact Number: tyhhr\r\n\r\n-------------------------------------------- Career Summary ------------------------------------\r\n\r\ntwqw\r\n\r\n------------------------------------------- Educational Details ---------------------------------\r\n\r\ntyj\r\n\r\n-------------------------------------------- Experience --------------------------------------\r\n\r\newq\r\n\r\n---------------------------------------------- Skills -------------------------------------\r\n\r\n1. Problem Solving\r\n2. Critical Thinking\r\n3. Communication\r\n'),
(4, 2, 'ccc', '========================================== Resume =======================================\r\n\r\n------------------------------------------ Personal Details ------------------------------------\r\n\r\nName: shivam shukla\r\n\r\nLocation: fdbg\r\n\r\nEmail: btrbt\r\n\r\nContact Number: rtb\r\n\r\n-------------------------------------------- Career Summary ------------------------------------\r\n\r\nrgre\r\n\r\n------------------------------------------- Educational Details ---------------------------------\r\n\r\nrger\r\n\r\n-------------------------------------------- Experience --------------------------------------\r\n\r\nbt\r\n\r\n---------------------------------------------- Skills -------------------------------------\r\n\r\n1. Problem Solving\r\n2. Critical Thinking\r\n3. Communication\r\n'),
(5, 2, 'somya resume', '========================================== Resume =======================================\r\n\r\n------------------------------------------ Personal Details ------------------------------------\r\n\r\nName: krunal ahir\r\n\r\nLocation: surat\r\n\r\nEmail: krunal@gmail.com\r\n\r\nContact Number: 7874228036\r\n\r\n-------------------------------------------- Career Summary ------------------------------------\r\n\r\nsdverg er ger ger ger g erg erg erg erg erg erg erg erer  ertr h t tyh tyj tyj tyj yh t e aew fe wq r ht jy ju k r g e\r\n\r\n------------------------------------------- Educational Details ---------------------------------\r\n\r\nef esfer er\r\n\r\n-------------------------------------------- Experience --------------------------------------\r\n\r\nes sgfewgf ew\r\n\r\n---------------------------------------------- Skills -------------------------------------\r\n\r\n1. Java\r\n2. Python\r\n3. JavaScript\r\n'),
(6, 4, 'messiresume', '========================================== Resume =======================================\r\n\r\n------------------------------------------ Personal Details ------------------------------------\r\n\r\nName: leonel messi\r\n\r\nLocation: qwf\r\n\r\nEmail: messi@gmail.com\r\n\r\nContact Number: 7890123456\r\n\r\n-------------------------------------------- Career Summary ------------------------------------\r\n\r\nsdfaerg rwh tyj yuk w aef eag t jyku tku  ger y ty utre waw a\r\n\r\n------------------------------------------- Educational Details ---------------------------------\r\n\r\nwqqf\r\n\r\n-------------------------------------------- Experience --------------------------------------\r\n\r\nqwf\r\n\r\n---------------------------------------------- Skills -------------------------------------\r\n\r\n1. Google Cloud\r\n2. Python\r\n3. Time Management\r\n'),
(7, 1, 'sdfg', '========================================== Resume =======================================\r\n\r\n------------------------------------------ Personal Details ------------------------------------\r\n\r\nName: shivam shukla\r\n\r\nLocation: ahmedabad\r\n\r\nEmail: shivam@gmail.com\r\n\r\nContact Number: 7572908034\r\n\r\n-------------------------------------------- Career Summary ------------------------------------\r\n\r\nfefef efew few few few few few f \r\n\r\n------------------------------------------- Educational Details ---------------------------------\r\n\r\ncse AIDS ljiet\r\n\r\n-------------------------------------------- Experience --------------------------------------\r\n\r\nsfew fewfewf ew\r\n\r\n---------------------------------------------- Skills -------------------------------------\r\n\r\n1. Problem Solving\r\n2. Critical Thinking\r\n3. Communication\r\n4. JavaScript\r\n5. HTML\r\n'),
(8, 1, 'sdfg', '========================================== Resume =======================================\r\n\r\n------------------------------------------ Personal Details ------------------------------------\r\n\r\nName: shivam shukla\r\n\r\nLocation: ahmedabad\r\n\r\nEmail: shivam@gmail.com\r\n\r\nContact Number: 7572908034\r\n\r\n-------------------------------------------- Career Summary ------------------------------------\r\n\r\ngtrtr hty  tyh tyh \r\n\r\n------------------------------------------- Educational Details ---------------------------------\r\n\r\ncse AIDS ljiet\r\n\r\n-------------------------------------------- Experience --------------------------------------\r\n\r\nsfew fewfewf ew\r\n\r\n---------------------------------------------- Skills -------------------------------------\r\n\r\n1. Problem Solving\r\n2. Critical Thinking\r\n3. Communication\r\n4. JavaScript\r\n5. HTML\r\n'),
(9, 2, 'xyz', '========================================== Resume =======================================\r\n\r\n------------------------------------------ Personal Details ------------------------------------\r\n\r\nName: krunal ahir\r\n\r\nLocation: surat\r\n\r\nEmail: krunal@gmail.com\r\n\r\nContact Number: 7874228036\r\n\r\n-------------------------------------------- Career Summary ------------------------------------\r\n\r\ndhtrh tyh ty hty j ty\r\n\r\n------------------------------------------- Educational Details ---------------------------------\r\n\r\ng Re\r\n\r\n-------------------------------------------- Experience --------------------------------------\r\n\r\nes sgfewgf ew\r\n\r\n---------------------------------------------- Skills -------------------------------------\r\n\r\n1. Java\r\n2. Python\r\n3. JavaScript\r\n'),
(10, 2, 'adjv', '========================================== Resume =======================================\r\n\r\n------------------------------------------ Personal Details ------------------------------------\r\n\r\nName: rgtrg54\r\n\r\nLocation: g54rg54\r\n\r\nEmail: dfvfd@.erfer\r\n\r\nContact Number: 7890123456\r\n\r\n-------------------------------------------- Career Summary ------------------------------------\r\n\r\nferg trg tr\r\n\r\n------------------------------------------- Educational Details ---------------------------------\r\n\r\ngf erg trg tr g\r\n\r\n-------------------------------------------- Experience --------------------------------------\r\n\r\nrg trg tr g\r\n\r\n---------------------------------------------- Skills -------------------------------------\r\n\r\n1. Problem Solving\r\n2. Critical Thinking\r\n'),
(11, 1, 'testIp', '========================================== Resume =======================================\r\n\r\n------------------------------------------ Personal Details ------------------------------------\r\n\r\nName: shivam shukla\r\n\r\nLocation: ahmedabad\r\n\r\nEmail: shivam@gmail.com\r\n\r\nContact Number: 7572908034\r\n\r\n-------------------------------------------- Career Summary ------------------------------------\r\n\r\nef er gtr gtr g g t\r\n\r\n------------------------------------------- Educational Details ---------------------------------\r\n\r\ncse AIDS ljiet\r\n\r\n-------------------------------------------- Experience --------------------------------------\r\n\r\nsfew fewfewf ew\r\n\r\n---------------------------------------------- Skills -------------------------------------\r\n\r\n1. Problem Solving\r\n2. Critical Thinking\r\n3. Communication\r\n4. JavaScript\r\n5. HTML\r\n'),
(12, 1, 'shivamss1', '========================================== Resume =======================================\r\n\r\n------------------------------------------ Personal Details ------------------------------------\r\n\r\nName: shivam sss\r\n\r\nLocation: surat\r\n\r\nEmail: shiv@gmail.com\r\n\r\nContact Number: 7572908034\r\n\r\n-------------------------------------------- Career Summary ------------------------------------\r\n\r\ns erg tr g trg trg tr ty j iuk 8i k df ca xew f erh yjk i liu jhrt gtr hty ju ky uh tr\r\n\r\n------------------------------------------- Educational Details ---------------------------------\r\n\r\njjj\r\n\r\n-------------------------------------------- Experience --------------------------------------\r\n\r\nsfew fewfewf ew\r\n\r\n---------------------------------------------- Skills -------------------------------------\r\n\r\n1. JavaScript\r\n2. HTML\r\n3. Python\r\n'),
(13, 1, 'forsomeone', '========================================== Resume =======================================\r\n\r\n------------------------------------------ Personal Details ------------------------------------\r\n\r\nName: gg  gg\r\n\r\nLocation: ss s s s\r\n\r\nEmail: ss\r\n\r\nContact Number: 6789012345\r\n\r\n-------------------------------------------- Career Summary ------------------------------------\r\n\r\ns s s s \r\n\r\n------------------------------------------- Educational Details ---------------------------------\r\n\r\ns s s \r\n\r\n-------------------------------------------- Experience --------------------------------------\r\n\r\ns s s \r\n\r\n---------------------------------------------- Skills -------------------------------------\r\n\r\n1. Python\r\n2. JavaScript\r\n3. Data Structures and Algorithms\r\n'),
(14, 1, 'demoviva', '========================================== Resume =======================================\r\n\r\n------------------------------------------ Personal Details ------------------------------------\r\n\r\nName: shivam sss\r\n\r\nLocation: surat\r\n\r\nEmail: shiv@gmail.com\r\n\r\nContact Number: 7572908034\r\n\r\n-------------------------------------------- Career Summary ------------------------------------\r\n\r\nf ewf erg r g rh trh \r\n\r\n------------------------------------------- Educational Details ---------------------------------\r\n\r\njjj\r\n\r\n-------------------------------------------- Experience --------------------------------------\r\n\r\nsfew fewfewf ew\r\n\r\n---------------------------------------------- Skills -------------------------------------\r\n\r\n1. Problem Solving\r\n2. Critical Thinking\r\n3. Python\r\n4. JavaScript\r\n5. HTML\r\n');

-- --------------------------------------------------------

--
-- Table structure for table `user_skills`
--

CREATE TABLE `user_skills` (
  `user_id` int(11) NOT NULL,
  `skill_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user_skills`
--

INSERT INTO `user_skills` (`user_id`, `skill_id`) VALUES
(1, 7),
(1, 8),
(1, 18),
(1, 19),
(1, 26),
(2, 17),
(2, 18),
(2, 19),
(3, 15),
(3, 27),
(3, 29),
(4, 11),
(4, 18),
(4, 25),
(5, 22),
(5, 23),
(6, 11),
(6, 23),
(6, 26),
(6, 27),
(7, 15),
(7, 16),
(7, 17),
(8, 10),
(9, 30),
(9, 31),
(10, 25),
(11, 27);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `careers`
--
ALTER TABLE `careers`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `job_listings1`
--
ALTER TABLE `job_listings1`
  ADD PRIMARY KEY (`id`),
  ADD KEY `rec_id` (`rec_id`);

--
-- Indexes for table `job_skills1`
--
ALTER TABLE `job_skills1`
  ADD PRIMARY KEY (`id`),
  ADD KEY `job_id` (`job_id`),
  ADD KEY `skill_id` (`skill_id`);

--
-- Indexes for table `personalityresultlog`
--
ALTER TABLE `personalityresultlog`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `recruiters`
--
ALTER TABLE `recruiters`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `skillresultlog`
--
ALTER TABLE `skillresultlog`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `skills`
--
ALTER TABLE `skills`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user_jobs1`
--
ALTER TABLE `user_jobs1`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `job_id` (`job_id`);

--
-- Indexes for table `user_resume`
--
ALTER TABLE `user_resume`
  ADD PRIMARY KEY (`resume_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `user_skills`
--
ALTER TABLE `user_skills`
  ADD PRIMARY KEY (`user_id`,`skill_id`),
  ADD KEY `skill_id` (`skill_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `careers`
--
ALTER TABLE `careers`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `job_listings1`
--
ALTER TABLE `job_listings1`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `job_skills1`
--
ALTER TABLE `job_skills1`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- AUTO_INCREMENT for table `personalityresultlog`
--
ALTER TABLE `personalityresultlog`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT for table `recruiters`
--
ALTER TABLE `recruiters`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `skillresultlog`
--
ALTER TABLE `skillresultlog`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `skills`
--
ALTER TABLE `skills`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `user_jobs1`
--
ALTER TABLE `user_jobs1`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `user_resume`
--
ALTER TABLE `user_resume`
  MODIFY `resume_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `job_listings1`
--
ALTER TABLE `job_listings1`
  ADD CONSTRAINT `job_listings1_ibfk_1` FOREIGN KEY (`rec_id`) REFERENCES `recruiters` (`id`);

--
-- Constraints for table `job_skills1`
--
ALTER TABLE `job_skills1`
  ADD CONSTRAINT `job_skills1_ibfk_1` FOREIGN KEY (`job_id`) REFERENCES `job_listings1` (`id`),
  ADD CONSTRAINT `job_skills1_ibfk_2` FOREIGN KEY (`skill_id`) REFERENCES `skills` (`id`);

--
-- Constraints for table `user_jobs1`
--
ALTER TABLE `user_jobs1`
  ADD CONSTRAINT `user_jobs1_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `user_jobs1_ibfk_2` FOREIGN KEY (`job_id`) REFERENCES `job_listings1` (`id`);

--
-- Constraints for table `user_resume`
--
ALTER TABLE `user_resume`
  ADD CONSTRAINT `user_resume_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `user_skills`
--
ALTER TABLE `user_skills`
  ADD CONSTRAINT `user_skills_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `user_skills_ibfk_2` FOREIGN KEY (`skill_id`) REFERENCES `skills` (`id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
