USE egene;

CREATE TABLE `tplo_decimals_29_1_1` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value` varchar(2048) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;


insert into `tplo_decimals_29_1_1`(`id`,`value`) values (1,'$name1 poured some $tplFluid into a $tplContainerFluide and it had a mass $item1WBruto kg.
$name2 poured some $tplFluid into an identical $tplContainerFluide and it had a mass of $item2WBruto kg.
The amount of $tplFluid in ${name2}''s $tplContainerFluide was $ratioNum/$ratioDenom that in ${name1}''s $tplContainerFluide.<br />
(a) How much $tplFluid was there in ${name2}''s $tplContainerFluide?<br />
(b) Find the mass of the $tplContainerFluide when it was empty.
#evaluate($vTemplateGenerator.retrieveTpl("tplo_decimals_29_1_11"))');
insert into `tplo_decimals_29_1_1`(`id`,`value`) values (2,'$name2 poured some $tplFluid into a $tplContainerFluide and it had a mass of $item2WBruto kg.
$name1 poured some $tplFluid into an identical $tplContainerFluide and it had a mass of $item1WBruto kg.
The amount of $tplFluid in ${name2}''s $tplContainerFluide was $ratioNum/$ratioDenom that in ${name1}''s $tplContainerFluide.<br />
(a) How much $tplFluid was there in ${name2}''s $tplContainerFluide?<br />
(b) Find the mass of an empty $tplContainerFluide.
#evaluate($vTemplateGenerator.retrieveTpl("tplo_decimals_29_1_11"))');
