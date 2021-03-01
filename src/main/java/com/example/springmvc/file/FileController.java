package com.example.springmvc.file;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class FileController {

    @GetMapping("/files")
    public String fileUploadForm(Model model) {
        return "files/index";
    }

    @PostMapping("/files")
    public String fileUpload(@RequestParam MultipartFile file, RedirectAttributes attributes) {
        // save -> File Storage Server (GCP : DataStore, AWS : S3) 등에 가상으로 저장을 했다고 가정한다.
        String message = String.format("%s is uploaded", file.getOriginalFilename());
        attributes.addFlashAttribute("message", message);
        return "redirect:/files";
    }
}
