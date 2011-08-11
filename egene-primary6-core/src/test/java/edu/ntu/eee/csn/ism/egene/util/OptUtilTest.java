package edu.ntu.eee.csn.ism.egene.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import edu.ntu.eee.csn.ism.egene.business.Noun;
import edu.ntu.eee.csn.ism.egene.business.Verb;
import edu.ntu.eee.csn.ism.egene.exception.OptUtilException;
import edu.ntu.eee.csn.ism.egene.util.OptUtil;

public class OptUtilTest extends TestCase {
	private static Logger LOGGER = Logger.getLogger(OptUtilTest.class);

	/**
	 * Constructor
	 */
	public OptUtilTest() {
		super();
		if (LOGGER.isDebugEnabled())
			LOGGER.debug("instantiate");
	}

	/**
	 * Constructor
	 * 
	 * @param name
	 *            test name
	 */
	public OptUtilTest(String name) {
		super(name);
		if (LOGGER.isDebugEnabled())
			LOGGER.debug("instantiate");
	}

	/**
	 * Declares the test suite.
	 * 
	 * @return the test suite.
	 */
	public static TestSuite suite() {
		TestSuite testSuite = new TestSuite(OptUtilTest.class);
		return testSuite;
	}

	public void testGenerateOptStr() {

		assertNull(OptUtil.generateOptStr(null));
		assertNull(OptUtil.generateOptStr(new ArrayList<String>()));

		String[] str = { "opt1", "opt2", "opt3" };
		List<String> opts = Arrays.asList(str);
		for (int i = 0; i < 10; i++) {
			String strGen = OptUtil.generateOptStr(opts);
			assertTrue("opt1".equals(strGen) || "opt2".equals(strGen)
					|| "opt3".equals(strGen));
		}

	}

	public void testGenerateOptStrExcl() {

		String[] str = { "opt1", "opt2", "opt3" };
		List<String> opts = Arrays.asList(str);

		String[] str2 = { "opt1", "opt2" };
		List<String> excl1 = Arrays.asList(str2);

		String[] str3 = { "opt1", "opt2", "opt3" };
		List<String> excl2 = Arrays.asList(str3);

		Throwable ex1 = null;
		try {
			assertNull(OptUtil.generateOptStrExcl(null, null));
		} catch (OptUtilException e) {
			if (LOGGER.isEnabledFor(Level.ERROR))
				LOGGER.error(e.getMessage(), e);
			ex1 = e;
		}
		assertNull(ex1);

		Throwable ex2 = null;
		try {
			assertNull(OptUtil.generateOptStrExcl(null, excl1));
		} catch (OptUtilException e) {
			if (LOGGER.isEnabledFor(Level.ERROR))
				LOGGER.error(e.getMessage(), e);
			ex2 = e;
		}
		assertNull(ex2);

		for (int i = 0; i < 10; i++) {
			Throwable ex3 = null;
			try {
				String strGen = OptUtil.generateOptStrExcl(opts, null);
				assertTrue("opt1".equals(strGen) || "opt2".equals(strGen)
						|| "opt3".equals(strGen));
			} catch (OptUtilException e) {
				if (LOGGER.isEnabledFor(Level.ERROR))
					LOGGER.error(e.getMessage(), e);
				ex3 = e;
			}
			assertNull(ex3);
		}

		for (int i = 0; i < 10; i++) {
			Throwable ex4 = null;
			try {
				@SuppressWarnings("unused")
				String strGen = OptUtil.generateOptStrExcl(opts, excl2);
			} catch (OptUtilException e) {
				ex4 = e;
			}
			assertNotNull(ex4);
		}

		for (int i = 0; i < 10; i++) {
			Throwable ex5 = null;
			try {
				String strGen = OptUtil.generateOptStrExcl(opts, excl1);
				assertTrue("opt3".equals(strGen));
			} catch (OptUtilException e) {
				if (LOGGER.isEnabledFor(Level.ERROR))
					LOGGER.error(e.getMessage(), e);
				ex5 = e;
			}
			assertNull(ex5);
		}
	}

