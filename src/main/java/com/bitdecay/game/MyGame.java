package com.bitdecay.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.bitdecay.game.screen.GameScreen;
import com.bitdecay.game.screen.SplashScreen;
import com.bitdecay.game.util.RunMode;
import com.bytebreakstudios.animagic.texture.AnimagicTextureAtlas;
import com.bytebreakstudios.animagic.texture.AnimagicTextureAtlasLoader;

public class MyGame extends Game {
    public static AssetManager assetManager = new AssetManager();
    public static AnimagicTextureAtlas atlas;
    public RunMode runMode;

    public MyGame(RunMode runMode){
        super();
        this.runMode = runMode;
    }

    public void queueAssetsForLoad() {
        assetManager.setLoader(AnimagicTextureAtlas.class, new AnimagicTextureAtlasLoader(new InternalFileHandleResolver()));
        assetManager.load("img/packed/main.atlas", AnimagicTextureAtlas.class);
    }

    @Override
    public void create() {
        queueAssetsForLoad();
        assetManager.finishLoading();
        atlas = assetManager.get("img/packed/main.atlas", AnimagicTextureAtlas.class);

        if (runMode == RunMode.DEV) setScreen(new GameScreen(this));
        else if (runMode == RunMode.PROD) setScreen(new SplashScreen(this));
    }
}
