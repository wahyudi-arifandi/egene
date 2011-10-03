USE egene;

CREATE TABLE `tplo_decimals_28_1_121` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value` varchar(2048) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;


insert into `tplo_decimals_28_1_121`(`id`,`value`) values (1,'#upperFirst($namePronS) spent $ratioNum/$ratioDenom of $namePossAdj remaining money on some $item2Plural and $item3Plural;
after which, half of $namePossAdj money was left.
The amount of money $namePronS spent on the $item2Plural was $priceItem2Mul times the amount $namePronS spent on the $item3Plural.<br />');
