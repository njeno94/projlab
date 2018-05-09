package Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Ez a singleton felelős a játék irányításáért. Ő valósítja meg a raktár épületet.
 */
public class Game {
	private ArrayList<Field> raktarepulet;
	private ArrayList<GoalField> celmezok;
	private boolean firstWorkerSetted;
	private int width;
	
	/**
	 * Konstruktor
	 */
	Game() {
		raktarepulet = new ArrayList<Field>();
		celmezok = new ArrayList<GoalField>();
		firstWorkerSetted = false;
	}
	
	public void addField(Field f) {
		raktarepulet.add(f);
	}
	/**
	 * Elindítja a játékot, inicializálja a pályát,
	 * ládákat és munkások kezdőpozícióját
	 */
	public void startGame(){
		
	}
	
	/**
	 *  Megvizsgálja, hogy a játék véget ért-e
	 * @return Igaz ha vége, egyébként hamis
	 */
	public boolean checkGameEnd() {
		if (!checkGoalFields()) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Ellenőrzi a munkásokat, hogy tudnak-e még érdemi lépést tenni.
	 * @return igaz, ha van olyan munkás, aki tud lépni, egyébként hamis
	 */
	public boolean checkWorkerDeadlock() {

		return true;
	}
	/**
	 * Ellenőrzi,hogy a ládák mozgathatók-e.
	 * @return igaz, ha van mozgatható láda, egyébként hamis
	 */
	public boolean checkBoxDeadlock() {

		return true;
	}
	
	/**
	 * Ellenőrzi, hogy van-e még pontot érő célmező.
	 * @return igaz, ha van, egyébként hamis
	 */
	public boolean checkGoalFields() {
		int count = 0;
		for (GoalField g : celmezok) {
			if (g.isBoxReached()) {
				count++;
			}
		}
		if (count == celmezok.size()) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * Leállítja és befejezi a játékot.
	 */
	public static void endGame() {
		System.exit(0);
	}
	
	public void setMap(Worker w1, Worker w2, String fileName) {
	
		ArrayList<SwitchField> switchfields = new ArrayList<SwitchField>();
		ArrayList<SwitchHoleField> switchholefields = new ArrayList<SwitchHoleField>();
			
		String line = "";
		String[] fields = null;
	
	        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
	            while ((line = br.readLine()) != null) {
	                fields = line.split(";");
	                
	                for (int i = 0; i < fields.length; i++) {
	                	Field f = null;
	                	switch(fields[i].charAt(0)) {
	                		case 'w':
	                			f = new Wall();
	                			break;
	                		case 'h':
	                			f = new Hole();
	                			break;
	                		case 's':
	                			SwitchField sf = new SwitchField();
						switchfields.add(sf);
						f=sf;
	                			break;
	                		case 't':
	                			SwitchHoleField shf = new SwitchHoleField();
						switchholefields.add(shf);
						f=shf;
	                			break;
	                		case 'g':
						GoalField g = new GoalField();
	                			celmezok.add(g);
						f=g;
	                			break;
	                		case 'f':
	                			f = new Field();
	                			break;
	                	}
	
	                	if (fields[i].length() > 1) {
	                		switch(fields[i].charAt(1)) {
	                		case 'w':
	                			if (!firstWorkerSetted) {
	                    				f.addThing(w1);
	                    				w1.addField(f);
	                    				firstWorkerSetted = true;
	                			} else {
	                    				f.addThing(w2);
	                    				w2.addField(f);;
	                			}
	                			
	                			break;
	                		case 'b':
	                			Box b = new Box();
	                			f.addThing(b);
	                			b.addField(f);
	                			break;
	                		}
	                	}
	                	
	                		if (fields[i].length() > 2) {                		
	                		switch(fields[i].charAt(2)) {
	                		case 'o':
	                			f.increaseFriction();
	                			break;
	                		case 'h':
	                			f.decreaseFriction();
	                			break;
	                		}
	                	}
	                	if (f != null)
	                		raktarepulet.add(f);
	                }
	            }
	
	        } catch (IOException e) {
	        	//
	        }
	        
		width=fields.length;
		if( !switchfields.isEmpty()  && !switchholefields.isEmpty()){
	        	setSwitchField(switchfields,switchholefields);
		}
	        setNeighbours();
	}
	
	public void setNeighbours() {
		for (int i=0; i < raktarepulet.size() - width; i++) {
			raktarepulet.get(i).setNeighbour(Direction.DOWN, raktarepulet.get(i + width));
		}
		
		for (int i=raktarepulet.size() - 1; i >= width; i--) {
			raktarepulet.get(i).setNeighbour(Direction.UP, raktarepulet.get(i - width));
		}
		
		for (int i=0; i < raktarepulet.size() - 1; i++) {
			raktarepulet.get(i).setNeighbour(Direction.RIGHT, raktarepulet.get(i + 1));
		}
		
		for (int i=raktarepulet.size() - 1; i > 0; i--) {
			raktarepulet.get(i).setNeighbour(Direction.LEFT, raktarepulet.get(i -1));
		}
	}
	
	public void setSwitchField(ArrayList<SwitchField> swlist, ArrayList<SwitchHoleField> swhlist) {
		for( SwitchField sw : swlist){
			sw.setSwitchHoleField(swhlist.get(swlist.indexOf(sw)));
		}
	}
	
	public void showWareHouse() {
		for (int i=0; i<raktarepulet.size(); i++) {
			System.out.print("|");
			raktarepulet.get(i).Draw();
			if ((i + 1 )% width == 0) {
				System.out.print("|");				
				System.out.println();
			}
		}
	}
}
