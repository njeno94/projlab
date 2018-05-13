package Model;

/**
 * A kapcsolható lyuk modellje: vagy egyszerû mezõként,
 * vagy lyukként viselkedik állapotától függõen.
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
	 *  befogadja a paraméterként kapott Worker objektumot.
	 * @param w 	a munkás, aki a mezõre szeretne lépni
	 * @param d		az irány, amerre a munkás lépni szeretne
	 * @return		igaz, ha a munkás rá tud lépni, egyébként hamis
	 */
	@Override
	public boolean accept(Worker w, Direction d) {
		if (!state) {
			pushThingIfExists(w, d);	
			if (thing == null) {
				moveThingToCurrentField(w);
				return true;
			}
			return false;
		}
		else {
			w.disappear();
			return true;
		}
	}
	
	/**
	 * Befogadja a paraméterként kapott Box objektumot.
	 * @param b		a láda, amit a mezõre szeretnének tolni
	 * @param d		az irány, amerre a ládát tolni szeretnék
	 */
	@Override
	public void accept(Box b, Direction d, int force, int friction) {
		if (force > friction) {
			if (!state) {
				pushThingIfExists(b, d, force, friction + currentFriction);
				if (thing == null) {
					moveThingToCurrentField(b);							
				}
			}
			else {
				b.disappear();
			}
		}
	}
	
	/**
	 * Beállítja a mezõ állapotát
	 * @param s az állapot amit beállít a mezõnek
	 */
	public void setState(boolean s) {
		state = s;
	}
	
	/**
	 * Átállítja a mezõ állapotát. Ha nyitottra állítja,
	 * akkor eltûnik róla a rajta lévõ dolog.
	 */
	public void changeState() {
		if (state && thing != null) {
			thing.disappear();
		}
	}
	
	@Override
	public void Draw() {
		System.out.print("t");
		if (thing != null) {
			thing.Draw();
		} else {
			System.out.print(" ");
		}
		DrawFriction();
	}
	
	public boolean getState() {
		return state;
	}
}
