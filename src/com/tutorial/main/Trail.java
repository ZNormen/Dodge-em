package com.tutorial.main;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Trail extends GameObject{

    private Handler handler;

    private Color color;

    private BufferedImage image;

    private float alpha = 1;
    private int width, height;
    private float life;             //life: 0.01 - 0.1


    public Trail(float x, float y, ID id, Color color, int width, int height, float life, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        this.color = color;
        this.width = width;
        this.height = height;
        this.life = life;
    }

    public Trail(float x, float y, ID id, BufferedImage image, int width, int height, float life, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        this.image = image;
        this.width = width;
        this.height = height;
        this.life = life;
    }

    @Override
    public void tick() {
        if(alpha > life) {
            alpha -= (life - 0.0001f);
        }else handler.removeObject(this);
    }

    //Trail of objects, that fade away slowly
    @Override
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setComposite(makeTransparent(alpha));

        if(image == null) {
            g.setColor(color);
            g.fillRect((int)x, (int)y, width, height);

            g2d.setComposite(makeTransparent(1));

        } else if(image != null) {
            g.drawImage(image, (int)x, (int)y, null);
        }
    }

    private AlphaComposite makeTransparent(float alpha) {
        int type = AlphaComposite.SRC_OVER;
        return (AlphaComposite.getInstance(type, alpha));
    }



    @Override
    public Rectangle getBounds() {
        return null;
    }
}
