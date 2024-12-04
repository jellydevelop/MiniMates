-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 04-12-2024 a las 20:09:26
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
  `id_mundo` bigint(20) NOT NULL,
  `nombre_mundo` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `mundo`
--

INSERT INTO `mundo` (`id_mundo`, `nombre_mundo`) VALUES
(1, 'pirata'),
(2, 'espacio'),
(3, 'bosque'),
(4, 'mar');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pantalla`
--

CREATE TABLE `pantalla` (
  `aciertos_pantalla` int(11) NOT NULL,
  `duracion_pantalla` time(6) NOT NULL,
  `fallos_pantalla` int(11) NOT NULL,
  `fecha_pant_jugada` date NOT NULL,
  `id_mundo` bigint(20) NOT NULL,
  `id_pantalla` bigint(20) NOT NULL,
  `id_partida` bigint(20) NOT NULL,
  `tipo_reto` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `pantalla`
--

INSERT INTO `pantalla` (`aciertos_pantalla`, `duracion_pantalla`, `fallos_pantalla`, `fecha_pant_jugada`, `id_mundo`, `id_pantalla`, `id_partida`, `tipo_reto`) VALUES
(10, '00:12:00.000000', 2, '2024-12-01', 1, 1, 1, 'RECONOCIMIENTO NUMEROS'),
(10, '00:13:11.000000', 2, '2024-12-02', 1, 2, 3, 'RECONOCIMIENTO NUMEROS'),
(1, '00:08:06.000000', 0, '2024-12-01', 1, 3, 1, 'CONTEO'),
(0, '00:05:48.000000', 1, '2024-12-02', 1, 4, 3, 'CONTEO'),
(7, '00:07:48.000000', 3, '2024-12-03', 1, 5, 4, 'RECONOCIMIENTO NUMEROS'),
(1, '00:05:00.000000', 0, '2024-12-03', 1, 6, 4, 'CONTEO'),
(10, '00:37:00.000000', 5, '2024-12-04', 1, 8, 22, 'RECONOCIMIENTO NUMEROS'),
(10, '00:47:00.000000', 8, '2024-12-04', 1, 9, 22, 'RECONOCIMIENTO NUMEROS'),
(10, '00:49:00.000000', 7, '2024-12-04', 1, 10, 23, 'RECONOCIMIENTO NUMEROS'),
(0, '00:04:00.000000', 0, '2024-12-04', 1, 11, 23, 'CONTEO'),
(0, '00:04:00.000000', 0, '2024-12-04', 1, 12, 23, 'CONTEO'),
(0, '00:03:00.000000', 0, '2024-12-04', 1, 13, 23, 'CONTEO');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `partida`
--

CREATE TABLE `partida` (
  `estado_mundo` tinyint(1) NOT NULL,
  `fecha_inicio_mundo` date NOT NULL,
  `fecha_partida_diaria` date NOT NULL,
  `id_partida` bigint(20) NOT NULL,
  `mundo_id` bigint(20) DEFAULT NULL,
  `usuario_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `partida`
--

