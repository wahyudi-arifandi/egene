

CREATE TABLE `tplo_decimals_23_1_s` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value` varchar(1024) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;


insert into `tplo_decimals_23_1_s`(`id`,`value`) values (1,'#upperFirst($name) was paid $escapeTool.getD()$vFormatter.formatDecimal($salaryNormal, 2) for each hour $vNounFormatter.genPronSubjGender($name, $tplNameGender) worked during office hours.
#upperFirst($vNounFormatter.genPronSubjGender($name, $tplNameGender)) was paid $escapeTool.getD()$vFormatter.formatDecimal($salaryOT, 2) for each hour $vNounFormatter.genPronSubjGender($name, $tplNameGender) worked overtime.
Last month #upperFirst($name)''s salary was  $escapeTool.getD()$vFormatter.formatDecimal($salaryMonth, 2).
For every $workHourTotal hours #upperFirst($name) worked, $workHourOT hours were overtime.');
