package com.bitdecay.game.system;

import com.bitdecay.game.gameobject.MyGameObject;
import com.bitdecay.game.room.AbstractRoom;
import com.bitdecay.game.trait.IUpdate;

/**
 * The update system simply calls update on any matching components.  This is probably a bad example as far as Systems go.  It's a bad example because ALL of the game logic would be contained within the Component instead of the System.  Ideally, you put all of the game logic in the Systems and NONE in the Components.
 */
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
