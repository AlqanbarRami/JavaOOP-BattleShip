package com.company;


import java.util.Random;
import java.util.concurrent.TimeUnit;

public class SettingDistributions extends Players  {
    //Those are JUST Colors to console !
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
    public static final String TEXT_YELLOW = "\u001B[33m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BLUE = "\u001B[34m";
    //..........................................//
    //Those what we actually need
    public final int[][]  battleZone = new int[10][10];
    private int totalSubmarines;
    private int totalCruiser;
    private int totalBattleShip;
    private int totalAircraftCarrier;
    private final int[][] firstPlayerShips = new int[10][10];
    private final int[][] secondPlayerShips = new int[10][10];
    private final char[][] firstPlayerHittingHistoryBoard = new char [10][10];
    private final char[][] secondPlayerHittingHistoryBoard = new char [10][10];
    private final int[][] avoidRepeatFirstPlayer = new int [10][10];
    private final int[][] avoidRepeatSecondPlayer = new int[10][10];

    public SettingDistributions() {
    }







    protected int getTotalBattleShip() {
        return totalBattleShip;
    }

    protected void setTotalBattleShip(int totalBattleShip) {
        this.totalBattleShip = totalBattleShip;
    }

    protected int getTotalSubmarines() {
        return totalSubmarines;
    }

    protected void setTotalSubmarines(int totalSubmarines) {
        this.totalSubmarines = totalSubmarines;
    }

    protected int getTotalCruiser() {
        return totalCruiser;
    }

    protected void setTotalCruiser(int totalCruiser) {
        this.totalCruiser = totalCruiser;
    }

    protected int getTotalAircraftCarrier() {
        return totalAircraftCarrier;
    }

    protected void setTotalAircraftCarrier(int totalAircraftCarrier) {
        this.totalAircraftCarrier = totalAircraftCarrier;
    }



// Not necessary I Think ! but to be safe and secure make all element in the 2d array Zero
    // this will help us in distribution
    protected void settingAllPositionToZero() {
        for (int col = 0; col < 10; col++) {
            for (int row = 0; row < 10; row++) {
                battleZone[col][row] = 0;
            }
        }
    }

    // After Distributions Move position from battle zone to each player , number = 1 FirstPlayer and numer = 2 SecondPlayer
    protected void savePositionsPlayers(int number ){
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if(number == 1) {
                    firstPlayerShips[i][j] = battleZone[i][j];
                }
                else if(number == 2){
                    secondPlayerShips[i][j] = battleZone[i][j];
                }
            }
        }
    }

    // Print Position Screen  number = 1 FirstPlayer and numer = 2 SecondPlayer
    protected void printPositions(int number) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (number == 1) {
                    System.out.print( ANSI_WHITE_BACKGROUND + ANSI_BLACK  +"  " + firstPlayerShips[i][j] + "\t" + ANSI_RESET);
                }
                else{
                    System.out.print( ANSI_WHITE_BACKGROUND + ANSI_BLACK  + "  " + secondPlayerShips[i][j] + "\t" + ANSI_RESET);
                }
            }
            System.out.println();
        }

    }

    // History shooting will appear with * instead of numbers
    protected void settingHittingHistoryToSymbol(){
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                firstPlayerHittingHistoryBoard[i][j] = '*';
                secondPlayerHittingHistoryBoard[i][j] = '*';
            }

        }

    }

    //Replace shooting history with X, number = 1 FirstPlayer and numer = 2 SecondPlayer
    protected void findingHittingPositionAndReplace(int number, int row, int column){
        if(number == 1){
            if(firstPlayerHittingHistoryBoard[row][column] == '*'){
                firstPlayerHittingHistoryBoard[row][column] = 'X';
            }
        }
        if(number == 2){
            if(secondPlayerHittingHistoryBoard[row][column] == '*'){
                secondPlayerHittingHistoryBoard[row][column] = 'X';
            }
        }
    }
    // Print Shooting History, number = 1 FirstPlayer and numer = 2 SecondPlayer
    protected void printHistoryHitting(int number) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (number == 1) {
                    System.out.print(ANSI_WHITE_BACKGROUND + ANSI_BLACK + "  " +firstPlayerHittingHistoryBoard[i][j] + "\t" + ANSI_RESET);
                }
                else{
                    System.out.print(ANSI_WHITE_BACKGROUND + ANSI_BLACK + "  " +secondPlayerHittingHistoryBoard[i][j] + "\t" + ANSI_RESET);
                }
            }
            System.out.println(ANSI_RESET);
        }

    }

    protected int getRandomPosition() {
        int randomPosition;
        Random randomPositionColumn = new Random();
        randomPosition = randomPositionColumn.nextInt(8) + 1;
        if (battleZone[randomPosition][randomPosition] == 0) {
            return randomPosition;
        } else {
            getRandomPosition();
        }
        return randomPosition;
    }

    protected void goRight(int typeOfShip) {
        int randomRow = getRandomPosition();
        int randomColumn = getRandomPosition();
        if (typeOfShip == 2) {
            if (getTotalCruiser() == 9) {
                if (battleZone[randomRow][randomColumn + 1] == 0) {
                    battleZone[randomRow][randomColumn] = 2;
                    battleZone[randomRow][randomColumn + 1] = 2;
                    int numberOfSub = getTotalSubmarines();
                    setTotalSubmarines(numberOfSub + 2);
                } else {
                    goLeft(2);
                }
            } else {
                goLeft(2);

            }

        } else if (typeOfShip == 3) {
            if (battleZone[randomRow][randomColumn] == 0) {
                if (battleZone[randomRow][randomColumn + 1] == 0 && randomColumn + 2 <= 9) {
                    if (battleZone[randomRow][randomColumn + 2] == 0) {
                        battleZone[randomRow][randomColumn] = 3;
                        battleZone[randomRow][randomColumn + 1] = 3;
                        battleZone[randomRow][randomColumn + 2] = 3;
                        int numberOfCur = getTotalCruiser();
                        setTotalCruiser(numberOfCur + 3);
                    } else {
                        goLeft(3);
                    }
                } else {
                    goLeft(3);
                }
            }
        }

    }

    protected void goLeft(int typeOfShip) {
        int randomRow = getRandomPosition();
        int randomColumn = getRandomPosition();
        if (typeOfShip == 2) {
            if (battleZone[randomRow][randomColumn] == 0) {
                if (battleZone[randomRow][randomColumn - 1] == 0) {
                    battleZone[randomRow][randomColumn] = 2;
                    battleZone[randomRow][randomColumn - 1] = 2;
                    int numberOfSub = getTotalSubmarines();
                    setTotalSubmarines(numberOfSub + 2);

                } else {
                    goUp(2);
                }
            } else {
                goUp(2);

            }

        } else if (typeOfShip == 3) {
            if (battleZone[randomRow][randomColumn] == 0) {
                if (battleZone[randomRow][randomColumn - 1] == 0 && randomColumn - 2 >= 0) {
                    if (battleZone[randomRow][randomColumn - 2] == 0) {
                        battleZone[randomRow][randomColumn] = 3;
                        battleZone[randomRow][randomColumn - 1] = 3;
                        battleZone[randomRow][randomColumn - 2] = 3;
                        int numberOfCur = getTotalCruiser();
                        setTotalCruiser(numberOfCur + 3);
                    } else {
                        goUp(3);
                    }
                } else {
                    goUp(3);
                }
            }
        }
    }

    protected void goUp(int typeOfShip) {
        int randomRow = getRandomPosition();
        int randomColumn = getRandomPosition();
        if (typeOfShip == 2) {
            if (battleZone[randomRow][randomColumn] == 0) {
                if (battleZone[randomRow - 1][randomColumn] == 0) {
                    battleZone[randomRow][randomColumn] = 2;
                    battleZone[randomRow - 1][randomColumn] = 2;
                    int numberOfSub = getTotalSubmarines();
                    setTotalSubmarines(numberOfSub + 2);
                } else {
                    goDown(2);
                }
            } else {
                goDown(2);

            }
        } else if (typeOfShip == 3) {
            if (battleZone[randomRow][randomColumn] == 0) {
                if (battleZone[randomRow - 1][randomColumn] == 0 && randomRow - 2 >= 0) {
                    if (battleZone[randomRow - 1][randomColumn] == 0 && battleZone[randomRow - 2][randomColumn] == 0) {
                        battleZone[randomRow][randomColumn] = 3;
                        battleZone[randomRow - 1][randomColumn] = 3;
                        battleZone[randomRow - 2][randomColumn] = 3;
                        int numberOfCur = getTotalCruiser();
                        setTotalCruiser(numberOfCur + 3);
                    } else {
                        goDown(3);
                    }
                } else {
                    goDown(3);
                }
            } else {
                goDown(3);
            }
        }
    }

    protected void goDown(int typeOfShip) {
        int randomRow = getRandomPosition();
        int randomColumn = getRandomPosition();
        if (typeOfShip == 2) {
            if (battleZone[randomRow][randomColumn] == 0) {
                if (battleZone[randomRow + 1][randomColumn] == 0) {
                    battleZone[randomRow][randomColumn] = 2;
                    battleZone[randomRow + 1][randomColumn] = 2;
                    int numberOfSub = getTotalSubmarines();
                    setTotalSubmarines(numberOfSub + 2);
                } else {
                    goRight(2);
                }
            } else {
                goRight(2);
            }
        } else if (typeOfShip == 3) {
            if (battleZone[randomRow][randomColumn] == 0) {
                if (battleZone[randomRow + 1][randomColumn] == 0 && randomRow + 2 <= 9) {
                    if (battleZone[randomRow + 1][randomColumn] == 0) {
                        if (battleZone[randomRow + 2][randomColumn] == 0) {
                            battleZone[randomRow][randomColumn] = 3;
                            battleZone[randomRow + 1][randomColumn] = 3;
                            battleZone[randomRow + 2][randomColumn] = 3;
                            int numberOfCur = getTotalCruiser();
                            setTotalCruiser(numberOfCur + 3);
                        } else {
                            goRight(3);
                        }
                    } else {
                        goRight(3);
                    }
                } else {
                    goRight(3);
                }
            } else {
                goRight(3);
            }
        }
    }


    protected void battleShipHorizontal(){
        if(getTotalBattleShip() < 8) {
            Random randomPosition = new Random();
            int randomRow = randomPosition.nextInt(10);
            int randomColumn = randomPosition.nextInt(10);
            if (randomColumn >= 0 && randomColumn <= 5) {
                //Go Right
                if (battleZone[randomRow][randomColumn] == 0) {
                    if(battleZone[randomRow][randomColumn+1] == 0 && battleZone[randomRow][randomColumn+2] == 0 &&
                            battleZone[randomRow][randomColumn+3] == 0){
                        battleZone[randomRow][randomColumn] = 4;
                        battleZone[randomRow][randomColumn+1] = 4;
                        battleZone[randomRow][randomColumn+2] = 4;
                        battleZone[randomRow][randomColumn+3] = 4;
                        setTotalBattleShip(getTotalBattleShip()+4);
                    }
                }
                else if(randomColumn >= 5 && randomColumn <= 9){
                    //Go LEFT
                    if (battleZone[randomRow][randomColumn] == 0) {
                        if(battleZone[randomRow][randomColumn-1] == 0 && battleZone[randomRow][randomColumn-2] == 0 &&
                                battleZone[randomRow][randomColumn-3] == 0){
                            battleZone[randomRow][randomColumn] = 4;
                            battleZone[randomRow][randomColumn-1] = 4;
                            battleZone[randomRow][randomColumn-2] = 4;
                            battleZone[randomRow][randomColumn-3] = 4;
                            setTotalBattleShip(getTotalBattleShip()+4);
                        }
                    }
                }

            }
        }

    }

    protected void battleShipVertical(){
            if(getTotalBattleShip() < 8) {
                Random randomPosition = new Random();
                int randomRow = randomPosition.nextInt(10);
                int randomColumn = randomPosition.nextInt(10);
                if (randomRow >= 0 && randomRow <= 5) {
                    //Go Down
                    if (battleZone[randomRow][randomColumn] == 0) {
                        if(battleZone[randomRow+1][randomColumn] == 0 && battleZone[randomRow+2][randomColumn] == 0 &&
                                battleZone[randomRow+3][randomColumn] == 0){
                            battleZone[randomRow][randomColumn] = 4;
                            battleZone[randomRow+1][randomColumn] = 4;
                            battleZone[randomRow+2][randomColumn] = 4;
                            battleZone[randomRow+3][randomColumn] = 4;
                            setTotalBattleShip(getTotalBattleShip()+4);
                        }
                    }
                    else if(randomRow >= 5 && randomRow <= 9){
                        //Go Up
                        if (battleZone[randomRow][randomColumn] == 0) {
                            if(battleZone[randomRow-1][randomColumn] == 0 && battleZone[randomRow-2][randomColumn] == 0 &&
                                    battleZone[randomRow-3][randomColumn] == 0){
                                battleZone[randomRow][randomColumn] = 4;
                                battleZone[randomRow-1][randomColumn] = 4;
                                battleZone[randomRow-2][randomColumn] = 4;
                                battleZone[randomRow-3][randomColumn] = 4;
                                setTotalBattleShip(getTotalBattleShip()+4);
                            }
                        }
                    }

                }
            }

        }



        protected void aircraftCarrierHorizontal() {
        if(getTotalAircraftCarrier() < 5) {
            Random randomPosition = new Random();
            int randomRow = randomPosition.nextInt(10);
            int randomColumn = randomPosition.nextInt(10);
            //if Column is Zero so go right!
            if (randomColumn == 0) {
                if (battleZone[randomRow][randomColumn] == 0) {
                    if (battleZone[randomRow][randomColumn + 2] == 0 && battleZone[randomRow][randomColumn + 4] == 0 &&
                            battleZone[randomRow][randomColumn + 6] == 0 && battleZone[randomRow][randomColumn + 8] == 0) {
                        battleZone[randomRow][randomColumn] = 5;
                        battleZone[randomRow][randomColumn + 2] = 5;
                        battleZone[randomRow][randomColumn + 4] = 5;
                        battleZone[randomRow][randomColumn + 6] = 5;
                        battleZone[randomRow][randomColumn + 8] = 5;
                        setTotalAircraftCarrier(5);
                    }

                }
            }
            //if Column is Zero so go left!
            else if (randomColumn == 9) {
                if (battleZone[randomRow][randomColumn] == 0) {
                    if (battleZone[randomRow][randomColumn - 2] == 0 && battleZone[randomRow][randomColumn - 4] == 0 &&
                            battleZone[randomRow][randomColumn - 6] == 0 && battleZone[randomRow][randomColumn - 8] == 0) {
                        battleZone[randomRow][randomColumn] = 5;
                        battleZone[randomRow][randomColumn - 2] = 5;
                        battleZone[randomRow][randomColumn - 4] = 5;
                        battleZone[randomRow][randomColumn - 6] = 5;
                        battleZone[randomRow][randomColumn - 8] = 5;
                        setTotalAircraftCarrier(5);
                    }
                }

            }
        }

        }
            protected void aircraftCarrierVertical() {
        if(totalAircraftCarrier <5) {
            Random randomPosition = new Random();
            int randomRow = randomPosition.nextInt(10);
            int randomColumn = randomPosition.nextInt(10);
            //if Row less or equal 1 go down
            if (randomRow <= 1) {
                if (battleZone[randomRow][randomColumn] == 0) {
                    if (battleZone[randomRow + 2][randomColumn] == 0 && battleZone[randomRow + 4][randomColumn] == 0 &&
                            battleZone[randomRow + 6][randomColumn] == 0 && battleZone[randomRow + 8][randomColumn] == 0) {
                        battleZone[randomRow][randomColumn] = 5;
                        battleZone[randomRow + 2][randomColumn] = 5;
                        battleZone[randomRow + 4][randomColumn] = 5;
                        battleZone[randomRow + 6][randomColumn] = 5;
                        battleZone[randomRow + 8][randomColumn] = 5;
                        setTotalAircraftCarrier(5);
                    }
                }
            }
            //if Row bigger than or equal 8 go up
            else if (randomRow >= 8) {
                if (battleZone[randomRow][randomColumn] == 0) {
                    if (battleZone[randomRow - 2][randomColumn] == 0 && battleZone[randomRow - 4][randomColumn] == 0 &&
                            battleZone[randomRow - 6][randomColumn] == 0 && battleZone[randomRow - 8][randomColumn] == 0) {
                        battleZone[randomRow][randomColumn] = 5;
                        battleZone[randomRow - 2][randomColumn] = 5;
                        battleZone[randomRow - 4][randomColumn] = 5;
                        battleZone[randomRow - 6][randomColumn] = 5;
                        battleZone[randomRow - 8][randomColumn] = 5;
                        setTotalAircraftCarrier(5);
                    }
                }
            }
        }
            }

                protected void upRightToLeftDownAndReverse() {
                    if(getTotalAircraftCarrier() < 5) {
                        Random randomPosition = new Random();
                        int randomRow = randomPosition.nextInt(10);
                        int randomColumn = randomPosition.nextInt(10);
                        //if Row less or equal 1 go down
                        if (randomRow <= 1 && randomColumn >= 8) {
                            if (battleZone[randomRow][randomColumn] == 0) {
                                if (battleZone[randomRow + 2][randomColumn - 2] == 0 && battleZone[randomRow + 4][randomColumn - 4] == 0 &&
                                        battleZone[randomRow + 6][randomColumn - 6] == 0 && battleZone[randomRow + 8][randomColumn - 8] == 0) {
                                    battleZone[randomRow][randomColumn] = 5;
                                    battleZone[randomRow + 2][randomColumn - 2] = 5;
                                    battleZone[randomRow + 4][randomColumn - 4] = 5;
                                    battleZone[randomRow + 6][randomColumn - 6] = 5;
                                    battleZone[randomRow + 8][randomColumn - 8] = 5;
                                    setTotalAircraftCarrier(5);
                                }

                            }
                        } else if (randomRow >= 8 && randomColumn <= 1) {
                            if (battleZone[randomRow][randomColumn] == 0) {
                                if (battleZone[randomRow - 2][randomColumn + 2] == 0 && battleZone[randomRow - 4][randomColumn + 4] == 0 &&
                                        battleZone[randomRow - 6][randomColumn + 6] == 0 && battleZone[randomRow - 8][randomColumn + 8] == 0) {
                                    battleZone[randomRow][randomColumn] = 5;
                                    battleZone[randomRow - 2][randomColumn + 2] = 5;
                                    battleZone[randomRow - 4][randomColumn + 4] = 5;
                                    battleZone[randomRow - 6][randomColumn + 6] = 5;
                                    battleZone[randomRow - 8][randomColumn + 8] = 5;
                                    setTotalAircraftCarrier(5);
                                }

                            }
                        }
                    }
                    }
    protected void upLefToRightDownAndReverse() {
                    if(getTotalAircraftCarrier()<5) {
                        Random randomPosition = new Random();
                        int randomRow = randomPosition.nextInt(10);
                        int randomColumn = randomPosition.nextInt(10);
                        //if Row less or equal 1 go down
                        if (randomRow <= 1 && randomColumn <= 1) {
                            if (battleZone[randomRow][randomColumn] == 0) {
                                if (battleZone[randomRow + 2][randomColumn + 2] == 0 && battleZone[randomRow + 4][randomColumn + 4] == 0 &&
                                        battleZone[randomRow + 6][randomColumn + 6] == 0 && battleZone[randomRow + 8][randomColumn + 8] == 0) {
                                    battleZone[randomRow][randomColumn] = 5;
                                    battleZone[randomRow + 2][randomColumn + 2] = 5;
                                    battleZone[randomRow + 4][randomColumn + 4] = 5;
                                    battleZone[randomRow + 6][randomColumn + 6] = 5;
                                    battleZone[randomRow + 8][randomColumn + 8] = 5;
                                    setTotalAircraftCarrier(5);
                                }

                            }
                        } else if (randomRow >= 8 && randomColumn >= 8) {
                            if (battleZone[randomRow][randomColumn] == 0) {
                                if (battleZone[randomRow - 2][randomColumn - 2] == 0 && battleZone[randomRow - 4][randomColumn - 4] == 0 &&
                                        battleZone[randomRow - 6][randomColumn - 6] == 0 && battleZone[randomRow - 8][randomColumn - 8] == 0) {
                                    battleZone[randomRow][randomColumn] = 5;
                                    battleZone[randomRow - 2][randomColumn - 2] = 5;
                                    battleZone[randomRow - 4][randomColumn - 4] = 5;
                                    battleZone[randomRow - 6][randomColumn - 6] = 5;
                                    battleZone[randomRow - 8][randomColumn - 8] = 5;
                                }

                            }
                        }
                    }
    }






    protected void setOneAircraftCarrier() {
        while (getTotalAircraftCarrier() < 5){
            aircraftCarrierHorizontal();
            aircraftCarrierVertical();
            upLefToRightDownAndReverse();
            upRightToLeftDownAndReverse();
        }
    }

    protected void setSubmarines() {
        while (getTotalSubmarines() < 8) {
            goRight(2);
        }
    }

    protected void setCruiser() {
        while (getTotalCruiser() < 9) {
            goRight(3);
        }
    }

    protected void setTwoBattleShip() {
        while (getTotalBattleShip() < 8) {
            battleShipHorizontal();
            battleShipVertical();
        }
    }



    /*  Those are not necessary but keep them for now anyway!
    protected void secondPlayerPositionToNull(int row, int col){
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if(secondPlayerShips[i][j] == secondPlayerShips[row][col]){
                    secondPlayerShips[i][j] = 1;
                }
            }
        }
    }


    protected void settingNullToAvoidRepeatArrays(){
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j <10 ; j++) {
              avoidRepeatSecondPlayer[i][j]  = 0;
              avoidRepeatFirstPlayer [i][j] = 0;
            }
        }
    }

    protected void firstPlayerPositionToNull(int row , int col){
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if(firstPlayerShips[i][j] == firstPlayerShips[row][col]){
                    firstPlayerShips[i][j] = 1;
                }
            }
        }
    }

     */



    protected int avoidRepeatHitting(int number, int row, int col){
                if (number == 1) {
                    if (secondPlayerShips[row][col] != 1) {
                        return 1;
                    } else {
                        return 0;
                    }
                } else if(number == 2) {
                    if (firstPlayerShips[row][col] != 1) {
                        return 1;
                    } else {
                        return 0;
                    }
                }
                return 0;
    }


    protected void chooseWhoStartsFirst() throws InterruptedException {
        setFirstPlayerHealth(100);
        setSecondPlayerHealth(100);
        System.out.println("You have two dice, the one with the largest number of the dice will start");
        TimeUnit.SECONDS.sleep(5);
        int twoDiceFirstPlayer;
        Random randomNumberFirstPlayer = new Random();
        twoDiceFirstPlayer = randomNumberFirstPlayer.nextInt(12) + 1;
        int twoDiceSecondPlayer;
        Random randomNumberSecondPlayer = new Random();
        twoDiceSecondPlayer = randomNumberSecondPlayer.nextInt(12) + 1;
        TimeUnit.SECONDS.sleep(3);
        System.out.println(getFirstPlayerName().toUpperCase() + " Got " + twoDiceFirstPlayer);
        TimeUnit.SECONDS.sleep(3);
        System.out.println(getSecondPlayerName().toUpperCase() + " Got " + twoDiceSecondPlayer);
        if(twoDiceFirstPlayer > twoDiceSecondPlayer){
            System.out.println(ANSI_BLUE + getFirstPlayerName() + " Will start!" + ANSI_RESET);
            TimeUnit.SECONDS.sleep(3);
            shootersChoice(1);

        }
        else if(twoDiceFirstPlayer < twoDiceSecondPlayer){
            System.out.println(ANSI_BLUE + getSecondPlayerName() + " Will start!"+ ANSI_RESET);
            TimeUnit.SECONDS.sleep(3);
            shootersChoice(2);
        }
        else{
            System.out.println("The result is a tie. Let's try again");
            chooseWhoStartsFirst();
        }

    }

    protected void shootersChoice(int number) throws InterruptedException {
            if (number == 1) {
                    shootFromFirstToSecond();
            }

            else if(number == 2){
                    shootFromSecondToFirst();
            }
            else{
                System.out.println("Something Wrong!");
            }
    }

    protected void shootFromFirstToSecond() throws InterruptedException {
        int randomPositionRow;
        int randomPositionColumn;
        Random randomPosition = new Random();
        randomPositionRow = randomPosition.nextInt(10);
        randomPositionColumn = randomPosition.nextInt(10);
        System.out.println();
        System.out.println(ANSI_WHITE_BACKGROUND + ANSI_BLACK + "    "+getFirstPlayerName().toUpperCase() + " TURN    " + ANSI_RESET);
            System.out.println(ANSI_BLUE + "Hitting Position :  " + "[ " + randomPositionRow + " ] " + " [ " + randomPositionColumn + " ]" + ANSI_RESET);
        TimeUnit.SECONDS.sleep(1);
                if (secondPlayerShips[randomPositionRow][randomPositionColumn] != 0) {
                    if (secondPlayerShips[randomPositionRow][randomPositionColumn] == 5) {
                        System.out.println(TEXT_YELLOW + "You hit 5" + ANSI_RESET);
                        secondPlayerShips[randomPositionRow][randomPositionColumn] = 1;
                        setSecondPlayerHealth(getSecondPlayerHealth() - 5);
                    } else if (secondPlayerShips[randomPositionRow][randomPositionColumn] == 4) {
                        System.out.println(TEXT_YELLOW + "You hit 4" + ANSI_RESET);
                        secondPlayerShips[randomPositionRow][randomPositionColumn] = 1;
                        setSecondPlayerHealth(getSecondPlayerHealth() - 4);
                    } else if (secondPlayerShips[randomPositionRow][randomPositionColumn] == 3) {
                        System.out.println(TEXT_YELLOW + "You hit 3" + ANSI_RESET);
                        secondPlayerShips[randomPositionRow][randomPositionColumn] = 1;
                        setSecondPlayerHealth(getSecondPlayerHealth() - 3);
                    } else if (secondPlayerShips[randomPositionRow][randomPositionColumn] == 2) {
                        System.out.println(TEXT_YELLOW + "You hit 2 " + ANSI_RESET);
                        secondPlayerShips[randomPositionRow][randomPositionColumn] = 1;
                        setSecondPlayerHealth(getSecondPlayerHealth() - 2);
                    } else {
                        System.out.println(ANSI_RED + "You hit nothing :( " + ANSI_RESET);
                    }
                } else {
                    System.out.println(ANSI_RED + "You hit nothing :( " + ANSI_RESET);
                }
                System.out.println(ANSI_WHITE_BACKGROUND + ANSI_BLACK + getSecondPlayerName().toUpperCase() + "S Health is " + getSecondPlayerHealth() + " now." + ANSI_RESET);
                findingHittingPositionAndReplace(1, randomPositionRow, randomPositionColumn);
                System.out.println(ANSI_BLUE + "This where did you hit until now" + ANSI_RESET);
                printHistoryHitting(1);
                System.out.println(ANSI_BLUE + "Enemy Position, Number one is successful targeting" + ANSI_RESET );
                printPositions(2);


            if (getSecondPlayerHealth() <= 0) {
            System.out.println(ANSI_RED + getFirstPlayerName() + " Win, Yay !!" + ANSI_RESET);
            }
            else{
                shootFromSecondToFirst();
            }

        }





    protected void  shootFromSecondToFirst() throws InterruptedException {
        int randomPositionRow;
        int randomPositionColumn;
        Random randomPosition = new Random();
        randomPositionRow = randomPosition.nextInt(10);
        randomPositionColumn = randomPosition.nextInt(10);
        System.out.println();
        System.out.println(ANSI_WHITE_BACKGROUND + ANSI_BLACK + "    "+getSecondPlayerName().toUpperCase() + " TURN    " + ANSI_RESET);
            System.out.println(ANSI_BLUE + "Hitting Position :  " + "[ " + randomPositionRow + " ] " + " [ " + randomPositionColumn + " ]" + ANSI_RESET);
        TimeUnit.SECONDS.sleep(1);
                if (firstPlayerShips[randomPositionRow][randomPositionColumn] != 0) {
                    if (firstPlayerShips[randomPositionRow][randomPositionColumn] == 5) {
                        System.out.println(TEXT_YELLOW + "You hit 5" + ANSI_RESET);
                        firstPlayerShips[randomPositionRow][randomPositionColumn] = 1;
                        setFirstPlayerHealth(getFirstPlayerHealth() - 5);
                    } else if (firstPlayerShips[randomPositionRow][randomPositionColumn] == 4) {
                        System.out.println(TEXT_YELLOW + "You hit 4" + ANSI_RESET);
                        firstPlayerShips[randomPositionRow][randomPositionColumn] = 1;
                        setFirstPlayerHealth(getFirstPlayerHealth() - 4);

                    } else if (firstPlayerShips[randomPositionRow][randomPositionColumn] == 3) {
                        System.out.println(TEXT_YELLOW + "You hit 3" + ANSI_RESET);
                        firstPlayerShips[randomPositionRow][randomPositionColumn] = 1;
                        setFirstPlayerHealth(getFirstPlayerHealth() - 3);
                    } else if (firstPlayerShips[randomPositionRow][randomPositionColumn] == 2) {
                        System.out.println(TEXT_YELLOW + "You hit 2 " + ANSI_RESET);
                        firstPlayerShips[randomPositionRow][randomPositionColumn] = 1;
                        setFirstPlayerHealth(getFirstPlayerHealth() - 2);
                    } else {
                        System.out.println(ANSI_RED + "You hit nothing :( " + ANSI_RESET);
                    }

                } else {
                    System.out.println(ANSI_RED + "You hit nothing :( " + ANSI_RESET);
                }
                System.out.println(ANSI_WHITE_BACKGROUND + ANSI_BLACK + getFirstPlayerName().toUpperCase() + "S Health is " + getFirstPlayerHealth() + " now." + ANSI_RESET);
                findingHittingPositionAndReplace(2, randomPositionRow, randomPositionColumn);
                System.out.println(ANSI_BLUE + "This where did you hit until now:" + ANSI_RESET);
                printHistoryHitting(2);
                System.out.println(ANSI_BLUE + "Enemy Position, Number one is successful targeting" + ANSI_RESET );
                printPositions(1);

                if(getFirstPlayerHealth() <= 0) {
                    System.out.println(ANSI_RED + getSecondPlayerName() + " Win, Yay !!" + ANSI_RESET);
                }
                else{
                    shootFromFirstToSecond();
                }
    }




    protected void distributionOfShipsInTheOceanFirstPlayer(){
        System.out.println(" ");
        System.out.println(ANSI_WHITE_BACKGROUND + ANSI_BLACK + getFirstPlayerName().toUpperCase() + "S Ship Positions are :" + ANSI_RESET);
        settingAllPositionToZero();
        setSubmarines();
        setCruiser();
        setTwoBattleShip();
        setOneAircraftCarrier();
        savePositionsPlayers(1);
        printPositions(1);
        System.out.println();
    }

    protected void distributionOfShipsInTheOceanSecondPlayer(){
        //Setting null again to or count parameters
        setTotalAircraftCarrier(0);
        setTotalCruiser(0);
        setTotalSubmarines(0);
        setTotalBattleShip(0);
        System.out.println( ANSI_WHITE_BACKGROUND + ANSI_BLACK + getSecondPlayerName().toUpperCase() + "S Ship Positions are :" + ANSI_RESET);
        settingAllPositionToZero();
        setOneAircraftCarrier();
        setSubmarines();
        setCruiser();
        setTwoBattleShip();
        savePositionsPlayers(2);
        printPositions(2);
        System.out.println(" ");
    }
}
