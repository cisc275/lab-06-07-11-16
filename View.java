
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
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;



@SuppressWarnings("serial")
class View extends JPanel{
	
	JButton b1 = new JButton("PUSH ME"); 
	boolean buttonOn = false;
	
	JFrame frame;
	Dimension windowSize;
	
	static int frameWidth = 500;
	static int frameHeight = 300;
	int imgWidth = 165;
	int imgHeight = 165;
	
	private int xloc = 0;
	private int yloc = 0;
	private Direction d = Direction.SOUTHEAST;
	private boolean moving;
	
	String state = "forward"; //initial the state of the orc
	
	int frameCount = 10;
	int picNum = 0;
	
	
	HashMap<String, BufferedImage[][]> pics;
	
	public View() {
		this.createFrame();
		this.createImages();
	}
	//(view.getWidth(), view.getHeight()), view.getImageWidth(), view.getImageHeight());
	public int getWidth(){return frameWidth;}
	public int getHeight(){return frameHeight;}
	public int getImageWidth(){return imgWidth;}
	public int getImageHeight(){return imgHeight;}
	
	
	void addControllerToButton(Controller c){
		b1.addActionListener(c);
	}
	void addControllerToKeyboard(Controller c) {
		addKeyListener(c);
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
		this.setFocusable(true);
	}
	
	//View.update(model.getX(), model.getY(), model.getDirect()); basically 
	public void update(int x, int y, Direction direction, boolean mov, String state) {
		this.requestFocus();
		frame.setSize(windowSize);
		xloc = x;
		yloc = y;
		d = direction;
		moving = mov;
		if (this.state != state)
			picNum = -1;
		this.state = state;
		
		frame.repaint();
		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private BufferedImage createImage(String sauce) {
		BufferedImage bufferedImage;
		try {
			bufferedImage = ImageIO.read(new File(sauce));
			return bufferedImage;
		} catch (IOException e) {
			System.out.println(sauce);
			e.printStackTrace();
		}
		return null;
	}
	
	private void createImages() {
		//create pics hash map
		pics = new HashMap<String, BufferedImage[][]>();
		
		String[] keyWords = new String[] {"forward","fire","jump"};
		
		for (String S:keyWords) {
			//add value to the pics hash map for the running state
			BufferedImage[][] img = new BufferedImage[8][10];
			pics.put(S, img);
			for (int h = 0; h < 8; h++) {
				Direction dirk = Direction.atIndex(h);
				BufferedImage imgS = createImage("images/orc/orc_"+S+"_"+dirk.getName()+".png");
				if(S == "forward") {
					frameCount = 10;
				}
				else if(S == "fire") {
					frameCount = 4 ;
				}
				else {
					frameCount = 8;
				}
				for(int i = 0; i <  frameCount; i++)
					img[h][i] = imgS.getSubimage(imgWidth*i, 0, imgWidth, imgHeight);
			}
		}
//		//add value to the pics hash map for the firing state
//		BufferedImage[][] fire = new BufferedImage[8][4];
//		pics.put("firing", fire);
//		for (int h = 0; h < 8; h++) {
//			Direction dirk = Direction.atIndex(h);
//			BufferedImage imgFire = createImage("images/orc/orc_fire_"+dirk.getName()+".png");
//			frameCount = 4;
//			for(int i = 0; i < frameCount; i++)
//				fire[h][i] = imgFire.getSubimage(imgWidth*i, 0, imgWidth, imgHeight);
//		}
//		
//		//add value to the pics hash map for the jumping state
//		BufferedImage[][] jump = new BufferedImage[8][8];
//		pics.put("jumping", jump);
//		for (int h = 0; h < 8; h++) {
//			Direction dirk = Direction.atIndex(h);
//			BufferedImage imgJump = createImage("images/orc/orc_jump_"+dirk.getName()+".png");
//			frameCount = 8;
//			for(int i = 0; i < frameCount; i++)
//				jump[h][i] = imgJump.getSubimage(imgWidth*i, 0, imgWidth, imgHeight);
//		}
	}
	
	public void paint(Graphics g) {
		if (moving) {
			picNum = picNum + 1;
		}
		picNum = Math.max(picNum % pics.get(state)[d.getIndex()].length, 0);
		g.drawImage(pics.get(state)[d.getIndex()][picNum], xloc, yloc, this); //draw image
	}
}
