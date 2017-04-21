package com.bitdecay.game.component;

/**
 * All components should extend this class
 */
public abstract class AbstractComponent {

    @Override
    public String toString(){return this.getClass().getSimpleName();}
}
