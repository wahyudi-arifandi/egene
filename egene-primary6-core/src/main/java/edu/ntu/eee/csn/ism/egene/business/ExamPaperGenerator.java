package edu.ntu.eee.csn.ism.egene.business;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import edu.ntu.eee.csn.ism.egene.exception.EgeneCoreException;
import edu.ntu.eee.csn.ism.egene.util.Gender;
import edu.ntu.eee.csn.ism.egene.util.NumberUtil;
import edu.ntu.eee.csn.ism.egene.util.StringVarUtil;
import edu.ntu.eee.csn.ism.egene.util.TemplateUtil;

public class ExamPaperGenerator {

	private static Logger LOGGER = Logger.getLogger(ExamPaperGenerator.class);

	private String topic = null;
	private Map<String, TemplateUtil> hTemplateGen = null;

	private Map<String, Noun> hNoun = null;
	private Map<String, Verb> hVerb = null;

	private Map<String, String> hStr = null;

	private Map<String, String> hTpl = null;

	private Map<String, Integer> hInt = null;
	private Map<String, Double> hDouble = null;

	private Map<String, String> hOpt = null;

	private static final String[] VALID_NOUN_TYPE = { "sl", "pl", "prosubj",
			"proobj", "posspro", "possadj" };
	private static final String[] VALID_VERB_TYPE = { "v1", "v2", "v3",
			"gerund", "es" };

	public ExamPaperGenerator(String topic) {
		this.hTemplateGen = new Hashtable<String, TemplateUtil>();

		this.hNoun = new Hashtable<String, Noun>();
		this.hVerb = new Hashtable<String, Verb>();

		this.hStr = new Hashtable<String, String>();

		this.hTpl = new Hashtable<String, String>();

		this.hInt = new Hashtable<String, Integer>();
		this.hDouble = new Hashtable<String, Double>();

		this.hOpt = new Hashtable<String, String>();

		this.topic = topic;
	}

	public synchronized String generate() throws EgeneCoreException {
		String res = null;

		if (LOGGER.isDebugEnabled())
			LOGGER.debug("Generate ExamPaper for topic=[" + this.topic + "]");
		String template = this.getTemplate(this.topic);
		res = this.compileTemplate(template);

		this.close();
		return res;
	}

	/**
	 * $string(varName,[ns|np|prs|pro|pospr|possadj|v1|v2|v3|oth])
	 * 
	 * @param template
	 * @return
	 */
	private String getTemplate(String tableName) throws EgeneCoreException {

		TemplateUtil strGen = null;
		if (!this.hTemplateGen.containsKey(tableName)) {
			strGen = new TemplateUtil(tableName);
			this.hTemplateGen.put(tableName, strGen);
		} else {
			strGen = this.hTemplateGen.get(tableName);
		}

		return strGen.retrieveTemplate();

	}

	private String compileTemplate(String template) {

		if (LOGGER.isDebugEnabled())
			LOGGER.debug("Template=[" + template + "]");

		List<String> vars = StringVarUtil.getVarsFromTemplate(template);
		if (null != vars && vars.size() == 0) {
			if (LOGGER.isDebugEnabled())
				LOGGER.debug("STOP recursive. Template contains no variable.");

			return template;
		} else {

			if (LOGGER.isDebugEnabled())
				LOGGER.debug("Vars=[" + vars.toString() + "]");
			for (int i = 0; i < vars.size(); i++) {

				try {
					String var = vars.get(i);
					if (LOGGER.isDebugEnabled())
						LOGGER.debug("Var=[" + var + "]");

					String val = this.evaluateVar(var);
					if (LOGGER.isDebugEnabled())
						LOGGER.debug("Val=[" + val + "]");

					template = template.replace(var, val);
				} catch (EgeneCoreException e) {
					if (LOGGER.isEnabledFor(Level.ERROR))
						LOGGER.error(e.getMessage(), e);
				}

			}
		}

		return template;

	}

