package com.company;

import java.util.Scanner;

public class Players{
    private String firstPlayerName;
    private int firstPlayerHealth;
    private String secondPlayerName;
    private int secondPlayerHealth;


    public String getSecondPlayerName() {
        return secondPlayerName;
    }

    public void setSecondPlayerName(String secondPlayerName) {
        this.secondPlayerName = secondPlayerName;
    }



    public int getSecondPlayerHealth() {
        return secondPlayerHealth;
    }

    public void setSecondPlayerHealth(int secondPlayerHealth) {
        this.secondPlayerHealth = secondPlayerHealth;
    }


    public String getFirstPlayerName() {
        return firstPlayerName;
    }

    public void setFirstPlayerName(String firstPlayerName) {
        this.firstPlayerName = firstPlayerName;
    }

    public int getFirstPlayerHealth() {
        return firstPlayerHealth;
    }

    public void setFirstPlayerHealth(int firstPlayerHealth) {
        this.firstPlayerHealth = firstPlayerHealth;
    }

    public Players() {
    }


    protected void settingPlayersName(){
        System.out.println("Enter frist player name: ");
        Scanner firstPlayer = new Scanner(System.in);
        setFirstPlayerName(firstPlayer.nextLine());
        System.out.println(("Enter second player name: "));
        Scanner secondPlayer = new Scanner(System.in);
        setSecondPlayerName(secondPlayer.nextLine());
        System.out.println("First Player name : " + getFirstPlayerName());
        System.out.println("Second Player name : " + getSecondPlayerName());
    }


}
