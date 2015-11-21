package com.gunnapse.hello;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

public class GameContactListener implements ContactListener {

	private boolean playerOnGround = false;

	@Override
	public void beginContact(Contact contact) {

	}

	@Override
	public void endContact(Contact contact) {
		Fixture fa = contact.getFixtureA();
		Fixture fb = contact.getFixtureB();

		if ("foot".equals(fa.getUserData()) || "foot".equals(fb.getUserData())) {
			playerOnGround = false;
		}
	}

	//Collision detection
	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		Fixture fa = contact.getFixtureA();
		Fixture fb = contact.getFixtureB();
		
		if ("foot".equals(fa.getUserData()) || "foot".equals(fb.getUserData())) {
			playerOnGround = true;
		}
	}

	//Collision handling
	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		// TODO Auto-generated method stub

	}

	public boolean isPlayerOnGround() {
		return playerOnGround;
	}

}
