package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import balloons.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import projectiles.Projectile;
import tiles.GrassTile;
import tiles.PathTile;
import tiles.ShopTile;
import tiles.Tile;
import towers.DartMonkey;
import towers.Tower;

public class GameManager {
	
	
	public static boolean fastFowarded = false;
	public static int resX = 1280;
	public static int resY = 720;
	
	public static int blockSize = resX  / 16; //or resY / 9, 100x100 pixel gridsize for 1600x900
												//1920/16 = 120 pixels for max size grid (hightest res)
												//so 120/4 --> 30x30 pixels for projectiles
												//120/2 --> 60x60 pixles for towers
												//balloon sizes ill just hold locally lol i dont hate the ovals (for now i suppose)
	
	public static int margin = blockSize/2;
	public static int xPlayable = resX - blockSize;
	public static int yPlayable = resY - blockSize;
	public static int xBlocks = xPlayable/blockSize;
	public static int yBlocks = yPlayable/blockSize;
	public static Font displayFont = Font.font("Arial", FontWeight.BOLD, 20);

	
	private boolean initializedMap = false;
	public static Color rangeColor = Color.GRAY;
	RoundButton roundButton = new RoundButton();
	Image marginImage = new Image("layout/marginscreen.png");
	
	
	
	//for the iterator (and not to take up excess memory)
	Projectile p;
	
	public static ArrayList<Tile> tiles; //only for visual purposes
	ArrayList<ShopTile> shopTiles;
	
	public static ArrayList<PathTile> nodes;
	public static ArrayList<Tower> towers;
	
	public static ArrayList<Balloon> bloons;
	public static ArrayList<Projectile> projectiles;
	public static PathTile startTile;
	
	public static List<Balloon> bloonsToRem;
	public static List<Balloon> bloonsToAdd;
	public static List<Projectile> projToRem;
	public static PlayerInfo playInfo;
	static List<Tower> towerToRem;
	LevelManager lm;
	
	
	int i = 0;
	public void update() {
		
		
		
		for(Projectile p: projectiles) {
			p.update();
		}
		projectiles.removeAll(projToRem);
		projToRem.clear();
		
		for(Tower t: towers) {
			t.update();
		}
		
		bloons.addAll(bloonsToAdd);
		bloonsToAdd.clear();
		
		towers.removeAll(towerToRem);
		for(Balloon b: bloons) {
			b.update();
		}
		bloons.removeAll(bloonsToRem);
		bloonsToRem.clear();
		
		
		
		lm.update();
		roundButton.update();
	}

	
	public void draw(GraphicsContext gc) {
		
		if(initializedMap == false) initializeMap();
		//draw background
		gc.setFill(Color.BLACK);
		gc.fillRect(0,0,resX, resY);
		
		
		
		
		
		//draw visual stuff
		for(Tile t : tiles) { //draw all path tiles including nodes
			t.draw(gc);
		}
		
		
		//THIS IS NEEDED BECAUSE THE LIST CAN CHANGE WHILE ITERATING
		for(Projectile p : projectiles) {
			p.draw(gc);
		}
		
		for(ShopTile t: shopTiles) {
			t.draw(gc);
		}
		//probably not the best way to do this, but i like how clicked items are drawn over all other shop tiles
		for(ShopTile t: shopTiles) {
			t.drawClicked(gc);
		}
		
		for(Tower t : towers) {
			t.draw(gc);
		}
		
		for(Balloon b : bloons) { //draw & update bloons
			b.draw(gc);

		}
		
		playInfo.draw(gc);
		
		
		
		
		gc.drawImage(marginImage, 0, 0, resX, resY);
		
	}
	
