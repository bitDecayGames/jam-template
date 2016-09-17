package com.bitdecay.game.trait;

import com.bitdecay.game.camera.FollowOrthoCamera;

public interface IUpdateWithCamera {
    void update(float delta, FollowOrthoCamera camera);
}
