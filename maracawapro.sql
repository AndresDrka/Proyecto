-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 26-11-2019 a las 13:54:11
-- Versión del servidor: 10.3.16-MariaDB
-- Versión de PHP: 7.3.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `maracawapro`
--

DELIMITER $$
--
-- Procedimientos
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `calculadora` (`descripcionx` VARCHAR(20))  BEGIN
	
SELECT p.descripcion, p.Valor_unitario, dt.cantidad, (p.Valor_unitario * dt.cantidad) as valorTotal, (p.Valor_unitario * dt.cantidad) * dt.PorcentajeIva as valorIva FROM detallefactura dt inner JOIN productos p on dt.id_producto = p.id_producto WHERE p.descripcion = descripcionx;

END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `materiaprima` ()  BEGIN
	
SELECT COUNT(*) AS cantidad FROM productos;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `metas` ()  BEGIN
	
SELECT COUNT(*) AS cantidad FROM pedidos WHERE fecha_pedido = MONTH (NOW());
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `pedifechas` (`fecha_inicio` DATE, `fecha_final` DATE, `estado` VARCHAR(20))  BEGIN
	
SELECT 	P.estadoPedido, P.fecha_pedido, P.estadoPedido, C.restaurante, U.Nombre1, U.Apellido1, U.Telefono FROM pedidos P INNER JOIN cliente C on P.id_cliente= C.id_cliente INNER JOIN usuarios U ON C.id_usuarios = U.id_usuarios

WHERE(P.fecha_pedido BETWEEN fecha_inicio AND fecha_final) AND p.estadoPedido = estado;

END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `productosNoVendidos` (`idCalidad` INT)  BEGIN
	
SELECT p.descripcion, pr.cantidad, cp.observaciones, ca.Tipocalidad FROM productos p INNER JOIN produccion pr ON p.id_producto = pr.id_producto INNER JOIN calidad_produccion cp ON pr.id_produccion = cp.id_produccion INNER JOIN calidad ca ON ca.id_calidad = cp.id_calidad WHERE ca.id_calidad= idCalidad;

END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `calidad`
--

