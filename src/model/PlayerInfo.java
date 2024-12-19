package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

public class PlayerInfo {
	private int health;
	private int money;
	public PlayerInfo() {
		health = 40;
		money = 400;
	}
	
	public int getHealth() {
		return health;
	}
	
	public int getMoney() {
		return money;
	}
	public void addMoney(int addition) {
		money += addition;
	}
	
	public void setHealth(int newHealth) {
		health = newHealth;
	}
	public void addHealth(int x) {
		health += x;
	}
	
	public void setMoney(int newMoney) {
		money = newMoney;
	}
	
	public void draw(GraphicsContext gc) {
		gc.setFill(Color.BLACK);
		if(!gc.getFont().equals(GameManager.displayFont))
		gc.setFont(GameManager.displayFont); //dont need to change it every draw 
		gc.fillText("money : " + money + "\nhealth : " + health , GameManager.blockSize, GameManager.blockSize);
		gc.setFill(Color.WHITE);
		gc.fillText("round : " + LevelManager.current.round , GameManager.blockSize * 11, GameManager.blockSize);
		
	}

}
