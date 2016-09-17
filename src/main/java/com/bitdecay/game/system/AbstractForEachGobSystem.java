package com.bitdecay.game.system;

import com.bitdecay.game.gameobject.MyGameObject;
import com.bitdecay.game.room.AbstractRoom;

public abstract class AbstractForEachGobSystem extends AbstractSystem {

    public AbstractForEachGobSystem(AbstractRoom room) {
        super(room);
    }

    @Override
    public void process(float delta) {
        gobs.forEach(gob -> forEach(delta, gob));
    }

    protected abstract void forEach(float delta, MyGameObject gob);
}
