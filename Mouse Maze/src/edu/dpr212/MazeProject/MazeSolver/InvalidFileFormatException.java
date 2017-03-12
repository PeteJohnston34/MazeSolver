package edu.dpr212.MazeProject.MazeSolver;

/**
 * An Exception which is thrown when the input file is of the wrong format.
 * @author Pete Johnston
 *
 */
public class InvalidFileFormatException extends Exception{

	private static final long serialVersionUID = 4210647418600708806L;

	public InvalidFileFormatException(){
		super();
	}
	
	public InvalidFileFormatException(String message){
		super(message);
	}
}
