package Model;

/**
 * A kapcsol� oszt�ly szerep�t t�lti be.
 * A hozz� tartoz� kapcsolhat� lyuk �llapot�t tudja megv�ltoztatni.
 */
public class SwitchField extends Field {
	
	private SwitchHoleField hole;
	
	/**
	 * Befogadja a param�terk�nt kapott Box objektumot.
	 * Megh�vja a hozz�tartoz� SwitchHoleField changeState() met�dus�t.
	 * @param b		a l�da, amit a mez�re akarnak tolni
	 * @param d		az ir�ny, amerre a l�d�t tolni szeretn�k
	 */
	@Override
	public void accept(Box b, Direction d) {
		Skeleton.printCall( Skeleton.getName(this) + 
				".accept(" + 
				Skeleton.getName(b) + "," +
				d.toString() + ")"
		);
		if (thing == null) {
			b.removeFromField();
			addThing(b);
			b.addField(this);
			hole.setState(true);
			hole.changeState();
		}
		else {
			thing.pushed(b, d);
			if (thing == null) {
				b.removeFromField();
				addThing(b);
				b.addField(this);
				hole.setState(true);
				hole.changeState();
			}
		}
		Skeleton.printReturn();
	}
	
	/**
	 *  Be�ll�tja a hozz� tartoz� kapcsolhat� lyukat
	 * @param sh 	a kapcsolhat� lyuk, amit kapcsolni fog tudni
	 */
	public void setSwitchHoleField(SwitchHoleField sh) {
		Skeleton.printCall( Skeleton.getName(this) + 
				".setSwitchHoleField(" + 
				Skeleton.getName(sh) +  ")"
		);
		hole = (SwitchHoleField)sh;
		Skeleton.printReturn();
	}
	
	/**
	 * Elt�vol�tja a p�ly�r�l a rajta l�v� dolgot,
	 * �s kikapcsolja a hozz� tartoz� kapcsolhat� lyukat
	 */
	@Override
	public void removeThing() {
		Skeleton.printCall( Skeleton.getName(this) + 
				".removeThing()"
		);
		thing = null;
		hole.setState(false);
		Skeleton.printReturn();
	}
}