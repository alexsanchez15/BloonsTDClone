package model;

import java.util.ArrayList;

import javafx.scene.image.Image;
import towers.DartMonkey;
import towers.Tower;

public class ShopItems {
	public static ArrayList<Tower> towers = new ArrayList<Tower>();
	
	
	public static void createShop() {
		towers.add(new DartMonkey());
	}
	
}
