package br.com.mayara.services;

import br.com.mayara.Exceptions.RequiredObjectIsNullException;
import br.com.mayara.Exceptions.ResourceNotFoundException;
import br.com.mayara.controller.BookController;
import br.com.mayara.data.vo.v1.BookVO;
import br.com.mayara.DozerMapper;
import br.com.mayara.model.Book;
import br.com.mayara.repositories.BookRepository;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
@Data
@Log4j2
@Service
public class BookServices {

    @Autowired
    BookRepository bookRepository;

    public List<BookVO> findAll() {
        log.info("Finding all book");

        var book = DozerMapper.parseListObjects(bookRepository.findAll(), BookVO.class);
        book.stream()
                .forEach(p -> p.add(linkTo(methodOn(BookController.class)
                        .findById(p.getId())).withSelfRel()));
        return book;
    }

    public BookVO findById(Long id){
        log.info("Finding one person");
        var entity = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records founf for this ID"));
        BookVO vo = DozerMapper.parseObject(entity, BookVO.class);
        vo.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
        return vo;
    }

    public BookVO create(BookVO book) {
        if (book == null) throw new RequiredObjectIsNullException();
        log.info("Creating one book");
        var entity = DozerMapper.parseObject(book, Book.class);
        BookVO vo = DozerMapper.parseObject(bookRepository.save(entity), BookVO.class);
        vo.add(linkTo(methodOn(BookController.class).findById(vo.getId())).withSelfRel());
        return vo;
    }

    public BookVO update(BookVO book) {
        if (book == null) throw new RequiredObjectIsNullException();
        log.info("Updating one book");
        var entity = bookRepository.findById(book.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        entity.setAuthor(book.getAuthor());
        entity.setTitle(book.getTitle());
        entity.setPrice(book.getPrice());
        entity.setLaunchDate(book.getLaunchDate());
        var vo = DozerMapper.parseObject(bookRepository.save(entity), BookVO.class);
        vo.add(linkTo(methodOn(BookController.class).findById(vo.getId())).withSelfRel());
        return vo;
    }

    public void delete(Long id) {
        log.info("Deleting one book");

        Book entity = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        bookRepository.delete(entity);
    }


}


