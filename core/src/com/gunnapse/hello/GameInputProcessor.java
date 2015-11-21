package com.gunnapse.hello;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;

public class GameInputProcessor extends InputAdapter {

	private Map<Integer, Integer> keyMapping = new HashMap<>();
	
	public GameInputProcessor() {
		keyMapping.put(Keys.UP, GameInput.UP);
		keyMapping.put(Keys.LEFT, GameInput.LEFT);
		keyMapping.put(Keys.RIGHT, GameInput.RIGHT);
		keyMapping.put(Keys.DOWN, GameInput.DOWN);
	}
	
	@Override
	public boolean keyDown(int keycode) {
		setGameInputForKey(keycode, true);
		return true;
	}
	
	@Override
	public boolean keyUp(int keycode) {
		setGameInputForKey(keycode, false);
		return true;
	}

	private void setGameInputForKey(int keycode, boolean isKeyPressed) {
		for (int key : keyMapping.keySet()) {
			if (keycode == key) {
				GameInput.setKey(keyMapping.get(key), isKeyPressed);
			}
		}
	}
}
