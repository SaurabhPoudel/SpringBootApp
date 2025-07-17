package com.example.firstAppBook.dto;

import com.example.firstAppBook.entity.Publisher;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class PublisherMapper {

    public PublisherDTO toDTO(Publisher publisher) {
        if (publisher == null) return null;
        return PublisherDTO.builder()
                .id(publisher.getId())
                .name(publisher.getName())
                .build();
    }

    public Publisher toEntity(PublisherDTO dto) {
        if (dto == null) return null;
        Publisher publisher = new Publisher();
        publisher.setId(dto.getId());
        publisher.setName(dto.getName());
        return publisher;
    }
}
