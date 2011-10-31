USE egene;

CREATE TABLE `tplo_decimals_23_3_s` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value` varchar(2048) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;


insert into `tplo_decimals_23_3_s`(`id`,`value`) values (1,'A $curier company charges ${currency}$priceNormal for each parcel delivered on time and ${currency}$priceDefect for each parcel delivered late.
Last month, the company collected ${currency}$saleLastMonth. 
For every $saleTotal parcels delivered, $saleDefect were late.');
