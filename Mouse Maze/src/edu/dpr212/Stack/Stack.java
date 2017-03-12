package edu.dpr212.Stack;

/**
 * A generic Stack interface
 * @author Pete Johnston
 *
 * @param <T>
 */
public interface Stack<T> {
	public void push(T element);
	public T pop();
	public T top() throws StackUnderflowException;
	public boolean isEmpty();
	public int getSize();
	public Object[]toArrayBottomUp();
	public Object[]toArrayTopDown();
}
