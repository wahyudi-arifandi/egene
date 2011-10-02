USE egene;

CREATE TABLE `tplo_decimals_23_2_q` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value` varchar(2048) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;


insert into `tplo_decimals_23_2_q`(`id`,`value`) values (1,'<br />(a) How many good $vNounFormatter.genPlural($goods) were sold yesterday?<br />
(b) How much less money did the music shop collect yesterday because of defective $vNounFormatter.genPlural($goods)?
#set($answerA = $setCount * ($saleTotal - $saleDefect))
#set($answerB = $setCount * $saleDefect * ($priceNormal - $priceDefect))
||(a) It sold $answerA good $vNounFormatter.genPlural($goods)<br />
(b) It collected $escapeTool.getD()$vFormatter.formatDecimal($answerB, 2) less money because of defective $vNounFormatter.genPlural($goods)');
