package com.example.formwriter.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/flux")
public class FluxController {


    @Autowired
    WebClient webClient;

    Logger LOG = LoggerFactory.getLogger(FluxController.class);
    @PostMapping(value = "/add",consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<Map<String,Object>> add(@RequestParam MultipartFile file) throws IOException {

        LOG.info("profile-add");
//        profileService.add(file,profileName);

        MultipartBodyBuilder builder = new MultipartBodyBuilder();
        builder.part("file",new ByteArrayResource(file.getBytes(), file.getOriginalFilename()));
        LOG.info(file.getOriginalFilename());
        UriComponentsBuilder uriComponentsBuilder =  UriComponentsBuilder.fromHttpUrl("https://vmware-gs--gsluat.sandbox.my.salesforce.com/services/liveagent/file?chatKey=84b037b8-d387-49cd-82e3-aef02f1e2456&fileToken=L-b6b07af6-3a59-4c47-9b28-32f6c4ac6c6a&orgId=00D8I0000008jF6&encoding=UTF-8");
        Flux<String> response = webClient.post().uri(uriComponentsBuilder.build().toString()).contentType(MediaType.MULTIPART_FORM_DATA)
                .header(HttpHeaders.CONTENT_TYPE,MediaType.MULTIPART_FORM_DATA.toString())
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromMultipartData(builder.build())).retrieve().bodyToFlux(String.class);

        String webClientResponse = response.blockLast();
        LOG.info("response {}",response.blockLast());

        Map<String,Object> respons = new HashMap<>();

        respons.put("Success","Profile Save Successfully");

        return new ResponseEntity<>(respons, HttpStatus.OK);
    }

    @PostMapping(value = "/flux1")
    public ResponseEntity<String> add1() throws IOException {

        LOG.info("call Second Api");
        return new ResponseEntity<>("Name",HttpStatus.OK);
    }

}
