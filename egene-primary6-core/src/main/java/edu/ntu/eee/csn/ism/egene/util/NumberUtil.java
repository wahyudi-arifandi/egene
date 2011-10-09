package edu.ntu.eee.csn.ism.egene.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import edu.ntu.eee.csn.ism.egene.exception.NumberUtilException;

public class NumberUtil {

	public static int generateInt(int min, int max) {

		if (max < min) {
			throw new NumberUtilException(
					"INVALID range, max must not less then min. RECEIVED [min="
							+ min + "; max=" + max + "]");
		}
		return (int) Math.round(min + (Math.random()) * (max - min));

	}

	public static int generateIntMul(int min, int max, List<Integer> mul) {

		if (null == mul || mul.size() == 0)
			return NumberUtil.generateInt(min, max);

		for (int i = 0; i < mul.size(); i++) {
			if ((max - min + 1) < mul.get(i).intValue()) {
				throw new NumberUtilException(
						"INVALID mul. Following formula must be satisfied [max-min+1 >= mul[i]. RECEIVED [min="
								+ min
								+ "; max="
								+ max
								+ "; mul["
								+ i
								+ "]="
								+ mul.get(i).intValue() + "]");
			}
		}

		int lcm = NumberUtil.lcm(mul);
		int res = NumberUtil.generateInt(min, max);
		while (!NumberUtil.isMul(res, lcm)) {
			res = NumberUtil.generateInt(min, max);
		}
		return res;

	}

	public static int generateIntExcl(int min, int max, List<Integer> excl) {

		if (null == excl || excl.size() == 0)
			return NumberUtil.generateInt(min, max);

		int allSetCount = max - min + 1;
		Set<Integer> set = new HashSet<Integer>(excl);
		int exclSetCount = 0;
		Iterator<Integer> iterator = set.iterator();
		while (iterator.hasNext()) {
			Integer integer = iterator.next().intValue();
			if (null != integer) {
				int i = integer.intValue();
				if (i >= min && i <= max)
					exclSetCount++;
			}
		}

		if (exclSetCount >= allSetCount) {
			throw new NumberUtilException(
					"NO possible int to be generated. allPossibleSetSize="
							+ allSetCount + ", exclSet=" + exclSetCount);
		}

		Integer res = new Integer(0);
		boolean got = false;
		while (!got) {
			res = new Integer(NumberUtil.generateInt(min, max));
			if (!set.contains(res))
				got = true;
		}

		return res.intValue();

	}

	public static int generateIntMulExcl(int min, int max, List<Integer> mul,
			List<Integer> excl) {

		if (null == excl || excl.size() == 0)
			return NumberUtil.generateIntMul(min, max, mul);

		if (null == mul || mul.size() == 0)
			return NumberUtil.generateIntExcl(min, max, excl);

		for (int i = 0; i < mul.size(); i++) {
			if ((max - min + 1) < mul.get(i).intValue()) {
				throw new NumberUtilException(
						"INVALID mul. Following formula must be satisfied [max-min+1 >= mul[i]. RECEIVED [min="
								+ min
								+ "; max="
								+ max
								+ "; mul["
								+ i
								+ "]="
								+ mul.get(i).intValue() + "]");
			}
		}

		int lcm = NumberUtil.lcm(mul);
		int allSetCount = (int) Math.floor((max - min) / lcm);
		Set<Integer> set = new HashSet<Integer>(excl);
		int exclSetCount = 0;
		Iterator<Integer> iterator = set.iterator();
		while (iterator.hasNext()) {
			Integer integer = iterator.next().intValue();
			if (null != integer) {
				int i = integer.intValue();
				if (i >= min && i <= max)
					exclSetCount++;
			}
		}

		if (exclSetCount >= allSetCount) {
			throw new NumberUtilException(
					"NO possible int to be generated. allPossibleSetSize="
							+ allSetCount + ", exclSet=" + exclSetCount);
		}

		Integer res = new Integer(0);
		boolean got = false;
		while (!got) {
			res = new Integer(NumberUtil.generateInt(min, max));
			if (NumberUtil.isMul(res.intValue(), lcm) && !set.contains(res))
				got = true;
		}

		return res.intValue();

	}

	public static double generateDouble(int min, int max, int commaFromLeft) {

		if (commaFromLeft < 0)
			throw new NumberUtilException(
					"INVALID value for comma from left, it must be more or equals to zero. RECEIVED ["
							+ commaFromLeft + "]");

		double res = NumberUtil.generateInt(min, max);
		res = res / (Math.pow(10, commaFromLeft));
		return res;

	}

