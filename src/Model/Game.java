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
		Skeleton.printCall(Skeleton.getName(this) +
				".addField(" +
				Skeleton.getName(f) + ")"
		);
		raktarepulet.add(f);
		Skeleton.printReturn();
	}
	/**
	 * Elind�tja a j�t�kot, inicializ�lja a p�ly�t,
	 * l�d�kat �s munk�sok kezd�poz�ci�j�t
	 */
	public void startGame(){
		Skeleton.printCall(Skeleton.getName(this) +
				".startGame()"
		);
		Skeleton.printReturn();
		
	}
	
	/**
	 *  Megvizsg�lja, hogy a j�t�k v�get �rt-e
	 * @return Igaz ha v�ge, egy�bk�nt hamis
	 */
	public boolean checkGameEnd() {
		Skeleton.printCall(Skeleton.getName(this) +
				".startGame()"
		);
		Skeleton.printReturn("False");
		return false;
		
	}
	
	/**
	 * Ellen�rzi a munk�sokat, hogy tudnak-e m�g �rdemi l�p�st tenni.
	 * @return igaz, ha van olyan munk�s, aki tud l�pni, egy�bk�nt hamis
	 */
	public boolean checkWorkerDeadlock() {
		Skeleton.printCall(Skeleton.getName(this) +
				".checkWorkerDeadlock()"
		);
		Skeleton.printReturn("True");
		return true;
	}
	/**
	 * Ellen�rzi,hogy a l�d�k mozgathat�k-e.
	 * @return igaz, ha van mozgathat� l�da, egy�bk�nt hamis
	 */
	public boolean checkBoxDeadlock() {
		Skeleton.printCall(Skeleton.getName(this) +
				".checkBoxDeadlock()"
		);
		Skeleton.printReturn("True");
		return true;
	}
	
	/**
	 * Ellen�rzi, hogy van-e m�g pontot �r� c�lmez�.
	 * @return igaz, ha van, egy�bk�nt hamis
	 */
	public boolean checkGoalFields() {
		Skeleton.printCall(Skeleton.getName(this) +
				".checkGoalFields()"
		);
		Skeleton.printReturn("True");
		return  true;
	}
	
	/**
	 * Le�ll�tja �s befejezi a j�t�kot.
	 */
	public void endGame() {
		Skeleton.printCall(Skeleton.getName(this) +
				".endGame()"
		);
		Skeleton.printReturn();
	}
}
