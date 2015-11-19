package drawing;

import java.awt.Graphics;
import java.awt.Point;

/**
 * Classe abstraite de type forme dessinable.
 */
public abstract class Shape implements Cloneable {
	
	protected Point origin;
	
	public void setOrigin(Point p)
	{
		origin = p;
	}
	
	public Point getOrigin() {
		return origin;
	}
	
	@Override
	public Shape clone() {
		Object o = null;
		
		try {
			o = super.clone();
		} catch(CloneNotSupportedException cnse) {
			cnse.printStackTrace(System.err);
		}
		return (Shape) o;
	}
	
	/**
	 * dessine la forme sur un Graphics
	 */
	public abstract void paint(Graphics g);
	
	/**
	 * renvoie true si la forme occupe sur le point donn�
	 */
	public abstract boolean isOn(Point p);
}
