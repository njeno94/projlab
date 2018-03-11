package Model;

/**
 * A munkáshoz tartozó osztály. Feladata a munkás életének
 * valamint pontjainak kezelése, a lépés illetve a ládák tolása.
 */

public class Worker extends Thing {
	private int points;
	
	/**
	 * Konstruktor
	 */
	public Worker() {
		points = 0;
	}
	
	/**
	 *  a munkás lépéséért felelõs metódus,
	 *  paraméterként megkapja, hogy milyen irányba lépjen
	 * @param d		az irány, amerre lépni szeretne
	 */
	public void step(Direction d) {
		Field nextField = field.getNeighbour(d);
		
		nextField.accept(this, d);
		
	}
	
	/**
	 * Ez a metódus kezeli a munkás tolását, amennyiben egy másik munkás tolja.
	 * @param w		a másik munkás, aki tolni szeretné 
	 * @param d		az irány, amerre tolni szeretné a munkást							
	 */
	@Override
	public void pushed(Worker w, Direction d) {
		//
	}
	
	/**
	 * Ez a metódus kezeli a Worker tolását,
	 * amennyiben Box tolja. Paraméterként kapja a dobozt,
	 * illetve, hogy milyen irányba szeretnék tolni. 
	 * @param b		a doboz, amit rá akarnak tolni a munkásra
	 * @param d		az irány, amerre tolni szeretnék a munkást
	 */
	@Override
	public void pushed(Box b, Direction d) {
		Field nextField = field.getNeighbour(d);
		
		if (nextField.accept(this, d)) {
			//When it can go...
		}
		else {
			this.disappear();
		}
	}
	
	@Override
	public void addPoint(Direction d) {
		Field neighbour = field.getNeighbour(d);
		if (neighbour != null) {
			neighbour.addPointToThing(d);
		}
		else {
			points++;
		}
	}
	
	public int getPoint() {
		return points;
	}
	
}