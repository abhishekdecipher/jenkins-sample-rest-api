package com.sample.rest.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {

  void save(List<MultipartFile> fileList);
}
