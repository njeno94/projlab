package Model;

/**
 * A falat illetve az oszlopot modellezõ osztály. 
 */
public class Wall extends Field {
	
	/**
	 * A felüldefiniált befogadó metódus,
	 * ezt hívják meg a Worker objektumok,
	 * és nem tudja õket befogadni ez a mezõ,
	 * hiszen ez falat vagy oszlopot reprezentáló mezõ.
	 * @param w		a munkás, aki a mezõre akar lépni
	 * @param d		az irány, amerre a munkás menni szeretne
	 * @return		mivel nem tud semmilyen dolgot fogadni, mindig hamissal tér vissza
	 */
	@Override
	public boolean accept(Worker w, Direction d) {
		System.out.println("Nem tud tovább menni, mert fal van!");
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
	public void accept(Box b, Direction d) {
		System.out.println("Nem lehet tovább tolni a dobozt, mert fal van!");
	}
}
