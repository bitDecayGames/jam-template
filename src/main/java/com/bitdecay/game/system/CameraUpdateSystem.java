package com.bitdecay.game.system;

import com.bitdecay.game.gameobject.MyGameObject;
import com.bitdecay.game.room.AbstractRoom;
import com.bitdecay.game.trait.IUpdateWithCamera;

public class CameraUpdateSystem extends AbstractForEachGobSystem {
    public CameraUpdateSystem(AbstractRoom room) {
        super(room);
    }

    @Override
    protected boolean validateGob(MyGameObject gob) {
        return gob.hasComponent(IUpdateWithCamera.class);
    }

    @Override
    protected void forEach(float delta, MyGameObject gob) {
        gob.forEach(IUpdateWithCamera.class, comp -> comp.update(delta, room.camera));
    }

}
