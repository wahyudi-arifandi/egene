package edu.ntu.eee.csn.ism.egene.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import edu.ntu.eee.csn.ism.egene.config.Config;
import edu.ntu.eee.csn.ism.egene.config.ConfigParam;
import edu.ntu.eee.csn.ism.egene.exception.ConfigException;
import edu.ntu.eee.csn.ism.egene.exception.NounUtilException;

public class NounUtil {

	private static Logger LOGGER = Logger.getLogger(NounUtil.class);

	private Config config = Config.getInstance();

	private LinkedList<Rule> plurals = new LinkedList<Rule>();
	private LinkedList<Rule> singulars = new LinkedList<Rule>();
	/**
	 * The lowercase words that are to be excluded and not processed. This map
	 * can be modified by the users via {@link #addUncountable(String...)}.
	 */
	private final Set<String> uncountables = new HashSet<String>();

	protected class Rule {

		protected final String expression;
		protected final Pattern expressionPattern;
		protected final String replacement;

		protected Rule(String expression, String replacement) {
			this.expression = expression;
			this.replacement = replacement != null ? replacement : "";
			this.expressionPattern = Pattern.compile(this.expression,
					Pattern.CASE_INSENSITIVE);
		}

		/**
		 * Apply the rule against the input string, returning the modified
		 * string or null if the rule didn't apply (and no modifications were
		 * made)
		 * 
		 * @param input
		 *            the input string
		 * @return the modified string if this rule applied, or null if the
		 *         input was not modified by this rule
		 */
		protected String apply(String input) {
			Matcher matcher = this.expressionPattern.matcher(input);
			if (!matcher.find())
				return null;
			return matcher.replaceAll(this.replacement);
		}

		@Override
		public int hashCode() {
			return expression.hashCode();
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == this)
				return true;
			if (obj != null && obj.getClass() == this.getClass()) {
				final Rule that = (Rule) obj;
				if (this.expression.equalsIgnoreCase(that.expression))
					return true;
			}
			return false;
		}

