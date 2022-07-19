package com.example.formwriter.serviceImpl;

import com.example.formwriter.dto.ProfileDTO;
import com.example.formwriter.entity.ProfileEntity;
import com.example.formwriter.repository.ProfileRepository;
import com.example.formwriter.serviceIntf.ProfileService;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfileServiceImpl implements ProfileService {


    @Autowired
    private NamedParameterJdbcTemplate npJdbcTemplete;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    ModelMapper modelMapper;

    Logger LOG = LoggerFactory.getLogger(ProfileServiceImpl.class);

    public Integer add(MultipartFile file, String profileName) throws IOException {


        ProfileEntity profileEntity = new ProfileEntity();

        String path = "D:\\Projects\\form-writer\\src\\main\\resources\\assets\\pdf\\" + file.getOriginalFilename();
        if(new File(path).exists()){
            new File(path).delete();
        }
        file.transferTo(new File(path));
        PDDocument doc = PDDocument.load(new File(path));
        System.out.println(doc);
        int count = doc.getNumberOfPages();
//        doc.close();

//        try{
//            Document document1 = new Document();
//            PdfWriter.getInstance(document1, new FileOutputStream(
//                    path));
//
//            document1.open();
//            System.out.println("Width : " + document1.getPageSize().getWidth() + " Height : " + document1.getPageSize().getWidth());
//            document1.close();
//        }
//        catch (Exception e){
//            System.out.println(e);
//        }
        doc.close();

        System.out.println();
        LOG.info("Pdf Pages : {}", count);
//        profileEntity.setPdfPages(count);
//        profileEntity.setProfilePdfPath(file.getOriginalFilename());
//        profileEntity.setProfileName(profileName);

        PDFToImage pdfToImage = new PDFToImage();
        pdfToImage.covertPDFtoImage(path, file.getOriginalFilename(), count);
//        profileEntity = profileRepository.save(profileEntity);


        LOG.info("File Name " + file.getOriginalFilename());
        LOG.debug("Profile  save {}", profileEntity);

//        return modelMapper.map(profileEntity, ProfileDTO.class);
//        return profileEntity.getProfileId();
        return null;
    }

    @Override
    public List<ProfileDTO> getList() {

//        List<ProfileDTO> profileDTOS = new ArrayList<>();
        List<ProfileEntity> profileEntities = profileRepository.findAll();
        System.out.println("Profile details");
        List<ProfileDTO> profileDTOS1 = profileEntities
                .stream()
                .map(user -> modelMapper.map(user, ProfileDTO.class))
                .collect(Collectors.toList());

        return profileDTOS1;
    }

    @Override
    public void uploadFile(MultipartFile file) throws IOException {

        file.transferTo(new File("D:\\Projects\\React\\form-filling\\src\\components\\" + file.getOriginalFilename()));
    }

    @Override
    public ProfileDTO getProfile(int profileId) {

        ProfileEntity profileEntity = profileRepository.findByProfileId(profileId);
        ProfileDTO profileDTO = modelMapper.map(profileEntity,ProfileDTO.class);
        if(profileEntity==null)
            return null;

        return profileDTO;
    }

    @Override
    public Resource downloadFile(int profileId) {
        ProfileEntity profileEntity = profileRepository.findByProfileId(profileId);

        String fileName = profileEntity.getProfilePdfPath();
        fileName = fileName.substring(0,fileName.length()-4);

        Path path = Paths.get("D:\\Projects\\form-writer\\src\\main\\resources\\assets\\pdf\\" + fileName+"_modify.pdf");
        Resource resource = null;
        try {
            resource = new UrlResource(path.toUri());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return resource;
    }
}
