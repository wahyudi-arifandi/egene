package edu.ntu.eee.csn.ism.egene.exception;

/**
 * Exception for accessing configuration file
 * 
 * @author arif
 * 
 */
public class ConfigException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1466361259007940981L;

	/**
	 * Constructor
	 */
	public ConfigException() {
		super();
	}

	/**
	 * Constructor
	 * 
	 * @param msg
	 *            Error message
	 */
	public ConfigException(String msg) {
		super(msg);
	}

	/**
	 * Constructor
	 * 
	 * @param msg
	 *            Error message
	 * @param t
	 *            Stack trace
	 */
	public ConfigException(String msg, Throwable t) {
		super(msg, t);
	}

}
