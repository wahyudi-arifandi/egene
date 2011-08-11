package edu.ntu.eee.csn.ism.egene.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import edu.ntu.eee.csn.ism.egene.business.Noun;
import edu.ntu.eee.csn.ism.egene.business.Verb;
import edu.ntu.eee.csn.ism.egene.exception.OptUtilException;

public class OptUtil {

	public static String generateOptStr(List<String> opts) {

		if (null == opts || opts.size() == 0)
			return null;

		int i = NumberUtil.generateInt(0, opts.size() - 1);
		return opts.get(i);

	}

	public static List<String> generateOptStrN(List<String> opts, int num) {

		List<String> selected = new ArrayList<String>();
		for (int i = 0; i < num; i++) {
			String str = OptUtil.generateOptStrExcl(opts, selected);
			selected.add(str);
		}

		return selected;
	}

	public static String generateOptStrExcl(List<String> opts, List<String> excl) {

		if (null == opts || opts.size() == 0)
			return null;

		if (null == excl || excl.size() == 0)
			return OptUtil.generateOptStr(opts);

		Set<String> setOpts = new HashSet<String>(opts);
		int allSetCount = setOpts.size();
		Set<String> setExcl = new HashSet<String>(excl);
		int exclSetCount = 0;
		Iterator<String> iterator = setExcl.iterator();
		while (iterator.hasNext()) {
			String str = iterator.next();
			if (null != str) {
				if (opts.contains(str))
					exclSetCount++;
			}
		}

		if (exclSetCount >= allSetCount) {
			throw new OptUtilException(
					"NO possible int to be generated. allPossibleSetSize="
							+ allSetCount + ", exclSet=" + exclSetCount);
		}

		String res = null;
		boolean got = false;
		while (!got) {
			res = OptUtil.generateOptStr(opts);
			if (!excl.contains(res))
				got = true;
		}

		return res;

	}

	public static int generateOptInt(List<Integer> opts) {

		if (null == opts || opts.size() == 0)
			throw new OptUtilException("INVALID opts. RECEIVED [empty]");

		int i = NumberUtil.generateInt(0, opts.size() - 1);
		return opts.get(i).intValue();

	}

	public static List<Integer> generateOptIntN(List<Integer> opts, int num) {

		List<Integer> selected = new ArrayList<Integer>();
		for (int i = 0; i < num; i++) {
			Integer intGen = OptUtil.generateOptIntExcl(opts, selected);
			selected.add(intGen);
		}

		return selected;
	}

	public static int generateOptIntExcl(List<Integer> opts, List<Integer> excl) {

		if (null == opts || opts.size() == 0)
			throw new OptUtilException("INVALID opts. RECEIVED [empty]");

		if (null == excl || excl.size() == 0)
			return OptUtil.generateOptInt(opts);

		Set<Integer> setOpts = new HashSet<Integer>(opts);
		int allSetCount = setOpts.size();
		Set<Integer> setExcl = new HashSet<Integer>(excl);
		int exclSetCount = 0;
		Iterator<Integer> iterator = setExcl.iterator();
		while (iterator.hasNext()) {
			Integer i = iterator.next();
			if (null != i) {
				if (opts.contains(i))
					exclSetCount++;
			}
		}

		if (exclSetCount >= allSetCount) {
			throw new OptUtilException(
					"NO possible int to be generated. allPossibleSetSize="
							+ allSetCount + ", exclSet=" + exclSetCount);
		}

		Integer res = null;
		boolean got = false;
		while (!got) {
			res = OptUtil.generateOptInt(opts);
			if (!excl.contains(res))
				got = true;
		}

		return res.intValue();

	}

	public static double generateOptDouble(List<Double> opts) {

		if (null == opts || opts.size() == 0)
			throw new OptUtilException("INVALID opts. RECEIVED [empty]");

		int i = NumberUtil.generateInt(0, opts.size() - 1);
		return opts.get(i).doubleValue();

	}

	public static List<Double> generateOptDoubleN(List<Double> opts, int num) {

		List<Double> selected = new ArrayList<Double>();
		for (int i = 0; i < num; i++) {
			Double dblGen = OptUtil.generateOptDoubleExcl(opts, selected);
			selected.add(dblGen);
		}

		return selected;
	}

	public static double generateOptDoubleExcl(List<Double> opts,
			List<Double> excl) {

		if (null == opts || opts.size() == 0)
			throw new OptUtilException("INVALID opts. RECEIVED [empty]");

		if (null == excl || excl.size() == 0)
			return OptUtil.generateOptDouble(opts);

		Set<Double> setOpts = new HashSet<Double>(opts);
		int allSetCount = setOpts.size();
		Set<Double> setExcl = new HashSet<Double>(excl);
		int exclSetCount = 0;
		Iterator<Double> iterator = setExcl.iterator();
		while (iterator.hasNext()) {
			Double dbl = iterator.next();
			if (null != dbl) {
				if (opts.contains(dbl))
					exclSetCount++;
			}
		}

		if (exclSetCount >= allSetCount) {
			throw new OptUtilException(
					"NO possible double to be generated. allPossibleSetSize="
							+ allSetCount + ", exclSet=" + exclSetCount);
		}

		Double res = null;
		boolean got = false;
		while (!got) {
			res = OptUtil.generateOptDouble(opts);
			if (!excl.contains(res))
				got = true;
		}

		return res.doubleValue();

	}

