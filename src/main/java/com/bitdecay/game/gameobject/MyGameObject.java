package com.bitdecay.game.gameobject;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.bitdecay.game.camera.FollowOrthoCamera;
import com.bitdecay.game.component.AbstractComponent;
import com.bitdecay.game.trait.ICleanup;
import com.bitdecay.game.trait.IDraw;
import com.bitdecay.game.trait.IUpdate;
import com.bitdecay.game.trait.IUpdateWithCamera;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class MyGameObject implements IUpdate, IUpdateWithCamera, IDraw, ICleanup {

    private List<AbstractComponent> components = new ArrayList<>();
    private List<AbstractComponent> componentsToAdd = new ArrayList<>();
    private List<AbstractComponent> componentsToRemove = new ArrayList<>();

    public <T> void forEach(Class<T> componentClass, Consumer<T> doFunc){
        components.stream().filter(componentClass::isInstance).map(componentClass::cast).forEach(doFunc);
    }

    /**
     * This method does not actually add the component.  It just marks the component as one to be added before the next cycle.
     */
    public void addComponent(AbstractComponent component){
        componentsToAdd.add(component);
    }

    /**
     * This method does not actually remove the component.  It just marks the component as one to be removed before the next cycle.
     */
    public <T extends AbstractComponent> Optional<T> removeComponent(Class<T> componentClass){
        Optional<T> comp = getComponent(componentClass);
        if (comp.isPresent()) componentsToRemove.add(comp.get());
        return comp;
    }

    public <T extends AbstractComponent> Optional<T> getComponent(Class<T> componentClass){
        return components.stream().filter(componentClass::isInstance).findFirst().map(componentClass::cast);
    }

    public void processComponentsToRemove(){
        componentsToRemove.forEach(components::remove);
        componentsToRemove.clear();
    }

    @Override
    public void update(float delta) {
        forEach(IUpdate.class, comp -> comp.update(delta));
    }

    @Override
    public void update(float delta, FollowOrthoCamera camera) {
        forEach(IUpdateWithCamera.class, comp -> comp.update(delta, camera));
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        forEach(IDraw.class, comp -> comp.draw(spriteBatch));
    }

    @Override
    public void cleanup() {
        forEach(ICleanup.class, ICleanup::cleanup);
        componentsToRemove.forEach(components::remove);
        componentsToRemove.clear();
        componentsToAdd.forEach(components::add);
        componentsToAdd.clear();
    }
}
