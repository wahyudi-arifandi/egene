USE egene;

CREATE TABLE `tplo_decimals_28_1_1` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value` varchar(2048) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;


insert into `tplo_decimals_28_1_1`(`id`,`value`) values (1,'#evaluate($vTemplateGenerator.retrieveTpl("tplo_decimals_28_1_111"))
#evaluate($vTemplateGenerator.retrieveTpl("tplo_decimals_28_1_112"))');
insert into `tplo_decimals_28_1_1`(`id`,`value`) values (2,'#evaluate($vTemplateGenerator.retrieveTpl("tplo_decimals_28_1_121"))
#evaluate($vTemplateGenerator.retrieveTpl("tplo_decimals_28_1_122"))');
