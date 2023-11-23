package dev.abhishek.productservice.controllers;

import dev.abhishek.productservice.dtos.GenericProductDto;
import dev.abhishek.productservice.dtos.SearchRequestDTO;
import dev.abhishek.productservice.services.SearchService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.print.Pageable;

@RestController
@RequestMapping("/search")
public class SearchController {
    private SearchService searchService;
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }
    @PostMapping
    public Page<GenericProductDto> searchProducts(@RequestBody SearchRequestDTO searchRequestDTO) {
        return searchService.searchProducts(
                searchRequestDTO.getQuery(), searchRequestDTO.getPageNumber(),searchRequestDTO.getSizeOfEachPage());
    }
}
