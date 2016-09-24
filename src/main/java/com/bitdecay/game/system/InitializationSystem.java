package com.bitdecay.game.system;

import com.bitdecay.game.gameobject.MyGameObject;
import com.bitdecay.game.room.AbstractRoom;
import com.bitdecay.game.system.abstracted.AbstractSystem;
import com.bitdecay.game.trait.IInitializable;

/**
 * This system is unique in the sense that it only runs logic on components that need initialization.  It does it in the validateGob section because that method does not get called every update, only when gobs change.
 */
public class InitializationSystem extends AbstractSystem {
    public InitializationSystem(AbstractRoom room) {
        super(room);
    }

    @Override
    protected boolean validateGob(MyGameObject gob) {
        // logic goes here because this method is only called when gobs changes
        gob.forEach(IInitializable.class, init -> {
            if (! init.isInitialized()) init.initialize(room); // this only gets called once
        });
        return false;
    }
}
