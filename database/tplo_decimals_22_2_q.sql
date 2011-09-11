

CREATE TABLE `tplo_decimals_22_2_q` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value` varchar(1024) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;


insert into `tplo_decimals_22_2_q`(`id`,`value`) values (1,'#set($item1CountQ = $vNumberGenerator.genInt(1, 15))
#set($item2CountQ = $vNumberGenerator.genInt(1, 15))
<br />(a) Find the length of a $item1.<br />
(b) Find the total length of $item1CountQ #switchNPS($item1, $item1CountQ) and $item2CountQ #switchNPS($item2, $item2CountQ).
#set($answerB = ($item1CountQ * $item1W) + ($item2CountQ * $item2W))
||(a) $item1W m<br />(b) $mathTool.roundTo(2, $answerB) m');
insert into `tplo_decimals_22_2_q`(`id`,`value`) values (2,'Find the differences in length between a $item1 and $item2.
#set($delta = $item1W - $item2W)
#set($answer2 = $mathTool.abs($delta))
||$mathTool.roundTo(2, $answer2) m');
