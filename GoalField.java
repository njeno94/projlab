package Model;

public class GoalField extends Field {
	public boolean accept(Worker w, Direction d) {
		if (thing == null) {
			w.removeFromField();
			thing = w;
			w.addField(this);
			return true;
		}
		else {
			System.out.println("Ezt worker nem tudja már eltolni, mert célmezõre jutott a láda!");
		}
		return false;
	}
	
	@Override
	public void accept(Box b, Direction d) {
		if (thing == null) {
			b.addPoint(convertDir(d));
			b.removeFromField();
			thing = b;
			b.addField(this);
		}
		else {
			System.out.println("Ezen a célmezõn már van doboz!");
		}
	}
}
