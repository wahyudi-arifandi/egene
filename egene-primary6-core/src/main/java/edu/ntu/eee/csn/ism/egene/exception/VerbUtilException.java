package edu.ntu.eee.csn.ism.egene.exception;

public class VerbUtilException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8537373425622437357L;

	/**
	 * Constructor
	 */
	public VerbUtilException() {
		super();
	}

	/**
	 * Constructor
	 * 
	 * @param msg
	 *            Error message
	 */
	public VerbUtilException(String msg) {
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
	public VerbUtilException(String msg, Throwable t) {
		super(msg, t);
	}
}
