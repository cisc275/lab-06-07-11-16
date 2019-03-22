
/**
 * View: Contains everything about graphics and images
 * Know size of world, which images to load etc
 *
 * has methods to
 * provide boundaries
 * use proper images for direction
 * load images for all direction (an image should only be loaded once!!! why?)
 */

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;



@SuppressWarnings("serial")
class View extends JPanel{


    JButton b1 = new JButton("PUSH ME"); 
    boolean buttonOn = false;
    public void clickButton(){
	buttonOn = !buttonOn;
	if(buttonOn){
	    System.out.println("turned on");
	}else{
	    System.out.println("turned off");
	}
    }

    JFrame frame;
    Dimension windowSize;
    
    static int frameWidth = 500;
    static int frameHeight = 300;
    int imgWidth = 165;
    int imgHeight = 165;

    private int xloc = 0;
    private int yloc = 0;
    private Direction d;

    final int frameCount = 10;
    int picNum = 0;

    BufferedImage[] pics;
    BufferedImage[] picsWest;
    BufferedImage[] picsEast;
    BufferedImage[] picsNorth;
    BufferedImage[] picsSouth;
    BufferedImage[] picsSWest;
    BufferedImage[] picsSEast;
    BufferedImage[] picsNWest;
    BufferedImage[] picsNEast;
   

    
    //(view.getWidth(), view.getHeight()), view.getImageWidth(), view.getImageHeight());
    public int getWidth(){return frameWidth;}
    public int getHeight(){return frameHeight;}
    public int getImageWidth(){return imgWidth;}
    public int getImageHeight(){return imgHeight;}


    public View() {
	this.createFrame();
	this.createImages();
    }

    void addControllerToButton(Controller c){
	b1.addActionListener(c);
    }
    
    
    private void createFrame(){
       
    	frame = new JFrame();
    	frame.getContentPane().add(this);
    	frame.setBackground(Color.gray);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(frameWidth, frameHeight);


	JPanel panel = new JPanel();
	panel.setBackground(Color.gray);
	panel.add(b1);   //so button does not take up entire frame
	frame.add(panel);
        frame.setVisible(true); //NOTE: must put all in frame before setVisible
	
    	windowSize  = new Dimension(frameWidth, frameHeight);   	
    	frame.setSize(windowSize);
    	frame.setMinimumSize(windowSize);
    	frame.setMaximumSize(windowSize);
	
    }
    
    //View.update(model.getX(), model.getY(), model.getDirect()); basically 
    public void update(int x, int y, Direction direction){
	frame.setSize(windowSize);
    	xloc = x;
    	yloc = y;
    	d = direction;

	
    	frame.repaint();
    	try {
    		Thread.sleep(100);
    		} 
    	catch (InterruptedException e) {
    		e.printStackTrace();
    		}
    	}

     private BufferedImage createImage(String d){
    	BufferedImage bufferedImage;
    	try {
    		bufferedImage = ImageIO.read(new File("images/orc/orc_forward_" + d +".png"));
    		return bufferedImage;
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
	return null;
       
    }
    
    private void createImages(){
		
	BufferedImage img = createImage("west"); //for west direction
    	picsWest = new BufferedImage[10];
    	for(int i = 0; i < frameCount; i++)
    		picsWest[i] = img.getSubimage(imgWidth*i, 0, imgWidth, imgHeight);
    	
    	BufferedImage img2 = createImage("east"); //for east direction
    	picsEast = new BufferedImage[10];
    	for(int i = 0; i < frameCount; i++)
    		picsEast[i] = img2.getSubimage(imgWidth*i, 0, imgWidth, imgHeight);
    	
    	BufferedImage img3 = createImage("north"); //for north direction
    	picsNorth = new BufferedImage[10];
    	for(int i = 0; i < frameCount; i++)
    		picsNorth[i] = img3.getSubimage(imgWidth*i, 0, imgWidth, imgHeight);
    	
    	BufferedImage img4 = createImage("south"); //for south direction
    	picsSouth = new BufferedImage[10];
    	for(int i = 0; i < frameCount; i++)
    		picsSouth[i] = img4.getSubimage(imgWidth*i, 0, imgWidth, imgHeight); 
    	
    	BufferedImage img5 = createImage("southwest"); //for south-west direction
    	picsSWest = new BufferedImage[10];
    	for(int i = 0; i < frameCount; i++)
    		picsSWest[i] = img5.getSubimage(imgWidth*i, 0, imgWidth, imgHeight); 
    	
    	BufferedImage img6 = createImage("southeast"); //for south-east direction
    	picsSEast = new BufferedImage[10];
    	for(int i = 0; i < frameCount; i++)
    		picsSEast[i] = img6.getSubimage(imgWidth*i, 0, imgWidth, imgHeight); 
    	
    	BufferedImage img7 = createImage("northwest"); //for north-west direction
    	picsNWest = new BufferedImage[10];
    	for(int i = 0; i < frameCount; i++)
    		picsNWest[i] = img7.getSubimage(imgWidth*i, 0, imgWidth, imgHeight);
    	
    	BufferedImage img8 = createImage("northeast"); //for north-west direction
    	picsNEast = new BufferedImage[10];
    	for(int i = 0; i < frameCount; i++)
    		picsNEast[i] = img8.getSubimage(imgWidth*i, 0, imgWidth, imgHeight);
    }



    public void paint(Graphics g){
	picNum = (picNum + 1) % frameCount;

	if (d == Direction.EAST) { //draw east
    	g.drawImage(picsEast[picNum], xloc, yloc, this);
	}
	else if (d == Direction.WEST) { //draw west
    	g.drawImage(picsWest[picNum], xloc, yloc,  this);
	}
	else if (d == Direction.NORTH) { // draw north
    	g.drawImage(picsNorth[picNum], xloc, yloc, this);
	}
	else if (d == Direction.SOUTH) { // draw south
    	g.drawImage(picsSouth[picNum], xloc, yloc, this);
	}
	else if (d == Direction.SOUTHEAST) { // draw south-east
    	g.drawImage(picsSEast[picNum], xloc, yloc, this);
	}
	else if (d == Direction.SOUTHWEST) { // draw south-west
    	g.drawImage(picsSWest[picNum], xloc, yloc, this);
	}
	else if (d == Direction.NORTHEAST) { // draw north-east
    	g.drawImage(picsNEast[picNum], xloc, yloc, this);
	}
	else if (d == Direction.NORTHWEST) { // draw north-west
    	g.drawImage(picsNWest[picNum], xloc, yloc, this);
	}
    }
   
    
}
