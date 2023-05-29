package ua.com.alevel.controller.open;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ua.com.alevel.persistence.entity.software.Software;
import ua.com.alevel.service.impl.SoftwareServiceImpl;

import java.util.Optional;


@Controller
public class SoftwareController {
    private final SoftwareServiceImpl softwareService;

    public SoftwareController(SoftwareServiceImpl softwareService) {
        this.softwareService = softwareService;
    }

    @GetMapping("/software")
    public String software(Model model) {
        Iterable<Software> softwareList = softwareService.findAllOrdered();
        model.addAttribute("title", "Програмне забезпечення");
        model.addAttribute("description", "");
        model.addAttribute("software", softwareList);
        return "catalog";
    }

    @GetMapping("/software/{id}")
    public String software_id(@PathVariable Long id, Model model) {
        Optional<Software> software = softwareService.findById(id);
        if (!software.isPresent()) {
            System.out.println("ERROR");
            return "error";
        }
        Software currentSoftware = software.get();
        model.addAttribute("name", currentSoftware.getSoftwareName());
        model.addAttribute("catalog_id", currentSoftware.getId());
        model.addAttribute("brief_description", currentSoftware.getBriefDescription());
        model.addAttribute("description", currentSoftware.getDescription());
        model.addAttribute("operating_system", currentSoftware.getOperatingSystem());
        model.addAttribute("license", currentSoftware.getLicense());
        model.addAttribute("languages", currentSoftware.getLanguages());
        final StringBuilder buttons = new StringBuilder();
        if (currentSoftware.getDownload() != null) {
            try {
                JSONObject jsonObject = (JSONObject) new JSONParser().parse(currentSoftware.getDownload());
                jsonObject.forEach((os, link) -> {
                    buttons.append("<a class=\"btn rounded\" role=\"button\" target=\"_blank\" href=\"");
                    buttons.append((String) link).append("\">").append("Скачати<br>(");
                    buttons.append((String) os).append(")</a>");
                });
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        buttons.append("<a class=\"btn rounded\" role=\"button\" target=\"_blank\" href=\"").append(currentSoftware.getSite()).append("\">Перейти на сайт</a>");
        model.addAttribute("buttons", buttons.toString());
        model.addAttribute("image_bg", currentSoftware.getImageBg());
        return "software";
    }

}
