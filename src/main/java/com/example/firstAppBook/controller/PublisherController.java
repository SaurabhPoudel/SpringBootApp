package com.example.firstAppBook.controller;


import com.example.firstAppBook.dto.PublisherDTO;
import com.example.firstAppBook.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/publishers")
@CrossOrigin(origins = "*")
public class PublisherController {

    private PublisherService publisherService;
    @Autowired
    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @GetMapping
    public List<PublisherDTO> getAllPublishers() {
        return publisherService.getAllPublishers();
    }


}
