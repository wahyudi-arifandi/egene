package edu.ntu.eee.csn.ism.egene.util;

import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.apache.log4j.Logger;

import edu.ntu.eee.csn.ism.egene.util.VerbUtil;

public class VerbUtilTest extends TestCase {

	private static Logger LOGGER = Logger.getLogger(VerbUtilTest.class);
	private VerbUtil verbUtil = VerbUtil.getInstance();

	/**
	 * Constructor
	 */
	public VerbUtilTest() {
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
	public VerbUtilTest(String name) {
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
		TestSuite testSuite = new TestSuite(VerbUtilTest.class);
		return testSuite;
	}

	public void testInfinitiveToThirdPersonS() {
		assertNull(this.verbUtil.infinitiveToThirdPersonS(null));
		assertEquals("", this.verbUtil.infinitiveToThirdPersonS(""));
		assertEquals("has", this.verbUtil.infinitiveToThirdPersonS("have"));
		assertEquals("does", this.verbUtil.infinitiveToThirdPersonS("do"));
		assertEquals("reaches", this.verbUtil.infinitiveToThirdPersonS("reach"));
		assertEquals("pushes", this.verbUtil.infinitiveToThirdPersonS("push"));
		assertEquals("misses", this.verbUtil.infinitiveToThirdPersonS("miss"));
		assertEquals("mixes", this.verbUtil.infinitiveToThirdPersonS("mix"));
		assertEquals("buzzes", this.verbUtil.infinitiveToThirdPersonS("buzz"));
		assertEquals("flies", this.verbUtil.infinitiveToThirdPersonS("fly"));
		assertEquals("buys", this.verbUtil.infinitiveToThirdPersonS("buy"));
		assertEquals("puts", this.verbUtil.infinitiveToThirdPersonS("put"));
	}

	public void testInfinitiveToGerund() {

		String[] strNull = this.verbUtil.infinitiveToGerund(null);
		assertEquals(0, strNull.length);

		String[] strEmpty = this.verbUtil.infinitiveToGerund("");
		assertEquals(0, strEmpty.length);

		String[] strHave = this.verbUtil.infinitiveToGerund("have");
		assertEquals(1, strHave.length);
		assertEquals("having", strHave[0]);

		String[] strLie = this.verbUtil.infinitiveToGerund("lie");
		assertEquals(1, strLie.length);
		assertEquals("lying", strLie[0]);

		String[] strSleep = this.verbUtil.infinitiveToGerund("sleep");
		assertEquals(1, strSleep.length);
		assertEquals("sleeping", strSleep[0]);

		String[] strPut = this.verbUtil.infinitiveToGerund("put");
		assertEquals(2, strPut.length);
		assertTrue("putting".equals(strPut[0]) || "puting".equals(strPut[0]));
		assertTrue("putting".equals(strPut[1]) || "puting".equals(strPut[1]));

		String[] strThink = this.verbUtil.infinitiveToGerund("think");
		assertEquals(1, strThink.length);
		assertEquals("thinking", strThink[0]);

	}

	public void testInfinitiveToSimplePast() {

		String[] strNull = this.verbUtil.infinitiveToSimplePast(null);
		assertEquals(0, strNull.length);

		String[] strEmpty = this.verbUtil.infinitiveToSimplePast("");
		assertEquals(0, strEmpty.length);

		String[] strDo = this.verbUtil.infinitiveToSimplePast("do");
		assertEquals(1, strDo.length);
		assertEquals("did", strDo[0]);

		String[] strBake = this.verbUtil.infinitiveToSimplePast("bake");
		assertEquals(1, strBake.length);
		assertEquals("baked", strBake[0]);

		String[] strFry = this.verbUtil.infinitiveToSimplePast("fry");
		assertEquals(1, strFry.length);
		assertEquals("fried", strFry[0]);

		String[] strFix = this.verbUtil.infinitiveToSimplePast("fix");
		assertEquals(1, strFix.length);
		assertEquals("fixed", strFix[0]);

		String[] strAdmit = this.verbUtil.infinitiveToSimplePast("admit");
		assertEquals(2, strAdmit.length);
		assertTrue("admitted".equals(strAdmit[0])
				|| "admited".equals(strAdmit[0]));
		assertTrue("admitted".equals(strAdmit[1])
				|| "admited".equals(strAdmit[1]));

		String[] strTurn = this.verbUtil.infinitiveToSimplePast("turn");
		assertEquals(1, strTurn.length);
		assertEquals("turned", strTurn[0]);
	}

	public void test() {

		String[] strNull = this.verbUtil.infinitiveToPastParticiple(null);
		assertEquals(0, strNull.length);

		String[] strEmpty = this.verbUtil.infinitiveToPastParticiple("");
		assertEquals(0, strEmpty.length);

		String[] strDo = this.verbUtil.infinitiveToPastParticiple("do");
		assertEquals(1, strDo.length);
		assertEquals("done", strDo[0]);

		String[] strBake = this.verbUtil.infinitiveToPastParticiple("bake");
		assertEquals(1, strBake.length);
		assertEquals("baked", strBake[0]);

		String[] strFry = this.verbUtil.infinitiveToPastParticiple("fry");
		assertEquals(1, strFry.length);
		assertEquals("fried", strFry[0]);

		String[] strFix = this.verbUtil.infinitiveToPastParticiple("fix");
		assertEquals(1, strFix.length);
		assertEquals("fixed", strFix[0]);

		String[] strAdmit = this.verbUtil.infinitiveToPastParticiple("admit");
		assertEquals(2, strAdmit.length);
		assertTrue("admitted".equals(strAdmit[0])
				|| "admited".equals(strAdmit[0]));
		assertTrue("admitted".equals(strAdmit[1])
				|| "admited".equals(strAdmit[1]));

		String[] strTurn = this.verbUtil.infinitiveToPastParticiple("turn");
		assertEquals(1, strTurn.length);
		assertEquals("turned", strTurn[0]);
	}

	public void testGetAllForms() {

		String[] strNull = this.verbUtil.getAllForms(null);
		assertEquals(0, strNull.length);

		String[] strEmpty = this.verbUtil.getAllForms("");
		assertEquals(0, strEmpty.length);

		String[] strDo = this.verbUtil.getAllForms("do");
		assertEquals(5, strDo.length);
		for (int i = 0; i < strDo.length; i++)
			assertNotNull(strDo[i]);
	}
}
