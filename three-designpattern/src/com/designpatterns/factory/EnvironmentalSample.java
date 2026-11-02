package com.designpatterns.factory;


public class EnvironmentalSample extends AbstractSample {

    private String ecosystemType = "Deep-Sea Hydrothermal Vent Sediment";
    private String gpsCoordinates = "27.2589° N, 111.4082° W";
    private double pHLevel = 7.4;

    public EnvironmentalSample(String id, String speciesName, SpeciesCategory speciesCategory) {
        super(id, speciesName, speciesCategory, SampleType.ENVIRONMENTAL);
        this.storageTemperatureCelsius = -20.0;
    }

    public String getEcosystemType() {
        return ecosystemType;
    }

    public void setEcosystemType(String ecosystemType) {
        this.ecosystemType = ecosystemType;
    }

    public String getGpsCoordinates() {
        return gpsCoordinates;
    }

    public void setGpsCoordinates(String gpsCoordinates) {
        this.gpsCoordinates = gpsCoordinates;
    }

    public double getPHLevel() {
        return pHLevel;
    }

    public void setPHLevel(double pHLevel) {
        this.pHLevel = pHLevel;
    }

    @Override
    public String getSampleDetails() {
        return "Ecosystem: " + ecosystemType + " | GPS: " + gpsCoordinates + " | pH: " + pHLevel;
    }
}
