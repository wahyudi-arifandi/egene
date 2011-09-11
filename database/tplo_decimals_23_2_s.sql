

CREATE TABLE `tplo_decimals_23_2_s` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value` varchar(1024) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;


insert into `tplo_decimals_23_2_s`(`id`,`value`) values (1,'At a music shop, a particular model of good $vNounFormatter.genPlural($goods) are sold at $escapeTool.getD()$vFormatter.formatDecimal($priceNormal, 2) each.
Each defective $goods was sold at $escapeTool.getD()$vFormatter.formatDecimal($priceDefect, 2).
Yesterday the music shop collected a total of $escapeTool.getD()$vFormatter.formatDecimal($saleYesterday, 2) from the sale of that model of $vNounFormatter.genPlural($goods).
For every $saleTotal $vNounFormatter.genPlural($goods) sold, $saleDefect were defective.');
