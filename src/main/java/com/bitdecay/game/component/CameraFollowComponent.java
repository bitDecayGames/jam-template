package com.bitdecay.game.component;

import com.bitdecay.game.camera.FollowOrthoCamera;
import com.bitdecay.game.gameobject.MyGameObject;

public class CameraFollowComponent extends AbstractComponent {
    public CameraFollowComponent(MyGameObject obj) {
        super(obj);
    }

    public void updateCamera(FollowOrthoCamera cam){
        if (cam != null) obj.getComponent(PositionComponent.class).ifPresent(pos -> cam.addFollowPoint(pos.toVector2()));
    }
}
