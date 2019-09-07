package com.sample.rest.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MultipleFileRequest {

  private List<MultipartFile> docs;

  public List<MultipartFile> getDocs() {
    return Objects.nonNull(docs) ? docs : new ArrayList<>();
  }

  public void setDocs(List<MultipartFile> docs) {
    this.docs = docs;
  }
}
