package com.mygdx.game.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by emmet on 11/18/2016.
 */

public class Brick extends GameObject {

    Rectangle hitBox;
    Sprite sprite;
    Texture texture;

    public Brick(int x, int y) {
        hitBox = new Rectangle(x,y,64,64);
        texture = new Texture("brick.png");
        sprite = new Sprite(texture, 0, 0, 64, 64);
        sprite.setPosition(x,y);
    }

    @Override
    public int hits(Rectangle r) {
        return 0;
    }

    @Override
    public void action(int type, float x, float y) {

    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void setPosition(float x, float y) {
        hitBox.x = x;
        hitBox.y = y;
        sprite.setPosition(x, y);
    }

    @Override
    public void moveLeft(float delta) {

    }

    @Override
    public void moveRight(float delta) {

    }

    @Override
    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }

    @Override
    public void jump() {

    }

    @Override
    public Rectangle getHitBox() {
        return hitBox;
    }
}
