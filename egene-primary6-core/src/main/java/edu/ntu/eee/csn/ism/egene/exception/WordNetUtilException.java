package edu.ntu.eee.csn.ism.egene.exception;

public class WordNetUtilException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2602431053017715174L;

	/**
	 * Constructor
	 */
	public WordNetUtilException() {
		super();
	}

	/**
	 * Constructor
	 * 
	 * @param msg
	 *            Error message
	 */
	public WordNetUtilException(String msg) {
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
	public WordNetUtilException(String msg, Throwable t) {
		super(msg, t);
	}
}
