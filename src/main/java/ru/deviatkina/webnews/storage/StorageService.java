package ru.deviatkina.webnews.storage;


import org.springframework.core.io.Resource;
import java.nio.file.Path;
import org.springframework.web.multipart.MultipartFile;

import java.util.stream.Stream;

public interface StorageService {
 //   void init();

    boolean store(MultipartFile file);

  //  Stream<Path> loadAll();

  //  Path load(String filename);

  //  Resource loadAsResource(String filename);

  //  void deleteAll();
}
