package net.unix.wallpaper;

/**
 * @author Unix
 * @since 27.07.2020
 */

public class ApplicationContext {

    public static void main(String... args) {
        new WallpaperEngine()
                .onStart();
    }

}