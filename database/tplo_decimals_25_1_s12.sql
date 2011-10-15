USE egene;

CREATE TABLE `tplo_decimals_25_1_s12` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value` varchar(2048) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;


insert into `tplo_decimals_25_1_s12`(`id`,`value`) values (1,'#upperFirst($namePronS) also receives a commission of ${currency}$commissionD for every ${currency}$commBasic $namePronS makes.');
insert into `tplo_decimals_25_1_s12`(`id`,`value`) values (2,'At the same time, $namePronS also receives a commission of ${currency}$commissionD for every ${currency}$commBasic of sales $namePronS makes.');
insert into `tplo_decimals_25_1_s12`(`id`,`value`) values (3,'#upperFirst($namePronS) also receives a bonus of ${currency}$commissionD for every ${currency}$commBasic $namePronS makes.');
insert into `tplo_decimals_25_1_s12`(`id`,`value`) values (4,'At the same time, $namePronS also receives a bonus of ${currency}$commissionD for every ${currency}$commBasic of sales $namePronS makes.');
