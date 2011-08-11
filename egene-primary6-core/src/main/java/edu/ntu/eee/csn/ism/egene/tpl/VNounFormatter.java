package edu.ntu.eee.csn.ism.egene.tpl;

import edu.ntu.eee.csn.ism.egene.business.Noun;
import edu.ntu.eee.csn.ism.egene.util.Gender;

public class VNounFormatter {

	public String genPlural(String sl) {
		return Noun.generatePlural(sl);
	}

	public String genPronSubj(String sl) {
		return Noun.generatePronounSubj(Gender.NOSEX);
	}

	public String genPronSubjGender(String sl, String strGender) {

		if ("male".equalsIgnoreCase(strGender.trim()))
			return Noun.generatePronounSubj(Gender.MALE);
		else if ("female".equalsIgnoreCase(strGender.trim()))
			return Noun.generatePronounSubj(Gender.FEMALE);
		else
			return Noun.generatePronounSubj(Gender.NOSEX);
	}

	public String genPronObj(String sl) {
		return Noun.generatePronounObj(Gender.NOSEX);
	}

	public String genPronObjGender(String sl, String strGender) {

		if ("male".equalsIgnoreCase(strGender.trim()))
			return Noun.generatePronounObj(Gender.MALE);
		else if ("female".equalsIgnoreCase(strGender.trim()))
			return Noun.generatePronounObj(Gender.FEMALE);
		else
			return Noun.generatePronounObj(Gender.NOSEX);

	}

	public String genPossPron(String sl) {
		return Noun.generatePossPro(Gender.NOSEX);
	}

	public String genPossPronGender(String sl, String strGender) {

		if ("male".equalsIgnoreCase(strGender.trim()))
			return Noun.generatePossPro(Gender.MALE);
		else if ("female".equalsIgnoreCase(strGender.trim()))
			return Noun.generatePossPro(Gender.FEMALE);
		else
			return Noun.generatePossPro(Gender.NOSEX);
		
	}

	public String genPossAdj(String sl) {
		return Noun.generatePossAdj(Gender.NOSEX);
	}

	public String genPossAdjGender(String sl, String strGender) {

		if ("male".equalsIgnoreCase(strGender.trim()))
			return Noun.generatePossAdj(Gender.MALE);
		else if ("female".equalsIgnoreCase(strGender.trim()))
			return Noun.generatePossAdj(Gender.FEMALE);
		else
			return Noun.generatePossAdj(Gender.NOSEX);
		
	}

}
