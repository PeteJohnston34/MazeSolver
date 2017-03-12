package edu.dpr212.MazeProject.MainWindow;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JComponent;

import edu.dpr212.MazeProject.MazeSolver.MazeElement;

/**
 * A custom JComponent class used as a canvas for drawing Graphics.
 * @author Pete Johnston
 *
 */
public class MazeCanvas extends JComponent{

	private static final long serialVersionUID = 2308678484652856971L;
	private int cellWidth;

	private Artist artist;
	private MazeElement[][] mazeData;
	private Dimension canvasSize;
	
	/**
	 * This constructor initializes the Artist object and sets the members and the size to default values;
	 */
	public MazeCanvas(){
		this.cellWidth = Window.INITIAL_CELL_WIDTH;
		this.mazeData = null;
		this.artist = new Artist();
		this.canvasSize = new Dimension(400, 400);
		setPreferredSize(canvasSize);
	}
	
	/**
	 * This constructor passes in a reference to the mazeData array and initializes the Artist object.
	 * The size is calculated dynamically using the size of the maze.
	 * @param mazeData
	 */
	public MazeCanvas(MazeElement[][] mazeData){
		this.cellWidth = Window.INITIAL_CELL_WIDTH;
		this.mazeData = mazeData;
		this.artist = new Artist();
		this.canvasSize = new Dimension((mazeData[0].length + 2) * cellWidth, (mazeData.length + 2) * cellWidth);
		setPreferredSize(canvasSize);
		setMaximumSize(canvasSize);
		setMinimumSize(canvasSize);
	}
	
	/**
	 * Paints the canvas using the Artist.
	 */
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		if (mazeData != null){
			artist.drawMaze(mazeData, g, cellWidth);
		}
		g.dispose();
	}

	/**
	 * Resets the size of the canvas relative to cellWidth.
	 */
	public void resize(){
		if(mazeData !=null){
			canvasSize.setSize((mazeData[0].length + 2) * cellWidth, (mazeData.length + 2) * cellWidth);
			setPreferredSize(canvasSize);
			setMaximumSize(canvasSize);
			setMinimumSize(canvasSize);
		}
	}
	
	/**
	 * Increases the size of the maze drawing on the canvas.
	 */
	public void increaseCellWidth(){
		cellWidth += 5;
		resize();
		repaint();
	}
	
	/**
	 * Decreases the size of the maze drawing on the canvas.
	 */
	public void decreaseCellWidth(){
		cellWidth -= 5;
		if (cellWidth < 10){
			cellWidth = 10;
		}
		resize();
		repaint();
	}

	public MazeElement[][] getMazeData() {
		return mazeData;
	}

	public void setMazeData(MazeElement[][] mazeData) {
		this.mazeData = mazeData;
	}

	public int getCellWidth() {
		return cellWidth;
	}

	public void setCellWidth(int cellWidth) {
		this.cellWidth = cellWidth;
	}

}
