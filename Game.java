package Model;

import java.util.ArrayList;

public class Game {
	private ArrayList<Field> raktarepulet;
	
	public Game() {
		raktarepulet = new ArrayList<Field>();
	}
	
	public void addField(Field f) {
		raktarepulet.add(f);
	}
}
