USE egene;

CREATE TABLE `tplo_decimals_27_1_q` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value` varchar(2048) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;


insert into `tplo_decimals_27_1_q`(`id`,`value`) values (1,'What is the total mass of the $container with all its contents?
||
1 - ${bookCountRatioNum}/${bookCountRatioDenom} = ${bookCountRatioNumX}/${bookCountRatioDenom}<br />
${bookCountRatioNumX}/${bookCountRatioDenom} of the $itemPlural weight $dMass kg<br />
${bookCountRatioNumX}/${bookCountRatioDenom} x $itemCountTotal = $itemCountX</br >
Mass of $itemCountX $itemPlural = $dMass kg<br />
Mass of 1 $item = $dMass / $itemCountX = $bookMass kg<br />
$bookMassRatioNum/$bookMassRatioDenom of the mass of $container = $bookMass kg<br />
$bookMassRatioDenom/$bookMassRatioDenom of the mass of $container = ($bookMassRatioDenom/$bookMassRatioNum) x $bookMass = $containerMass kg<br />
The total mass of the $container with all its contents is:<br />
$containerMass + ($itemCountTotal x $bookMass) = $massTotal kg');
insert into `tplo_decimals_27_1_q`(`id`,`value`) values (2,'Find the total mass of the $container and a $item.
Give you answer in grams.
||
1 - ${bookCountRatioNum}/${bookCountRatioDenom} = ${bookCountRatioNumX}/${bookCountRatioDenom}<br />
${bookCountRatioNumX}/${bookCountRatioDenom} of the $itemPlural weight $dMass kg<br />
${bookCountRatioNumX}/${bookCountRatioDenom} x $itemCountTotal = $itemCountX</br >
Mass of $itemCountX $itemPlural = $dMass kg<br />
Mass of 1 $item = $dMass / $itemCountX = $bookMass kg<br />
$bookMassRatioNum/$bookMassRatioDenom of the mass of $container = $bookMass kg<br />
$bookMassRatioDenom/$bookMassRatioDenom of the mass of $container = ($bookMassRatioDenom/$bookMassRatioNum) x $bookMass = $containerMass kg<br />
The total mass of the $container with all its contents is:<br />
$containerMass + $bookMass = $massOneOneKg kg = $massOneOneGr gr');
