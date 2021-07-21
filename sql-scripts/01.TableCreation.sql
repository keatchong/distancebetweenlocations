CREATE DATABASE distances;

use distances;

CREATE TABLE `postcodelatlng` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `postcode` varchar(8) NOT NULL,
  `latitude` decimal(12,9) NOT NULL,
  `longitude` decimal(12,9) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `requestlog` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `postcode1` varchar(8) NOT NULL,
  `postcode2` varchar(8) NOT NULL,
  `requestdatetime` datetime NOT NULL,
  PRIMARY KEY (`id`)
);






