package edu.ntu.eee.csn.ism.egene.business;

import edu.ntu.eee.csn.ism.egene.util.Gender;
import edu.ntu.eee.csn.ism.egene.util.NounUtil;

public class Noun {

	private String sl = null;
	private String pl = null;
	private String proSubj = null;
	private String proObj = null;
	private String possPro = null;
	private String possAdj = null;
	private Gender gender = null;

	public Noun(String aSingular) {
		this.sl = aSingular.toLowerCase().trim();

		this.gender = Gender.NOSEX;
		this.pl = Noun.generatePlural(this.sl);
		this.proSubj = Noun.generatePronounSubj(this.gender);
		this.proObj = Noun.generatePronounObj(this.gender);
		this.possPro = Noun.generatePossPro(this.gender);
		this.possAdj = Noun.generatePossAdj(this.gender);
	}

	public Noun(String name, Gender aGender) {
		this.sl = name.toLowerCase().trim();
		this.gender = aGender;

		this.pl = Noun.generatePlural(this.sl);
		this.proSubj = Noun.generatePronounSubj(this.gender);
		this.proObj = Noun.generatePronounObj(this.gender);
		this.possPro = Noun.generatePossPro(this.gender);
		this.possAdj = Noun.generatePossAdj(this.gender);
	}

	public static String generatePlural(String aSingular) {

		return NounUtil.getInstance().pluralize(aSingular);

	}

	public static  String generatePronounSubj(Gender aGender) {
		String result = null;
		if (Gender.MALE.equals(aGender)) {
			result = "he";
		} else if (Gender.FEMALE.equals(aGender)) {
			result = "she";
		} else {
			// NounGender.NOSEX.equals(gender)
			result = "it";
		}

		return result;
	}

	public static  String generatePronounObj(Gender aGender) {
		String result = null;
		if (Gender.MALE.equals(aGender)) {
			result = "him";
		} else if (Gender.FEMALE.equals(aGender)) {
			result = "her";
		} else {
			// NounGender.NOSEX.equals(gender)
			result = "it";
		}

		return result;
	}

	public static  String generatePossPro(Gender aGender) {
		String result = null;
		if (Gender.MALE.equals(aGender)) {
			result = "his";
		} else if (Gender.FEMALE.equals(aGender)) {
			result = "hers";
		} else {
			// NounGender.NOSEX.equals(gender)
			result = "its";
		}

		return result;
	}

	public static String generatePossAdj(Gender aGender) {
		String result = null;
		if (Gender.MALE.equals(aGender)) {
			result = "his";
		} else if (Gender.FEMALE.equals(aGender)) {
			result = "her";
		} else {
			// NounGender.NOSEX.equals(gender)
			result = "its";
		}

		return result;
	}

	public String getPlural() {
		return this.pl;
	}

	public String getSingular() {
		return this.sl;
	}

	public String getPronounSubj() {
		return this.proSubj;
	}

	public String getPronounObj() {
		return this.proObj;
	}

	public String getPossPron() {
		return this.possPro;
	}

	public String getPossAdj() {
		return this.possAdj;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();

		sb.append("singular=").append(this.sl).append("; plural=")
				.append(this.pl).append("; pronSubj=").append(this.proSubj)
				.append("; pronObj=").append(this.proObj).append("; possAdj=")
				.append(this.possAdj).append("; possPron=")
				.append(this.possPro);

		return sb.toString();
	}

	@Override
	public boolean equals(Object noun) {
		if (!(noun instanceof Noun))
			return false;

		return this.sl.equals(((Noun) noun).getSingular());
	}

}