	public static double generateDoubleMul(int min, int max, int commaFromLeft,
			List<Integer> mul) {

		if (commaFromLeft < 0)
			throw new NumberUtilException(
					"INVALID value for comma from left, it must be more or equals to zero. RECEIVED ["
							+ commaFromLeft + "]");

		double res = NumberUtil.generateIntMul(min, max, mul);
		res = res / (Math.pow(10, commaFromLeft));
		return res;

	}

	public static double generateDoubleExcl(int min, int max,
			int commaFromLeft, List<Integer> excl) {

		if (commaFromLeft < 0)
			throw new NumberUtilException(
					"INVALID value for comma from left, it must be more or equals to zero. RECEIVED ["
							+ commaFromLeft + "]");

		double res = NumberUtil.generateIntExcl(min, max, excl);
		res = res / (Math.pow(10, commaFromLeft));
		return res;

	}

	public static double generateDoubleMulExcl(int min, int max,
			int commaFromLeft, List<Integer> mul, List<Integer> excl) {

		if (commaFromLeft < 0)
			throw new NumberUtilException(
					"INVALID value for comma from left, it must be more or equals to zero. RECEIVED ["
							+ commaFromLeft + "]");

		double res = NumberUtil.generateIntMulExcl(min, max, mul, excl);
		res = res / (Math.pow(10, commaFromLeft));
		return res;

	}

	public static boolean isMul(int num, List<Integer> mul) {

		if (null == mul || mul.size() == 0) {
			return true;
		}

		for (int i = 0; i < mul.size(); i++) {
			if (null != mul.get(i))
				mul.set(i, new Integer(Math.abs(mul.get(i).intValue())));
		}

		int lcm = NumberUtil.lcm(mul);
		return NumberUtil.isMul(Math.abs(num), lcm);

	}

	public static boolean isMul(int num, int mul) {
		if (Math.abs(num) < Math.abs(mul))
			return false;

		return ((num % mul) == 0);
	}

	public static int gcd(int a, int b) {

		if (a <= 0 || b <= 0)
			throw new NumberUtilException(
					"INVALID a or b. They must more than 0. RECEIVED [a=" + a
							+ "; b=" + b + "]");

		// Euclidean algorithm
		int t;
		while (b != 0) {
			t = b;
			b = a % b;
			a = t;
		}
		return a;

	}

	public static int lcm(int a, int b) {

		if (a <= 0 || b <= 0)
			throw new NumberUtilException(
					"INVALID a or b. They must more than 0. RECEIVED [a=" + a
							+ "; b=" + b + "]");

		return ((a * b) / NumberUtil.gcd(a, b));

	}

	public static int lcm(List<Integer> num) {

		// Recursively iterate through pairs of arguments
		// i.e. lcm(args[0], lcm(args[1], lcm(args[2], args[3])))

		if (null == num || num.size() == 0)
			throw new NumberUtilException(
					"INVALID num[]. They must not null. RECEIVED [num=null]");

		for (int i = 0; i < num.size(); i++) {
			if (null != num.get(i))
				num.set(i, new Integer(Math.abs(num.get(i).intValue())));
		}

		if (num.size() == 1)
			return num.get(0).intValue();

		if (num.size() == 2) {
			return lcm(num.get(0).intValue(), num.get(1).intValue());
		} else {
			int arg0 = num.get(0).intValue();
			List<Integer> remain = num.subList(1, num.size());
			return lcm(arg0, lcm(remain));
		}

	}

	public static List<Integer> simplifyFraction(List<Integer> fraction) {

		if (null == fraction || fraction.size() != 2)
			throw new NumberUtilException("INVALID fraction: "
					+ fraction.toString());

		int gcd = NumberUtil.gcd(fraction.get(0).intValue(), fraction.get(1)
				.intValue());
		fraction.set(0, new Integer(fraction.get(0).intValue() / gcd));
		fraction.set(1, new Integer(fraction.get(1).intValue() / gcd));

		return fraction;
	}

	public static int removeDecimal(double num) {

		return Integer.parseInt(Double.toString(num).replace(".", ""));

	}

	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>();
		list.add(0, new Integer(1));
		list.add(1, new Integer(8));
		list.add(2, new Integer(6));
		System.out.println(NumberUtil.lcm(list));
	}

}
