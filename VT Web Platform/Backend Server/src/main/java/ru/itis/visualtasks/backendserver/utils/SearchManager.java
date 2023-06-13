package ru.itis.visualtasks.backendserver.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import ru.itis.visualtasks.backendserver.dto.search.SearchAttributes;
import ru.itis.visualtasks.backendserver.dto.search.SearchOrder;

public class SearchManager {

    public static Pageable preparePageable(SearchAttributes searchAttributes){
        Sort sort = Sort.by(searchAttributes.getOrderBy().getColumnKey());
        sort = (searchAttributes.getOrder() == SearchOrder.ASC) ? sort.ascending() : sort.descending();
        return PageRequest.of(
                searchAttributes.getPage(),
                searchAttributes.getPageSize(),
                sort);
    }

}
