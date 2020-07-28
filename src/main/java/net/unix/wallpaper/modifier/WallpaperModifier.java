package net.unix.wallpaper.modifier;

import java.io.File;
import java.util.HashMap;
import com.sun.jna.Native;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.win32.StdCallLibrary;
import com.sun.jna.win32.W32APIFunctionMapper;
import com.sun.jna.win32.W32APITypeMapper;

/**
 * @author Unix
 * @since 27.07.2020
 */

public class WallpaperModifier
{
    private final JNA jna = JNA.INSTANCE;

    public void setWallpaperBackground(final File file) {
        jna.SystemParametersInfo(new WinDef.UINT_PTR(0x14),
                new WinDef.UINT_PTR(0x00), file.getAbsolutePath(),
                new WinDef.UINT_PTR(0x03)
        );
    }

    public interface JNA extends StdCallLibrary
    {
        JNA INSTANCE = Native.load("user32", JNA.class,
                new HashMap<String, Object>() {
                    {
                        put(OPTION_TYPE_MAPPER, W32APITypeMapper.UNICODE);
                        put(OPTION_FUNCTION_MAPPER, W32APIFunctionMapper.UNICODE);
                    }
                });

        void SystemParametersInfo(final WinDef.UINT_PTR uiAction, final WinDef.UINT_PTR ui, final String pv, final WinDef.UINT_PTR WinIni);
    }
}