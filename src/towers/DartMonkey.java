package towers;

import javafx.geometry.Point2D;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import model.GameManager;

public class DartMonkey extends Tower {

	// int radius;
	// int size; //2x radius, all towers are squares
	// int x; //exact pixel vars not ideal for chaning res and loading from saved
	// state, wont be addressing these things in the game though lol
	// int y;
	Image dartMonkeyImage = new Image(GameManager.class.getResource("/sprites/towers/testTower.png").toExternalForm());

	public DartMonkey(int x, int y) {
		size = getRadius() * 2;
		this.x = x;
		this.y = y;
		this.range = GameManager.blockSize;
		this.fireRate = 80;
		this.thisImage = dartMonkeyImage;
		this.fireRateCounter = fireRate;
		name = "Dart Monkey";

	}

	public DartMonkey() {
		this.range = GameManager.blockSize;
		this.thisImage = dartMonkeyImage;
		this.cost = 200;
		name = "Dart Monkey";
	}

	@Override
	public void makeTower(GraphicsContext gc) {
		// gc.setFill(Color.KHAKI);
		// gc.fillOval(x-radius, y-radius, size, size);
		// gc.setFill(Color.BLACK);
		// gc.fillRect(x-5, y, 10, 100);
		gc.drawImage(thisImage, x - getRadius(), y - getRadius(), size, size);
		// gc.fillOval(x+radius - radius/8, y + radius/5, radius/4, radius/4);

	}

	@Override
	public void fire() {
		// TODO Auto-generated method stub

	}

}
