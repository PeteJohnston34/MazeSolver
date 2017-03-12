package edu.dpr212.MazeProject.MazeSolver;

import edu.dpr212.Stack.*;

/**
 * A class that is responsible for solving the maze.
 * A Stack is used for the solving algorithm.
 * @author Pete Johnston
 *
 */
public class Mouse {

	private boolean solvable;
	private boolean solved;

	private Coordinate location;
	private Stack<Cell> pathFinder;
	private MazeElement[][] mazeData;

	/**
	 * Takes in x and y coordinates for the starting location as well as a reference to the mazeData array.
	 * Validates the starting location and throws an InvalidStartingPointException if invalid.
	 * @param startX
	 * @param startY
	 * @param mazeData
	 * @throws InvalidStartingPointException
	 */
	public Mouse(int startX, int startY, MazeElement[][] mazeData) throws InvalidStartingPointException {

		if(startX < 0 || startX > mazeData[0].length - 1 || startY < 0 || startY > mazeData.length - 1){
			throw new InvalidStartingPointException("Must start within the maze");
		}
		
		if (mazeData[startY][startX].getTYPE() == ElementType.Boundary) {
			throw new InvalidStartingPointException("Cannot start on a boundary");
		}

		if (mazeData[startY][startX].getTYPE() == ElementType.Exit) {
			throw new InvalidStartingPointException("Cannot start on an exit");
		}

		this.solvable = true;
		this.solved = false;
		this.location = new Coordinate(startX, startY);
		this.mazeData = mazeData;
		this.pathFinder = new UnboundedStackImplementation<Cell>();
	}

	/**
	 * This is the main solving algorithm for the program. 
	 * The Mouse first checks for an adjacent exit, then enters a loop: 
	 * He checks for a valid path to an adjacent Cell, using the Cell's traverse() method,
	 * and advances through the maze until he reaches either an adjacent exit or a dead end,
	 * keeping track of his progress by placing each Cell in the Stack. 
	 * At each dead end, he backtracks in the Stack to the last intersection.
	 * If the Stack empties, he admits defeat.
	 * If he reaches the exit, the path is returned as Cell[] array. 
	 * 		
	 * @return
	 */
	public Cell[] solveMaze() {
		char lastCell = 'X';
		Cell thisCell = null;
		Coordinate nextCell = null;
		Cell[] solution = null;
		
		pathFinder.push((Cell) mazeData[getY()][getX()]);
		solved = checkForExit();

		while (solvable && !solved) {
			thisCell = (Cell) mazeData[getY()][getX()];
			nextCell = thisCell.findNextCell(lastCell);

			if (nextCell == null) {
				lastCell = 'X';
				backtrack();
			} else {
				location = nextCell;
				pathFinder.push((Cell) mazeData[getY()][getX()]);
				lastCell = location.getLast(thisCell.getLocation());
				solved = checkForExit();
			}

		}

		if (solvable) {
			solution = generateSolution(pathFinder);
			proclaimVictory();
		} else {
			admitDefeat();
		}
		return solution;
	}

	/**
	 * Used in the solving algorithm to backtrack through the Stack until an Intersection is reached.
	 * If the stack empties, the maze is considered unsolvable.
	 */
	private void backtrack() {
		if (!pathFinder.isEmpty()) {

			do {
				pathFinder.pop().setPartOfPath(false);

			} while (!pathFinder.isEmpty() && (pathFinder.top().getTYPE() != ElementType.Intersection));

			if (!pathFinder.isEmpty()) {
				location = pathFinder.top().getLocation();
				pathFinder.top().setPartOfPath(false);
			} else {
				solvable = false;
			}

		}
		else{
			solvable = false;
		}
	}

	/**
	 * Used in the solving algorithm to check the Mouse's current location for an adjacent Exit Cell.
	 * @return
	 */
	private boolean checkForExit() {
		boolean canExit = false;

		if (mazeData[getY() + 1][getX()].getTYPE() == ElementType.Exit) {
			pathFinder.push((Cell) mazeData[getY() + 1][getX()]);
			canExit = true;
		} else if (mazeData[getY() - 1][getX()].getTYPE() == ElementType.Exit) {
			pathFinder.push((Cell) mazeData[getY() - 1][getX()]);
			canExit = true;
		} else if (mazeData[getY()][getX() + 1].getTYPE() == ElementType.Exit) {
			pathFinder.push((Cell) mazeData[getY()][getX() + 1]);
			canExit = true;
		} else if (mazeData[getY()][getX() - 1].getTYPE() == ElementType.Exit) {
			pathFinder.push((Cell) mazeData[getY()][getX() - 1]);
			canExit = true;
		}

		return canExit;
	}

	/**
	 * Takes in the stack of Cells a Cell[] array in reverse order.
	 * @param pathFinderResult
	 * @return
	 */
	private Cell[] generateSolution(Stack<Cell> pathFinderResult) {
		Object[] tempArray = pathFinderResult.toArrayBottomUp();
		Cell[] solution = new Cell[pathFinderResult.getSize()];

		for (int i = 0; i < pathFinderResult.getSize(); i++) {
			solution[i] = (Cell) tempArray[i];
		}

		tempArray = null;
		return solution;
	}

	private void proclaimVictory() {
		System.out.println("\"I have escaped!\"");
	}

	private void admitDefeat() {
		System.out.println("\"I am never getting out of here.\"");

	}

	public boolean isSolved() {
		return solved;
	}

	public int getX() {
		return location.getX();
	}

	public int getY() {
		return location.getY();
	}

	/**
	 * Overrides toString() from the Object class. Displays coordinate information.
	 */
	public String toString() {
		return ("Mouse Coordinates: " + location.toString());
	}
}
