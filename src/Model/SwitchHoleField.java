package Model;

/**
 * A kapcsolhat� lyuk modellje: vagy egyszer� mez�k�nt,
 * vagy lyukk�nt viselkedik �llapot�t�l f�gg�en.
 *
 */
public class SwitchHoleField extends Field {
	
	private boolean state;
	
	/**
	 * Konstruktor
	 */
	SwitchHoleField() {
		state = false;
	}
	
	/**
	 *  befogadja a param�terk�nt kapott Worker objektumot.
	 * @param w 	a munk�s, aki a mez�re szeretne l�pni
	 * @param d		az ir�ny, amerre a munk�s l�pni szeretne
	 * @return		igaz, ha a munk�s r� tud l�pni, egy�bk�nt hamis
	 */
	@Override
	public boolean accept(Worker w, Direction d) {
		Skeleton.printCall( Skeleton.getName(this) + 
				".accept(" + 
				Skeleton.getName(w) + "," +
				d.toString() + ")"
		);
		
		if (!state) {
			if (thing == null) {
				w.removeFromField();
				addThing(w);
				w.addField(this);
				Skeleton.printReturn("True");
				return true;
			}
			else {
				thing.pushed(w, d);
				if (thing == null) {
					w.removeFromField();
					addThing(w);
					w.addField(this);
					Skeleton.printReturn("True");
					return true;
				}
			}
			Skeleton.printReturn("False");
			return false;
		}
		else {
			w.disappear();
			Skeleton.printReturn("True");
			return true;
		}
	}
	
	/**
	 * Befogadja a param�terk�nt kapott Box objektumot.
	 * @param b		a l�da, amit a mez�re szeretn�nek tolni
	 * @param d		az ir�ny, amerre a l�d�t tolni szeretn�k
	 */
	@Override
	public void accept(Box b, Direction d) {
		
		Skeleton.printCall( Skeleton.getName(this) + 
				".accept(" + 
				Skeleton.getName(b) + "," +
				d.toString() + ")"
		);
		
		if (!state) {
			if (thing == null) {
				b.removeFromField();
				addThing(b);
				b.addField(this);
			}
			else {
				thing.pushed(b, d);
				if (thing == null) {
					b.removeFromField();
					addThing(b);
					b.addField(this);
				}
			}
		}
		else {
			b.disappear();
		}
		Skeleton.printReturn();
	}
	
	/**
	 * Be�ll�tja a mez� �llapot�t
	 * @param s az �llapot amit be�ll�t a mez�nek
	 */
	public void setState(boolean s) {
		Skeleton.printCall( Skeleton.getName(this) + 
				".setState(" + 
				s + ")"
		);
		state = s;
		Skeleton.printReturn();
	}
	
	/**
	 * �t�ll�tja a mez� �llapot�t. Ha nyitottra �ll�tja,
	 * akkor elt�nik r�la a rajta l�v� dolog.
	 */
	public void changeState() {
		Skeleton.printCall( Skeleton.getName(this) + 
				".changeState()"
		);
		if (state && thing!=null) {
			thing.disappear();
		}
		Skeleton.printReturn();
	}
}
