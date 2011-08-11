package edu.ntu.eee.csn.ism.egene.exception;

public class NounUtilException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7713950891413906477L;

	/**
	 * Constructor
	 */
	public NounUtilException() {
		super();
	}

	/**
	 * Constructor
	 * 
	 * @param msg
	 *            Error message
	 */
	public NounUtilException(String msg) {
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
	public NounUtilException(String msg, Throwable t) {
		super(msg, t);
	}
}
