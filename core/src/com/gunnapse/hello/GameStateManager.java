package com.gunnapse.hello;

import java.util.Stack;

public class GameStateManager {

	private MyGdxGame game;
	private Stack<GameState> gameStates = new Stack<GameState>();

	public GameStateManager(MyGdxGame game) {
		this.game = game;
		this.gameStates.push(new Play(this));
	}
	
	public void update(float dt) {
		gameStates.peek().update(dt);
	}
	
	public void render() {
		gameStates.peek().render();
	}
	
	private GameState getState() {
		return gameStates.peek();
	}

	public MyGdxGame getGame() {
		return game;
	}
}
