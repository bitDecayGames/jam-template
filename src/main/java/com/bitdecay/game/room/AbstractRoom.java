package com.bitdecay.game.room;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.bitdecay.game.Launcher;
import com.bitdecay.game.MyGame;
import com.bitdecay.game.camera.FollowOrthoCamera;
import com.bitdecay.game.gameobject.MyGameObjects;
import com.bitdecay.game.screen.GameScreen;
import com.bitdecay.game.system.SystemManager;
import com.bitdecay.game.trait.*;
import com.bitdecay.game.util.RunMode;
import com.bitdecay.jump.collision.BitWorld;
import com.bitdecay.jump.gdx.level.EditorIdentifierObject;
import com.bitdecay.jump.gdx.level.RenderableLevelObject;
import com.bitdecay.jump.level.Level;
import com.bitdecay.jump.leveleditor.EditorHook;
import com.bitdecay.jump.leveleditor.render.LibGDXWorldRenderer;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Arrays;
import java.util.List;

/**
 * The room object is an added layer to the GameScreen class.  Instead of having all of the game logic reside in the GameScreen, it now will reside in the AbstractRoom and the individual room implementations.  Think of it like: you have one room for each level in the game and one room for each boss fight in the game.  The abstract room takes care of all the updating and drawing of everything.  However, if you have special requirements, you can always override the update or draw methods.  Just make sure to call super.update() somewhere in your override.  Several configuration values are set at the top of this class using the conf files in resources/conf.  Each room is separate of every other room so you need to go through the initialization each time you create a new room.  The reason for this is to limit cross talk.  We don't want bugs that span multiple rooms...  That would make it very difficult to debug.
 */
public abstract class AbstractRoom implements IUpdate, IDraw, IHasScreenSize, ICanSetScreen, EditorHook, IDisposable {

    protected final GameScreen gameScreen;
    public final SystemManager systemManager = new SystemManager();
    protected final MyGameObjects gobs = new MyGameObjects(systemManager);
    protected final List<ICleanup> cleanupables = Arrays.asList(systemManager, gobs);

    public final SpriteBatch spriteBatch = new SpriteBatch();
    public final ShapeRenderer shapeRenderer = new ShapeRenderer();
    public final FollowOrthoCamera camera = new FollowOrthoCamera(Launcher.conf.getInt("resolution.camera.width"), Launcher.conf.getInt("resolution.camera.height"));

    protected final LibGDXWorldRenderer worldRenderer = new LibGDXWorldRenderer();
    protected final BitWorld world = new BitWorld();

    public AbstractRoom(GameScreen gameScreen, Level level){
        this.gameScreen = gameScreen;

        camera.maxZoom = new Double(Launcher.conf.getDouble("resolution.camera.maxZoom")).floatValue();
        camera.minZoom = new Double(Launcher.conf.getDouble("resolution.camera.minZoom")).floatValue();
        camera.snapSpeed = new Double(Launcher.conf.getDouble("resolution.camera.snapSpeed")).floatValue();
        camera.buffer = 100;

        world.setGravity(new Double(Launcher.conf.getDouble("world.gravity.x")).floatValue(), new Double(Launcher.conf.getDouble("world.gravity.y")).floatValue());

        levelChanged(level);
    }

    public void render(float delta){
        update(delta);
        draw(spriteBatch);
    }

    @Override
    public void update(float delta) {
        cleanupables.forEach(c -> {
            if (c.isDirty()) c.cleanup();
        });

        camera.update(delta);
        world.step(delta);

        systemManager.process(delta);
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        if (MyGame.RUN_MODE == RunMode.DEV) worldRenderer.render(world, camera);
    }

    @Override
    public Vector2 screenSize() {
        return this.gameScreen.screenSize();
    }

    @Override
    public void setScreen(Screen screen) {
        gameScreen.setScreen(screen);
    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
    }

    // /////////////////////////////////////
    // Level editor hook methods
    // /////////////////////////////////////

    @Override
    public void render(OrthographicCamera orthographicCamera) {
        throw new NotImplementedException();
    }

    @Override
    public final BitWorld getWorld() {
        return world;
    }

    @Override
    public final List<EditorIdentifierObject> getTilesets() {
        throw new NotImplementedException();
    }

    @Override
    public final List<EditorIdentifierObject> getThemes() {
        throw new NotImplementedException();
    }

    @Override
    public final List<RenderableLevelObject> getCustomObjects() {
        throw new NotImplementedException();
    }

    @Override
    public final void levelChanged(Level level) {
        world.removeAllBodies();
        world.setLevel(level);
    }

}
