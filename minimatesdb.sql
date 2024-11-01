-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 01-11-2024 a las 18:57:20
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
-- Estructura de tabla para la tabla `centro_educativo`
--

CREATE TABLE `centro_educativo` (
  `id_centro` bigint(20) NOT NULL,
  `direccion_centro` varchar(50) NOT NULL,
  `nombre_centro` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `centro_educativo`
--

INSERT INTO `centro_educativo` (`id_centro`, `direccion_centro`, `nombre_centro`) VALUES
(28010101, 'c/ perico de los palotes 15', 'aventura ramirez');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clase`
--

CREATE TABLE `clase` (
  `id_centro` bigint(20) NOT NULL,
  `id_clase` bigint(20) NOT NULL,
  `letra_clase` varchar(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `clase`
--

INSERT INTO `clase` (`id_centro`, `id_clase`, `letra_clase`) VALUES
(28010101, 10, 'A'),
(28010101, 11, 'B'),
(28010101, 12, 'C');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mundo`
--

CREATE TABLE `mundo` (
  `activado` char(1) NOT NULL,
  `id_mundo` bigint(20) NOT NULL,
  `partida_id` bigint(20) DEFAULT NULL,
  `nombre_mundo` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pantalla`
--

CREATE TABLE `pantalla` (
  `aciertos_pantalla` bigint(2) NOT NULL,
  `fallos_pantalla` bigint(2) NOT NULL,
  `id` bigint(20) NOT NULL,
  `mundo_id` bigint(20) DEFAULT NULL,
  `tiempo_fin_pantalla` datetime(6) NOT NULL,
  `tiempo_inicio_pantalla` datetime(6) NOT NULL,
  `tipo_reto` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `partida`
--

CREATE TABLE `partida` (
  `duracion_partida` int(11) NOT NULL,
  `fecha_inicio` datetime(6) NOT NULL,
  `id_partida` bigint(20) NOT NULL,
  `inicio_partida` datetime(6) NOT NULL,
  `usuario_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id_usuario` bigint(20) NOT NULL,
  `letra_clase` bigint(20) DEFAULT NULL,
  `codigo_centro` varchar(8) NOT NULL,
  `mail_usuario` varchar(25) NOT NULL,
  `nia_alumno` varchar(8) DEFAULT NULL,
  `nombre_usuario` varchar(20) NOT NULL,
  `pass_usuario` varchar(10) NOT NULL,
  `primer_apellido_usuario` varchar(25) NOT NULL,
  `rol_usuario` varchar(10) NOT NULL,
  `segundo_apellido_usuario` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id_usuario`, `letra_clase`, `codigo_centro`, `mail_usuario`, `nia_alumno`, `nombre_usuario`, `pass_usuario`, `primer_apellido_usuario`, `rol_usuario`, `segundo_apellido_usuario`) VALUES
(1, 10, '28010101', 'prueba@gmail.com', NULL, 'prueba', 'admin', 'sisale', 'profesor', 'mejor'),
(2, 10, '28010101', 'asahi@gmail.com', '42326593', 'asahi', 'as001', 'sagasti', 'alumno', 'figueroa'),
(3, 11, '28010101', 'profe2@gmail.com', NULL, 'jesusin', 'admin2', 'ubrique', 'profesor', 'gomez'),
(4, 12, '28010101', 'profe3@gmail.com', NULL, 'marta', 'admin3', 'palomar', 'profesor', 'siliconum'),
(5, 10, '28010101', 'pepin@gmail.com', '41000001', 'pepinillo', 'pe002', 'En', 'alumno', 'Vinagre'),
(6, 10, '28010101', 'mac@yahoo.es', '40000002', 'macarron', 'mac003', 'Con', 'alumno', 'Queso'),
(7, 11, '28010101', 'shaoran@gmail.com', '41111110', 'shaoran', 'sh005', 'sagasti', 'alumno', 'figueroa'),
(8, 12, '28010101', 'uryuu@gmail.com', '46666663', 'uryuu', 'ur006', 'sagasti', 'alumno', 'figueroa'),
(9, 10, '28010101', 'ryouga@gmail.com', '49999992', 'ryouga', 'ry007', 'figueroa', 'alumno', 'sagasti'),
(10, 10, '28010101', 'david@hotmail.com', '42222223', 'david', 'da008', 'cuidaDe', 'alumno', 'losPekes');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `centro_educativo`
--
ALTER TABLE `centro_educativo`
  ADD PRIMARY KEY (`id_centro`);

--
-- Indices de la tabla `clase`
--
ALTER TABLE `clase`
  ADD PRIMARY KEY (`id_clase`),
  ADD UNIQUE KEY `UK_h9nrtr8ye2ttyi86c1cveeuas` (`letra_clase`),
  ADD KEY `FKe42oge2yk6c6n7uhhylw8agww` (`id_centro`);

--
-- Indices de la tabla `mundo`
--
ALTER TABLE `mundo`
  ADD PRIMARY KEY (`id_mundo`),
  ADD KEY `FKrvnxbnhgbbnl260a11co5xsu8` (`partida_id`);

--
-- Indices de la tabla `pantalla`
--
ALTER TABLE `pantalla`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKj621lew13nw5qq5g2xjdefgrs` (`mundo_id`);

--
-- Indices de la tabla `partida`
--
ALTER TABLE `partida`
  ADD PRIMARY KEY (`id_partida`),
  ADD KEY `FKtmaxxk00hoolj2hd5hq034je3` (`usuario_id`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id_usuario`),
  ADD KEY `FKtgn3pcdikwbfe1ixsi84jhwa7` (`letra_clase`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `centro_educativo`
--
ALTER TABLE `centro_educativo`
  MODIFY `id_centro` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28010102;

--
-- AUTO_INCREMENT de la tabla `clase`
--
ALTER TABLE `clase`
  MODIFY `id_clase` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT de la tabla `mundo`
--
ALTER TABLE `mundo`
  MODIFY `id_mundo` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `pantalla`
--
ALTER TABLE `pantalla`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `partida`
--
ALTER TABLE `partida`
  MODIFY `id_partida` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id_usuario` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `clase`
--
ALTER TABLE `clase`
  ADD CONSTRAINT `FKe42oge2yk6c6n7uhhylw8agww` FOREIGN KEY (`id_centro`) REFERENCES `centro_educativo` (`id_centro`);

--
-- Filtros para la tabla `mundo`
--
ALTER TABLE `mundo`
  ADD CONSTRAINT `FKrvnxbnhgbbnl260a11co5xsu8` FOREIGN KEY (`partida_id`) REFERENCES `partida` (`id_partida`);

--
-- Filtros para la tabla `pantalla`
--
ALTER TABLE `pantalla`
  ADD CONSTRAINT `FKj621lew13nw5qq5g2xjdefgrs` FOREIGN KEY (`mundo_id`) REFERENCES `mundo` (`id_mundo`);

--
-- Filtros para la tabla `partida`
--
ALTER TABLE `partida`
  ADD CONSTRAINT `FKtmaxxk00hoolj2hd5hq034je3` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id_usuario`);

--
-- Filtros para la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `FKtgn3pcdikwbfe1ixsi84jhwa7` FOREIGN KEY (`letra_clase`) REFERENCES `clase` (`id_clase`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
