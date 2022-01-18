package com.zensar.exceptions;

import java.io.PrintWriter;
import java.io.StringWriter;


public class MacysRunTimeException extends RuntimeException {

	static final long serialVersionUID = 5850243503337783048L;

	/**
	 * Creates the <code>JCDeltaRunTimeException</code> instance using exception
	 * message and root cause of RunTime exception.
	 * 
	 * @param msg
	 *            message as String object.
	 * @param t
	 *            Throwable object.
	 * 
	 *  
	 */
	public MacysRunTimeException(){
		super();
	}
	public MacysRunTimeException(String message){
		super(message);
	}
	public MacysRunTimeException(String msg, Throwable t) {
		super(msg, t);
	}
	public String getExceptionStackTrace(){
		   StringWriter writer=new StringWriter();
		  PrintWriter printWriter=new PrintWriter(writer); 
		  printStackTrace(printWriter);
		  return writer.toString();
	      }

}