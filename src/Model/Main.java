package Model;

import java.io.IOException;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws InterruptedException, IOException {

		
		Worker w1 = new Worker("Worker 1");
		Worker w2 = new Worker("Worker 2");

		
		Game game = new Game();
		game.setMap(w1, w2, args[0]);
		

		game.showWareHouse();
		
		
		String s = null;
		Scanner in = new Scanner(System.in);
		
		while (s == null || !(s.equals("exit"))) {
			s = in.nextLine();
			
			switch(s.toUpperCase()) {
			case "D":
				if (w1 != null)
				w1.step(Direction.RIGHT);
				break;
			case "A":
				w1.step(Direction.LEFT);
				break;
			case "W":
				w1.step(Direction.UP);
				break;
			case "S":
				w1.step(Direction.DOWN);
				break;
			case "H" :
				w1.oilFieldWithHoney();
				break;
			case "O" :
				w1.oilFieldWithOil();
				break;
			}
			
			//new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			game.showWareHouse();
			if (game.checkGameEnd()) {
				Game.endGame();
			}
		}
		
        System.exit(0);

	}
}
