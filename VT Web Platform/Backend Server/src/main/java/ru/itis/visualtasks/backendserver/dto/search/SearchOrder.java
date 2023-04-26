package ru.itis.visualtasks.backendserver.dto.search;

import lombok.Getter;
import ru.itis.visualtasks.backendserver.exceptions.search.UnsupportedSearchOrderException;

import java.util.Arrays;

public enum SearchOrder {

    ASC("ascending"),
    DESC("descending");

    @Getter
    private final String attributeName;

    SearchOrder(String attributeName) {
        this.attributeName = attributeName;
    }

    public static SearchOrder getByAttributeName(String attributeName){
        return Arrays.stream(values())
                .filter(searchOrder -> searchOrder.getAttributeName().equals(attributeName))
                .findFirst()
                .orElseThrow(UnsupportedSearchOrderException::new);
    }

}
