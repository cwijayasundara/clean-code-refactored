package main.com.cw.domain;

import java.util.List;

/**
 * Created by cwijayasundara on 14/02/2017.
 */
public class GalacticQuery {

    private List<GalacticCode> galacticCode;
    private Metal metal;
    private boolean isValidQuery;

    public List<GalacticCode> getGalacticCode() {
        return galacticCode;
    }

    public void setGalacticCode(List<GalacticCode> galacticCode) {
        this.galacticCode = galacticCode;
    }

    public Metal getMetal() {
        return metal;
    }

    public void setMetal(Metal metal) {
        this.metal = metal;
    }

    public boolean isValidQuery() {
        return isValidQuery;
    }

    public void setValidQuery(boolean validQuery) {
        isValidQuery = validQuery;
    }
}
