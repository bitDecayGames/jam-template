package com.bitdecay.game.system;

import com.bitdecay.game.room.AbstractRoom;
import com.bitdecay.game.trait.IUpdate;

/**
 * An updatable system
 */
public abstract class AbstractUpdatableSystem extends AbstractSystem implements IUpdate {

    public AbstractUpdatableSystem(AbstractRoom room) {
        super(room);
    }
}
