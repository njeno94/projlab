package Model;

/**
 * A munk�s �s a l�da absztrakt �soszt�lya. Felel�ss�ge a mozgat�suk kezel�se.
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
	 * Be�ll�tja a Thing field attrib�tum�t
	 * @param f		a mez�, amin van a dolog
	 */
	public void addField(Field f) {
		Skeleton.printCall( Skeleton.getName(this) + 
				".addField(" + 
				Skeleton.getName(f) + ")"
		);
		field = f;
		Skeleton.printReturn();
	}
	
	/**
	 *  Hozz�adja a l�d��rt kapott pontokat
	 *  a l�d�t tol� Worker pontjaihoz,
	 *  egy�bk�nt a c�lmez�t�l a tol�st ind�t� Workerig
	 *  h�vja a pontad� met�dusokat.
	 * @param d
	 */
	public void addPoint(Direction d) {
		Skeleton.printCall( Skeleton.getName(this) + 
				".addPoint(" + 
				d.toString() + ")"
		);
		Field neighbour = field.getNeighbour(d);
			
		if (neighbour != null) {
			neighbour.addPointToThing(d);
		}
		Skeleton.printReturn();
	}

	/**
	 * A thingen h�vhat� mez�elt�vol�t� met�dus,
	 * megh�vja a Field removeThing() met�dus�t
	 */
	public void removeFromField() {
		Skeleton.printCall( Skeleton.getName(this) + 
				".removeFromField()"
		);
		if (field != null) {		
			field.removeThing();
		}
		Skeleton.printReturn();
	}

	/**
	 * Elt�nteti a dolgot a p�ly�r�l.
	 */
	public void disappear() {
		Skeleton.printCall( Skeleton.getName(this) + 
				".disappear()"
		);
		removeFromField();
		field = null;
		Skeleton.printReturn();
	}

}
