package com.bitdecay.game.editor;

import com.bitdecay.game.gameobject.MyGameObjectFactory;
import com.typesafe.config.Config;

public class LevelObjectFromConf extends LevelObjectFromMyGameObject {

    private Config conf;

    public LevelObjectFromConf(Config conf){
        super(MyGameObjectFactory.objectFromConf(null, conf.getString("name"), 0, 0));
        this.conf = conf;
    }

    // TODO: need the newInstance method here so that I can pass the conf object along
}
