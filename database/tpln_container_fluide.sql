USE egene;

CREATE TABLE `tpln_container_fluide` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value` varchar(1024) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;


insert into `tpln_container_fluide`(`id`,`value`) values (1,'bottle');
insert into `tpln_container_fluide`(`id`,`value`) values (2,'glass');
insert into `tpln_container_fluide`(`id`,`value`) values (3,'pot');
insert into `tpln_container_fluide`(`id`,`value`) values (4,'container');
