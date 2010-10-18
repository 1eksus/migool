package migool.util;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import javax.imageio.ImageIO;

/**
 * 
 * @author Denis Migol
 * 
 */
public class ImageUtil {

	private static final Set<String> IMAGE_FORMATS = new HashSet<String>();

	static {
		IMAGE_FORMATS.add("jpeg");
		IMAGE_FORMATS.add("png");
		IMAGE_FORMATS.add("gif");
		IMAGE_FORMATS.add("bmp");
		IMAGE_FORMATS.add("wbmp");
	}

	public static String getFormatNameByFilename(final String filename) {
		String ret = null;
		final int index = filename.lastIndexOf('.');
		if (index >= 0 && index < (filename.length() - 1)) {
			ret = filename.substring(index + 1).toLowerCase();
		}
		if (ret == null || "jpg".equalsIgnoreCase(ret) || !IMAGE_FORMATS.contains(ret)) {
			ret = "jpeg";
		}
		return ret;
	}

	public static BufferedImage resize(final Image image, final int newWidth, final int newHeight,
			final boolean preserveAlpha) {
		final int imageType = preserveAlpha ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
		final BufferedImage scaledBI = new BufferedImage(newWidth, newHeight, imageType);
		final Graphics2D g = scaledBI.createGraphics();
		if (preserveAlpha) {
			g.setComposite(AlphaComposite.Src);
		}
		g.drawImage(image, 0, 0, newWidth, newHeight, null);
		g.dispose();
		return scaledBI;
	}

	public static BufferedImage resize(final BufferedImage image, final int newSize, final boolean preserveAlpha) {
		final int width = image.getWidth();
		final int height = image.getHeight();
		int newWidth;
		int newHeight;
		if (width > height) {
			newWidth = newSize;
			newHeight = newWidth * height / width;
		} else {
			newHeight = newSize;
			newWidth = newHeight * width / height;
		}
		return resize(image, newWidth, newHeight, preserveAlpha);
	}

	public static boolean concatHorizontal(final BufferedImage im1, final BufferedImage im2, final File out,
			final String formatName) {
		try {
			if (im1.getHeight() != im2.getHeight()) {
				return false;
			}
			final int h = im1.getHeight();
			final int w1 = im1.getWidth();
			final int w2 = im2.getWidth();

			final int[] int1 = im1.getRGB(0, 0, w1, h, null, 0, w1);
			final int[] int2 = im2.getRGB(0, 0, w2, h, null, 0, w2);

			final int[] intOut = new int[int1.length + int2.length];

			int off = 0;
			int yoff = 0;
			for (int y = 0; y < h; y++) {
				yoff = y * w1;
				for (int x = 0; x < w1; x++) {
					intOut[off++] = int1[yoff + x];
				}
				yoff = y * w2;
				for (int x = 0; x < w2; x++) {
					intOut[off++] = int2[yoff + x];
				}
			}
			final BufferedImage imOut = new BufferedImage(w1 + w2, h, BufferedImage.TYPE_INT_RGB);
			imOut.setRGB(0, 0, w1 + w2, h, intOut, 0, w1 + w2);
			ImageIO.write(imOut, formatName, out);
			return true;
		} catch (final Exception e) {
		}
		return false;
	}

	public static boolean concatHorizontal(final URL url1, final URL url2, final File out, final String formatName) {
		try {
			final BufferedImage im1 = ImageIO.read(url1);
			final BufferedImage im2 = ImageIO.read(url2);

			return concatHorizontal(im1, im2, out, formatName);
		} catch (final Exception e) {
		}
		return false;
	}

	public static boolean concatHorizontal(final File file1, final File file2, final File out, final String formatName) {
		try {
			final BufferedImage im1 = ImageIO.read(file1);
			final BufferedImage im2 = ImageIO.read(file2);

			return concatHorizontal(im1, im2, out, formatName);
		} catch (final Exception e) {
		}
		return false;
	}

	public static boolean concatVertical(final BufferedImage im1, final BufferedImage im2, final File out,
			final String formatName) {
		try {
			if (im1.getWidth() != im2.getWidth()) {
				return false;
			}
			final int h1 = im1.getHeight();
			final int h2 = im2.getHeight();
			final int w = im1.getWidth();

			final int[] int1 = im1.getRGB(0, 0, w, h1, null, 0, w);
			final int[] int2 = im2.getRGB(0, 0, w, h2, null, 0, w);

			final int[] intOut = new int[int1.length + int2.length];

			int xoff = 0;
			for (int x = 0; x < w; x++) {
				xoff = x * h1;
				for (int y = 0; y < h1; y++) {
					intOut[xoff + y] = int1[xoff + y];
				}
				// xoff += w;
				xoff = x * h2;
				for (int y = 0; y < h2; y++) {
					intOut[h1 * w + xoff + y] = int2[xoff + y];
				}
			}
			final BufferedImage imOut = new BufferedImage(w, h1 + h2, BufferedImage.TYPE_INT_RGB);
			imOut.setRGB(0, 0, w, h1 + h2, intOut, 0, w);
			ImageIO.write(imOut, formatName, out);
			return true;
		} catch (final Exception e) {
		}
		return false;
	}

	public static boolean concatVertical(final URL url1, final URL url2, final File out, final String formatName) {
		try {
			final BufferedImage im1 = ImageIO.read(url1);
			final BufferedImage im2 = ImageIO.read(url2);

			return concatVertical(im1, im2, out, formatName);
		} catch (final Exception e) {
		}
		return false;
	}

	public static boolean concatVertical(final File file1, final File file2, final File out, final String formatName) {
		try {
			final BufferedImage im1 = ImageIO.read(file1);
			final BufferedImage im2 = ImageIO.read(file2);

			return concatVertical(im1, im2, out, formatName);
		} catch (final Exception e) {
		}
		return false;
	}
}