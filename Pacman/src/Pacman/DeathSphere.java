package Pacman; 

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;


public class DeathSphere {
	private int x = 0;
	private int y = 0;
	private Image image;
	private Image imageright;
	private Image imagedown;
	private Image imageup;
	private Image imageleft;
	private int facing;
	
	public DeathSphere(int x, int y){
		this.x = x;
		this.y = y;
		ImageIcon i = new ImageIcon(this.getClass().getResource("Pacman1.png"));
		ImageIcon i2 = new ImageIcon(this.getClass().getResource("Pacman2.png"));
		ImageIcon i3 = new ImageIcon(this.getClass().getResource("Pacman3.png"));
		ImageIcon i4 = new ImageIcon(this.getClass().getResource("Pacman4.png"));
		imageright = i.getImage();
		imagedown = i2.getImage();
		imageleft = i3.getImage();
		imageup = i4.getImage();
		image = imageleft;
		}
	
	public int Getx(){
		return x;
	}
	public int Gety(){
		return y;
	}
	public void Setx(int a){
		x += a;
		if(a == -725){
                    image = imageright;
                    facing = 4;
                }
                else if(a == 725){
                    image = imageleft;
                    facing = 2;
                }
                else if (a < 0 && a != -725){
			image = imageleft;
			facing = 2;
		}
                else if (a > 0){
			image = imageright;
			facing = 4;
	}
	}
	public void Sety(int a){
		y+=a;
		if (a < 0){
			image = imageup;
			facing = 1;
		}
		if (a > 0){
			image = imagedown;
			facing = 3;
	}
	}
	public Image GetImage(){
		return image;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x , y , 25, 25);
}
	public int getFacing(){
		return facing;
	}
        
        public void setFacing(int a){
            facing = a;
        }
}