	public static Verb generateOptVerb(List<Verb> opts) {

		if (null == opts || opts.size() == 0)
			throw new OptUtilException("INVALID opts. RECEIVED [empty]");

		int i = NumberUtil.generateInt(0, opts.size() - 1);
		return opts.get(i);

	}

	public static List<Verb> generateOptVerbN(List<Verb> opts, int num) {

		List<Verb> selected = new ArrayList<Verb>();
		for (int i = 0; i < num; i++) {
			Verb verbGen = OptUtil.generateOptVerbExcl(opts, selected);
			selected.add(verbGen);
		}

		return selected;
	}

	public static Verb generateOptVerbExcl(List<Verb> opts, List<Verb> excl) {

		if (null == opts || opts.size() == 0)
			throw new OptUtilException("INVALID opts. RECEIVED [empty]");

		if (null == excl || excl.size() == 0)
			return OptUtil.generateOptVerb(opts);

		Set<Verb> setOpts = new HashSet<Verb>();
		Set<String> tmpOpts = new HashSet<String>();
		for (int i = 0; i < opts.size(); i++) {
			if (!tmpOpts.contains(opts.get(i).getSimplePresent())) {
				setOpts.add(opts.get(i));
				tmpOpts.add(opts.get(i).getSimplePresent());
			}
		}
		// tmpOpts.clear();
		int allSetCount = setOpts.size();

		Set<Verb> setExcl = new HashSet<Verb>();
		Set<String> tmpExcl = new HashSet<String>();
		for (int i = 0; i < excl.size(); i++) {
			if (!tmpExcl.contains(excl.get(i).getSimplePresent())) {
				setExcl.add(excl.get(i));
				tmpExcl.add(opts.get(i).getSimplePresent());
			}
		}
		// tmpExcl.clear();

		System.out.println(setOpts.toString());
		System.out.println(setExcl.toString());

		int exclSetCount = 0;
		Iterator<Verb> iterator = setExcl.iterator();
		while (iterator.hasNext()) {
			Verb verb = iterator.next();
			if (null != verb) {
				for (int i = 0; i < opts.size(); i++) {
					if (opts.get(i).getSimplePresent()
							.equals(verb.getSimplePresent())) {
						exclSetCount++;
						break;
					}
				}

			}
		}

		if (exclSetCount >= allSetCount) {
			throw new OptUtilException(
					"NO possible Verb to be generated. allPossibleSetSize="
							+ allSetCount + ", exclSet=" + exclSetCount);
		}

		Verb res = null;
		boolean got = false;
		while (!got) {
			res = OptUtil.generateOptVerb(opts);
			boolean has = false;
			for (int i = 0; i < excl.size(); i++) {
				if (excl.get(i).getSimplePresent()
						.equals(res.getSimplePresent())) {
					has = true;
					break;
				}
			}
			if (!has)
				got = true;
		}

		return res;

	}

	public static Noun generateOptNoun(List<Noun> opts) {

		if (null == opts || opts.size() == 0)
			throw new OptUtilException("INVALID opts. RECEIVED [empty]");

		int i = NumberUtil.generateInt(0, opts.size() - 1);
		return opts.get(i);

	}

	public static List<Noun> generateOptNounN(List<Noun> opts, int num) {

		List<Noun> selected = new ArrayList<Noun>();
		for (int i = 0; i < num; i++) {
			Noun nounGen = OptUtil.generateOptNounExcl(opts, selected);
			selected.add(nounGen);
		}

		return selected;
	}

	public static Noun generateOptNounExcl(List<Noun> opts, List<Noun> excl) {

		if (null == opts || opts.size() == 0)
			throw new OptUtilException("INVALID opts. RECEIVED [empty]");

		if (null == excl || excl.size() == 0)
			return OptUtil.generateOptNoun(opts);

		Set<Noun> setOpts = new HashSet<Noun>();
		Set<String> tmpOpts = new HashSet<String>();
		for (int i = 0; i < opts.size(); i++) {
			if (!tmpOpts.contains(opts.get(i).getSingular())) {
				setOpts.add(opts.get(i));
				tmpOpts.add(opts.get(i).getSingular());
			}
		}
		// tmpOpts.clear();
		int allSetCount = setOpts.size();

		Set<Noun> setExcl = new HashSet<Noun>();
		Set<String> tmpExcl = new HashSet<String>();
		for (int i = 0; i < excl.size(); i++) {
			if (!tmpExcl.contains(excl.get(i).getSingular())) {
				setExcl.add(excl.get(i));
				tmpExcl.add(opts.get(i).getSingular());
			}
		}
		// tmpExcl.clear();

		System.out.println(setOpts.toString());
		System.out.println(setExcl.toString());

		int exclSetCount = 0;
		Iterator<Noun> iterator = setExcl.iterator();
		while (iterator.hasNext()) {
			Noun noun = iterator.next();
			if (null != noun) {
				for (int i = 0; i < opts.size(); i++) {
					if (opts.get(i).getSingular().equals(noun.getSingular())) {
						exclSetCount++;
						break;
					}
				}

			}
		}

		if (exclSetCount >= allSetCount) {
			throw new OptUtilException(
					"NO possible Verb to be generated. allPossibleSetSize="
							+ allSetCount + ", exclSet=" + exclSetCount);
		}

		Noun res = null;
		boolean got = false;
		while (!got) {
			res = OptUtil.generateOptNoun(opts);
			boolean has = false;
			for (int i = 0; i < excl.size(); i++) {
				if (excl.get(i).getSingular().equals(res.getSingular())) {
					has = true;
					break;
				}
			}
			if (!has)
				got = true;
		}

		return res;
	}

}
