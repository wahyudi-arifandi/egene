package edu.ntu.eee.csn.ism.egene.exception;

import javax.servlet.ServletException;

public class EgeneWebException extends ServletException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4681238751899658623L;

	/**
	 * Constructor
	 */
	public EgeneWebException() {
		super();
	}

	/**
	 * Constructor
	 * 
	 * @param msg
	 *            Error message
	 */
	public EgeneWebException(String msg) {
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
	public EgeneWebException(String msg, Throwable t) {
		super(msg, t);
	}

}
