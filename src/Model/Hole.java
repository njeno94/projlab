package Model;

/**
 * Az egyszerű lyuk modellezésére szolgáló osztály.
 * Ha egy munkás rálép, meghal, ha egy dobozt rátolnak, eltűnik.
 */
public class Hole extends Field {
	/**
	 * Befogadja a paraméterként kapott Worker objektumot
	 * és eltávolítja a pályáról.
	 * @param w		a munkás, aki a mezőre szeretne lépni
	 * @param d		az irány, amerre a munkás mozogni szeretne
	 * @return
	 */
	@Override
	public boolean accept(Worker w, Direction d) {
		w.disappear();
		return false;
	}
	
	/**
	 * Befogadja a paraméterként kapott Box objektumot, és eltávolítja a pályáról
	 * @param b		a láda, amit a mezőre akarnak tolni
	 * @param d		az irány, amerre a ládát tolni szeretnék
	 */
	@Override
	public void accept(Box b, Direction d, int force, int friction) {
		if (force > friction) {			
			b.disappear();
		}
	}
	
	@Override
	public void Draw() {
		System.out.print("h  ");
	}
}
