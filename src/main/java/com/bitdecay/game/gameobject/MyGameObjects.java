package com.bitdecay.game.gameobject;

import com.bitdecay.game.trait.ICleanup;
import com.bitdecay.game.trait.IRefreshable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class MyGameObjects implements ICleanup {
    private boolean dirty = false;

    private final List<MyGameObject> gobs = new ArrayList<>();
    private final List<MyGameObject> gobsToAdd = new ArrayList<>();
    private final List<MyGameObject> gobsToRemove = new ArrayList<>();
    private final List<IRefreshable> refreshables = new ArrayList<>();


    public MyGameObjects(IRefreshable... refreshables){
        this.refreshables.addAll(Arrays.asList(refreshables));
    }

    public MyGameObjects add(MyGameObject gob){
        gobsToAdd.add(gob);
        dirty = true;
        return this;
    }

    public Optional<MyGameObject> remove(MyGameObject gob){
        if (gobs.contains(gob)){
            gobsToRemove.add(gob);
            dirty = true;
            return Optional.of(gob);
        }
        return Optional.empty();
    }

    public MyGameObjects forEach(Consumer<MyGameObject> forEach){
        gobs.forEach(forEach);
        return this;
    }

    @Override
    public boolean isDirty() {
        return gobs.stream().filter(MyGameObject::isDirty).findFirst().isPresent() || dirty;
    }

    @Override
    public void cleanup() {
        gobsToRemove.forEach(gobs::remove);
        gobsToRemove.clear();
        gobsToAdd.forEach(gobs::add);
        gobsToAdd.clear();
        gobs.forEach(MyGameObject::cleanup);
        dirty = false;
        refreshables.forEach(r -> r.refresh(gobs));
    }
}
