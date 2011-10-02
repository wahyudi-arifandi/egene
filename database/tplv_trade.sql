USE egene;

CREATE TABLE `tplv_trade` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value` varchar(1024) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;


insert into `tplv_trade`(`id`,`value`) values (1,'sell');
insert into `tplv_trade`(`id`,`value`) values (2,'pay');
insert into `tplv_trade`(`id`,`value`) values (3,'buy');
insert into `tplv_trade`(`id`,`value`) values (4,'trade');
