package edu.ntu.eee.csn.ism.egene.tpl;

import edu.ntu.eee.csn.ism.egene.util.VerbUtil;

public class VVerbFormatter {

	private VerbUtil verbUtil = VerbUtil.getInstance();

	public String genV2(String v1) {
		return this.verbUtil.infinitiveToSimplePast(v1)[0];
	}

	public String genV3(String v1) {
		return this.verbUtil.infinitiveToPastParticiple(v1)[0];
	}

	public String genVs(String v1) {
		return this.verbUtil.infinitiveToThirdPersonS(v1);
	}

	public String genVing(String v1) {
		return this.verbUtil.infinitiveToGerund(v1)[0];
	}

}
