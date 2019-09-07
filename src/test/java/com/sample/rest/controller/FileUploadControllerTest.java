package com.sample.rest.controller;

import com.sample.rest.service.impl.FileServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.spy;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class FileUploadControllerTest {


  private MockMvc mockMvc;
  private FileServiceImpl fileService;
  private FileUploadController fileUploadController;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);

    fileService = Mockito.mock(FileServiceImpl.class);
    fileUploadController = spy(new FileUploadController(fileService));
    this.mockMvc = MockMvcBuilders.standaloneSetup(fileUploadController).build();
  }

  @Test
  public void uploadFiles() throws Exception {
    MockMultipartFile file = new MockMultipartFile("fileUpload", "hello.txt", MediaType.TEXT_PLAIN_VALUE, "hello".getBytes());

    mockMvc.perform(MockMvcRequestBuilders.multipart("/upload")
      .file("fileUpload", file.getBytes()).characterEncoding("UTF-8"))
      .andDo(print())
      .andExpect(status().isOk());
  }
}