USE egene;

CREATE TABLE `tplo_decimals_24_1_q` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value` varchar(2048) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;


insert into `tplo_decimals_24_1_q`(`id`,`value`) values (1,'#upperFirst($name2) bought $name2Item1Count #switchNPS($item1, $name2Item1Count) and $name2Item2Count #switchNPS($item2, $name2Item2Count).
How much did $name2PronS pay for these items?
|| 
#if($name1Item1Count > $name1Item2Count)
Price of ($name1Item2Count $item1 + $name1Item2Count $item2) = $name1Item2Count x $item1Item2PriceD = $item1Item2PriceND<br />
$name1Item1Count - $name1Item2Count = $name1ItemDiff<br />
Price of $name1ItemDiff $item1 = $name1TotalPaidD - $item1Item2PriceND = $itemNPriceD<br />
Price of one $item1 = $itemNPriceD/$name1ItemDiff = $item1PriceD<br />
Price of one $item2 = $item1Item2PriceD - $item1PriceD = $item2PriceD<br />
#else
Price of ($name1Item1Count $item1 + $name1Item1Count $item2) = $name1Item1Count x $item1Item2PriceD = $item1Item2PriceND<br />
$name1Item2Count - $name1Item1Count = $name1ItemDiff<br />
Price of $name1ItemDiff $item2 = $name1TotalPaidD - $item1Item2PriceND = $itemNPriceD<br />
Price of one $item2 = $itemNPriceD/$name1ItemDiff = $item2PriceD<br />
Price of one $item1 = $item1Item2PriceD - $item2PriceD = $item1PriceD<br />
#end
Price of $name2Item1Count #switchNPS($item1, $name2Item1Count) and $name2Item2Count #switchNPS($item2, $name2Item2Count) = ($name2Item1Count x $item1PriceD) + ($name2Item2Count x $item2PriceD) = $name2TotalPaidD<br />
#upperFirst($name2) paid ${currency}$name2TotalPaidD for $name2Item1Count #switchNPS($item1, $name2Item1Count) and $name2Item2Count #switchNPS($item2, $name2Item2Count).
||
$name2TotalPaidD');
insert into `tplo_decimals_24_1_q`(`id`,`value`) values (2,'#upperFirst($name2) had ${currency}$name2TotalPaidD.
#upperFirst($name2PronS) bought $name2Item1Count #switchNPS($item1, $name2Item1Count) and used the rest of $name2PossA money to buy $item2P.
How many $vNounFormatter.genPlural($item2) did $name2PronS buy?
|| 
#if($name1Item1Count > $name1Item2Count)
Price of ($name1Item2Count $item1 + $name1Item2Count $item2) = $name1Item2Count x $item1Item2PriceD = $item1Item2PriceND<br />
$name1Item1Count - $name1Item2Count = $name1ItemDiff<br />
Price of $name1ItemDiff $item1 = $name1TotalPaidD - $item1Item2PriceND = $itemNPriceD<br />
Price of one $item1 = $itemNPriceD/$name1ItemDiff = $item1PriceD<br />
Price of one $item2 = $item1Item2PriceD - $item1PriceD = $item2PriceD<br />
#else
Price of ($name1Item1Count $item1 + $name1Item1Count $item2) = $name1Item1Count x $item1Item2PriceD = $item1Item2PriceND<br />
$name1Item2Count - $name1Item1Count = $name1ItemDiff<br />
Price of $name1ItemDiff $item2 = $name1TotalPaidD - $item1Item2PriceND = $itemNPriceD<br />
Price of one $item2 = $itemNPriceD/$name1ItemDiff = $item2PriceD<br />
Price of one $item1 = $item1Item2PriceD - $item2PriceD = $item1PriceD<br />
#end
Money spent by $name2 for buying $item2(s) = $name2TotalPaidD - ($name2Item1Count * $item1PriceD) = $name2Item2NPriceD<br />
Number of $item2(s) bought by $name2 = $name2Item2NPriceD/$item2PriceD = $name2Item2Count<br />
$name2 bought $name2Item2Count #switchNPS($item2, $name2Item2Count).
||
$name2Item2Count');
insert into `tplo_decimals_24_1_q`(`id`,`value`) values (3,'#set($name2MoneyMin = $name2TotalPaid)
#set($name2MoneyMax = 1000 + $name2TotalPaid)
#set($name2Money = $vNumberGenerator.genIntMul($name2MoneyMin, $name2MoneyMax, [1000]))
#set($name2MoneyD = $mathTool.toDouble($name2Money) / 100)
#set($name2MoneyD = $vFormatter.formatDecimal($name2MoneyD, 2))
#set($answer = $name2Money - $name2TotalPaid)
#set($answerD = $mathTool.toDouble($answer) / 100)
#set($answerD = $vFormatter.formatDecimal($answerD, 2))
#upperFirst($name2) bought $name2Item1Count #switchNPS($item1, $name2Item1Count) and $name2Item2Count #switchNPS($item2, $name2Item2Count).
How much change would $name2PronS receive if $name2PronS paid the cashier ${currency}$name2MoneyD
|| 
#if($name1Item1Count > $name1Item2Count)
Price of ($name1Item2Count $item1 + $name1Item2Count $item2) = $name1Item2Count x $item1Item2PriceD = $item1Item2PriceND<br />
$name1Item1Count - $name1Item2Count = $name1ItemDiff<br />
Price of $name1ItemDiff $item1 = $name1TotalPaidD - $item1Item2PriceND = $itemNPriceD<br />
Price of one $item1 = $itemNPriceD/$name1ItemDiff = $item1PriceD<br />
Price of one $item2 = $item1Item2PriceD - $item1PriceD = $item2PriceD<br />
#else
Price of ($name1Item1Count $item1 + $name1Item1Count $item2) = $name1Item1Count x $item1Item2PriceD = $item1Item2PriceND<br />
$name1Item2Count - $name1Item1Count = $name1ItemDiff<br />
Price of $name1ItemDiff $item2 = $name1TotalPaidD - $item1Item2PriceND = $itemNPriceD<br />
Price of one $item2 = $itemNPriceD/$name1ItemDiff = $item2PriceD<br />
Price of one $item1 = $item1Item2PriceD - $item2PriceD = $item1PriceD<br />
#end
Price of $name2Item1Count #switchNPS($item1, $name2Item1Count) and $name2Item2Count #switchNPS($item2, $name2Item2Count) = ($name2Item1Count x $item1PriceD) + ($name2Item2Count x $item2PriceD) = $name2TotalPaidD<br />
$name2MoneyD - $name2TotalPaidD = $answerD<br />
$name2 received change ${currency}$answerD
||
$answerD');
