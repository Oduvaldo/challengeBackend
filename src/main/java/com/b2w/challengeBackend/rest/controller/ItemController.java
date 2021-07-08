package com.b2w.challengeBackend.rest.controller;

import com.b2w.challengeBackend.excetption.InvalidParameterException;
import com.b2w.challengeBackend.model.Item;
import com.b2w.challengeBackend.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@RestController
@RequestMapping("/item")
@RequiredArgsConstructor
@Slf4j
public class ItemController {
    @Autowired
    private final ItemService itemService;

    @Value("${data.parameter.datePattern}")
    private String datePattern;

    @GetMapping
    public List<Item> getItem(@RequestParam(name = "begindate", required = false) String beginDate,
                              @RequestParam(name = "finaldate", required = false) String finalDate) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(datePattern);
            LocalDate locBeginDate = beginDate != null ? LocalDate.parse(beginDate, formatter) : null;
            LocalDate locFinalDate = finalDate != null ? LocalDate.parse(finalDate, formatter) : null;

            return itemService.findItemsByDate(locBeginDate, locFinalDate);
        } catch (DateTimeParseException exception) {
            log.error("ItemController : getItem :: " + exception.getMessage());
            throw new InvalidParameterException("INVALID_DATE");
        }
    }
}
