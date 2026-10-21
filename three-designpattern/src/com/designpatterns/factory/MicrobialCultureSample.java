package com.designpatterns.factory;


public class MicrobialCultureSample extends AbstractSample {

    private String growthMedium = "Luria-Bertani (LB) Broth";
    private String colonyConcentration = "1.5 x 10^8 CFU/mL";
    private double incubationTemperature = 37.0;

    public MicrobialCultureSample(String id, String speciesName, SpeciesCategory speciesCategory) {
        super(id, speciesName, speciesCategory, SampleType.MICROBIAL_CULTURE);
        this.storageTemperatureCelsius = -80.0;
    }

    public String getGrowthMedium() {
        return growthMedium;
    }

    public void setGrowthMedium(String growthMedium) {
        this.growthMedium = growthMedium;
    }

    public String getColonyConcentration() {
        return colonyConcentration;
    }

    public void setColonyConcentration(String colonyConcentration) {
        this.colonyConcentration = colonyConcentration;
    }

    public double getIncubationTemperature() {
        return incubationTemperature;
    }

    public void setIncubationTemperature(double incubationTemperature) {
        this.incubationTemperature = incubationTemperature;
    }

    @Override
    public String getSampleDetails() {
        return "Medium: " + growthMedium + " | Concentration: " + colonyConcentration + " | Incubated: " + incubationTemperature + "°C";
    }
}
