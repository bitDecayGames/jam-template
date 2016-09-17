package com.bitdecay.game.trait;


import com.bitdecay.game.gameobject.MyGameObject;

import java.util.List;

public interface IRefreshable {
    void refresh(List<MyGameObject> gobs);
}
