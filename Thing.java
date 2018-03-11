package Model;

/**
 * A munkás és a láda absztrakt õsosztálya. Felelõssége a mozgatásuk kezelése.
 */
public abstract class Thing {
	protected Field field;

	/**
	 * Konstruktor
	 */
	public Thing() {
		field = null;
	}

	public abstract void pushed(Worker w, Direction d);
	
	public abstract void pushed(Box b, Direction d);

	/**
	 * Beállítja a Thing field attribútumát
	 * @param f		a mezõ, amin van a dolog
	 */
	public void addField(Field f) {
		field = f;
	}
	
	/**
	 *  Hozzáadja a ládáért kapott pontokat
	 *  a ládát toló Worker pontjaihoz,
	 *  egyébként a célmezõtõl a tolást indító Workerig
	 *  hívja a pontadó metódusokat.
	 * @param d
	 */
	public void addPoint(Direction d) {
			Field neighbour = field.getNeighbour(d);
			
			if (neighbour != null) {
				neighbour.addPointToThing(d);
			}
				}

	/**
	 * A thingen hívható mezõeltávolító metódus,
	 * meghívja a Field removeThing() metódusát
	 */
	public void removeFromField() {
		if (field != null) {		
			field.removeThing();
		}
	}

	/**
	 * Eltûnteti a dolgot a pályáról.
	 */
	public void disappear() {
		removeFromField();
		field = null;
	}

}
