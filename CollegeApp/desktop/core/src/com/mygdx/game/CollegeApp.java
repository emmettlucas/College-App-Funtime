package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.objects.Brick;
import com.mygdx.game.objects.GameObject;

import java.util.ArrayList;

import static com.badlogic.gdx.math.Interpolation.circle;

public class CollegeApp extends ApplicationAdapter {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private com.mygdx.game.objects.PixelGuy player1;
	//private ArrayList<GameObject> list = new ArrayList<GameObject>();
	//private float accumulator = 0;
	World world;
	//Box2DDebugRenderer debugRenderer = new Box2DDebugRenderer();
	Sprite sprite;
	Body body;
	Vector2 vel;
	Vector2 pos;
	final float MAX_VELOCITY = 5000f;
	ShapeRenderer shapeRenderer;
	
	@Override
	public void create () {
		Box2D.init();
		shapeRenderer = new ShapeRenderer();
		//shapeRenderer.setProjectionMatrix(camera.combined);

		camera = new OrthographicCamera();//Sets up camera and view to eventually be rendered
		camera.setToOrtho(false, 800, 480);
		batch = new SpriteBatch();

		player1 = new com.mygdx.game.objects.PixelGuy();//Creates player and positions him
		player1.setPosition(200, 300);

		world = new World(new Vector2(0, -30), true);

		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.DynamicBody;
		bodyDef.position.set(player1.getX(), player1.getY());

		body = world.createBody(bodyDef);

		PolygonShape shape = new PolygonShape();
		//shape.setAsBox(sprite.getWidth()/2-2, sprite.getHeight()/2-2);
		//shape.setAsBox(28, 79.5f);
		shape.setAsBox(28f, 79.5f);

		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.density = 1f;
		fixtureDef.friction = 0.1f;
		fixtureDef.restitution = 0.3f;

		Fixture fixture = body.createFixture(fixtureDef);

		// Create our brick definition
		BodyDef groundBodyDef = new BodyDef();
		// Set its world position
		groundBodyDef.position.set(new Vector2(0, 10));

		// Create a body from the definition and add it to the world
		Body groundBody = world.createBody(groundBodyDef);

		// Create a polygon shape
		PolygonShape groundBox = new PolygonShape();
		// Set the polygon shape as a box twice the size of our view port and 20 high

		groundBox.setAsBox(camera.viewportWidth, 10.0f);
		// Create a fixture from our polygon shape and add it to ground body
		groundBody.createFixture(groundBox, 0.0f);
		// Clean up
		groundBox.dispose();

		BodyDef box1BodyDef = new BodyDef();
		box1BodyDef.position.set(new Vector2(160+110, 20+50));
		Body box1Body = world.createBody(box1BodyDef);
		PolygonShape box1 = new PolygonShape();
		box1.setAsBox(110, 50);
		box1Body.createFixture(box1, 0.0f);
		box1.dispose();

		BodyDef box2BodyDef = new BodyDef();
		box2BodyDef.position.set(new Vector2(640, 20));
		Body box2Body = world.createBody(box2BodyDef);
		PolygonShape box2 = new PolygonShape();
		box2.setAsBox(25, 25);
		box2Body.createFixture(box2, 0.0f);
		box2.dispose();

		/*
		//creates an new instance of each brick in world
		list.add(new Brick(0,0));
		list.add(new Brick(64,0));
		list.add(new Brick(128,0));
		list.add(new Brick(192,0));
		list.add(new Brick(256,0));
		list.add(new Brick(0,64));
		list.add(new Brick(64,64));
		list.add(new Brick(128,64));/*
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

//		body.applyForce(1.0f, 0.0f, body.getPosition().x, body.getPosition().y, true);
//		body.applyForceToCenter(1.0f, 0.0f, true);



//		//Controls
//		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){//Left arrow input, moves left
//			player1.moveLeft(Gdx.graphics.getDeltaTime());
//		}
//		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){//Right arrow input, moves right
//			player1.moveRight(Gdx.graphics.getDeltaTime());
//		}
//		if(Gdx.input.isKeyPressed(Input.Keys.UP)){//Up arrow input, jumps
//			player1.jump();
//		}

//		while (true){
//			body.applyForceToCenter(0.0f, 1.0f, true);
//		}
	}

	@Override
	public void render() {

		//world.step(Gdx.graphics.getDeltaTime(), 6, 2);
		world.step(1/45f, 6, 4);


		vel = body.getLinearVelocity();
		pos = body.getPosition();

		// apply left impulse, but only if max velocity is not reached yet
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && vel.x > -MAX_VELOCITY) {
			body.applyLinearImpulse(-20f, 0, pos.x, pos.y, true);
		}

		// apply right impulse, but only if max velocity is not reached yet
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && vel.x < MAX_VELOCITY) {
			body.applyLinearImpulse(20f, 0, pos.x, pos.y, true);
		}

		if (Gdx.input.isKeyPressed(Input.Keys.UP) && vel.x < MAX_VELOCITY) {
			body.applyLinearImpulse(0, 100f, pos.x, pos.y, true);
		}//ASDOJF

		player1.setPosition(body.getPosition().x, body.getPosition().y);

		Gdx.gl.glClearColor(0, 1, 0, 1);//Colors background
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		shapeRenderer.setColor(Color.BLACK);
		shapeRenderer.rect(-20,-20,camera.viewportWidth+20,40);
		shapeRenderer.rect(160,20,220,100);
		shapeRenderer.rect(640,20,50,50);
		shapeRenderer.end();

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		player1.draw(batch);
		//batch.draw(player1, player1.getX(), player1.getY());
		batch.end();
	}



//	private void doPhysicsStep(float deltaTime) {
//		// fixed time step
//		// max frame time to avoid spiral of death (on slow devices)
//		float frameTime = Math.min(deltaTime, 0.25f);
//		accumulator += frameTime;
//		while (accumulator >= Constants.TIME_STEP) {
//			WorldManager.world.step(Constants.TIME_STEP, Constants.VELOCITY_ITERATIONS, Constants.POSITION_ITERATIONS);
//			accumulator -= Constants.TIME_STEP;
//		}
//	}
	
	@Override
	public void dispose () {
		batch.dispose();
		//shape.dispose();
		world.dispose();
		//circle.dispose();
	}
}
