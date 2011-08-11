package edu.ntu.eee.csn.ism.egene.exception;

public class EgeneCoreException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2463651449569886560L;

	/**
	 * Constructor
	 */
	public EgeneCoreException() {
		super();
	}

	/**
	 * Constructor
	 * 
	 * @param msg
	 *            Error message
	 */
	public EgeneCoreException(String msg) {
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
	public EgeneCoreException(String msg, Throwable t) {
		super(msg, t);
	}

}
