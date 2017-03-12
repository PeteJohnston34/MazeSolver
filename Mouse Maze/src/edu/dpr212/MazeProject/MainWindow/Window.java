package edu.dpr212.MazeProject.MainWindow;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;

import edu.dpr212.MazeProject.MazeSolver.Cell;
import edu.dpr212.MazeProject.MazeSolver.ElementType;
import edu.dpr212.MazeProject.MazeSolver.InvalidFileFormatException;
import edu.dpr212.MazeProject.MazeSolver.InvalidStartingPointException;
import edu.dpr212.MazeProject.MazeSolver.Maze;
import edu.dpr212.MazeProject.MazeSolver.MazeElement;
import edu.dpr212.MazeProject.MazeSolver.Mouse;

/**
 * The main JFrame window for the Maze Solver program. This class holds the GUI
 * components and launches the program.
 * 
 * @author Pete Johnston
 *
 */
public class Window extends JFrame {

	private static final long serialVersionUID = 6996938059047415722L;

	public static final int INITIAL_CELL_WIDTH = 30;
	public static final int FRAME_WIDTH = 850;
	public static final int FRAME_HEIGHT = 850;
	public static final Dimension FRAME_DIMENSION = new Dimension(FRAME_WIDTH,FRAME_HEIGHT);
	public static final String DEFAULT_TEXT_AREA = "Input the starting location then click \"Solve\". You must start in an open cell and you may not start on an exit.";
	public static final Font WINDOW_FONT = new Font(Font.SERIF, Font.PLAIN, 18);

	private Maze maze;
	private Mouse toby;
	private Cell[] solution;

	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenuItem loadMenuItem;
	private JMenuItem exitMenuItem;
	private JButton solveButton;
	private JButton resetButton;
	private JButton increaseCellSizeButton;
	private JButton decreaseCellSizeButton;
	private JLabel startXLabel;
	private JLabel startYLabel;
	private JTextField startXField;
	private JTextField startYField;
	private JTextArea textArea;
	private JPanel southPanel;
	private JScrollPane canvasScrollPane;
	private MazeCanvas canvas;

	/**
	 * The constructor initializes the Maze object, creates and places the
	 * components of the GUI, and sets the configurations for the frame.
	 * 
	 * @throws FileNotFoundException
	 * @throws InvalidFileFormatException
	 */
	public Window() {
		createComponents();
		
		setLayout(new BorderLayout());
		getContentPane().add(canvasScrollPane, BorderLayout.CENTER);
		getContentPane().add(southPanel, BorderLayout.SOUTH);
		getContentPane().add(textArea, BorderLayout.NORTH);
		
		getRootPane().setDefaultButton(solveButton);
		setMinimumSize(FRAME_DIMENSION);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Maze Solver");
		setVisible(true);
	}

	/**
	 * Launches the Window. 
	 * 
	 * @param args *not used*
	 */
	public static void main(String[] args) {
		new Window();
	}

	/**
	 * Initializes all of the GUI components and places them on the frame.
	 */
	private void createComponents() {
		startXLabel = new JLabel("Starting Column:");
		startYLabel = new JLabel("Starting Row:");

		startXField = new JTextField(4);
		startXField.requestFocus();
		startYField = new JTextField(4);

		textArea = new JTextArea("Load a maze using File>Load");
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setFocusable(false);

		canvas = new MazeCanvas(); // custom JComponent for drawing
		canvasScrollPane = new JScrollPane(canvas);

		createMenuBar();
		createButtons();
		setFonts(WINDOW_FONT);

		southPanel = new JPanel();
		southPanel.add(startXLabel);
		southPanel.add(startXField);
		southPanel.add(startYLabel);
		southPanel.add(startYField);
		southPanel.add(solveButton);
		southPanel.add(resetButton);
		southPanel.add(decreaseCellSizeButton);
		southPanel.add(increaseCellSizeButton);
	}

	/**
	 * Creates the menu bar. Contains an inner class for each menu item.
	 */
	private void createMenuBar() {
		menuBar = new JMenuBar();
		fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_F);

