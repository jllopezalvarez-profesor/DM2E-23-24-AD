package es.jllopezalvarez.sakila.api.controllers;

import es.jllopezalvarez.sakila.api.dto.ImportCitiesResult;
import es.jllopezalvarez.sakila.api.services.ImportService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("import")
public class ImportController {
    private final ImportService importService;


    public ImportController(ImportService importService) {
        this.importService = importService;
    }

    @PostMapping(value = "cities", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody()
    public ResponseEntity<ImportCitiesResult> importCities(@RequestParam MultipartFile citiesXmlFile){
        return ResponseEntity.ok(importService.importCities(citiesXmlFile));
    }

}
