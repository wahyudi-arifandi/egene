package edu.ntu.eee.csn.ism.egene.business;

import edu.ntu.eee.csn.ism.egene.util.VerbUtil;

public class Verb {

	private VerbUtil verbUtil = VerbUtil.getInstance();

	private String simplePresent = null;
	private String simplePast = null;
	private String pastParticiple = null;
	private String gerund = null;
	private String thirdPersonS = null;

	public Verb(String present) {
		this.simplePresent = present;
		this.simplePast = this.genSimplePast(this.simplePresent);
		this.pastParticiple = this.genPastParticiple(this.simplePresent);
		this.gerund = this.genGerund(this.simplePresent);
		this.thirdPersonS = this.genThirdPersonS(this.simplePresent);
	}

	private String genSimplePast(String simplePresent) {
		return this.verbUtil.infinitiveToSimplePast(simplePresent)[0];
	}

	private String genPastParticiple(String simplePresent) {
		return this.verbUtil.infinitiveToPastParticiple(simplePresent)[0];
	}

	private String genGerund(String simplePresent) {
		return this.verbUtil.infinitiveToGerund(simplePresent)[0];
	}

	private String genThirdPersonS(String simplePresent) {
		return this.verbUtil.infinitiveToThirdPersonS(simplePresent);
	}

	public String getSimplePresent() {
		return this.simplePresent;
	}

	public String getSimplePast() {
		return this.simplePast;
	}

	public String getPastParticiple() {
		return this.pastParticiple;
	}

	public String getGerund() {
		return this.gerund;
	}

	public String getThirdPersonS() {
		return this.thirdPersonS;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("simplePresent=").append(this.simplePresent)
				.append("; simplePast=").append(this.simplePast)
				.append("; pastParticiple=").append(this.pastParticiple)
				.append("; gerund=").append(this.gerund)
				.append("; thirdPersonS=").append(this.thirdPersonS);

		return sb.toString();
	}

	@Override
	public boolean equals(Object verb) {

		if (!(verb instanceof Verb))
			return false;

		return this.simplePresent.equals(((Verb) verb).getSimplePresent());
	}

}
