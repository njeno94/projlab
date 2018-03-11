package Model;




public class Skeleton {

//pálya szerkezet---------
		Warehouse raktar = new Warehouse();
		Worker w1 = new Worker();
		Worker w2 = new Worker();
		Box b1 = new Box();
		Box b2 = new Box();
		Field f1 = new Wall();
		Field f2 = new GoalField();
		Field f3 = new Field();
		Field f4 = new Field();
		Field f5 = new SwitchHoleField();
		Field f6 = new SwitchField();
		Game game = new Game();
		



	
	Skeleton(){

		game.addField(f1);
		game.addField(f2);
		game.addField(f3);
		game.addField(f4);
		game.addField(f5);
		game.addField(f6);
	}

	public void SetFields(){
		//// Field-ek beállítása
		f1.setNeighbour(Direction.RIGHT, f2);
		f2.setNeighbour(Direction.LEFT, f1);
		f2.setNeighbour(Direction.RIGHT, f3);
		f3.setNeighbour(Direction.LEFT, f2);
		f3.setNeighbour(Direction.RIGHT, f4);
		f4.setNeighbour(Direction.LEFT, f3);
		f4.setNeighbour(Direction.RIGHT, f5);
		f5.setNeighbour(Direction.LEFT, f4);
		f5.setNeighbour(Direction.RIGHT, f6);
		f6.setNeighbour(Direction.LEFT, f5);
		
		//// Kapcsoló hozzáadása
		f6.setSwitchHoleField(f5);

	}
//---------------------


//tesztek-------------
	public void WorkerStepToEmptyField(){}

	public void WorkerStepToWall(){}

	public void WorkerDie1_SwithHoleField(){}

	public void WorkerDie2_Wall(){}

	public void WorkerPushBoxToEmptyField(){}

	public void WorkerPushBoxesToEmptyField(){}

	public void WorkerPushBoxIntoSwitchHoleField(){}

	public void WorkerPushBoxToSwitchField(){}

	public void WorkerGetPoint(){}

	public void WorkerWinTheGame(){}


//---------------




}