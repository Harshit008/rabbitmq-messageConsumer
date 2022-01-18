package com.zensar.exceptions;

public class MacysException extends BaseException {

	static final long serialVersionUID = 4157262600607325995L;

	/**
	 * a public constructor for <code>JCDeltaException</code>.
	 * 
	 * @param msg
	 *            exception message.
	 *  
	 */
	public MacysException(){
		super();
	}
	public MacysException(String msg) {
		super(msg);
	}
}