package com.designpatterns.builder;

import com.designpatterns.factory.Sample;
import com.designpatterns.factory.SampleFactory;
import com.designpatterns.factory.SampleType;
import com.designpatterns.factory.SpeciesCategory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class SampleProfileBuilder {

    private final Sample sample;
    private String researcher = "BioTech Senior Scientist";
    private String collectionDate = "2026-03-15";
    private String storageLocation = "Cryo-Storage Wing B";
    private Double storageTemperatureCelsius = null;
    private SequencedData sequencedData = null;
    private final List<String> researchProjects = new ArrayList<>();

    public SampleProfileBuilder(Sample sample) {
        if (sample == null) {
            throw new IllegalArgumentException("Sample cannot be null.");
        }
        this.sample = sample;
    }

    public SampleProfileBuilder(SampleType type, String id, String speciesName, SpeciesCategory category) {
        this(SampleFactory.createSample(type, id, speciesName, category));
    }

    public SampleProfileBuilder setResearcher(String researcher) {
        if (researcher != null && !researcher.trim().isEmpty()) {
            this.researcher = researcher.trim();
        }
        return this;
    }

    public SampleProfileBuilder setCollectionDate(String collectionDate) {
        if (collectionDate != null && !collectionDate.trim().isEmpty()) {
            this.collectionDate = collectionDate.trim();
        }
        return this;
    }

    public SampleProfileBuilder setStorageLocation(String storageLocation) {
        if (storageLocation != null && !storageLocation.trim().isEmpty()) {
            this.storageLocation = storageLocation.trim();
        }
        return this;
    }

    public SampleProfileBuilder setStorageTemperatureCelsius(double temperatureCelsius) {
        this.storageTemperatureCelsius = temperatureCelsius;
        return this;
    }

    public SampleProfileBuilder setSequencedData(SequencedData sequencedData) {
        this.sequencedData = sequencedData;
        return this;
    }

    public SampleProfileBuilder addResearchProject(String projectCode) {
        if (projectCode != null && !projectCode.trim().isEmpty()) {
            this.researchProjects.add(projectCode.trim());
        }
        return this;
    }

    public SampleProfileBuilder addResearchProjects(List<String> projectCodes) {
        if (projectCodes != null) {
            for (String code : projectCodes) {
                addResearchProject(code);
            }
        }
        return this;
    }

    public List<String> getResearchProjects() {
        return Collections.unmodifiableList(researchProjects);
    }


    public Sample build() {
        sample.setResearcher(this.researcher);
        sample.setCollectionDate(this.collectionDate);
        sample.setStorageLocation(this.storageLocation);

        if (this.storageTemperatureCelsius != null) {
            sample.setStorageTemperatureCelsius(this.storageTemperatureCelsius);
        }

        if (this.sequencedData != null) {
            sample.setSequencedData(this.sequencedData);
        }

        return sample;
    }
}