	public void initializeMap(){
		rangeColor = new Color(rangeColor.getRed(), rangeColor.getGreen(), rangeColor.getBlue(), 0.5f);
		//initializes the color for range fits better here than other places
		
		bloons = new ArrayList<Balloon>();
		projectiles = new ArrayList<Projectile>();
		tiles = new ArrayList<Tile>();
		nodes = new ArrayList<PathTile>();
		towers = new ArrayList<Tower>();
		ShopItems.createShop();
		shopTiles = new ArrayList<ShopTile>();
		bloonsToRem = new ArrayList<Balloon>();
		projToRem = new ArrayList<Projectile>();
		towerToRem = new ArrayList<Tower>();
		bloonsToAdd = new ArrayList<Balloon>();
		
		//intialize the level manager & playerinfo
		lm = new LevelManager();
		playInfo = new PlayerInfo();
		
		//manual bc needs to be this way 
		
		startTile = new PathTile(0,3, 1);
		
		
		
		
		nodes.add(startTile);
		tiles.add(startTile);
		tiles.add(new PathTile(1,3));
		nodes.add(new PathTile(2,3, 0));
		tiles.add(new PathTile(2,3, 0));//move up
		tiles.add(new PathTile(2,2));
		nodes.add(new PathTile(2,1, 1));
		tiles.add(new PathTile(2,1, 1));//move right
		tiles.add(new PathTile(3, 1));
		nodes.add(new PathTile(4, 1, 2)); //move down
		tiles.add(new PathTile(4, 1, 2));
		tiles.add(new PathTile(4, 2));
		tiles.add(new PathTile(4, 3));
		tiles.add(new PathTile(4, 4));
		nodes.add(new PathTile(4, 5, 3)); //move left	
		tiles.add(new PathTile(4, 5, 3));
		tiles.add(new PathTile(3, 5));
		tiles.add(new PathTile(2, 5));
		nodes.add(new PathTile(1, 5, 2));
		tiles.add(new PathTile(1, 5, 2));//move down
		tiles.add(new PathTile(1, 6));
		nodes.add(new PathTile(1, 7, 1));
		tiles.add(new PathTile(1, 7, 1));//move right
		tiles.add(new PathTile(2, 7));
		tiles.add(new PathTile(3, 7));
		tiles.add(new PathTile(4, 7));
		tiles.add(new PathTile(5, 7));
		tiles.add(new PathTile(6, 7));
		tiles.add(new PathTile(7, 7));
		nodes.add(new PathTile(8, 7, 0));
		tiles.add(new PathTile(8, 7, 0));//move up
		tiles.add(new PathTile(8, 6));
		tiles.add(new PathTile(8, 5));
		nodes.add(new PathTile(8, 4, 3));
		tiles.add(new PathTile(8, 4, 3));//move left
		tiles.add(new PathTile(7, 4));
		nodes.add(new PathTile(6, 4, 0));
		tiles.add(new PathTile(6, 4, 0));//move up
		tiles.add(new PathTile(6, 3));
		nodes.add(new PathTile(6, 2, 1));
		tiles.add(new PathTile(6, 2, 1));//move right
		tiles.add(new PathTile(7, 2));
		nodes.add(new PathTile(8, 2, 0));
		tiles.add(new PathTile(8, 2, 0)); //move up
		tiles.add(new PathTile(8, 1));
		tiles.add(new PathTile(8, 0));
		//path tiles are good, now add shop tiles
		
		
		shopTiles.add(new ShopTile(11, 2, 0));
		shopTiles.add(new ShopTile(12, 2, 0));
		shopTiles.add(new ShopTile(11, 3, 0));
		shopTiles.add(new ShopTile(12, 3, 0));
		
		
		//set all the grass tiles to non path tiles in the area
		for(int i = 0; i<=9; i++) {
			for(int j = 0; j<= 7; j++) {
				GrassTile t = new GrassTile(i, j);
				if(!tiles.contains(t)) { //works becaose overloaded equals method
					tiles.add(t);
					
				}
				
				
			}
		}
		
		
		
		
		
		initializedMap = true;
		System.out.println(" dsdsds");
		
		
		
	}
	
	public void tileAt(int i, int j) {
		
		
	}
	
	public void handleMouseMovement(int x, int y) {
		
		
		
		//HILIGHTSHOPTILES FIXME make a method somewhere else rly bloats this piece
		if(shopTiles != null) {
			for(ShopTile t : shopTiles) {
				
				if(t.isClicked()) { //if its clicked, draw on the mouse
					t.setMouseCoords(x, y);
				}
				
				if((x>= t.getTrueX() && x <= t.getTrueX()+blockSize) && (y>= t.getTrueY() && y <= t.getTrueY()+blockSize)) {
					t.setHovered(true);
					
					
					
				}  else t.setHovered(false);
			}	
		}
		
		
		
	}
	public void handleMouseClick(int x, int y) {
		//3 cases (i think) where this is needed, buying from the shop, clicking on towers, placing towers being bought.
		
		
		//handle mouse click for towers that are placed
		
		if(towers != null) {
			for(Tower t : towers) {
				if((x >= t.getX() - t.getRadius() && x <= t.getX() + t.getRadius()) && (y >= t.getY() - t.getRadius() && y <= t.getY() + t.getRadius())) {

					t.setSelected(true);
					
					
				} else t.setSelected(false);
			}	
		}
		
		//handle clicking of shoptiles
		if(shopTiles != null) {
			
			//PLACE THE TOWER IF ONE IS IN CLICKED STATE
			for(ShopTile t : shopTiles) {
				if(t.isClicked() && t.placeable()) {
					playInfo.addMoney(t.getThisTower().getCost() * -1); //remove cost
					t.setClicked(false);
					
					if(t.getThisTower() instanceof DartMonkey)
						towers.add(new DartMonkey(x,y));
					
				}
				//IF NONE ARE IN CLICKED STATE
				
				else if((x>= t.getTrueX() && x <= t.getTrueX()+blockSize) && (y>= t.getTrueY() && y <= t.getTrueY()+blockSize) && t.getThisTower().getCost() <= playInfo.getMoney()) {
					t.setMouseCoords(x, y);
					t.isClicked(true);
					//pay for the tower
					
					
					
				}  else t.isClicked(false);
				
			}	
			//System.out.println(shopTiles);
		}
	}
	
	public RoundButton getRoundButton() {
		return roundButton;
	}
	
	

}
