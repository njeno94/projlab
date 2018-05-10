package Model;

import View.ThingView;

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

	@Override
	public void Draw() {
		System.out.print("b");
	}
	
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

	@Override
	public ThingView getView() {
		return new ThingView(this);
	}
}