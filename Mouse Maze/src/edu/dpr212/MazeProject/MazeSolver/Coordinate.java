package edu.dpr212.MazeProject.MazeSolver;

/**
 * A class which represents a location on the maze.
 * @author Pete Johnston
 *
 */
public class Coordinate {
	private int x;
	private int y;
	
	public Coordinate(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Takes in another Coordinate and return a char representation of the direction said Coordinate
	 * is located in relation to this Coordinate object. 
	 * Only used for Coordinates which are next to one another.
	 * @param lastCoord
	 * @return
	 */
	public char getLast(Coordinate lastCoord){
		char last = 'X';

		if(x - lastCoord.getX() == -1 && y == lastCoord.getY()){
			last = 'E';
		}
		else if(x - lastCoord.getX() == 1 && y == lastCoord.getY()){
			last = 'W';
		}
		else if(y - lastCoord.getY() == -1 && x == lastCoord.getX()){
			last = 'S';
		}
		else if(y - lastCoord.getY() == 1 && x == lastCoord.getX()){
			last = 'N';
		}
		return last;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Overrides toString() from the Object class. Displays x and y as a coordinate.
	 */
	public String toString(){
		return ("(" + x + ", " + y + ")");
	}
}
