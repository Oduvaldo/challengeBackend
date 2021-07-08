package com.b2w.challengeBackend.service.impl;

import com.b2w.challengeBackend.excetption.InvalidParameterException;
import com.b2w.challengeBackend.model.Item;
import com.b2w.challengeBackend.service.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ItemServiceImpl implements ItemService {
    @Value("${data.item.url}")
    private String dataItemUrl;

    private List<Item> getItems() {
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        final RestTemplate resTemplate = new RestTemplate();

        return Arrays.asList(resTemplate.getForObject(dataItemUrl, (Item[].class)));
    }

    @Override
    public List<Item> findItemsByDate(LocalDate beginDate, LocalDate finalDate) {
        if ((!Objects.isNull(beginDate) && !Objects.isNull(finalDate)) && beginDate.isAfter(finalDate)) {
            throw new InvalidParameterException("INVALID_DATES");
        }

        try {
            return getItems().stream()
                    .filter(item -> {
                        LocalDate dateItem = item.getDate().toLocalDate();

                        boolean result = true;

                        if (!Objects.isNull(beginDate)) {
                            result = result && (beginDate.isEqual(dateItem) || beginDate.isBefore(dateItem));
                        }
                        if (result && !Objects.isNull(finalDate)) {
                            result = result && (finalDate.isEqual(dateItem) || finalDate.isAfter(dateItem));
                        }

                        return result;
                    })
                    .collect(Collectors.toList());
        } catch (Exception exception) {
            log.error("ItemServiceImpl : findItemsByDate :: " + exception.getMessage());
            throw exception;
        }
    }
}
