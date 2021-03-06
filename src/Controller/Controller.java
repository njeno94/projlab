package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import Model.Direction;
import Model.Field;
import Model.Game;
import View.View;

public class Controller implements ActionListener, KeyListener {
	private JFrame frame;
	private JMenuBar menuBar;
	private JMenu newGameMenu;
	private JMenuItem exitMenuItem;
	private JMenuItem onePlayerMenuI;
	private JMenuItem twoPlayerMenuI;
	private boolean twoPlayerGame;
	
	private View view = new View();
	private Game game = null;
	
	private Timer timer;
	private boolean running = false;
	
	public Controller() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Sokoban Killer by emoji");
		frame.setResizable(false);
		
		menuBar = new JMenuBar();
		newGameMenu = new JMenu("New Game");
		exitMenuItem = new JMenuItem("Exit");
		onePlayerMenuI = new JMenuItem("1 Player Mode");
		twoPlayerMenuI = new JMenuItem("2 Player Mode");
		
		
		newGameMenu.add(onePlayerMenuI);
		newGameMenu.add(twoPlayerMenuI);
		menuBar.add(newGameMenu);
		menuBar.add(exitMenuItem);
		
		exitMenuItem.addActionListener(new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				Game.endGame();
			}
		});
		
		onePlayerMenuI.addActionListener(new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				startGame(false);
			}
		});
		
		twoPlayerMenuI.addActionListener(new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				startGame(true);
			}
		});
		
		twoPlayerGame = false;
		
		frame.setJMenuBar(menuBar);
		frame.add(view);		
		frame.addKeyListener(this);
		
		timer = new Timer(20, this);
		
		//Starting with 2 players mode game
		startGame(true);
		//Fit frame to View's JPanel
		frame.pack();
		frame.setVisible(true);
	}
	
	
	public static void main(String[] args) throws InterruptedException, IOException {
		Controller controller = new Controller();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		view.setWorkersPoints(game.getWorkerPoints()[0], game.getWorkerPoints()[1]);
		updateViews();
		view.update();
		if (checkGameEnd()) {
			timer.stop();
			running = false;
		}
	}
	
	public void startGame(boolean twoPlayerMode) {
		view.clear();
		game = new Game(view);
		twoPlayerGame = twoPlayerMode;
		game.startGame(twoPlayerMode);
		game.setMap("src/Model/warehouse.txt");
		timer.start();
		running = true;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (running) {
			switch(e.getKeyCode()) {
			case KeyEvent.VK_RIGHT:
				game.moveW1(Direction.RIGHT);
				break;
			case KeyEvent.VK_LEFT:
				game.moveW1(Direction.LEFT);
				break;
			case KeyEvent.VK_UP:
				game.moveW1(Direction.UP);
				break;
			case KeyEvent.VK_DOWN:
				game.moveW1(Direction.DOWN);
				break;
			case KeyEvent.VK_D:
				game.moveW2(Direction.RIGHT);
				break;
			case KeyEvent.VK_A:
				game.moveW2(Direction.LEFT);
				break;
			case KeyEvent.VK_W:
				game.moveW2(Direction.UP);
				break;
			case KeyEvent.VK_S:
				game.moveW2(Direction.DOWN);
				break;
				
			case KeyEvent.VK_O:
				game.oilW1("o");
				break;
			case KeyEvent.VK_H:
				game.oilW1("h");
				break;
			case KeyEvent.VK_M:
				game.oilW2("m");
				break;
			case KeyEvent.VK_N:
				game.oilW2("n");
				break;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {}

	@Override
	public void keyTyped(KeyEvent arg0) {}
	
	public void updateViews() {
		List<Field> wareHouse = game.getWareHouse();
		
		for (int i = 0; i < wareHouse.size(); i++) {
			view.setThingView(wareHouse.get(i).getActualThingView(), i);
		}
	}
	
	public boolean checkGameEnd() {
		if (!game.checkWorkersAlive()) {
			if (twoPlayerGame) {
				if (game.getWorkerPoints()[0] > game.getWorkerPoints()[1]) {
					JOptionPane.showMessageDialog(frame, "Player 2 meghalt... Player 1 győzött!", "Game over", 0);				
				} else {
					JOptionPane.showMessageDialog(frame, "Player 1 meghalt... Player 2 győzött!", "Game over", 0);
				}				
			} else {
				JOptionPane.showMessageDialog(frame, "Player 1 meghalt...", "Game over", 0);
			}
			return true;
		}
		
		if (!game.checkGoalFields()) {
			if (twoPlayerGame) {
				if (game.getWorkerPoints()[0] > game.getWorkerPoints()[1]) {
					JOptionPane.showMessageDialog(frame, "Minden célhelyre került láda! Player 1 győzött több ponttal!", "Congratulations!", 0);				
				} else {
					JOptionPane.showMessageDialog(frame, "Minden célhelyre került láda! Player 2 győzött több ponttal!", "Congratulations!", 0);
				}				
			} else {
				JOptionPane.showMessageDialog(frame, "Minden célhelyre került láda! Player 1 győzött " + game.getWorkerPoints()[0] + " ponttal!", "Congratulations!", 0);
			}
			return true;
		}
		
		if (!game.checkBoxDeadlock()) {
			JOptionPane.showMessageDialog(frame, "Nincs elég mozgatható láda!", "Game over", 0);
			return true;
		}
		
		if (!game.checkWorkerDeadlock()) {
			JOptionPane.showMessageDialog(frame, "Nem tud lépni egy munkás sem!", "Game over", 0);
			return true;
		}
		
		return false;
	}
}
