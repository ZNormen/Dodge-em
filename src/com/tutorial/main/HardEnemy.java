package com.tutorial.main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class HardEnemy extends GameObject{

    private Handler handler;

    private Random r = new Random();

    private BufferedImage hard_enemy_image;

    public HardEnemy(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;

        velX = 5;
        velY = 5;

        SpriteSheet ss = new SpriteSheet(Game.sprite_sheet);
        hard_enemy_image = ss.grabImage(2, 1, 16, 16);
    }

    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 16, 16);
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;

        if(y <= 0 || y >=Game.HEIGHT - 57) {
            if(velY < 0)
                velY = -(r.nextInt(7) + 1) * -1;
            else
                velY = (r.nextInt(7) + 1) * -1;
        }
        if(x <= 0 || x >=Game.WIDTH - 24) {
            if(velX < 0)
                velX = -(r.nextInt(7) + 1) * -1;
            else
                velX = (r.nextInt(7) + 1) * -1;
        }

        handler.addObject(new Trail(x, y, ID.Trail, hard_enemy_image, 16, 16, 0.05f, handler));
    }

    @Override
    public void render(Graphics g) {
//        g.setColor(Color.YELLOW);
//        g.fillRect((int)x, (int)y, 16, 16);
        g.drawImage(hard_enemy_image, (int)x, (int)y, null);
    }


}
