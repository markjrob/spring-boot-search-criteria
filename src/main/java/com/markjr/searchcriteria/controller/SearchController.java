package com.markjr.searchcriteria.controller;

import com.markjr.searchcriteria.dto.SearchDataCriteria;
import com.markjr.searchcriteria.response.Response;
import com.markjr.searchcriteria.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchController {

    @Autowired
    DataService dataService;

    @GetMapping
    public ResponseEntity<Response> searchContactPerson(SearchDataCriteria criteria){
        Response response = new Response();
        response.setMessage("SUCCESS");
        response.setCode(200);
        response.setResult(dataService.getAllWithCriteria(criteria));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
