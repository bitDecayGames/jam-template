package com.bitdecay.game.system;

import com.bitdecay.game.gameobject.MyGameObject;
import com.bitdecay.game.room.AbstractRoom;
import com.bitdecay.game.trait.IDraw;

public class DrawSystem extends AbstractSystem {
    public DrawSystem(AbstractRoom room) { super(room); }

    @Override
    protected boolean validateGob(MyGameObject gob) {
        return gob.hasComponent(IDraw.class);
    }

    @Override
    public void process(float delta) {
        room.spriteBatch.begin();
        gobs.forEach(gob -> gob.forEach(IDraw.class, drawable -> drawable.draw(room.spriteBatch)));
        room.spriteBatch.end();
    }
}
