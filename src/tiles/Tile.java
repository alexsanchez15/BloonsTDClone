package tiles;

import javafx.scene.canvas.GraphicsContext;
import model.GameManager;

public abstract class Tile{
	protected int x; //coorindinates
	protected int y;
	
	
	public abstract void draw(GraphicsContext gc);
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof Tile) {
			Tile t = (Tile) o;
			
			if(t.getX() == this.getX() && t.getY() == this.getY()) {
				return true;
			}
		}
		
		if(o instanceof int[]) {
			
			int[] t = (int[]) o;
			
			if(t[0] == this.getX() && t[1] == this.getY()) {
				return true;
			}
		}
		return false;
		
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	
	public int getTrueX() {
		return x * GameManager.blockSize + GameManager.margin;
	}
	public int getTrueY() {
		return y * GameManager.blockSize + GameManager.margin;
	}
	
	public String toString() {
		return "X:"+x + " Y:" +y;
	}
	
	
}
