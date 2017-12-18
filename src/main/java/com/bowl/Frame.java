package com.bowl;
import java.util.ArrayList;

/**
 * Created by gohan on 14/12/17.
 */
public class Frame {

    public ArrayList<Integer> rollScores ;
    private boolean hasStrike;
    private int totalScore;
    private int rollsRemaining;
  
    private static final int  MAX_PINS = 10;


    public Frame (int noOfRoles){
        this.rollScores = new ArrayList<>();
        this.totalScore = 0;
        this.hasStrike = false;
        this.rollsRemaining = noOfRoles;
    }

    public boolean frameHasStrike (){
        return this.hasStrike;
    }

    public boolean isSpare(){
        if (this.rollScores.size() < 2){
            return false;
        }
        else{
            int sumOfTwoRoles = this.rollScores.get(0) + this.rollScores.get(1);
            return  sumOfTwoRoles == MAX_PINS && this.rollsRemaining == 0;
        }

    }



    public boolean isFrameStarted (){ return this.rollScores.isEmpty(); }

    public boolean isFrameComplete(){
        return this.rollsRemaining <= 0;
    }

    public void setRollScores (int pins){

        this.rollScores.add(pins);
        this.rollsRemaining--;
        this.totalScore+=pins;


        if(pins == MAX_PINS){
            this.rollsRemaining--;
            this.hasStrike= true;
        }
    }

    public void setTotalScore (int score){
        this.totalScore = score;
    }

    public int getTotalScore (){
        return  this.totalScore;
    }

    public int getFirstRollScore (){
        return  this.rollScores.get(0);
    }

    public void addBonusRolls (int rolls){

        this.rollsRemaining += rolls;
    }

}
