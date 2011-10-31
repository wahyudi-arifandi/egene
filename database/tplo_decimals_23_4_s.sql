USE egene;

CREATE TABLE `tplo_decimals_23_4_s` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value` varchar(2048) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;


insert into `tplo_decimals_23_4_s`(`id`,`value`) values (1,'In a multiple choice quiz, $markCorrect marks was awarded for each correct answer.
$markWrong marks were deducted for each wrong answer.
No mark was awarded or deducted for any question not attempted.
$name scored $qActualMark marks for the quiz.
For every $qTotal questions, $namePronS not attempt $qBlank questions and $qWrong questions were wrongly answered.');
