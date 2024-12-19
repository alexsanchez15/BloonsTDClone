package model;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class RoundButton extends Button{
	String startText = "Start";
	static String runningText = ">>";
	static String slowdownText = "<<";
	String startNextRound = "NextRound";
	
	public RoundButton() {
		this.setText(startText);
		this.setLayoutX(GameManager.blockSize*11);
		this.setLayoutY(GameManager.blockSize*6.5);
		this.setMaxWidth(GameManager.blockSize *4);	
		this.setMinWidth(GameManager.blockSize*4);
		
		
		
		this.getStyleClass().add("StyledButton");
	
		
	
		
		this.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				handler();
			}
			
		});
		
	}
	public void update() {
		
		if(LevelManager.current.round == 0) return;
		if(LevelManager.started && (!this.getText().equals(runningText) && !this.getText().equals(slowdownText))) {
			System.out.println(LevelManager.started + " ");
			this.setText(runningText);
		} else if(LevelManager.levelOver && (!this.getText().equals(startNextRound))) {
			this.setText(startNextRound);
		}
	}
	
	public void handler() {
		if(!(LevelManager.started) && (LevelManager.current.round -1 == 0 || LevelManager.levelOver)) {
			LevelManager.levelOver = false;
			LevelManager.started = true;
		}
		else if(LevelManager.started) { 
			if(GameManager.fastFowarded) {
				this.setText(runningText);
				GameManager.fastFowarded = false;
			}
			else {
				GameManager.fastFowarded = true;
				this.setText(slowdownText);
				}
			}
		}
	


}
