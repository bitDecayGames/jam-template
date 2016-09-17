package com.bitdecay.game.system;

import com.bitdecay.game.gameobject.MyGameObject;
import com.bitdecay.game.room.AbstractRoom;
import com.bitdecay.game.trait.IUpdate;

public class UpdateSystem extends AbstractForEachGobSystem {
    public UpdateSystem(AbstractRoom room) {
        super(room);
    }

    @Override
    protected boolean validateGob(MyGameObject gob) {
        return gob.hasComponent(IUpdate.class);
    }

    @Override
    protected void forEach(float delta, MyGameObject gob) {
        gob.forEach(IUpdate.class, comp -> comp.update(delta));
    }

}