	public void testGenerateOptInt() {

		Throwable ex1 = null;
		try {
			assertNull(OptUtil.generateOptInt(null));
		} catch (Throwable e) {
			ex1 = e;
		}
		assertNotNull(ex1);

		Throwable ex2 = null;
		try {
			assertNull(OptUtil.generateOptInt(new ArrayList<Integer>()));
		} catch (Throwable e) {
			ex2 = e;
		}
		assertNotNull(ex2);

		Integer[] arrOpts = { 1, 2, 3 };
		List<Integer> opts = Arrays.asList(arrOpts);
		for (int i = 0; i < 10; i++) {
			Throwable ex3 = null;
			try {
				int intGen = OptUtil.generateOptInt(opts);
				assertTrue(1 == intGen || 2 == intGen || 3 == intGen);
			} catch (Throwable e) {
				if (LOGGER.isEnabledFor(Level.ERROR))
					LOGGER.error(e.getMessage(), e);
				ex3 = e;
			}
			assertNull(ex3);
		}
	}

	public void testGenerateOptIntExcl() {

		Integer[] arrOpts = { 1, 2, 3 };
		List<Integer> opts = Arrays.asList(arrOpts);

		Integer[] arrExcl1 = { 1, 2 };
		List<Integer> excl1 = Arrays.asList(arrExcl1);

		Integer[] arrExcl2 = { 1, 2, 3 };
		List<Integer> excl2 = Arrays.asList(arrExcl2);

		Throwable ex1 = null;
		try {
			assertNull(OptUtil.generateOptIntExcl(null, null));
		} catch (OptUtilException e) {
			ex1 = e;
		}
		assertNotNull(ex1);

		Throwable ex2 = null;
		try {
			assertNull(OptUtil.generateOptIntExcl(null, excl1));
		} catch (OptUtilException e) {
			ex2 = e;
		}
		assertNotNull(ex2);

		for (int i = 0; i < 10; i++) {
			Throwable ex3 = null;
			try {
				int intGen = OptUtil.generateOptIntExcl(opts, null);
				assertTrue(1 == intGen || 2 == intGen || 3 == intGen);
			} catch (OptUtilException e) {
				if (LOGGER.isEnabledFor(Level.ERROR))
					LOGGER.error(e.getMessage(), e);
				ex3 = e;
			}
			assertNull(ex3);
		}

		for (int i = 0; i < 10; i++) {
			Throwable ex4 = null;
			try {
				@SuppressWarnings("unused")
				int intGen = OptUtil.generateOptIntExcl(opts, excl2);
			} catch (OptUtilException e) {
				ex4 = e;
			}
			assertNotNull(ex4);
		}

		for (int i = 0; i < 10; i++) {
			Throwable ex5 = null;
			try {
				int intGen = OptUtil.generateOptIntExcl(opts, excl1);
				assertTrue(3 == intGen);
			} catch (OptUtilException e) {
				if (LOGGER.isEnabledFor(Level.ERROR))
					LOGGER.error(e.getMessage(), e);
				ex5 = e;
			}
			assertNull(ex5);
		}

	}

	public void testGenerateOptDouble() {

		Throwable ex1 = null;
		try {
			assertNull(OptUtil.generateOptDouble(null));
		} catch (Throwable e) {
			ex1 = e;
		}
		assertNotNull(ex1);

		Throwable ex2 = null;
		try {
			assertNull(OptUtil.generateOptDouble(new ArrayList<Double>()));
		} catch (Throwable e) {
			ex2 = e;
		}
		assertNotNull(ex2);

		Double[] arrOpts = { 0.1d, 0.2d, 0.3d };
		List<Double> opts = Arrays.asList(arrOpts);
		for (int i = 0; i < 10; i++) {
			Throwable ex3 = null;
			try {
				double dblGen = OptUtil.generateOptDouble(opts);
				assertTrue(0.1d == dblGen || 0.2d == dblGen || 0.3d == dblGen);
			} catch (Throwable e) {
				if (LOGGER.isEnabledFor(Level.ERROR))
					LOGGER.error(e.getMessage(), e);
				ex3 = e;
			}
			assertNull(ex3);
		}
	}

