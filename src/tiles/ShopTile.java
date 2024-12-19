package tiles;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import model.GameManager;
import model.ShopItems;
import towers.Tower;

public class ShopTile extends Tile {
	int size = GameManager.blockSize;// convenience
	int margin = GameManager.margin;
	int item;
	int lineWidth = size / 10;
	double towerSize = size / 2;
	boolean isHovered = false;
	boolean isClicked = false;
	int mouseX = 0;
	int mouseY = 0;
	int tileX = 0;
	int tileY = 0;
	Color redRangeColor;
	Tower thisTower;

	public ShopTile(int x, int y, int i) {
		this.x = x;
		this.y = y;
		item = i;
		
		redRangeColor = Color.RED;		
		redRangeColor = new Color(redRangeColor.getRed(), redRangeColor.getGreen(), redRangeColor.getBlue(), 0.3f);
		thisTower = ShopItems.towers.get(item);
		
	}

	@Override
	public void draw(GraphicsContext gc) {

		if (ShopItems.towers.get(item) != null) {
			gc.setFill(Color.LIGHTSLATEGRAY);
			
			if (isHovered) { // outline the square & show the name & price (tower toString)
				
				gc.setStroke((Color.WHITE));
				gc.setLineWidth(lineWidth);
				gc.strokeRect(margin + (x * size), margin + (y * size), size - lineWidth / 2, size - lineWidth / 2);
				
				gc.setFill(Color.WHITE);
				gc.setFont(GameManager.displayFont); //dont need to change it every draw 
				gc.fillText(thisTower.toString() , GameManager.blockSize*11, GameManager.blockSize*1.5);

			gc.setFill(Color.LIGHTSLATEGRAY);
				
				
			}
			gc.fillRect(margin + (x * size), margin + (y * size), size - lineWidth / 2, size - lineWidth / 2);
			gc.drawImage(getThisTower().getImage(), margin + (x * size) + towerSize / 2,
					margin + (y * size) + towerSize / 2, towerSize, towerSize);

		}

	}

	public void drawClicked(GraphicsContext gc) {
		if (isClicked()) {
			if(placeable()) gc.setFill(GameManager.rangeColor);
			else gc.setFill(redRangeColor);
			gc.fillOval(mouseX-getThisTower().getRange(), mouseY-getThisTower().getRange(), getThisTower().getRange()*2, getThisTower().getRange()*2);
			gc.drawImage(getThisTower().getImage(), mouseX - towerSize / 2, mouseY - towerSize / 2, towerSize,
					towerSize);

		}
	}

	public void setHovered(boolean s) {
		isHovered = s;
	}

	public void isClicked(boolean s) {
		setClicked(s);

	}

	public String toString() {
		return isClicked() + "" + "whattt";
	}

	public void setMouseCoords(int x, int y) {
		mouseX = x;
		mouseY = y;
	}

	@SuppressWarnings("unlikely-arg-type")
	public boolean placeable() { //maps is 9x8 tiles fills the tileX and tileY vars
		int xLocation = GameManager.margin; //to start
		for(int i = 0 ; i<=15 ; i++) {
			if(mouseX >= xLocation && mouseX <xLocation + size) {
				//is in i block on x axis
				tileX = i;
				break; //breaks out of i loop
			} else xLocation += size;
		}
		
		int yLocation = GameManager.margin; //to start
		for(int i = 0 ; i<=8 ; i++) {
			if(mouseY >= yLocation && mouseY <yLocation + size) {
				//is in i block on x axis
				tileY=i;
				break; //breaks out of i loop
			} else yLocation += size;
		}
		
		int[] a = new int[] {tileX, tileY};
		for(Tile p: GameManager.tiles) {
			if(p.equals(new int[] {tileX, tileY}) && p instanceof GrassTile) {
				
				//System.out.println(p);
				
				return true;
				
				}
			}
		return false;
		}

	public boolean isClicked() {
		return isClicked;
	}

	public void setClicked(boolean isClicked) {
		this.isClicked = isClicked;
	}

	public Tower getThisTower() {
		return thisTower;
	}
}
