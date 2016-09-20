package com.bitdecay.game.util;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.bitdecay.game.Launcher;
import com.bitdecay.game.MyGame;
import com.bitdecay.jump.gdx.level.EditorIdentifierObject;
import com.typesafe.config.ConfigObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class Tilesets {
    private Tilesets(){}

    private static List<Tileset> tilesets;

    private static void initTilesets(){
        if (tilesets == null){
            tilesets = new ArrayList<>();
            Launcher.conf.getList("tilesets").forEach(confValue -> {
                if (confValue instanceof ConfigObject){
                    ConfigObject confObj = (ConfigObject) confValue;
                    String name = null;
                    if (confObj.containsKey("name")) name = (String) confObj.get("name").unwrapped();
                    String path = null;
                    if (confObj.containsKey("path")) path = (String) confObj.get("path").unwrapped();
                    int material = 0;
                    if (confObj.containsKey("material")) material = (int) confObj.get("material").unwrapped();
                    if (name != null && path != null) {
                        List<TextureRegion> regions = Arrays.asList(MyGame.ATLAS.findRegions(path).toArray());
                        Tileset t = new Tileset(name, material, regions);
                        tilesets.add(t);
                    }
                }
            });
        }
    }

    public static List<EditorIdentifierObject> editorTilesets(){
        initTilesets();
        return tilesets.stream().map(ts -> new EditorIdentifierObject(ts.material(), ts.name(), ts.icon())).collect(Collectors.toList());
    }

    public static List<Tileset> tilesets(){
        initTilesets();
        List<Tileset> t = new ArrayList<>();
        t.addAll(tilesets);
        return t;
    }

    public static Tileset tilesetForMaterial(int material){
        return tilesets.stream().filter(t -> t.material == material).findFirst().get();
    }

    public static class Tileset {
        private String name;
        private int material;
        private List<TextureRegion> regions;

        public Tileset(String name, int material, List<TextureRegion> regions){
            this.name = name;
            this.material = material;
            this.regions = regions;
        }

        public String name() {
            return name;
        }

        public int material() {
            return material;
        }

        public List<TextureRegion> regions() {
            return regions;
        }

        public TextureRegion icon() {
            return regions.get(0);
        }
    }
}
