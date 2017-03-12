package edu.dpr212.Stack;

/**
 * A custom Exception class for the UnboundedStackImplementation.
 * It is thrown if a copy constructor is passed a null value
 * @author Pete Johnston
 *
 */
public class InvalidCopyConstructorException extends RuntimeException{

	private static final long serialVersionUID = -7935705063493488272L;

	public InvalidCopyConstructorException(){
		super();
	}
	
	public InvalidCopyConstructorException(String message){
		super(message);
	}
	
}
