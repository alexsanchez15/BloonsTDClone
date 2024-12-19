package projectiles;

import balloons.Balloon;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.transform.Rotate;
import model.GameManager;

public abstract class Projectile {
	double x;
	double y;
	double angle;
	double xspeed;
	double yspeed;
	int durability;
	int range; // frames until disappears
	int size = GameManager.blockSize / 4;
	int power;
	Image thisImage;
	// x and y are center points already
	int rangeCounter = 0;
	boolean needsRemove;
	Balloon lastHit = null;

	public Rotate r;

	public void update() {
		move();
		handleCollision();

	}

	public void draw(GraphicsContext gc) {
		faceTarget(gc);

	}

	public abstract void makeProjectile(GraphicsContext gc);

	public void faceTarget(GraphicsContext gc) {
		gc.save();

		// Apply rotation
		// Rotate r = new Rotate(angle, x , y ); //does this every time which is prob
		// not that efficient
		r.setPivotX(this.x); // update the pivit
		r.setPivotY(this.y);
		r.setAngle(angle + 180);
		gc.transform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());

		// Draw the shapes
		makeProjectile(gc);

		// Restore the original transformation state
		gc.restore();
	}

	public void move() {
		
		if(rangeCounter >=range ) {
			GameManager.projToRem.add(this);
			return;
		}

		x += xspeed;
		y += yspeed;

		rangeCounter++;
	}

	public int getRangeCounter() {
		return rangeCounter;
	}

	public int getRange() {
		return range;
	}

	public void handleCollision() {
		for (Balloon b : GameManager.bloons) {
			if ((x >= b.getX() && x <= b.getX() + b.getBloonWidth()) && y >= b.getY() && y <= b.getY() + b.getBloonHeight()) {
				//collision occurs
				if(b.getLastHit() != this) {//check if the current projectile is not the one the balloon holds as lastHit (genius)(thankyou)
					
					durability -= 1;
					if(durability <= 0) {
						GameManager.projToRem.add(this);
					}
				
					b.takeDamage(power, this);
				}
				
			}
		}
	}

}