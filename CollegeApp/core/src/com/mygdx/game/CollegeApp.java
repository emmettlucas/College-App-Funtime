package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class CollegeApp extends ApplicationAdapter {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private PixelGuy player1;
	
	@Override
	public void create () {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		batch = new SpriteBatch();

		player1 = new PixelGuy();
		player1.setPosition(200, 100);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 1, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		player1.draw(batch);
		batch.end();

		//Updates
		player1.update(Gdx.graphics.getDeltaTime());
		Rectangle temp = new Rectangle(0,0,800,20);
		if (player1.hits(temp) != -1){
			player1.action(1, 0, 20);
		}

		//Controls
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
			player1.moveLeft(Gdx.graphics.getDeltaTime());
		}
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
			player1.moveRight(Gdx.graphics.getDeltaTime());
		}
		if(Gdx.input.isKeyPressed(Input.Keys.UP)){
			player1.jump();
		}

	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
