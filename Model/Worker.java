package Model;

/**
 * A munk�shoz tartoz� oszt�ly. Feladata a munk�s �let�nek
 * valamint pontjainak kezel�se, a l�p�s illetve a l�d�k tol�sa.
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
	 *  a munk�s l�p�s��rt felel�s met�dus,
	 *  param�terk�nt megkapja, hogy milyen ir�nyba l�pjen
	 * @param d		az ir�ny, amerre l�pni szeretne
	 */
	public void step(Direction d) {
		Skeleton.printCall(Skeleton.getName(this) + 
				".step(" + d.toString() + ")");
		Field nextField = field.getNeighbour(d);
		nextField.accept(this, d);
		Skeleton.printReturn();
		
	}
	
	/**
	 * Ez a met�dus kezeli a munk�s tol�s�t, amennyiben egy m�sik munk�s tolja.
	 * @param w		a m�sik munk�s, aki tolni szeretn� 
	 * @param d		az ir�ny, amerre tolni szeretn� a munk�st							
	 */
	@Override
	public void pushed(Worker w, Direction d) {
		Skeleton.printCall(Skeleton.getName(this) + 
				"pushed(" + Skeleton.getName(w) + 
				"," + d.toString()+ ")"
		);
		Skeleton.printReturn("");
		//
	}
	
	/**
	 * Ez a met�dus kezeli a Worker tol�s�t,
	 * amennyiben Box tolja. Param�terk�nt kapja a dobozt,
	 * illetve, hogy milyen ir�nyba szeretn�k tolni. 
	 * @param b		a doboz, amit r� akarnak tolni a munk�sra
	 * @param d		az ir�ny, amerre tolni szeretn�k a munk�st
	 */
	@Override
	public void pushed(Box b, Direction d) {
		
		Skeleton.printCall(Skeleton.getName(this) + 
				"pushed(" + Skeleton.getName(b) + 
				"," + d.toString()+ ")"
		);
		
		Field nextField = field.getNeighbour(d);
		
		if (nextField.accept(this, d)) {
			//When it can go...
		}
		else {
			this.disappear();
		}
		Skeleton.printReturn();
	}
	
	@Override
	public void addPoint(Direction d) {
		Skeleton.printCall(Skeleton.getName(this) + 
				"addPoint(" + d.toString()+ ")"
		);
		Field neighbour = field.getNeighbour(d);
		if (neighbour != null) {
			neighbour.addPointToThing(d);
		}
		else {
			points++;
		}
		Skeleton.printReturn();
	}
	
	public int getPoint() {
		Skeleton.printCall(Skeleton.getName(this) + 
				"getPoint()"
		);
		Skeleton.printReturn(""+points);
		return points;
	}
	
}