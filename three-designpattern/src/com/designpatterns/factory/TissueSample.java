package com.designpatterns.factory;

public class TissueSample extends AbstractSample {

    private String organOrTissueSource = "Hepatic Tissue";
    private String preservationMethod = "Cryopreservation (Liquid Nitrogen, -196 deg C)";

    public TissueSample(String id, String speciesName, SpeciesCategory speciesCategory) {
        super(id, speciesName, speciesCategory, SampleType.TISSUE);
        this.storageTemperatureCelsius = -196.0;
    }

    public String getOrganOrTissueSource() {
        return organOrTissueSource;
    }

    public void setOrganOrTissueSource(String organOrTissueSource) {
        this.organOrTissueSource = organOrTissueSource;
    }

    public String getPreservationMethod() {
        return preservationMethod;
    }

    public void setPreservationMethod(String preservationMethod) {
        this.preservationMethod = preservationMethod;
    }

    @Override
    public String getSampleDetails() {
        return "Source: " + organOrTissueSource + " | Preservation: " + preservationMethod;
    }
}
