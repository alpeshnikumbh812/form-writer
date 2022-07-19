package com.example.formwriter.serviceImpl;

import net.coobird.thumbnailator.Thumbnails;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class PDFToImage {

     public void covertPDFtoImage(String path,String fileName,int pages) throws IOException {
         PDDocument document = PDDocument.load(new File(path));
//            List<PDPage> pages = document.getDocumentCatalog().getPages();
//            System.out.println(pages);

        String imagePath = "D:\\Projects\\form-writer\\src\\main\\resources\\assets\\images\\";

        if(fileName.contains("_modify.pdf"))
            fileName = fileName.substring(0,fileName.length()-11);
        else{
            fileName = fileName.substring(0,fileName.length()-4);
        }

         System.out.println("File Name " + fileName);
         PDFRenderer pdfRenderer = new PDFRenderer(document);
        for(int i =0;i<pages;i++) {

            BufferedImage bim = pdfRenderer.renderImageWithDPI(i, 300, ImageType.RGB);

            System.out.println(pdfRenderer + " " + bim.getHeight());
            ImageIOUtil.writeImage(bim, imagePath + fileName + (i + 1) + ".jpg", 300);

            if(bim.getWidth()!=2479)
            resize(imagePath+fileName+(i+1) + ".jpg",imagePath+fileName+(i+1) + ".jpg",2479,3508);

        }
         document.close();
     }

    public static void resize(String inputImagePath,
                              String outputImagePath, int scaledWidth, int scaledHeight)
            throws IOException {
        // reads input image
        File inputFile = new File(inputImagePath);
        BufferedImage inputImage = ImageIO.read(inputFile);
        System.out.println("In resize :");
        // creates output image
        BufferedImage outputImage = new BufferedImage(scaledWidth,
                scaledHeight, inputImage.getType());

        // scales the input image to the output image
        Graphics2D g2d = outputImage.createGraphics();
        g2d.drawImage(inputImage, 0, 0, scaledWidth, scaledHeight, null);
        g2d.dispose();

        // extracts extension of output file
//        String formatName = outputImagePath.substring(outputImagePath
//                .lastIndexOf(".") + 1);

        // writes to output file
        ImageIOUtil.writeImage(outputImage,outputImagePath,300);
    }
}
