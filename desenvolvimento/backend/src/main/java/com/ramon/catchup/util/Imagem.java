package com.ramon.catchup.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;

import com.ramon.catchup.exception.FileException;

public abstract class Imagem {

	  public static byte[] converterFotoBase64ToArrayByte(String fotoBase64) throws IOException {
	        BufferedImage imageConverter = decodeToImage(fotoBase64);
	        return (converterArryByte(imageConverter));
	    }

	  public static BufferedImage redimecionarImage(BufferedImage image) {
	        return Scalr.resize(image, Scalr.Method.ULTRA_QUALITY, image.getWidth(), image.getHeight());
	    }

	    
	  public static byte[] tratarImagem(String fotoBase64, String extencao) throws IOException {
          BufferedImage imageConverter = decodeToImage(fotoBase64);
          BufferedImage bufferedImage = getJpgImageFromFile(imageConverter, extencao);
          BufferedImage imagemRedimencionada = redimecionarImage(bufferedImage);

      return (converterArryByte(imagemRedimencionada));
  }

	  public static BufferedImage decodeToImage(String imageString) {
      BufferedImage image = null;
     
      byte[] imageByte;
      try {
  
          imageByte =  Base64.getDecoder().decode(imageString);
          ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
          image = ImageIO.read(bis);
          bis.close();
      } catch (Exception e) {
          e.printStackTrace();
      }
      return image;
  }

	  public static BufferedImage getJpgImageFromFile(BufferedImage file, String extencao) throws IOException {
      if (!"image/png".equals(extencao) && !"image/jpg".equals(extencao) && !"image/jpeg".equals(extencao)) {
          throw new FileException("Somente imagens PNG, JPG e JPEG são permitidas");
      }
      BufferedImage img = null;
      if ("image/png".equals(extencao)) {
          img = pngToJpg(file);
          return img;
      } else {
          return file;
      }
  }

	  public static byte[] converterArryByte(BufferedImage image) {
      try {
          ByteArrayOutputStream baos = new ByteArrayOutputStream();
          ImageIO.write(image, "jpg", baos);
          baos.flush();
          byte[] imageInByte = baos.toByteArray();
          baos.close();
          return imageInByte;
      } catch (IOException e) {
          System.out.println(e.getMessage());
      }
      return null;
  }

	  public static BufferedImage pngToJpg(BufferedImage img) {
      BufferedImage jpgImage = new BufferedImage(img.getWidth(), img.getHeight(),
              BufferedImage.TYPE_INT_RGB);

      return jpgImage;
  }

}
