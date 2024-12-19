package towers;

import balloons.Balloon;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Rotate;
import model.GameManager;
import projectiles.Dart;

public abstract class Tower {
	String name;
	int radius = GameManager.blockSize/4;
	int size = getRadius() *2; //2x radius, all towers are squares
	int x; //exact pixel vars not ideal for chaning res and loading from saved state, wont be addressing these things in the game though lol
	int y;
	int range;
	int fireRate; //frames between each fire
	int fireRateCounter=0;
	boolean selected = false;
	int lastAngle;
	int cost = -1;
	Balloon focus;
	Image thisImage;
	
	
	public Tower() {
		
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void update() {
		fireProjectile();
		
	}
	
	public void draw(GraphicsContext gc) {
		faceTarget(gc);
		
		if(selected) {
			gc.setFill(GameManager.rangeColor);
			gc.fillOval(x-getRange(), y-getRange(), getRange()*2, getRange()*2);

		}
		
	}
	
	
	public abstract void makeTower(GraphicsContext gc);
	
	public abstract void fire();
	
	public void fireProjectile() {
		
		fireRateCounter++;
		if(fireRateCounter >= this.fireRate && focus != null ) { //&& BALLOON IS IN RANGE FIXME
			int angle = getAngleToBalloon();
			GameManager.projectiles.add(new Dart(x, y, angle));
			fireRateCounter = 0;
			//System.out.println(GameManager.projectiles.size());
		}
	}
	
	public void faceTarget(GraphicsContext gc) {
		gc.save();
		int angle = this.getAngleToBalloon() +90;

        // Apply rotation
        Rotate r = new Rotate(angle, x , y );
        gc.transform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());

        // Draw the shapes
        makeTower(gc);

        // Restore the original transformation state
        gc.restore();
	}
	
	public int getAngleToBalloon() {
		focus = getFocusedBalloon();
		if (focus == null) return lastAngle;
		int xdist = this.x - (int)focus.getCenterX() ;
		int ydist = this.y - (int)focus.getCenterY();
		
		double angleRadians = Math.atan2(ydist, xdist);
		int angleDegrees = (int)Math.toDegrees(angleRadians);
		
		
		lastAngle = angleDegrees;
		return angleDegrees;
		
	}
	
	public Balloon getFocusedBalloon() {
		//disgusting nesting ewwww
		focus = null;
		for(Balloon b: GameManager.bloons) { 
			if(b!= null) {
				if(Math.hypot(Math.abs((this.x) - b.getCenterX()) - b.getBloonWidth()/2, Math.abs((this.y) - b.getCenterY()) - b.getBloonHeight()/2) <= (this.getRange())) {
					if(focus == null) {
						focus = b;
					}
					if(b.getDistTraveled()> focus.getDistTraveled()) {
						focus= b;
					}
					
				}
			}
			
			
		}
		return focus; //null if none exists
	}
	public void setXY(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	public Image getImage() {
		return thisImage;
	}

	public int getRange() {
		return range;
	}

	public int getRadius() {
		return radius;
	}

	public int getCost() {
		return cost;
	}
	
	@Override
	public String toString() {
		return name + "\nCost: " + cost;
	}
}
