USE egene;

CREATE TABLE `tplo_decimals_28_1_112` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value` varchar(2048) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;


insert into `tplo_decimals_28_1_112`(`id`,`value`) values (1,'How much did $namePronS spend on the $item2?
|| 
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
<br />
price of $item1 = total - ($ratioItem1.get(0)/$ratioItem1.get(1)) x total<br />
total = ($ratioItem1.get(1)/$ratioItem1.get(0)) x (price of $item1)<br />
total = ($ratioItem1.get(1)/$ratioItem1.get(0)) x ($priceItem1) = ${currency}$totalMoney<br />
remaining = total - (price of $item1) = $totalMoney - $priceItem1 = $remaining1<br />
price of $item2 + price of $item3 = ($ratioNum/$ratioDenom) x $remaining1 = $priceItem23<br />
price of $item2 : price of $item3 = $priceItem2Mul : 1<br />
price of $item2 = ($priceItem2Mul / ($priceItem2Mul + 1)) x $priceItem23 = $priceItem2<br />
$name spent ${currency}$priceItem2 on the $item2');
insert into `tplo_decimals_28_1_112`(`id`,`value`) values (2,'(a) What fraction of $namePossAdj money did $name spend on the $item1?<br />
(b) How much did $namePronS spend on the $item2?
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
$name spent ${currency}$priceItem2 on the $item2');
