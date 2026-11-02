package com.designpatterns.factory;


public class GeneticMaterialSample extends AbstractSample {

    private String nucleicAcidType = "Purified Genomic DNA (gDNA)";
    private double purityRatioA260A280 = 1.85; // Standard high-purity DNA is ~1.8
    private double concentrationNgPerUl = 240.0;

    public GeneticMaterialSample(String id, String speciesName, SpeciesCategory speciesCategory) {
        super(id, speciesName, speciesCategory, SampleType.GENETIC_MATERIAL);
        this.storageTemperatureCelsius = -80.0;
    }

    public String getNucleicAcidType() {
        return nucleicAcidType;
    }

    public void setNucleicAcidType(String nucleicAcidType) {
        this.nucleicAcidType = nucleicAcidType;
    }

    public double getPurityRatioA260A280() {
        return purityRatioA260A280;
    }

    public void setPurityRatioA260A280(double purityRatioA260A280) {
        this.purityRatioA260A280 = purityRatioA260A280;
    }

    public double getConcentrationNgPerUl() {
        return concentrationNgPerUl;
    }

    public void setConcentrationNgPerUl(double concentrationNgPerUl) {
        this.concentrationNgPerUl = concentrationNgPerUl;
    }

    @Override
    public String getSampleDetails() {
        return "Type: " + nucleicAcidType + " | A260/A280: " + purityRatioA260A280 + " | Conc: " + concentrationNgPerUl + " ng/µL";
    }
}
