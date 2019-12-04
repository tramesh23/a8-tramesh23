package a8;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class LifeModel {

	private List<LifeController> observers;
	private JSpotBoard _board;
	private JSpotBoard next_board;
	private int size;
	private int low_birth;
	private int high_birth;
	private int low_death;
	private int high_death;
	private boolean torus;
	
	public LifeModel() {
		size = 10;
		low_birth = 3;
		high_birth = 3;
		low_death = 2;
		high_death = 3;
		torus = false;
		
		observers = new ArrayList<LifeController>();
		next_board = new JSpotBoard(size, size);
		_board = new JSpotBoard(size,size);
		
		for (Spot s : _board) {
			s.setBackground(Color.GRAY);
			s.setSpotColor(Color.BLACK);
		}
		for (Spot s : next_board) {
			s.setBackground(Color.GRAY);
			s.setSpotColor(Color.BLACK);
		}
	}
	
	public void addObserver(LifeController o) {
		observers.add(o);
	}
	
	public void updateObservers() {
		for(LifeController o : observers)
			o.updateBoard(_board);
	}
	
	public void resetGame() {
		for (Spot s : _board) {
			s.clearSpot();
		}
		for (Spot s : next_board) {
			s.clearSpot();
		}
		updateObservers();
	}
	
	/*
	 * randomlyFill
	 * 
	 * Fills random spots
	 */
	
	public void randomFill() {
		resetGame();
		for(Spot s: _board)
			if(Math.random() < .5)
				s.toggleSpot();
		updateObservers();
	}
	
	/*
	 * advance
	 * 
	 * Does next steps
	 */
	
	public void advance() {
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
		updateObservers();
	}
	
	/*
	 * Results from pressing the settings button and checks out all of the fields to dynamically change the game
	 */
	public void updateSettings(int size, int low_birth, int high_birth, int low_death, int high_death, int t) {
		this.size = size;
		this.low_birth = low_birth;
		this.high_birth = high_birth;
		this.low_death = low_death;
		this.high_death = high_death;
		if(t == 0)
			this.torus = false;
		else {this.torus = true;}
		
		next_board = new JSpotBoard(size, size);
		_board = new JSpotBoard(size,size);
		
		for (Spot s : _board) {
			s.setBackground(Color.GRAY);
			s.setSpotColor(Color.BLACK);
		}
		for (Spot s : next_board) {
			s.setBackground(Color.GRAY);
			s.setSpotColor(Color.BLACK);
		}
		updateObservers();
	}
}
