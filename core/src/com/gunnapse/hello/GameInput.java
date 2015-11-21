package com.gunnapse.hello;

public class GameInput {

	private static boolean[] keys;
	private static boolean[] pressedKeys;
	
	private static final int NUM_KEYS = 4;
	public static final int UP = 0;
	public static final int LEFT = 1;
	public static final int RIGHT = 2;
	public static final int DOWN = 3;
	
	static {
		keys = new boolean[NUM_KEYS];
		pressedKeys = new boolean[NUM_KEYS];
	}
	
	public static void update() {
		for(int i = 0; i < NUM_KEYS; i++) {
			pressedKeys[i] = keys[i];
		}
	}
	
	public static void setKey(int i, boolean b) {
		keys[i] = b;
	}

	public static boolean isDown(int i) {
		return keys[i];
	}

	public static boolean isPressed(int i) {
		return keys[i] && !pressedKeys[i];
	}

}
