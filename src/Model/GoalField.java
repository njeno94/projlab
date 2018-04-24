package Model;

/**
 * Egy speci�lis Field, amely a rakt�r �p�letnek
 * azt a n�gyzet�t reprezent�lja, ahov� dobozt kell juttatni.
 * Amennyiben toltak r� l�d�t, a l�da nem mozd�that� onnan. 
 */
public class GoalField extends Field {
	private boolean boxReached = false;
	/**
	 * Befogadja a param�terk�nt kapott Worker objektumot.
	 * Megh�vja a a param�terk�nt kapott Box objektum addPoint() met�dus�t
	 * ellent�tes ir�nnyal, hogy a l�nc v�g�n megkapja a dobozt tol� munk�s a pontot.
	 * @param w a munk�s, aki a mez�re akar l�pni
	 * @param d az ir�ny, amerre a munk�s mozogni szeretne
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
			//Box has reached GoalField
		}
		return false;
	}
	
	/**
	 * Befogadja a param�terk�nt kapott Box objektumot.
	 * Megh�vja a a param�terk�nt kapott Box objektum addPoint() met�dus�t
	 * ellent�tes ir�nnyal, hogy a l�nc v�g�n megkapja a dobozt tol� munk�s a pontot.
	 * @param b a l�da, amit a mez�re akarnak tolni
	 * @param d az ir�ny, amerre a l�d�t tolni szeretn�k
	 * @return
	 */
	@Override
	public void accept(Box b, Direction d, int force, int friction) {
		if (force > friction) {
			if (thing == null) {
				b.addPoint(convertDir(d));
				b.removeFromField();
				thing = b;
				b.addField(this);
				boxReached = true;
			}
			else {
				//Box has reached GoalField
			}			
		}
	}
	@Override
	public void Draw() {
		System.out.print("g");
		if (thing != null) {
			thing.Draw();
		}
		else {
			System.out.print(" ");
		}
	}
	
	public boolean isBoxReached() {
		return boxReached;
	}
	
}
