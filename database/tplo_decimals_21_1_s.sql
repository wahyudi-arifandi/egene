

CREATE TABLE `tplo_decimals_21_1_s` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value` varchar(1024) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;


insert into `tplo_decimals_21_1_s`(`id`,`value`) values (1,'There are $total pieces of $centSort.get(0)#cminus()cent, $centSort.get(1)#cminus()cent, and $centSort.get(2)#cminus()cent coins $vTemplateGenerator.retrieveTpl("tpln_coin_place"). There are $morecent $cmp $cent.get(0)#cminus()cent coins than $cent.get(1)#cminus()cent coins, and $mulcent times as many $cent.get(2)#cminus()cent coins as $cent.get(0)#cminus()cent coins.');
insert into `tplo_decimals_21_1_s`(`id`,`value`) values (2,'#evaluate($vTemplateGenerator.retrieveTpl($tplNameTbl)) place a stack of $centSort.get(0)#cminus()cent coins, a stack of $centSort.get(1)#cminus()cent coins, and a stack of $centSort.get(2)#cminus()cent coins #evaluate($vTemplateGenerator.retrieveTpl("tpln_coin_place")). There are $morecent $cmp $cent.get(0)#cminus()cent coins than $cent.get(1)#cminus()cent coins, and $mulcent times as many $cent.get(2)#cminus()cent coins as $cent.get(0)#cminus()cent coins. Altogether there are $total coins.');
