package net.unix.wallpaper;

import net.unix.wallpaper.modifier.WallpaperModifier;
import net.unix.wallpaper.task.UpdaterTask;
import net.unix.wallpaper.util.ConditionsUtil;
import java.io.File;
import java.util.Timer;

/**
 * @author Unix
 * @since 27.07.2020
 */

public class WallpaperEngine
{
    private final WallpaperModifier wallpaperModifier = new WallpaperModifier();
    private final File dataFolder = new File("wallpaper_engine");

    public void onStart() {
        ConditionsUtil.check(!System.getProperty("os.name").toLowerCase().contains("win"), () -> {
            throw new RuntimeException("Not supported");
        });
        ConditionsUtil.check(!dataFolder.exists(), dataFolder::mkdirs);

        new Timer().scheduleAtFixedRate(new UpdaterTask(this), 2000L, 1000L);
    }

    public WallpaperModifier getWallpaperModifier() {
        return wallpaperModifier;
    }

    public File getDataFolder() {
        return dataFolder;
    }
}