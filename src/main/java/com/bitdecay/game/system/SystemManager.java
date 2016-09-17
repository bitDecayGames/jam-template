package com.bitdecay.game.system;

import com.bitdecay.game.gameobject.MyGameObject;
import com.bitdecay.game.trait.ICleanup;
import com.bitdecay.game.trait.IProcessable;
import com.bitdecay.game.trait.IRefreshable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SystemManager implements ICleanup, IRefreshable, IProcessable {

    private boolean dirty = false;
    private final List<AbstractSystem> systems = new ArrayList<>();
    private final List<AbstractSystem> systemsToRemove = new ArrayList<>();
    private final List<AbstractSystem> systemsToAdd = new ArrayList<>();

    public <T extends AbstractSystem> Optional<T> getSystem(Class<T> systemClass){
        return systems.stream().filter(systemClass::isInstance).findFirst().map(systemClass::cast);
    }

    public SystemManager addSystem(AbstractSystem system){
        systemsToAdd.add(system);
        dirty = true;
        return this;
    }

    public <T extends AbstractSystem> Optional<T> removeSystem(Class<T> systemClass){
        Optional<T> system = getSystem(systemClass);
        if (system.isPresent()) {
            systemsToRemove.add(system.get());
            dirty = true;
        }
        return system;
    }

    @Override
    public boolean isDirty() {
        return dirty;
    }

    @Override
    public void cleanup() {
        systemsToRemove.forEach(systems::remove);
        systemsToRemove.clear();
        systemsToAdd.forEach(systems::add);
        systemsToAdd.clear();
        dirty = false;
    }

    @Override
    public void refresh(List<MyGameObject> gobs) {
        systems.forEach(sys -> sys.refresh(gobs));
    }

    @Override
    public void process(float delta) {
        systems.forEach(sys -> sys.process(delta));
    }
}
