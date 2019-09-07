package com.sample.rest.util;

import com.sample.rest.exception.FileNotFoundException;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

public final class FileUtil {

  private static final Logger LOGGER = LogManager.getLogger(FileUtil.class);

  private FileUtil() {
  }

  public static String persist(MultipartFile file, String location, String fileName) {
    if (Objects.isNull(file)) {
      return StringUtils.EMPTY;
    }
    try {
      Path path = normalizePath(location + File.separator + fileName);
      path.toFile().mkdirs();
      LOGGER.info("Saving file {}", path);
      Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
      LOGGER.debug("Saved file at location {}", path);
      return path.toAbsolutePath().toString();
    } catch (Exception e) {
      throw new FileNotFoundException("Error while saving file", e);
    }

  }

  private static Path normalizePath(String location) {
    if (StringUtils.isBlank(location)) {
      throw new FileNotFoundException("Location provided for normalization is blank");
    }
    return Paths.get(location).toAbsolutePath().normalize();
  }
}
