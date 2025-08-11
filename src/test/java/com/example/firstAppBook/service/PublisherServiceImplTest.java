package com.example.firstAppBook.service;

import com.example.firstAppBook.dto.PublisherDTO;
import com.example.firstAppBook.dto.PublisherMapper;
import com.example.firstAppBook.entity.Publisher;
import com.example.firstAppBook.repository.PublisherRepository;
import com.example.firstAppBook.service.impl.PublisherServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PublisherServiceImplTest {
    @Mock
    private PublisherRepository publisherRepository;
    @Mock
    private PublisherMapper publisherMapper;
    @InjectMocks
    private PublisherServiceImpl publisherService;
    private PublisherDTO publisherDTO;
    private Publisher publisher;
    // This method will be implemented to test the getAllPublishers method
    @Test
    public void testGetAllPublishers() {
        // This method will be implemented to test the getAllPublishers method
        // You can use Mockito to define the behavior of publisherRepository
        // and then call the method you want to test in publisherService.
    when(publisherRepository.findAll()).thenReturn(Collections.emptyList());
        List<PublisherDTO> publishers = publisherService.getAllPublishers();
        assertNotNull(publishers);
        assertTrue(publishers.isEmpty());

        // Verify that the repository method was called
        verify(publisherRepository).findAll();
    }
}
