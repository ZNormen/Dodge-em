package com.tutorial.main;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

public class BufferedImageLoader {

    BufferedImage image;

    public BufferedImage loadImage(String path) {
        try {
            image = ImageIO.read(new FileInputStream(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

}
