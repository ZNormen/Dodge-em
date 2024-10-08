package com.tutorial.main;

import java.awt.*;

public class HUD {

    public int bounds = 0;
    public static float HEALTH = 100;
    private float greenValue = 255f;

    private int score = 0;
    private int level = 1;

    public void tick() {
        HEALTH = Game.clamp(HEALTH, 0 , 100 + (bounds/2));
        greenValue = HEALTH * 2;                                            //greenValue decreases every time the player takes damage
        greenValue = Game.clamp(greenValue, 0, 255);



        score++;
    }

    public void render(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect((int)15, (int)15, 200 + bounds, 32);
        g.setColor(new Color(75, (int)greenValue, 0));              //The lower the health, the health bar gets more and more red
        g.fillRect((int)15, (int)15, (int)HEALTH * 2, 32);
        g.setColor(Color.WHITE);
        g.drawRect(15, 15, 200 + bounds, 32);

        g.drawString("Score: " + score, 15, 64);
        g.drawString("Level: " + level, 15, 80);
        g.drawString("Space for Shop", 15, 96);
    }

    public void setScore(int score) {
        this.score = score;
    }
    public int getScore() {
        return score;
    }
    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }

}
