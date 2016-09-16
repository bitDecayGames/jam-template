package com.bitdecay.game;

import com.badlogic.gdx.Game;
import com.bitdecay.jump.leveleditor.render.LevelEditor;
import com.bitdecay.game.utils.RunMode;

public class EditorApp extends Game {

    @Override
    public void create() {
        // Change to our game screen
        MyGame game = new MyGame(RunMode.DEV);
        game.create();
        GameScreen screen = new GameScreen(game);
        setScreen(new LevelEditor(screen));
    }

}