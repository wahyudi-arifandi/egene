

CREATE TABLE `tplo_decimals_22_1_s` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value` varchar(1024) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;


insert into `tplo_decimals_22_1_s`(`id`,`value`) values (1,'The total mass of $item1Count #switchNPS($item1, $item1Count) and $item2Count #switchNPS($item2, $item2Count) is $mathTool.roundTo(2, $total) kg.
#upperFirst($vNumberGenerator.toEnglishWords($item1Ratio)) #switchNPS($item1, $item1Ratio) #switchVPS("have", $item1Ratio) the same mass as $item2Ratio #switchNPS($item2, $item2Ratio).');
