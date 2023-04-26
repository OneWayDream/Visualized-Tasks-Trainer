package ru.itis.visualtasks.backendserver.dto.search;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.visualtasks.backendserver.exceptions.search.UnsupportedLanguageException;
import ru.itis.visualtasks.backendserver.exceptions.search.UnsupportedSearchOrderByException;
import ru.itis.visualtasks.backendserver.exceptions.search.UnsupportedSearchOrderException;

import java.util.Arrays;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchAttributes {

    private String taskName;
    private String authorName;
    private SearchOrder order;
    private OrderBy orderBy;
    private List<Language> languages;
    private int page;
    private int pageSize = 10;

    public static Builder builder() {
        return new SearchAttributes().new Builder();
    }

    public class Builder {

        private Builder() {

        }

        public Builder taskName(String taskName) {
            SearchAttributes.this.taskName = (taskName == null || taskName.length() == 0) ? null : taskName;
            return this;
        }

        public Builder authorName(String authorName) {
            SearchAttributes.this.authorName = (authorName == null || authorName.length() == 0) ? null : authorName;
            return this;
        }

        public Builder order(String order){
            try {
                SearchAttributes.this.order = SearchOrder.getByAttributeName(order);
            } catch (UnsupportedSearchOrderException exception){
                SearchAttributes.this.order = SearchOrder.DESC;
            }
            return this;
        }

        public Builder orderBy(String orderBy){
            try {
                SearchAttributes.this.orderBy = OrderBy.getByFieldKey(orderBy);
            } catch (UnsupportedSearchOrderByException exception){
                SearchAttributes.this.orderBy = OrderBy.ADDITION_DATE;
            }
            return this;
        }

        public Builder languages(String languages){
            SearchAttributes.this.languages = parseLanguageKeys(languages);
            return this;
        }

        private List<Language> parseLanguageKeys(String languageKeysString){
            try{
                List<String> languageKeys = Arrays.asList(languageKeysString.split(","));
                return Language.getByLanguageKeys(languageKeys);
            } catch (UnsupportedLanguageException | NullPointerException exception){
                return Language.getAll();
            }
        }

        public Builder page(Integer page){
            SearchAttributes.this.page = (page == null || page < 0) ? 0 : page;
            return this;
        }

        public SearchAttributes build() {
            return SearchAttributes.this;
        }

    }

}
