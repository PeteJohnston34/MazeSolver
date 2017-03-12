package edu.dpr212.MazeProject.MazeSolver;

import java.awt.Color;

/**
 * An abstract class which represents each element (square) of the maze.
 * @author Pete Johnston
 *
 */
public abstract class MazeElement{
	protected Coordinate location;
	private ElementType TYPE;
	private Color color;
	
	/**
	 * Takes in coordinate information and an ElementType.
	 * Initializes the Coordinate using the x and y integers.
	 * @param x
	 * @param y
	 * @param TYPE
	 */
	public MazeElement(int x, int y, ElementType TYPE, Color color)
	{
		this.location = new Coordinate(x, y);
		this.TYPE = TYPE;
		this.color = color;
	}

	public ElementType getTYPE() {
		return TYPE;
	}

	public int getX() {
		return location.getX();
	}

	public int getY() {
		return location.getY();
	}
	
	public Coordinate getLocation() {
		return location;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}	
}
