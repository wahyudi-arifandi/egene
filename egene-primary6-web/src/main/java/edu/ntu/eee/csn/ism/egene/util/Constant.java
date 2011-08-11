package edu.ntu.eee.csn.ism.egene.util;

public enum Constant {
	
	HTTP_REQUEST_PARAM_TABLE_NAME_KEY("table_name"),
	
	TABLE_MASTER_NAME("master_topics"),
	TABLE_MASTER_C_ID("id"),
	TABLE_MASTER_C_TOPIC_NAME("topic_name"),
	TABLE_MASTER_C_TEMPLATE_NAME("template_name"),
	TABLE_MASTER_C_DESCRIPTION("description"),
	
	TABLE_REF_MASTER_NAME_PREFIX("tplm_"),
	TABLE_REF_NOUN_NAME_PREFIX("tpln_"),
	TABLE_REF_VERB_NAME_PREFIX("tplv_"),
	TABLE_REF_OTHER_NAME_PREFIX("tplo_"),
	TABLE_REF_C_ID("id"),
	TABLE_REF_C_VALUE("value"),
	
	TABLE_SELECT_START_ROW("start_row"),
	TABLE_SELECT_COUNT("count"),
	
	TABLES_REF_MASTER("tables_ref_master"),
	TABLES_REF("tables_ref"),
	TABLE("table"),
	
	TOPICS("topics"),
	TOPIC("topic"),
	
	ROW_COUNT("row_count"),
	;
	
	private String constant;
	private Constant(String constant) {
		this.constant = constant;
	}

	@Override
	public String toString() {
		return this.constant;
	}
}
