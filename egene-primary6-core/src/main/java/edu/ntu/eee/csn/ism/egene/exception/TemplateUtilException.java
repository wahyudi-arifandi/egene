package edu.ntu.eee.csn.ism.egene.exception;

public class TemplateUtilException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4121882267303199685L;

	/**
	 * Constructor
	 */
	public TemplateUtilException() {
		super();
	}

	/**
	 * Constructor
	 * 
	 * @param msg
	 *            Error message
	 */
	public TemplateUtilException(String msg) {
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
	public TemplateUtilException(String msg, Throwable t) {
		super(msg, t);
	}
}
