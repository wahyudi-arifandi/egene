package edu.ntu.eee.csn.ism.egene.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.ntu.eee.csn.ism.egene.exception.EgeneCoreException;

public class StringVarUtil {

	private static final Pattern patternFindVar = Pattern
			.compile(".?\\$[a-zA-Z]+\\w*\\([^\\$\\(\\)]*?\\)");

	private static final Pattern patternVar = Pattern
			.compile("\\$[a-zA-Z]+\\w*\\([^\\$\\(\\)]*?\\)");

	private static final Pattern patternOBrace = Pattern.compile("\\(");

	public static List<String> getVarsFromTemplate(String str) {
		//Set<String> vars = new HashSet<String>();
		List<String> vars = new ArrayList<String>();
		
		Matcher matcher = StringVarUtil.patternFindVar.matcher(str);
		while (matcher.find()) {
			String found = matcher.group();
			if (!found.startsWith("\\")) {
				if (found.startsWith("$")) {
					vars.add(matcher.group());
				} else {
					vars.add(found.substring(1));
				}
			}
		}

		//return new ArrayList<String>(vars);
		return vars;

	}

	public static boolean isVar(String var) {
		Matcher matcher = StringVarUtil.patternVar.matcher(var);
		return matcher.find();
	}

	public static String getVarFunctionName(String var) throws EgeneCoreException {
		String varName = null;

		if (!StringVarUtil.isVar(var)) {
			throw new EgeneCoreException("INVALID var=" + var);
		}

		Matcher matcher = StringVarUtil.patternOBrace.matcher(var);
		if (matcher.find()) {
			varName = var.substring(1, matcher.start());
		} else {
			throw new EgeneCoreException(
					"CAN'T extract varName. INVALID var=" + var);
		}

		return varName;
	}

	public static List<String> getVarParams(String var)
			throws EgeneCoreException {
		List<String> params = new ArrayList<String>();

		if (!StringVarUtil.isVar(var)) {
			throw new EgeneCoreException("INVALID var=" + var);
		}

		Matcher matcher = StringVarUtil.patternOBrace.matcher(var);
		if (matcher.find()) {
			int iParamStart = matcher.start() + 1;
			String paramStr = var.substring(iParamStart, var.length() - 1);
			String[] arrParam = paramStr.split(",");

			for (int i = 0; i < arrParam.length; i++) {
				params.add(arrParam[i].trim());
			}

		} else {
			throw new EgeneCoreException(
					"CAN'T extract varName. INVALID var=" + var);
		}

		return params;
	}

}
