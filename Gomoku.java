import java.awt.Color;
import java.awt.Graphics;
import java.awt.List;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Gomoku extends JPanel implements MouseListener{
	
	//General variables, such as screen size etc.
	private Rectangle screen;	
	public JFrame frame;
	private Point boardSize;
	private int tileSize;
	public gomokuTimerTask gomokuTask;
	private Random random;
	
	//Game
	private int currentColor;	//Either 1 or 2
	private int[][] grid;
	
	//Colors
	private final Color player1Color = new Color(0,200,0);
	private final Color player2Color = new Color(0,0,200);
	private final Color emptyColor = new Color(200,200,200);
	
	public Gomoku(){
		
		super();
		
		//	General variables
		gomokuTask = new gomokuTimerTask();
		boardSize = new Point(70,40);
		grid = new int[boardSize.x][boardSize.y];
		for(int x = 0; x<boardSize.x;x++){
			for(int y = 0;y<boardSize.y;y++){
				grid[x][y] = 0;
			}
		}
		tileSize = 20;
		int screenHeigth = boardSize.y*tileSize;
		int screenWidth = boardSize.x*tileSize;
		screen = new Rectangle(0, 0, screenWidth, screenHeigth);
		frame = new JFrame("Gomoku");
		random = new Random();
		
		//Game
		currentColor = 1;

		//Add listeners that keep track of the mouse 
		addMouseListener(this);
	}
	
	class gomokuTimerTask extends TimerTask{ 
		//	Main loop, done every iteration.
		public void run(){
			
			//Repaint
			frame.repaint();
		}
	}
	
	public void paintComponent(Graphics g){
		
		for(int x = 0;x<boardSize.x;x++){
			for(int y = 0; y<boardSize.y;y++){
				
				if(grid[x][y] == 1){
					g.setColor(player1Color);
				}else if(grid[x][y]== 2){
					g.setColor(player2Color);
				}else{
					g.setColor(emptyColor);
				}
				g.fillOval(x*tileSize, y*tileSize, tileSize, tileSize);
			}
		}
	}
	
	//	All functions need to be written (even if they are empty) in order to comply with the MouseListener interface
	public void mouseClicked(MouseEvent mouse){
		grid[mouse.getX()/tileSize][mouse.getY()/tileSize] = currentColor;
		//Switch player
		if(currentColor == 1){
			currentColor = 2;
		}else{
			currentColor = 1;
		}
    }
	public void mouseEntered(MouseEvent mouse){ 
	}   
    public void mouseExited(MouseEvent mouse){
    }
    public void mousePressed(MouseEvent mouse){
    }
    public void mouseReleased(MouseEvent mouse){ 
    }
    public void mouseDragged(MouseEvent mouse){	
    }
    public void mouseMoved(MouseEvent mouse){
    }
    
	public static void main(String args[]){
		//	Create a timer.
		java.util.Timer mainProgramTimer = new java.util.Timer();
		//	Create a new instance of the program
		Gomoku panel = new Gomoku();
		
		// 	Set up the settings of our JFrame
		//	Close program if window is closed
	    panel.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    //	Set size of window
	    panel.frame.setSize(panel.screen.width, panel.screen.height);
	    //	Things should happen in the panel
	    panel.frame.setContentPane(panel); 
	    //	Make window visible
	    panel.frame.setVisible(true);
	    
		//	Do this regularly
	    mainProgramTimer.schedule(panel.gomokuTask, 0, 20);
	}
}
