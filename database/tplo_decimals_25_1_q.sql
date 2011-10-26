USE egene;

CREATE TABLE `tplo_decimals_25_1_q` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value` varchar(2048) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;


insert into `tplo_decimals_25_1_q`(`id`,`value`) values (1,'What was $namePossAdj average monthly earnings during this period?
|| 
${name}''s commission for the $periodMonth#cminus()month = ($nMonthSalesD / $commBasic) x $commissionD = $nCommissionTotalD<br />
#upperFirst($namePossAdj) average monthly earnings during this period was: $fixSalaryD + ($nCommissionTotalD / $periodMonth) = ${currency}$monthlyEarningsD
||
$monthlyEarningsD');
