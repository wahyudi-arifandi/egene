

CREATE TABLE `tpln_coin_place` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value` varchar(1024) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;


insert into `tpln_coin_place`(`id`,`value`) values (1,'in a coin box');
insert into `tpln_coin_place`(`id`,`value`) values (2,'on a table');
insert into `tpln_coin_place`(`id`,`value`) values (3,'in a pocket');
insert into `tpln_coin_place`(`id`,`value`) values (4,'in a drawer');
