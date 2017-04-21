package com.bitdecay.game.component;

/**
 * This component is mostly for the level editor for when you want to add a name to the level object.
 */
public class NameComponent extends AbstractComponent {
    private String name;

    public NameComponent(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
