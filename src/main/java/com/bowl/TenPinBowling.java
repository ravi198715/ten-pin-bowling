package com.bowl;

import java.util.ArrayList;

/**
 * Created by gohan on 14/12/17.
 */
public class TenPinBowling implements  BowlingGame{


    private static final int MAX_FRAMES = 10;
    private static final int MAX_ROLL_PER_FRAME = 2;
    private static final String  FRAMES_EXHAUSTED_EXCEPTION = "Limit reached for max. number of frames per game, Please start a new game to play more";


    public ArrayList<Frame> frames;
    public Frame frame;
    public int currentFrameCount;
    private  int gameScore;
    private boolean bonusFrame = false;



    public TenPinBowling () {
        this.frames = new ArrayList<Frame>();
        for (int i=0; i < MAX_FRAMES ; i++){
            this.frames.add(new Frame(MAX_ROLL_PER_FRAME));
        }

        this.currentFrameCount = 0;
        this.gameScore =0;

    }

    public int getGameScore () {
        return this.gameScore;
    }

    public void roll(int pinsCount) {

        frame = frames.get(currentFrameCount);

        frame.setRollScores(pinsCount);


        // Switch Frames
        if(currentFrameCount < MAX_FRAMES && frame.isFrameComplete() && !bonusFrame){
            currentFrameCount++;
        }

        //logic for Bonus roll in last  frame
        if(currentFrameCount ==10 && frame.frameHasStrike() && !bonusFrame){
            currentFrameCount--;
            bonusFrame =true;
            frame.addBonusRolls(2);
        }
        else if(currentFrameCount ==10 && frame.isSpare() && !bonusFrame){
            currentFrameCount--;
            bonusFrame =true;
            frame.addBonusRolls(1);
        }


    }

    public void score() {

        int frameIndex = 0;
        gameScore = 0;
        Frame previousFrame, frameBeforePreviousFrame ;

        try{
            //calculate total for each frames;
            for(Frame f : frames){


                int frameScore =0;
                if(f.isFrameStarted() || !f.isFrameComplete()){
                    break;
                }

                //Calculating Score for  the frame with a strike Roll
                if (frameIndex >=2 && currentFrameCount <MAX_FRAMES){
                    previousFrame = frames.get(frameIndex-1);
                    frameBeforePreviousFrame = frames.get(frameIndex-2);

                    if(frameBeforePreviousFrame.frameHasStrike() && previousFrame.frameHasStrike()){
                        frameScore = frameBeforePreviousFrame.getTotalScore();
                        frameScore += previousFrame.getTotalScore()+ f.getTotalScore();
                        frameBeforePreviousFrame.setTotalScore(frameScore);
                    }
                }
                else if(frameIndex >=1 ){
                    // Calculating total for frame with spare
                    previousFrame  = frames.get(frameIndex-1);
                    if(previousFrame.isSpare()){
                        frameScore += previousFrame.getTotalScore()+ f.getFirstRollScore();
                        previousFrame.setTotalScore(frameScore);
                    }
                }
                else if(frameIndex >=0 & !f.isSpare() && !f.frameHasStrike()) {
                    //calculate total for normal frame
                    gameScore += f.getTotalScore();
                }
                frameIndex++;
            }

            //calculate total game score
            for(Frame f : frames){
               gameScore += f.getTotalScore();
            }

        }catch (IndexOutOfBoundsException ie){
            System.out.println(FRAMES_EXHAUSTED_EXCEPTION);
        }


    }

}



