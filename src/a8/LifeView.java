package a8;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LifeView extends JPanel implements ActionListener, SpotListener {

	private JSpotBoard _board;
	private JTextField size_field;
	private JTextField _low_birth;
	private JTextField _high_birth;
	private JTextField _low_death;
	private JTextField _high_death;
	private JTextField _torus;
	private JTextField _initial;
	private List<LifeController> listeners;

	public LifeView() {

		/* Create SpotBoard and message label. */
		_board = new JSpotBoard(10, 10);
		listeners = new ArrayList<LifeController>();

		for (Spot s : _board) {
			s.setBackground(Color.GRAY);
			s.setSpotColor(Color.BLACK);
		}

		/* Set layout and place SpotBoard at center. */

		setLayout(new BorderLayout());
		_initial = new JTextField("Put in your conditions and press settings to start!");
		add(_initial, BorderLayout.CENTER);

		/* Create subpanel for message area and reset button. */

		JPanel button_panel = new JPanel();
		button_panel.setLayout(new BorderLayout());

		/* Buttons! Add ourselves as the action listener. */

		JButton reset_button = new JButton("Restart");
		reset_button.addActionListener(this);
		reset_button.setActionCommand("restart");
		button_panel.add(reset_button, BorderLayout.EAST);

		JButton advance_button = new JButton("Advance");
		advance_button.addActionListener(this);
		advance_button.setActionCommand("advance");
		button_panel.add(advance_button, BorderLayout.CENTER);

		JButton random_button = new JButton("Randomly Fill");
		random_button.addActionListener(this);
		random_button.setActionCommand("random");
		button_panel.add(random_button, BorderLayout.WEST);

		/* Add subpanels! */

		add(button_panel, BorderLayout.SOUTH);

		JPanel settings_panel = new JPanel();
		settings_panel.setLayout(new BoxLayout(settings_panel, BoxLayout.Y_AXIS));

		JPanel size_panel = new JPanel();
		size_panel.setLayout(new BorderLayout());
		size_panel.setAlignmentX(Component.CENTER_ALIGNMENT);
		settings_panel.add(size_panel);

		JPanel low_birth = new JPanel();
		low_birth.setLayout(new BorderLayout());
		low_birth.setAlignmentX(Component.CENTER_ALIGNMENT);
		settings_panel.add(low_birth);

		JPanel high_birth = new JPanel();
		high_birth.setLayout(new BorderLayout());
		high_birth.setAlignmentX(Component.CENTER_ALIGNMENT);
		settings_panel.add(high_birth);

		JPanel low_survival = new JPanel();
		low_survival.setLayout(new BorderLayout());
		low_survival.setAlignmentX(Component.CENTER_ALIGNMENT);
		settings_panel.add(low_survival);

		JPanel high_survival = new JPanel();
		high_survival.setLayout(new BorderLayout());
		high_survival.setAlignmentX(Component.CENTER_ALIGNMENT);
		settings_panel.add(high_survival);

		JPanel torus = new JPanel();
		torus.setLayout(new BorderLayout());
		torus.setAlignmentX(Component.CENTER_ALIGNMENT);
		settings_panel.add(torus);

		/* Add labels */

		JLabel size_label = new JLabel("Size");
		size_panel.add(size_label, BorderLayout.EAST);
		size_field = new JTextField("10");
		size_panel.add(size_field, BorderLayout.CENTER);

		JLabel low_birth_label = new JLabel("Low Birth");
		low_birth.add(low_birth_label, BorderLayout.EAST);
		_low_birth = new JTextField("3");
		low_birth.add(_low_birth, BorderLayout.CENTER);

		JLabel high_birth_label = new JLabel("High Birth");
		high_birth.add(high_birth_label, BorderLayout.EAST);
		_high_birth = new JTextField("3");
		high_birth.add(_high_birth, BorderLayout.CENTER);

		JLabel low_death_label = new JLabel("Low Survival");
		low_survival.add(low_death_label, BorderLayout.EAST);
		_low_death = new JTextField("2");
		low_survival.add(_low_death, BorderLayout.CENTER);

		JLabel high_death_label = new JLabel("High Survival");
		high_survival.add(high_death_label, BorderLayout.EAST);
		_high_death = new JTextField("3");
		high_survival.add(_high_death, BorderLayout.CENTER);

		JLabel torus_label = new JLabel("Torus On (1), Off (0)");
		torus.add(torus_label, BorderLayout.EAST);
		_torus = new JTextField("0");
		torus.add(_torus, BorderLayout.CENTER);

		JButton last = new JButton("Settings");
		last.setAlignmentX(Component.CENTER_ALIGNMENT);
		last.addActionListener(this);
		last.setActionCommand("settings");
		settings_panel.add(last);
		add(settings_panel, BorderLayout.EAST);
		_board.addSpotListener(this);
	}

	/*
	 * Goes through the listeners and gets their events (so button pressing)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().contentEquals("settings") && (Integer.parseInt(size_field.getText()) < 10 || Integer.parseInt(size_field.getText()) > 500)) {
			_initial.setText("Enter a size 10 to 500");
			add(_initial, BorderLayout.CENTER);
			return;
		}
		if(e.getActionCommand().equals("settings") && 
				(Integer.parseInt(_low_death.getText()) < 0 || Integer.parseInt(_high_death.getText()) > 8
						|| Integer.parseInt(_low_death.getText()) > Integer.parseInt(_high_death.getText()) || Integer.parseInt(_low_birth.getText()) < 0 || 
				Integer.parseInt(_high_birth.getText()) > 8 || Integer.parseInt(_low_birth.getText()) > Integer.parseInt(_high_birth.getText()))) {
			_initial.setText("Fix the thresholds");
			add(_initial, BorderLayout.CENTER);
			return;
		}
		for(LifeController l : listeners)
			l.getEvent(e);
	}

	@Override
	public void spotClicked(Spot spot) {
		// TODO Auto-generated method stub
		spot.toggleSpot();
	}

	@Override
	public void spotEntered(Spot spot) {
		// TODO Auto-generated method stub
		spot.highlightSpot();
	}

	@Override
	public void spotExited(Spot spot) {
		// TODO Auto-generated method stub
		spot.unhighlightSpot();
	}

	public void addListener(LifeController l) {
		listeners.add(l);
	}

	public int getLength() {
		return Integer.parseInt(size_field.getText());
	}

	public int getlowBirth() {
		return Integer.parseInt(_low_birth.getText());
	}

	public int getHighBirth() {
		return Integer.parseInt(_high_birth.getText());
	}

	public int getLowSurvival() {
		return Integer.parseInt(_low_death.getText());
	}

	public int getHighSurvival() {
		return Integer.parseInt(_high_death.getText());
	}

	public int getTorus() {
		return Integer.parseInt(_torus.getText());
	}

	/*
	 * Gets rid of very initial hi message and shows a new board every time
	 */
	public void setBoard(JSpotBoard board) {
		remove(_initial);
		remove(_board);
		_board = board;
		add(_board, BorderLayout.CENTER);
		_board.removeSpotListener(this);
		_board.addSpotListener(this);
		revalidate();
		repaint();
	}
}
