package tiles;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import model.GameManager;

public class GrassTile extends Tile {
	int size = GameManager.blockSize;//convenience
	
	
	public GrassTile(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	
	//maybe temp?but draw metgod
	public void draw(GraphicsContext gc) {
		gc.setFill(Color.LIMEGREEN);
		gc.fillRect(x * size + GameManager.margin, y*size + GameManager.margin, size, size);
		
	}
	

}