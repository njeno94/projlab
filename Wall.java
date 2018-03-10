package Model;

public class Wall extends Field {
	
	@Override
	public boolean accept(Worker w, Direction d) {
		System.out.println("Nem tud tovább menni, mert fal van!");
		return false;
	}
	
	@Override
	public void accept(Box b, Direction d) {
		System.out.println("Nem lehet tovább tolni a dobozt, mert fal van!");
	}
}
