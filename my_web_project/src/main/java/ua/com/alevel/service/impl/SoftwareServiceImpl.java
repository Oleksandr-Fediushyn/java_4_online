package ua.com.alevel.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.entity.software.Software;
import ua.com.alevel.persistence.repository.software.SoftwareRepository;
import ua.com.alevel.service.SoftwareService;

import java.util.List;
import java.util.Optional;

@Service
public class SoftwareServiceImpl implements SoftwareService {

    private final SoftwareRepository softwareRepository;

    @Autowired
    public SoftwareServiceImpl(SoftwareRepository softwareRepository) {

        this.softwareRepository = softwareRepository;
    }

    @Override
    public List<Software> findAllByCatalogId(Long id) {

        return softwareRepository.findAllByCatalogId(id);
    }

    @Override
    public List<Software> findFullText(String string) {
        if (string == null || string.isEmpty()) {
            throw new IllegalArgumentException("Invalid search string");
        }
        return softwareRepository.findFullText(string);
    }

    @Override
    public Optional<Software> findById(Long id) {
        Optional<Software> software = softwareRepository.findById(id);
        if (software.isEmpty()) {
            throw new NullPointerException("Software not found");
        }
        return software;
    }

    @Override
    public Iterable<Software> findAllOrdered() {

        return softwareRepository.findAllOrdered();
    }
}
