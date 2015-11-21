package com.gunnapse.hello;

import static com.gunnapse.hello.Box2dVars.BIT_BOX;
import static com.gunnapse.hello.Box2dVars.PPM;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Play extends GameState {

	private static final int JUMP_SPEED = 100;
	private static final int MOVEMENT_SPEED = 150;
	private World world;
	private Box2DDebugRenderer b2ddr;
	private OrthographicCamera b2dCam;
	private Body playerBody;
	private GameContactListener cl = new GameContactListener();
	
	private TiledMap tiledMap;
	private OrthogonalTiledMapRenderer tmr;
	private float tileSize;
	
	public Play(GameStateManager gameStateManager) {
		super(gameStateManager);
		world = new World(new Vector2(0, 0), true);
		world.setContactListener(cl);
		b2ddr = new Box2DDebugRenderer();

		createPlayer(new BodyDef());

		// set up b2dcam
		b2dCam = new OrthographicCamera();
		b2dCam.setToOrtho(false, MyGdxGame.V_WIDTH, MyGdxGame.V_HEIGHT);
		
		loadMap();
	}

	private void loadMap() {
		tiledMap = new TmxMapLoader().load("hello-tiled.tmx");
		tmr = new OrthogonalTiledMapRenderer(tiledMap);
		
		TiledMapTileLayer layer = (TiledMapTileLayer) tiledMap.getLayers().get("solid");
		tileSize = layer.getTileWidth();
		
		BodyDef bd = new BodyDef();
		FixtureDef fd = new FixtureDef();		
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(16, 16);
		for (int x = 0; x < layer.getWidth(); x++) {
			for (int y = 0; y < layer.getHeight(); y++) {
				Cell cell = layer.getCell(x, y);
				
				if (cell != null && cell.getTile() != null) {
					bd.type = BodyType.StaticBody;
					bd.position.set(x * tileSize + 16, y * tileSize + 16);
					fd.shape = shape;
					fd.friction = 1;
					world.createBody(bd).createFixture(fd);
				}
			}
		}
	}

	private void createPlayer(BodyDef bdef) {
		playerBody = buildBodyDef(bdef, 16 * 32, 16* 32, BodyType.DynamicBody);
		
		PolygonShape playerShape = new PolygonShape();
		playerShape.setAsBox(16, 16);

		Fixture playerFixture = buildFixtureFromShape(playerBody, playerShape, BIT_BOX);
		playerFixture.setUserData("player");
		
		PolygonShape footShape = new PolygonShape();
		footShape.setAsBox(5 / PPM, 1 / PPM, new Vector2(0, -5 / PPM), 0);
		
		Fixture footFixture = buildFixtureFromShape(playerBody, footShape, BIT_BOX);
		footFixture.setUserData("foot");
	}

	private Fixture buildFixtureFromShape(Body body, PolygonShape shape, short categoryBits) {
		
		FixtureDef fDef = new FixtureDef();
		fDef.shape = shape;
		fDef.filter.categoryBits = categoryBits;
		//fDef.filter.maskBits = BIT_GROUND;
		return body.createFixture(fDef);
	}

	private Body buildBodyDef(BodyDef bdef, int posX, int posY, BodyType bodyType) {
		bdef.position.set(posX, posY);
		bdef.type = bodyType;
		return world.createBody(bdef);
	}

	@Override
	public void update(float dt) {
		handleInput();
		world.step(dt, 6, 2);
		b2dCam.position.set(playerBody.getPosition().x,playerBody.getPosition().y, 0);
		b2dCam.update();
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		tmr.setView(b2dCam);
		tmr.render();
		
		b2ddr.render(world, b2dCam.combined); 
	}

	@Override
	public void handleInput() {
		playerBody.setLinearVelocity(0, 0);
		
		if (GameInput.isDown(GameInput.UP) ){//&& cl.isPlayerOnGround()) {
			playerBody.setLinearVelocity(0, MOVEMENT_SPEED);
		}

		if (GameInput.isDown(GameInput.LEFT)) {
			playerBody.setLinearVelocity(-MOVEMENT_SPEED, 0);
		}

		if (GameInput.isDown(GameInput.RIGHT)) {
			playerBody.setLinearVelocity(MOVEMENT_SPEED, 0);
		}

		if (GameInput.isDown(GameInput.DOWN) ){
			playerBody.setLinearVelocity(0, -MOVEMENT_SPEED);
		}

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
	}
}
