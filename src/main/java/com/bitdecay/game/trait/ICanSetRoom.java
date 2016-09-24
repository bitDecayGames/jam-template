package com.bitdecay.game.trait;

import com.bitdecay.game.room.AbstractRoom;

/**
 * Allow the object to set the current room
 */
public interface ICanSetRoom {
    void setRoom(AbstractRoom room);
}
