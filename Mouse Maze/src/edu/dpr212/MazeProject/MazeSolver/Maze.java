package edu.dpr212.MazeProject.MazeSolver;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * A class that initializes and holds all of the data for the maze.
 * @author Pete Johnston
 *
 */
public class Maze {
	private int width;
	private int height;

	private boolean[][] mazeIn;
	private MazeElement[][] mazeData;
	private String[] input;
	
	/**
	 * Takes in a file path and uses a Scanner to retrieve the maze data from the file.
	 * A boolean[][] array is used first to retrieve the data, then a MazeElement[][] array 
	 * is created using the boolean data.
	 * @param file
	 * @throws FileNotFoundException
	 * @throws InvalidFileFormatException
	 */
	public Maze(File file) throws FileNotFoundException, InvalidFileFormatException, NumberFormatException
	{			
		this.mazeIn = readFile(file);
		this.mazeData = createMaze(mazeIn);
	}
	
	/**
	 * Takes in a File path and uses a Scanner to read the file data and populate a boolean[][] array.
	 * The data in the file is validated according to the format provided in the assignment and
	 * an InvalidFileFormatException is thrown is the data is of the wrong format.
	 * @param in
	 * @return
	 * @throws InvalidFileFormatException
	 * @throws FileNotFoundException 
	 */
	private boolean[][] readFile(File file) throws InvalidFileFormatException, FileNotFoundException, NumberFormatException{
		Scanner in = new Scanner(file);		
		
		//Validate the first line containing the size of the maze.
		if (!in.hasNextLine() || !validateInputString(input = in.nextLine().split(" "), 2)){
			in.close();
			throw new InvalidFileFormatException("Invalid Input File");
		}
		else{
			width = Integer.parseInt(input[0]);
			height = Integer.parseInt(input[1]);
		}
		
		boolean[][] mazeIn = new boolean[height][width];
		
		//Validate the number of lines and number of tokens on each line in the rest of the file.
		for (int y = 0; y < height; y++){
			if (!in.hasNextLine() || !validateInputString(input = in.nextLine().split(" "), width)){
				in.close();
				throw new InvalidFileFormatException("Invalid Input File");
			}
			else{
				for (int x = 0; x < width; x++){
					//populate the boolean[][] array
					if (Integer.parseInt(input[x]) == 0){
						mazeIn[y][x] = false;
					}
					else if (Integer.parseInt(input[x]) == 1){
						mazeIn[y][x] = true;
					}
					else{
						in.close();
						throw new InvalidFileFormatException("Invalid Input File");
					}
				}
			}

		}		
		in.close();
		return mazeIn;		
	}
	
	/**
	 * Takes in a boolean[][] array and uses it to create a MazeElement[][] array,
	 * where false represents an open cell and true represents a boundary.
	 * It checks each element of the boolean[][] array for false "neighbors":
	 * A new MazeElement sub-object is created for each entry in the boolean[][]array, and is 
	 * assigned the proper ElementType.
	 * @param mazeIn
	 * @return
	 */
	private MazeElement[][] createMaze(boolean[][] mazeIn){
		MazeElement[][] data = new MazeElement[height][width];
		
		for (int y = 0; y < height; y++){
			for (int x = 0; x < width; x++){
				
				if (mazeIn[y][x] == true){
					data[y][x] = new Boundary(x, y, ElementType.Boundary);
				}
				
				else if (y == 0 || y == height - 1 || x == 0 || x == width - 1){
					data[y][x] = new Cell(x, y, ElementType.Exit);
				}
				
				else{
					boolean canMoveNorth = false;
					boolean canMoveSouth = false;
					boolean canMoveEast = false;
					boolean canMoveWest = false;
					int exitCount = 0;

					if (mazeIn[y - 1][x] == false) {
						canMoveNorth = true;
						exitCount++;
					}
					if (mazeIn[y + 1][x] == false) {
						canMoveSouth = true;
						exitCount++;
					}
					if (mazeIn[y][x + 1] == false) {
						canMoveEast = true;
						exitCount++;
					}
					if (mazeIn[y][x - 1] == false) {
						canMoveWest = true;
						exitCount++;
					}
					
					switch(exitCount){
					case 1:
						data[y][x] = new Cell(x, y, canMoveNorth, canMoveSouth, canMoveEast, canMoveWest, ElementType.DeadEnd);
						break;
					case 2:
						data[y][x] = new Cell(x, y, canMoveNorth, canMoveSouth, canMoveEast, canMoveWest, ElementType.Continuing);
						break;
					case 3:
					case 4:
						data[y][x] = new Cell(x, y, canMoveNorth, canMoveSouth, canMoveEast, canMoveWest, ElementType.Intersection);
					}
										
				}
				
			}
		}
		
		return data;
	}
	
	/**
	 * Takes in a String[] array and an integer. Returns true if the integer = the size of the array,
	 * otherwise it returns false.
	 * @param input
	 * @param expectedSize
	 * @return
	 */
	private boolean validateInputString(String[] input, int expectedSize){
		boolean valid = false;
		if (input.length == expectedSize){
			valid = true;
		}
		return valid;
	}

	public MazeElement[][] getMazeData() {
		return mazeData;
	}
	
}
