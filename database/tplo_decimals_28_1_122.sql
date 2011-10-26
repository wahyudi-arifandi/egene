USE egene;

CREATE TABLE `tplo_decimals_28_1_122` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value` varchar(2048) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;


insert into `tplo_decimals_28_1_122`(`id`,`value`) values (1,'(a) What fraction of $namePossAdj money did $name spend on the $item1?<br />
(b) If $namePronS bought $item3Count $item3Plural, how much did $namePronS pay for each $item3?
|| 
(a)<br />
Since<br /> 
1 - $ratioNum/$ratioDenom = $diffDenomNum/$ratioDenom<br />
$diffDenomNum/$ratioDenom of $namePossAdj remaining money after spending for $item1 is half of total $namePossAdj money.<br />
$diffDenomNumDouble/$ratioDenom of $namePossAdj remaining money after spending for $item1 is total of $namePossAdj money.<br />
total = ($diffDenomNumDouble/$ratioDenom) x remaining<br />
and<br />
remaining = total - (price of $item1)<br />
Hence<br />
total = ($diffDenomNumDouble/$ratioDenom) x (total - (price of $item1))<br />
($ratioDenom/$diffDenomNumDouble) x total = total - (price of $item1)<br />
price of $item1 = total - ($ratioDenom/$diffDenomNumDouble) x total<br />
price of $item1 = $diff3/$diffDenomNumDouble x total<br />
So, $name spent $ratioItem1.get(0)/$ratioItem1.get(1) of $namePossAdj money on $item1<br />
(b)<br />
price of $item1 = ($ratioItem1.get(0)/$ratioItem1.get(1)) x total<br />
total = ($ratioItem1.get(1)/$ratioItem1.get(0)) x (price of $item1)<br />
total = ($ratioItem1.get(1)/$ratioItem1.get(0)) x ($priceItem1) = ${currency}$totalMoney<br />
remaining = total - (price of $item1) = $totalMoney - $priceItem1 = $remaining1<br />
price of $item2 + price of $item3 = ($ratioNum/$ratioDenom) x $remaining1 = $priceItem23<br />
price of $item2 : price of $item3 = $priceItem2Mul : 1<br />
price of $item3 = (1 / ($priceItem2Mul + 1)) x $priceItem23 = $priceItem3<br />
$name spent ${currency}$priceItem3 on the $item3Plural.<br />
Since $namePronS bought $item3Count $item3Plural, hence:<br />
#upperFirst($namePronS) paid for each $item3 = $priceItem3 / $item3Count = ${currency}$priceItem3x
||
$ratioItem1.get(0)/$ratioItem1.get(1)|$priceItem3x');
insert into `tplo_decimals_28_1_122`(`id`,`value`) values (2,'(a) What fraction of $namePossAdj money did $name spend on the $item1?<br />
(b) If each $item2 cost ${currency}$priceItem2x, how many $item2Plural did $namePronS he buy?
|| 
(a)<br />
Since<br /> 
1 - $ratioNum/$ratioDenom = $diffDenomNum/$ratioDenom<br />
$diffDenomNum/$ratioDenom of $namePossAdj remaining money after spending for $item1 is half of total $namePossAdj money.<br />
$diffDenomNumDouble/$ratioDenom of $namePossAdj remaining money after spending for $item1 is total of $namePossAdj money.<br />
total = ($diffDenomNumDouble/$ratioDenom) x remaining<br />
and<br />
remaining = total - (price of $item1)<br />
Hence<br />
total = ($diffDenomNumDouble/$ratioDenom) x (total - (price of $item1))<br />
($ratioDenom/$diffDenomNumDouble) x total = total - (price of $item1)<br />
price of $item1 = total - ($ratioDenom/$diffDenomNumDouble) x total<br />
price of $item1 = $diff3/$diffDenomNumDouble x total<br />
So, $name spent $ratioItem1.get(0)/$ratioItem1.get(1) of $namePossAdj money on $item1<br />
(b)<br />
price of $item1 = ($ratioItem1.get(0)/$ratioItem1.get(1)) x total<br />
total = ($ratioItem1.get(1)/$ratioItem1.get(0)) x (price of $item1)<br />
total = ($ratioItem1.get(1)/$ratioItem1.get(0)) x ($priceItem1) = ${currency}$totalMoney<br />
remaining = total - (price of $item1) = $totalMoney - $priceItem1 = $remaining1<br />
price of $item2 + price of $item3 = ($ratioNum/$ratioDenom) x $remaining1 = $priceItem23<br />
price of $item2 : price of $item3 = $priceItem2Mul : 1<br />
price of $item2 = ($priceItem2Mul / ($priceItem2Mul + 1)) x $priceItem23 = $priceItem2<br />
$name spent ${currency}$priceItem2 on the $item2Plural.<br />
Since each $item2 cost ${currency}$priceItem2x, hence:<br />
Number of $item2Plural that $namePronS bought = $priceItem2 / $priceItem2x = $item2Count
||
$ratioItem1.get(0)/$ratioItem1.get(1)|$item2Count');
