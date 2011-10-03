USE egene;

CREATE TABLE `tplo_decimals_27_2_s` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value` varchar(2048) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;


insert into `tplo_decimals_27_2_s`(`id`,`value`) values (1,'The total mass of a $container with $bookCountRatioNum/$bookCountRatioDenom of its contents is $dMass kg lighter than when it is full.
The $container contains $itemCountTotal similar $itemPlural.
The $container weighs $containerMassRatioNum/$containerMassRatioDenom times the mass of each $item.');
insert into `tplo_decimals_27_2_s`(`id`,`value`) values (2,'A $container contains $itemCountTotal similar $itemPlural.
The total mass of the $container with $bookCountRatioNum/$bookCountRatioDenom of the $itemPlural is $dMass kg lighter than that of the container with all the $itemPlural.
The mass of $container is $containerMassRatioNum/$containerMassRatioDenom times the mass of each $item.');
