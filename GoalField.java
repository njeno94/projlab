package Model;

/**
 * Egy speciális Field, amely a raktár épületnek
 * azt a négyzetét reprezentálja, ahová dobozt kell juttatni.
 * Amennyiben toltak rá ládát, a láda nem mozdítható onnan. 
 */
public class GoalField extends Field {
	
	/**
	 * Befogadja a paraméterként kapott Worker objektumot.
	 * Meghívja a a paraméterként kapott Box objektum addPoint() metódusát
	 * ellentétes iránnyal, hogy a lánc végén megkapja a dobozt toló munkás a pontot.
	 * @param w a munkás, aki a mezõre akar lépni
	 * @param d az irány, amerre a munkás mozogni szeretne
	 * @return
	 */
	@Override
	public boolean accept(Worker w, Direction d) {
		if (thing == null) {
			w.removeFromField();
			thing = w;
			w.addField(this);
			return true;
		}
		else {
			System.out.println("Ezt worker nem tudja már eltolni, mert célmezõre jutott a láda!");
		}
		return false;
	}
	
	/**
	 * Befogadja a paraméterként kapott Box objektumot.
	 * Meghívja a a paraméterként kapott Box objektum addPoint() metódusát
	 * ellentétes iránnyal, hogy a lánc végén megkapja a dobozt toló munkás a pontot.
	 * @param b a láda, amit a mezõre akarnak tolni
	 * @param d az irány, amerre a ládát tolni szeretnék
	 * @return
	 */
	@Override
	public void accept(Box b, Direction d) {
		if (thing == null) {
			b.addPoint(convertDir(d));
			b.removeFromField();
			thing = b;
			b.addField(this);
		}
		else {
			System.out.println("Ezen a célmezõn már van doboz!");
		}
	}
}
