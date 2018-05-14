package Model;

import View.ThingView;

/**
 * Ez az osztály reprezentálja a ládákat a játékban.
 */
public class Box extends Thing {
	private boolean onGoalField;
	/**
	 * A ládák tolását valósítja meg, amikor munkás tolja a ládát.
	 * @param w a munkás, aki tolja a ládát
	 * @param d az irány, amelyik irányba a ládát tolja a munkás
	 */
	public void pushed(Worker w, Direction d) {
		Field nextField = field.getNeighbour(d);
		
		nextField.accept(this, d, w.getForce(), field.getFriction());
	}
	
	/**
	 * A ládák tolását valósítja meg, amikor munkás tolja a ládát.
	 * @param b a láda, amit egy munkás ennek a ládának a helyére akar tolni.
	 * @param d az irány, amelyikbe a ládát tolni akarják
	 */
	public void pushed(Box b, Direction d, int force, int friction) {
		Field nextField = field.getNeighbour(d);
	
		nextField.accept(this, d, force, friction);
	}
	
	/**
	 * A ládák tolhatóságát vizsgáló függvény. 
	 * @return Igaz, ha tolható valamerről a láda, hamis ha nem.
	 */
	public boolean canBePushed() {
		Direction[] dirs = Direction.values();
		for (int i = 0; i < dirs.length; i++) {
			if (field != null) {
				if ((field.getNeighbour(dirs[i]) != null) && (field.getNeighbour(Field.convertDir(dirs[i])) != null)) {
					if ((field.getNeighbour(dirs[i]).isOpened()) && (field.getNeighbour(Field.convertDir(dirs[i])).isOpened())) {					
						return true;
					}
				}				
			}
		}
		return false;
	}

	/**
	 * A doboz nézetét létrehozó metódus.
	 * @return az aktuális doboz ThingView objektuma.
	 */
	@Override
	public ThingView getView() {
		return new ThingView(this);
	}
	
	/**
	 * Visszaadja, hogy a doboz elért-e már célmezőre.
	 * @return Igaz, ha célmezőn van, egyébként hamis.
	 */
	public boolean isReachedToGoalField() {
		return onGoalField;
	}

	/**
	 * A doboz célmezőre jutásakor hívódik.
	 */
	public void arrivedOnGoalField() {
		onGoalField = true;
	}
}
