package edu.ntu.eee.csn.ism.egene.config;

/**
 * Enumaration of Config.java
 * @author arif
 *
 */
public enum ConfigParam {

	EGENE_DEFAULT_GENERATED_QUESTION_COUNT("egene-default-generated-question-count", "10"),
	
	JDBC_CONN_USE_JNDI_LOOKUP("jdbc-conn-use-jndi-lookup", "false"),
	JDBC_CONN_URL("jdbc-conn-url", "jdbc:mysql://localhost:3306/"),
	JDBC_CONN_DB("jdbc-conn-db", "epgencore"),
	JDBC_CONN_DRIVER("jdbc-conn-driver", "com.mysql.jdbc.Driver"),
	JDBC_CONN_USER("jdbc-conn-user", "epgencore"),
	JDBC_CONN_PASSWORD("jdbc-conn-password", "epgencore"),
	
	NOUNS_IRREGULAR_DICT_FILE("nouns-irregular-dict-file", "nouns-irregular.dict"),
	NOUNS_UNCOUNTABLE_DICT_FILE("nouns-uncountable-dict-file", "nouns-uncountable.dict"),
	VELOCITY_EXAMPAPER_TEMPLATE_DIR("velocity-exampaper-template-dir", "tpl"),
	VELOCITY_PROPERTIES_FILE("velocity-properties-file", "velocity.properties"),
	VERBS_IRREGULAR_DICT_FILE("verbs-irregular-dict-file", "verbs-irregular.dict"),
	WORDNET_PROPERTIES_FILE("wordnet-properties-file", "wordnet-properties.xml"),
	;
	
	/**
	 * Name of parameter
	 */
	private String name;
	
	/**
	 * Default value of parameter
	 */
	private String defVal;
	
	/**
	 * Constructor
	 * @param name Name of parameter
	 * @param defVal Default value of parameter 
	 */
	private ConfigParam(String name, String defVal) {
		this.name = name;
		this.defVal = defVal;
	}
	
	/**
	 * Get name of parameter
	 * @return name of parameter
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Get default value of parameter
	 * @return default value of parameter
	 */
	public String getDefVal() {
		return this.defVal;
	}
	
}
