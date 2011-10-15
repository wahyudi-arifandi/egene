USE egene;

CREATE TABLE `tplo_decimals_25_1_s1` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value` varchar(2048) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;


insert into `tplo_decimals_25_1_s1`(`id`,`value`) values (1,'#upperFirst($name) is a $profession. #evaluate($vTemplateGenerator.retrieveTpl("tplo_decimals_25_1_s11")) #evaluate($vTemplateGenerator.retrieveTpl("tplo_decimals_25_1_s12")) In a $periodMonth#cminus()month period, the total sales $namePronS made was ${currency}$nMonthSalesD.');
