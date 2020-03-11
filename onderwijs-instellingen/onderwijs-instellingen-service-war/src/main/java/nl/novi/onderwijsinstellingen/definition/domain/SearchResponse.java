package nl.novi.onderwijsinstellingen.definition.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import nl.novi.onderwijsinstellingen.definition.domain.onderwijsinstellingen.Instellingen;

import java.util.ArrayList;
import java.util.List;

public class SearchResponse {
    @JsonProperty("beschikkingen")
    private List<Instellingen> instellingen = new ArrayList<>();

    public SearchResponse(List<Instellingen> instellingen) {
        this.instellingen = instellingen;
    }

    public List<Instellingen> getInstellingen() {
        return instellingen;
    }

    public SearchResponse setInstellingen(List<Instellingen> instellingen) {
        this.instellingen = instellingen;
        return this;
    }
}
