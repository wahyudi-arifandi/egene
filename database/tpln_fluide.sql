USE egene;

CREATE TABLE `tpln_fluide` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value` varchar(1024) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;


insert into `tpln_fluide`(`id`,`value`) values (1,'water');
insert into `tpln_fluide`(`id`,`value`) values (2,'sugar');
insert into `tpln_fluide`(`id`,`value`) values (3,'oil');
insert into `tpln_fluide`(`id`,`value`) values (4,'salt');
insert into `tpln_fluide`(`id`,`value`) values (5,'soil');
