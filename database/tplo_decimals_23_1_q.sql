USE egene;

CREATE TABLE `tplo_decimals_23_1_q` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value` varchar(2048) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;


insert into `tplo_decimals_23_1_q`(`id`,`value`) values (1,'<br />(a) How many hours of overtime did #upperFirst($name) work last month?<br />
(b) How much less money would #upperFirst($name) earn if $vNounFormatter.genPronSubjGender($name, $tplNameGender) was not paid more for each hour of overtime?
#set($answerA = $setCount * $workHourOT)
#set($answerB = $answerA * ($salaryOT - $salaryNormal))
||(a) #upperFirst($vNounFormatter.genPronSubjGender($name, $tplNameGender)) worked $answerA hours of overtime.<br />
(b) #upperFirst($vNounFormatter.genPronSubjGender($name, $tplNameGender)) would earn $escapeTool.getD()$vFormatter.formatDecimal($answerB, 2) less.');
