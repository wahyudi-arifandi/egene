﻿USE egene;

CREATE TABLE `tplo_decimals_25_1_s11` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value` varchar(2048) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;


insert into `tplo_decimals_25_1_s11`(`id`,`value`) values (1,'#upperFirst($vNounFormatter.genPronSubjGender($name, $nameGender)) receives a fixed salary $escapeTool.getD()$vFormatter.formatDecimal($fixSalaryD, 2) each month.');
insert into `tplo_decimals_25_1_s11`(`id`,`value`) values (2,'#upperFirst($vNounFormatter.genPronSubjGender($name, $nameGender)) receives a fixed salary $escapeTool.getD()$vFormatter.formatDecimal($fixSalaryD, 2) a month.');
