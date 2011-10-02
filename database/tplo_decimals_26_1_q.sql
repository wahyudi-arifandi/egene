USE egene;

CREATE TABLE `tplo_decimals_26_1_q` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value` varchar(2048) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;


insert into `tplo_decimals_26_1_q`(`id`,`value`) values (1,'How much money did $namePronS earn?
||
Number of money collected by $name from the $itemPlural $namePronS sold = ($weightSellGrTotal / $priceSellWeightGr) x $priceSellWeightGr = ${currency}$revenue.<br />
#upperFirst($namePronS) earned = $revenue - ($weightSellGrTotal x $priceBuyKg) = ${currency}$profit');
