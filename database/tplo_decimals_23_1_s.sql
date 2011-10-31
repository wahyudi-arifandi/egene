USE egene;

CREATE TABLE `tplo_decimals_23_1_s` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value` varchar(2048) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;


insert into `tplo_decimals_23_1_s`(`id`,`value`) values (1,'#upperFirst($name) was paid ${currency}$salaryNormal for each hour $namePronS worked during office hours.
#upperFirst($namePronS) was paid ${currency}$salaryOT for each hour $namePronS worked overtime.
Last month #upperFirst($name)''s salary was  ${currency}$salaryMonth.
For every $workHourTotal hours #upperFirst($name) worked, $workHourOT hours were overtime.');
