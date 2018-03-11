package Model;

/**
 * Az egyszerû lyuk modellezésére szolgáló osztály.
 * Ha egy munkás rálép, meghal, ha egy dobozt rátolnak, eltûnik.
 */
public class Hole extends Field {
	/**
	 * Befogadja a paraméterként kapott Worker objektumot
	 * és eltávolítja a pályáról.
	 * @param w		a munkás, aki a mezõre szeretne lépni
	 * @param d		az irány, amerre a munkás mozogni szeretne
	 * @return
	 */
	@Override
	public boolean accept(Worker w, Direction d) {
		System.out.println("A munkás meghalt, beleesett a lyukba!");
		w.disappear();
		return false;
	}
	
	/**
	 * Befogadja a paraméterként kapott Box objektumot, s eltávolítja a pályáról
	 * @param b		a láda, amit a mezõre akarnak tolni
	 * @param d		az irány, amerre a ládát tolni szeretnék
	 */
	@Override
	public void accept(Box b, Direction d) {
		b.disappear();
		System.out.println("A doboz beleesett a lyukba!");
	}
}
