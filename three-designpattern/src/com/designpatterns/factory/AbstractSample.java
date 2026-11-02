package com.designpatterns.factory;

import com.designpatterns.builder.SequencedData;

public abstract class AbstractSample implements Sample {

    protected final String id;
    protected final String speciesName;
    protected final SpeciesCategory speciesCategory;
    protected final SampleType sampleType;

    protected String collectionDate = "2026-01-01";
    protected String storageLocation = "BioBank Vault A";
    protected double storageTemperatureCelsius = -80.0;
    protected String researcher = "Lead Biotechnologist";
    protected SequencedData sequencedData;

    protected AbstractSample(String id, String speciesName, SpeciesCategory speciesCategory, SampleType sampleType) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Sample ID cannot be null or empty.");
        }
        if (speciesName == null || speciesName.trim().isEmpty()) {
            throw new IllegalArgumentException("Species name cannot be null or empty.");
        }
        if (speciesCategory == null) {
            throw new IllegalArgumentException("Species category cannot be null.");
        }
        if (sampleType == null) {
            throw new IllegalArgumentException("Sample type cannot be null.");
        }

        this.id = id.trim();
        this.speciesName = speciesName.trim();
        this.speciesCategory = speciesCategory;
        this.sampleType = sampleType;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getSpeciesName() {
        return speciesName;
    }

    @Override
    public SpeciesCategory getSpeciesCategory() {
        return speciesCategory;
    }

    @Override
    public SampleType getSampleType() {
        return sampleType;
    }

    @Override
    public String getCollectionDate() {
        return collectionDate;
    }

    @Override
    public void setCollectionDate(String collectionDate) {
        this.collectionDate = collectionDate;
    }

    @Override
    public String getStorageLocation() {
        return storageLocation;
    }

    @Override
    public void setStorageLocation(String storageLocation) {
        this.storageLocation = storageLocation;
    }

    @Override
    public double getStorageTemperatureCelsius() {
        return storageTemperatureCelsius;
    }

    @Override
    public void setStorageTemperatureCelsius(double storageTemperatureCelsius) {
        this.storageTemperatureCelsius = storageTemperatureCelsius;
    }

    @Override
    public String getResearcher() {
        return researcher;
    }

    @Override
    public void setResearcher(String researcher) {
        this.researcher = researcher;
    }

    @Override
    public SequencedData getSequencedData() {
        return sequencedData;
    }

    @Override
    public void setSequencedData(SequencedData sequencedData) {
        this.sequencedData = sequencedData;
    }

    @Override
    public boolean hasSequencedData() {
        return sequencedData != null;
    }

    @Override
    public void displaySampleInfo() {

        System.out.println("Sample ID           : " + id);
        System.out.println("Species Name        : " + speciesName);
        System.out.println("Species Category    : " + speciesCategory);
        System.out.println("Sample Type         : " + sampleType);
        System.out.println("Storage Location    : " + storageLocation + " (" + storageTemperatureCelsius + " deg C)");
        System.out.println("Collection Date     : " + collectionDate + " | Researcher: " + researcher);
        System.out.println("Specific Details    : " + getSampleDetails());
        if (hasSequencedData()) {
            System.out.println("Sequenced Data Info : " + sequencedData);
        } else {
            System.out.println("Sequenced Data Info : [Pending Sequencing]");
        }
    }
}
