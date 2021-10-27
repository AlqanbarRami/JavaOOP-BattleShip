package com.company;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class StartFight extends SettingDistributions  {


    public StartFight(){

    }
    protected void letsStartOurFight() throws InterruptedException {


        settingPlayersName();
        //TimeUnit.SECONDS.sleep(3);
        System.out.println("Setting ships positions for " + getFirstPlayerName().toUpperCase());
        TimeUnit.SECONDS.sleep(5);
        distributionOfShipsInTheOceanFirstPlayer();
        TimeUnit.SECONDS.sleep(5);
        System.out.println("Setting ships positions for " + getSecondPlayerName().toUpperCase());
        TimeUnit.SECONDS.sleep(5);
        distributionOfShipsInTheOceanSecondPlayer();
        TimeUnit.SECONDS.sleep(5);
        System.out.println("Let's decide who will start first! *_^ ");
        TimeUnit.SECONDS.sleep(3);
        settingHittingHistoryToSymbol();
        chooseWhoStartsFirst();

    }


}
