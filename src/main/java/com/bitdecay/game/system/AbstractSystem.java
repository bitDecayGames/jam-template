package com.bitdecay.game.system;

import com.bitdecay.game.gameobject.MyGameObject;
import com.bitdecay.game.room.AbstractRoom;
import com.bitdecay.game.trait.IProcessable;
import com.bitdecay.game.trait.IRefreshable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractSystem implements IRefreshable, IProcessable {

    protected final AbstractRoom room;
    protected final List<MyGameObject> gobs = new ArrayList<>();

    public AbstractSystem(AbstractRoom room){
        this.room = room;
        this.room.systemManager.addSystem(this);
    }

    public final void refresh(List<MyGameObject> gobs){
        this.gobs.clear();
        this.gobs.addAll(gobs.stream().filter(this::validateGob).collect(Collectors.toList()));
    }

    protected abstract boolean validateGob(MyGameObject gob);

    public abstract void process(float delta);
}
