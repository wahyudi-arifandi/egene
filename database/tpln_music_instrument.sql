USE egene;

CREATE TABLE `tpln_music_instrument` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value` varchar(1024) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;


insert into `tpln_music_instrument`(`id`,`value`) values (1,'guitar');
insert into `tpln_music_instrument`(`id`,`value`) values (2,'violin');
insert into `tpln_music_instrument`(`id`,`value`) values (3,'piano');
insert into `tpln_music_instrument`(`id`,`value`) values (4,'drum');
insert into `tpln_music_instrument`(`id`,`value`) values (5,'keyboard');
insert into `tpln_music_instrument`(`id`,`value`) values (6,'trumpet');
insert into `tpln_music_instrument`(`id`,`value`) values (7,'saxophone');
insert into `tpln_music_instrument`(`id`,`value`) values (8,'flute');
