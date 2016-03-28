package Pacman;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;


public class Game extends JPanel implements ActionListener{
private DeathSphere sphere1;
private Ghost ghost1;
private Ghost ghost2;
private Ghost ghost3;
private Ghost ghost4;
private Timer timer;
private int direction;
private boolean canmove;
private int score = 0;
Font myFont = new Font("Serif", Font.BOLD, 25);

int x1;
int y1;
int[][] a={
		  {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
		  {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
		  {1,1,2,2,2,2,2,2,2,2,2,2,2,2,1,1,2,2,2,2,2,2,2,2,2,2,2,2,1,1},
		  {1,1,2,1,1,1,1,2,1,1,1,1,1,2,1,1,2,1,1,1,1,1,2,1,1,1,1,2,1,1},
		  {1,1,3,1,0,0,1,2,1,0,0,0,1,2,1,1,2,1,0,0,0,1,2,1,0,0,1,3,1,1},
		  {1,1,2,1,1,1,1,2,1,1,1,1,1,2,1,1,2,1,1,1,1,1,2,1,1,1,1,2,1,1},
		  {1,1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1,1},
		  {1,1,2,1,1,1,1,2,1,1,2,1,1,1,1,1,1,1,1,2,1,1,2,1,1,1,1,2,1,1},
		  {1,1,2,1,1,1,1,2,1,1,2,1,1,1,1,1,1,1,1,2,1,1,2,1,1,1,1,2,1,1},
		  {1,1,2,2,2,2,2,2,1,1,2,2,2,2,1,1,2,2,2,2,1,1,2,2,2,2,2,2,1,1},
		  {1,1,1,1,1,1,1,2,1,1,1,1,1,0,1,1,0,1,1,1,1,1,2,1,1,1,1,1,1,1},
		  {1,1,0,0,0,0,1,2,1,1,1,1,1,0,1,1,0,1,1,1,1,1,2,1,0,0,0,0,1,1},
		  {1,1,0,0,0,0,1,2,1,1,0,0,0,0,0,0,0,0,0,0,1,1,2,1,0,0,0,0,1,1},
		  {1,1,0,0,0,0,1,2,1,1,0,1,1,1,0,0,1,1,1,0,1,1,2,1,0,0,0,0,1,1},
		  {1,1,1,1,1,1,1,2,1,1,0,1,0,0,0,0,0,0,1,0,1,1,2,1,1,1,1,1,1,1},
		  {0,0,0,0,0,0,0,2,0,0,0,1,0,0,0,0,0,0,1,0,0,0,2,0,0,0,0,0,0,0},
		  {1,1,1,1,1,1,1,2,1,1,0,1,0,0,0,0,0,0,1,0,1,1,2,1,1,1,1,1,1,1},
		  {1,1,0,0,0,0,1,2,1,1,0,1,1,1,1,1,1,1,1,0,1,1,2,1,0,0,0,0,1,1}, //"1" - obstacle
		  {1,1,0,0,0,0,1,2,1,1,0,0,0,0,0,0,0,0,0,0,1,1,2,1,0,0,0,0,1,1},
		  {1,1,0,0,0,0,1,2,1,1,0,1,1,1,1,1,1,1,1,0,1,1,2,1,0,0,0,0,1,1}, 
		  {1,1,1,1,1,1,1,2,1,1,0,1,1,1,1,1,1,1,1,0,1,1,2,1,1,1,1,1,1,1},
		  {1,1,2,2,2,2,2,2,2,2,2,2,2,2,1,1,2,2,2,2,2,2,2,2,2,2,2,2,1,1},
		  {1,1,2,1,1,1,1,2,1,1,1,1,1,2,1,1,2,1,1,1,1,1,2,1,1,1,1,2,1,1},
		  {1,1,2,1,1,1,1,2,1,1,1,1,1,2,1,1,2,1,1,1,1,1,2,1,1,1,1,2,1,1},
		  {1,1,3,2,2,1,1,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,1,1,2,2,3,1,1},
		  {1,1,1,1,2,1,1,2,1,1,2,1,1,1,1,1,1,1,1,2,1,1,2,1,1,2,1,1,1,1},
		  {1,1,1,1,2,1,1,2,1,1,2,1,1,1,1,1,1,1,1,2,1,1,2,1,1,2,1,1,1,1},
		  {1,1,2,2,2,2,2,2,1,1,2,2,2,2,1,1,2,2,2,2,1,1,2,2,2,2,2,2,1,1},
		  {1,1,2,1,1,1,1,1,1,1,1,1,1,2,1,1,2,1,1,1,1,1,1,1,1,1,1,2,1,1},
		  {1,1,2,1,1,1,1,1,1,1,1,1,1,2,1,1,2,1,1,1,1,1,1,1,1,1,1,2,1,1},
		  {1,1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1,1},
		  {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
		  {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
		};
	private ArrayList <Ghost> ghosts;
	
	public Game(){
		setFocusable(true);
		requestFocus();
		addKeyListener(new TAdapter());
		sphere1 = new DeathSphere(350,600);
		setBackground(Color.BLACK);
		
		ghosts = new ArrayList();
		ghost1 = new Ghost (425,350);
		ghosts.add(ghost1);
		ghost2 = new Ghost (300,350);
		ghosts.add(ghost2);
		ghost3 = new Ghost (300,400);
		ghosts.add(ghost3);
		ghost4 = new Ghost (425,400);
		ghosts.add(ghost4);
	
		timer = new Timer(500, this);
		timer.start();
	}
	
	public void paint(Graphics g){
		super.paint(g);
		g.drawImage(sphere1.GetImage(),sphere1.Getx(),sphere1.Gety(),null);
		g.drawImage(ghost1.getImage(), ghost1.Getx(), ghost1.Gety(), null);
		g.drawImage(ghost2.getImage(), ghost2.Getx(), ghost2.Gety(), null);
		g.drawImage(ghost3.getImage(), ghost3.Getx(), ghost3.Gety(), null);
		g.drawImage(ghost4.getImage(), ghost4.Getx(), ghost4.Gety(), null);
		
		for(int x=0;x<33;x++){
			for(int y=0;y<30;y++){
				if(a[x][y]==1){
					g.setColor(Color.BLUE);
					g.fillRoundRect(y*25, x*25, 25, 25,5,5);
					g.setColor(Color.BLACK);
				    g.drawRect(y*25, x*25, 25, 25);
					
				}
				else if (a[x][y] == 2){
					g.setColor(Color.YELLOW);
					g.fillOval((y*25 +8), (x*25 +8), 10, 10);
				}
				else if (a[x][y] == 3){
					g.setColor(Color.YELLOW);
					g.fillOval((y*25 +3), (x*25 + 3), 20, 20);
				}         
			}
                }
                g.setFont(myFont);
                g.setColor(Color.yellow);
                g.drawString("Score: " + score, 20, 35);
                System.out.println(sphere1.Getx());
		}
        
                public int countSpheres()
                {
                    int sum = 0;
                    for(int x = 0; x < 33; x++)
                        for(int y = 0; y < 30; y++)
                            if(a[x][y] == 2)
                                sum++;
                    return sum;
                }
                
		// facing: 1 = UP, 2 = LEFT, 3 = DOWN, 4 = RIGHT
		public void wallcollision() {
			for(int x=0;x<33;x++){
				for(int y=0;y<30;y++){
					if(a[x][y]==1){
					Rectangle rect1 = new Rectangle (y*25, x*25, 25, 25);
						if (sphere1.getBounds().intersects(rect1)){
							if (sphere1.getFacing() == 1)
									sphere1.Sety(25);
							else if (sphere1.getFacing() == 2)
									sphere1.Setx(25);
							else if (sphere1.getFacing() == 3)
									sphere1.Sety(-25);
							else if (sphere1.getFacing() == 4)
									sphere1.Setx(-25);	
						}
					}
				}
			}
		}
		
		public void dotcollision(){
			for (int x=0; x<33; x++){
				for (int y=0; y<30; y++){
					if(a[x][y] == 2){
						Rectangle rect2 = new Rectangle(y*25,x*25, 25, 25);
						if (sphere1.getBounds().intersects(rect2))
                                                {
                                                    a[x][y] = 0;
                                                    score += 10;
                                                }
                                                if(countSpheres() == 0)
                                                    System.exit(0);
					}
					else if(a[x][y] ==3){
						Rectangle rect3 = new Rectangle(y*25,x*25, 25, 25);
						if (sphere1.getBounds().intersects (rect3))
                                                {
                                                        a[x][y] = 0;
                                                        score += 25;
                                                }
					}
					}
				}
			}
		
		public void ghostcollision(){
			for(Ghost ghost : ghosts){
				if(sphere1.getBounds().intersects(ghost.getBounds())){
					System.exit(0);
				}
			}
		}
class TAdapter extends KeyAdapter{
	public void keyPressed(KeyEvent e) {
            //if(sphere1.Getx() > 0 & sphere1.Getx() < 725)
            //{
                if(sphere1.Getx() == 0 && e.getKeyCode()== KeyEvent.VK_A)
                        sphere1.Setx(725);
                else if(sphere1.Getx() == 725 && e.getKeyCode() == KeyEvent.VK_D)
                        sphere1.Setx(-725);
                else if (e.getKeyCode()== KeyEvent.VK_A)
			sphere1.Setx(-25);
		else if (e.getKeyCode() == KeyEvent.VK_D)
			sphere1.Setx(25);
		else if (e.getKeyCode() == KeyEvent.VK_W)
			sphere1.Sety(-25);
		else if (e.getKeyCode() == KeyEvent.VK_S)
			sphere1.Sety(25);
            //}
            
		wallcollision();
		dotcollision();
		ghostcollision();
		repaint();
	}
}
//public void endgame(){
//	System.exit(0);
//}

/*public void ghostmove(){
	Random r = new Random();
	int n = r.nextInt(4);
		for(Ghost ghost : ghosts){
			if(n == 0)
				ghost.Setx(25);
			if(n == 1)
				ghost.Setx(-25);
			if(n == 2)
				ghost.Sety(25);
			if(n == 3)
				ghost.Sety(-25);
		}	
}*/

public boolean isvalid(){
	for(Ghost ghost : ghosts){
            for(int x=0;x<33;x++){
            for(int y=0;y<30;y++){
		if(a[x][y]==1){
                    Rectangle rect1 = new Rectangle (y*25, x*25, 25, 25);
		if (ghost.getBounds().intersects(rect1))
                    return false;
                              }
                        }
                        }   
	}
return true;
}
public void ghostmove(){
	Random r = new Random();
	int n = r.nextInt(4);
	for(Ghost ghost : ghosts){
            if(isvalid()){
			if(n == 0)
				ghost.Setx(25);
			if(n == 1)
				ghost.Setx(-25);
			if(n == 2)
				ghost.Sety(25);
			if(n == 3)
				ghost.Sety(-25);
                }
        }
}

public void actionPerformed(ActionEvent arg0){
	//ghostmove();
	repaint();

}
}