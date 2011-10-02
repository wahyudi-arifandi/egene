USE egene;

CREATE TABLE `tplo_decimals_25_3_s11` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value` varchar(2048) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;


insert into `tplo_decimals_25_3_s11`(`id`,`value`) values (1,'$name wanted to pack $packTotalWeightKg kg of $groceries into $packCountTotal packets.');
insert into `tplo_decimals_25_3_s11`(`id`,`value`) values (2,'$packTotalWeightKg kg of $groceries needed to be packed into $packCountTotal packets.');
