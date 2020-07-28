package net.unix.wallpaper.task;

import com.sun.management.OperatingSystemMXBean;
import net.unix.wallpaper.WallpaperEngine;
import net.unix.wallpaper.util.DateUtil;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.lang.management.ManagementFactory;
import java.text.DecimalFormat;
import java.util.TimerTask;

/**
 * @author Unix
 * @since 28.07.2020
 */

public class UpdaterTask extends TimerTask
{
    private final WallpaperEngine wallpaperEngine;
    private final DecimalFormat decimalFormat;

    public UpdaterTask(WallpaperEngine engine) {
        wallpaperEngine = engine;
        decimalFormat = new DecimalFormat("##.##");
    }

    @Override
    public void run() {
        try {
            final BufferedImage bufferedImage = ImageIO.read(new File(wallpaperEngine.getDataFolder(), "image.png"));
            final Font font = Font.createFont(Font.PLAIN, new File(wallpaperEngine.getDataFolder(), "font.ttf"))
                    .deriveFont(Font.PLAIN, 18);

            final Graphics2D graphics = bufferedImage.createGraphics();
            graphics.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            graphics.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
            graphics.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
            graphics.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
            graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            graphics.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            graphics.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
            graphics.setFont(font);
            graphics.setColor(new Color(255, 255, 255));

            final FontMetrics fontMetrics = graphics.getFontMetrics();
            final OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);
            final long currentTime = System.currentTimeMillis();
            final String time = DateUtil.getTime(currentTime),
                    date = DateUtil.getDate(currentTime),
                    cpu = decimalFormat.format(operatingSystemMXBean.getSystemCpuLoad()) + "%";

            graphics.drawString(time, 105 - fontMetrics.stringWidth(time) / 2, bufferedImage.getHeight() - 20);
            graphics.drawString(date, (bufferedImage.getWidth() - 90) - fontMetrics.stringWidth(date) / 2, bufferedImage.getHeight() - 20);
            graphics.drawString(cpu, (bufferedImage.getWidth() / 2) - fontMetrics.stringWidth(cpu) / 2, bufferedImage.getHeight() - 15);
            graphics.dispose();

            final File file = new File(wallpaperEngine.getDataFolder(), "image_updated.png");
            ImageIO.write(bufferedImage, "png", file);
            wallpaperEngine.getWallpaperModifier().setWallpaperBackground(file);
        } catch (final Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}