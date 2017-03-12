package edu.dpr212.MazeProject.MazeSolver;

/**
 * An Exception which is thrown when the user inputs starting coordinates which either don't
 * exist within the maze, or point to a Boundary or an Exit ElementType.
 * @author Pete Johnston
 *
 */
public class InvalidStartingPointException extends Exception {

	private static final long serialVersionUID = 4234364815180110153L;

	public InvalidStartingPointException(){
		super();	
	}
	
	public InvalidStartingPointException(String message){
		super(message);
	}
	
}
