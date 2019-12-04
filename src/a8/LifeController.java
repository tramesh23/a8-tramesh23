package a8;

import java.awt.event.ActionEvent;

public class LifeController {

	LifeModel model;
	LifeView view;

	public LifeController(LifeModel model, LifeView view) {
		this.model = model;
		this.view = view;

		view.addListener(this);
		model.addObserver(this);
	}
	
	/*
	 * Depending on what button is clicked then do the thing specified in the button
	 */
	public void getEvent(ActionEvent e) {
		if (e.getActionCommand().equals("restart")) {
			model.resetGame();
		} 
		else if (e.getActionCommand().equals("advance")) {
			model.advance();
		} 
		else if (e.getActionCommand().equals("random")) {
			model.randomFill();
		} 
		else if (e.getActionCommand().contentEquals("settings")) {
			model.updateSettings(view.getLength(), view.getlowBirth(), view.getHighBirth(),
					 view.getLowSurvival(), view.getHighSurvival(), view.getTorus());
		}
	}
	
	public void updateBoard(JSpotBoard board) {
		view.setBoard(board);
	}
	
	public void updateLowBirth(JSpotBoard board) {
		view.setBoard(board);
	}
	
	public void updateHighBirth(JSpotBoard board) {
		view.setBoard(board);
	}
	
	public void updateLowSurvial(JSpotBoard board) {
		view.setBoard(board);
	}
	
	public void updateHighSurvival(JSpotBoard board) {
		view.setBoard(board);
	}
}
