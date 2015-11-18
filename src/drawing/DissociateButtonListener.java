package drawing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DissociateButtonListener implements ActionListener {

	Drawing drawing;
	
	public DissociateButtonListener(Drawing drawing){
		this.drawing=drawing;
	}
	
	public void actionPerformed(ActionEvent arg0) {
		drawing.dissociateShape();
	}

}
