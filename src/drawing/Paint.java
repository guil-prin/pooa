package drawing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Classe Interface graphique pour l'application de dessin
 */
public class Paint implements Observer {

	private JFrame frame;
	private JButton clearButton;
	private JButton circleButton;
	private JButton rectangleButton;
	private JButton groupButton;
	private JButton dissociateButton;
	private JButton cloneButton;
	private JButton undoButton;
	private JButton redoButton;
	private JPanel statusPanel;
	private JPanel buttonPanel;
	private JPanel createPanel;
	private JPanel interactPanel;
	private JPanel bottomPanel;
	private JPanel mainPanel;
	private JTextField statusBar;
	private Drawing drawing;
	private final int WIDTH = 640;
	
	public void run(){
		frame = new JFrame("Paint");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainPanel = new JPanel(new BorderLayout());
		
		drawing = new Drawing();
		drawing.addObserver(this);
		drawing.setBackground(Color.WHITE);
		clearButton = new JButton("Clear");
		circleButton = new JButton("Circle");
		rectangleButton = new JButton("Rectangle");
		groupButton = new JButton("Group");
		dissociateButton = new JButton("Dissociate");
		cloneButton = new JButton("Duplicate");
		undoButton = new JButton("Undo");
		redoButton = new JButton("Redo");
		
		bottomPanel = new JPanel(new BorderLayout());
		
		statusPanel = new JPanel();
		statusBar = new JTextField();
		statusPanel.add(statusBar);
		statusBar.setPreferredSize(new Dimension(WIDTH, 20));
		statusBar.setText(Integer.toString(0));
		
		buttonPanel = new JPanel(new BorderLayout());
		createPanel = new JPanel();
		interactPanel = new JPanel();
		createPanel.add(clearButton);
		createPanel.add(circleButton);
		createPanel.add(rectangleButton);
		interactPanel.add(groupButton);
		interactPanel.add(dissociateButton);
		interactPanel.add(cloneButton);
		interactPanel.add(undoButton);
		interactPanel.add(redoButton);
		
		bottomPanel.add(statusPanel, BorderLayout.NORTH);
		buttonPanel.add(createPanel, BorderLayout.NORTH);
		buttonPanel.add(interactPanel, BorderLayout.SOUTH);
		bottomPanel.add(buttonPanel, BorderLayout.SOUTH);
		mainPanel.add(bottomPanel, BorderLayout.SOUTH);
		mainPanel.add(drawing, BorderLayout.CENTER);
		
		//listeners pour les boutons
		clearButton.addActionListener(new ClearButtonListener(drawing));
		circleButton.addActionListener(new CircleButtonListener(drawing));
		rectangleButton.addActionListener(new RectangleButtonListener(drawing));
		groupButton.addActionListener(new GroupButtonListener(drawing));
		dissociateButton.addActionListener(new DissociateButtonListener(drawing));
		cloneButton.addActionListener(new CloneButtonListener(drawing));
		undoButton.addActionListener(new UndoButtonListener(drawing));
		redoButton.addActionListener(new RedoButtonListener(drawing));
		
		//listeners pour la zone de dessin
		DrawingMouseListener l = new DrawingMouseListener(drawing);
		drawing.addMouseListener(l);
		drawing.addMouseMotionListener(l);

		frame.getContentPane().add(mainPanel);
		frame.setSize(WIDTH,480);
		frame.setVisible(true);
	}
	
	
	public static void main(String[] args){
		Paint app = new Paint();
		app.run();
	}
	
	@Override
	public void update(int value) {
		statusBar.setText(Integer.toString(value));
	}
}
