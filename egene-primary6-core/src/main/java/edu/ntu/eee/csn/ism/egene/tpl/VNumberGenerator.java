package edu.ntu.eee.csn.ism.egene.tpl;

import java.util.ArrayList;
import java.util.List;

import edu.ntu.eee.csn.ism.egene.util.EnglishNumberToWords;
import edu.ntu.eee.csn.ism.egene.util.NumberUtil;

public class VNumberGenerator {

	public int genInt(int min, int max) {
		return NumberUtil.generateInt(min, max);
	}

	public int genIntMul(int min, int max, List<Integer> mul) {
		return NumberUtil.generateIntMul(min, max, mul);
	}

	public int genIntExcl(int min, int max, List<Integer> excl) {
		return NumberUtil.generateIntExcl(min, max, excl);
	}

	public int genIntMulExcl(int min, int max, List<Integer> mul,
			List<Integer> excl) {
		return NumberUtil.generateIntMulExcl(min, max, mul, excl);
	}

	public double genDouble(int min, int max, int commaPos) {
		return NumberUtil.generateDouble(min, max, commaPos);
	}

	public double genDoubleMul(int min, int max, int commaPos, List<Integer> mul) {
		return NumberUtil.generateDoubleMul(min, max, commaPos, mul);
	}

	public double genDoubleExcl(int min, int max, int commaPos,
			List<Integer> excl) {
		return NumberUtil.generateDoubleExcl(min, max, commaPos, excl);
	}

	public double genDoubleMulExcl(int min, int max, int commaPos,
			List<Integer> mul, List<Integer> excl) {
		return NumberUtil.generateDoubleMulExcl(min, max, commaPos, mul, excl);
	}

	public String toEnglishWords(int number) {
		return EnglishNumberToWords.convert(number);
	}

	public List<Integer> genDivisor(int num) {
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 1; i <= num; i++) {
			if (num % i == 0) {
				list.add(new Integer(i));
			}
		}

		return list;
	}

	public List<Integer> simplifyFraction(List<Integer> fraction) {
		return NumberUtil.simplifyFraction(fraction);
	}

}
