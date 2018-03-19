package Model;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {

	    Skeleton s1 = new Skeleton();

        Scanner reader = new Scanner(System.in);
        int option =0;
        while(option != 17) {
            System.out.println("1. Munkás üres mezőre lép\n" +
                    "2. Munkást falnak megy\n" +
                    "3. Munkás meghal lyukba esik\n" +
                    "4. Munkás1 meghal Munkás2 falhoz nyomja\n" +
                    "5. Munkás üres mezőre dobozt tol\n" +
                    "6. Munkás kettő elemű dobozláncot tol\n" +
                    "7. Munkás lyukba tolja a ládát\n" +
                    "8. Munkás váltóra tolja a ládát\n" +
                    "9. Munkás pontot kap\n" +
                    "10.Munkás váltóra lép\n" +
                    "11.Munkás inaktív lyukra lép\n" +
                    "12.Munkás célmezőre lép\n" +
                    "13.Munkás mozdíthatatlan dobozt tol\n" +
                    "14.Munkás inaktív lyukra dobozt tol\n" +
                    "15.Munkás1 meghal Munkás2 lyukba löki egy dobozzal\n" +
                    "16. Kilépés");
            option = reader.nextInt();


            switch (option) {
                case 1:
                    s1.WorkerStepToEmptyField();
                    break;
                case 2:
                    s1.WorkerStepToWall();
                    break;
                case 3:
                    s1.WorkerDie1FallIntoSwitchHoleField();
                    break;
                case 4:
                    s1.Worker1Die2PushedToWallByWorker2();
                    break;
                case 5:
                    s1.WorkerPushBoxToEmptyField();
                    break;
                case 6:
                    s1.WorkerPushBoxesToEmptyField();
                    break;
                case 7:
                    s1.WorkerPushBoxIntoSwitchHoleField();
                    break;
                case 8:
                    s1.WorkerPushBoxToSwitchField();
                    break;
                case 9:
                    s1.WorkerGetPoint();
                    break;
                case 10:
                    s1.WorkerStepToSwitchField();
                    break;
                case 11:
                    s1.WorkerStepToInactiveSwitchHoleField();
                    break;
                case 12:
                    s1.WorkerStepToGoalField();
                    break;
                case 13:
                    s1.WorkerPushBlockedBox();
                    break;
                case 14:
                    s1.WorkerPushBoxToInactiveSwitchHoleField();
                    break;
                case 15:
                    s1.Worker1Die3PushedIntoSwitchHoleFieldByWorker2WithBox();
                    break;
                default:
                    break;
            }

        }
        reader.close();
        System.exit(0);

	}
}
