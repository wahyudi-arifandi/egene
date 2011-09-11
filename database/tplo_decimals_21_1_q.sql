

CREATE TABLE `tplo_decimals_21_1_q` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value` varchar(1024) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;


insert into `tplo_decimals_21_1_q`(`id`,`value`) values (1,'How much do all the $cent.get(0)#cminus()cent coins add up to?
#set($answer = ($cent0Count * $cent.get(0)))
#set($answer = $mathTool.div($answer, 100))
||$escapeTool.getD()$vFormatter.formatDecimal($answer,2)');
insert into `tplo_decimals_21_1_q`(`id`,`value`) values (2,'Find the total value of the $cent.get(0)#cminus()cent coins and $cent.get(1)#cminus()cent coins.
#set($answer = ($cent0Count * $cent.get(0)) + ($cent1Count * $cent.get(1)))
#set($answer = $mathTool.div($answer, 100))
||$escapeTool.getD()$vFormatter.formatDecimal($answer,2)');
insert into `tplo_decimals_21_1_q`(`id`,`value`) values (4,'How much do all these coins add up to?
#set($answer = ($cent0Count * $cent.get(0)) + ($cent1Count * $cent.get(1)) + ($cent2Count * $cent.get(2)))
#set($answer = $mathTool.div($answer, 100))
||$escapeTool.getD()$vFormatter.formatDecimal($answer,2)

');