	public void testGenerateOptDoubleExcl() {

		Double[] arrOpts = { 0.1d, 0.2d, 0.3d };
		List<Double> opts = Arrays.asList(arrOpts);

		Double[] arrExcl1 = { 0.1d, 0.2d };
		List<Double> excl1 = Arrays.asList(arrExcl1);

		Double[] arrExcl2 = { 0.1d, 0.2d, 0.3d };
		List<Double> excl2 = Arrays.asList(arrExcl2);

		Throwable ex1 = null;
		try {
			assertNull(OptUtil.generateOptDoubleExcl(null, null));
		} catch (OptUtilException e) {
			ex1 = e;
		}
		assertNotNull(ex1);

		Throwable ex2 = null;
		try {
			assertNull(OptUtil.generateOptDoubleExcl(null, excl1));
		} catch (OptUtilException e) {
			ex2 = e;
		}
		assertNotNull(ex2);

		for (int i = 0; i < 10; i++) {
			Throwable ex3 = null;
			try {
				double dblGen = OptUtil.generateOptDoubleExcl(opts, null);
				assertTrue(0.1d == dblGen || 0.2d == dblGen || 0.3d == dblGen);
			} catch (OptUtilException e) {
				if (LOGGER.isEnabledFor(Level.ERROR))
					LOGGER.error(e.getMessage(), e);
				ex3 = e;
			}
			assertNull(ex3);
		}

		for (int i = 0; i < 10; i++) {
			Throwable ex4 = null;
			try {
				@SuppressWarnings("unused")
				double dblGen = OptUtil.generateOptDoubleExcl(opts, excl2);
			} catch (OptUtilException e) {
				ex4 = e;
			}
			assertNotNull(ex4);
		}

		for (int i = 0; i < 10; i++) {
			Throwable ex5 = null;
			try {
				double dblGen = OptUtil.generateOptDoubleExcl(opts, excl1);
				assertTrue(0.3d == dblGen);
			} catch (OptUtilException e) {
				if (LOGGER.isEnabledFor(Level.ERROR))
					LOGGER.error(e.getMessage(), e);
				ex5 = e;
			}
			assertNull(ex5);
		}

	}

	public void testGenerateOptVerb() {

		Throwable ex1 = null;
		try {
			assertNull(OptUtil.generateOptVerb(null));
		} catch (Throwable e) {
			ex1 = e;
		}
		assertNotNull(ex1);

		Throwable ex2 = null;
		try {
			assertNull(OptUtil.generateOptVerb(new ArrayList<Verb>()));
		} catch (Throwable e) {
			ex2 = e;
		}
		assertNotNull(ex2);

		List<Verb> opts = new ArrayList<Verb>();
		opts.add(new Verb("do"));
		opts.add(new Verb("talk"));
		opts.add(new Verb("hear"));
		for (int i = 0; i < 10; i++) {
			Throwable ex3 = null;
			try {
				Verb verbGen = OptUtil.generateOptVerb(opts);
				assertTrue(verbGen.equals(new Verb("do"))
						|| verbGen.equals(new Verb("talk"))
						|| verbGen.equals(new Verb("hear")));
			} catch (Throwable e) {
				if (LOGGER.isEnabledFor(Level.ERROR))
					LOGGER.error(e.getMessage(), e);
				ex3 = e;
			}
			assertNull(ex3);
		}
	}

	public void testGenerateOptVerbExcl() {

		List<Verb> opts = new ArrayList<Verb>();
		opts.add(new Verb("do"));
		opts.add(new Verb("talk"));
		opts.add(new Verb("hear"));

		List<Verb> excl1 = new ArrayList<Verb>();
		excl1.add(new Verb("do"));
		excl1.add(new Verb("talk"));

		List<Verb> excl2 = new ArrayList<Verb>();
		excl2.add(new Verb("do"));
		excl2.add(new Verb("talk"));
		excl2.add(new Verb("hear"));

		Throwable ex1 = null;
		try {
			assertNull(OptUtil.generateOptVerbExcl(null, null));
		} catch (OptUtilException e) {
			ex1 = e;
		}
		assertNotNull(ex1);

		Throwable ex2 = null;
		try {
			assertNull(OptUtil.generateOptVerbExcl(null, excl1));
		} catch (OptUtilException e) {
			ex2 = e;
		}
		assertNotNull(ex2);

		for (int i = 0; i < 10; i++) {
			Throwable ex3 = null;
			try {
				Verb verbGen = OptUtil.generateOptVerbExcl(opts, null);
				assertTrue(verbGen.equals(new Verb("do"))
						|| verbGen.equals(new Verb("talk"))
						|| verbGen.equals(new Verb("hear")));
			} catch (OptUtilException e) {
				if (LOGGER.isEnabledFor(Level.ERROR))
					LOGGER.error(e.getMessage(), e);
				ex3 = e;
			}
			assertNull(ex3);
		}

		for (int i = 0; i < 10; i++) {
			Throwable ex4 = null;
			try {
				@SuppressWarnings("unused")
				Verb verbGen = OptUtil.generateOptVerbExcl(opts, excl2);
			} catch (OptUtilException e) {
				ex4 = e;
			}
			assertNotNull(ex4);
		}

		for (int i = 0; i < 10; i++) {
			Throwable ex5 = null;
			try {
				Verb verbGen = OptUtil.generateOptVerbExcl(opts, excl1);
				assertTrue(verbGen.equals(new Verb("hear")));
			} catch (OptUtilException e) {
				if (LOGGER.isEnabledFor(Level.ERROR))
					LOGGER.error(e.getMessage(), e);
				ex5 = e;
			}
			assertNull(ex5);
		}

	}

