USE egene;

CREATE TABLE `tpln_courier` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value` varchar(1024) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;


insert into `tpln_courier`(`id`,`value`) values (1,'postal');
insert into `tpln_courier`(`id`,`value`) values (2,'courier');
insert into `tpln_courier`(`id`,`value`) values (3,'delivery');
