package com.bitdecay.game;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.bitdecay.game.util.RunMode;
import com.bytebreakstudios.animagic.texture.AnimagicTexturePacker;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import java.io.File;

public class Launcher {

    public static Config conf = ConfigFactory.load("conf/application.conf");

    public static void main(String[] args) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = Launcher.conf.getInt("resolution.default.width");
        config.height = Launcher.conf.getInt("resolution.default.height");
        config.title = Launcher.conf.getString("title");
        config.resizable = false;

        RunMode runMode = RunMode.PROD;
        if (args != null && args.length > 0) {
            // check for command line arguments
            if (arg(args, "dev")) runMode = RunMode.DEV;
        }
        System.out.println("Run Mode: " + runMode);
        if (runMode == RunMode.DEV){
            if (needToPack()) {
                System.out.println("Need to pack images");
                AnimagicTexturePacker.pack(new File("src/main/resources/img/packable"), new File("src/main/resources/img/packed"));
            } else System.out.println("Did not need to pack images");
        }

        new LwjglApplication(new MyGame(runMode), config);
    }

    private static boolean arg(String[] args, String arg){
        for (String cmd : args) if (cmd.equalsIgnoreCase(arg)) return true;
        return false;
    }

    /**
     * Only run the texture packer if the atlas file is older than any of the files within the img/packable folder
     */
    private static boolean needToPack(){
        File atlasFile = new File("src/main/resources/img/packed/main.atlas");
        if (!atlasFile.exists()) return true;
        long atlasFileModifiedDate = atlasFile.lastModified();

        File packableFolder = new File("src/main/resources/img/packable");
        if (!packableFolder.exists()) return false;
        long mostRecentLastModifiedDateInPacked = recursiveFileLastModified(packableFolder);

        return mostRecentLastModifiedDateInPacked > atlasFileModifiedDate;
    }

    private static long recursiveFileLastModified(File f){
        if (f == null) return 0;
        else if (!f.exists()) return 0;
        else if (f.isDirectory()) {
            long mostRecent = 0;
            for (File sub : f.listFiles()){
                long curMod = recursiveFileLastModified(sub);
                if (curMod > mostRecent) mostRecent = curMod;
            }
            return mostRecent;
        }
        else return f.lastModified();
    }
}
