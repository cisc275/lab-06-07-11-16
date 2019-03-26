/**
 * Do not modify this file without permission from your TA.
 **/


import java.awt.event.*;


public class Controller implements ActionListener,KeyListener{
	
	private Model model;
	private View view;
	
	public Controller(){
		view = new View();
		model = new Model(view.getWidth(), view.getHeight(), view.getImageWidth(), view.getImageHeight());
		view.addControllerToButton(this);
		view.addControllerToKeyboard(this);
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
			view.update(model.getX(), model.getY(), model.getDirect(), model.getMoving(), model.getState());
			//System.out.println("in start");
		}
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		//change the state of the orc with the value of the pressed key
		if (e.getKeyCode() == KeyEvent.VK_J)
			model.jump(); 
		if (e.getKeyCode() == KeyEvent.VK_F)
			model.fire();
		if(e.getKeyCode() == KeyEvent.VK_R)
			model.run();
		
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}        
}