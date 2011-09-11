

CREATE TABLE `tpln_profession` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value` varchar(1024) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;


insert into `tpln_profession`(`id`,`value`) values (1,'car agent');
insert into `tpln_profession`(`id`,`value`) values (2,'property agent');
insert into `tpln_profession`(`id`,`value`) values (3,'salesgirl');
insert into `tpln_profession`(`id`,`value`) values (4,'salesman');
insert into `tpln_profession`(`id`,`value`) values (5,'travel agent');
