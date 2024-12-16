package com.pranay.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.pranay.model.SearchHistory;
import com.pranay.service.SearchService;


import java.util.*;

@Controller
public class SearchController {
    private final SearchService searchService;
    private List<String> searchHistory = new ArrayList<>();

    @Value("${google.api.key}")
    private String apiKey;

    @Value("${google.search.engine.id}")
    private String searchEngineId;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/search")
   
    public ResponseEntity<String> saveSearchQuery(@RequestBody Map<String, String> body) {
        String query = body.get("query");

        // Save the query to the database
        searchService.saveSearchQuery(query);

        // Return a response if needed (this can be a success message or just an HTTP status)
        return ResponseEntity.ok("Query saved successfully");
    }


    @GetMapping("/history")
    public String viewHistory(Model model) {
        List<SearchHistory> history = searchService.getAllHistory();
        model.addAttribute("history", history);
        return "history";
    }
    
}
