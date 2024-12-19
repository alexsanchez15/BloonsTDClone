package projectiles;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import model.Bloons;
import model.GameManager;

public class Dart extends Projectile{
	public Dart(int x, int y, double angle) {
		
		this.x = x;
		this.y = y;
		this.angle = angle;
		this.range = 60; 
		double speed = GameManager.blockSize  / 4;
		this.r = new Rotate(angle, x , y);
		double angleRad = Math.toRadians(angle+180);
		xspeed =  (Math.cos(angleRad)) * speed;
        yspeed = (Math.sin(angleRad)) * speed;
        thisImage = new Image("projectiles/dartpng.png");
		power = 1;
		durability =2;
		
	}
	

	@Override
	public void makeProjectile(GraphicsContext gc) {
		
		gc.drawImage(thisImage, x-size/2, y-size/2, size, size);
		
//		gc.setFill(Color.BLACK);
//		gc.fillRect(x-size/2, y-size/2, size, size);
		
		
	
		
	}
}
