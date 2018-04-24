package Model;

import java.util.HashMap;
import java.util.Map;

/**
 * A rakt�r�p�let egy n�gyzet�t reprezent�lja,
 * amely k�pes t�rolni egy l�d�t, vagy �llhat rajta egy munk�s,
 * illetve vannak speci�lis v�ltozatai.
 * Ismeri a k�r�l�tte l�v� mez�ket.
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
	 * Be�ll�tja d ir�nyba az f mez�t szomsz�dnak.
	 * @param d az ir�ny, amerre a szomsz�dot be�ll�tjuk
	 * @param f a mez�, ami a szomsz�d lesz
	 */
	public void setNeighbour(Direction d, Field f) {
		fields.put(d, f);
	}
	
	/**
	 * Visszaadja a d ir�nyban l�v� szomsz�d mez�t.
	 * @param d az ir�ny, amerre a mez� vanfile:///home/jeno/eclipse-workspace/.metadata/.plugins/org.eclipse.jdt.ui/jdt-images/0.png
	 * @return d  ir�nyban l�v� szomsz�d mez�
	 */
	public Field getNeighbour(Direction d) {
		return fields.get(d);
	}
	
	/**
	 *  Ez a f�ggv�ny ellen�rzi, hogy a param�terk�nt kapott munk�st
	 *  be tudja-e fogadni a mez�. Ha m�r van rajta valami,
	 *  akkor megh�vja a rajta l�v� thing pushed met�dus�t.
	 *  Amennyiben a mez� befogadja a param�terk�nt kapott Worker objektumot,
	 *  az el�z� mez�r�l elt�vol�t�dik �s a jelenlegi mez�re ker�l.
	 * @param w a munk�s, aki szeretne a mez�re l�pni
	 * @param d az ir�ny, amelyikbe a munk�s menni szeretne
	 * @return igazzal t�r vissza, ha tudja fogadni, egy�bk�nt hamissal
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
	 *  Ez a f�ggv�ny ellen�rzi, hogy a param�terk�nt kapott l�d�t
	 *  be tudja-e fogadni a mez�. Ha m�r van rajta valami,
	 *  akkor megh�vja a rajta l�v� thing pushed met�dus�t.
	 *  Amennyiben a mez� befogadja a param�terk�nt kapott Box objektumot,
	 *  az el�z� mez�r�l elt�vol�t�dik �s a jelenlegi mez�re ker�l.
	 * @param w a munk�s, aki szeretne a mez�re l�pni
	 * @param d az ir�ny, amelyikbe a munk�s menni szeretne
	 * @return igazzal t�r vissza, ha tudja fogadni, egy�bk�nt hamissal
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
	 * A rajta l�v� thingnek h�vja a pontad�s�rt felel�s met�dus�t.
	 * @param d az ir�ny, amerre van az adott thing
	 */
	public void addPointToThing(Direction d) {
		if (thing != null) {
			thing.addPoint(d);			
		}
	}
	
	/**
	 * Hozz�adja a mez�h�z a param�terk�nt kapott thing objektumot.
	 * @param t egy Thing, ami a mez�re l�p
	 */
	public void addThing(Thing t) {
		thing = t;
	}
	
	/**
	 * Elt�vol�tja a rajta l�v� thinget a mez�r�l.
	 */
	public void removeThing() {
		thing = null;
	}
	
	/**
	 * Megadja egy ir�nynak az ellentettj�t
	 * @param d az ir�ny, aminek keress�k az ellentettj�t
	 * @return a d ir�ny ellentettje
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
			DrawFriction();
		} else {
			System.out.print(" ");
			DrawFriction();
		}
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
