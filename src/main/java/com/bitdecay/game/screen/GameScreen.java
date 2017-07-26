package com.bitdecay.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.bitdecay.game.Launcher;
import com.bitdecay.game.MyGame;
import com.bitdecay.game.room.DemoRoom;
import com.bitdecay.game.trait.ICanSetRoom;
import com.bitdecay.game.trait.ICanSetScreen;
import com.bitdecay.game.trait.IHasScreenSize;
import com.bitdecay.game.util.SoundLibrary;

/**
 * The game screen used to be the main source of game logic.  It is now more just like any other screen.  It allows for the game to switch to it, but the main logic is moved into the Room class.  In the same way you can switch from screen to screen with a reference to the MyGame object, you can switch from room to room with the GameScreen object.
 */
public class GameScreen implements Screen, IHasScreenSize, ICanSetScreen, ICanSetRoom {

    private MyGame game;

    private com.bitdecay.game.room.AbstractRoom room;

    public GameScreen(MyGame game){
        this.game = game;
        setRoom(new DemoRoom(this));
    }
    public GameScreen(MyGame game, com.bitdecay.game.room.AbstractRoom room){
        this.game = game;
        setRoom(room);
    }

    @Override
    public void show() {
        SoundLibrary.stopMusic(Launcher.conf.getString("splash.music"));
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if (room != null) room.render(delta);
    }

    @Override
    public void resize(int width, int height) { }

    @Override
    public void pause() { }

    @Override
    public void resume() { }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        if (room != null) room.dispose();
    }

    @Override
    public Vector2 screenSize() {
        return null; // TODO: need to implement a way to change the screen size
    }

    @Override
    public void setScreen(Screen screen) {
        game.setScreen(screen);
    }

    @Override
    public void setRoom(com.bitdecay.game.room.AbstractRoom room) {
        if (this.room != null) this.room.dispose();
        this.room = room;
    }
}
