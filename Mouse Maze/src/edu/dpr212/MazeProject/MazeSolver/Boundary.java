package edu.dpr212.MazeProject.MazeSolver;

import java.awt.Color;

/**
 * A MazeElement subclass which represents a boundary in the maze
 * @author Pete Johnston
 *
 */
public class Boundary extends MazeElement{

	/**
	 * Takes in coordinate information and an ElementType and passes it to the MazeElement constructor.
	 * @param x
	 * @param y
	 * @param TYPE
	 */
	public Boundary(int x, int y, ElementType TYPE) {
		super(x, y, TYPE, Color.DARK_GRAY);
	}
	
	/**
	 * Overrides toString() from the Object class. Displays coordinate information.
	 */
	public String toString(){
		return ("Boundary Coordinates: " + location.toString());	}
}
