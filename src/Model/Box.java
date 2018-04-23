package Model;


/**
 * Ez az oszt�ly reprezent�lja a l�d�kat a j�t�kban.
 */
public class Box extends Thing {
	
	/**
	 * A l�d�k tol�s�t val�s�tja meg, amikor munk�s tolja a l�d�t.
	 * @param w a munk�s, aki tolja a l�d�t
	 * @param d az ir�ny, amelyik ir�nyba a l�d�t tolja a munk�s
	 */
	public void pushed(Worker w, Direction d) {
		Field nextField = field.getNeighbour(d);
		
		nextField.accept(this, d, w.getForce(), field.getFriction());

	}
	
	/**
	 * A l�d�k tol�s�t val�s�tja meg, amikor munk�s tolja a l�d�t.
	 * @param b a l�da, amit egy munk�s ennek a l�d�nak a hely�re akar tolni.
	 * @param d az ir�ny, amelyikbe a l�d�t tolni akarj�k
	 */
	public void pushed(Box b, Direction d, int force, int friction) {
		Field nextField = field.getNeighbour(d);
	
		nextField.accept(this, d, force, friction);
	}

	@Override
	public void Draw() {
		System.out.print("b");
	}

}
