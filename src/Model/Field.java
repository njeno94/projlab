package Model;

import java.util.HashMap;
import java.util.Map;

/**
 * A raktárépület egy négyzetét reprezentálja,
 * amely képes tárolni egy ládát, vagy állhat rajta egy munkás,
 * illetve vannak speciális változatai.
 * Ismeri a körülötte lévő mezőket.
 */
public class Field {
	protected Thing thing;
	private Map<Direction, Field> fields;
	private static final int frictionAtStart = 1;
	private int currentFriction;
	/**
	 * konstruktor
	 */
	public Field() {
		thing = null;
		fields = new HashMap<Direction, Field>();
		currentFriction = frictionAtStart;
	}
	
	/**
	 * Beállítja d irányba az f mezőt szomszédnak.
	 * @param d az irány, amerre a szomszádot beállítjuk
	 * @param f a mező, ami a szomszéd lesz
	 */
	public void setNeighbour(Direction d, Field f) {
		fields.put(d, f);
	}
	
	/**
	 * Visszaadja a d irányban lévő szomszéd mezőt.
	 * @param d az irány, amerre a mező van
	 * @return d  irányban lévő szomszéd mező
	 */
	public Field getNeighbour(Direction d) {
		return fields.get(d);
	}
	
	/**
	 *  Ez a függvény ellenőrzi, hogy a paraméterként kapott munkást
	 *  be tudja-e fogadni a mező. Ha már van rajta valami,
	 *  akkor meghívja a rajta lévő thing pushed metódusát.
	 *  Amennyiben a mező befogadja a paraméterként kapott Worker objektumot,
	 *  az előző mezőről eltávolítódik és a jelenlegi mezőre kerül.
	 * @param w a munkás, aki szeretne a mezőre lépni
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
	 *  Ez a függvény ellenőrzi, hogy a paraméterként kapott ládát
	 *  be tudja-e fogadni a mező. Ha már van rajta valami,
	 *  akkor meghívja a rajta lévő thing pushed metódusát.
	 *  Amennyiben a mező befogadja a paraméterként kapott Box objektumot,
	 *  az előző mezőről eltávolítódik és a jelenlegi mezőre kerül.
	 * @param w a munkás, aki szeretne a mezőre lépni
	 * @param d az irány, amelyikbe a munkás menni szeretne
	 * @return igazzal tér vissza, ha tudja fogadni, egyébként hamissal
	 */
	public void accept(Box b, Direction d, int force, int friction) {
		if (force > friction) {
			if (thing == null) {
				b.removeFromField();
				addThing(b);
				b.addField(this);
			}
			else {
				thing.pushed(b, d, force, friction + currentFriction);
				if (thing == null) {
						b.removeFromField();
						addThing(b);
						b.addField(this);						
				}
			}
		}
		
	}
	
	/**
	 * A rajta lévő thingnek hívja a pontadásért felelős metódusát.
	 * @param d az irány, amerre van az adott thing
	 */
	public void addPointToThing(Direction d) {
		if (thing != null) {
			thing.addPoint(d);			
		}
	}
	
	/**
	 * Hozz�adja a mezőhöz a paraméterként kapott thing objektumot.
	 * @param t egy Thing, ami a mezőre lép
	 */
	public void addThing(Thing t) {
		thing = t;
	}
	
	/**
	 * Eltávolítja a rajta lévő thinget a mezőről.
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
	
	public void increaseFriction() {
		currentFriction -= 3;
	}
	
	public void decreaseFriction() {
		currentFriction += 3;
	}
	
	public int getFriction() {
		return currentFriction;
	}
	
	public void Draw() {
		System.out.print("f");
		if (thing != null) {
			thing.Draw();
		} else {
			System.out.print(" ");
		}
		DrawFriction();
	}

	public void DrawFriction(){
		if( currentFriction == frictionAtStart ){
			System.out.print(" ");			
		} else if( currentFriction < frictionAtStart ){
			System.out.print("h");			
		}else if( currentFriction > frictionAtStart ){
			System.out.print("o");		
		}
	}
}
