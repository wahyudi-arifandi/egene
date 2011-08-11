package edu.ntu.eee.csn.ism.egene.exception;

public class CodecException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1248049236698333580L;

	/**
	 * Constructor
	 */
	public CodecException() {
		super();
	}

	/**
	 * Constructor
	 * 
	 * @param msg
	 *            Error message
	 */
	public CodecException(String msg) {
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
	public CodecException(String msg, Throwable t) {
		super(msg, t);
	}
}
