package com.b2w.challengeBackend.service;

import com.b2w.challengeBackend.model.Item;

import java.time.LocalDate;
import java.util.List;

public interface ItemService {
    List<Item> findItemsByDate(LocalDate beginDate, LocalDate finalDate);
}
