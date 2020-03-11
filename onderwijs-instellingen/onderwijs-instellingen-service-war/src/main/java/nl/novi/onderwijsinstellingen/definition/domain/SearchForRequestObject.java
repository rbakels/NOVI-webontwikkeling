package nl.novi.onderwijsinstellingen.definition.domain;

import javax.validation.constraints.NotNull;

public class SearchForRequestObject {
    @NotNull
    private String zoekWaarde;

    @NotNull
    private ZoekenOpType zoekOpdrachtType;

    public String getZoekWaarde() {
        return zoekWaarde;
    }

    public SearchForRequestObject setZoekWaarde(String zoekWaarde) {
        this.zoekWaarde = zoekWaarde;
        return this;
    }

    public ZoekenOpType getZoekOpdrachtType() {
        return zoekOpdrachtType;
    }

    public SearchForRequestObject setZoekOpdrachtType(ZoekenOpType zoekOpdrachtType) {
        this.zoekOpdrachtType = zoekOpdrachtType;

        return this;
    }
}
