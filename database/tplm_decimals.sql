USE egene;

CREATE TABLE `tplm_decimals` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value` varchar(1024) NOT NULL,
  `value_type` int(11) NOT NULL DEFAULT '1',
  `question_type` varchar(64) NOT NULL DEFAULT '''''''''default''''''''',
  `active` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `IDX1` (`question_type`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;


insert into `tplm_decimals`(`id`,`value`,`value_type`,`question_type`,`active`) values (1,'psp21.1.vm',1,'psp21.1',0);
insert into `tplm_decimals`(`id`,`value`,`value_type`,`question_type`,`active`) values (2,'psp22.1.vm',1,'psp22.1',0);
insert into `tplm_decimals`(`id`,`value`,`value_type`,`question_type`,`active`) values (3,'psp22.2.vm',1,'psp22.2',0);
insert into `tplm_decimals`(`id`,`value`,`value_type`,`question_type`,`active`) values (4,'psp22.3.vm',1,'psp22.3',0);
insert into `tplm_decimals`(`id`,`value`,`value_type`,`question_type`,`active`) values (5,'psp23.1.vm',1,'psp23.1',1);
insert into `tplm_decimals`(`id`,`value`,`value_type`,`question_type`,`active`) values (6,'psp23.2.vm',1,'psp23.2',1);
insert into `tplm_decimals`(`id`,`value`,`value_type`,`question_type`,`active`) values (7,'psp23.3.vm',1,'psp23.3',1);
insert into `tplm_decimals`(`id`,`value`,`value_type`,`question_type`,`active`) values (8,'psp23.4.vm',1,'psp23.4',1);
insert into `tplm_decimals`(`id`,`value`,`value_type`,`question_type`,`active`) values (9,'psp24.1.vm',1,'psp24.1',1);
insert into `tplm_decimals`(`id`,`value`,`value_type`,`question_type`,`active`) values (10,'psp25.1.vm',1,'psp25.1',1);
insert into `tplm_decimals`(`id`,`value`,`value_type`,`question_type`,`active`) values (11,'psp25.2.vm',1,'psp25.2',1);
insert into `tplm_decimals`(`id`,`value`,`value_type`,`question_type`,`active`) values (12,'psp25.3.vm',1,'psp25.3',1);
insert into `tplm_decimals`(`id`,`value`,`value_type`,`question_type`,`active`) values (13,'psp26.1.vm',1,'psp26.1',1);
insert into `tplm_decimals`(`id`,`value`,`value_type`,`question_type`,`active`) values (14,'psp26.2.vm',1,'psp26.2',1);
insert into `tplm_decimals`(`id`,`value`,`value_type`,`question_type`,`active`) values (15,'psp27.1.vm',1,'psp27.1',1);
insert into `tplm_decimals`(`id`,`value`,`value_type`,`question_type`,`active`) values (16,'psp27.2.vm',1,'psp27.2',1);
insert into `tplm_decimals`(`id`,`value`,`value_type`,`question_type`,`active`) values (17,'psp28.1.vm',1,'psp28.1',1);
insert into `tplm_decimals`(`id`,`value`,`value_type`,`question_type`,`active`) values (18,'psp29.1.vm',1,'psp29.1',1);
