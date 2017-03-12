package edu.dpr212.MazeProject.MazeSolver;

import java.awt.Color;

/**
 * A MazeElement subclass which represents and open cell in the maze.
 * Contains logic for finding a valid path to neighboring Cells.
 * @author Pete Johnston
 *
 */
public class Cell extends MazeElement{

	private boolean canMoveNorth;
	private boolean canMoveSouth;
	private boolean canMoveEast;
	private boolean canMoveWest;
	private boolean checkedNorth;
	private boolean checkedSouth;
	private boolean checkedEast;
	private boolean checkedWest;
	private boolean partOfPath;
	
	/**
	 * Takes in coordinate information and an ElementType and passes it to the superclass constructor.
	 * Initializes the boolean variables to false.
	 * This constructor is used for Cells which represent an exit from the maze.
	 * @param x
	 * @param y
	 * @param TYPE
	 */
	public Cell(int x, int y, ElementType TYPE) {
		super(x, y, TYPE, Color.WHITE);
		this.canMoveNorth = false;
		this.canMoveSouth = false;
		this.canMoveEast = false;
		this.canMoveWest = false;
		this.checkedNorth = false;
		this.checkedSouth = false;
		this.checkedEast = false;
		this.checkedWest = false;
		this.partOfPath = false;
		}

	/**
	 * Takes in coordinate information and an ElementType and passes it to the superclass constructor.
	 * Also takes in booleans which represent any neighboring Cells and initalizes the member booleans accordingly.
	 * @param x
	 * @param y
	 * @param canMoveNorth
	 * @param canMoveSouth
	 * @param canMoveEast
	 * @param canMoveWest
	 * @param TYPE
	 */
	public Cell(int x, int y, boolean canMoveNorth, boolean canMoveSouth,
			boolean canMoveEast, boolean canMoveWest, ElementType TYPE) {
		super(x, y, TYPE, Color.WHITE);
		this.canMoveNorth = canMoveNorth;
		this.canMoveSouth = canMoveSouth;
		this.canMoveEast = canMoveEast;
		this.canMoveWest = canMoveWest;
		this.checkedNorth = false;
		this.checkedSouth = false;
		this.checkedEast = false;
		this.checkedWest = false;
		this.partOfPath = false;
	}

	/**
	 * Takes in a char representing the previous cell. Uses the member booleans to find a valid
	 * neighboring Cell and returns a Coordinate which represents that cell.
	 * @param lastCell
	 * @return
	 */
	public Coordinate findNextCell(char lastCell) {
		Coordinate nextCell = null;
		
		switch (lastCell) {
		case 'N':
			checkedNorth = true;
			break;
		case 'S':
			checkedSouth = true;
			break;
		case 'E':
			checkedEast = true;
			break;
		case 'W':
			checkedWest = true;
		}

		if (!partOfPath) {
			if (canMoveEast && !checkedEast) {
				nextCell = new Coordinate(getX() + 1, getY());
				checkedEast = true;
				partOfPath = true;
			} else if (canMoveNorth && !checkedNorth) {
				nextCell = new Coordinate(getX(), getY() - 1);
				checkedNorth = true;
				partOfPath = true;
			} else if (canMoveWest && !checkedWest) {
				nextCell = new Coordinate(getX() - 1, getY());
				checkedWest = true;
				partOfPath = true;
			} else if (canMoveSouth && !checkedSouth) {
				nextCell = new Coordinate(getX(), getY() + 1);
				checkedSouth = true;
				partOfPath = true;
			}
		}
		return nextCell;
	}

	public void setPartOfPath(boolean partOfPath) {
		this.partOfPath = partOfPath;
	}
	
	public boolean isPartOfPath() {
		return partOfPath;
	}
	
	public void setCheckedNorth(boolean checkedNorth) {
		this.checkedNorth = checkedNorth;
	}

	public void setCheckedSouth(boolean checkedSouth) {
		this.checkedSouth = checkedSouth;
	}

	public void setCheckedEast(boolean checkedEast) {
		this.checkedEast = checkedEast;
	}

	public void setCheckedWest(boolean checkedWest) {
		this.checkedWest = checkedWest;
	}

	/**
	 * Overrides toString() from the Object class. Displays coordinate information.
	 */
	public String toString() {
		return ("Cell Coordinates: " + location.toString());
	}

}
