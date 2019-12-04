package a8;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameofLifeGame {
	public static void main(String[] args) {
		
		/* Model-view relationship is created and the controller is their mediator */
		
		LifeModel model = new LifeModel();
		LifeView view = new LifeView();
		LifeController controller = new LifeController(model, view);
		
		/* Create top level window. */
		
		JFrame main_frame = new JFrame();
		main_frame.setTitle("Game of Life");
		main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		main_frame.setContentPane(view);

		/* Pack main frame and make visible. */
		
		main_frame.pack();
		main_frame.setVisible(true);		
	}	
}
