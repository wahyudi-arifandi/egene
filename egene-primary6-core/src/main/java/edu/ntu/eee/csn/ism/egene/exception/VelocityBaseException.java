package edu.ntu.eee.csn.ism.egene.exception;

public class VelocityBaseException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8454333289901838094L;

	/**
	 * Constructor
	 */
	public VelocityBaseException() {
		super();
	}

	/**
	 * Constructor
	 * 
	 * @param msg
	 *            Error message
	 */
	public VelocityBaseException(String msg) {
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
	public VelocityBaseException(String msg, Throwable t) {
		super(msg, t);
	}
}