	private String evaluateVar(String var) throws EgeneCoreException {

		String res = "";
		String varFunctionName = StringVarUtil.getVarFunctionName(var);

		if ("int".equalsIgnoreCase(varFunctionName)) {
			if (LOGGER.isDebugEnabled())
				LOGGER.debug("varType=int");
			// $int(<varName>,<min>,<max>[,<mul>])
			res = this.generateInt(var);

		} else if ("double".equalsIgnoreCase(varFunctionName)) {
			if (LOGGER.isDebugEnabled())
				LOGGER.debug("varType=double");
			// $double(<varName>,<min>,<max>,<commaPos>[,<mul>])
			res = this.generateDouble(var);

		} else if ("str".equalsIgnoreCase(varFunctionName)) {
			if (LOGGER.isDebugEnabled())
				LOGGER.debug("varType=str");
			// $str(<varName>,<tableName>,noun,<sl|pl|prosubj|proobj|posspro|possadj>[,<male|female|nosex>])
			// $str(<varName>,<tableName>,verb,<v1|v2|v3|gerund|es>)
			// $str(<varName>,<tableName>)
			res = this.generateStr(var);

		} else if ("tpl".equalsIgnoreCase(varFunctionName)) {
			if (LOGGER.isDebugEnabled())
				LOGGER.debug("varType=tpl");
			// $tpl(<varName>,<tableName>)
			res = this.generateTpl(var);

		} else if ("opt".equalsIgnoreCase(varFunctionName)) {
			if (LOGGER.isDebugEnabled())
				LOGGER.debug("varType=opt");
			// $opt(<varName>,<tableName>)
			res = this.generateOpt(var);

		} else {
			if (LOGGER.isDebugEnabled())
				LOGGER.debug("misc varType=[" + varFunctionName + "]");
			res = var;
		}

		return res;
	}

	/**
	 * $int(varName,min,max,mul)
	 * 
	 * @return
	 */
	private String generateInt(String var) throws EgeneCoreException {
		// $int(<varName>,<0|1>,<min>,<max>[,<mul>])
		
		return null;

//		List<String> params = StringVarUtil.getVarParams(var);
//		if (LOGGER.isDebugEnabled())
//			LOGGER.debug("var-params=[" + params.toString() + "]");
//		int paramSize = params.size();
//
//		if (paramSize < 1)
//			throw new GeneratorException(
//					"CAN'T find varName. paramSize equals to 0");
//
//		String name = params.get(0);
//		Integer intGen = this.hInt.get(name);
//
//		if (null == intGen) {
//			if (LOGGER.isDebugEnabled())
//				LOGGER.debug("generate new");
//
//			try {
//				if (paramSize == 4) {
//					if (LOGGER.isDebugEnabled())
//						LOGGER.debug("paramSize=4");
//					int unique = Integer.parseInt(params.get(1));
//					int min = Integer.parseInt(params.get(2));
//					int max = Integer.parseInt(params.get(3));
//					if (LOGGER.isDebugEnabled())
//						LOGGER.debug("unique=" + unique + "; min=" + min
//								+ "; max=" + max);
//
//					if (unique != 0) {
//						int x = 0;
//						for (int i = 0; i < this.hInt.size(); i++) {
//							int num = this.hInt.get(i);
//							if (num >= min && num <= max)
//								x++;
//						}
//
//						if ((max - min + 1) <= x)
//							throw new GeneratorException(
//									"CAN'T generate unique integer in range "
//											+ min + "-" + max
//											+ ". current generated = "
//											+ this.hInt.toString());
//					}
//
//					boolean got = false;
//					while (!got) {
//						intGen = new Integer(NumberUtil.generateInt(min, max));
//						if (unique != 0) {
//							got = this.isUniqueInt(intGen);
//						} else {
//							got = true;
//						}
//					}
//
//				} else if (paramSize == 5) {
//					if (LOGGER.isDebugEnabled())
//						LOGGER.debug("paramSize=5");
//					int unique = Integer.parseInt(params.get(1));
//					int min = Integer.parseInt(params.get(2));
//					int max = Integer.parseInt(params.get(3));
//					String[] strMul = params.get(4).split("&");
//					int[] mul = new int[strMul.length];
//					for (int i = 0; i < strMul.length; i++) {
//						if (StringUtils.isNumeric(strMul[i])) {
//							mul[i] = Integer.parseInt(strMul[i]);
//						} else {
//							Integer tmp = this.hInt.get(strMul[i]);
//							if (null == tmp) {
//								mul[i] = 1;
//							} else {
//								mul[i] = tmp.intValue();
//							}
//						}
//					}
//					if (LOGGER.isDebugEnabled())
//						LOGGER.debug("unique=" + unique + "; min=" + min
//								+ "; max=" + max + "; mul="
//								+ Codec.intArrayToString(mul));
//
//					if (unique != 0) {
//						int x = 0;
//						for (int i = 0; i < this.hInt.size(); i++) {
//							int num = this.hInt.get(i);
//							if (num >= min && num <= max
//									&& NumberUtil.isMul(num, mul))
//								;
//							x++;
//						}
//
//						if ((max - min + 1) <= NumberUtil.lcm(mul))
//							throw new GeneratorException(
//									"CAN'T generate unique integer in range "
//											+ min + "-" + max
//											+ ". current generated = "
//											+ this.hInt.toString());
//					}
//
//					boolean got = false;
//					while (!got) {
//						intGen = new Integer(NumberUtil.generateIntMul(min, max,
//								mul));
//						if (unique != 0) {
//							got = this.isUniqueInt(intGen);
//						} else {
//							got = true;
//						}
//					}
//
//				} else {
//
//					throw new GeneratorException(
//							"INVALID number of parameters for $int. "
//									+ "It MUST have 4 (<varName>,<0|1>,<min>,<max>) or 5 (<varName>,<0|1>,<min>,<max>,<mul>) parameters. "
//									+ "ACTUAL number of parameters: "
//									+ paramSize);
//
//				}
//			} catch (RuntimeException e) {
//				throw new GeneratorException(e.getMessage(), e);
//			}
//			this.hInt.put(name, intGen);
//		} else {
//			if (LOGGER.isDebugEnabled())
//				LOGGER.debug("take from cache");
//
//		}
//
//		if (LOGGER.isDebugEnabled())
//			LOGGER.debug("generated: " + intGen.toString());
//		return intGen.toString();
	}

