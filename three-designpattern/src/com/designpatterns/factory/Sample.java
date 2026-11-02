package com.designpatterns.factory;

import com.designpatterns.builder.SequencedData;

public interface Sample {

    String getId();

    String getSpeciesName();

    SpeciesCategory getSpeciesCategory();

    SampleType getSampleType();

    String getCollectionDate();

    void setCollectionDate(String collectionDate);

    String getStorageLocation();

    void setStorageLocation(String storageLocation);

    double getStorageTemperatureCelsius();

    void setStorageTemperatureCelsius(double temperature);

    String getResearcher();

    void setResearcher(String researcher);

    SequencedData getSequencedData();

    void setSequencedData(SequencedData sequencedData);

    boolean hasSequencedData();

    String getSampleDetails();

    void displaySampleInfo();
}
