package a8;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameofLifeWidget extends JPanel implements ActionListener, SpotListener {

	private JSpotBoard _board; /* SpotBoard playing area. */
	private JSpotBoard next_board;
	private JLabel _message; /* Label for messages. */
	private int size;
	private int low_birth;
	private int high_birth;
	private int low_death;
	private int high_death;
	private boolean torus;

	public GameofLifeWidget(int size, int low_birth, int high_birth, int low_death, int high_death, boolean torus) {
		
		this.size = size;
		this.low_birth = low_birth;
		this.high_birth = high_birth;
		this.low_death = low_death;
		this.high_death = high_death;
		this.torus = torus;

		/* Create SpotBoard and message label. */

		_board = new JSpotBoard(size, size);
		next_board = new JSpotBoard(size, size);
		_message = new JLabel();

		/* Set layout and place SpotBoard at center. */

		setLayout(new BorderLayout());
		add(_board, BorderLayout.CENTER);

		/* Create subpanel for message area and buttons. */

		JPanel button_message_panel = new JPanel();
		button_message_panel.setLayout(new BorderLayout());

		/* Reset button. Add ourselves as the action listener. */

		JButton reset_button = new JButton("Restart");
		reset_button.addActionListener(this);
		reset_button.setActionCommand("restart");
		button_message_panel.add(reset_button, BorderLayout.EAST);
		
		/* Randomly fill button. Add ourselves as the action listener. */
		
		JButton random_button = new JButton("Randomly Fill");
		random_button.addActionListener(this);
		random_button.setActionCommand("random");
		button_message_panel.add(random_button, BorderLayout.CENTER);
		
		/* Advance button. Add ourselves as the action listener. */
		
		JButton advance_button = new JButton("Advance");
		advance_button.addActionListener(this);
		advance_button.setActionCommand("advance");
		button_message_panel.add(advance_button, BorderLayout.WEST);

		/* Add subpanel in south area of layout. */

		add(button_message_panel, BorderLayout.SOUTH);
		
		/*
		 * Add ourselves as a spot listener for all of the spots on the spot board.
		 */
		_board.addSpotListener(this);
		for (Spot s : _board) {
			s.setBackground(Color.BLACK);
			s.setSpotColor(Color.WHITE);
		}
		for (Spot s : next_board) {
			s.setBackground(Color.BLACK);
		}
		

		/* Reset game. */
		resetGame();
	}

	/*
	 * resetGame
	 * 
	 * Resets the game by clearing all the spots on the board, picking a new secret
	 * spot, resetting game status fields, and displaying start message.
	 * 
	 */

	private void resetGame() {
		/*
		 * Clear all spots on board. Uses the fact that SpotBoard implements
		 * Iterable<Spot> to do this in a for-each loop.
		 */

		for (Spot s : _board) {
			s.clearSpot();
		}
	}
	
	/*
	 * randomlyFill
	 * 
	 * Fills random spots
	 */
	
	private void randomlyFill() {
		for (Spot s: _board) {
			if(Math.random()<.5)
				spotClicked(s);
		}
	}
	
	/*
	 * advance
	 * 
	 * Does next steps
	 */
	
	private void advance() {
		for(Spot s: _board) {
				int neighbors = 0;
				if(s.getSpotX() > 0 && s.getSpotY() > 0 && !_board.getSpotAt(s.getSpotX()-1, s.getSpotY()-1).isEmpty()) {
					neighbors++;
				}
				if(s.getSpotX() > 0 && (s.getSpotY() < size - 1) && !_board.getSpotAt(s.getSpotX()-1, s.getSpotY()+1).isEmpty()) {
					neighbors++;
				}
				if((s.getSpotX() < size - 1) && s.getSpotY() > 0 && !_board.getSpotAt(s.getSpotX()+1, s.getSpotY()-1).isEmpty()) {
					neighbors++;
				}
				if((s.getSpotX() < size - 1) && (s.getSpotY() < size - 1) && !_board.getSpotAt(s.getSpotX()+1, s.getSpotY()+1).isEmpty()) {
					neighbors++;
				}
				if(s.getSpotX() > 0 && !_board.getSpotAt(s.getSpotX()-1, s.getSpotY()).isEmpty()) {
					neighbors++;
				}
				if(s.getSpotY() > 0 && !_board.getSpotAt(s.getSpotX(), s.getSpotY()-1).isEmpty()) {
					neighbors++;
				}
				if((s.getSpotX() < size - 1) && !_board.getSpotAt(s.getSpotX()+1, s.getSpotY()).isEmpty()) {
					neighbors++;
				}
				if((s.getSpotY() < size -1) && !_board.getSpotAt(s.getSpotX(), s.getSpotY()+1).isEmpty()) {
					neighbors++;
				}
				if(torus) {
					if(s.getSpotX() == 0 && s.getSpotY() == 0 && !_board.getSpotAt(size-1, size-1).isEmpty())
						neighbors++;
					if(s.getSpotX() == 0 && s.getSpotY() == 0 && !_board.getSpotAt(0, size-1).isEmpty())
						neighbors++;
					if(s.getSpotX() == 0 && s.getSpotY() == 0 && !_board.getSpotAt(size-1, 0).isEmpty())
						neighbors++;
					if(s.getSpotX() == 0 && s.getSpotY() == 0 && !_board.getSpotAt(size-1, 1).isEmpty())
						neighbors++;
					if(s.getSpotX() == 0 && s.getSpotY() == 0 && !_board.getSpotAt(1, size - 1).isEmpty())
						neighbors++;
					if(s.getSpotX() == 0 && (s.getSpotY() == size - 1) && !_board.getSpotAt(size-1, 0).isEmpty())
						neighbors++;
					if(s.getSpotX() == 0 && (s.getSpotY() == size - 1) && !_board.getSpotAt(size-1, size-1).isEmpty())
						neighbors++;
					if(s.getSpotX() == 0 && (s.getSpotY() == size - 1) && !_board.getSpotAt(0,0).isEmpty())
						neighbors++;
					if(s.getSpotX() == 0 && (s.getSpotY() == size - 1) && !_board.getSpotAt(1,0).isEmpty())
						neighbors++;
					if(s.getSpotX() == 0 && (s.getSpotY() == size - 1) && !_board.getSpotAt(size - 1, size - 2).isEmpty())
						neighbors++;
					if((s.getSpotX() == size - 1) && s.getSpotY() == 0 && !_board.getSpotAt(0, size-1).isEmpty())
						neighbors++;
					if((s.getSpotX() == size - 1) && s.getSpotY() == 0 && !_board.getSpotAt(size - 1, size - 1).isEmpty())
						neighbors++;
					if((s.getSpotX() == size - 1) && s.getSpotY() == 0 && !_board.getSpotAt(0,0).isEmpty())
						neighbors++;
					if((s.getSpotX() == size - 1) && s.getSpotY() == 0 && !_board.getSpotAt(0,1).isEmpty())
						neighbors++;
					if((s.getSpotX() == size - 1) && s.getSpotY() == 0 && !_board.getSpotAt(size - 2, size - 1).isEmpty())
						neighbors++;
					if((s.getSpotX() == size - 1) && (s.getSpotY() == size - 1) && !_board.getSpotAt(0, 0).isEmpty())
						neighbors++;
					if((s.getSpotX() == size - 1) && (s.getSpotY() == size - 1) && !_board.getSpotAt(0, size - 1).isEmpty())
						neighbors++;
					if((s.getSpotX() == size - 1) && (s.getSpotY() == size - 1) && !_board.getSpotAt(size - 1, 0).isEmpty())
						neighbors++;
					if((s.getSpotX() == size - 1) && (s.getSpotY() == size - 1) && !_board.getSpotAt(size-2, 0).isEmpty())
						neighbors++;
					if((s.getSpotX() == size - 1) && (s.getSpotY() == size - 1) && !_board.getSpotAt(0, size -2).isEmpty())
						neighbors++;
					if(s.getSpotX() == 0 && s.getSpotY()!=0 && (s.getSpotY()!= size - 1) && !_board.getSpotAt(size-1, s.getSpotY()).isEmpty())
						neighbors++;
					if(s.getSpotX() == 0 && s.getSpotY()!=0 && (s.getSpotY()!= size - 1) && !_board.getSpotAt(size-1, s.getSpotY()-1).isEmpty())
						neighbors++;
					if(s.getSpotX() == 0 && s.getSpotY()!=0 && (s.getSpotY()!= size - 1) && !_board.getSpotAt(size-1, s.getSpotY()+1).isEmpty())
						neighbors++;
					if(s.getSpotY() == 0 && s.getSpotX()!= 0 && (s.getSpotX()!= size - 1) && !_board.getSpotAt(s.getSpotX(), size-1).isEmpty())
						neighbors++;
					if(s.getSpotY() == 0 && s.getSpotX()!= 0 && (s.getSpotX()!= size - 1) && !_board.getSpotAt(s.getSpotX()-1, size-1).isEmpty())
						neighbors++;
					if(s.getSpotY() == 0 && s.getSpotX()!= 0 && (s.getSpotX()!= size - 1) && !_board.getSpotAt(s.getSpotX()+1, size-1).isEmpty())
						neighbors++;
					if((s.getSpotX() == size - 1) && s.getSpotY()!=0 && (s.getSpotY()!= size - 1) && !_board.getSpotAt(0, s.getSpotY()).isEmpty())
						neighbors++;
					if((s.getSpotX() == size - 1) && s.getSpotY()!=0 && (s.getSpotY()!= size - 1) && !_board.getSpotAt(0, s.getSpotY()-1).isEmpty())
						neighbors++;
					if((s.getSpotX() == size - 1) && s.getSpotY()!=0 && (s.getSpotY()!= size - 1) && !_board.getSpotAt(0, s.getSpotY()+1).isEmpty())
						neighbors++;
					if((s.getSpotY() == size - 1) && s.getSpotX()!= 0 && (s.getSpotX()!= size - 1) && !_board.getSpotAt(s.getSpotX(), 0).isEmpty())
						neighbors++;
					if((s.getSpotY() == size - 1) && s.getSpotX()!= 0 && (s.getSpotX()!= size - 1) && !_board.getSpotAt(s.getSpotX()-1, 0).isEmpty())
						neighbors++;
					if((s.getSpotY() == size - 1) && s.getSpotX()!= 0 && (s.getSpotX()!= size - 1) && !_board.getSpotAt(s.getSpotX()+1, 0).isEmpty())
						neighbors++;
				}
				if(s.isEmpty()) {
					if(neighbors >= low_birth && neighbors <= high_birth)
						next_board.getSpotAt(s.getSpotX(), s.getSpotY()).setSpot();
				}
				else {
					if(neighbors >= low_death && neighbors <= high_death)
						next_board.getSpotAt(s.getSpotX(), s.getSpotY()).setSpot();
					else
						next_board.getSpotAt(s.getSpotX(), s.getSpotY()).clearSpot();
				}

		}
		for(Spot s : _board) {
			if(next_board.getSpotAt(s.getSpotX(), s.getSpotY()).isEmpty())
				s.clearSpot();
			else
				s.setSpot();
			next_board.getSpotAt(s.getSpotX(), s.getSpotY()).clearSpot();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		/* Handles game buttons.*/
		if(e.getActionCommand().equals("restart"))
			resetGame();
		else if(e.getActionCommand().equals("random"))
			randomlyFill();
		else {advance();}
	}

	/*
	 * Implementation of SpotListener below. Implements game logic as responses to
	 * enter/exit/click on spots.
	 */

	@Override
	public void spotClicked(Spot s) {

		s.toggleSpot();
	}

	@Override
	public void spotEntered(Spot s) {

		s.highlightSpot();
	}

	@Override
	public void spotExited(Spot s) {
		/* Unhighlight spot. */

		s.unhighlightSpot();
	}
}
