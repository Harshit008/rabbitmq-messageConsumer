package com.zensar.exceptions;



import java.io.PrintWriter;
import java.io.StringWriter;


public class BaseException extends Exception {

	static final long serialVersionUID = -5829545098534135052L;

	/**
	 * the message of the BaseException.
	 */
	private String exceptionMessage="";

	/**
	 * A public constructor for BaseException containing no arguments.
	 *  
	 */
	public BaseException() {
		super();
	}

	/**
	 * A public constructor for BaseException specifying exception message.
	 * <p>
	 *  @param msg
	 *            exception message.  
	 */
	public BaseException(String msg) {
		super(msg);
		this.exceptionMessage = msg;
	}

	/**
	 * A public constructor of <code>BaseException</code> containing
	 * message and root cause (as <code>Throwable</code>) of the exception.
	 * 
	 * @param msg
	 *            exception message.
	 * @param e
	 *            Throwable object.
	 *  
	 */
	public BaseException(String msg, Throwable e) {
		super(msg,e);
		this.exceptionMessage = msg;
		this.initCause(e);
	}

	/**
	 * sets the root cause of the exception. Used for setting Java built in
	 * exception in <code>BaseException</code>.
	 * 
	 * @param e
	 *            Throwable object.
	 *  
	 */
	public void setCause(Throwable e) {
		this.initCause(e);
	}

	/*
	 * Gets the class name and exception message.
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String s = getClass().getName();
		return s + " : " + exceptionMessage;
	}

	/*
	 * Gets the message of the exception. 
	 * @see java.lang.Throwable#getMessage()
	 */
	public String getMessage() {
		return exceptionMessage;
	}

	
	 public String getExceptionStackTrace(){
		   StringWriter writer=new StringWriter();
		  PrintWriter printWriter=new PrintWriter(writer); 
		  printStackTrace(printWriter);
		  return writer.toString();
	      }
	
	
}