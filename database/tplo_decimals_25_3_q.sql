USE egene;

CREATE TABLE `tplo_decimals_25_3_q` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value` varchar(2048) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;


insert into `tplo_decimals_25_3_q`(`id`,`value`) values (1,'Find the total mass of $packBigCountQ big packets and $packSmallCountQ small packets of $groceries.
Give your answer in kilograms.
|| #if($pack1WeightDGr > $pack2WeightDGr)
Given total number of packets = $packCountTotal, and total number of big packets = $pack1Count,<br />
then total number of small packets = $packCountTotal - $pack1Count = $pack2Count.<br />
Total mass of all small packets = ${pack2RatioPer10}/10 x ($packTotalWeightKg kg) = $pack2TotalWeightKg kg,<br />
then mass of a small packet = ($pack2TotalWeightKg kg) / $pack2Count = $pack2WeightKg kg.<br />
Total mass of all big packets = (1 - ${pack2RatioPer10}/10) x ($packTotalWeightKg kg) = ${pack1RatioPer10}/10 x ($packTotalWeightKg kg) = $pack1TotalWeightKg kg,<br />
then mass of a big packet = ($pack1TotalWeightKg kg) / $pack1Count = $pack1WeightKg kg.<br />
Total mass of $packBigCountQ big packets and $packSmallCountQ small packets of $groceries = ($packBigCountQ x ($pack1WeightKg kg)) + ($packSmallCountQ x ($pack2WeightKg kg)) = $packWeightQ kg.<br />
#else
Given total number of packets = $packCountTotal, and total number of small packets = $pack1Count,<br />
then total number of big packets = $packCountTotal - $pack1Count = $pack2Count.<br />
Total mass of all small packets = ${pack1RatioPer10}/10 x $packTotalWeightKg kg = $pack1TotalWeightKg kg.<br />
then mass of a small packet = ($pack1TotalWeightKg kg) / $pack1Count = $pack1WeightKg kg.<br />
Total mass of all big packets = (1 - ${pack1RatioPer10}/10) x ($packTotalWeightKg kg) = ${pack2RatioPer10}/10 x ($packTotalWeightKg kg) = $pack2TotalWeightKg kg,<br />
then mass of a big packet = ($pack2TotalWeightKg kg) / $pack2Count = $pack2WeightKg kg.<br />
Total mass of $packBigCountQ big packets and $packSmallCountQ small packets of $groceries = ($packBigCountQ x ($pack2WeightKg kg)) + ($packSmallCountQ x ($pack1WeightKg kg)) = $packWeightQ kg.<br />
#end
||
$packWeightQ');
