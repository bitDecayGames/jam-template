package com.bitdecay.game.trait;

import com.bitdecay.game.room.AbstractRoom;

/**
 * Allows specific components that require removal logic to be called when they are removed
 */
public interface IRemovable {
    void remove(AbstractRoom room);
}
