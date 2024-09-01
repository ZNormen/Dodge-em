package com.tutorial.main;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Menu extends MouseAdapter {

    private Game game;
    private Handler handler;
    private HUD hud;

    private Random r = new Random();

    public Menu(Game game, Handler handler, HUD hud) {
        this.game = game;
        this.hud = hud;
        this.handler = handler;
    }

    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        if(game.gameState == Game.STATE.Menu) {
            //Play button
            if(mouseOver(mx ,my, 210, 150, 200, 64)) {
                AudioPlayer.playMenuSound();
                game.gameState = Game.STATE.Select;
                return;
            }

            //Help button
            if(mouseOver(mx, my, 210, 250, 200, 64)) {
                game.gameState = Game.STATE.Help;
                AudioPlayer.playMenuSound();
            }

            //Quit button
            if(mouseOver(mx, my, 210, 350, 200, 64)) {
                System.exit(1);
            }
        }

        if(game.gameState == Game.STATE.Select) {
            //Normal button - Playing in Normal difficulty
            if(mouseOver(mx ,my, 210, 150, 200, 64)) {
                game.gameState = Game.STATE.Game;
                handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler));
                handler.clearEnemies();
                handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));

                game.diff = 0;

                AudioPlayer.playMenuSound();
            }

            //Hard button - Playing  in Hard difficulty
            if(mouseOver(mx, my, 210, 250, 200, 64)) {
                game.gameState = Game.STATE.Game;
                handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler));
                handler.clearEnemies();
                handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));

                game.diff = 1;

                AudioPlayer.playMenuSound();
            }

            //Back button for Select
            if(mouseOver(mx, my, 210, 350, 200, 64)) {
                game.gameState = Game.STATE.Menu;
                AudioPlayer.playMenuSound();
                return;
            }
        }

        //Back button for Help
        if(game.gameState == Game.STATE.Help) {
            if(mouseOver(mx, my, 210, 350, 200, 64)) {
                game.gameState = Game.STATE.Menu;
                AudioPlayer.playMenuSound();
                return;
            }
        }

        //Try Again button
        if(game.gameState == Game.STATE.End) {
            if(mouseOver(mx, my, 210, 350, 200, 64)) {
                game.gameState = Game.STATE.Menu;
                hud.setLevel(1);
                hud.setScore(0);
                AudioPlayer.playMenuSound();
            }
        }

        //New Game button
        if(game.gameState == Game.STATE.Win) {
            if(mouseOver(mx, my, 210, 350, 200, 64)) {
                game.gameState = Game.STATE.Menu;
                hud.setLevel(1);
                hud.setScore(0);
                AudioPlayer.playMenuSound();
            }
        }

    }

    public void mouseReleased(MouseEvent e) {

    }

    private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
        if(mx > x && mx < x + width) {
            if(my > y && my < y + height) {
                return true;
            } else {
                return false;
            }
        } else return false;
    }

    public  void tick() {

    }

    public void render(Graphics g) {
        if(game.gameState == Game.STATE.Menu) {
            Font fnt = new Font("arial", 1, 50);
            Font fnt2 = new Font("arial", 1, 30);

            g.setFont(fnt);
            g.setColor(Color.WHITE);
            g.drawString("Dodge 'Em", 180, 70);


            g.setFont(fnt2);
            g.drawRect(210, 150, 200, 64);
            g.drawString("Play", 270, 190);


            g.drawRect(210, 250, 200, 64);
            g.drawString("Help", 270, 290);


            g.drawRect(210, 350, 200, 64);
            g.drawString("Quit", 270, 390);

        } else if(game.gameState == Game.STATE.Help) {
            Font fnt = new Font("arial", 1, 50);
            Font fnt2 = new Font("arial", 1, 30);
            Font fnt3 = new Font("arial", 1, 20);

            g.setFont(fnt);
            g.setColor(Color.WHITE);
            g.drawString("Help", 254, 70);

            g.setFont(fnt3);
            g.drawString("Use WASD keys to move player and dodge enemies", 60, 200);

            g.setFont(fnt2);
            g.drawRect(210, 350, 200, 64);
            g.drawString("Back", 270, 390);

        } else if(game.gameState == Game.STATE.End) {
            Font fnt = new Font("arial", 1, 50);
            Font fnt2 = new Font("arial", 1, 30);
            Font fnt3 = new Font("arial", 1, 20);

            g.setFont(fnt);
            g.setColor(Color.WHITE);
            g.drawString("Game Over", 180, 70);

            g.setFont(fnt3);
            g.drawString("You lost with a score of: " + hud.getScore(), 175, 200);

            g.setFont(fnt2);
            g.drawRect(210, 350, 200, 64);
            g.drawString("Try Again", 245, 390);

        } else if(game.gameState == Game.STATE.Win) {
            Font fnt = new Font("arial", 1, 50);
            Font fnt2 = new Font("arial", 1, 30);
            Font fnt3 = new Font("arial", 1, 20);

            g.setFont(fnt);
            g.setColor(Color.WHITE);
            g.drawString("You Won", 196, 70);

            g.setFont(fnt3);
            g.drawString("You won with a score of: " + hud.getScore() + ", on Level: " + hud.getLevel(), 122, 200);

            g.setFont(fnt2);
            g.drawRect(210, 350, 200, 64);
            g.drawString("New Game", 232, 390);

        } else if(game.gameState == Game.STATE.Select) {
            Font fnt = new Font("arial", 1, 50);
            Font fnt2 = new Font("arial", 1, 30);

            g.setFont(fnt);
            g.setColor(Color.WHITE);
            g.drawString("SELECT DIFFICULTY", 60, 70);


            g.setFont(fnt2);
            g.drawRect(210, 150, 200, 64);
            g.drawString("Normal", 256, 190);


            g.drawRect(210, 250, 200, 64);
            g.drawString("Hard", 275, 290);


            g.drawRect(210, 350, 200, 64);
            g.drawString("Back", 270, 390);

        }

    }

}
