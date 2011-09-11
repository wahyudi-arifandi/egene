

CREATE TABLE `tplo_decimals_22_3_s` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value` varchar(1024) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;


insert into `tplo_decimals_22_3_s`(`id`,`value`) values (1,'The total cost of $item1Count #switchNPS($item1, $item1Count) and $item2Count #switchNPS($item2, $item2Count) is $escapeTool.getD()$vFormatter.formatDecimal($total, 2).
#upperFirst($vNumberGenerator.toEnglishWords($item1Ratio)) #switchNPS($item1, $item1Ratio) #switchVPS("cost", $item1Ratio) as much as as $item2Ratio #switchNPS($item2, $item2Ratio).');
insert into `tplo_decimals_22_3_s`(`id`,`value`) values (2,'$name paid $escapeTool.getD()$vFormatter.formatDecimal($total, 2) for $item1Count #switchNPS($item1, $item1Count) and $item2Count #switchNPS($item2, $item2Count).
#upperFirst($vNumberGenerator.toEnglishWords($item1Ratio)) #switchNPS($item1, $item1Ratio) #switchVPS("cost", $item1Ratio) as much as $item2Ratio #switchNPS($item2, $item2Ratio).');
