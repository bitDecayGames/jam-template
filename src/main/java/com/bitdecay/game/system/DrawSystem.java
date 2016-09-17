package com.bitdecay.game.system;

import com.bitdecay.game.MyGame;
import com.bitdecay.game.gameobject.MyGameObject;
import com.bitdecay.game.trait.IDraw;

public class DrawSystem extends AbstractSystem {
    public DrawSystem(MyGame game) {
        super(game);
    }

    @Override
    protected boolean validateGob(MyGameObject gob) {
        return gob.hasComponent(IDraw.class);
    }

    @Override
    public void process(float delta) {
        game.spriteBatch.begin();
        gobs.forEach(gob -> gob.forEach(IDraw.class, drawable -> drawable.draw(game.spriteBatch)));
        game.spriteBatch.end();
    }
}
