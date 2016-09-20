package com.bitdecay.game.system;

import com.bitdecay.game.gameobject.MyGameObject;
import com.bitdecay.game.room.AbstractRoom;
import com.bitdecay.game.trait.IDraw;

/**
 * The draw system is one of the few systems that extends AbstractSystem directly.  The reason for this is that there is a call to spritebatch begin and end within the process method.  You will notice however that the forEach method is called in between the begin and end calls.  When you extend the AbstractForEachGobSystem, that forEach call isn't necessary becasue it happens behind the scenes in the AbstractForEachGobSystem.process method.
 */
public class DrawSystem extends AbstractSystem {
    public DrawSystem(AbstractRoom room) { super(room); }

    @Override
    protected boolean validateGob(MyGameObject gob) {
        return gob.hasComponent(IDraw.class);
    }

    @Override
    public void process(float delta) {
        room.spriteBatch.setProjectionMatrix(room.camera.combined);
        room.spriteBatch.begin();
        gobs.forEach(gob -> gob.forEach(IDraw.class, drawable -> drawable.draw(room.spriteBatch)));
        room.spriteBatch.end();
    }
}
