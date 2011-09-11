

CREATE TABLE `tpln_dress_length` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value` varchar(1024) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;


insert into `tpln_dress_length`(`id`,`value`) values (1,'belt');
insert into `tpln_dress_length`(`id`,`value`) values (2,'shoe lace');
insert into `tpln_dress_length`(`id`,`value`) values (3,'tie');
