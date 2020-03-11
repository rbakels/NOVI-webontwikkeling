package nl.novi.onderwijsinstellingen.definition.domain;

import javax.validation.constraints.NotNull;

/**
 * search request
 */
public class DetailRequest {

    @NotNull
    private String zoekWaarde;

    @NotNull
    private ZoekenOpType zoekOpdracht;

    /**
     * Constructor
     * @param searchValue search value
     * @param zoekOpdracht search type the search type
     */
    public DetailRequest(String searchValue, ZoekenOpType zoekOpdracht) {
        this.zoekWaarde = searchValue;
        this.zoekOpdracht = zoekOpdracht;
    }

    public String getZoekWaarde() {
        return zoekWaarde;
    }

    public DetailRequest setZoekWaarde(String zoekWaarde) {
        this.zoekWaarde = zoekWaarde;
        return this;
    }

    public ZoekenOpType getZoekOpdracht() {
        return zoekOpdracht;
    }

    public void setZoekOpdracht(ZoekenOpType zoekOpdracht) {
        this.zoekOpdracht = zoekOpdracht;
    }
}
