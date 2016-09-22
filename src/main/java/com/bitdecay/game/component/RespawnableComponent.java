package com.bitdecay.game.component;

import com.badlogic.gdx.math.Vector2;
import com.bitdecay.game.gameobject.MyGameObject;

/**
 * The component in charge of tracking the x and y position of the respawn point of the object.
 */
public class RespawnableComponent extends AbstractComponent {
    private float x = 0;
    private float y = 0;

    public RespawnableComponent(MyGameObject obj){super(obj);}
    public RespawnableComponent(MyGameObject obj, float x, float y){
        super(obj);
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
