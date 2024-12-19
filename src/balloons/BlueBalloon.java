package balloons;

import javafx.scene.paint.Color;
import model.*;
import tiles.PathTile;

public class BlueBalloon extends Balloon{
//	final int blockSize = GameManager.blockSize; //convenience
//	int value; //1 = red balloon, 2 = blue balloon, 3 = yellow balloon
//	int speed; //pixels/second (using block for refrence)
//	int x;
//	int y;
//	int tileX;
//	int tileY;
//	Color color;
	
	
	public BlueBalloon(PathTile startTile) {
		bloonWidth = REDWIDTH;
		bloonHeight = REDHEIGHT + sizeIncrement;
		this.speed = REDSPEED + speedIncrement;
		this.x = startTile.getX()*blockSize + GameManager.margin; 
		this.y = startTile.getY()*blockSize + GameManager.margin + (getBloonHeight()/2);
		color = Color.BLUE;
		value = 2;
		this.dir = startTile.getDir();
		this.tileX = startTile.getX(); //these are grid valyes
		this.tileY = startTile.getY();
		
	
	}
	
	

}
