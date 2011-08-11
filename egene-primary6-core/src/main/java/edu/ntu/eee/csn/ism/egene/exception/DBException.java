package edu.ntu.eee.csn.ism.egene.exception;

/**
 * Exception for accessing database
 * 
 * @author arif
 * 
 */
public class DBException extends Exception {


	/**
	 * 
	 */
	private static final long serialVersionUID = 5454038596581048329L;

	/**
	 * Constructor
	 */
	public DBException() {
		super();
	}

	/**
	 * Constructor
	 * 
	 * @param msg
	 *            Error message
	 */
	public DBException(String msg) {
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
	public DBException(String msg, Throwable t) {
		super(msg, t);
	}

}
