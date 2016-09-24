package com.bitdecay.game.component;

import com.bitdecay.game.gameobject.MyGameObject;

/**
 * Add this component to a MyGameObject to tell the camera to follow the obj
 */
public class CameraFollowComponent extends AbstractComponent {
    public CameraFollowComponent(MyGameObject obj) {
        super(obj);
    }
}
