package Model;

/**
 * A falat illetve az oszlopot modellez� oszt�ly. 
 */
public class Wall extends Field {
	
	/**
	 * A fel�ldefini�lt befogad� met�dus,
	 * ezt h�vj�k meg a Worker objektumok,
	 * �s nem tudja �ket befogadni ez a mez�,
	 * hiszen ez falat vagy oszlopot reprezent�l� mez�.
	 * @param w		a munk�s, aki a mez�re akar l�pni
	 * @param d		az ir�ny, amerre a munk�s menni szeretne
	 * @return		mivel nem tud semmilyen dolgot fogadni, mindig hamissal t�r vissza
	 */
	@Override
	public boolean accept(Worker w, Direction d) {
		System.out.println("Nem tud tov�bb menni, mert fal van!");
		return false;
	}
	 /**
	  * A fel�ldefini�lt befogad� met�dus,
	  * ezt h�vj�k meg a Box objektumok,
	  * �s nem tudja �ket befogadni ez a mez�, hiszen ez falat
	  * vagy oszlopot reprezent�l� mez�.
	  * @param b 	a l�da, amit a mez�re szeretn�nek tolni
	  * @param d	az ir�ny, amerre a l�d�t tolni szeretn�k
	  */
	@Override
	public void accept(Box b, Direction d) {
		System.out.println("Nem lehet tov�bb tolni a dobozt, mert fal van!");
	}
}
