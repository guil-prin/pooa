package drawing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CloneButtonListener implements ActionListener {

	Drawing drawing;
	
	public CloneButtonListener(Drawing drawing){
		this.drawing=drawing;
	}
	
	public void actionPerformed(ActionEvent arg0) {
		drawing.duplicateShape();
	}

}
