package drawing;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.Point;

/**
 * Listener pour gerer la souris dans la zone de dessin
 */
public class DrawingMouseListener implements MouseMotionListener, MouseListener {

	Drawing drawing;
	Shape currentShape = null;
	
	public DrawingMouseListener(Drawing d){
		drawing = d;
	}
	
	/**
	 * Bouge la forme selectionnee (si une forme est selectionnee)
	 */
	public void mouseDragged(MouseEvent e) {
		if(currentShape != null){
			if(e.getModifiers() == MouseEvent.BUTTON1_MASK) {
				currentShape.setOrigin(e.getPoint());
				drawing.repaint();
			} else {
				if(e.getModifiers() == MouseEvent.BUTTON3_MASK) {
					for(Shape s : drawing.groupedShapes){
						if(s.isOn(e.getPoint())){
							for(Shape sh : drawing.groupedShapes){
								//int px = (int) e.getPoint().getX();
								//int py = (int) e.getPoint().getY();
								//int sx = (int) sh.getOrigin().getX();
								//int sy = (int) sh.getOrigin().getY();
								Point p = new Point(e.getPoint());
								sh.setOrigin(p);
								drawing.repaint();
							}
						}
					}
				}
			}
		}
	}
	
	/**
	 * Selectionne la forme sur laquelle l'utilisateur a clique
	 */
	public void mousePressed(MouseEvent e) {
		for(Shape s : drawing){
			if(s.isOn(e.getPoint())){
				drawing.currentShape = s;
				currentShape = s;
				break;
			}
		}
	}

	/**
	 * Deselectionne la forme
	 */
	public void mouseReleased(MouseEvent e) {
		currentShape = null;

	}

	public void mouseMoved(MouseEvent e) {
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}
}
