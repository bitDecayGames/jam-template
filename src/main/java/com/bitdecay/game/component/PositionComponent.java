package com.bitdecay.game.component;

import com.badlogic.gdx.math.Vector2;

/**
 * The component in charge of tracking the x and y position of the object.
 */
public class PositionComponent extends AbstractComponent {
    public float x = 0;
    public float y = 0;

    public PositionComponent(float x, float y){
        this.x = x;
        this.y = y;
    }

    /**
     * Immutable
     * @return new Vector2
     */
    public Vector2 toVector2(){
        return new Vector2(x, y);
    }
}
