package com.bitdecay.game.trait;

import com.bitdecay.game.room.AbstractRoom;

/**
 * Allows specific components that require initialization to be handled in a ECS way
 */
public interface IInitializable {
    boolean isInitialized();
    void initialize(AbstractRoom room);
}
