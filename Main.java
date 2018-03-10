package Model;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		
		Warehouse raktar = new Warehouse();
		Worker w1 = new Worker();
		Worker w2 = new Worker();
		Box b1 = new Box();
		Box b2 = new Box();
		
		SwitchHoleField f1 = new SwitchHoleField();
		Field f2 = new Field();
		Field f3 = new Field();
		Field f4 = new Field();
		SwitchField f5 = new SwitchField();
		Field f6 = new Field();
		
		Game game = new Game();
		
		game.addField(f1);
		game.addField(f2);
		game.addField(f3);
		game.addField(f4);
		game.addField(f5);
		game.addField(f6);
		
		//// Field-ek beállítása
		f1.setNeighbour(Direction.RIGHT, f2);
		f2.setNeighbour(Direction.LEFT, f1);
		f2.setNeighbour(Direction.RIGHT, f3);
		f3.setNeighbour(Direction.LEFT, f2);
		f3.setNeighbour(Direction.RIGHT, f4);
		f4.setNeighbour(Direction.LEFT, f3);
		f4.setNeighbour(Direction.RIGHT, f5);
		f5.setNeighbour(Direction.LEFT, f4);
		f5.setNeighbour(Direction.RIGHT, f6);
		f6.setNeighbour(Direction.LEFT, f5);
		
		//// Munkás, láda beállítása
		f3.accept(w1, Direction.UP);
		f2.accept(b1, Direction.UP);
		f4.accept(b2, Direction.UP);
		
		//// Kapcsoló hozzáadása
		f5.setSwitchHoleField(f1);

		
		String s = null;
		Scanner in = new Scanner(System.in);
		
		while (s == null || !(s.equals("exit"))) {
			s = in.nextLine();
			
			switch(s) {
			case "D":
				w1.step(Direction.RIGHT);
				break;
			case "A":
				w1.step(Direction.LEFT);
				break;
			}
		}
		System.out.println("w1 pont: " + w1.getPoint());
	}
}
