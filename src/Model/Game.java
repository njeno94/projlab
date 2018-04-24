package Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Ez a singleton felel�s a j�t�k ir�ny�t�s��rt. � val�s�tja meg a rakt�r �p�letet.
 */
public class Game {
	private ArrayList<Field> raktarepulet;
	private ArrayList<GoalField> celmezok;
	private int SHFindex, SFindex;
	private boolean firstWorkerSetted;
	
	/**
	 * Konstruktor
	 */
	Game() {
		raktarepulet = new ArrayList<Field>();
		celmezok = new ArrayList<GoalField>();
		firstWorkerSetted = false;
		SHFindex = -1;
		SFindex = -1;
	}
	
	public void addField(Field f) {
		raktarepulet.add(f);
	}
	/**
	 * Elind�tja a j�t�kot, inicializ�lja a p�ly�t,
	 * l�d�kat �s munk�sok kezd�poz�ci�j�t
	 */
	public void startGame(){
		
	}
	
	/**
	 *  Megvizsg�lja, hogy a j�t�k v�get �rt-e
	 * @return Igaz ha v�ge, egy�bk�nt hamis
	 */
	public boolean checkGameEnd() {
		if (!checkGoalFields()) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Ellen�rzi a munk�sokat, hogy tudnak-e m�g �rdemi l�p�st tenni.
	 * @return igaz, ha van olyan munk�s, aki tud l�pni, egy�bk�nt hamis
	 */
	public boolean checkWorkerDeadlock() {

		return true;
	}
	/**
	 * Ellen�rzi,hogy a l�d�k mozgathat�k-e.
	 * @return igaz, ha van mozgathat� l�da, egy�bk�nt hamis
	 */
	public boolean checkBoxDeadlock() {

		return true;
	}
	
	/**
	 * Ellen�rzi, hogy van-e m�g pontot �r� c�lmez�.
	 * @return igaz, ha van, egy�bk�nt hamis
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
	 * Le�ll�tja �s befejezi a j�t�kot.
	 */
	public static void endGame() {
		System.exit(0);
	}
	
	public void setMap(Worker w1, Worker w2, String fileName) {
		
		String line = "";

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

        	int j = 0;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(";");
                
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
                			f = new SwitchField();
                			SFindex = j;
                			break;
                		case 't':
                			f = new SwitchHoleField();
                			SHFindex = j;
                			System.out.println(SHFindex);
                			break;
                		case 'g':
                			f = new GoalField();
                			celmezok.add((GoalField)f);
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
                	j++; //For the indexes
                }
            }

        } catch (IOException e) {
        	//
        }
        
        setSwitchField(SFindex,SHFindex);
        
        setNeighbours();
	}
	
	public void setNeighbours() {
		for (int i=0; i < raktarepulet.size() - 10; i++) {
			raktarepulet.get(i).setNeighbour(Direction.DOWN, raktarepulet.get(i+10));
		}
		
		for (int i=raktarepulet.size() - 1; i >= 10; i--) {
			raktarepulet.get(i).setNeighbour(Direction.UP, raktarepulet.get(i-10));
		}
		
		for (int i=0; i < raktarepulet.size() - 1; i++) {
			raktarepulet.get(i).setNeighbour(Direction.RIGHT, raktarepulet.get(i+1));
		}
		
		for (int i=raktarepulet.size() - 1; i > 0; i--) {
			raktarepulet.get(i).setNeighbour(Direction.LEFT, raktarepulet.get(i-1));
		}
	}
	
	public void setSwitchField(int SFidx, int SHFidx) {
		SwitchField sf = (SwitchField)raktarepulet.get(SFidx);
		sf.setSwitchHoleField(raktarepulet.get(SHFidx));
	}
	
	public void showWareHouse() {
		for (int i=0; i<raktarepulet.size(); i++) {
			System.out.print("|");
			raktarepulet.get(i).Draw();
			if ((i + 1 )% 10 == 0) {
				System.out.print("|");				
				System.out.println();
			}
		}
	}
}
