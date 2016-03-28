package Pacman;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

import javax.swing.ImageIcon;


public class Ghost {

	private int x;
	private int y;
	private Image image;
	private Image imageleft;
	private Image imageright;
	private int facing;

	public Ghost (int x, int y){
		this.x = x;
		this.y = y;
		ImageIcon i = new ImageIcon(this.getClass().getResource("Ghost1.gif"));
		ImageIcon i2 = new ImageIcon(this.getClass().getResource("Ghost2.gif"));
		imageleft = i.getImage();
		imageright = i2.getImage();
		image = imageleft;
	}
	public int Getx(){
		return x;
}
	public int Gety(){
		return y;
	}
	public void Setx(int a){
		a+=0;
		x += a;
		if (a < 0){
			image = imageleft;
			facing = 2;
		}
		if (a > 0){
			image = imageright;
			facing = 4;
	}
	}
	public void Sety(int a){
			a+=0;
			y += a;
	}
	public Image getImage(){
		return image;
	}
	public Rectangle getBounds() {
		return new Rectangle(x , y , 25, 25);
}
	public int getFacing(){
		return facing;
	}
	
}