	/**
	 * $double(varName,min,max,commaPosFromRight,mul)
	 * 
	 * @return
	 */
	private String generateDouble(String var) throws EgeneCoreException {
		// $double(<varName>,<0|1>,<min>,<max>,<commaPos>[,<mul>])

		return null;
		
//		List<String> params = StringVarUtil.getVarParams(var);
//		if (LOGGER.isDebugEnabled())
//			LOGGER.debug("var-params=[" + params.toString() + "]");
//		int paramSize = params.size();
//
//		if (paramSize < 1)
//			throw new GeneratorException(
//					"CAN'T find varName. paramSize equals to 0");
//
//		String name = params.get(0);
//		Double dblGen = this.hDouble.get(name);
//
//		if (null == dblGen) {
//			if (LOGGER.isDebugEnabled())
//				LOGGER.debug("generate new");
//
//			try {
//				if (paramSize == 5) {
//					if (LOGGER.isDebugEnabled())
//						LOGGER.debug("paramSize=5");
//					int unique = Integer.parseInt(params.get(1));
//					int min = Integer.parseInt(params.get(2));
//					int max = Integer.parseInt(params.get(3));
//					int commaPos = Integer.parseInt(params.get(4));
//					if (LOGGER.isDebugEnabled())
//						LOGGER.debug("unique=" + unique + "; min=" + min
//								+ "; max=" + max);
//
//					if (unique != 0) {
//						int x = 0;
//						for (int i = 0; i < this.hDouble.size(); i++) {
//							double num = this.hDouble.get(i);
//							int iNum = NumberUtil.removeDecimal(num);
//							if (iNum >= min && iNum <= max)
//								x++;
//						}
//
//						if ((max - min + 1) <= x)
//							throw new GeneratorException(
//									"CAN'T generate unique integer in range "
//											+ min + "-" + max
//											+ ". current generated = "
//											+ this.hInt.toString());
//					}
//
//					boolean got = false;
//					while (!got) {
//						dblGen = new Double(NumberUtil.generateDouble(min, max,
//								commaPos));
//						if (unique != 0) {
//							got = this.isUniqueDouble(dblGen);
//						} else {
//							got = true;
//						}
//					}
//
//				} else if (paramSize == 6) {
//					if (LOGGER.isDebugEnabled())
//						LOGGER.debug("paramSize=6");
//					int unique = Integer.parseInt(params.get(1));
//					int min = Integer.parseInt(params.get(2));
//					int max = Integer.parseInt(params.get(3));
//					int commaPos = Integer.parseInt(params.get(4));
//					String[] strMul = params.get(5).split("&");
//					int[] mul = new int[strMul.length];
//					for (int i = 0; i < strMul.length; i++) {
//						if (StringUtils.isNumeric(strMul[i])) {
//							mul[i] = Integer.parseInt(strMul[i]);
//						} else {
//							Double tmp = this.hDouble.get(strMul[i]);
//							if (null == tmp) {
//								mul[i] = 1;
//							} else {
//								mul[i] = NumberUtil.removeDecimal(tmp
//										.doubleValue());
//							}
//						}
//					}
//					if (LOGGER.isDebugEnabled())
//						LOGGER.debug("unique=" + unique + "; min=" + min
//								+ "; max=" + max + "; mul="
//								+ Codec.intArrayToString(mul));
//
//					if (unique != 0) {
//						int x = 0;
//						for (int i = 0; i < this.hDouble.size(); i++) {
//							int num = NumberUtil.removeDecimal(this.hDouble
//									.get(i).doubleValue());
//							if (num >= min && num <= max
//									&& NumberUtil.isMul(num, mul))
//								;
//							x++;
//						}
//
//						if ((max - min + 1) <= NumberUtil.lcm(mul))
//							throw new GeneratorException(
//									"CAN'T generate unique integer in range "
//											+ min + "-" + max
//											+ ". current generated = "
//											+ this.hInt.toString());
//					}
//
//					boolean got = false;
//					while (!got) {
//						dblGen = new Double(NumberUtil.generateDoubleMul(min, max,
//								commaPos, mul));
//						if (unique != 0) {
//							got = this.isUniqueDouble(dblGen);
//						} else {
//							got = true;
//						}
//
//					}
//
//				} else {
//					throw new GeneratorException(
//							"INVALID number of parameters for $double. "
//									+ "It MUST have 5 (<varName>,<0|1>,<min>,<max>,<commaPos>) or 5 (<varName>,<0|1>,<min>,<max>,<commaPos>,<mul>) parameters. "
//									+ "ACTUAL number of parameters: "
//									+ paramSize);
//
//				}
//			} catch (RuntimeException e) {
//				throw new GeneratorException(e.getMessage(), e);
//			}
//			this.hDouble.put(name, dblGen);
//		} else {
//			if (LOGGER.isDebugEnabled())
//				LOGGER.debug("take from cache");
//
//		}
//
//		if (LOGGER.isDebugEnabled())
//			LOGGER.debug("generated: " + dblGen.toString());
//
//		return dblGen.toString();
	}

