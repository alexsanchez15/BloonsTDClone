package model;

import balloons.*;

public class LevelManager {
	protected static boolean started = false;
	protected static boolean levelOver = false;
	protected static boolean startNewLevel = false; //for starting new levels (?)
	
	
	static Round current = new Round1();
	

	public void start() {
		
	}
	
	public void update() {
		
		if(started) {
			
			if(!levelOver) {
				current.runRound();
				return;
			}else {
				current.balloons = 0;
			}
			
			
		}
			
	}
	
}


abstract class Round{ 
	//each adds some amound of balloons to the game, when they are done spawning, check for the
	//bloons arraylist in the GameManager to be empty, if it is, the round is over
	int balloons = 0;
	int count = 0;
	int round = 0;
	
	public abstract void runRound();
	
}

class Round1 extends Round{
	public Round1() { round = 1;}
	
	@Override
	public void runRound() {
		//round 1: 40 red balloons
		
		if(balloons < 5) {
			if(count % (Bloons.TARGET_FPS/1.5) == 0) { //1.5 per second, up to 40;
				System.out.println(balloons);
				GameManager.bloonsToAdd.add(new RedBalloon(GameManager.startTile));
				balloons++;
			}
			count++;
		}
		else if(GameManager.bloons.isEmpty()) {
			System.out.println("round is over");
			LevelManager.levelOver = true;
			LevelManager.started = false;
			LevelManager.current = new Round2();
		}	
	}


	
}


class Round2 extends Round{
	public Round2() { round = 2;}
	
	@Override
	public void runRound() {
		//round 2: spawns 20 blue balloons and 20 red balloons. total balloons = 40
		if(balloons < 40) {
			if(count % (Bloons.TARGET_FPS) == 0) { //2 per second, up to 40;
				if(balloons % 2 ==0) {
					
					GameManager.bloonsToAdd.add(new RedBalloon(GameManager.startTile));
				}
				if(balloons % 2 ==1) {
					GameManager.bloonsToAdd.add(new BlueBalloon(GameManager.startTile));
					
				}	
				balloons++;
			}	
			count++;
		}
		else if(GameManager.bloons.isEmpty()) {
			LevelManager.current = new Round3();
			LevelManager.levelOver = true;
			LevelManager.started = false;
		}	
	}
}
class Round3 extends Round{
	public Round3() { round = 3;}
	
	@Override
	public void runRound() {
		//round 2: spawns 20 blue balloons and 20 red balloons. total balloons = 40
		if(balloons < 50) {
			if(count % (Bloons.TARGET_FPS) == 0) { //50 blue balloons
					GameManager.bloonsToAdd.add(new BlueBalloon(GameManager.startTile));
					
					
				balloons++;
			}	
			count++;
		}
		else if(GameManager.bloons.isEmpty()) {
			LevelManager.levelOver = true;
			LevelManager.started = false;
		}	
	}
}
