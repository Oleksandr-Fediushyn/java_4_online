package ua.com.alevel.controller.open;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.com.alevel.persistence.entity.catalog.Catalog;
import ua.com.alevel.persistence.entity.software.Software;
import ua.com.alevel.service.impl.CatalogServiceImpl;
import ua.com.alevel.service.impl.SoftwareServiceImpl;

import java.util.List;
import java.util.Optional;

@Controller
public class CatalogController {
    private final CatalogServiceImpl catalogService;
    private final SoftwareServiceImpl softwareService;

    public CatalogController(CatalogServiceImpl catalogService, SoftwareServiceImpl softwareService) {
        this.catalogService = catalogService;
        this.softwareService = softwareService;
    }

    @RequestMapping(value ={"/", "/main"})
    public String main(Model model) {
        Iterable<Catalog> catalogs = catalogService.findAllOrdered();
        model.addAttribute("catalogs", catalogs);
        return "main";
    }
    @GetMapping("/catalog/")
    public String catalog(Model model) {
        return "redirect:/main";
    }
    @GetMapping("/catalog/{id}")
    public String catalog_id(@PathVariable Long id, Model model) {
        Optional<Catalog> catalog = catalogService.findById(id);
        if (!catalog.isPresent()) {
            return "error";
        }
        Catalog currentCatalog = catalog.get();
        List<Software> softwareList = softwareService.findAllByCatalogId(currentCatalog.getId());
        model.addAttribute("title", currentCatalog.getCatalogName());
        model.addAttribute("description", currentCatalog.getDescription());
        model.addAttribute("software", softwareList);
        return "catalog";
    }
    @GetMapping("/catalog")
    public String catalog_string(@RequestParam String search, Model model) {
        if (search.isEmpty() || search.length() >= 255) {
            return "error";
        }
        String[] strings = search.split("[\\s,;&]+");
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : strings) {
            stringBuilder.append(s.charAt(0) != '-' ? " +" : " ").append(s);
        }
        String realSearch = stringBuilder.toString();
        if (realSearch.isEmpty()) {
            return "error";
        }
        List<Software> softwareList = softwareService.findFullText(realSearch.trim());
        model.addAttribute("title", "Пошук по запиту «" + search + "»");
        model.addAttribute("description", "");
        model.addAttribute("software", softwareList);
        return "catalog";
    }

}



