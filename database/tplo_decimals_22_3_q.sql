USE egene;

CREATE TABLE `tplo_decimals_22_3_q` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value` varchar(2048) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;


insert into `tplo_decimals_22_3_q`(`id`,`value`) values (1,'#set($item1CountQ = $vNumberGenerator.genInt(1, 15))
#set($item2CountQ = $vNumberGenerator.genInt(1, 15))
<br />(a) Find the cost of a $item1.<br />
(b) Find the total cost of $item1CountQ #switchNPS($item1, $item1CountQ) and $item2CountQ #switchNPS($item2, $item2CountQ).
#set($answerB = ($item1CountQ * $item1W) + ($item2CountQ * $item2W))
||(a) $escapeTool.getD()$item1W<br />(b) $escapeTool.getD()$mathTool.roundTo(2, $answerB)');
insert into `tplo_decimals_22_3_q`(`id`,`value`) values (2,'Find the differences cost between a $item1 and $item2.
#set($delta = $item1W - $item2W)
#set($answer2 = $mathTool.abs($delta))
||$escapeTool.getD()$mathTool.roundTo(2, $answer2)');
insert into `tplo_decimals_22_3_q`(`id`,`value`) values (3,'<br />(a) Find the cost of a $item1.<br />
#set($item1Buy = $vNumberGenerator.genInt(2, 15))
#set($item1BuyPay = $vNumberGenerator.genInt(1, 20) + ($item1Buy * $item1W))
#set($item1BuyPay2 = $mathTool.toInteger($item1BuyPay))
(b) How much change would $name receive if $vNounFormatter.genPronSubjGender($name, $tplNameGender) paid the cashier $escapeTool.getD()$item1BuyPay2 for $item1Buy #switchNPS($item1, $item1Buy)? 
#set($answerB = ($item1BuyPay2 - ($item1Buy * $item1W)))
||(a) $escapeTool.getD()$item1W <br />(b) $escapeTool.getD()$mathTool.roundTo(2, $answerB)');
