package com.mygdx.game.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by emmett on 11/16/2016.
 */

public class PixelGuy extends com.mygdx.game.objects.GameObject {
    Rectangle bottom, left, right, top;
    Sprite sprite;
    Texture img;
    int action;
    float x = 100;
    float y = 50;
    float velocityY;

    //constructor
    public PixelGuy(){
        bottom = new Rectangle(0.0f,0.0f,128.0f,128.0f);

        img = new Texture("PixelGuy.jpg");

        sprite = new Sprite(img, 0, 0, 58, 161);
        this.setPosition(x, y);
        velocityY = 0;//
    }

    public float getX(){
        return x;
    }

    public float getY(){
        return y;
    }

    public int hits(Rectangle r){
        if(bottom.overlaps(r)){
            return 1;
        }
        return -1;
    }

    public void action(int type, float x, float y){
        if (type == 1){
            velocityY = 0;
            setPosition(bottom.x,y);
        }
    }

    public void update(float delta){
        velocityY -= 50*delta;
        bottom.y += velocityY;
        sprite.setPosition(bottom.x, bottom.y);
    }

    public void setPosition(float x1, float y1){
        x = x1;
        y = y1;
        sprite.setPosition(x,y);
    }

    public void moveLeft(float delta){
        bottom.x -= 200*delta;
        sprite.setPosition(bottom.x, bottom.y);
    }

    public void moveRight(float delta){
        bottom.x += 200*delta;
        sprite.setPosition(bottom.x, bottom.y);
    }

    public void draw(SpriteBatch batch){
        sprite.draw(batch);
    }

    public void jump(){
        velocityY = 10;
    }

    @Override
    public Rectangle getHitBox() {
        return bottom;
    }
}
