package Model;




public class Skeleton {

//p�lya szerkezet---------

		Worker w1 = new Worker();
		Worker w2 = new Worker();
		Box b1 = new Box();
		Box b2 = new Box();
		Wall f1 = new Wall();
        Field f2 = new Field();
		Field f3 = new Field();
		Field f4 = new Field();
		Field f5 = new Field();
	    GoalField f6 = new GoalField();
		SwitchField f7 = new SwitchField();
		Field f8 = new Field();
		Field f9 = new Field();
		Field f10 = new Field();
	    SwitchHoleField f11 = new SwitchHoleField();
		Game game = new Game();
		



	
	Skeleton(){

		game.addField(f1);
		game.addField(f2);
		game.addField(f3);
		game.addField(f4);
		game.addField(f5);
		game.addField(f6);
        game.addField(f7);
        game.addField(f8);
        game.addField(f9);
        game.addField(f10);
        game.addField(f11);
	}

	public void SetFields(){
		//// Field-ek be�ll�t�sa
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
        f6.setNeighbour(Direction.RIGHT, f7);
        f7.setNeighbour(Direction.LEFT, f6);
        f7.setNeighbour(Direction.RIGHT, f8);
        f8.setNeighbour(Direction.LEFT, f7);
        f8.setNeighbour(Direction.RIGHT, f9);
        f9.setNeighbour(Direction.LEFT, f8);
        f9.setNeighbour(Direction.RIGHT, f10);
        f10.setNeighbour(Direction.LEFT, f9);
        f10.setNeighbour(Direction.RIGHT, f11);

		f11.setNeighbour(Direction.LEFT, f10);
		
		//// Kapcsol� hozz�ad�sa
		f7.setSwitchHoleField(f11);

	}
	public void Reset(){
	    w1.disappear();
	    w2.disappear();
	    b1.disappear();
	    b2.disappear();
	    f11.setState(false);
    }

//---------------------


//tesztek-------------
	public void WorkerStepToEmptyField(){
	    f3.addThing(w1);
	    w1.addField(f3);
	    w1.step(Direction.RIGHT);
	    Reset();

    }

	public void WorkerStepToWall(){
        f2.addThing(w1);
        w1.addField(f2);
        w1.step(Direction.LEFT);
        Reset();
    }

	public void WorkerDie1FallIntoSwitchHoleField(){
	    f11.setState(true);
	    f10.addThing(w1);
	    w1.addField(f10);
	    w1.step(Direction.RIGHT);
	    Reset();
    }

	public void Worker1Die2PushedToWallByWorker2(){
        f2.addThing(w1);
        w1.addField(f2);
        f3.addThing(b1);
        b1.addField(f3);
        f4.addThing(w2);
        w2.addField(f4);
        w2.step(Direction.LEFT);
        Reset();
    }

	public void WorkerPushBoxToEmptyField(){
        f2.addThing(w1);
        w1.addField(f2);
        f3.addThing(b1);
        b1.addField(f3);
        w1.step(Direction.RIGHT);
        Reset();
    }

	public void WorkerPushBoxesToEmptyField(){
        f2.addThing(w1);
        w1.addField(f2);
        f3.addThing(b1);
        b1.addField(f3);
        f4.addThing(b2);
        b2.addField(f4);
        w1.step(Direction.RIGHT);
        Reset();
    }

	public void WorkerPushBoxIntoSwitchHoleField(){
        f11.setState(true);
        f9.addThing(w1);
        w1.addField(f9);
        f10.addThing(b1);
        b1.addField(f10);
        w1.step(Direction.RIGHT);
        Reset();
    }

	public void WorkerPushBoxToSwitchField(){
        f9.addThing(w1);
        w1.addField(f9);
        f8.addThing(b1);
        b1.addField(f8);
        w1.step(Direction.LEFT);
        Reset();
    }

	public void WorkerGetPoint(){
        f4.addThing(w1);
        w1.addField(f4);
        f5.addThing(b1);
        b1.addField(f5);
        w1.step(Direction.RIGHT);
    }

    public void WorkerStepToSwitchField(){
        f8.addThing(w1);
        w1.addField(f8);
        w1.step(Direction.LEFT);
        Reset();

    }

    public void WorkerStepToInactiveSwitchHoleField(){
        f10.addThing(w1);
        w1.addField(f10);
        w1.step(Direction.RIGHT);
        Reset();

    }
    public void WorkerStepToGoalField(){
        f5.addThing(w1);
        w1.addField(f5);
        w1.step(Direction.RIGHT);
        Reset();

    }

    public void WorkerPushBlockedBox(){
        f3.addThing(w1);
        w1.addField(f3);
        f2.addThing(b1);
        b1.addField(f2);
        w1.step(Direction.LEFT);
        Reset();
    }

    public void WorkerPushBoxToInactiveSwitchHoleField(){
        f9.addThing(w1);
        w1.addField(f9);
        f10.addThing(b1);
        b1.addField(f10);
        w1.step(Direction.RIGHT);
        Reset();
    }
    public void WorkerActiveteTheHoleFieldAndFallIntoTheHall() {
        f10.addThing(w1);
        w1.addField(f10);
        f9.addThing(b1);
        b1.addField(f9);
        w1.step(Direction.LEFT);
        w1.step(Direction.RIGHT);
        w1.step(Direction.RIGHT);
        Reset();
    }

    public void Worker1Die3PushedIntoSwitchHoleFieldByWorker2WithBox() {
        f11.setState(true);
        f10.addThing(w1);
        w1.addField(f10);
        f9.addThing(b1);
        b1.addField(f9);
        f8.addThing(w2);
        w2.addField(f8);
        w2.step(Direction.RIGHT);
        Reset();
    }





//---------------




}