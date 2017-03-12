package edu.dpr212.Stack;

/**
 * A custom Exception class for the UnboundedStackImplementation.
 * It is thrown if the top() method is called and the stack is empty.
 * @author Pete Johnston
 *
 */
public class StackUnderflowException extends RuntimeException{

	private static final long serialVersionUID = 8813173126215175051L;
	
	public StackUnderflowException(){
		super();	
	}
	
	public StackUnderflowException(String message){
		super(message);
	}
	
}
