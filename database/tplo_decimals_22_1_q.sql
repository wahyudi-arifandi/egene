USE egene;

CREATE TABLE `tplo_decimals_22_1_q` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value` varchar(2048) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;


insert into `tplo_decimals_22_1_q`(`id`,`value`) values (1,'#set($item1CountQ = $vNumberGenerator.genInt(1, 15))
#set($item2CountQ = $vNumberGenerator.genInt(1, 15))
<br />(a) Find the mass of a $item1.<br />
(b) Find the total mass of $item1CountQ #switchNPS($item1, $item1CountQ) and $item2CountQ #switchNPS($item2, $item2CountQ).
#set($answerB = ($item1CountQ * $item1W) + ($item2CountQ * $item2W))
||(a) $item1W kg<br />(b) $mathTool.roundTo(2, $answerB) kg');
insert into `tplo_decimals_22_1_q`(`id`,`value`) values (2,'Find the differences in mass between a $item1 and $item2.
#set($delta = $item1W - $item2W)
#set($answer2 = $mathTool.abs($delta))
||$mathTool.roundTo(2, $answer2) kg');
