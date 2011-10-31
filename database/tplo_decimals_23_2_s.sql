USE egene;

CREATE TABLE `tplo_decimals_23_2_s` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value` varchar(2048) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;


insert into `tplo_decimals_23_2_s`(`id`,`value`) values (1,'At a music shop, a particular model of good $goodsPl are sold at ${currency}$priceNormal each.
Each defective $goods was sold at ${currency}$priceDefect.
Yesterday the music shop collected a total of ${currency}$saleYesterday from the sale of that model of $goodsPl.
For every $saleTotal $goodsPl sold, $saleDefect were defective.');
