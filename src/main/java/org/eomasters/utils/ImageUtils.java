package org.eomasters.utils;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Objects;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

public class ImageUtils {

  public static Image[] loadFramesFromGif(ImageInputStream imageInputStream) throws IOException {
    ArrayList<BufferedImage> frames = new ArrayList<>();
    ImageReader gifReader = ImageIO.getImageReadersByFormatName("gif").next();
    gifReader.setInput(imageInputStream);
    int width = gifReader.getWidth(0);
    int height = gifReader.getHeight(0);
    for (int i = 0; i < gifReader.getNumImages(true); i++) {
      BufferedImage frame = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
      frames.add(frame);
      frame.getGraphics().drawImage(gifReader.read(i), 0, 0, null);
    }
    return frames.toArray(new Image[0]);
  }

  public static Image[] loadNumberedImages(Class<?> aClass, String resourceStr, int start, int end) {
    ArrayList<Image> list = new ArrayList<>();
    for (int i = start; i <= end; i++) {
      try {
        try (InputStream resource = aClass.getResourceAsStream(
            resourceStr.replace("#", String.valueOf(i)))) {
          list.add(ImageIO.read(Objects.requireNonNull(resource)));
        }
      } catch (IOException ignored) {
      }
    }
    return list.toArray(new Image[0]);
  }
}
