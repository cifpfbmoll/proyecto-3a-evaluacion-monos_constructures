-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 01-04-2021 a las 12:30:23
-- Versión del servidor: 10.4.18-MariaDB
-- Versión de PHP: 8.0.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `mce_cruceros`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `asignacion`
--

CREATE TABLE `asignacion` (
  `CODIGO_CRUCERO` varchar(20) NOT NULL,
  `FECHA_EMBARQUE_VIAJE` datetime NOT NULL,
  `CODIGO_EMPLEADO` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `billete`
--

CREATE TABLE `billete` (
  `NIE` varchar(20) NOT NULL,
  `FECHA_EMBARQUE_VIAJE` datetime NOT NULL,
  `CODIGO_CAMAROTE` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `camarotes`
--

CREATE TABLE `camarotes` (
  `CODIGO_CAMAROTE` varchar(20) NOT NULL,
  `CODIGO_CRUCERO` varchar(20) NOT NULL,
  `CODIGO_TARIFA` varchar(20) NOT NULL,
  `NUMERO_CAMAS` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ciudad`
--

CREATE TABLE `ciudad` (
  `CODIGO_CIUDAD` varchar(20) NOT NULL,
  `NOMBRE_CIUDAD` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `NIE` varchar(20) NOT NULL,
  `NOMBRE_CLIENTE` varchar(30) NOT NULL,
  `APELLIDOS_CLIENTE` varchar(40) NOT NULL,
  `FECHA_NACIMIENTO_CLIENTE` int(11) NOT NULL,
  `CODIGO_DESCUENTO` varchar(20) NOT NULL,
  `TELEFONO_CLIENTE` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `crucero`
--

CREATE TABLE `crucero` (
  `CODIGO_CRUCERO` varchar(20) NOT NULL,
  `NOMBRE_CRUCERO` varchar(20) NOT NULL,
  `MODELO_CRUCERO` varchar(30) NOT NULL,
  `ESLORA` int(11) NOT NULL,
  `MANGA` int(11) NOT NULL,
  `CALADO` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `descuento`
--

CREATE TABLE `descuento` (
  `CODIGO_DESCUENTO` varchar(20) NOT NULL,
  `NOMBRE_DESCUENTO` varchar(30) NOT NULL,
  `PORCENTAJE_DESCUENTO` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleado`
--

CREATE TABLE `empleado` (
  `CODIGO_EMPLEADO` varchar(20) NOT NULL,
  `NIE_EMPLEADO` varchar(20) NOT NULL,
  `NOMBRE_EMPLEADO` varchar(30) NOT NULL,
  `APELLIDO_EMPLEADO` varchar(40) NOT NULL,
  `DOMICILIACION_EMPLEADO` varchar(40) NOT NULL,
  `FECHA_NACIMIENTO_EMPLEADO` date NOT NULL,
  `CODIGO_SERVICIO` varchar(20) NOT NULL,
  `CONTRASEÑA_EMPLEADO` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `nomina`
--

CREATE TABLE `nomina` (
  `CODIGO_EMPLEADO` varchar(20) NOT NULL,
  `FECHA_NOMINA` date NOT NULL,
  `HORAS_ORDINARIAS` int(11) NOT NULL,
  `HORAS_EXTRA` int(11) NOT NULL,
  `PLUS_NOMINA` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `paradas`
--

CREATE TABLE `paradas` (
  `CODIGO_CRUCERO` varchar(20) NOT NULL,
  `FECHA_EMBARQUE_VIAJE` datetime NOT NULL,
  `CODIGO_PUERTO` varchar(20) NOT NULL,
  `NUMERO_PARADA` int(11) NOT NULL,
  `FECHA_LLEGADA` datetime NOT NULL,
  `FECHA_SALIDA` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `puerto`
--

CREATE TABLE `puerto` (
  `CODIGO_PUERTO` varchar(20) NOT NULL,
  `NOMBRE_PUERTO` varchar(30) NOT NULL,
  `CODIGO_CIUDAD` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `servicio`
--

CREATE TABLE `servicio` (
  `CODIGO_SERVICIO` varchar(20) NOT NULL,
  `NOMBRE_SERVICIO` varchar(30) NOT NULL,
  `SALARIO_SERVICIO` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tarifa`
--

CREATE TABLE `tarifa` (
  `CODIGO_TARIFA` varchar(20) NOT NULL,
  `NOMBRE_TARIFA` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `viaje`
--

CREATE TABLE `viaje` (
  `CODIGO_CRUCERO` varchar(20) NOT NULL,
  `FECHA_EMBARQUE_VIAJE` datetime NOT NULL,
  `FECHA_LLEGADA_VIAJE` datetime NOT NULL,
  `DESCRIPCION_VIAJE` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `asignacion`
--
ALTER TABLE `asignacion`
  ADD PRIMARY KEY (`CODIGO_CRUCERO`,`FECHA_EMBARQUE_VIAJE`,`CODIGO_EMPLEADO`),
  ADD KEY `FECHA_EMBARQUE_VIAJE` (`FECHA_EMBARQUE_VIAJE`),
  ADD KEY `CODIGO_EMPLEADO` (`CODIGO_EMPLEADO`);

--
-- Indices de la tabla `billete`
--
ALTER TABLE `billete`
  ADD PRIMARY KEY (`NIE`,`FECHA_EMBARQUE_VIAJE`) USING BTREE,
  ADD KEY `CODIGO_CAMAROTE` (`CODIGO_CAMAROTE`),
  ADD KEY `FECHA_EMBARQUE_VIAJE` (`FECHA_EMBARQUE_VIAJE`);

--
-- Indices de la tabla `camarotes`
--
ALTER TABLE `camarotes`
  ADD PRIMARY KEY (`CODIGO_CAMAROTE`),
  ADD KEY `CODIGO_CRUCERO` (`CODIGO_CRUCERO`),
  ADD KEY `CODIGO_TARIFA` (`CODIGO_TARIFA`);

--
-- Indices de la tabla `ciudad`
--
ALTER TABLE `ciudad`
  ADD PRIMARY KEY (`CODIGO_CIUDAD`);

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`NIE`),
  ADD KEY `CODIGO_DESCUENTO` (`CODIGO_DESCUENTO`);

--
-- Indices de la tabla `crucero`
--
ALTER TABLE `crucero`
  ADD PRIMARY KEY (`CODIGO_CRUCERO`);

--
-- Indices de la tabla `descuento`
--
ALTER TABLE `descuento`
  ADD PRIMARY KEY (`CODIGO_DESCUENTO`);

--
-- Indices de la tabla `empleado`
--
ALTER TABLE `empleado`
  ADD PRIMARY KEY (`CODIGO_EMPLEADO`),
  ADD KEY `CODIGO_SERVICIO` (`CODIGO_SERVICIO`);

--
-- Indices de la tabla `nomina`
--
ALTER TABLE `nomina`
  ADD PRIMARY KEY (`CODIGO_EMPLEADO`,`FECHA_NOMINA`);

--
-- Indices de la tabla `paradas`
--
ALTER TABLE `paradas`
  ADD PRIMARY KEY (`CODIGO_CRUCERO`,`NUMERO_PARADA`,`FECHA_EMBARQUE_VIAJE`) USING BTREE,
  ADD KEY `CODIGO_PUERTO` (`CODIGO_PUERTO`),
  ADD KEY `paradas_ibfk_3` (`FECHA_EMBARQUE_VIAJE`);

--
-- Indices de la tabla `puerto`
--
ALTER TABLE `puerto`
  ADD PRIMARY KEY (`CODIGO_PUERTO`),
  ADD KEY `CODIGO_CIUDAD` (`CODIGO_CIUDAD`);

--
-- Indices de la tabla `servicio`
--
ALTER TABLE `servicio`
  ADD PRIMARY KEY (`CODIGO_SERVICIO`);

--
-- Indices de la tabla `tarifa`
--
ALTER TABLE `tarifa`
  ADD PRIMARY KEY (`CODIGO_TARIFA`);

--
-- Indices de la tabla `viaje`
--
ALTER TABLE `viaje`
  ADD PRIMARY KEY (`CODIGO_CRUCERO`,`FECHA_EMBARQUE_VIAJE`) USING BTREE,
  ADD KEY `FECHA_EMBARQUE_VIAJE` (`FECHA_EMBARQUE_VIAJE`),
  ADD KEY `CODIGO_CRUCERO` (`CODIGO_CRUCERO`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `asignacion`
--
ALTER TABLE `asignacion`
  ADD CONSTRAINT `asignacion_ibfk_1` FOREIGN KEY (`CODIGO_CRUCERO`) REFERENCES `viaje` (`CODIGO_CRUCERO`),
  ADD CONSTRAINT `asignacion_ibfk_2` FOREIGN KEY (`FECHA_EMBARQUE_VIAJE`) REFERENCES `viaje` (`FECHA_EMBARQUE_VIAJE`),
  ADD CONSTRAINT `asignacion_ibfk_3` FOREIGN KEY (`CODIGO_EMPLEADO`) REFERENCES `empleado` (`CODIGO_EMPLEADO`);

--
-- Filtros para la tabla `billete`
--
ALTER TABLE `billete`
  ADD CONSTRAINT `billete_ibfk_1` FOREIGN KEY (`NIE`) REFERENCES `cliente` (`NIE`),
  ADD CONSTRAINT `billete_ibfk_2` FOREIGN KEY (`CODIGO_CAMAROTE`) REFERENCES `camarotes` (`CODIGO_CAMAROTE`),
  ADD CONSTRAINT `billete_ibfk_3` FOREIGN KEY (`FECHA_EMBARQUE_VIAJE`) REFERENCES `viaje` (`FECHA_EMBARQUE_VIAJE`);

--
-- Filtros para la tabla `camarotes`
--
ALTER TABLE `camarotes`
  ADD CONSTRAINT `camarotes_ibfk_1` FOREIGN KEY (`CODIGO_CRUCERO`) REFERENCES `crucero` (`CODIGO_CRUCERO`),
  ADD CONSTRAINT `camarotes_ibfk_2` FOREIGN KEY (`CODIGO_TARIFA`) REFERENCES `tarifa` (`CODIGO_TARIFA`);

--
-- Filtros para la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD CONSTRAINT `cliente_ibfk_1` FOREIGN KEY (`CODIGO_DESCUENTO`) REFERENCES `descuento` (`CODIGO_DESCUENTO`);

--
-- Filtros para la tabla `empleado`
--
ALTER TABLE `empleado`
  ADD CONSTRAINT `empleado_ibfk_1` FOREIGN KEY (`CODIGO_SERVICIO`) REFERENCES `servicio` (`CODIGO_SERVICIO`);

--
-- Filtros para la tabla `nomina`
--
ALTER TABLE `nomina`
  ADD CONSTRAINT `nomina_ibfk_1` FOREIGN KEY (`CODIGO_EMPLEADO`) REFERENCES `empleado` (`CODIGO_EMPLEADO`);

--
-- Filtros para la tabla `paradas`
--
ALTER TABLE `paradas`
  ADD CONSTRAINT `paradas_ibfk_1` FOREIGN KEY (`CODIGO_PUERTO`) REFERENCES `puerto` (`CODIGO_PUERTO`),
  ADD CONSTRAINT `paradas_ibfk_3` FOREIGN KEY (`FECHA_EMBARQUE_VIAJE`) REFERENCES `viaje` (`FECHA_EMBARQUE_VIAJE`),
  ADD CONSTRAINT `paradas_ibfk_4` FOREIGN KEY (`CODIGO_CRUCERO`) REFERENCES `viaje` (`CODIGO_CRUCERO`);

--
-- Filtros para la tabla `puerto`
--
ALTER TABLE `puerto`
  ADD CONSTRAINT `puerto_ibfk_1` FOREIGN KEY (`CODIGO_CIUDAD`) REFERENCES `ciudad` (`CODIGO_CIUDAD`);

--
-- Filtros para la tabla `viaje`
--
ALTER TABLE `viaje`
  ADD CONSTRAINT `viaje_ibfk_1` FOREIGN KEY (`CODIGO_CRUCERO`) REFERENCES `crucero` (`CODIGO_CRUCERO`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