CREATE TABLE `calidad` (
  `id_calidad` bigint(11) NOT NULL,
  `Tipocalidad` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `calidad`
--

INSERT INTO `calidad` (`id_calidad`, `Tipocalidad`) VALUES
(1, 'buena'),
(2, 'tapa dañada'),
(3, 'etiqueta torcida'),
(4, 'etiqueta trocada');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `calidad_produccion`
--

CREATE TABLE `calidad_produccion` (
  `id_calidad` bigint(11) NOT NULL,
  `id_produccion` bigint(11) NOT NULL,
  `observaciones` varchar(200) NOT NULL,
  `id_etapa` bigint(11) NOT NULL,
  `Imagenes` text NOT NULL,
  `id_cali_pro` bigint(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cargo`
--

CREATE TABLE `cargo` (
  `id_cargo` bigint(11) NOT NULL,
  `Cargo` varchar(50) DEFAULT NULL,
  `sueldo` bigint(11) DEFAULT NULL,
  `id_Turno` bigint(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `cargo`
--

INSERT INTO `cargo` (`id_cargo`, `Cargo`, `sueldo`, `id_Turno`) VALUES
(1, 'Operario', 900000, 1),
(2, 'Jefe de producción', 1800000, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ciudades`
--

CREATE TABLE `ciudades` (
  `Id_ciudades` bigint(11) NOT NULL,
  `Ciudades` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `ciudades`
--

INSERT INTO `ciudades` (`Id_ciudades`, `Ciudades`) VALUES
(1, 'Bogotá'),
(2, 'Medellín'),
(3, 'Cali');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `id_cliente` bigint(11) NOT NULL,
  `restaurante` varchar(100) NOT NULL,
  `id_usuarios` bigint(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detallefactura`
--

CREATE TABLE `detallefactura` (
  `Id_detalle` bigint(11) NOT NULL,
  `id_producto` bigint(11) NOT NULL,
  `cantidad` int(11) DEFAULT NULL,
  `PrecioTotal` int(11) DEFAULT NULL,
  `PorcentajeIva` int(11) NOT NULL,
  `num_pedido` bigint(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleado`
--

CREATE TABLE `empleado` (
  `id_empleado` bigint(11) NOT NULL,
  `id_cargo` bigint(11) NOT NULL,
  `id_usuarios` bigint(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `empleado`
--

INSERT INTO `empleado` (`id_empleado`, `id_cargo`, `id_usuarios`) VALUES
(1, 1, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleados_novedades`
--

CREATE TABLE `empleados_novedades` (
  `id_empleado` bigint(11) NOT NULL,
  `id_novedad` bigint(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `empleados_novedades`
--

INSERT INTO `empleados_novedades` (`id_empleado`, `id_novedad`) VALUES
(1, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `etapa_produccion`
--

CREATE TABLE `etapa_produccion` (
  `id_etapa` bigint(11) NOT NULL,
  `etapa` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `insumos`
--

CREATE TABLE `insumos` (
  `id_insumos` bigint(11) NOT NULL,
  `nombreInsumo` varchar(50) DEFAULT NULL,
  `cantidad` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `insumos`
--

INSERT INTO `insumos` (`id_insumos`, `nombreInsumo`, `cantidad`) VALUES
(1, 'Habanero maduro', 50),
(2, 'Envase 500 ml', 60);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `logusuarios`
--

CREATE TABLE `logusuarios` (
  `Nombre1` varchar(100) NOT NULL,
  `Nombre2` varchar(100) NOT NULL,
  `Apellido1` varchar(100) NOT NULL,
  `Apellido2` varchar(100) NOT NULL,
  `fecha_Nacimiento` date NOT NULL,
  `Direccion` varchar(50) NOT NULL,
  `Telefono` int(11) NOT NULL,
  `correo` varchar(30) NOT NULL,
  `contrasena` varchar(10) NOT NULL,
  `id_ciudades` int(11) NOT NULL,
  `id_rol` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `novedadesempleado`
--

CREATE TABLE `novedadesempleado` (
  `id_novedad` bigint(11) NOT NULL,
  `BPM` varchar(100) DEFAULT NULL,
  `observaciones` varchar(300) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `novedadesempleado`
--

INSERT INTO `novedadesempleado` (`id_novedad`, `BPM`, `observaciones`) VALUES
(1, 'Dotación', 'Cumple con la dotación'),
(2, 'Aseo personal', 'Cumple con el aseo personal');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pedidos`
--

CREATE TABLE `pedidos` (
  `num_pedido` bigint(11) NOT NULL,
  `id_cliente` bigint(11) NOT NULL,
  `id_empleado` bigint(11) NOT NULL,
  `fecha_pedido` date NOT NULL,
  `estado_pedido` varchar(45) NOT NULL,
  `valor_total` bigint(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `permisos`
--

CREATE TABLE `permisos` (
  `id_permisos` bigint(11) NOT NULL,
  `Nombre_permisos` varchar(45) DEFAULT NULL,
  `URL` text DEFAULT NULL,
  `ICON` varchar(45) DEFAULT NULL,
  `permiso_padre` bigint(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `permisos`
--

INSERT INTO `permisos` (`id_permisos`, `Nombre_permisos`, `URL`, `ICON`, `permiso_padre`) VALUES
(1, 'Usuarios', NULL, NULL, NULL),
(2, 'Consultar Usuarios', 'vista/usuarios/listar.xhtml', NULL, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `produccion`
--

CREATE TABLE `produccion` (
  `id_produccion` bigint(11) NOT NULL,
  `id_producto` bigint(11) NOT NULL,
  `lote` int(11) DEFAULT NULL,
  `cantidad` int(11) DEFAULT NULL,
  `FechaElaboracion` date NOT NULL,
  `FechaVencimiento` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

CREATE TABLE `productos` (
  `id_producto` bigint(11) NOT NULL,
  `codBarras` bigint(13) DEFAULT NULL,
  `descripcion` varchar(100) NOT NULL,
  `Valor_unitario` double NOT NULL,
  `iamgenesp` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `productos`
--

INSERT INTO `productos` (`id_producto`, `codBarras`, `descripcion`, `Valor_unitario`, `iamgenesp`) VALUES
(5, 999991, 'Encurtido Habanero 1000ml', 40000, '/Proyecto/archivo/encurtido  habanero 1000.jpg'),
(6, 999992, 'Encurtido Habanero 400ml', 16000, '/Proyecto/archivo/encurtido  habanero 4000.png'),
(7, 999993, 'Encurtido Habanero 500ml', 20000, '/Proyecto/archivo/encurtido  habanero 1000.jpg'),
(8, 999994, 'Salsa Biche 1000ml', 40000, '/Proyecto/archivo/salsa biche1000.png'),
(9, 999995, 'Salsa Picante Habanero 1000ml', 40000, '/Proyecto/archivo/salsa picante habanero 1000.png'),
(10, 999996, 'Salsa Picante Roja 1000ml', 40000, '/Proyecto/archivo/salsa picante roja1000.png'),
(11, 999997, 'Salsa Habanero 230ml', 9000, '/Proyecto/archivo/salsa habanero 230ml.png'),
(12, 999998, 'Salsa Picante Biche 230ml', 10000, '/Proyecto/archivo/salsa picamte biche 230 ml.png'),
(13, 999999, 'Salsa Picante Roja 230ml', 10000, '/Proyecto/archivo/salsapicante roja 230 mil.png'),
(14, 9999991, 'Entero Chile Habanero 1000gr', 30000, '/Proyecto/archivo/chile habanero entero.png'),
(15, 9999992, 'Chile Jalapeño Entero 1000gr', 30000, '/Proyecto/archivo/chile jalapeño entero.png');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos_insumos`
--

CREATE TABLE `productos_insumos` (
  `id_producto` bigint(11) NOT NULL,
  `id_insumos` bigint(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `roles`
--

CREATE TABLE `roles` (
  `id_rol` bigint(11) NOT NULL,
  `Roles` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `roles`
--

INSERT INTO `roles` (`id_rol`, `Roles`) VALUES
(1, 'Administrador'),
(2, 'Jefe de Producción'),
(3, 'Cliente'),
(4, 'Empleado');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `roles_has_permisos`
--

CREATE TABLE `roles_has_permisos` (
  `id_rol` bigint(11) NOT NULL,
  `id_permisos` bigint(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `roles_has_permisos`
--

INSERT INTO `roles_has_permisos` (`id_rol`, `id_permisos`) VALUES
(1, 1),
(1, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tarea`
--

CREATE TABLE `tarea` (
  `id_tarea` bigint(11) NOT NULL,
  `descripcionTarea` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tarea`
--

INSERT INTO `tarea` (`id_tarea`, `descripcionTarea`) VALUES
(1, 'Tajar habaneros'),
(2, 'Empaquetar');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tarea_turnos`
--

CREATE TABLE `tarea_turnos` (
  `id_tarea` bigint(11) NOT NULL,
  `id_turno` bigint(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tarea_turnos`
--

INSERT INTO `tarea_turnos` (`id_tarea`, `id_turno`) VALUES
(2, 1),
(1, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_identificacion`
--

CREATE TABLE `tipo_identificacion` (
  `id_tpidentificacion` bigint(11) NOT NULL,
  `Tipo_id` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tipo_identificacion`
--

INSERT INTO `tipo_identificacion` (`id_tpidentificacion`, `Tipo_id`) VALUES
(1, 'Cédula de ciudadania'),
(2, 'Cédula Extranjera');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `turnos`
--

CREATE TABLE `turnos` (
  `id_turno` bigint(11) NOT NULL,
  `TurnoAsignado` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `turnos`
--

INSERT INTO `turnos` (`id_turno`, `TurnoAsignado`) VALUES
(1, '6 am - 2 pm'),
(2, '2 pm - 10 pm');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `id_usuarios` bigint(11) NOT NULL,
  `Nombre1` varchar(100) DEFAULT NULL,
  `Nombre2` varchar(100) DEFAULT NULL,
  `Apellido1` varchar(100) DEFAULT NULL,
  `Apellido2` varchar(100) DEFAULT NULL,
  `fecha_Nacimiento` date DEFAULT NULL,
  `Direccion` varchar(50) DEFAULT NULL,
  `Telefono` varchar(15) DEFAULT NULL,
  `correo` varchar(30) NOT NULL,
  `contrasena` varchar(10) NOT NULL,
  `id_ciudades` bigint(11) NOT NULL,
  `id_tpidentificacion` bigint(11) NOT NULL,
  `identificacion` bigint(11) NOT NULL,
  `id_rol` bigint(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id_usuarios`, `Nombre1`, `Nombre2`, `Apellido1`, `Apellido2`, `fecha_Nacimiento`, `Direccion`, `Telefono`, `correo`, `contrasena`, `id_ciudades`, `id_tpidentificacion`, `identificacion`, `id_rol`) VALUES
(1, 'Wilmer', 'Andrey', 'Uribe', 'Torres', '1996-11-12', 'calle 56 # 98-23 norte', '7898989', 'wauribe@correo.com', '123456', 1, 1, 1233456712, 1),
(2, 'Pedro', 'Giovanny', 'Hurtado', 'Hurtado', '1996-08-27', 'Carrera 76 # 52B-23 sur', '31333990', 'giocorreo@gmail.com', 'qwerty', 3, 2, 989893434, 4);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `calidad`
--
ALTER TABLE `calidad`
  ADD PRIMARY KEY (`id_calidad`);

--
-- Indices de la tabla `calidad_produccion`
--
ALTER TABLE `calidad_produccion`
  ADD PRIMARY KEY (`id_cali_pro`),
  ADD KEY `id_etapa` (`id_etapa`),
  ADD KEY `id_produccion` (`id_produccion`),
  ADD KEY `id_calidad` (`id_calidad`);

--
-- Indices de la tabla `cargo`
--
ALTER TABLE `cargo`
  ADD PRIMARY KEY (`id_cargo`),
  ADD KEY `id_Turno` (`id_Turno`);

--
-- Indices de la tabla `ciudades`
--
ALTER TABLE `ciudades`
  ADD PRIMARY KEY (`Id_ciudades`);

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`id_cliente`),
  ADD KEY `id_usuarios` (`id_usuarios`);

--
-- Indices de la tabla `detallefactura`
--
ALTER TABLE `detallefactura`
  ADD PRIMARY KEY (`Id_detalle`),
  ADD KEY `id_producto` (`id_producto`),
  ADD KEY `num_pedido` (`num_pedido`);

--
-- Indices de la tabla `empleado`
--
ALTER TABLE `empleado`
  ADD PRIMARY KEY (`id_empleado`),
  ADD KEY `id_cargo` (`id_cargo`),
  ADD KEY `id_usuarios` (`id_usuarios`);

--
-- Indices de la tabla `empleados_novedades`
--
ALTER TABLE `empleados_novedades`
  ADD KEY `id_novedad` (`id_novedad`),
  ADD KEY `id_empleado` (`id_empleado`);

--
-- Indices de la tabla `etapa_produccion`
--
ALTER TABLE `etapa_produccion`
  ADD PRIMARY KEY (`id_etapa`);

--
-- Indices de la tabla `insumos`
--
ALTER TABLE `insumos`
  ADD PRIMARY KEY (`id_insumos`);

--
-- Indices de la tabla `novedadesempleado`
--
ALTER TABLE `novedadesempleado`
  ADD PRIMARY KEY (`id_novedad`);

--
-- Indices de la tabla `pedidos`
--
ALTER TABLE `pedidos`
  ADD PRIMARY KEY (`num_pedido`),
  ADD KEY `id_cliente` (`id_cliente`),
  ADD KEY `id_empleado` (`id_empleado`);

--
-- Indices de la tabla `permisos`
--
ALTER TABLE `permisos`
  ADD PRIMARY KEY (`id_permisos`),
  ADD KEY `permiso_padre` (`permiso_padre`);

--
-- Indices de la tabla `produccion`
--
ALTER TABLE `produccion`
  ADD PRIMARY KEY (`id_produccion`),
  ADD KEY `id_producto` (`id_producto`);

--
-- Indices de la tabla `productos`
--
ALTER TABLE `productos`
  ADD PRIMARY KEY (`id_producto`);

--
-- Indices de la tabla `productos_insumos`
--
ALTER TABLE `productos_insumos`
  ADD KEY `id_insumos` (`id_insumos`),
  ADD KEY `id_producto` (`id_producto`);

--
-- Indices de la tabla `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id_rol`);

--
-- Indices de la tabla `roles_has_permisos`
--
ALTER TABLE `roles_has_permisos`
  ADD KEY `id_rol` (`id_rol`),
  ADD KEY `id_permisos` (`id_permisos`);

--
-- Indices de la tabla `tarea`
--
ALTER TABLE `tarea`
  ADD PRIMARY KEY (`id_tarea`);

--
-- Indices de la tabla `tarea_turnos`
--
ALTER TABLE `tarea_turnos`
  ADD KEY `id_tarea` (`id_tarea`),
  ADD KEY `id_turno` (`id_turno`);

--
-- Indices de la tabla `tipo_identificacion`
--
ALTER TABLE `tipo_identificacion`
  ADD PRIMARY KEY (`id_tpidentificacion`);

--
-- Indices de la tabla `turnos`
--
ALTER TABLE `turnos`
  ADD PRIMARY KEY (`id_turno`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id_usuarios`),
  ADD KEY `id_ciudades` (`id_ciudades`),
  ADD KEY `id_tpidentificacion` (`id_tpidentificacion`),
  ADD KEY `id_rol` (`id_rol`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `calidad`
--
ALTER TABLE `calidad`
  MODIFY `id_calidad` bigint(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `cargo`
--
ALTER TABLE `cargo`
  MODIFY `id_cargo` bigint(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `ciudades`
--
ALTER TABLE `ciudades`
  MODIFY `Id_ciudades` bigint(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `cliente`
--
ALTER TABLE `cliente`
  MODIFY `id_cliente` bigint(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `detallefactura`
--
ALTER TABLE `detallefactura`
  MODIFY `Id_detalle` bigint(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `empleado`
--
ALTER TABLE `empleado`
  MODIFY `id_empleado` bigint(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `etapa_produccion`
--
ALTER TABLE `etapa_produccion`
  MODIFY `id_etapa` bigint(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `insumos`
--
ALTER TABLE `insumos`
  MODIFY `id_insumos` bigint(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `novedadesempleado`
--
ALTER TABLE `novedadesempleado`
  MODIFY `id_novedad` bigint(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `pedidos`
--
ALTER TABLE `pedidos`
  MODIFY `num_pedido` bigint(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `permisos`
--
ALTER TABLE `permisos`
  MODIFY `id_permisos` bigint(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `produccion`
--
ALTER TABLE `produccion`
  MODIFY `id_produccion` bigint(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `productos`
--
ALTER TABLE `productos`
  MODIFY `id_producto` bigint(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT de la tabla `roles`
--
ALTER TABLE `roles`
  MODIFY `id_rol` bigint(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `tarea`
--
ALTER TABLE `tarea`
  MODIFY `id_tarea` bigint(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `tipo_identificacion`
--
ALTER TABLE `tipo_identificacion`
  MODIFY `id_tpidentificacion` bigint(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `turnos`
--
ALTER TABLE `turnos`
  MODIFY `id_turno` bigint(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id_usuarios` bigint(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `calidad_produccion`
--
ALTER TABLE `calidad_produccion`
  ADD CONSTRAINT `calidad_produccion_ibfk_1` FOREIGN KEY (`id_calidad`) REFERENCES `calidad` (`id_calidad`),
  ADD CONSTRAINT `calidad_produccion_ibfk_2` FOREIGN KEY (`id_produccion`) REFERENCES `produccion` (`id_produccion`),
  ADD CONSTRAINT `calidad_produccion_ibfk_3` FOREIGN KEY (`id_etapa`) REFERENCES `etapa_produccion` (`id_etapa`);

--
-- Filtros para la tabla `cargo`
--
ALTER TABLE `cargo`
  ADD CONSTRAINT `cargo_ibfk_1` FOREIGN KEY (`id_Turno`) REFERENCES `turnos` (`id_turno`);

--
-- Filtros para la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD CONSTRAINT `cliente_ibfk_1` FOREIGN KEY (`id_usuarios`) REFERENCES `usuarios` (`id_usuarios`);

--
-- Filtros para la tabla `detallefactura`
--
ALTER TABLE `detallefactura`
  ADD CONSTRAINT `detallefactura_ibfk_1` FOREIGN KEY (`id_producto`) REFERENCES `productos` (`id_producto`),
  ADD CONSTRAINT `detallefactura_ibfk_2` FOREIGN KEY (`num_pedido`) REFERENCES `pedidos` (`num_pedido`);

--
-- Filtros para la tabla `empleado`
--
ALTER TABLE `empleado`
  ADD CONSTRAINT `empleado_ibfk_1` FOREIGN KEY (`id_cargo`) REFERENCES `cargo` (`id_cargo`),
  ADD CONSTRAINT `empleado_ibfk_2` FOREIGN KEY (`id_usuarios`) REFERENCES `usuarios` (`id_usuarios`);

--
-- Filtros para la tabla `empleados_novedades`
--
ALTER TABLE `empleados_novedades`
  ADD CONSTRAINT `empleados_novedades_ibfk_3` FOREIGN KEY (`id_novedad`) REFERENCES `novedadesempleado` (`id_novedad`),
  ADD CONSTRAINT `empleados_novedades_ibfk_4` FOREIGN KEY (`id_empleado`) REFERENCES `empleado` (`id_empleado`);

--
-- Filtros para la tabla `pedidos`
--
ALTER TABLE `pedidos`
  ADD CONSTRAINT `pedidos_ibfk_1` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id_cliente`),
  ADD CONSTRAINT `pedidos_ibfk_2` FOREIGN KEY (`id_empleado`) REFERENCES `empleado` (`id_empleado`);

--
-- Filtros para la tabla `permisos`
--
ALTER TABLE `permisos`
  ADD CONSTRAINT `permisos_ibfk_1` FOREIGN KEY (`permiso_padre`) REFERENCES `permisos` (`id_permisos`);

--
-- Filtros para la tabla `produccion`
--
ALTER TABLE `produccion`
  ADD CONSTRAINT `produccion_ibfk_1` FOREIGN KEY (`id_producto`) REFERENCES `productos` (`id_producto`);

--
-- Filtros para la tabla `productos_insumos`
--
ALTER TABLE `productos_insumos`
  ADD CONSTRAINT `productos_insumos_ibfk_1` FOREIGN KEY (`id_producto`) REFERENCES `productos` (`id_producto`),
  ADD CONSTRAINT `productos_insumos_ibfk_2` FOREIGN KEY (`id_insumos`) REFERENCES `insumos` (`id_insumos`);

--
-- Filtros para la tabla `roles_has_permisos`
--
ALTER TABLE `roles_has_permisos`
  ADD CONSTRAINT `roles_has_permisos_ibfk_1` FOREIGN KEY (`id_permisos`) REFERENCES `permisos` (`id_permisos`),
  ADD CONSTRAINT `roles_has_permisos_ibfk_2` FOREIGN KEY (`id_rol`) REFERENCES `roles` (`id_rol`);

--
-- Filtros para la tabla `tarea_turnos`
--
ALTER TABLE `tarea_turnos`
  ADD CONSTRAINT `tarea_turnos_ibfk_1` FOREIGN KEY (`id_tarea`) REFERENCES `tarea` (`id_tarea`),
  ADD CONSTRAINT `tarea_turnos_ibfk_2` FOREIGN KEY (`id_turno`) REFERENCES `turnos` (`id_turno`);

--
-- Filtros para la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD CONSTRAINT `usuarios_ibfk_1` FOREIGN KEY (`id_ciudades`) REFERENCES `ciudades` (`Id_ciudades`),
  ADD CONSTRAINT `usuarios_ibfk_2` FOREIGN KEY (`id_tpidentificacion`) REFERENCES `tipo_identificacion` (`id_tpidentificacion`),
  ADD CONSTRAINT `usuarios_ibfk_3` FOREIGN KEY (`id_rol`) REFERENCES `roles` (`id_rol`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
