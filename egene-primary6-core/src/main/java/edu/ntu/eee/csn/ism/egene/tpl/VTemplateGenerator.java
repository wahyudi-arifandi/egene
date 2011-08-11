package edu.ntu.eee.csn.ism.egene.tpl;

import java.util.List;

import edu.ntu.eee.csn.ism.egene.exception.VelocityBaseException;
import edu.ntu.eee.csn.ism.egene.util.TemplateUtil;

public class VTemplateGenerator {

	//private static Logger LOGGER = Logger.getLogger(VTemplateGenerator.class);


	public String retrieveTpl(String tableName) throws VelocityBaseException {
		TemplateUtil tplUtil = new TemplateUtil(tableName);
		String res = tplUtil.retrieveTemplate();
		tplUtil.close();
		
		return res;


	}

	public String retrieveTplExcl(String tableName, List<String> excl) {
		TemplateUtil tplUtil = new TemplateUtil(tableName);
		String str = tplUtil.retrieveTemplateExcl(excl);
		tplUtil.close();
		return str;
	}

}
