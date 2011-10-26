USE egene;

CREATE TABLE `tplo_decimals_24_1_s` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value` varchar(2048) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;


insert into `tplo_decimals_24_1_s`(`id`,`value`) values (1,'#upperFirst($name1) paid ${currency}$name1TotalPaidD for $name1Item1Count #switchNPS($item1, $name1Item2Count) and $name1Item2Count #switchNPS($item2, $name1Item1Count).
The total cost of a $item1 and a $item2 was ${currency}$item1Item2PriceD.');
