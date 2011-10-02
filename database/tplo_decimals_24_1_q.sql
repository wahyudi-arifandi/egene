USE egene;

CREATE TABLE `tplo_decimals_24_1_q` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value` varchar(2048) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;


insert into `tplo_decimals_24_1_q`(`id`,`value`) values (1,'#upperFirst($name2) bought $name2Item1Count #switchNPS($item1, $name2Item1Count) and $name2Item2Count #switchNPS($item2, $name2Item2Count).
How much did $vNounFormatter.genPronSubjGender($name2, $name2Gender) pay for these items?
|| #upperFirst($name2) paid $escapeTool.getD()$vFormatter.formatDecimal($name2TotalPaidD, 2) for $name2Item1Count #switchNPS($item1, $name2Item1Count) and $name2Item2Count #switchNPS($item2, $name2Item2Count).');
insert into `tplo_decimals_24_1_q`(`id`,`value`) values (2,'#upperFirst($name2) had $escapeTool.getD()$vFormatter.formatDecimal($name2TotalPaidD, 2).
#upperFirst($vNounFormatter.genPronSubjGender($name2, $name2Gender)) bought $name2Item1Count #switchNPS($item1, $name2Item1Count) and used the rest of $vNounFormatter.genPossAdjGender($name2, $name2Gender) money to buy $vNounFormatter.genPlural($item2).
How many $vNounFormatter.genPlural($item2) did $vNounFormatter.genPronSubjGender($name2, $name2Gender) buy?
|| $name2 bought $name2Item2Count #switchNPS($item2, $name2Item2Count).');
insert into `tplo_decimals_24_1_q`(`id`,`value`) values (3,'#set($name2MoneyMin = $name2TotalPaid)
#set($name2MoneyMax = 1000 + $name2TotalPaid)
#set($name2Money = $vNumberGenerator.genIntMul($name2MoneyMin, $name2MoneyMax, [1000]))
#set($name2MoneyD = $mathTool.toDouble($name2Money) / 100)
#set($answer = $name2Money - $name2TotalPaid)
#set($answerD = $mathTool.toDouble($answer) / 100)
#upperFirst($name2) bought $name2Item1Count #switchNPS($item1, $name2Item1Count) and $name2Item2Count #switchNPS($item2, $name2Item2Count).
How much change would $vNounFormatter.genPronSubjGender($name2, $name2Gender) receive if $vNounFormatter.genPronSubjGender($name2, $name2Gender) paid the cashier $escapeTool.getD()$vFormatter.formatDecimal($name2MoneyD, 2)
|| $name2 received change $escapeTool.getD()$vFormatter.formatDecimal($answerD, 2)');
