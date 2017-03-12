package edu.dpr212.Stack;

/**
 * A generic container class for each element in the Stack. 
 * It holds the element plus a link to the next Node in the Stack.
 * @author Pete Johnston
 *
 * @param <T>
 */
class Node<T> {

	private T element;
	private Node<T> next;
	
	/**
	 * This constructor passes in an element and sets next to null.
	 * @param element
	 */
	Node(T element)
	{
		this.element = element;
		this.next = null;
	}
	
	/**
	 * This constructor passes in an element and Node
	 * @param element
	 * @param next
	 */
	Node(T element, Node<T> next)
	{
		this.element = element;
		this.next = next;
	}
	
	/**
	 * This is a copy constructor that takes in a Node to be copied.
	 * It throws an InvalidCopyConstructorException if a null value is passed in.
	 * @param copy
	 */
	Node(Node<T> copy)
	{
		if (copy != null && copy.getNext() != null){
			this.element = copy.getElement();
			this.next = new Node<T>(copy.getNext());
			}
		else if (copy != null){
			this.element = copy.element;
			this.next = null;
		}
		else
		{
			throw new InvalidCopyConstructorException("Copy cannot be null");
		}
	}
	
	public T getElement() {
		return element;
	}

	public void setElement(T element) {
		this.element = element;
	}

	public Node<T> getNext() {
		return next;
	}

	public void setNext(Node<T> next) {
		this.next = next;
	}

	
}
