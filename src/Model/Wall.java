package Model;

/**
 * A falat illetve az oszlopot modellezõ osztály. 
 */
public class Wall extends Field {
	
	public Wall() {
		//
		this.opened = false; ///For the game end checks...
	}
	
	/**
	 * A felüldefiniált befogadó metódus,
	 * ezt hívják meg a Worker objektumok,
	 * és nem tudja õket befogadni ez a mezó,
	 * hiszen ez falat vagy oszlopot reprezentáló mezõ.
	 * @param w		a munkás, aki a mezõre akar lépni
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
	  * és nem tudja õket befogadni ez a mezõ, hiszen ez falat
	  * vagy oszlopot reprezentáló mezõ.
	  * @param b 	a láda, amit a mezõre szeretnének tolni
	  * @param d	az irány, amerre a ládát tolni szeretnék
	  */
	@Override
	public void accept(Box b, Direction d, int force, int friction) {
	}
	
	@Override
	public void Draw() {
		System.out.print("w");
		if (thing != null) {
			thing.Draw();
		}
		else {
			System.out.print("  ");
		}
	}
}