		loadMenuItem = new JMenuItem("Load", KeyEvent.VK_L);
		loadMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				loadFile();
			}
		});

		exitMenuItem = new JMenuItem("Exit", KeyEvent.VK_X);
		exitMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4,
				ActionEvent.ALT_MASK));
		exitMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				exitProgram();
			}
		});

		fileMenu.add(loadMenuItem);
		fileMenu.add(exitMenuItem);
		menuBar.add(fileMenu);
		setJMenuBar(menuBar);
	}

	/**
	 * Creates the buttons for the GUI. Contains an inner class for each
	 * button's action.
	 */
	private void createButtons() {
		solveButton = new JButton("Solve");
		solveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				solveMaze();
			}
		});

		resetButton = new JButton("Reset");
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				resetMaze();
				startXField.setText("");
				startXField.requestFocus();
				startYField.setText("");
				textArea.setText(DEFAULT_TEXT_AREA);
			}
		});

		increaseCellSizeButton = new JButton("Increase Size");
		increaseCellSizeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				canvas.increaseCellWidth();
				canvasScrollPane.revalidate();
			}

		});

		decreaseCellSizeButton = new JButton("Decrease Size");
		decreaseCellSizeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				canvas.decreaseCellWidth();
				canvasScrollPane.revalidate();
			}

		});
	}

	/**
	 * Takes in a font and sets all of the component's fonts to that font.
	 */
	private void setFonts(Font font) {
		startXLabel.setFont(font);
		startYLabel.setFont(font);
		startXField.setFont(font);
		startYField.setFont(font);
		textArea.setFont(font);
		solveButton.setFont(font);
		resetButton.setFont(font);
		increaseCellSizeButton.setFont(font);
		decreaseCellSizeButton.setFont(font);
		menuBar.setFont(font);
		fileMenu.setFont(font);
		loadMenuItem.setFont(font);
		exitMenuItem.setFont(font);
	}

	/**
	 * Used by the Load menu item's inner class to allow the user to select a
	 * maze file.
	 */
	private void loadFile() {
		JFileChooser fileChooser = new JFileChooser("src/");
		fileChooser.setFileFilter(new FileNameExtensionFilter(
				"Text Documents (*.txt)", "txt"));

		if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			loadMaze(fileChooser.getSelectedFile());
		}
	}

	/**
	 * Takes in a File path and attempts to create a Maze object using the file.
	 * Handles the FileNotFoundException and InvalidFileFormatException.
	 * 
	 * @param file
	 */
	private void loadMaze(File file) {
		try {
			maze = new Maze(file);
			textArea.setText(DEFAULT_TEXT_AREA);
			canvas.setMazeData(maze.getMazeData());
			canvas.setCellWidth(Window.INITIAL_CELL_WIDTH);
			canvas.resize();
			canvas.repaint();
			canvasScrollPane.revalidate();

			if (toby != null) {
				toby = null;
			}

		} catch (FileNotFoundException | InvalidFileFormatException | NumberFormatException e) {
			textArea.setText("Invalid File. Make sure the file is a .txt file and is of the correct format.");
		}
	}

	/**
	 * Initializes the Mouse object using the data in startXField and
	 * startYField. Starts the solveMaze() algorithm in the Mouse class. If a
	 * mouse already exists, the maze is reset before a new one is created.
	 */
	private void solveMaze() {
		int startX;
		int startY;
		if (toby != null) {
			resetMaze();
		}

		if (maze != null) {
			try {
				startX = Integer.parseInt(startXField.getText().trim());
				startY = Integer.parseInt(startYField.getText().trim());

				toby = new Mouse(startX, startY, maze.getMazeData());
				solution = toby.solveMaze();

				// For now, the solution is stored in a Cell[] array and printed to the console		
				if (toby.isSolved()) {
					textArea.setText("Toby has escaped!");
					System.out.println("Exit Path:");

					for (int i = 0; i < solution.length; i++) {
						System.out.println(solution[i].toString());
						solution[i].setColor(Color.BLUE);
					}
					
					canvas.repaint();
				} else {
					resetMaze();
					textArea.setText("Toby stumbled around for a while, bumping into walls, then eventually died of boredom and frustration.");
				}
			} catch (InvalidStartingPointException | NumberFormatException e) {
				resetMaze();
				textArea.setText("Invalid starting location. You must start in an open cell within the maze, and you may not start on an exit.");
			}
		}
		else{
			textArea.setText("One cannot simply solve a maze that does not exist. Load a maze then try again.");
		}
	}

	/**
	 * Resets each MazeElement to their initial state, disposes of the Mouse
	 * object (RIP Toby), and repaints the canvas.
	 */
	private void resetMaze() {
		if (maze != null) {
			MazeElement[][] data = maze.getMazeData();
			for (int y = 0; y < data.length; y++) {
				for (int x = 0; x < data[0].length; x++) {
					if (data[y][x].getTYPE() != ElementType.Boundary) {
						Cell cell = (Cell) data[y][x];
						cell.setPartOfPath(false);
						cell.setCheckedEast(false);
						cell.setCheckedWest(false);
						cell.setCheckedNorth(false);
						cell.setCheckedSouth(false);
						cell.setColor(Color.WHITE);
					}
				}
			}
		}
		
		toby = null;
		canvas.repaint();
	}

	/**
	 * Used by the Exit menu item's inner class to close the frame.
	 */
	private void exitProgram() {
		this.dispose();
	}

}
