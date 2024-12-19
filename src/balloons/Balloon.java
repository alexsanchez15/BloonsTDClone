package balloons;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import model.GameManager;
import projectiles.Projectile;
import tiles.PathTile;

public abstract class Balloon {
	final int blockSize = GameManager.blockSize; //convenience
	int value; //1 = red balloon, 2 = blue balloon, 3 = yellow balloon
	double speed; //pixels/second (using block for refrence)
	double x;
	double y;
	int tileX;
	int tileY;
	int dir = -1;
	int centerX;
	int centerY;
	double index =0.0;
	boolean moving = false;
	int newDir;
	int bloonWidth;
	int bloonHeight;
	double distTraveled=0;
	Projectile lastHit = null;
	int sizeIncrement = blockSize/20;
	double speedIncrement = (double)blockSize/(blockSize*2);
	public final double REDSPEED = ((double)blockSize/((double)blockSize));
	public final int REDWIDTH = (int) (blockSize /2.5);
	public final int REDHEIGHT = blockSize/2;

	Color color;
	
	public double getX() {
		return x;
	}
	
	public int getCenterX() {
		return centerX;
	}
	public int getCenterY() {
		return centerY;
	}
	public double getY() {
		return y;
	}
	
	public int getValue() {
		return value;
	}
	
	
	public void tileAt() { //maps is 9x8 tiles fills the tileX and tileY vars
		int xLocation = GameManager.margin; //to start
		boolean xFound = false;
		boolean yFound = false;
		for(int i = 0 ; i<=8 ; i++) {
			if(centerX >= xLocation && centerX <xLocation + blockSize) {
				//is in i block on x axis
				tileX = i;
				xFound = true;
				break; //breaks out of i loop
			} else xLocation += blockSize;
		}
		
		int yLocation = GameManager.margin; //to start
		for(int i = 0 ; i<=8 ; i++) {
			if(centerY >= yLocation && centerY <yLocation + blockSize) {
				//is in i block on x axis
				tileY=i;
				yFound = true;
				break; //breaks out of i loop
			} else yLocation += blockSize;
		}
		if(distTraveled > 200 &&( !xFound || !yFound)) {
			GameManager.bloonsToRem.add(this);
			GameManager.playInfo.addHealth(value * -1);
		}
		
		
	}
	
	
	public void update() {
		moveInDirection();
		reactToNode();
		
	}
	
	public void draw(GraphicsContext gc) {
		gc.setFill(color);
		gc.fillOval(x, y, getBloonWidth(), getBloonHeight());
		//gc.fillText("" + distTraveled, x, y);
		
	}
	
	@SuppressWarnings("unlikely-arg-type")
	public void reactToNode() {
		centerX = (int)(x + getBloonWidth()/2);
		centerY = (int)(y + getBloonHeight()/2);
		this.tileAt();
		
		
		for(PathTile p: GameManager.nodes) {
			if(p.equals(new int[] {tileX, tileY})) {
				if(this.dir != p.getDir()) { 
					
					this.newDir = p.getDir();
					moving = true;
				
				} 
			}
		}
		
		
	}
	
	
	public void moveInDirection() {
		checkIfOffMap();
		if(moving) {
			
			index+=speed;
			if(index >= blockSize/2 && (dir == 0 || dir == 1)) {
				dir = newDir;
				index =0;
				moving = false;
			}
			if(index >= blockSize/2 && (dir ==2 || dir ==3)) {
				dir = newDir;
				index = 0;
				moving = false;
			}
		}
		distTraveled += speed;
		switch(dir) {
		case(0):
			this.y -= speed;
			break;
		case(1):
			this.x += speed;
			break;
		case(2):
			this.y += speed;
			break;
		case(3):
			this.x -= speed;
			break;
		}
	}
	
	public void takeDamage(int damage, Projectile proj) {
		
		if(getLastHit() != proj || getLastHit() == null) {
			lastHit = proj;
			value -= damage;
			GameManager.playInfo.addMoney(damage);
			GameManager.bloonsToRem.add(this);
			if(value <= 0) {
				 return;//no more bloons to spawn after 0 hp
			}
			else {
				
				makeNewBalloonAfterDamage(proj);
			}
			
		}
		
		
			
	}
	
	private void makeNewBalloonAfterDamage(Projectile proj){
		
		//switch based on value
		switch(value) {
		case(1):
			GameManager.bloonsToAdd.add(new RedBalloon(this));
			break;
		}
		GameManager.bloonsToRem.add(this);
		
	}

	public int getBloonWidth() {
		return bloonWidth;
	}

	public int getBloonHeight() {
		return bloonHeight;
	}

	public double getDistTraveled() {
		return distTraveled;
	}

	public Projectile getLastHit() {
		return lastHit;
	}
	
	public void checkIfOffMap() {
		if (distTraveled > 200 && (tileX < 0 || tileX > 9 || tileY <0 || tileY > 8)) {
			System.out.println("bloon off the map");
			
		}
	}
}
