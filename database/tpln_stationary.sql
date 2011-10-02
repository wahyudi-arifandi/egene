USE egene;

CREATE TABLE `tpln_stationary` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value` varchar(1024) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;


insert into `tpln_stationary`(`id`,`value`) values (1,'pen');
insert into `tpln_stationary`(`id`,`value`) values (2,'file');
insert into `tpln_stationary`(`id`,`value`) values (3,'pencil');
insert into `tpln_stationary`(`id`,`value`) values (4,'mechanical pencil');
insert into `tpln_stationary`(`id`,`value`) values (5,'ruler');
insert into `tpln_stationary`(`id`,`value`) values (6,'writing pad');
insert into `tpln_stationary`(`id`,`value`) values (7,'greeting card');
insert into `tpln_stationary`(`id`,`value`) values (8,'birthday card');
