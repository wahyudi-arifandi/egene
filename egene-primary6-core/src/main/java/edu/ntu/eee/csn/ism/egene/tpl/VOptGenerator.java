package edu.ntu.eee.csn.ism.egene.tpl;

import java.util.List;

import edu.ntu.eee.csn.ism.egene.util.OptUtil;

public class VOptGenerator {

	public String genOptStr(List<String> opts) {
		return OptUtil.generateOptStr(opts);
	}

	public List<String> genOptStrN(List<String> opts, int n) {
		// List<String> res = OptUtil.generateOptStrN(opts, n);
		// Collections.sort(res);
		// return res;
		return OptUtil.generateOptStrN(opts, n);
	}

	public String genOptStrExcl(List<String> opts, List<String> excl) {
		return OptUtil.generateOptStrExcl(opts, excl);
	}

	public int genOptInt(List<Integer> opts) {
		return OptUtil.generateOptInt(opts);
	}

	public List<Integer> genOptIntN(List<Integer> opts, int n) {
		// List<Integer> res = OptUtil.generateOptIntN(opts, n);
		// Collections.sort(res);
		// return res;
		return OptUtil.generateOptIntN(opts, n);
	}

	public int genOptIntExcl(List<Integer> opts, List<Integer> excl) {
		return OptUtil.generateOptIntExcl(opts, excl);
	}

	public double genOptDouble(List<Double> opts) {
		return OptUtil.generateOptDouble(opts);
	}

	public List<Double> genOptDoubleN(List<Double> opts, int n) {
		// List<Double> res = OptUtil.generateOptDoubleN(opts, n);
		// Collections.sort(res);
		// return res;
		return OptUtil.generateOptDoubleN(opts, n);
	}

	public double genOptDoubleExcl(List<Double> opts, List<Double> excl) {
		return OptUtil.generateOptDoubleExcl(opts, excl);
	}

}
