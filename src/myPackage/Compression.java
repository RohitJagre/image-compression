/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myPackage;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;

/**
 *
 * @author Rohit Jagre
 */
public class Compression {
    public static void main(String[] args) throws IOException{
        File input = new File("hummingbird.jpg");
        BufferedImage image = ImageIO.read(input);
        
        File compressedImageFile = new File("0.5compressImage.jpg");
        OutputStream os = new FileOutputStream(compressedImageFile);
        
        Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("jpg");
        ImageWriter writer = writers.next();
        
        ImageOutputStream ios = ImageIO.createImageOutputStream(os);
        writer.setOutput(ios);
        
        ImageWriteParam param = writer.getDefaultWriteParam();
        
        param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        param.setCompressionQuality(0.5f);
        writer.write(null, new IIOImage(image,null,null), param);
        
        os.close();
        ios.close();
        writer.dispose();
    }
}
