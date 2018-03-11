package Model;


/**
 * Ez az osztály reprezentálja a ládákat a játékban.
 */
public class Box extends Thing {
	
	/**
	 * A ládák tolását valósítja meg, amikor munkás tolja a ládát.
	 * @param w a munkás, aki tolja a ládát
	 * @param d az irány, amelyik irányba a ládát tolja a munkás
	 */
	public void pushed(Worker w, Direction d) {
		Field nextField = field.getNeighbour(d);
		
		nextField.accept(this, d);
	}
	
	/**
	 * A ládák tolását valósítja meg, amikor munkás tolja a ládát.
	 * @param b a láda, amit egy munkás ennek a ládának a helyére akar tolni.
	 * @param d az irány, amelyikbe a ládát tolni akarják
	 */
	public void pushed(Box b, Direction d) {
		Field nextField = field.getNeighbour(d);
		
		nextField.accept(this, d);
	}

}