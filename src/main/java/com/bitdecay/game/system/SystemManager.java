package com.bitdecay.game.system;

import com.bitdecay.game.trait.ICleanup;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SystemManager implements ICleanup{

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
    public boolean dirty() {
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
}
