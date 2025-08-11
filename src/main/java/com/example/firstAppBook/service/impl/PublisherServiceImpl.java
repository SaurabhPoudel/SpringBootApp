package com.example.firstAppBook.service.impl;

import com.example.firstAppBook.dto.PublisherDTO;
import com.example.firstAppBook.repository.PublisherRepository;
import com.example.firstAppBook.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;
import java.util.List;

@Service
public class PublisherServiceImpl implements PublisherService {

    private PublisherRepository publisherRepository;
    // Constructor injection is preferred, but field injection is also acceptable in some cases
    @Autowired
    public PublisherServiceImpl(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    @Override
    public List<PublisherDTO> getAllPublishers() {
        return publisherRepository.findAll()
                .stream()
                .map(publisher -> new PublisherDTO(publisher.getId(), publisher.getName()))
                .collect(Collectors.toList());
    }
}
