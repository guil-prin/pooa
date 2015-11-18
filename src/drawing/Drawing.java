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
	
	private Vector<Observer> observers = new Vector<>();

	int counter = 0;
	
	public Drawing(){
		super();
		shapes = new ArrayList<Shape>();
		groupedShapes = new ArrayList<Shape>();
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
	public void clear(){
		shapes.clear();
		groupedShapes.clear();
		this.repaint();
		counter = 0;
		notifyObservers();
	}
	
	/**
	 * Groupe la shape selectionnee dans la liste.
	 */
	public void groupShape(){
		if(currentShape != null && !(groupedShapes.contains(currentShape))) {
			groupedShapes.add(currentShape);
			notifyObservers();
		}
	}
	
	/**
	 * Degroupe la shape selectionnee dans la liste.
	 */
	public void dissociateShape(){
		if(currentShape != null && !(groupedShapes.contains(currentShape))) {
			groupedShapes.remove(currentShape);
			notifyObservers();
		}
	}
}