		@Override
		public String toString() {
			return expression + ", " + replacement;
		}
	}

	private static NounUtil instance = null;

	private NounUtil() {
		this.init();
	}

	public static NounUtil getInstance() {
		if (null == NounUtil.instance) {
			synchronized (NounUtil.class) {
				if (null == NounUtil.instance) {
					NounUtil.instance = new NounUtil();
				}
			}
		}
		return NounUtil.instance;
	}

	private void init() {

		this.addPluralize("$", "s");
		this.addPluralize("s$", "s");
		this.addPluralize("(ax|test)is$", "$1es");
		this.addPluralize("(octop|vir)us$", "$1i");
		this.addPluralize("(octop|vir)i$", "$1i"); // already plural
		this.addPluralize("(alias|status)$", "$1es");
		this.addPluralize("(bu)s$", "$1ses");
		this.addPluralize("(buffal|tomat)o$", "$1oes");
		this.addPluralize("([ti])um$", "$1a");
		this.addPluralize("([ti])a$", "$1a"); // already plural
		this.addPluralize("sis$", "ses");
		this.addPluralize("(?:([^f])fe|([lr])f)$", "$1$2ves");
		this.addPluralize("(hive)$", "$1s");
		this.addPluralize("([^aeiouy]|qu)y$", "$1ies");
		this.addPluralize("(x|ch|ss|sh)$", "$1es");
		this.addPluralize("(matr|vert|ind)ix|ex$", "$1ices");
		this.addPluralize("([m|l])ouse$", "$1ice");
		this.addPluralize("([m|l])ice$", "$1ice");
		this.addPluralize("^(ox)$", "$1en");
		this.addPluralize("(quiz)$", "$1zes");
		// Need to check for the following words that are already pluralized:
		this.addPluralize("(people|men|children|sexes|moves|stadiums)$", "$1"); // irregulars
		this.addPluralize("(oxen|octopi|viri|aliases|quizzes)$", "$1"); // special
																		// rules

		this.addSingularize("s$", "");
		this.addSingularize("(s|si|u)s$", "$1s"); // '-us' and '-ss' are
													// already singular
		this.addSingularize("(n)ews$", "$1ews");
		this.addSingularize("([ti])a$", "$1um");
		this.addSingularize(
				"((a)naly|(b)a|(d)iagno|(p)arenthe|(p)rogno|(s)ynop|(t)he)ses$",
				"$1$2sis");
		this.addSingularize("(^analy)ses$", "$1sis");
		this.addSingularize("(^analy)sis$", "$1sis"); // already singular,
														// but ends in 's'
		this.addSingularize("([^f])ves$", "$1fe");
		this.addSingularize("(hive)s$", "$1");
		this.addSingularize("(tive)s$", "$1");
		this.addSingularize("([lr])ves$", "$1f");
		this.addSingularize("([^aeiouy]|qu)ies$", "$1y");
		this.addSingularize("(s)eries$", "$1eries");
		this.addSingularize("(m)ovies$", "$1ovie");
		this.addSingularize("(x|ch|ss|sh)es$", "$1");
		this.addSingularize("([m|l])ice$", "$1ouse");
		this.addSingularize("(bus)es$", "$1");
		this.addSingularize("(o)es$", "$1");
		this.addSingularize("(shoe)s$", "$1");
		this.addSingularize("(cris|ax|test)is$", "$1is"); // already
															// singular, but
															// ends in 's'
		this.addSingularize("(cris|ax|test)es$", "$1is");
		this.addSingularize("(octop|vir)i$", "$1us");
		this.addSingularize("(octop|vir)us$", "$1us"); // already singular,
														// but ends in 's'
		this.addSingularize("(alias|status)es$", "$1");
		this.addSingularize("(alias|status)$", "$1"); // already singular,
														// but ends in 's'
		this.addSingularize("^(ox)en", "$1");
		this.addSingularize("(vert|ind)ices$", "$1ex");
		this.addSingularize("(matr)ices$", "$1ix");
		this.addSingularize("(quiz)zes$", "$1");

		this.loadNounsIrregular();
		// this.addIrregular("person", "people");
		// this.addIrregular("man", "men");
		// this.addIrregular("child", "children");
		// this.addIrregular("sex", "sexes");
		// this.addIrregular("move", "moves");
		// this.addIrregular("stadium", "stadiums");

		this.loadNounsUncountable();
		// this.addUncountable("equipment", "information", "rice", "money",
		// "species", "series", "fish", "sheep");

	}

	private void loadNounsIrregular() throws NounUtilException {

		String nounsIrregularDictFile = this.config.getEgeneCfgDirName()
				+ File.separator
				+ this.config.getString(ConfigParam.NOUNS_IRREGULAR_DICT_FILE);
		File file = new File(nounsIrregularDictFile);
		if (!file.exists()) {
			throw new ConfigException("CAN'T find "
					+ ConfigParam.NOUNS_IRREGULAR_DICT_FILE.getName() + "=["
					+ file.getAbsolutePath() + "]");
		} else {
			if (LOGGER.isDebugEnabled())
				LOGGER.debug("FOUND "
						+ ConfigParam.NOUNS_IRREGULAR_DICT_FILE.getName()
						+ "=[" + file.getAbsolutePath() + "]");
		}

		try {
			Properties prop = new Properties();
			prop.load(new FileInputStream(file));

			Enumeration<Object> enumeration = prop.keys();
			while (enumeration.hasMoreElements()) {
				String key = (String) enumeration.nextElement();
				String val = (String) prop.get(key);
				this.addIrregular(key.trim(), val.trim());
			}

			prop.clear();

		} catch (FileNotFoundException e) {
			if (LOGGER.isEnabledFor(Level.ERROR))
				LOGGER.error(e.getMessage(), e);
			throw new NounUtilException(e.getMessage(), e);
		} catch (IOException e) {
			if (LOGGER.isEnabledFor(Level.ERROR))
				LOGGER.error(e.getMessage(), e);
			throw new NounUtilException(e.getMessage(), e);
		}

	}

	private void loadNounsUncountable() throws NounUtilException {

		String nounsIrregularDictFile = this.config.getEgeneCfgDirName()
				+ File.separator
				+ this.config
						.getString(ConfigParam.NOUNS_UNCOUNTABLE_DICT_FILE);
		File file = new File(nounsIrregularDictFile);
		if (!file.exists()) {
			throw new ConfigException("CAN'T find "
					+ ConfigParam.NOUNS_UNCOUNTABLE_DICT_FILE.getName() + "=["
					+ nounsIrregularDictFile + "]");
		} else {
			if (LOGGER.isDebugEnabled())
				LOGGER.debug("FOUND "
						+ ConfigParam.NOUNS_UNCOUNTABLE_DICT_FILE.getName()
						+ "=[" + file.getAbsolutePath() + "]");
		}

		try {

			InputStreamReader inputStream = new InputStreamReader(
					new FileInputStream(file));
			LineNumberReader reader = new LineNumberReader(inputStream);

			String line = null;
			while (null != (line = reader.readLine())) {
				if (!StringUtils.isEmpty(line))
					this.addUncountable(line);
			}

		} catch (FileNotFoundException e) {
			if (LOGGER.isEnabledFor(Level.ERROR))
				LOGGER.error(e.getMessage(), e);
			throw new NounUtilException(e.getMessage(), e);
		} catch (IOException e) {
			if (LOGGER.isEnabledFor(Level.ERROR))
				LOGGER.error(e.getMessage(), e);
			throw new NounUtilException(e.getMessage(), e);
		}

	}

	public void addPluralize(String rule, String replacement)
			throws NounUtilException {

		Rule pluralizeRule = new Rule(rule, replacement);
		this.plurals.addFirst(pluralizeRule);
	}

	public void addSingularize(String rule, String replacement)
			throws NounUtilException {

		final Rule singularizeRule = new Rule(rule, replacement);
		this.singulars.addFirst(singularizeRule);
	}

	public void addIrregular(String singular, String plural)
			throws NounUtilException {

		// CheckArg.isNotEmpty(singular, "singular rule");
		// CheckArg.isNotEmpty(plural, "plural rule");
		String singularRemainder = singular.length() > 1 ? singular
				.substring(1) : "";
		String pluralRemainder = plural.length() > 1 ? plural.substring(1) : "";
		addPluralize("(" + singular.charAt(0) + ")" + singularRemainder + "$",
				"$1" + pluralRemainder);
		addSingularize("(" + plural.charAt(0) + ")" + pluralRemainder + "$",
				"$1" + singularRemainder);
	}

	public void addUncountable(String... words) {
		if (words == null || words.length == 0)
			return;

		for (String word : words) {
			if (!StringUtils.isEmpty(word))
				this.uncountables.add(word.trim().toLowerCase());
		}
	}

	/**
	 * Returns the plural form of the word in the string.
	 * <p>
	 * Examples:
	 * 
	 * <pre>
	 *   instance.pluralize(&quot;post&quot;)               #=&gt; &quot;posts&quot;
	 *   instance.pluralize(&quot;octopus&quot;)            #=&gt; &quot;octopi&quot;
	 *   instance.pluralize(&quot;sheep&quot;)              #=&gt; &quot;sheep&quot;
	 *   instance.pluralize(&quot;words&quot;)              #=&gt; &quot;words&quot;
	 *   instance.pluralize(&quot;the blue mailman&quot;)   #=&gt; &quot;the blue mailmen&quot;
	 *   instance.pluralize(&quot;CamelOctopus&quot;)       #=&gt; &quot;CamelOctopi&quot;
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @param word
	 *            the word that is to be pluralized.
	 * @return the pluralized form of the word, or the word itself if it could
	 *         not be pluralized
	 * @see #singularize(String)
	 */
	public String pluralize(String word) {
		if (StringUtils.isEmpty(word))
			return word;

		String noun = word.trim().toLowerCase();
		if (noun.length() < 1)
			return noun;

		if (isUncountable(noun))
			return noun;

		for (Rule rule : this.plurals) {
			String result = rule.apply(noun);
			if (result != null)
				return result;
		}
		return noun;
	}

	/**
	 * Returns the singular form of the word in the string.
	 * <p>
	 * Examples:
	 * 
	 * <pre>
	 *   instance.singularize(&quot;posts&quot;)             #=&gt; &quot;post&quot;
	 *   instance.singularize(&quot;octopi&quot;)            #=&gt; &quot;octopus&quot;
	 *   instance.singularize(&quot;sheep&quot;)             #=&gt; &quot;sheep&quot;
	 *   instance.singularize(&quot;words&quot;)             #=&gt; &quot;word&quot;
	 *   instance.singularize(&quot;the blue mailmen&quot;)  #=&gt; &quot;the blue mailman&quot;
	 *   instance.singularize(&quot;CamelOctopi&quot;)       #=&gt; &quot;CamelOctopus&quot;
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @param word
	 *            the word that is to be pluralized.
	 * @return the pluralized form of the word, or the word itself if it could
	 *         not be pluralized
	 * @see #pluralize(String)
	 */
	public String singularize(String word) {
		if (StringUtils.isEmpty(word))
			return word;

		String noun = word.toString().trim();
		if (noun.length() < 1)
			return noun;

		if (isUncountable(noun))
			return noun;

		for (Rule rule : this.singulars) {
			String result = rule.apply(noun);
			if (result != null)
				return result;
		}
		return noun;
	}

	/**
	 * Determine whether the supplied word is considered uncountable by the
	 * {@link #pluralize(String) pluralize} and {@link #singularize(String)
	 * singularize} methods.
	 * 
	 * @param word
	 *            the word
	 * @return true if the plural and singular forms of the word are the same
	 */
	public boolean isUncountable(String word) {
		if (StringUtils.isEmpty(word))
			return false;

		String noun = word.trim().toLowerCase();
		if (noun.length() < 1)
			return false;

		return this.uncountables.contains(noun);
	}

}
