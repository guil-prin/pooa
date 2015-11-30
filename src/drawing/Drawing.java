package drawing;

import javax.swing.*;

import java.awt.*;
import java.util.*;
/**
 * JPanel pouvant afficher des objets de type Shape
 */
public class Drawing extends JPanel implements Iterable<Shape> {

	private static final long serialVersionUID = 1L;
	
	ArrayList<Shape> shapes;
	Shape currentShape = null;
	ArrayList<Shape> groupedShapes;
	
	ArrayList<Shape> undoCommands;
	ArrayList<Shape> redoCommands;
	
	private Vector<Observer> observers = new Vector<>();

	int counter = 0;
	
	public Drawing(){
		super();
		shapes = new ArrayList<Shape>();
		groupedShapes = new ArrayList<Shape>();
		undoCommands = new ArrayList<Shape>();
		redoCommands = new ArrayList<Shape>();
	}
	
	public void addObserver(Observer obs){
		observers.add(obs);
	}
	
	public void removeObserver(Observer obs){
		observers.remove(obs);
	}
	
	private void notifyObservers(){
		for (Observer obs : observers) {
			obs.update(counter);
		}
	}
	
	/**
	 * Implementation de l'interface Iterable<Shape>
	 */
	public Iterator<Shape> iterator(){
		return shapes.iterator();
	}
	
	/**
	 * Ajoute une forme au dessin et redessine
	 */
	public void addShape(Shape s){
		shapes.add(s);
		undoCommands.add(s);
		redoCommands.clear();
		this.repaint();
		counter++;
		notifyObservers();
	}
	
	/** 
	 * Redefinition de la methode paintComponent() de JComponent
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for(Shape s : shapes){
			s.paint(g);
		}
	}
	
	/**
	 * Enleve toutes les formes et redessine
	 */
	public void clear() {
		shapes.clear();
		groupedShapes.clear();
		this.repaint();
		counter = 0;
		notifyObservers();
	}
	
	/**
	 * Groupe la shape selectionnee dans la liste.
	 */
	public void groupShape() {
		if(currentShape != null && !(groupedShapes.contains(currentShape))) {
			groupedShapes.add(currentShape);
			notifyObservers();
		}
	}
	
	/**
	 * Degroupe la shape selectionnee dans la liste.
	 */
	public void dissociateShape() {
		if(currentShape != null && (groupedShapes.contains(currentShape))) {
			groupedShapes.remove(currentShape);
			notifyObservers();
		}
	}
	
	public void duplicateShape() {
		if(currentShape != null) {
			Shape s = currentShape.clone();
			this.addShape(s);
			notifyObservers();
		}
	}
	
	public void cancelWork() {
		if(undoCommands.size() > 0) {
			Shape s = undoCommands.get(undoCommands.size() - 1);
			undoCommands.remove(s);
			redoCommands.add(s);
			shapes.remove(s);
			this.repaint();
			counter--;
			notifyObservers();
		}
	}
	
	public void redoWork() {
		if(redoCommands.size() > 0) {
			Shape s = redoCommands.get(redoCommands.size() - 1);
			redoCommands.remove(s);
			undoCommands.add(s);
			shapes.add(s);
			this.repaint();
			counter++;
			notifyObservers();
		}
	}
}
