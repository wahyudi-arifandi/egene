USE egene;

CREATE TABLE `tpln_meat` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value` varchar(1024) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;


insert into `tpln_meat`(`id`,`value`) values (1,'shark meat');
insert into `tpln_meat`(`id`,`value`) values (2,'lamb');
insert into `tpln_meat`(`id`,`value`) values (3,'mutton');
insert into `tpln_meat`(`id`,`value`) values (4,'chicken');
insert into `tpln_meat`(`id`,`value`) values (5,'beef');
