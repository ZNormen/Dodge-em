package com.tutorial.main;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SmartEnemy extends GameObject{

    private Handler handler;
    private GameObject player;

    private BufferedImage smart_enemy_image;

    public SmartEnemy(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;

        for(int i = 0; i < handler.object.size(); i++) {
            if(handler.object.get(i).getId() == ID.Player) player = handler.object.get(i);
        }

        SpriteSheet ss = new SpriteSheet(Game.sprite_sheet);
        smart_enemy_image = ss.grabImage(1, 4, 16, 16);
    }

    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 16, 16);
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;

        //Mathematical formulas, so that the Smart Enemy can track down the Player and follow it
        float diffX = x - player.getX() - 16;
        float diffY = y - player.getY() - 16;
        float distance = (float) Math.sqrt((x - player.getX())*(x - player.getX()) + (y - player.getY())*(y - player.getY()));

        velX = (float) ((-1/distance) * diffX);
        velY = (float) ((-1/distance) * diffY);

        handler.addObject(new Trail(x, y, ID.Trail, smart_enemy_image, 16, 16, 0.05f, handler));
    }

    @Override
    public void render(Graphics g) {
//        g.setColor(Color.GREEN);
//        g.fillRect((int)x, (int)y, 16, 16);
        g.drawImage(smart_enemy_image, (int)x, (int)y, null);
    }

}
