USE egene;

CREATE TABLE `tplo_decimals_26_1_s` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value` varchar(2048) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;


insert into `tplo_decimals_26_1_s`(`id`,`value`) values (1,'$name bought $itemPlural at ${currency}${priceBuyKg}.00 per kg.
#upperFirst($namePronS) sold $weightSellGrTotal kg of them at ${currency}$priceSellWeightGr per $weightSellGr g.');
