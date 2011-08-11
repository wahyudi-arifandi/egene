package edu.ntu.eee.csn.ism.egene.exception;

public class NumberUtilException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2645130269047388278L;

	/**
	 * Constructor
	 */
	public NumberUtilException() {
		super();
	}

	/**
	 * Constructor
	 * 
	 * @param msg
	 *            Error message
	 */
	public NumberUtilException(String msg) {
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
	public NumberUtilException(String msg, Throwable t) {
		super(msg, t);
	}
}
