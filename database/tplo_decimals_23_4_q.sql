

CREATE TABLE `tplo_decimals_23_4_q` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value` varchar(1024) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;


insert into `tplo_decimals_23_4_q`(`id`,`value`) values (1,'How many questions were there in the quiz?
#set($answer = $setCount * $qTotal)
|| There were $answer questions in the quiz.');