INSERT INTO `partida` (`estado_mundo`, `fecha_inicio_mundo`, `fecha_partida_diaria`, `id_partida`, `mundo_id`, `usuario_id`) VALUES
(1, '2024-12-01', '2024-12-01', 1, 1, 7),
(1, '2024-12-01', '2024-12-02', 3, 1, 7),
(1, '2024-12-01', '2024-12-03', 4, 1, 7),
(1, '2024-12-04', '2024-12-04', 17, 1, 2),
(1, '2024-12-04', '2024-12-04', 18, 1, 2),
(1, '2024-12-04', '2024-12-04', 19, 1, 2),
(1, '2024-12-04', '2024-12-04', 20, 1, 2),
(1, '2024-12-04', '2024-12-04', 21, 1, 2),
(1, '2024-12-04', '2024-12-04', 22, 1, 2),
(1, '2024-12-04', '2024-12-04', 23, 1, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id_usuario` bigint(20) NOT NULL,
  `codigo_centro` varchar(8) NOT NULL,
  `letra_clase` varchar(3) DEFAULT NULL,
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

INSERT INTO `usuario` (`id_usuario`, `codigo_centro`, `letra_clase`, `mail_usuario`, `nia_alumno`, `nombre_usuario`, `pass_usuario`, `primer_apellido_usuario`, `rol_usuario`, `segundo_apellido_usuario`) VALUES
(1, '28010101', 'A', 'profesorprimero42@gmail.com', NULL, 'prueba', 'admin', 'sisale', 'profesor', 'mejor'),
(2, '28010101', 'A', 'asahi@gmail.com', '42326593', 'asahi', 'as001', 'sagasti', 'alumno', 'figueroa'),
(3, '28010101', 'B', 'profe2@gmail.com', NULL, 'jesusin', 'admin2', 'ubrique', 'profesor', 'gomez'),
(4, '28010101', 'C', 'profe3@gmail.com', NULL, 'marta', 'admin3', 'palomar', 'profesor', 'siliconum'),
(5, '28010101', 'A', 'pepin@gmail.com', '41000001', 'pepinillo', 'pe002', 'En', 'alumno', 'Vinagre'),
(6, '28010101', 'A', 'mac@yahoo.es', '40000002', 'macarron', 'mac003', 'Con', 'alumno', 'Queso'),
(7, '28010101', 'B', 'shaoran@gmail.com', '41111110', 'shaoran', 'sh005', 'sagasti', 'alumno', 'figueroa'),
(8, '28010101', 'C', 'uryuu@gmail.com', '46666663', 'uryuu', 'ur006', 'sagasti', 'alumno', 'figueroa'),
(9, '28010101', 'A', 'ryouga@gmail.com', '49999992', 'ryouga', 'ry007', 'figueroa', 'alumno', 'sagasti'),
(10, '28010101', 'A', 'david@hotmail.com', '42222223', 'david', 'da008', 'cuidaDe', 'alumno', 'losPekes'),
(11, '28010101', 'B', 'aladin@gmail.com', '42163529', 'Las', 'Las794', 'MilY', 'alumno', 'UnaNoches'),
(13, '28010101', 'C', 'miau@gmail.com', '41233852', 'misifu', 'mis496', 'botines', 'alumno', 'bigotines'),
(16, '28010101', 'A', 'aymadre@gmail.com', '45555588', 'Que', 'Que389', 'Jaleo', 'alumno', 'Timoteo'),
(17, '28010101', 'B', 'locura@gmail.com', '46432356', 'locura', 'loc621', 'todo', 'alumno', 'eldia'),
(18, '28010101', 'B', 'pedro@gmail.com', '43636363', 'pedro', 'ped903', 'Yel', 'alumno', 'lobo'),
(19, '28010101', 'B', 'cap@gmail.com', '42525252', 'caperucita', 'cap899', 'roja', 'alumno', 'ylobo'),
(20, '28010101', 'B', 'alumno300@gmail.com', '42326869', 'alumno', 'alu392', 'numero', 'alumno', 'trescientos'),
(21, '28010101', 'C', ' m@hotm.com', '41777780', 'defresa', 'mar006', 'marco', 'alumno', 'polo'),
(22, '28010101', 'C', 'j@gmail.com', '41875986', 'pollito', 'pol421', 'pollo', 'alumno', 'gallina'),
(23, '28010101', 'A', 'lo@gmail.com', '43333333', 'loro', 'lor779', 'lorito', 'alumno', 'parrot'),
(24, '28010101', 'A', 'fre@gmail.com', 'e3333333', 'eefeef', 'eef398', 'iuhiuh', 'ALUMNO', 'iuhih'),
(25, '28010101', 'A', 'gegerg@gmail.com', '45454445', 'Pepito', 'Pep435', 'Grillo', 'ALUMNO', 'Pinocho'),
(26, '28010101', 'B', 'jimeo@gmail.com', 'fdfdfdfd', 'Fundacion', 'Fun351', 'Jimenez', 'ALUMNO', 'Diaz'),
(27, '28010101', 'B', 'jimeo@gmail.com', 'fdfdfdfd', 'Fundacion', 'Fun978', 'Jimenez', 'ALUMNO', 'Diaz'),
(28, '28010101', 'B', 'lobitos@gmail.com', '52525252', 'Cinco', 'Cin851', 'Lobitos', 'ALUMNO', 'TieneLaLoba'),
(29, '28010101', 'A', 'paco@gmail.com', '42222222', 'Paco', 'Pac866', 'Perez', 'alumno', 'Sanchez'),
(30, '28010101', 'B', 'MIMO@GAMIL.COM', '43333316', 'MI', 'MI498', 'MAMA', 'alumno', 'MEMIMA'),
(31, '28010101', 'B', 'JAAAAA@homtail.com', '41111113', 'elena', 'ele905', 'soto', 'alumno', 'garbin'),
(32, '28010101', 'B', 'KOKOK@HOEMAI.COM', '40000003', 'RUBEN', 'RUB293', 'JAMON', 'alumno', 'YCROQUETAS'),
(33, '28010101', 'B', 'KOKOKMNJN@HOEMAI.COM', '40000005', 'LOPRENZO', 'LOP776', 'CAYETANO', 'alumno', 'PALOMA');

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
  ADD PRIMARY KEY (`letra_clase`),
  ADD KEY `FKe42oge2yk6c6n7uhhylw8agww` (`id_centro`);

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
  ADD KEY `FK2o16tpgtuqyadowws41uk54h2` (`id_mundo`),
  ADD KEY `FK59yj2wypuj1qs8yng7g4edq1n` (`id_partida`);

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
  MODIFY `id_pantalla` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT de la tabla `partida`
--
ALTER TABLE `partida`
  MODIFY `id_partida` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id_usuario` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `clase`
--
ALTER TABLE `clase`
  ADD CONSTRAINT `FKe42oge2yk6c6n7uhhylw8agww` FOREIGN KEY (`id_centro`) REFERENCES `centro_educativo` (`id_centro`);

--
-- Filtros para la tabla `pantalla`
--
ALTER TABLE `pantalla`
  ADD CONSTRAINT `FK2o16tpgtuqyadowws41uk54h2` FOREIGN KEY (`id_mundo`) REFERENCES `mundo` (`id_mundo`),
  ADD CONSTRAINT `FK59yj2wypuj1qs8yng7g4edq1n` FOREIGN KEY (`id_partida`) REFERENCES `partida` (`id_partida`);

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
