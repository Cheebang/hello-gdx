package com.gunnapse.hello;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class GameState {

	protected GameStateManager gsm;
	protected MyGdxGame game;
	protected SpriteBatch sb;
	protected OrthographicCamera camera;
	protected OrthographicCamera hudCam;
	
	public GameState(GameStateManager gsm) {
		this.gsm = gsm;
		game = gsm.getGame();
		sb = game.getSb();
		camera = game.getCamera();
		hudCam = game.getHudCam();
	}
	
	public abstract void update(float dt);
	public abstract void render();
	public abstract void handleInput();
	public abstract void dispose();
}
