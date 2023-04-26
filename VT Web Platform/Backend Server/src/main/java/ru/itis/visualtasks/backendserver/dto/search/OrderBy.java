package ru.itis.visualtasks.backendserver.dto.search;

import lombok.Getter;
import ru.itis.visualtasks.backendserver.exceptions.search.UnsupportedSearchOrderByException;

import java.util.Arrays;

public enum OrderBy {

    ADDITION_DATE("addition-date", "additionDate");

    @Getter
    private final String fieldKey;
    @Getter
    private final String columnKey;

    OrderBy(String fieldKey, String columnKey) {
        this.fieldKey = fieldKey;
        this.columnKey = columnKey;
    }

    public static OrderBy getByFieldKey(String fieldKey){
        return Arrays.stream(values())
                .filter(orderBy -> orderBy.getFieldKey().equals(fieldKey))
                .findFirst()
                .orElseThrow(UnsupportedSearchOrderByException::new);
    }

}
