-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 15-10-2024 a las 20:01:54
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `minimatesdb`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mundo`
--

CREATE TABLE `mundo` (
  `id_mundo` bigint(20) NOT NULL,
  `cant_pantallas_mundo` char(2) NOT NULL,
  `nombre_mundo` varchar(7) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pantalla`
--

CREATE TABLE `pantalla` (
  `id_pantalla` bigint(20) NOT NULL,
  `nombre_pantalla` varchar(255) DEFAULT NULL,
  `mundo_id_mundo` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id` bigint(20) NOT NULL,
  `mail_usuario` varchar(25) DEFAULT NULL,
  `nombre_usuario` varchar(10) DEFAULT NULL,
  `pass_usuario` varchar(10) DEFAULT NULL,
  `rol_usuario` varchar(8) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id`, `mail_usuario`, `nombre_usuario`, `pass_usuario`, `rol_usuario`) VALUES
(1, 'prueba@gmail.com', 'test', 'admi', 'profesor'),
(2, 'asahi@gmail.con', 'asahi', 'as001', 'alumno'),
(5, 'peripipitas@gmail.com', 'pablo', 'pup502', 'alumno'),
(6, 'popopitas@gmail.com', 'popitas', 'pup137', 'alumno'),
(7, 'popitas@gmail.com', 'pupitas', 'pup449', 'alumno'),
(8, 'juan@gmail.ocm', 'juan', 'jua478', 'alumno'),
(9, 'pedrito@gmail.com', 'pedro', 'ped584', 'alumno'),
(10, 'sesito@gtim.bom', 'sesito', 'ses554', 'alumno'),
(11, 'jaimito@gtim.bom', 'jaime', 'jai178', 'alumno'),
(12, 'pitufin@bahoo.es', 'joselin', 'pit298', 'alumno');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario_seq`
--

CREATE TABLE `usuario_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuario_seq`
--

INSERT INTO `usuario_seq` (`next_val`) VALUES
(1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `mundo`
--
ALTER TABLE `mundo`
  ADD PRIMARY KEY (`id_mundo`);

--
-- Indices de la tabla `pantalla`
--
ALTER TABLE `pantalla`
  ADD PRIMARY KEY (`id_pantalla`),
  ADD KEY `FKhgaek5v93w7s54pssra9732a1` (`mundo_id_mundo`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `mundo`
--
ALTER TABLE `mundo`
  MODIFY `id_mundo` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `pantalla`
--
ALTER TABLE `pantalla`
  MODIFY `id_pantalla` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `pantalla`
--
ALTER TABLE `pantalla`
  ADD CONSTRAINT `FKhgaek5v93w7s54pssra9732a1` FOREIGN KEY (`mundo_id_mundo`) REFERENCES `mundo` (`id_mundo`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
