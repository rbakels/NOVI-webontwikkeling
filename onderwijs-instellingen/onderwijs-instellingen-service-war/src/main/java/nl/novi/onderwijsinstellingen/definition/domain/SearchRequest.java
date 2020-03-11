package nl.novi.onderwijsinstellingen.definition.domain;

import javax.validation.constraints.NotNull;
import java.util.List;

public class SearchRequest {
    @NotNull
    private List<SearchForRequestObject> search;

    public List<SearchForRequestObject> getSearch() {
        return search;
    }

    public SearchRequest setSearch(List<SearchForRequestObject> search) {
        this.search = search;
        return this;
    }
}
