package edu.ntu.eee.csn.ism.egene.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import edu.ntu.eee.csn.ism.egene.config.Config;
import edu.ntu.eee.csn.ism.egene.config.ConfigParam;
import edu.ntu.eee.csn.ism.egene.exception.VerbUtilException;

public class VerbUtil {

	private final Logger LOGGER = Logger.getLogger(VerbUtil.class);
	private WordNet wordNet = WordNet.getInstance();
	private Properties irregular = null;
	private Config config = Config.getInstance();

	private static VerbUtil instance = null;

	private VerbUtil() {
		this.init();
	}

	public static VerbUtil getInstance() {
		if (null == VerbUtil.instance) {
			synchronized (VerbUtil.class) {
				if (null == VerbUtil.instance) {
					VerbUtil.instance = new VerbUtil();
				}
			}
		}
		return VerbUtil.instance;
	}

	private void init() {

		String irregularVerbsDictFileName = this.config.getEgeneCfgDirName()
				+ File.separator
				+ this.config.getString(ConfigParam.VERBS_IRREGULAR_DICT_FILE);
		File irregularVerbLibFile = new File(irregularVerbsDictFileName);
		if (!irregularVerbLibFile.exists()) {
			throw new VerbUtilException("CAN'T find "
					+ ConfigParam.VERBS_IRREGULAR_DICT_FILE.getName() + "=["
					+ irregularVerbLibFile.getAbsolutePath() + "]");
		} else {
			if (LOGGER.isDebugEnabled())
				LOGGER.debug("FOUND "
						+ ConfigParam.VERBS_IRREGULAR_DICT_FILE.getName()
						+ "=[" + irregularVerbLibFile.getAbsolutePath()
						+ "]");
		}

		try {
			this.irregular = new Properties();
			this.irregular.load(new FileInputStream(irregularVerbLibFile));
		} catch (FileNotFoundException e) {
			throw new VerbUtilException(e.getMessage(), e);
		} catch (IOException e) {
			throw new VerbUtilException(e.getMessage(), e);
		}
	}

	/**
	 * Converts the infinitive of a regular verb to simple past.
	 * 
	 * @param verb
	 *            regular verb in infinitive
	 * @return simple past forms of the regular verb
	 */
	private String[] infinitiveToSimplePastReg(String verb) {
		if (StringUtils.isEmpty(verb))
			return new String[0];

		if (verb.matches(".*e")) {
			// If the verb ends in "e", only the letter "d" must be added in
			// order to form the simple past.
			return new String[] { verb + "d" };
		} else if (verb.matches(".*[bcdfghjklmnpqrstvwxyz]y")) {
			// If the verb ends in "y" immediately preceded by a consonant, the
			// "y" is changed to "i" before the ending "ed" is added.
			return new String[] { verb.substring(0, verb.length() - 1) + "ied" };
		} else if (verb.matches(".*[bcdfghjklmnpqrstvwxyz][aeiou]"
				+ "[bcdfghjklmnpqrstvz]")) {
			// If the verb ends in a single consonant other than "w", "x" or "y"
			// immediately preceded by a single vowel, the final consonant must
			// be doubled only if it is a one-syllable verb or if the last
			// syllable is pronounced with the heaviest stress.
			// Since this is hard to check, two forms are created, one with a
			// single consonant and one with a doubled consonant.
			String sp1 = verb + "ed";
			String sp2 = verb + verb.substring(verb.length() - 1) + "ed";
			// WordNet is then used to eliminate misspellings.
			if (this.wordNet.isVerb(sp1))
				if (this.wordNet.isVerb(sp2))
					return new String[] { sp1, sp2 };
				else
					return new String[] { sp1 };
			else if (this.wordNet.isVerb(sp2))
				return new String[] { sp2 };
			else
				return new String[] { sp1 }; // fallback: return first form
		} else {
			// If none of the above cases applies, just add "ed".
			return new String[] { verb + "ed" };
		}
	}

