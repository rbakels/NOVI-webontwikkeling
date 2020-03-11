package nl.novi.onderwijsinstellingen.definition.domain;

import javax.validation.constraints.NotNull;

public class DetailResponse {
    @NotNull
    private String zoekOpdracht;

    @NotNull
    private ZoekenOpType soortBerekening;

    public String getZoekOpdracht() {
        return zoekOpdracht;
    }

    public DetailResponse setZoekOpdracht(String zoekOpdracht) {
        this.zoekOpdracht = zoekOpdracht;
        return this;
    }

    public ZoekenOpType getSoortBerekening() {
        return soortBerekening;
    }

    public DetailResponse setSoortBerekening(ZoekenOpType soortBerekening) {
        this.soortBerekening = soortBerekening;
        return this;
    }
}
