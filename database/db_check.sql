

CREATE TABLE `db_check` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `foo` varchar(25) DEFAULT NULL,
  `bar` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;


insert into `db_check`(`id`,`foo`,`bar`) values (1,'hello',12345);
