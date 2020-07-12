package com.naat.nix.user.util;

import com.naat.nix.menu.controller.FoodService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StringUtils;
import org.springframework.stereotype.Service;

import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.io.File;
import java.nio.file.Files;

/* Clase para la creación de archivos */
@Service
public class FileService {

    /* Referencia a la ubicación donde se guardan las imagenes */
    @Value("${upload.path}")
    public String uploadDir;

    /* Manipulación de platillos */
    @Autowired
    private FoodService foodService;

    /**
    * Guarda el archivo en la carpeta especificada por
    * uploadDir y regresa el nombre del archivo donde se guardó.
    * @param file el archivo de la imagen.
    * @return el nombre del archivo.
    */
    public String uploadFile(MultipartFile file){
        String path = null;
        String fileName = null;
        try {
            path = getPath (uploadDir + File.separator + StringUtils.cleanPath(file.getOriginalFilename()));
            fileName = getFileName (path);
            Path copyLocation = Paths.get(path);
            Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
          e.printStackTrace();
        }
        return fileName;
    }

    /**
    * Remueve el archivo identificado por el nombre.
    * @param filename el nombre del archivo a eliminar.
    */
    public void removeFile (String filename) {
      if (filename == null || filename == "") {
        return;
      }
      try {
        String path = uploadDir + File.separator + filename;
        Path removeLocation = Paths.get(path);
        Files.delete (removeLocation);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    /**
    * Crea una ruta para el archivo, si ya existe,
    * crea una copia de la ruta y le agrega un numero.
    * @param rt la ruta inicial para el archivo.
    * @return la ruta donde se guardó el archivo.
    */
    public String getPath (String rt) {
      if (foodService.getFoodByImage(getFileName(rt)) == null) {
        return rt;
      }
      String tmp = rt;
      int formatIndex = getFormatIndex (tmp);
      String fmt = "." + tmp.substring (formatIndex + 1, tmp.length());
      for (int i = 1;; i++) {
        formatIndex = getFormatIndex (tmp);
        tmp = tmp.substring(0, formatIndex) + Integer.toString(i) + fmt;
        if (foodService.getFoodByImage(getFileName(tmp)) == null) {
          break;
        }
      }
      return tmp;
    }

    /**
    * Obtiene la posicion a partir de la cual se indica el formato de la imagen.
    * p.ej "bruce_lee.jpg" -> 9
    * @param file el nombre de la imagen.
    * @return la posición a partir de la cual se indica el formato de la imagen.
    */
    public int getFormatIndex (String file) {
      int i = file.length() - 1;
      while (file.charAt(i) != '.') {
        i--;
      }
      return i;
    }


    /**
    * Obtiene el nombre del archivo guardado por la ruta.
    * @param path la ruta donde se guardó el archivo.
    * @return el nombre del archivo.
    */
    public String getFileName (String path) {
      int t;
      char c;
      for (t = path.length() - 1 ; t > 0 ; t--) {
        c = path.charAt(t);
        if (Character.toString(c).equals(File.separator)) {
          break;
        }
      }
      String archivo = path.substring(t+1, path.length());
      return archivo;
    }

}
