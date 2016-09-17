package com.bitdecay.game.gameobject;

import com.bitdecay.game.component.AbstractComponent;
import com.bitdecay.game.trait.ICleanup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class MyGameObject implements ICleanup {

    protected boolean dirty = false;

    private List<AbstractComponent> components = new ArrayList<>();
    private List<AbstractComponent> componentsToAdd = new ArrayList<>();
    private List<AbstractComponent> componentsToRemove = new ArrayList<>();

    public boolean hasComponents(Class<?>... componentClasses){
        return components.stream().map(Object::getClass).collect(Collectors.toList()).containsAll(Arrays.asList(componentClasses));
    }

    public boolean hasComponent(Class<?> componentClass){
        return components.stream().filter(componentClass::isInstance).findFirst().isPresent();
    }

    public <T extends AbstractComponent> Optional<T> getComponent(Class<T> componentClass){
        return components.stream().filter(componentClass::isInstance).findFirst().map(componentClass::cast);
    }

    public <T> void forEach(Class<T> componentClass, Consumer<T> doFunc){
        components.stream().filter(componentClass::isInstance).map(componentClass::cast).forEach(doFunc);
    }

    /**
     * This method does not actually add the component.  It just marks the component as one to be added before the next cycle.
     */
    public MyGameObject addComponent(AbstractComponent component){
        dirty = true;
        componentsToAdd.add(component);
        return this;
    }

    /**
     * This method does not actually remove the component.  It just marks the component as one to be removed before the next cycle.
     */
    public <T extends AbstractComponent> Optional<T> removeComponent(Class<T> componentClass){
        Optional<T> comp = getComponent(componentClass);
        if (comp.isPresent()) {
            componentsToRemove.add(comp.get());
            dirty = true;
        }
        return comp;
    }

    @Override
    public boolean dirty() {
        return dirty;
    }

    @Override
    public void cleanup() {
        forEach(ICleanup.class, ICleanup::cleanup);
        componentsToRemove.forEach(components::remove);
        componentsToRemove.clear();
        componentsToAdd.forEach(components::add);
        componentsToAdd.clear();
        dirty = false;
    }
}