	private String generateStr(String var) throws EgeneCoreException {
		// $str(<varName>,<tableName>,noun,<sl|pl|prosubj|proobj|posspro|possadj>[,<male|female|nosex>])
		// $str(<varName>,<tableName>,verb,<v1|v2|v3|gerund|es>)
		// $str(<varName>,<tableName>)

		List<String> params = StringVarUtil.getVarParams(var);
		if (LOGGER.isDebugEnabled())
			LOGGER.debug("var-params=[" + params.toString() + "]");
		int paramSize = params.size();

		if (paramSize < 1)
			throw new EgeneCoreException(
					"CAN'T find varName. paramSize equals to 0");

		// paramSize >= 1
		String name = params.get(0);

		String strGen = null;
		if (paramSize == 1) {
			strGen = this.hStr.get(name);
			if (null == strGen)
				strGen = var;

			return strGen;
		}

		// paramSize > 1
		if (null == strGen) {

			String tableName = params.get(1);
			if (paramSize == 2) {
				// $str(<varName>,<tableName>)

				boolean got = false;
				while (!got) {
					strGen = this.getTemplate(tableName);
					got = this.isUniqueStr(strGen);
				}
				this.hStr.put(name, strGen);

			} else { // paramSize > 2
				// $str(<varName>,<tableName>,noun,<sl|pl|prosubj|proobj|posspro|possadj>[,<male|female|nosex>])
				// $str(<varName>,<tableName>,verb,<v1|v2|v3|gerund|es>)

				String strType = params.get(2);
				if ("noun".equalsIgnoreCase(strType)) {

					Noun noun = this.hNoun.get(name);
					if (null == noun) {

						boolean got = false;
						while (!got) {
							strGen = this.getTemplate(tableName);
							got = this.isUniqueNoun(strGen);
						}

						Gender nounGender = Gender.NOSEX;
						if (paramSize > 4) {
							try {
								nounGender = Gender.valueOf(params.get(4));
							} catch (RuntimeException e) {
								throw new EgeneCoreException(
										"INVALID nounGender. It must be either male, female, or nosex, ACTUAL nounGender: "
												+ params.get(4)
												+ "; EXCEPTION: "
												+ e.getMessage(), e);
							}
						}
						noun = new Noun(strGen, nounGender);
					}

					String nounType = "sl";
					if (paramSize > 3) {
						nounType = params.get(3);
					}

					if (paramSize > 5) {
						throw new EgeneCoreException(
								"INVALID number of parameters for $str, strType=noun. It MUST NOT more than 5. ACTUAL paramSize: "
										+ paramSize);
					}

					if (ExamPaperGenerator.VALID_NOUN_TYPE[0]
							.equalsIgnoreCase(nounType)) {
						strGen = noun.getSingular();

					} else if (ExamPaperGenerator.VALID_NOUN_TYPE[1]
							.equalsIgnoreCase(nounType)) {
						strGen = noun.getPlural();

					} else if (ExamPaperGenerator.VALID_NOUN_TYPE[2]
							.equalsIgnoreCase(nounType)) {
						strGen = noun.getPronounSubj();

					} else if (ExamPaperGenerator.VALID_NOUN_TYPE[3]
							.equalsIgnoreCase(nounType)) {
						strGen = noun.getPronounObj();

					} else if (ExamPaperGenerator.VALID_NOUN_TYPE[4]
							.equalsIgnoreCase(nounType)) {
						strGen = noun.getPossPron();

					} else if (ExamPaperGenerator.VALID_NOUN_TYPE[5]
							.equalsIgnoreCase(nounType)) {
						strGen = noun.getPossAdj();

					} else {
						strGen = noun.getSingular();
						if (LOGGER.isEnabledFor(Level.ERROR)) {
							LOGGER.error("INVALID nounType. It must be either sl, pl, prosubj, proobj, posspro, or possadj. ACTUAL nounType:"
									+ nounType);
						}
					}

					this.hNoun.put(name, noun);

				} else if ("verb".equalsIgnoreCase(strType)) {

					Verb verb = this.hVerb.get(name);
					if (null == verb) {
						boolean got = false;
						while (!got) {
							strGen = this.getTemplate(tableName);
							got = this.isUniqueVerb(strGen);
						}
						verb = new Verb(strGen);
					}

					String nounType = "vl";
					if (paramSize > 3) {
						nounType = params.get(3);
					}

					if (paramSize > 4) {
						throw new EgeneCoreException(
								"INVALID number of parameters for $str, strType=verb. It MUST NOT more than 4. ACTUAL paramSize: "
										+ paramSize);
					}

					if (ExamPaperGenerator.VALID_VERB_TYPE[0]
							.equalsIgnoreCase(nounType)) {
						strGen = verb.getSimplePresent();

					} else if (ExamPaperGenerator.VALID_VERB_TYPE[1]
							.equalsIgnoreCase(nounType)) {
						strGen = verb.getSimplePast();

					} else if (ExamPaperGenerator.VALID_VERB_TYPE[2]
							.equalsIgnoreCase(nounType)) {
						strGen = verb.getPastParticiple();

					} else if (ExamPaperGenerator.VALID_VERB_TYPE[3]
							.equalsIgnoreCase(nounType)) {
						strGen = verb.getGerund();

					} else if (ExamPaperGenerator.VALID_VERB_TYPE[4]
							.equalsIgnoreCase(nounType)) {
						strGen = verb.getThirdPersonS();

					} else {
						strGen = verb.getSimplePresent();
						if (LOGGER.isEnabledFor(Level.ERROR)) {
							LOGGER.error("INVALID nounType. It must be either v1, v2, v2, gerund, or es. ACTUAL nounType:"
									+ nounType);
						}
					}

					this.hVerb.put(name, verb);

				} else {

					throw new EgeneCoreException(
							"INVALID strType. It must be either noun or verb. ACTUAL strType: "
									+ strType);
				}

			}
		}

		return strGen;
	}

