package com.sample.rest.service.impl;


import com.sample.rest.domain.Asset;
import com.sample.rest.exception.FileNotFoundException;
import com.sample.rest.repository.FileServiceRepository;
import com.sample.rest.service.FileService;
import com.sample.rest.util.FileUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;


@Service
public class FileServiceImpl implements FileService {

  private static final Logger LOGGER = LogManager.getLogger(FileServiceImpl.class);

  private String basePath;

  private FileServiceRepository repository;

  @Autowired
  public FileServiceImpl(FileServiceRepository repository) {
    this.repository = repository;
    this.basePath = System.getProperty("user.home") + File.separator + "jenkins-plugin" + File.separator;
  }

  @Override
  public void save(List<MultipartFile> fileList) {
    if (fileList.isEmpty()) {
      throw new FileNotFoundException("No files found in request for saving");
    }

    LOGGER.info("#{} files received in request for saving", fileList.size());

    fileList.forEach(file -> {
      Asset asset = new Asset();
      asset.setOriginalFilename(file.getOriginalFilename());
      repository.update(repository.save(asset).getId(), FileUtil.persist(file, basePath, asset.getId() + "_" + file.getOriginalFilename()));
    });

    LOGGER.info("#{} files successfully saved", fileList.size());
  }
}
