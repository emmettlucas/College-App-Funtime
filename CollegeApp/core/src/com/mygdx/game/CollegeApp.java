package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.objects.Brick;
import com.mygdx.game.objects.GameObject;

import java.util.ArrayList;

public class CollegeApp extends ApplicationAdapter {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private com.mygdx.game.objects.PixelGuy player1;
	private ArrayList<GameObject> list = new ArrayList<GameObject>();
	World world = new World(new Vector2(0, -10), true);
	Box2DDebugRenderer debugRenderer = new Box2DDebugRenderer();
	
	@Override
	public void create () {
		Box2D.init();

		camera = new OrthographicCamera();//Sets up camera and view to eventually be rendered
		camera.setToOrtho(false, 800, 480);
		batch = new SpriteBatch();

		player1 = new com.mygdx.game.objects.PixelGuy();//Creates player and positions him
		player1.setPosition(200, 100);

		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.DynamicBody;
		bodyDef.position.set(100, 300);

		Body body = world.createBody(bodyDef);

		//creates an new instance of each brick in world
		list.add(new Brick(0,0));
		list.add(new Brick(64,0));
		list.add(new Brick(128,0));
		list.add(new Brick(192,0));
		list.add(new Brick(256,0));
		list.add(new Brick(0,64));
		list.add(new Brick(64,64));
		list.add(new Brick(128,64));
	}

	@Override
	public void render () {
		/*Gdx.gl.glClearColor(0, 1, 0, 1);//Colors background
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		player1.draw(batch);
		for(GameObject t: list){//Renders all game objects
			t.draw(batch);
		}
		batch.end();

		//Updates
		player1.update(Gdx.graphics.getDeltaTime());
		Rectangle temp = new Rectangle(0,-20,800,20); //Creates a rectangle at bottom of screen
		if (player1.hits(temp) != -1){ //Checks if player hits the bottom of the screen
			player1.action(1, 0, 0);
		}

		for(GameObject t: list){//Checks if the player is overlapping with a Brick object
			if(player1.hits(t.getHitBox()) != -1){
				player1.action(1, 0, t.getHitBox().y + t.getHitBox().height);
			}
		}
*/
		debugRenderer.render(world, camera.combined);

		//Controls
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){//Left arrow input, moves left
			player1.moveLeft(Gdx.graphics.getDeltaTime());
		}
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){//Right arrow input, moves right
			player1.moveRight(Gdx.graphics.getDeltaTime());
		}
		if(Gdx.input.isKeyPressed(Input.Keys.UP)){//Up arrow input, jumps
			player1.jump();
		}

		world.step(1/45f, 6, 2);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