	/**
	 * Converts the infinitive of a verb to 3rd person singular.
	 * 
	 * @param verb
	 *            verb in infinitive
	 * @return 3rd person singular
	 */
	public String infinitiveToThirdPersonS(String verb) {
		if (StringUtils.isEmpty(verb))
			return verb;

		verb = verb.toLowerCase();

		if (verb.equals("have"))
			// "have" is irregular.
			return "has";
		else if (verb.matches(".*(ch|sh|s|x|z|o)"))
			// If the verb ends in "ch", "sh", "s", "x", "z" or "o",
			// append "es".
			return verb + "es";
		else if (verb.matches(".*[bcdfghjklmnpqrstvwxyz]y"))
			// If the verb ends in "y" immediately preceded by a consonant, drop
			// the "y" and append "ies".
			return verb.substring(0, verb.length() - 1) + "ies";
		else
			// If none of the above cases applies, just append "s".
			return verb + "s";
	}

	/**
	 * Converts the infinitive of a verb to its gerund (present progressive).
	 * 
	 * @param verb
	 *            in infinitive
	 * @return gerund
	 */
	public String[] infinitiveToGerund(String verb) {
		if (StringUtils.isEmpty(verb))
			return new String[0];

		verb = verb.toLowerCase();

		if (verb.matches(".*ie")) {
			// If the verb ends in "ie", the "ie" is changed to "y" before the
			// ending "ing" is added.
			return new String[] { verb.substring(0, verb.length() - 2) + "ying" };
		} else if (verb.matches(".*[^e]e")) {
			// If the verb ends in a single "e", the "e" is dropped and the
			// ending "ing" is added.
			return new String[] { verb.substring(0, verb.length() - 1) + "ing" };
		} else if (verb.matches(".*[aeiou][bcdfghjklmnpqrstvz]")) {
			// If the verb ends in a consonant immediately preceded by a vowel,
			// two forms are generated, one with a single consonant and one with
			// a doubled consonant.
			String sp1 = verb + "ing";
			String sp2 = verb + verb.substring(verb.length() - 1) + "ing";
			// WordNet is then used to eliminate misspellings.
			if (this.wordNet.isVerb(sp1))
				if (this.wordNet.isVerb(sp2))
					return new String[] { sp1, sp2 };
				else
					return new String[] { sp1 };
			else if (this.wordNet.isVerb(sp2))
				return new String[] { sp2 };
			else
				return new String[] { sp1 }; // fallback: return first form
		} else {
			// If none of the above cases applies, just add "ing".
			return new String[] { verb + "ing" };
		}
	}

	/**
	 * Converts the infinitive of an arbitrary verb (regular or irregular) to
	 * simple past.
	 * 
	 * @param verb
	 *            verb in infinitive
	 * @return simple past forms of the verb
	 */
	public String[] infinitiveToSimplePast(String verb) {
		if (StringUtils.isEmpty(verb))
			return new String[0];

		verb = verb.toLowerCase();

		// String[] sp = IrregularVerbs.getSimplePast(verb);
		String strSp = this.irregular.getProperty(verb);
		String[] sp = null;
		if (strSp == null) {
			sp = infinitiveToSimplePastReg(verb); // regular verb
		} else {
			sp = strSp.split(" ")[0].split("/");
		}

		return sp;
	}

	/**
	 * Converts the infinitive of an arbitrary verb (regular or irregular) to
	 * past participle.
	 * 
	 * @param verb
	 *            verb in infinitive
	 * @return past participle forms of the verb
	 */
	public String[] infinitiveToPastParticiple(String verb) {
		if (StringUtils.isEmpty(verb))
			return new String[0];

		verb = verb.toLowerCase();

		// String[] sp = IrregularVerbs.getPastParticiple(verb);
		String strSp = this.irregular.getProperty(verb);
		String[] sp = null;
		if (strSp == null) {
			sp = infinitiveToSimplePastReg(verb); // regular verb
		} else {
			sp = strSp.split(" ")[1].split("/");
		}

		return sp;
	}

	/**
	 * Gets all grammatical forms of a verb and drops duplicates.
	 * 
	 * @param verb
	 *            verb in infinitive
	 * @return all verb forms
	 */
	public String[] getAllForms(String verb) {
		if (StringUtils.isEmpty(verb))
			return new String[0];

		HashSet<String> allForms = new HashSet<String>();

		verb = verb.toLowerCase();
		allForms.add(verb);
		allForms.add(infinitiveToThirdPersonS(verb));
		for (String gerund : infinitiveToGerund(verb))
			allForms.add(gerund);
		for (String simplePast : infinitiveToSimplePast(verb))
			allForms.add(simplePast);
		for (String pastParticiple : infinitiveToPastParticiple(verb))
			allForms.add(pastParticiple);

		return allForms.toArray(new String[allForms.size()]);
	}

}
