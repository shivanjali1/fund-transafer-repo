package com.hcl.springbootbankapp.exception;

public class ApplicationException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public ApplicationException(Throwable t, String msg) {
		super(msg, t);
	}

	public ApplicationException(Throwable t) {
		super(t);
	}

	public ApplicationException(String msg) {
		super(msg);
	}

	

}
