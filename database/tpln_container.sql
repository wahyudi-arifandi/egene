USE egene;

CREATE TABLE `tpln_container` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value` varchar(1024) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;


insert into `tpln_container`(`id`,`value`) values (1,'box');
insert into `tpln_container`(`id`,`value`) values (2,'carton');
insert into `tpln_container`(`id`,`value`) values (3,'bag');
insert into `tpln_container`(`id`,`value`) values (4,'container');
