USE egene;

CREATE TABLE `master_topics` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `topic_name` varchar(64) NOT NULL,
  `template_table` varchar(64) NOT NULL,
  `description` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;


insert into `master_topics`(`id`,`topic_name`,`template_table`,`description`) values (1,'Decimals','tplm_decimals',null);
insert into `master_topics`(`id`,`topic_name`,`template_table`,`description`) values (2,'Algebra','tplm_algebra',null);
insert into `master_topics`(`id`,`topic_name`,`template_table`,`description`) values (3,'Whole Numbers','tplm_whole_numbers',null);
insert into `master_topics`(`id`,`topic_name`,`template_table`,`description`) values (4,'Fractions','tplm_fractions',null);
insert into `master_topics`(`id`,`topic_name`,`template_table`,`description`) values (5,'Volume','tplm_volume',null);
insert into `master_topics`(`id`,`topic_name`,`template_table`,`description`) values (6,'Ratio','tplm_ratio',null);
insert into `master_topics`(`id`,`topic_name`,`template_table`,`description`) values (7,'Percentage','tplm_percentage',null);
insert into `master_topics`(`id`,`topic_name`,`template_table`,`description`) values (8,'Distance, Time, and Speed','tplm_dist_time_speed',null);
