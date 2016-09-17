package com.bitdecay.game.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.bitdecay.game.Launcher;
import com.typesafe.config.ConfigList;
import com.typesafe.config.ConfigObject;

import java.io.File;
import java.util.HashMap;

public class SoundLibrary {
    private static final HashMap<String, SoundEffect> sounds = new HashMap<>();
    private static final HashMap<String, MusicEffect> musics = new HashMap<>();

    static {
        // ///////////////////////////
        // Sound Effects
        // ///////////////////////////
        File fxDir = new File("src/main/resources/sound/fx");
        float defaultFxVolume = new Double(Launcher.conf.getDouble("sounds.defaultFxVolume")).floatValue();
        // add all the fx files with a default volume
        if (fxDir.isDirectory() && fxDir.exists()) for (File fxFile : fxDir.listFiles()) {
            if (fxFile != null && fxFile.exists()){
                sounds.put(new FileHandle(fxFile).nameWithoutExtension(), new SoundEffect(defaultFxVolume));
            }
        }
        // now loop through the defined sounds and adjust their volume
        ConfigList fxs = Launcher.conf.getList("sounds.fx");
        fxs.forEach(configValue -> {
            if (configValue instanceof ConfigObject){
                ConfigObject fx = (ConfigObject) configValue;
                if (fx.containsKey("name") && fx.containsKey("volume")){
                    String fxName = (String) fx.get("name").unwrapped();
                    float fxVolume = new Double((double) fx.get("volume").unwrapped()).floatValue();
                    SoundEffect fxObj = getSound(fxName);
                    if (fxObj != null) fxObj.volume = fxVolume;
                }
            }
        });

        // ///////////////////////////
        // Music
        // ///////////////////////////
        File musicDir = new File("src/main/resources/sound/music");
        float defaultMusicVolume = new Double(Launcher.conf.getDouble("sounds.defaultMusicVolume")).floatValue();
        // add all the music files with a default volume
        if (musicDir.isDirectory() && musicDir.exists()) for (File musicFile : musicDir.listFiles()) {
            if (musicFile != null && musicFile.exists()){
                musics.put(new FileHandle(musicFile).nameWithoutExtension(), new MusicEffect(defaultMusicVolume));
            }
        }
        // now loop through the defined sounds and adjust their volume
        ConfigList musicsConf = Launcher.conf.getList("sounds.music");
        musicsConf.forEach(configValue -> {
            if (configValue instanceof ConfigObject){
                ConfigObject music = (ConfigObject) configValue;
                if (music.containsKey("name") && music.containsKey("volume")){
                    String musicName = (String) music.get("name").unwrapped();
                    float musicVolume = new Double((double) music.get("volume").unwrapped()).floatValue();
                    MusicEffect musicObj = getMusic(musicName);
                    if (musicObj != null) musicObj.volume = musicVolume;
                }
            }
        });
    }

    public static synchronized Sound playSound(String name) {
        return getSound(name).play();
    }

    public static synchronized Sound stopSound(String name) {
        return getSound(name).stop();
    }

    private static SoundEffect getSound(String name) {
        SoundEffect sound;

        sound = sounds.get(name);
        if (sound.sound == null) {
            sound.sound = Gdx.audio.newSound(Gdx.files.classpath("sound/fx/" + name + ".wav"));
        }

        return sound;
    }

    public static synchronized Music playMusic(String name) {
        return getMusic(name).play();
    }

    public static synchronized Music loopMusic(String name) {
        return getMusic(name).loop();
    }


    private static MusicEffect getMusic(String name) {
        MusicEffect music;

        music = musics.get(name);
        if (music.music == null) {
            FileHandle musicFile = Gdx.files.classpath("sound/music/" + name + ".mp3");
            if (!musicFile.exists()) {
                musicFile = Gdx.files.classpath("sound/music/" + name + ".wav");
            }
            music.music = Gdx.audio.newMusic(musicFile);
            musics.put(name, music);
        }

        return music;
    }

    public static void stopMusic(String name) {
        getMusic(name).music.stop();
    }


    private static class SoundEffect {
        public Sound sound;
        public float volume;

        public SoundEffect(Sound sound, float volume) {
            this.sound = sound;
            this.volume = volume;
        }

        public SoundEffect(float volume) {
            this.volume = volume;
        }

        public Sound play() {
            this.sound.play(this.volume);
            return this.sound;
        }

        public Sound stop() {
            this.sound.stop();
            return this.sound;
        }

        public String toString(){
            return "Volume=" + this.volume;
        }
    }

    private static class MusicEffect {
        public Music music;
        public float volume;

        public MusicEffect(Music music, float volume) {
            this.music = music;
            this.volume = volume;
        }

        public MusicEffect(float volume) {
            this.volume = volume;
        }

        public Music play() {
            this.music.play();
            this.music.setVolume(this.volume);
            return this.music;
        }

        public Music loop() {
            this.music.play();
            this.music.setVolume(this.volume);
            this.music.setLooping(true);
            return this.music;
        }

        public String toString(){
            return "Volume=" + this.volume;
        }
    }
}