package com.example.testresource.controller;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UncheckedIOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
  @Autowired
  ResourceLoader resourceLoader;

  @GetMapping("/")
  public ResponseEntity getFile() throws IOException {
    Resource resource = resourceLoader.getResource("classpath:test.txt");

    String result;
    try (Reader reader = new InputStreamReader(resource.getInputStream(), UTF_8)) {
      result = FileCopyUtils.copyToString(reader);
  } catch (IOException e) {
      throw new UncheckedIOException(e);
  }
    return ResponseEntity.ok(result);
  }

}
