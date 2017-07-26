package com.bitdecay.game.gameobject;

/**
 * The idea here is to provide a single place for you to add your game objects.  You know that the "Player" game object will have a PositionComponent, a SizeComponent, and a CameraFollowComponent.  So in a static method (maybe called buildPlayer) you want to create a generic MyGameObject and populate it with the correct components.
 */
public final class MyGameObjectFactory {
    private MyGameObjectFactory(){}

    public static MyGameObject objectFromConf(String name, float x, float y){
        // hmmm this is basically just a wrapper... i'm not sure if this is the right way to do it
        return MyGameObjectFromConf.objectFromConf(name, x, y);
    }
}
