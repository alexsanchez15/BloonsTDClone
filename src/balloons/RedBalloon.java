package balloons;

import javafx.scene.paint.Color;
import model.GameManager;
import projectiles.Projectile;
import tiles.PathTile;

public class RedBalloon extends Balloon {
	
	
	public RedBalloon(PathTile startTile) {
		bloonWidth = REDWIDTH;
		bloonHeight = REDHEIGHT;
		this.speed = REDSPEED;
		this.x = startTile.getX() * blockSize + GameManager.margin;
		this.y = startTile.getY() * blockSize + GameManager.margin + (getBloonHeight() / 2);
		color = Color.RED;
		value = 1;
		this.dir = startTile.getDir();
		this.tileX = startTile.getX(); // these are grid valyes
		this.tileY = startTile.getY();

	}

	public RedBalloon(Balloon b) {
		value = 1; // 1 = red balloon, 2 = blue balloon, 3 = yellow balloon
		speed = REDSPEED;// pixels/second (using block for refrence)
		x = b.x;
		y = b.y;
		tileX = b.tileX;
		tileY = b.tileY;
		dir = b.dir;
		centerX = b.centerX;
		centerY = b.centerY;
		bloonWidth = REDWIDTH;
		bloonHeight = REDHEIGHT;
		index = b.index;
		moving = b.moving;
		newDir = b.newDir;
		distTraveled = b.distTraveled;
		lastHit = b.getLastHit();

		color = Color.RED;

	}

}
