package Model;

/**
 * Egy speciális Field, amely a raktár épületnek
 * azt a négyzetét reprezentálja, ahová dobozt kell juttatni.
 * Amennyiben toltak rá ládát, a láda nem mozdítható onnan. 
 */
public class GoalField extends Field {

	private boolean boxReached;

	/**
	 * Befogadja a paraméterként kapott Worker objektumot.
	 * Meghívja a a paraméterként kapott Box objektum addPoint() metódusát
	 * ellentétes iránnyal, hogy a lánc végén megkapja a dobozt toló munkás a pontot.
	 * @param w a munkás, aki a mezőre akar lépni
	 * @param d az irány, amerre a munkás mozogni szeretne
	 * @return
	 */
	@Override
	public boolean accept(Worker w, Direction d) {
		if (thing == null) {
			moveThingToCurrentField(w);
			return true;
		}
		else {
			//Box has reached GoalField
		}
		return false;
	}
	
	/**
	 * Befogadja a paraméterként kapott Box objektumot.
	 * Meghívja a a paraméterként kapott Box objektum addPoint() metódusát
	 * ellentétes iránnyal, hogy a lánc végén megkapja a dobozt toló munkás a pontot.
	 * @param b a láda, amit a mezőre akarnak tolni
	 * @param d az irány, amerre a ládát tolni szeretnék
	 * @return
	 */
	@Override
	public void accept(Box b, Direction d, int force, int friction) {
		if (force > friction) {
			if (!boxReached) {
				pushThingIfExists(b, d, force, friction + currentFriction);
				if (thing == null) {
					b.addPoint(convertDir(d));
					moveThingToCurrentField(b);
					boxReached = true;
				}
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
		} else {
			System.out.print(" ");
		}
		DrawFriction();
	}

	public boolean isBoxReached() {
		return boxReached;
	}
	
}
