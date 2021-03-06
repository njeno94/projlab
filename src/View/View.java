package View;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.LinkedList;
import javax.swing.JPanel;
import Model.Field;
import Model.GoalField;
import Model.Hole;
import Model.SwitchField;
import Model.SwitchHoleField;
import Model.Wall;

public class View extends JPanel {
	private LinkedList<Drawable> elements;
	public static int width = 500;
	public static int height = 500;
	public static final int imageWidth = 50;
	private int columns;
	private int player1Points = 0;
	private int player2Points = 0;
	
	public View() {
		this.setPreferredSize(new Dimension(width, height));
		this.setSize(width, height);
		
		elements = new LinkedList<Drawable>();
	}
	
	public void createFieldView(Field f) {
		elements.add(new FieldView(f));
	}
	
	public void createWallView(Wall w) {
		elements.add(new WallView(w));
	}
	
	public void createSwitchFieldView(SwitchField sf) {
		elements.add(new SwitchFieldView(sf));
	}
	
	public void createSwitchHoleFieldView(SwitchHoleField shf) {
		elements.add(new SwitchHoleFieldView(shf));
	}
	
	public void createHoleView(Hole h) {
		elements.add(new HoleView(h));
	}
	
	public void createGoalFieldView(GoalField g) {
		elements.add(new GoalFieldView(g));
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		int x = 0;
		int y = 0;
		for (int i = 0; i < elements.size(); i++) {
			elements.get(i).Draw(g, x, y);
			x += imageWidth;
			if ((i + 1 ) % columns == 0) {
				x = 0;
				y += imageWidth;
			}
		}
		
		g.setColor(Color.yellow);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
		g.drawString("Player1: " + player1Points, 10, 35);
		g.drawString("Player2: " + player2Points, 360, 35);
	}
	
	public void addThingView(ThingView tv) {
		elements.getLast().setThingView(tv);
	}
	
	public void setThingView(ThingView tv, int idx) {
		elements.get(idx).setThingView(tv);
	}
	
	public void update() {
		this.repaint();
	}

	public void clear() {
		elements.clear();
	}

	public void setColumns(int col) {
		columns = col;
	}

	public int getColumns() {
		return columns;
	}
	
	public void setWorkersPoints(int w1Points, int w2Points) {
		player1Points = w1Points;
		player2Points = w2Points;
	}
}
