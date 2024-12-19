package tiles;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import model.GameManager;

public class PathTile extends Tile {
	int size = GameManager.blockSize;//convenience
	int margin = GameManager.margin;
	boolean isNode = false;
	int dir =-1; //0 is up	1 is right	2 is down	3 is left
	
	
	public PathTile(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public PathTile(int x, int y, int dir) {
		this.x = x;
		this.y = y;
		this.isNode = true;
		this.dir = dir;
		
	}
	
	public boolean isNode() {
		return isNode;
	}
	
	public int getDir() {
		return dir;
	}
	
	
	//maybe temp?but draw metgod
	public void draw(GraphicsContext gc) {
		gc.setFill(Color.BROWN);
		gc.fillRect(x * size + margin, y*size + margin, size, size);
	}
	

}
