package com.tutorial.main;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

public class Shop extends MouseAdapter {

    Handler handler;
    HUD hud;

    private int B1 = 1000;
    private int B2 = 750;
    private int B3 = 500;

    public Shop(Handler handler, HUD hud) {
        this.handler = handler;
        this.hud = hud;
    }

    public void render(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("arial", 0, 48));
        g.drawString("Shop", Game.WIDTH/2 - 70, 50);

        //Box 1 - Health Upgrade
        g.setFont(new Font("arial", 0, 12));
        g.drawString("Upgrade Health", 110, 120);
        g.drawString("Cost: " + B1, 110, 140);
        g.drawRect(100, 100, 100, 80);

        //Box 2 - Speed Upgrade
        g.drawString("Upgrade Speed", 260, 120);
        g.drawString("Cost: " + B2, 260, 140);
        g.drawRect(250, 100, 100, 80);

        //Box 3 - Health Refill
        g.drawString("Refill Health", 410, 120);
        g.drawString("Cost: " + B3, 410, 140);
        g.drawRect(400, 100, 100, 80);

        //Show current Score
        g.drawString("SCORE: " + hud.getScore(), Game.WIDTH/2 - 74, 300);
        g.drawString("Press Space to go back", Game.WIDTH/2 - 74, 330);
    }

    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();


        if(Game.gameState == Game.STATE.Shop) {
            //Box 1
            if(mx >= 100 && mx <= 200) {
                if(my >= 100 && my <= 180) {
                    if(hud.getScore() >= B1) {
                        hud.setScore(hud.getScore() - B1);              //Buying Health Upgrade
                        B1 += 1000;                                     //increases it's price by a 1000
                        hud.bounds += 20;                               //and also increases the Player's maximum Health
                        hud.HEALTH = 100 + (hud.bounds/2);
                    }
                }
            }

            //Box 2
            if(mx >= 250 && mx <= 350) {
                if(my >= 100 && my <= 180) {
                    if(hud.getScore() >= B2) {
                        hud.setScore(hud.getScore() - B2);              //Buying Speed Upgrade
                        B2 += 750;                                      //increases it's price by 750
                        handler.speed++;                                //and also the Player's speed by 1
                    }
                }
            }

            //Box 3
            if(mx >= 400 && mx <= 500) {
                if(my >= 100 && my <= 180) {
                    if(hud.getScore() >= B3) {                          //Buying Health Refill
                        hud.setScore(hud.getScore() - B3);              //refills the Player's health to the maximum possible,
                        hud.HEALTH = 100 + (hud.bounds/2);              //but it's price won't increase
                    }
                }
            }
        }


    }

}
