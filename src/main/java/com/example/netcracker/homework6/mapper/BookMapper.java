package com.example.netcracker.homework6.mapper;


import com.example.netcracker.homework6.model.dto.BookDTO;
import com.example.netcracker.homework6.model.entity.Book;
import org.mapstruct.Mapper;

@Mapper(config = EntityMapper.class)
public interface BookMapper extends EntityMapper<Book, BookDTO> {
}
