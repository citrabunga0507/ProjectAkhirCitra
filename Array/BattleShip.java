package Array;
import java.util.Scanner;
public class BattleShip {
    public static void main(String[] args) {
        Scanner game = new Scanner(System.in);

        //deklarasi data
        int win = 0;
        int turn = 1;
        int placeship = 0;
        int shipA = 0;
        int shipB = 0;
        int bomb = 0;
        int radar = 0;

        //opening game
        System.out.println("=======================================");
        System.out.println("              BATTLE SHIP              ");
        System.out.println("=======================================");
        System.out.print("Box Size (5-9)\t: ");

        //nextInt ditutup dengan nextLine
        int box = game.nextInt();game.nextLine();
        System.out.println("\n");

        //membuat array untuk isi data boxnya nantI
        String status[][] = new String[box][box];
        int sea[][] = new int[box][box];
        String colloum[] = {"A","B","C","D","E","F","G","H","I"};

        //mengisi array untuk boxbattle

        //randomisasi lokasi kapal menggunakan Math.random
        do{
            for(int i = 0; i < box-2; i++){
                for(int j = 0; j < box - 3; j++){
                    int summon = (int)(Math.random()*50);
                    //jika nilai summonnya adalah 1, maka akan menjadi titik dimana kapal akan di summon.
                    if(summon==1){
                        sea[i][j] = 1;sea[i][j+1] = 1;
                        sea[i+1][j] = 1;sea[i+1][j+1] = 1;
                        sea[i+2][j] = 1;sea[i+2][j+1] = 1;
                        placeship = 1;
                        shipA = i;
                        shipB = j;
                        i = box + 10;
                        j = box + 10;
                    }
                }
            }
        }while(placeship==0);

        for(int i = 0; i < box; i++){
            for(int j = 0; j < box; j++){
                status[i][j] = "[ ]";
            }
        }


        //mengulang game sampai win atau game over
        do{

            System.out.println("\n\n\n\n");
            System.out.println("Game " + turn);

            BattleBox(box, colloum, status);

            //Milih Alat radar atau bom
            System.out.println("What do you want to use?");
            System.out.println("(B)omb");
            if(turn < 5){
                System.out.println("(R)adar");
            }
            else{
                System.out.println("(R)adar |You can't use this because this is your last chance !|");
            }
            System.out.print("Choose (r/R/b/B) : ");

            //next ditutup dengan nextLine supaya tidak mengambil data inputan setelahnya
            String tool = game.next();game.nextLine();
            System.out.println("=======================================");

            //seleksi alat yang dipake menggunakan switch case sesuai inputan
            switch (tool) {
                case "b":
                case "B":
                    bomb++;
                    System.out.println("Using Bomb");
                    System.out.println("Where do you want to throw the bomb?");
                    System.out.print("Coordinate (example C5): ");
                    String ABCBomb = game.nextLine();

                    int bBomb = 0;
                    int aBomb = (Integer.parseInt(ABCBomb.substring(1))-1);

                    switch (ABCBomb.substring(0,1)) {
                        case "A":
                            bBomb = 0;
                            break;
                        case "B":
                            bBomb = 1;
                            break;
                        case "C":
                            bBomb = 2;
                            break;
                        case "D":
                            bBomb = 3;
                            break;
                        case "E":
                            bBomb = 4;
                            break;
                        case "F":
                            bBomb = 5;
                            break;
                        case "G":
                            bBomb = 6;
                            break;
                        case "H":
                            bBomb = 7;
                            break;
                        case "I":
                            bBomb = 8;
                            break;
                        case "J":
                            bBomb = 9;
                        default:

                            break;
                    }

                    if(sea[aBomb][bBomb]==1){
                        System.out.println("You Destroy The Ship, Great Work!");
                        win = 1;
                    }
                    else if(sea[aBomb][bBomb]!=1){
                        status[aBomb][bBomb]="[x]";
                    }
                    turn++;
                    break;

                case "r":
                case "R":
                    radar++;
                    System.out.println("Using Radar");
                    System.out.println("Where do you want to scan the area?");
                    System.out.print("Coordinate (example C5): ");
                    String ABCRadar = game.nextLine();

                    int bRadar = 0;
                    int aRadar = (Integer.parseInt(ABCRadar.substring(1))-1);

                    switch (ABCRadar.substring(0,1)) {
                        case "A":
                            bRadar = 0;
                            break;
                        case "B":
                            bRadar = 1;
                            break;
                        case "C":
                            bRadar = 2;
                            break;
                        case "D":
                            bRadar = 3;
                            break;
                        case "E":
                            bRadar = 4;
                            break;
                        case "F":
                            bRadar = 5;
                            break;
                        case "G":
                            bRadar = 6;
                            break;
                        case "H":
                            bRadar = 7;
                            break;
                        case "I":
                            bRadar = 8;
                            break;
                        case "J":
                            bRadar = 9;
                        default:

                            break;
                    }
                    int detect = 0;
                    for(int i = aRadar-1; i <= aRadar+1; i++){
                        for(int j = bRadar-1; j <= bRadar+1; j++){
                            if(i!=-1&&i!=box&&j!=-1&&j!=box){
                                if(sea[i][j]==1){
                                    System.out.println("There is enemy ship around!");
                                    i *= 35;
                                    j *= 35;
                                    detect++;
                                }
                            }
                        }
                    }
                    if(detect==0){
                        System.out.println("There is no enemy ship nearby!");
                    }
                    //TURN AKAN BERTAMBAH SETIAP AKHIR LOOP DO WHILE, DAN AKAN LANJUT KE ROUND/ GAME SELANJUTNYA
                    turn++;
                    break;

                default:

                    break;
            }

            System.out.println("Press ENTER to continue...");
            game.nextLine();
        }while(win==0&&turn<6);

        if(win == 1){
            System.out.println("\n\n\n\n\n=======================================\nCongratulations!!!");
        }
        else{
            System.out.println("\n\n\n\n\n=======================================\n        !GAME OVER!");
        }

        status[shipA][shipB] = "{  ";status[shipA][shipB+1] = "  }";
        status[shipA+1][shipB] = "{  ";status[shipA+1][shipB+1] = "  }";
        status[shipA+2][shipB] = "{  ";status[shipA+2][shipB+1] = "  }";
        BattleBox(box, colloum, status);
        if (win==1) {
            System.out.println("=======================================");
            System.out.println("                 SCORE                 ");
            System.out.println("=======================================");
            System.out.printf("Bomb(%s)\t\t\t\t\t%5s",bomb,(5-bomb)*100);
            System.out.printf("\nRadar(%s)\t\t\t\t%5s",radar,radar*-100);
            System.out.printf("\nDifficulty(%s)\t\t\t\t%5s",box,box*50);
            System.out.printf("\nTotal Score\t\t\t\t%5s",(((5-bomb)*100)+(radar*-100)+(box*50)*win));
            System.out.println("\n=======================================");
        }
    }

    public static void BattleBox(int box, String colloum[], String status[][]) {
        for(int i = 0; i <= box; i++){
            if(i > 0){
                System.out.printf("%3s",colloum[i-1]);
            }
            else{
                System.out.print(" X");
            }
        }

        System.out.println();
        for(int i = 0; i < box; i++){
            System.out.printf(" %s ",i+1);
            for(int j = 0; j < box; j++){
                System.out.print(status[i][j]);
            }
            System.out.println();
        }
    }
}