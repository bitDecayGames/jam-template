package com.bitdecay.game.component;

import com.badlogic.gdx.math.Vector2;
import com.bitdecay.game.room.AbstractRoom;
import com.bitdecay.game.trait.IInitializable;

/**
 * The component in charge of tracking the x and y position of the respawn point of the object.
 */
public class RespawnableComponent extends AbstractComponent implements IInitializable {
    private float x = 0;
    private float y = 0;
    private boolean initialized = false;

    private RespawnableComponent(float x, float y){
        this.x = x;
        this.y = y;
    }

    private RespawnableComponent(PositionComponent pos){
        x = pos.x;
        y = pos.y;
    }

    /**
     * Immutable
     * @return new Vector2
     */
    public Vector2 toVector2(){
        return new Vector2(x, y);
    }

    @Override
    public boolean isInitialized() {
        return initialized;
    }

    @Override
    public void initialize(AbstractRoom room) {
        initialized = true;
    }
}
