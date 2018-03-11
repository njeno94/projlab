package Model;

import java.util.HashMap;
import java.util.Map;

/**
 * A raktárépület egy négyzetét reprezentálja,
 * amely képes tárolni egy ládát, vagy állhat rajta egy munkás,
 * illetve vannak speciális változatai.
 * Ismeri a körülötte lévõ mezõket.
 */
public class Field {
	protected Thing thing;
	private Map<Direction, Field> fields;
	
	/**
	 * konstruktor
	 */
	public Field() {
		thing = null;
		fields = new HashMap<Direction, Field>();
	}
	
	/**
	 * Beállítja d irányba az f mezõt szomszédnak.
	 * @param d az irány, amerre a szomszédot beállítjuk
	 * @param f a mezõ, ami a szomszéd lesz
	 */
	public void setNeighbour(Direction d, Field f) {
		fields.put(d, f);
	}
	
	/**
	 * Visszaadja a d irányban lévõ szomszéd mezõt.
	 * @param d az irány, amerre a mezõ van
	 * @return d  irányban lévõ szomszéd mezõ
	 */
	public Field getNeighbour(Direction d) {
		return fields.get(d);
	}
	
	/**
	 *  Ez a függvény ellenõrzi, hogy a paraméterként kapott munkást
	 *  be tudja-e fogadni a mezõ. Ha már van rajta valami,
	 *  akkor meghívja a rajta lévõ thing pushed metódusát.
	 *  Amennyiben a mezõ befogadja a paraméterként kapott Worker objektumot,
	 *  az elõzõ mezõrõl eltávolítódik és a jelenlegi mezõre kerül.
	 * @param w a munkás, aki szeretne a mezõre lépni
	 * @param d az irány, amelyikbe a munkás menni szeretne
	 * @return igazzal tér vissza, ha tudja fogadni, egyébként hamissal
	 */
	public boolean accept(Worker w, Direction d) {
		if (thing == null) {
			w.removeFromField();
			addThing(w);
			w.addField(this);
			return true;
		}
		else {
			thing.pushed(w, d);
			if (thing == null) {
				w.removeFromField();
				addThing(w);
				w.addField(this);
				return true;
			}
		}
		return false;
	}
	
	/**
	 *  Ez a függvény ellenõrzi, hogy a paraméterként kapott ládát
	 *  be tudja-e fogadni a mezõ. Ha már van rajta valami,
	 *  akkor meghívja a rajta lévõ thing pushed metódusát.
	 *  Amennyiben a mezõ befogadja a paraméterként kapott Box objektumot,
	 *  az elõzõ mezõrõl eltávolítódik és a jelenlegi mezõre kerül.
	 * @param w a munkás, aki szeretne a mezõre lépni
	 * @param d az irány, amelyikbe a munkás menni szeretne
	 * @return igazzal tér vissza, ha tudja fogadni, egyébként hamissal
	 */
	public void accept(Box b, Direction d) {
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
	
	/**
	 * A rajta lévõ thingnek hívja a pontadásért felelõs metódusát.
	 * @param d az irány, amerre van az adott thing
	 */
	public void addPointToThing(Direction d) {
		if (thing != null) {
			thing.addPoint(d);			
		}
	}
	
	/**
	 * Hozzáadja a mezõhöz a paraméterként kapott thing objektumot.
	 * @param t egy Thing, ami a mezõre lép
	 */
	public void addThing(Thing t) {
		thing = t;
	}
	
	/**
	 * Eltávolítja a rajta lévõ thinget a mezõrõl.
	 */
	public void removeThing() {
		thing = null;
	}
	
	/**
	 * Megadja egy iránynak az ellentettjét
	 * @param d az irány, aminek keressük az ellentettjét
	 * @return a d irány ellentettje
	 */
	public Direction convertDir(Direction d) {
		Direction opp = null;
		switch(d) {
		case RIGHT:
			opp = Direction.LEFT;
			break;
		case LEFT:
			opp = Direction.RIGHT;
			break;
		case UP:
			opp = Direction.DOWN;
			break;
		case DOWN:
			opp = Direction.UP;
			break;
		}
		return opp;
	}
}
