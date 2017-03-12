package edu.dpr212.Stack;

/**
 * An unbounded implementation of the Stack interface using dynamic memory
 * allocation. The Node class is designed as a generic container class with a
 * link to the next Node in the Stack.
 * 
 * @author Pete Johnston
 *
 * @param <T>
 */
public class UnboundedStackImplementation<T> implements Stack<T> {

	private Node<T> topNode;
	private int count;

	/**
	 * This constructor initializes count to 0 and topNode to null.
	 */
	public UnboundedStackImplementation() {
		this.count = 0;
		this.topNode = null;
	}

	/**
	 * This is a copy constructor that takes in an UnboundedStackImplementation
	 * to be copied. It throws an InvalidCopyConstructorException if passed in a
	 * null value.
	 * 
	 * @param copy
	 */
	public UnboundedStackImplementation(UnboundedStackImplementation<T> copy) {
		if (copy != null) {
			this.count = copy.getSize();
			if (copy.getTopNode() != null) {
				this.topNode = new Node<T>(copy.getTopNode());
			} else {
				this.topNode = null;
			}
		} else {
			throw new InvalidCopyConstructorException("Copy cannot be null");
		}
	}

	/**
	 * Passes in an element to be added to the Stack, creates a new Node to hold
	 * that element, and sets topNode to the new Node. Increments count.
	 */
	public void push(T element) {
		Node<T> newNode = new Node<T>(element, this.topNode);
		this.topNode = newNode;
		count++;
	}

	/**
	 * Retrieves and removes the top element from the Stack.
	 * Updates the topNode reference to the next Node in the Stack or null if the Stack is emptied.
	 * Throws a StackUnderflowException is the Stack is empty when called.
	 */
	public T pop() throws StackUnderflowException{
		T popResult = top();

		if (topNode != null && topNode.getNext() != null) {
			topNode = topNode.getNext();
			count--;
		} else if (topNode != null) {
			topNode = null;
			count--;
		}

		return popResult;

	}

	/**
	 * Returns the top element in the Stack.
	 * Throws a StackUnderflowException is the Stack is empty.
	 */
	public T top() {
		T returnElement = null;

		if (!isEmpty())
			returnElement = topNode.getElement();
		else
			throw new StackUnderflowException("The stack is empty");

		return returnElement;
	}

	/**
	 * Returns true if the Stack is empty, otherwise returns false.
	 */
	public boolean isEmpty() {
		boolean empty;

		if (topNode == null)
			empty = true;
		else
			empty = false;

		return empty;
	}

	public int getSize() {
		return count;
	}

	public Node<T> getTopNode() {
		return topNode;
	}

	/**
	 * Returns an Object[] array that contains all of the elements in the Stack.
	 * This method creates the array in the same order as the Stack.
	 */
	public Object[] toArrayTopDown() {
		Object[] tempArray = new Object[count];
		Stack<T> tempStack = new UnboundedStackImplementation<T>(this);

		for (int i = 0; i < count; i++) {
			tempArray[i] = tempStack.pop();
		}

		tempStack = null;
		return tempArray;
	}
	/**
	 * Returns an Object[] array that contains all of the elements in the Stack.
	 * This method creates the array in reverse order as the Stack.
	 */
	public Object[] toArrayBottomUp() {

		Object[] tempArray = new Object[count];
		Stack<T> tempStack = new UnboundedStackImplementation<T>(this);

		for (int i = (count - 1); i >= 0; i--) {
			tempArray[i] = tempStack.pop();
		}

		tempStack = null;
		return tempArray;
	}

}
