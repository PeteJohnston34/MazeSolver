package edu.dpr212.MazeProject.MainWindow;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

import edu.dpr212.MazeProject.MazeSolver.Cell;
import edu.dpr212.MazeProject.MazeSolver.ElementType;
import edu.dpr212.MazeProject.MazeSolver.MazeElement;

/**
 * A helper class used for drawing the maze onto the MazeCanvas.
 * 
 * @author Pete Johnston
 *
 */
public class Artist {
	
	/**
	 * Default constructor.
	 */
	public Artist(){
	}
	
	/**
	 * Takes in the mazeData array of MazeElements,the Graphics context of the MazeCanvas, and the width of the cells.
	 * Runs through the array and draws each element onto the canvas using the element's Coordinate.
	 * Also draws numbers for identifying each row and column.
	 * @param mazeData
	 * @param g
	 */
	public void drawMaze(MazeElement[][] mazeData, Graphics g, int cellWidth){
		g.setFont(new Font(Font.SERIF, Font.PLAIN, cellWidth / 2));
		
		for (int y = 0; y < mazeData.length; y++){
			Rectangle2D yStringBounds = g.getFontMetrics().getStringBounds(Integer.toString(y), g);
			g.drawString(Integer.toString(y), cellWidth / 2 - (int)yStringBounds.getWidth() / 2, y * cellWidth + cellWidth * 3 / 2 + (int)yStringBounds.getHeight() / 3);
			
			for (int x = 0; x < mazeData[0].length; x++){
				Rectangle2D xStringBounds = g.getFontMetrics().getStringBounds(Integer.toString(x),  g);
				g.drawString(Integer.toString(x), x * cellWidth + cellWidth * 3 / 2 - (int)xStringBounds.getWidth() / 2, cellWidth / 2 + (int)xStringBounds.getHeight() / 3);
				drawElement(mazeData[y][x], g, cellWidth, x, y);
			}
		}	
	}

	/**
	 * Takes in a MazeElement, the canvas Graphics context, and the width of the cells. 
	 * Draws the element on the canvas depending on its ElementType.
	 * @param element
	 * @param g
	 */
	public void drawElement(MazeElement element, Graphics g, int cellWidth, int x, int y){		
			g.setColor(element.getColor());
			g.fillRect((x + 1) * cellWidth, (y + 1) * cellWidth, cellWidth, cellWidth);
			g.setColor(Color.BLACK);
			g.drawRect((x + 1) * cellWidth, (y + 1) * cellWidth, cellWidth, cellWidth);
	}	
}