	private String generateTpl(String var) throws EgeneCoreException {
		// $tpl(<varName>,<tableName>)

		List<String> params = StringVarUtil.getVarParams(var);
		if (LOGGER.isDebugEnabled())
			LOGGER.debug("var-params=[" + params.toString() + "]");
		int paramSize = params.size();

		if (paramSize < 1)
			throw new EgeneCoreException(
					"CAN'T find varName. paramSize equals to 0");
		String name = params.get(0);

		String tplGen = this.hTpl.get(name);
		if (null == tplGen) {
			if (paramSize != 2)
				throw new EgeneCoreException(
						"INVALID number of parameters for $tpl. It MUST have 2 (<varName>,<tableName>). ACTUAL number of parameters: "
								+ params.size());

			try {

				ExamPaperGenerator epgen = new ExamPaperGenerator(params.get(1));
				epgen.generate();

			} catch (RuntimeException e) {
				throw new EgeneCoreException(e.getMessage(), e);
			}
			this.hTpl.put(name, tplGen);
		}

		return tplGen;
	}

	private String generateOpt(String var) throws EgeneCoreException {
		// $opt(<varName>,<choice>)

		List<String> params = StringVarUtil.getVarParams(var);
		if (LOGGER.isDebugEnabled())
			LOGGER.debug("var-params=[" + params.toString() + "]");
		int paramSize = params.size();

		if (paramSize < 1)
			throw new EgeneCoreException(
					"CAN'T find varName. paramSize equals to 0");
		String name = params.get(0);

		String optGen = this.hOpt.get(name);
		if (null == optGen) {
			if (paramSize != 2)
				throw new EgeneCoreException(
						"INVALID number of parameters for $tpl. It MUST have 2 (<varName>,<choice>). ACTUAL number of parameters: "
								+ params.size());

			try {

				String[] choices = params.get(2).split("|");
				boolean got = false;
				while (!got) {
					int idx = NumberUtil.generateInt(0, choices.length - 1);
					optGen = choices[idx];
					got = this.isUniqueOpt(optGen);
				}

			} catch (RuntimeException e) {
				throw new EgeneCoreException(e.getMessage(), e);
			}
			this.hOpt.put(name, optGen);
		}

		return optGen;

	}

