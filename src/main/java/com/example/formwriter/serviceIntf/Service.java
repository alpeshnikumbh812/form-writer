package com.example.formwriter.serviceIntf;

import com.example.formwriter.dto.ProfileDetailsDTO;
import com.example.formwriter.entity.Controls;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;

public interface Service {

     void getControls(ArrayList<ProfileDetailsDTO> controls);
     byte[] getImageUrl(int profileId,int pageNo) throws IOException;
}
