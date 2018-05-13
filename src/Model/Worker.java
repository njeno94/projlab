package Model;

import View.ThingView;

/**
 * A munk�shoz tartoz� oszt�ly. Feladata a munk�s �let�nek
 * valamint pontjainak kezel�se, a l�p�s illetve a l�d�k tol�sa.
 */
public class Worker extends Thing {
	private int points;
	private int force;
	private String name;
	private boolean playing;
	private boolean stepping;
	/**
	 * Konstruktor
	 */
	public Worker(String name) {
		this.name = name;
		points = 0;
		force = 6;
		playing = true;
		stepping = false;
	}
	
	/**
	 *  a munk�s l�p�s��rt felel�s met�dus,
	 *  param�terk�nt megkapja, hogy milyen ir�nyba l�pjen
	 * @param d		az ir�ny, amerre l�pni szeretne
	 */
	public void step(Direction d) {
		stepping = true;
		Field nextField = field.getNeighbour(d);
		stepping = true;
		if (nextField != null)
			nextField.accept(this, d);
		stepping = false;
	}
	
	/**
	 * Ez a met�dus kezeli a munk�s tol�s�t, amennyiben egy m�sik munk�s tolja.
	 * @param w		a m�sik munk�s, aki tolni szeretn� 
	 * @param d		az ir�ny, amerre tolni szeretn� a munk�st							
	 */
	@Override
	public void pushed(Worker w, Direction d) {
		//Not possible
	}
	
	/**
	 * Ez a met�dus kezeli a Worker tol�s�t,
	 * amennyiben Box tolja. Param�terk�nt kapja a dobozt,
	 * illetve, hogy milyen ir�nyba szeretn�k tolni. 
	 * @param b		a doboz, amit r� akarnak tolni a munk�sra
	 * @param d		az ir�ny, amerre tolni szeretn�k a munk�st
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
<<<<<<< HEAD
		if (stepping) {
=======
		if (neighbour != null && !stepping) {
>>>>>>> 6f8bb64b15f57ab460863da04f88f553360eb8f0
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
