package Model;

import View.ThingView;

/**
 * A munkáshoz tartozó osztály. Feladata a munkás életének
 * valamint pontjainak kezelése, a lépés illetve a ládák tolása.
 */
public class Worker extends Thing {
	private int points;
	private int force;
	private String name;
	private boolean playing;
	/**
	 * Konstruktor
	 */
	public Worker(String name) {
		this.name = name;
		points = 0;
		force = 6;
		playing = true;
	}
	
	/**
	 *  a munkás lépéséért felelős metódus,
	 *  paraméterként megkapja, hogy milyen irányba lépjen
	 * @param d		az irány, amerre lépni szeretne
	 */
	public void step(Direction d) {
		Field nextField = field.getNeighbour(d);
		if (nextField != null)
			nextField.accept(this, d);
	}
	
	/**
	 * Ez a metódus kezeli a munkás tolását, amennyiben egy másik munkás tolja.
	 * @param w		a másik munkás, aki tolni szeretné 
	 * @param d		az irány, amerre tolni szeretné a munkást							
	 */
	@Override
	public void pushed(Worker w, Direction d) {
		//Not possible
	}
	
	/**
	 * Ez a metódus kezeli a Worker tolását,
	 * amennyiben Box tolja. Paraméterként kapja a dobozt,
	 * illetve, hogy milyen irányba szeretnék tolni. 
	 * @param b		a doboz, amit rá akarnak tolni a munkásra
	 * @param d		az irány, amerre tolni szeretnék a munkást
	 */
	@Override
	public void pushed(Box b, Direction d, int force, int friction) {
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
	
	public void oilFieldWithOil() {
		field.decreaseFriction();
	}
	
	public void oilFieldWithHoney() {
		field.increaseFriction();
	}
	
	public int getForce() {
		return force;
	}

	@Override
	public void Draw() {
		System.out.print("w");
	}
	
	@Override
	public void disappear() {
		super.disappear();
		playing = false;
		System.out.println(points);
	}
	
	public boolean canStep() {
		Direction[] dirs = Direction.values();
		for (Direction d : dirs) {
			if (field != null) {
				if (field.getNeighbour(d) != null) {
					if (field.getNeighbour(d).isOpened()) {
						return true;					
					}
				}				
			}
		}
		return false;
	}

	@Override
	public ThingView getView() {
		return new ThingView(this, name);
	}
	
	public boolean isPlaying() {
		return playing;
	}
}
