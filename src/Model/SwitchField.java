package Model;

/**
 * A kapcsol� oszt�ly szerep�t t�lti be.
 * A hozz� tartoz� kapcsolhat� lyuk �llapot�t tudja megv�ltoztatni.
 */
public class SwitchField extends Field {
	
	private SwitchHoleField hole;
	private boolean active = false;
	
	/**
	 * Befogadja a param�terk�nt kapott Box objektumot.
	 * Megh�vja a hozz�tartoz� SwitchHoleField changeState() met�dus�t.
	 * @param b		a l�da, amit a mez�re akarnak tolni
	 * @param d		az ir�ny, amerre a l�d�tt tolni szeretn�k
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
	 *  Be�ll�tja a hozz� tartoz� kapcsolhat� lyukat
	 * @param sh 	a kapcsolhat� lyuk, amit kapcsolni fog tudni
	 */
	public void setSwitchHoleField(Field sh) {
		hole = (SwitchHoleField)sh;
	}
	
	/**
	 * Elt�vol�tja a p�ly�r�l a rajta l�v� dolgot,
	 * �s kikapcsolja a hozz� tartoz� kapcsolhat� lyukat
	 */
	@Override
	public void removeThing() {
		thing = null;
		hole.setState(false);
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
