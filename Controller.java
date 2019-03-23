/**
 * Do not modify this file without permission from your TA.
 **/


import java.awt.event.*;

public class Controller implements ActionListener{
	
	private Model model;
	private View view;
	
	public Controller(){
		view = new View();
		model = new Model(view.getWidth(), view.getHeight(), view.getImageWidth(), view.getImageHeight());
		view.addControllerToButton(this);
	}  
	public void actionPerformed(ActionEvent e){
		//code that reacts to action
		model.startStop();
	}
	
	//run the simulation
	public void start(){
		for(int i = 0; i < 5000; i++) {
			//increment the x and y coordinates, alter direction if necessary
			model.updateLocationAndDirection();
			//update the view
			view.update(model.getX(), model.getY(), model.getDirect(), model.getMoving());
		}
	}        
}