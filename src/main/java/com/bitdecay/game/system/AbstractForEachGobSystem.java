package com.bitdecay.game.system;

import com.bitdecay.game.MyGame;
import com.bitdecay.game.gameobject.MyGameObject;

public abstract class AbstractForEachGobSystem extends AbstractSystem {

    public AbstractForEachGobSystem(MyGame game) {
        super(game);
    }

    @Override
    public void process(float delta) {
        gobs.forEach(gob -> forEach(delta, gob));
    }

    protected abstract void forEach(float delta, MyGameObject gob);
}
