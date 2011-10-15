USE egene;

CREATE TABLE `tplo_decimals_25_2_q` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value` varchar(2048) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;


insert into `tplo_decimals_25_2_q`(`id`,`value`) values (1,'If $namePossAdj total earnings for this period was ${currency}$monthlyEarningsD,
find $namePossAdj monthly salary.
||
${name}''s commission for the $periodMonth#cminus()month = ($nMonthSalesD / $commBasic) x $commissionD = $nCommissionTotalD<br />
#upperFirst($namePossAdj) monthly salary = $monthlyEarningsD - ($nCommissionTotalD / $periodMonth) = ${currency}$fixSalaryD');
