
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
	
	JFrame frame;
	Dimension windowSize;
	
	static int frameWidth = 500;
	static int frameHeight = 300;
	int imgWidth = 165;
	int imgHeight = 165;
	
	private int xloc = 0;
	private int yloc = 0;
	private Direction d;
	private boolean moving;
	
	final int frameCount = 10;
	int picNum = 0;
	
	BufferedImage[][] pics;
	
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
	public void update(int x, int y, Direction direction, boolean mov) {
		frame.setSize(windowSize);
		xloc = x;
		yloc = y;
		d = direction;
		moving = mov;
		
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
		pics = new BufferedImage[8][10];
		for (int h = 0; h < 8; h++) {
			Direction dirk = Direction.atIndex(h);
			//System.out.println("orc/orc_forward_"+dirk.getName()+".png");
			BufferedImage img = createImage("images/orc/orc_forward_"+dirk.getName()+".png");
			for(int i = 0; i < frameCount; i++)
				pics[h][i] = img.getSubimage(imgWidth*i, 0, imgWidth, imgHeight);
		}
	}
	
	public void paint(Graphics g) {
		if (moving) {
			picNum = (picNum + 1) % frameCount;
		}
		try {
			g.drawImage(pics[d.getIndex()][picNum], xloc, yloc, this);
		} catch (NullPointerException e) {
			
		}
	}
}
