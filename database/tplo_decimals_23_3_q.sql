USE egene;

CREATE TABLE `tplo_decimals_23_3_q` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value` varchar(2048) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;


insert into `tplo_decimals_23_3_q`(`id`,`value`) values (1,'<br />(a) What was the total number of parcels delivered on time last time?<br />
(b) How much less money did the company collect last month because of the parcels which were delivered late?
#set($answerA = $setCount * ($saleTotal - $saleDefect))
#set($answerB = $setCount * $saleDefect * ($priceNormal - $priceDefect))
||(a) It delivered $answerA parcels on time<br />
(b) It collected $escapeTool.getD()$vFormatter.formatDecimal($answerB, 2) less money because of delivering parcels late.');
