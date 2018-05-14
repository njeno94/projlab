package Model;

/**
 * A kapcsoló osztály szerepét tölti be.
 * A hozzá tartozó kapcsolható lyuk állapotát tudja megváltoztatni.
 */
public class SwitchField extends Field {
	
	private SwitchHoleField hole;
	private boolean active = false;
	
	/**
	 * Befogadja a paraméterként kapott Box objektumot.
	 * Meghívja a hozzátartozó SwitchHoleField changeState() metódusát.
	 * @param b		a láda, amit a mezőre akarnak tolni
	 * @param d		az irány, amerre a ládátt tolni szeretnék
	 */
	@Override
	public void accept(Box b, Direction d, int force, int friction) {
		if (force > friction) {
			pushThingIfExists(b, d, force, friction + currentFriction);
			if (thing == null) {
				moveThingToCurrentField(b);
				hole.setState(true);
				hole.changeState();
				active = true;
			}			
		}
	}
	
	/**
	 *  Beállítja a hozzá tartozó kapcsolható lyukat
	 * @param sh 	a kapcsolható lyuk, amit kapcsolni fog tudni
	 */
	public void setSwitchHoleField(Field sh) {
		hole = (SwitchHoleField)sh;
	}
	
	/**
	 * Eltávolítja a pályáról a rajta lévő dolgot,
	 * és kikapcsolja a hozzá tartozó kapcsolható lyukat
	 */
	@Override
	public void removeThing() {
		thing = null;
		hole.setState(false);
		active = false;
	}
	
	@Override
	public void Draw() {
		System.out.print("s");
		if (thing != null) {
			thing.Draw();
		} else {
			System.out.print(" ");
		}
		DrawFriction();
	}
	
	public boolean isActive() {
		return active;
	}
}
