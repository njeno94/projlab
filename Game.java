package Model;

import java.util.ArrayList;

/**
 * Ez a singleton felelõs a játék irányításáért. Õ valósítja meg a raktár épületet.
 */
public class Game {
	private ArrayList<Field> raktarepulet;
	
	/**
	 * Konstruktor
	 */
	Game() {
		raktarepulet = new ArrayList<Field>();
	}
	
	public void addField(Field f) {
		raktarepulet.add(f);
	}
	/**
	 * Elindítja a játékot, inicializálja a pályát,
	 * ládákat és munkások kezdõpozícióját
	 */
	public void startGame(){
		
	}
	
	/**
	 *  Megvizsgálja, hogy a játék véget ért-e
	 * @return Igaz ha vége, egyébként hamis
	 */
	public boolean checkGameEnd() {
		
	}
	
	/**
	 * Ellenõrzi a munkásokat, hogy tudnak-e még érdemi lépést tenni.
	 * @return igaz, ha van olyan munkás, aki tud lépni, egyébként hamis
	 */
	public boolean checkWorkerDeadlock() {
		
	}
	/**
	 * Ellenõrzi,hogy a ládák mozgathatók-e.
	 * @return igaz, ha van mozgatható láda, egyébként hamis
	 */
	public boolean checkBoxDeadlock() {
		
	}
	
	/**
	 * Ellenõrzi, hogy van-e még pontot érõ célmezõ.
	 * @return igaz, ha van, egyébként hamis
	 */
	public boolean checkGoalFields() {
		
	}
	
	/**
	 * Leállítja és befejezi a játékot.
	 */
	public void endGame() {
		
	}
}
