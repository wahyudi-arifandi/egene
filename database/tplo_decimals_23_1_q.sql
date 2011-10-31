USE egene;

CREATE TABLE `tplo_decimals_23_1_q` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value` varchar(2048) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;


insert into `tplo_decimals_23_1_q`(`id`,`value`) values (1,'<br />(a) How many hours of overtime did #upperFirst($name) work last month?<br />
(b) How much less money would #upperFirst($name) earn if $namePronS was not paid more for each hour of overtime?
||
(a)<br />
For every $workHourTotal hours, $namePossAdj salary = ($workHourTotal - $workHourOT) x $salaryNormal + $workHourOT x $salaryOT = $salaryTotalSet<br />
$salaryMonth/$salaryTotalSet = $setCount<br />
$workHourOT x $setCount = $answerA<br />
#upperFirst($namePronS) worked $answerA hours of overtime.<br />
(b)<br />
($salaryOT - $salaryNormal) x $answerA = $answerB<br />
#upperFirst($namePronS) would earn ${currency}$answerB less.
||
$answerA|$answerB');
