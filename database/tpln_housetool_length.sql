USE egene;

CREATE TABLE `tpln_housetool_length` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value` varchar(1024) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;


insert into `tpln_housetool_length`(`id`,`value`) values (1,'rope');
insert into `tpln_housetool_length`(`id`,`value`) values (2,'wire');
insert into `tpln_housetool_length`(`id`,`value`) values (3,'cable');
insert into `tpln_housetool_length`(`id`,`value`) values (4,'pipe');
insert into `tpln_housetool_length`(`id`,`value`) values (5,'hose');
