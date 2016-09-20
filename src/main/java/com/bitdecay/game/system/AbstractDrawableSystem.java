package com.bitdecay.game.system;

import com.bitdecay.game.room.AbstractRoom;
import com.bitdecay.game.trait.IDrawWithCamera;

/**
 * A drawable system
 */
public abstract class AbstractDrawableSystem extends AbstractSystem implements IDrawWithCamera {

    public AbstractDrawableSystem(AbstractRoom room) {
        super(room);
    }
}
