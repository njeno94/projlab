package Model;

/**
 * Az egyszer� lyuk modellez�s�re szolg�l� oszt�ly.
 * Ha egy munk�s r�l�p, meghal, ha egy dobozt r�tolnak, elt�nik.
 */
public class Hole extends Field {
	/**
	 * Befogadja a param�terk�nt kapott Worker objektumot
	 * �s elt�vol�tja a p�ly�r�l.
	 * @param w		a munk�s, aki a mez�re szeretne l�pni
	 * @param d		az ir�ny, amerre a munk�s mozogni szeretne
	 * @return
	 */
	@Override
	public boolean accept(Worker w, Direction d) {
		w.disappear();
		return false;
	}
	
	/**
	 * Befogadja a param�terk�nt kapott Box objektumot, s elt�vol�tja a p�ly�r�l
	 * @param b		a l�da, amit a mez�re akarnak tolni
	 * @param d		az ir�ny, amerre a l�d�t tolni szeretn�k
	 */
	@Override
	public void accept(Box b, Direction d, int force, int friction) {
		if (force > friction) {			
			b.disappear();
		}
	}
	
	@Override
	public void Draw() {
		System.out.print("h");
		System.out.print(" ");
	}
}
