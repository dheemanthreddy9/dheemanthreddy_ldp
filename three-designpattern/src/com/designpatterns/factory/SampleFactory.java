package com.designpatterns.factory;

public class SampleFactory {
    public static Sample createSample(SampleType type, String id, String speciesName, SpeciesCategory speciesCategory) {
        if (type == null) {
            throw new IllegalArgumentException("SampleType cannot be null.");
        }

        switch (type) {
            case TISSUE:
                return new TissueSample(id, speciesName, speciesCategory);
            case BLOOD:
                return new BloodSample(id, speciesName, speciesCategory);
            case MICROBIAL_CULTURE:
                return new MicrobialCultureSample(id, speciesName, speciesCategory);
            case ENVIRONMENTAL:
                return new EnvironmentalSample(id, speciesName, speciesCategory);
            case GENETIC_MATERIAL:
                return new GeneticMaterialSample(id, speciesName, speciesCategory);
            default:
                throw new UnsupportedOperationException("Unsupported sample type: " + type);
        }
    }
}
