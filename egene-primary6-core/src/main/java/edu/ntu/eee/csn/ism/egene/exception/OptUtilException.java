package edu.ntu.eee.csn.ism.egene.exception;

public class OptUtilException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2893503831680677740L;

	/**
	 * Constructor
	 */
	public OptUtilException() {
		super();
	}

	/**
	 * Constructor
	 * 
	 * @param msg
	 *            Error message
	 */
	public OptUtilException(String msg) {
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
	public OptUtilException(String msg, Throwable t) {
		super(msg, t);
	}
}
