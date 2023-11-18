package es.jllopezalvarez.llmm.formviewer.controllers;

import es.jllopezalvarez.tools.forminformation.models.FileInfo;
import es.jllopezalvarez.tools.forminformation.models.FilesFormData;
import es.jllopezalvarez.tools.forminformation.models.FormData;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("view-form")
public class FormProcessingController {

    @GetMapping("test-form-get")
    public String testFormGet() {
        return "test-form-get";
    }

    @GetMapping("test-form-post")
    public String testFormPost() {
        return "test-form-post";
    }

    @GetMapping("test-form-files")
    public String testFormFiles() {
        return "test-form-files";
    }


    @GetMapping("get")
    //@RequestMapping(value = "post", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String formGet(@RequestParam Map<String, String> items, Model model) {
        FormData formData = new FormData(RequestMethod.GET.name(), items);
        model.addAttribute(formData);
        return "view-form";
    }

    @RequestMapping(value = "post", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String formPost(@RequestParam Map<String, String> items, Model model) {
        FormData formData = new FormData(RequestMethod.POST.name(), items);
        model.addAttribute(formData);
        return "view-form";
    }

    @RequestMapping(value = "files", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String formPostFiles(@RequestParam Map<String, String> items, @RequestParam("file") MultipartFile[] files, Model model) {
        List<FileInfo> filesInfo = new ArrayList<>();
        for (MultipartFile file : files) {
            filesInfo.add(new FileInfo(file.getName(), file.getSize(), file.getContentType()));
        }
        FilesFormData formData = new FilesFormData(RequestMethod.POST.name(), items, filesInfo);
        model.addAttribute(formData);
        return "view-form-files";
    }
}
