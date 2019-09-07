package com.sample.rest.controller;


import com.sample.rest.request.MultipleFileRequest;
import com.sample.rest.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class FileUploadController {

  private FileService fileService;

  @Autowired
  public FileUploadController(FileService fileService) {
    this.fileService = fileService;
  }

  @PostMapping(value = "/upload")
  public HttpStatus uploadFiles(@ModelAttribute MultipleFileRequest multipleFileRequest) {
    fileService.save(multipleFileRequest.getDocs());
    return HttpStatus.OK;
  }

}