	public void testGenerateOptNoun() {

		Throwable ex1 = null;
		try {
			assertNull(OptUtil.generateOptNoun(null));
		} catch (Throwable e) {
			ex1 = e;
		}
		assertNotNull(ex1);

		Throwable ex2 = null;
		try {
			assertNull(OptUtil.generateOptNoun(new ArrayList<Noun>()));
		} catch (Throwable e) {
			ex2 = e;
		}
		assertNotNull(ex2);

		List<Noun> opts = new ArrayList<Noun>();
		opts.add(new Noun("movie"));
		opts.add(new Noun("home"));
		opts.add(new Noun("child"));
		for (int i = 0; i < 10; i++) {
			Throwable ex3 = null;
			try {
				Noun noun = OptUtil.generateOptNoun(opts);
				assertTrue(noun.equals(new Noun("movie"))
						|| noun.equals(new Noun("home"))
						|| noun.equals(new Noun("child")));
			} catch (Throwable e) {
				if (LOGGER.isEnabledFor(Level.ERROR))
					LOGGER.error(e.getMessage(), e);
				ex3 = e;
			}
			assertNull(ex3);
		}
	}

	public void testGenerateOptNounExcl() {

		List<Noun> opts = new ArrayList<Noun>();
		opts.add(new Noun("movie"));
		opts.add(new Noun("home"));
		opts.add(new Noun("child"));

		List<Noun> excl1 = new ArrayList<Noun>();
		excl1.add(new Noun("movie"));
		excl1.add(new Noun("home"));

		List<Noun> excl2 = new ArrayList<Noun>();
		excl2.add(new Noun("movie"));
		excl2.add(new Noun("home"));
		excl2.add(new Noun("child"));

		Throwable ex1 = null;
		try {
			assertNull(OptUtil.generateOptNounExcl(null, null));
		} catch (OptUtilException e) {
			ex1 = e;
		}
		assertNotNull(ex1);

		Throwable ex2 = null;
		try {
			assertNull(OptUtil.generateOptNounExcl(null, excl1));
		} catch (OptUtilException e) {
			ex2 = e;
		}
		assertNotNull(ex2);

		for (int i = 0; i < 10; i++) {
			Throwable ex3 = null;
			try {
				Noun nounGen = OptUtil.generateOptNounExcl(opts, null);
				assertTrue(nounGen.equals(new Noun("movie"))
						|| nounGen.equals(new Noun("home"))
						|| nounGen.equals(new Noun("child")));
			} catch (OptUtilException e) {
				if (LOGGER.isEnabledFor(Level.ERROR))
					LOGGER.error(e.getMessage(), e);
				ex3 = e;
			}
			assertNull(ex3);
		}

		for (int i = 0; i < 10; i++) {
			Throwable ex4 = null;
			try {
				@SuppressWarnings("unused")
				Noun nounGen = OptUtil.generateOptNounExcl(opts, excl2);
			} catch (OptUtilException e) {
				ex4 = e;
			}
			assertNotNull(ex4);
		}

		for (int i = 0; i < 10; i++) {
			Throwable ex5 = null;
			try {
				Noun nounGen = OptUtil.generateOptNounExcl(opts, excl1);
				assertTrue(nounGen.equals(new Noun("child")));
			} catch (OptUtilException e) {
				if (LOGGER.isEnabledFor(Level.ERROR))
					LOGGER.error(e.getMessage(), e);
				ex5 = e;
			}
			assertNull(ex5);
		}

	}

}
