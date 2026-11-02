package com.designpatterns.factory;


public class BloodSample extends AbstractSample {

    private String anticoagulant = "K2-EDTA";
    private double volumeMl = 5.0;

    public BloodSample(String id, String speciesName, SpeciesCategory speciesCategory) {
        super(id, speciesName, speciesCategory, SampleType.BLOOD);
        this.storageTemperatureCelsius = -80.0;
    }

    public String getAnticoagulant() {
        return anticoagulant;
    }

    public void setAnticoagulant(String anticoagulant) {
        this.anticoagulant = anticoagulant;
    }

    public double getVolumeMl() {
        return volumeMl;
    }

    public void setVolumeMl(double volumeMl) {
        this.volumeMl = volumeMl;
    }

    @Override
    public String getSampleDetails() {
        return "Anticoagulant: " + anticoagulant + " | Volume: " + volumeMl + " mL";
    }
}
