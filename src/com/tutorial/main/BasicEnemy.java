package com.tutorial.main;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BasicEnemy extends GameObject{

    private Handler handler;

    private BufferedImage enemy_image;

    public BasicEnemy(int x, int y, ID id, Handler handler) {
        super(x, y, id);

        this.handler = handler;

        velX = 5;
        velY = 5;

        SpriteSheet ss = new SpriteSheet(Game.sprite_sheet);
        enemy_image = ss.grabImage(1, 2, 16, 16);
    }

    //Hitbox of the Basic Enemy
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 16, 16);
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;

        if(y <= 0 || y >=Game.HEIGHT - 57) velY *= -1;
        if(x <= 0 || x >=Game.WIDTH - 24) velX *= -1;

        handler.addObject(new Trail(x, y, ID.Trail, enemy_image, 16, 16, 0.05f, handler));
    }

    @Override
    public void render(Graphics g) {
//        g.setColor(Color.red);                    //If you want your Basic Enemy to be a colored rectangle
//        g.fillRect((int)x, (int)y, 16, 16);       //rather than the image

        g.drawImage(enemy_image, (int)x, (int)y, null);
    }


}
