package edu.ntu.eee.csn.ism.egene.tpl;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class VFormatter {

	public String formatDecimal(double number, int fDigit) {

		NumberFormat df = DecimalFormat.getInstance();
		df.setMinimumFractionDigits(fDigit);
		df.setMaximumFractionDigits(fDigit);
		df.setRoundingMode(RoundingMode.HALF_UP);
		
		return df.format(number);
		
	}

}
