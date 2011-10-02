USE egene;

CREATE TABLE `tplo_decimals_25_3_s1` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value` varchar(2048) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;


insert into `tplo_decimals_25_3_s1`(`id`,`value`) values (3,'#evaluate($vTemplateGenerator.retrieveTpl("tplo_decimals_25_3_s11")) ${pack1RatioPer10}/10 of the $groceries was packed equally into $pack1Count packets.
The ramaining $groceries was packed into $cmp packets.');
