-- phpMyAdmin SQL Dump
-- version 2.10.2
-- http://www.phpmyadmin.net
-- 
-- Servidor: localhost
-- Tiempo de generación: 06-07-2015 a las 16:57:32
-- Versión del servidor: 5.0.45
-- Versión de PHP: 5.2.3

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";

-- 
-- Base de datos: `carritobd`
-- 

-- --------------------------------------------------------

-- 
-- Estructura de tabla para la tabla `t_carrito`
-- 

DROP TABLE IF EXISTS `t_carrito`;
CREATE TABLE IF NOT EXISTS `t_carrito` (
  `id_carrito` int(11) NOT NULL auto_increment,
  `id_usuaio` int(11) default NULL,
  `feha_carrito` date default NULL,
  `nota_carrito` varchar(200) default NULL,
  `estado_carrito` int(11) default NULL,
  PRIMARY KEY  (`id_carrito`),
  KEY `id_usuaio` (`id_usuaio`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- 
-- Volcar la base de datos para la tabla `t_carrito`
-- 


-- --------------------------------------------------------

-- 
-- Estructura de tabla para la tabla `t_carrito_detalle`
-- 

DROP TABLE IF EXISTS `t_carrito_detalle`;
CREATE TABLE IF NOT EXISTS `t_carrito_detalle` (
  `id_carrito` int(11) NOT NULL,
  `id_producto` int(11) NOT NULL,
  `cantidad_carrito_detalle` int(11) NOT NULL,
  `id_carrito_detalle` int(11) NOT NULL auto_increment,
  PRIMARY KEY  (`id_carrito_detalle`),
  KEY `id_carrito` (`id_carrito`,`id_producto`),
  KEY `id_producto` (`id_producto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- 
-- Volcar la base de datos para la tabla `t_carrito_detalle`
-- 


-- --------------------------------------------------------

-- 
-- Estructura de tabla para la tabla `t_categorias`
-- 

DROP TABLE IF EXISTS `t_categorias`;
CREATE TABLE IF NOT EXISTS `t_categorias` (
  `id_categoria` int(11) NOT NULL auto_increment,
  `nombre_categoria` varchar(100) default NULL,
  PRIMARY KEY  (`id_categoria`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- 
-- Volcar la base de datos para la tabla `t_categorias`
-- 


-- --------------------------------------------------------

-- 
-- Estructura de tabla para la tabla `t_productos`
-- 

DROP TABLE IF EXISTS `t_productos`;
CREATE TABLE IF NOT EXISTS `t_productos` (
  `id_producto` int(11) NOT NULL auto_increment,
  `cantidad_producto` int(11) default NULL,
  `precio_producto` double(12,2) default NULL,
  `nombre_producto` varchar(150) default NULL,
  `descripcion_producto` varchar(150) default NULL,
  `id_categoria` int(11) default NULL,
  `id_proveedor` int(11) default NULL,
  `imagen_producto` varchar(500) default NULL,
  PRIMARY KEY  (`id_producto`),
  KEY `id_categoria` (`id_categoria`,`id_proveedor`),
  KEY `id_proveedor` (`id_proveedor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- 
-- Volcar la base de datos para la tabla `t_productos`
-- 


-- --------------------------------------------------------

-- 
-- Estructura de tabla para la tabla `t_proveedores`
-- 

DROP TABLE IF EXISTS `t_proveedores`;
CREATE TABLE IF NOT EXISTS `t_proveedores` (
  `id_proveedor` int(11) NOT NULL auto_increment,
  `nombre_proveedor` varchar(100) default NULL,
  `nit_proveedor` varchar(18) default NULL,
  `nrc_proveedor` varchar(10) default NULL,
  `direccion_proveedor` varchar(255) default NULL,
  `telefono_proveedor` varchar(9) default NULL,
  PRIMARY KEY  (`id_proveedor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- 
-- Volcar la base de datos para la tabla `t_proveedores`
-- 


-- --------------------------------------------------------

-- 
-- Estructura de tabla para la tabla `t_usuarios`
-- 

DROP TABLE IF EXISTS `t_usuarios`;
CREATE TABLE IF NOT EXISTS `t_usuarios` (
  `id_usuario` int(11) NOT NULL auto_increment,
  `nombre_usuario` varchar(150) default NULL,
  `nickname_usuario` varchar(100) default NULL,
  `correo_usuario` varchar(100) default NULL,
  `rol_usuario` varchar(15) default NULL,
  `estado_usuario` int(11) default NULL,
  `pass_usuario` varchar(100) default NULL,
  PRIMARY KEY  (`id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- 
-- Volcar la base de datos para la tabla `t_usuarios`
-- 


-- 
-- Filtros para las tablas descargadas (dump)
-- 

-- 
-- Filtros para la tabla `t_carrito`
-- 
ALTER TABLE `t_carrito`
  ADD CONSTRAINT `t_carrito_ibfk_1` FOREIGN KEY (`id_usuaio`) REFERENCES `t_usuarios` (`id_usuario`) ON DELETE CASCADE ON UPDATE CASCADE;

-- 
-- Filtros para la tabla `t_carrito_detalle`
-- 
ALTER TABLE `t_carrito_detalle`
  ADD CONSTRAINT `t_carrito_detalle_ibfk_2` FOREIGN KEY (`id_producto`) REFERENCES `t_productos` (`id_producto`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `t_carrito_detalle_ibfk_1` FOREIGN KEY (`id_carrito`) REFERENCES `t_carrito` (`id_carrito`) ON DELETE CASCADE ON UPDATE CASCADE;

-- 
-- Filtros para la tabla `t_productos`
-- 
ALTER TABLE `t_productos`
  ADD CONSTRAINT `t_productos_ibfk_1` FOREIGN KEY (`id_categoria`) REFERENCES `t_categorias` (`id_categoria`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `t_productos_ibfk_2` FOREIGN KEY (`id_proveedor`) REFERENCES `t_proveedores` (`id_proveedor`) ON DELETE CASCADE ON UPDATE CASCADE;
