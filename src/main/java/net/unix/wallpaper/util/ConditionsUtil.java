package net.unix.wallpaper.util;

/**
 * @author Unix
 * @since 27.07.2020
 */

public final class ConditionsUtil
{
    private ConditionsUtil() {
    }

    public static void check(final boolean value, final Runnable runnable) {
        if (!value)
            return;

        runnable.run();
    }
}