package Model;

public class Hole extends Field {
	@Override
	public boolean accept(Worker w, Direction d) {
		System.out.println("A munkás meghalt, beleesett a lyukba!");
		w.disappear();
		return false;
	}
	
	@Override
	public void accept(Box b, Direction d) {
		b.disappear();
		System.out.println("A doboz beleesett a lyukba!");
	}
}
