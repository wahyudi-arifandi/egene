USE egene;

CREATE TABLE `tplo_decimals_23_3_q` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value` varchar(2048) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;


insert into `tplo_decimals_23_3_q`(`id`,`value`) values (1,'<br />(a) What was the total number of parcels delivered on time last time?<br />
(b) How much less money did the company collect last month because of the parcels which were delivered late?
||
(a)<br />
For every $saleTotal parcels, its revenue = ($saleTotal - $saleDefect) x $priceNormal + $saleDefect x $priceDefect = $saleTotalSet<br />
$saleLastMonth/$saleTotalSet = $setCount<br />
Total number of parcels delivered on time last time = ($saleTotal - $saleDefect) x $setCount = $answerA<br />
(b)<br /> 
Total number of parcels delivered late last time = $saleDefect x $setCount = $answerBTmp<br />
$answerBTmp x ($priceNormal - $priceDefect) = $answerB<br />
It collected ${currency}$answerB less money because of delivering parcels late.
||
$answerA|$answerB');
