package com.tutorial.main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class EnemyBoss extends GameObject{

    private Handler handler;

    Random r = new Random();

    private BufferedImage boss_image;

    private int timer = 80;
    private int timer2 = 50;

    public EnemyBoss(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;

        SpriteSheet ss = new SpriteSheet(Game.sprite_sheet);
        boss_image = ss.grabImage(2, 2, 96, 96);

        velX = 0;
        velY = 2;
    }

    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 640, 96);
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;

        if(timer <= 0) velY = 0;
        else timer--;

        if(timer <= 0) timer2--;
        if(timer2 <= 0) {

            if(velX == 0) velX = 2;

            if(velX > 0) velX += 0.005f;
            else if(velX < 0) velX -= 0.005f;

            velX = Game.clamp(velX, -10, 10);

            int spawn = r.nextInt(10);
            if(spawn == 0) {
                handler.addObject(new EnemyBossBullet((int) x + 48, (int) y + 48, ID.BasicEnemy, handler));
            }
        }

        if(x <= 0 || x >=Game.WIDTH - 104) velX *= -1;
    }

    @Override
    public void render(Graphics g) {
//        g.setColor(Color.red);
//        g.fillRect((int)x, (int)y, 96, 96);
        g.drawImage(boss_image, (int)x, (int)y, null);
    }


}
