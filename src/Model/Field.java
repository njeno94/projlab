package Model;

import java.util.HashMap;
import java.util.Map;
import View.ThingView;

/**
 * A raktárépület egy négyzetét reprezentálja,
 * amely képes tárolni egy ládát, vagy állhat rajta egy munkás,
 * illetve vannak speciális változatai.
 * Ismeri a körülötte lévõ mezõket.
 */
public class Field {
	protected Thing thing;
	private Map<Direction, Field> fields;
	public static final int frictionAtStart = 1;
	protected int currentFriction;
	protected boolean opened;
	protected boolean boxReached;
	/**
	 * konstruktor
	 */
	public Field() {
		thing = null;
		fields = new HashMap<Direction, Field>();
		currentFriction = frictionAtStart;
		opened = true;
		boxReached = false; //Csak a GoalFieldben használjuk, viszont a Box felõl mindenkit Fieldként kezelünk.
	}
	
	/**
	 * Beállítja d irányba az f mezõt szomszédnak.
	 * @param d az irány, amerre a szomszádot beállítjuk
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
		pushThingIfExists(w, d);	
		if (thing == null) {
			moveThingToCurrentField(w);
			return true;
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
	public void accept(Box b, Direction d, int force, int friction) {
		if (force > friction) {
			pushThingIfExists(b, d, force, friction + currentFriction);
			if (thing == null) {
				moveThingToCurrentField(b);
				opened = false;
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
		opened = true;
	}
	
	/**
	 * Megadja egy iránynak az ellentettjét
	 * @param d az irány, aminek keressük az ellentettjét
	 * @return a d irány ellentettje
	 */
	public static Direction convertDir(Direction d) {
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
		currentFriction -= 2;
	}
	
	public void decreaseFriction() {
		currentFriction += 2;
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

	public void DrawFriction() {
		if( currentFriction == frictionAtStart ){
			System.out.print(" ");		
		} else if( currentFriction < frictionAtStart ){
			System.out.print("h");			
		} else if( currentFriction > frictionAtStart ){
			System.out.print("o");		
		}
	}
	
	protected void moveThingToCurrentField(Thing t) {
		t.removeFromField();
		addThing(t);
		t.addField(this);
	}
	
	protected void pushThingIfExists(Box b, Direction d, int force, int friction){
		if (thing != null) {
			thing.pushed(b, d, force, friction);
		}
	}
	
	protected void pushThingIfExists(Worker w, Direction d){
		if (thing != null) {
			thing.pushed(w, d);
		}
	}
	
	public boolean isOpened() {
		return opened;
	}
	
	public ThingView getActualThingView() {
		if (thing == null) {
			return null;
		}
		return thing.getView();
	}
	
	public boolean isBoxReached() {
		return boxReached;
	}
	
	
}