package Model;

/**
 * A falat illetve az oszlopot modellező osztály. 
 */
public class Wall extends Field {
	
	public Wall() {
		//
		this.opened = false; ///For the game end checks...
	}
	
	/**
	 * A felüldefiniált befogadó metódus,
	 * ezt hívják meg a Worker objektumok,
	 * és nem tudja őket befogadni ez a mezó,
	 * hiszen ez falat vagy oszlopot reprezentáló mező.
	 * @param w		a munkás, aki a mezőre akar lépni
	 * @param d		az irány, amerre a munkás menni szeretne
	 * @return		mivel nem tud semmilyen dolgot fogadni, mindig hamissal tér vissza
	 */
	@Override
	public boolean accept(Worker w, Direction d) {
		return false;
	}
	
	/**
	  * A felüldefiniált befogadó metódus,
	  * ezt hívják meg a Box objektumok,
	  * és nem tudja őket befogadni ez a mező, hiszen ez falat
	  * vagy oszlopot reprezentáló mező.
	  * @param b 	a láda, amit a mezőre szeretnének tolni
	  * @param d	az irány, amerre a ládát tolni szeretnék
	  */
	@Override
	public void accept(Box b, Direction d, int force, int friction) {
	}
}
