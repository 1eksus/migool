package migool.util;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;

import migool.util.NetUtil;

public class ImageUtil {
	public static boolean saveImage(URL url, String path) {
		try {
			InputStream in = url.openStream();
			OutputStream out = new BufferedOutputStream(new FileOutputStream(
					path));
			// for (int b; (b = in.read()) != -1; ) {
			// out.write(b);
			// }
			int n;
			byte buffer[] = new byte[8192];
			for (;;) {
				n = buffer.length;
				n = in.read(buffer, 0, n);
				if (n < 0) {
					break;
				}
				out.write(buffer, 0, n);
				out.flush();
			}
			out.close();
			in.close();
			return true;
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		}
		return false;
	}

	public static boolean saveImage(String url, String path) {
		try {
			return saveImage(NetUtil.newURL(url), path);
		} catch (Exception e) {
		}
		return false;
	}

	public static boolean fileExist(String url) {
		try {
			NetUtil.newURL(url).openStream();
			return true;
		} catch (Exception e) {
		}
		return false;
	}

	public static boolean concatHorizontal(BufferedImage im1, BufferedImage im2, File out, String formatName) {
		try {
			if (im1.getHeight() != im2.getHeight()) {
				return false;
			}
			final int h = im1.getHeight();
			final int w1 = im1.getWidth();
			final int w2 = im2.getWidth();
			
			int[] int1 = im1.getRGB(0, 0, w1, h, null, 0, w1);
			int[] int2 = im2.getRGB(0, 0, w2, h, null, 0, w2);
			
			int[] intOut = new int[int1.length + int2.length];
			
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
			BufferedImage imOut = new BufferedImage(w1 + w2, h, BufferedImage.TYPE_INT_RGB);
			imOut.setRGB(0, 0, w1 + w2, h, intOut, 0, w1 + w2);
			ImageIO.write(imOut, formatName, out);
			return true;
		} catch (Exception e) {
		}
		return false;
	}
	
	public static boolean concatHorizontal(URL url1, URL url2, File out, String formatName) {
		try {
			BufferedImage im1 = ImageIO.read(url1);
			BufferedImage im2 = ImageIO.read(url2);
			
			return concatHorizontal(im1, im2, out, formatName);
		} catch (Exception e) {
		}
		return false;
	}
	
	public static boolean concatHorizontal(File file1, File file2, File out, String formatName) {
		try {
			BufferedImage im1 = ImageIO.read(file1);
			BufferedImage im2 = ImageIO.read(file2);
			
			return concatHorizontal(im1, im2, out, formatName);
		} catch (Exception e) {
		}
		return false;
	}
	
	public static boolean concatVertical(BufferedImage im1, BufferedImage im2, File out, String formatName) {
		try {
			if (im1.getWidth() != im2.getWidth()) {
				return false;
			}
			final int h1 = im1.getHeight();
			final int h2 = im2.getHeight();
			final int w = im1.getWidth();

			int[] int1 = im1.getRGB(0, 0, w, h1, null, 0, w);
			int[] int2 = im2.getRGB(0, 0, w, h2, null, 0, w);

			int[] intOut = new int[int1.length + int2.length];

			int xoff = 0;
			for (int x = 0; x < w; x++) {
				xoff = x * h1;
				for (int y = 0; y < h1; y++) {
					intOut[xoff + y] = int1[xoff + y];
				}
				//xoff += w;
				xoff = x * h2;
				for (int y = 0; y < h2; y++) {
					intOut[h1 * w + xoff + y] = int2[xoff + y];
				}
			}
			BufferedImage imOut = new BufferedImage(w, h1 + h2, BufferedImage.TYPE_INT_RGB);
			imOut.setRGB(0, 0, w, h1 + h2, intOut, 0, w);
			ImageIO.write(imOut, formatName, out);
			return true;
		} catch (Exception e) {
		}
		return false;
	}

	public static boolean concatVertical(URL url1, URL url2, File out, String formatName) {
		try {
			BufferedImage im1 = ImageIO.read(url1);
			BufferedImage im2 = ImageIO.read(url2);
			
			return concatVertical(im1, im2, out, formatName);
		} catch (Exception e) {
		}
		return false;
	}

	public static boolean concatVertical(File file1, File file2, File out, String formatName) {
		try {
			BufferedImage im1 = ImageIO.read(file1);
			BufferedImage im2 = ImageIO.read(file2);
			
			return concatVertical(im1, im2, out, formatName);
		} catch (Exception e) {
		}
		return false;
	}

	public static void main(String[] args) throws MalformedURLException, IOException {
		migool.GlobalOptions.setProxyServer("127.0.0.1:8081");
		
		String url0 = "http://thumbs.redtube.com/_thumbs/0000019/0019287/0019287_014.jpg";
		String url1 = "http://thumbs.redtube.com/_thumbs/0000019/0019287/0019287_015.jpg";
		String url2 = "http://thumbs.redtube.com/_thumbs/0000019/0019287/0019287_016.jpg";
		
		String f0 = LinkUtil.getFileName(url0);
		String f1 = LinkUtil.getFileName(url1);
		String f2 = LinkUtil.getFileName(url2);
		
		saveImage(url0, f0);
		saveImage(url1, f1);
		saveImage(url2, f2);
		
//		concatHorizontal(new File(f0), new File(f1), new File("h" + f0 + f1), "jpg");
//		concatHorizontal(new File("h" + f0 + f1), new File(f2), new File("h" + f0 + f1 + f2), "jpg");
		
		concatVertical(new File(f0), new File(f1), new File("v" + f0 + f1), "jpg");
		concatVertical(new File("v" + f0 + f1), new File(f2), new File("v" + f0 + f1 + f2), "jpg");
	}
}