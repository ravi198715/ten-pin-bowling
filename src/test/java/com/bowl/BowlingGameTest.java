package com.bowl;


import org.junit.Assert;
import org.junit.Test;

/**
 * Unit test for simple BowlingGame.
 */

public class BowlingGameTest
{

    private TenPinBowling game;



    @Test
    public void assertSumOfRolls(){

        game = new TenPinBowling();

        game.roll(4);
        game.roll(4);

        game.score();

        Assert.assertEquals(8, game.frames.get(0).getTotalScore());
    }


    @Test
    public void assertFrameHasSpare(){

        game = new TenPinBowling();

        game.roll(4);
        game.roll(6);

        game.score();

        Assert.assertEquals(true, game.frames.get(0).isSpare());
        Assert.assertEquals(10, game.frames.get(0).getTotalScore());
    }


    @Test
    public void assertFrameHasStrike(){

        game = new TenPinBowling();

        game.roll(10);

        game.score();

        Assert.assertEquals(true, game.frames.get(0).frameHasStrike());

    }

    @Test
    public void assertFrameHasNoSpareOrStrike(){

        game = new TenPinBowling();

        game.roll(4);
        game.roll(4);

        game.score();

        Assert.assertEquals(false, game.frames.get(0).isSpare());
        Assert.assertEquals(false, game.frames.get(0).frameHasStrike());

        Assert.assertNotEquals(10 , game.frames.get(0).getTotalScore());


    }



    @Test
    public void assertFramesFinished(){

        game = new TenPinBowling();

        for(int i =0; i <1 ; i++){
            game.roll(10);
        }

        game.score();

        Frame frame = game.frames.get(0);

        Assert.assertEquals(true,frame.isFrameComplete());
    }


    @Test
    public void assertFramesIsNotFinished(){

        game = new TenPinBowling();

        for(int i =0; i <1 ; i++){
            game.roll(8);
        }

        game.score();

        Frame frame = game.frames.get(0);

        Assert.assertEquals(false, frame.isFrameComplete());
    }

    @Test
    public void assertBonusRollsForSpikeInLastFrame(){

        game = new TenPinBowling();

        for(int i =0; i <12; i++){
            game.roll(10);
        }

        game.score();

        Frame lastFrame = game.frames.get(9);
        Assert.assertEquals(30, lastFrame.getTotalScore());

    }

    @Test
    public void assertBonusRollsForSpareInLastFrame(){

        game = new TenPinBowling();

        for(int i =0; i <9; i++){
            game.roll(10);
        }
        game.roll(5);

        game.roll(5);

        game.roll(5);

        game.score();

        Frame lastFrame = game.frames.get(9);

        Assert.assertEquals(15, lastFrame.getTotalScore());

    }


    @Test
    public void assertStrikeForEveryRoll(){
        game = new TenPinBowling();

        for(int i =0; i <12; i++){
            game.roll(10);
        }

        game.score();


        Assert.assertEquals(300, game.getGameScore());
    }

}
