package com.example.formwriter.serviceImpl;

import com.example.formwriter.dto.ProfileDTO;
import com.example.formwriter.dto.ProfileDetailsDTO;
import com.example.formwriter.entity.Controls;
import com.example.formwriter.serviceIntf.ProfileService;
import com.example.formwriter.serviceIntf.Service;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.*;
import org.apache.pdfbox.io.IOUtils;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Map;

@org.springframework.stereotype.Service
public class ServiceImpl implements Service {

    @Autowired
    ProfileService profileService;

    @Override
    public void getControls(ArrayList<ProfileDetailsDTO> controls) {
        System.out.println("Object : " + controls);

        try {

            int ba_y = 782;
            int fr_y = 1077;
            int ba_x = 600;
            int fr_x = 752;

            int profileId = controls.get(0).getProfileId();
            ProfileDTO profileDTO = profileService.getProfile(profileId);
            String pdfName =profileDTO.getProfilePdfPath();
            System.out.println("pdf name" + pdfName);
//            String filePath = "D:/Projects/React/form-filling/src/components/ac-opening-individual.pdf";
            String filePath = "D:\\Projects\\form-writer\\src\\main\\resources\\assets\\pdf\\"+pdfName;
            String destFile = "D:\\Projects\\form-writer\\src\\main\\resources\\assets\\pdf\\"+pdfName.substring(0,pdfName.length()-4)+"_modify.pdf";
            PdfReader pdfReader = new PdfReader(filePath);
            System.out.println("Page width : " + pdfReader.getPageSize(1).getWidth());
            PdfStamper pdfStamper = new PdfStamper(pdfReader, new FileOutputStream(destFile));



            BaseFont bf = BaseFont.createFont(BaseFont.COURIER_BOLD, BaseFont.CP1257, BaseFont.EMBEDDED);

            for (ProfileDetailsDTO control : controls) {
                PdfContentByte canvas = pdfStamper.getOverContent(control.getPageNum());
                canvas.setFontAndSize(bf, control.getFontSize());
                String value = control.getValue();
                int x = (ba_x * control.getX()) / fr_x;
                int y = ba_y - (ba_y * control.getY()) / fr_y;
//                int y = control.getY();
                if (control.getControlType().equals("box")) {
                    System.out.println("Hear1");
                    for (Character ch : value.toCharArray()) {
                        //canvas.rectangle(x, y, 20, 20);
                        canvas.showTextAligned(PdfContentByte.ALIGN_LEFT,""+ch,x,y,0);
//                        ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("" + ch), x, y, 0);
                        x = x + control.getGap();
                        System.out.println("Gap X : " + x);
                    }
                } else if (control.getControlType().equals("checkbox")) {
                    String imagePath = "D:/Projects/React/form-filling/src/components/check-sign.jpg";
                    Image img = Image.getInstance(imagePath);
                    img.setAbsolutePosition(x, y);
                    canvas.addImage(img);

                } else {
                    canvas.rectangle(x, y, 20, 20);
                    ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(value), x, y, 0);
                }

                System.out.println("x : " + x);
                System.out.println("y : " + y);

            }
            pdfReader.close();
            pdfStamper.close();

            PDFToImage pdfToImage = new PDFToImage();
            pdfToImage.covertPDFtoImage(destFile,pdfName.substring(0,pdfName.length()-4)+"_modify.pdf",profileDTO.getPdfPages());
        } catch (IOException | DocumentException e) {
            e.printStackTrace();
        }

    }

    @Override
    public byte[] getImageUrl(int profileId, int pageNum) throws IOException {

        String path = "D:\\Projects\\form-writer\\src\\main\\resources\\assets\\images\\";
        String imageName = profileService.getProfile(profileId).getProfilePdfPath();
        imageName = imageName.substring(0,imageName.length()-4);
        InputStream in = new FileInputStream(new File(path+imageName+pageNum+".jpg"));
        byte[] out=IOUtils.toByteArray(in);
        in.close();
        return out;
    }
}
