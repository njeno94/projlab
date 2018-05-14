package Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import View.ThingView;
import View.View;

/**
 * Ez a singleton felelős a játék irányításáért. Ő valósítja meg a raktár épületet.
 */
public class Game {
	private List<Field> raktarepulet;
	private List<GoalField> celmezok;
	private List<Box> dobozok;
	private boolean firstWorkerSetted;
	private boolean twoPlayerMode;
	public static int width = 520;
	private Worker w1;
	private Worker w2;
	
	private View view;
	/**
	 * Konstruktor
	 */
	public Game(View v) {
		view = v;
		
		raktarepulet = new ArrayList<Field>();
		celmezok = new ArrayList<GoalField>();
		dobozok = new ArrayList<Box>();
		w1 = new Worker("w1");
		w2 = new Worker("w2");
	}
	
	public void addField(Field f) {
		raktarepulet.add(f);
	}
	/**
	 * Elindítja a játékot, inicializálja a pályát,
	 * ládákat és munkások kezdőpozícióját
	 */
	public void startGame(boolean twoPlayerMode){
		raktarepulet.clear();
		dobozok.clear();
		celmezok.clear();
		firstWorkerSetted = false;
		this.twoPlayerMode = twoPlayerMode;
	}
	
	/**
	 *  Megvizsgálja, hogy a játék véget ért-e
	 * @return Igaz ha vége, egyébként hamis
	 */
	public boolean checkGameEnd() {
		if (checkGoalFields() || !checkWorkerDeadlock() || !checkBoxDeadlock()) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean checkWorkersAlive() {
		return (w1.isPlaying() && w2.isPlaying());
	}
	
	/**
	 * Ellenőrzi a munkásokat, hogy tudnak-e még érdemi lépést tenni.
	 * @return igaz, ha van olyan munkás, aki tud lépni, egyébként hamis
	 */
	public boolean checkWorkerDeadlock() {
		if (w1.canStep() || w2.canStep()) {
			return true;
		}
		return false;
	}
	/**
	 * Ellenőrzi,hogy a ládák mozgathatók-e.
	 * @return igaz, ha van mozgatható láda, egyébként hamis
	 */
	public boolean checkBoxDeadlock() {
		int disappeared = 0;
		for (Box b : dobozok) {
			if (b.disappeared()) {
				disappeared++;
			}
		}
		if ((dobozok.size() - disappeared) < celmezok.size()) {
			return false;
		}
		
		for (Box b : dobozok) {
			if (b.canBePushed()  && !b.isReachedToGoalField()) {
				return true;
			}
		}
		return false;
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
	
	public void setMap(String fileName) {
	
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
                			Wall w = new Wall();
                			view.createWallView(w);
                			f = w;
                			break;
                		case 'h':
                			Hole h = new Hole();
                			view.createHoleView(h);
                			f = h;
                			break;
                		case 's':
                			SwitchField sf = new SwitchField();
                			switchfields.add(sf);
                			view.createSwitchFieldView(sf);
                			f = sf;
                			break;
                		case 't':
                			SwitchHoleField shf = new SwitchHoleField();
							switchholefields.add(shf);
							view.createSwitchHoleFieldView(shf);
							f=shf;
                			break;
                		case 'g':
                			GoalField g = new GoalField();
                			celmezok.add(g);
                			view.createGoalFieldView(g);
                			f=g;
                			break;
                		case 'f':
                			f = new Field();
                			view.createFieldView(f);
                			break;
                	}

                	if (fields[i].length() > 1) {
                		switch(fields[i].charAt(1)) {
                		case 'w':
                			if (!firstWorkerSetted) {
                    			f.addThing(w1);
                    			w1.addField(f);
                    			firstWorkerSetted = true;
                    			view.addThingView(new ThingView(w1, "w1"));
                			} else {
                				if (twoPlayerMode) {
                					f.addThing(w2);
                					w2.addField(f);
                					view.addThingView(new ThingView(w2, "w2"));                					
                				}
                			}
                			
                			break;
                		case 'b':
                			Box b = new Box();
                			f.addThing(b);
                			b.addField(f);
                			dobozok.add(b);
                			view.addThingView(new ThingView(b));
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
        
		width = fields.length;
		view.setColumns(width);
		if( !switchfields.isEmpty()  && !switchholefields.isEmpty()){
        	setSwitchField(switchfields,switchholefields);
	}
        setNeighbours();
	}
	
	public void setNeighbours() {
		for (int i=0; i < raktarepulet.size() - width; i++) {
			raktarepulet.get(i).setNeighbour(Direction.DOWN, raktarepulet.get(i+10));
		}
		
		for (int i=raktarepulet.size() - 1; i >= width; i--) {
			raktarepulet.get(i).setNeighbour(Direction.UP, raktarepulet.get(i-10));
		}
		
		for (int i=0; i < raktarepulet.size() - 1; i++) {
			raktarepulet.get(i).setNeighbour(Direction.RIGHT, raktarepulet.get(i+1));
		}
		
		for (int i=raktarepulet.size() - 1; i > 0; i--) {
			raktarepulet.get(i).setNeighbour(Direction.LEFT, raktarepulet.get(i-1));
		}
	}
	
	public void setSwitchField(ArrayList<SwitchField> swlist, ArrayList<SwitchHoleField> swhlist) {
		for( SwitchField sw : swlist){
			sw.setSwitchHoleField(swhlist.get(swlist.indexOf(sw)));
		}
	}
	
	public void moveW1(Direction d) {
		w1.step(d);
	}
	
	public void moveW2(Direction d) {
		if (twoPlayerMode)
			w2.step(d);
	}
	
	public void oilW1(String oilType) {
		switch(oilType) {
		case "o":
			w1.oilFieldWithOil();
			break;
		case "h":
			w1.oilFieldWithHoney();
			break;
		}
	}
	
	public void oilW2(String oilType) {
		switch(oilType) {
		case "n":
			w2.oilFieldWithOil();
			break;
		case "m":
			w2.oilFieldWithHoney();
			break;
		}
	}

	public List<Field> getWareHouse(){
		return raktarepulet;
	}
	
	public int[] getWorkerPoints() {
		int[] points = {w1.getPoint(), w2.getPoint()};
		return points;
	}
}
