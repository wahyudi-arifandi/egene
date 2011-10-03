USE egene;

CREATE TABLE `tpln_book` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value` varchar(1024) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;


insert into `tpln_book`(`id`,`value`) values (1,'dictionary');
insert into `tpln_book`(`id`,`value`) values (2,'phone directory');
insert into `tpln_book`(`id`,`value`) values (3,'notebook');
insert into `tpln_book`(`id`,`value`) values (4,'exercise book');
insert into `tpln_book`(`id`,`value`) values (5,'novel');
