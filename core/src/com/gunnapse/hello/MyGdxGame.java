package com.gunnapse.hello;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame implements ApplicationListener {
	
	private GameStateManager gsm;
	private SpriteBatch sb;
	private OrthographicCamera camera;
	private float accum = 0f;
    public static final float V_WIDTH = 400;
    public static final float V_HEIGHT = 400;
	
	public static final float STEP = 1/60f;
	
	private OrthographicCamera hudCam;
	
	@Override
	public void create() {	
        camera = new OrthographicCamera();
        camera.setToOrtho(false, V_WIDTH, V_HEIGHT);
		gsm = new GameStateManager(this);
		Gdx.input.setInputProcessor(new GameInputProcessor());
	}

	@Override
	public void dispose() {
		
	}

	@Override
	public void render() {	
		accum += Gdx.graphics.getDeltaTime();

		while (accum >= STEP) {
			accum -= STEP;
			gsm.update(STEP);
			gsm.render();
		}
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	public GameStateManager getGsm() {
		return gsm;
	}

	public void setGsm(GameStateManager gsm) {
		this.gsm = gsm;
	}

	public SpriteBatch getSb() {
		return sb;
	}

	public void setSb(SpriteBatch sb) {
		this.sb = sb;
	}

	public OrthographicCamera getCamera() {
		return camera;
	}

	public void setCamera(OrthographicCamera camera) {
		this.camera = camera;
	}

	public OrthographicCamera getHudCam() {
		return hudCam;
	}

	public void setHudCam(OrthographicCamera hudCam) {
		this.hudCam = hudCam;
	}
}
