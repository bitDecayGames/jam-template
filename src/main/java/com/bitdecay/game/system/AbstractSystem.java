package com.bitdecay.game.system;

import com.bitdecay.game.MyGame;
import com.bitdecay.game.gameobject.MyGameObject;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractSystem {

    protected final MyGame game;
    protected final List<MyGameObject> gobs = new ArrayList<>();

    public AbstractSystem(MyGame game){
        this.game = game;
    }

    public final void refresh(List<MyGameObject> gobs){
        this.gobs.clear();
        this.gobs.addAll(gobs.stream().filter(this::validateGob).collect(Collectors.toList()));
    }

    protected abstract boolean validateGob(MyGameObject gob);

    public abstract void process(float delta);
}
