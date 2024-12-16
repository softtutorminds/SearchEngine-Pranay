package com.pranay.service;

import org.springframework.stereotype.Service;

import com.pranay.model.SearchHistory;
import com.pranay.repository.SearchHistoryRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SearchService {

    private final SearchHistoryRepository repository;

    public SearchService(SearchHistoryRepository repository) {
        this.repository = repository;
    }

    public void saveSearchQuery(String query) {
        SearchHistory history = new SearchHistory();
        history.setQuery(query);
        history.setTimestamp(LocalDateTime.now());  // Set the current timestamp
        repository.save(history);
    }

    public List<SearchHistory> getAllHistory() {
        return repository.findAll();
    }
}
