package Model;

import java.util.ArrayList;

/**
 * Ez a singleton felel�s a j�t�k ir�ny�t�s��rt. � val�s�tja meg a rakt�r �p�letet.
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
	 * Elind�tja a j�t�kot, inicializ�lja a p�ly�t,
	 * l�d�kat �s munk�sok kezd�poz�ci�j�t
	 */
	public void startGame(){
		
	}
	
	/**
	 *  Megvizsg�lja, hogy a j�t�k v�get �rt-e
	 * @return Igaz ha v�ge, egy�bk�nt hamis
	 */
	public boolean checkGameEnd() {
		
	}
	
	/**
	 * Ellen�rzi a munk�sokat, hogy tudnak-e m�g �rdemi l�p�st tenni.
	 * @return igaz, ha van olyan munk�s, aki tud l�pni, egy�bk�nt hamis
	 */
	public boolean checkWorkerDeadlock() {
		
	}
	/**
	 * Ellen�rzi,hogy a l�d�k mozgathat�k-e.
	 * @return igaz, ha van mozgathat� l�da, egy�bk�nt hamis
	 */
	public boolean checkBoxDeadlock() {
		
	}
	
	/**
	 * Ellen�rzi, hogy van-e m�g pontot �r� c�lmez�.
	 * @return igaz, ha van, egy�bk�nt hamis
	 */
	public boolean checkGoalFields() {
		
	}
	
	/**
	 * Le�ll�tja �s befejezi a j�t�kot.
	 */
	public void endGame() {
		
	}
}
