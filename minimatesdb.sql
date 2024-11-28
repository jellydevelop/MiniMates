-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 28-11-2024 a las 20:26:38
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
  `letra_clase` varchar(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `clase`
--

INSERT INTO `clase` (`id_centro`, `letra_clase`) VALUES
(28010101, 'A'),
(28010101, 'B'),
(28010101, 'C');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mundo`
--

CREATE TABLE `mundo` (
  `activado` tinyint(1) NOT NULL,
  `id_mundo` bigint(20) NOT NULL,
  `partida_id` bigint(20) DEFAULT NULL,
  `nombre_mundo` varchar(20) NOT NULL,
  `fecha_inicio_mundo` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `mundo`
--

INSERT INTO `mundo` (`activado`, `id_mundo`, `partida_id`, `nombre_mundo`, `fecha_inicio_mundo`) VALUES
(0, 1, NULL, 'pirata', NULL),
(0, 2, NULL, 'espacio', NULL),
(0, 3, NULL, 'bosque', NULL),
(0, 4, NULL, 'mar', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pantalla`
--

CREATE TABLE `pantalla` (
  `aciertos_pantalla` int(11) NOT NULL,
  `fallos_pantalla` int(11) NOT NULL,
  `id` bigint(20) NOT NULL,
  `mundo_id` bigint(20) DEFAULT NULL,
  `tipo_reto` varchar(30) NOT NULL,
  `duracion_pantalla` datetime(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `pantalla`
--

INSERT INTO `pantalla` (`aciertos_pantalla`, `fallos_pantalla`, `id`, `mundo_id`, `tipo_reto`, `duracion_pantalla`) VALUES
(10, 2, 1, 1, 'RECONOCIMIENTO NUMEROS', '1970-01-01 00:00:00.000000'),
(10, 2, 2, 1, 'RECONOCIMIENTO NUMEROS', '1970-01-02 09:13:11.000000'),
(1, 0, 3, 1, 'CONTEO', '2024-11-28 17:02:06.000000');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `partida`
--

CREATE TABLE `partida` (
  `duracion_partida` int(11) NOT NULL,
  `fecha_inicio` datetime(6) NOT NULL,
  `id_partida` bigint(20) NOT NULL,
  `inicio_partida` datetime(6) NOT NULL,
  `usuario_id` bigint(20) NOT NULL,
  `mundo_id` bigint(20) DEFAULT NULL,
  `estado` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `partida`
--

INSERT INTO `partida` (`duracion_partida`, `fecha_inicio`, `id_partida`, `inicio_partida`, `usuario_id`, `mundo_id`, `estado`) VALUES
(15, '2024-11-25 20:17:15.000000', 1, '2024-11-25 11:36:18.000000', 5, 1, ''),
(4, '2024-11-28 17:04:58.000000', 3, '2024-11-28 17:00:58.000000', 5, 1, '');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id_usuario` bigint(20) NOT NULL,
  `letra_clase` varchar(3) DEFAULT NULL,
  `codigo_centro` varchar(8) NOT NULL,
  `mail_usuario` varchar(35) NOT NULL,
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
(1, 'A', '28010101', 'profesorprimero42@gmail.com', NULL, 'prueba', 'admin', 'sisale', 'profesor', 'mejor'),
(2, 'A', '28010101', 'asahi@gmail.com', '42326593', 'asahi', 'as001', 'sagasti', 'alumno', 'figueroa'),
(3, 'B', '28010101', 'profe2@gmail.com', NULL, 'jesusin', 'admin2', 'ubrique', 'profesor', 'gomez'),
(4, 'C', '28010101', 'profe3@gmail.com', NULL, 'marta', 'admin3', 'palomar', 'profesor', 'siliconum'),
(5, 'A', '28010101', 'pepin@gmail.com', '41000001', 'pepinillo', 'pe002', 'En', 'alumno', 'Vinagre'),
(6, 'A', '28010101', 'mac@yahoo.es', '40000002', 'macarron', 'mac003', 'Con', 'alumno', 'Queso'),
(7, 'B', '28010101', 'shaoran@gmail.com', '41111110', 'shaoran', 'sh005', 'sagasti', 'alumno', 'figueroa'),
(8, 'C', '28010101', 'uryuu@gmail.com', '46666663', 'uryuu', 'ur006', 'sagasti', 'alumno', 'figueroa'),
(9, 'A', '28010101', 'ryouga@gmail.com', '49999992', 'ryouga', 'ry007', 'figueroa', 'alumno', 'sagasti'),
(10, 'A', '28010101', 'david@hotmail.com', '42222223', 'david', 'da008', 'cuidaDe', 'alumno', 'losPekes'),
(11, 'B', '28010101', 'aladin@gmail.com', '42163529', 'Las', 'Las794', 'MilY', 'alumno', 'UnaNoches'),
(13, 'C', '28010101', 'miau@gmail.com', '41233852', 'misifu', 'mis496', 'botines', 'alumno', 'bigotines'),
(16, 'A', '28010101', 'aymadre@gmail.com', '45555588', 'Que', 'Que389', 'Jaleo', 'alumno', 'Timoteo'),
(17, 'B', '28010101', 'locura@gmail.com', '46432356', 'locura', 'loc621', 'todo', 'alumno', 'eldia'),
(18, 'B', '28010101', 'pedro@gmail.com', '43636363', 'pedro', 'ped903', 'Yel', 'alumno', 'lobo'),
(19, 'B', '28010101', 'cap@gmail.com', '42525252', 'caperucita', 'cap899', 'roja', 'alumno', 'ylobo'),
(20, 'B', '28010101', 'alumno300@gmail.com', '42326869', 'alumno', 'alu392', 'numero', 'alumno', 'trescientos'),
(21, 'C', '28010101', ' m@hotm.com', '41777780', 'defresa', 'mar006', 'marco', 'alumno', 'polo'),
(22, 'C', '28010101', 'j@gmail.com', '41875986', 'pollito', 'pol421', 'pollo', 'alumno', 'gallina'),
(23, 'A', '28010101', 'lo@gmail.com', '43333333', 'loro', 'lor779', 'lorito', 'alumno', 'parrot'),
(24, 'A', '28010101', 'fre@gmail.com', 'e3333333', 'eefeef', 'eef398', 'iuhiuh', 'ALUMNO', 'iuhih'),
(25, 'A', '28010101', 'gegerg@gmail.com', '45454445', 'Pepito', 'Pep435', 'Grillo', 'ALUMNO', 'Pinocho'),
(26, 'B', '28010101', 'jimeo@gmail.com', 'fdfdfdfd', 'Fundacion', 'Fun351', 'Jimenez', 'ALUMNO', 'Diaz'),
(27, 'B', '28010101', 'jimeo@gmail.com', 'fdfdfdfd', 'Fundacion', 'Fun978', 'Jimenez', 'ALUMNO', 'Diaz'),
(28, 'B', '28010101', 'lobitos@gmail.com', '52525252', 'Cinco', 'Cin851', 'Lobitos', 'ALUMNO', 'TieneLaLoba'),
(29, 'A', '28010101', 'paco@gmail.com', '42222222', 'Paco', 'Pac866', 'Perez', 'alumno', 'Sanchez'),
(30, 'B', '28010101', 'MIMO@GAMIL.COM', '43333316', 'MI', 'MI498', 'MAMA', 'alumno', 'MEMIMA'),
(31, 'B', '28010101', 'JAAAAA@homtail.com', '41111113', 'elena', 'ele905', 'soto', 'alumno', 'garbin'),
(32, 'B', '28010101', 'KOKOK@HOEMAI.COM', '40000003', 'RUBEN', 'RUB293', 'JAMON', 'alumno', 'YCROQUETAS'),
(33, 'B', '28010101', 'KOKOKMNJN@HOEMAI.COM', '40000005', 'LOPRENZO', 'LOP776', 'CAYETANO', 'alumno', 'PALOMA');

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
  ADD PRIMARY KEY (`letra_clase`) USING BTREE,
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
  ADD KEY `FKtmaxxk00hoolj2hd5hq034je3` (`usuario_id`),
  ADD KEY `FK24eve8gpoh18vau6xmauy0s7r` (`mundo_id`);

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
-- AUTO_INCREMENT de la tabla `mundo`
--
ALTER TABLE `mundo`
  MODIFY `id_mundo` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `pantalla`
--
ALTER TABLE `pantalla`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `partida`
--
ALTER TABLE `partida`
  MODIFY `id_partida` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id_usuario` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;

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
  ADD CONSTRAINT `FK24eve8gpoh18vau6xmauy0s7r` FOREIGN KEY (`mundo_id`) REFERENCES `mundo` (`id_mundo`),
  ADD CONSTRAINT `FKtmaxxk00hoolj2hd5hq034je3` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id_usuario`);

--
-- Filtros para la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `FKtgn3pcdikwbfe1ixsi84jhwa7` FOREIGN KEY (`letra_clase`) REFERENCES `clase` (`letra_clase`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
