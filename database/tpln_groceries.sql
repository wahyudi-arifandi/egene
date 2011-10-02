USE egene;

CREATE TABLE `tpln_groceries` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value` varchar(1024) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;


insert into `tpln_groceries`(`id`,`value`) values (1,'sugar');
insert into `tpln_groceries`(`id`,`value`) values (2,'salt');
insert into `tpln_groceries`(`id`,`value`) values (3,'vegetable oil');
insert into `tpln_groceries`(`id`,`value`) values (4,'flour');
insert into `tpln_groceries`(`id`,`value`) values (5,'tapioca');
insert into `tpln_groceries`(`id`,`value`) values (6,'rice');
insert into `tpln_groceries`(`id`,`value`) values (7,'butter');
insert into `tpln_groceries`(`id`,`value`) values (8,'margarine');
