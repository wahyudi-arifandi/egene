USE egene;

CREATE TABLE `tplo_decimals_26_2_s` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value` varchar(2048) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;


insert into `tplo_decimals_26_2_s`(`id`,`value`) values (1,'$name bought $itemPlural at $priceBuyKg cents per kg.
#upperFirst($namePronS) sold $weightSellKgTotal kg of them at $priceSellWeightGrTxt per $weightSellGr g.');