	private void close() {
		Set<String> keys = this.hTemplateGen.keySet();
		Iterator<String> iterator = keys.iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			TemplateUtil strGen = this.hTemplateGen.get(key);
			strGen.close();
		}
		this.hTemplateGen.clear();
		this.hNoun.clear();
		this.hVerb.clear();
		this.hStr.clear();
		this.hTpl.clear();
		this.hInt.clear();
		this.hDouble.clear();
		this.hOpt.clear();
	}

	private boolean isUniqueNoun(String noun) {

		Set<String> set = this.hNoun.keySet();
		Iterator<String> iterator = set.iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			Noun val = this.hNoun.get(key);
			if (val.getSingular().equals(noun)) {
				return false;
			}
		}

		return true;
	}

	private boolean isUniqueVerb(String verb) {

		Set<String> set = this.hVerb.keySet();
		Iterator<String> iterator = set.iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			Verb val = this.hVerb.get(key);
			if (val.getSimplePresent().equals(verb)) {
				return false;
			}
		}

		return true;
	}

	private boolean isUniqueStr(String str) {

		Set<String> set = this.hStr.keySet();
		Iterator<String> iterator = set.iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			String val = this.hStr.get(key);
			if (val.equals(str)) {
				return false;
			}
		}

		return true;
	}

	// private boolean isUniqueTpl(String noun) {
	// return true;
	// }

	@SuppressWarnings("unused")
	private boolean isUniqueInt(Integer integer) {

		Set<String> set = this.hInt.keySet();
		Iterator<String> iterator = set.iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			Integer val = this.hInt.get(key);
			if (val.equals(integer)) {
				return false;
			}
		}

		return true;
	}

	@SuppressWarnings("unused")
	private boolean isUniqueDouble(Double dbl) {

		Set<String> set = this.hDouble.keySet();
		Iterator<String> iterator = set.iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			Double val = this.hDouble.get(key);
			if (val.equals(dbl)) {
				return false;
			}
		}

		return true;
	}

	private boolean isUniqueOpt(String opt) {

		Set<String> set = this.hOpt.keySet();
		Iterator<String> iterator = set.iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			String val = this.hOpt.get(key);
			if (val.equals(opt)) {
				return false;
			}
		}

		return true;
	}



}
