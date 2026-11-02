package com.designpatterns.singleton;

import com.designpatterns.builder.SequencedData;
import com.designpatterns.factory.Sample;
import com.designpatterns.factory.SampleType;
import com.designpatterns.factory.SpeciesCategory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class SampleManagementSystem {

    private final String systemName;
    private final String organization;
    private final Map<String, Sample> sampleStore;

    private SampleManagementSystem() {
        this.systemName = "BioTrack-Genomics Enterprise System";
        this.organization = "Global Biotech Research Labs";
        this.sampleStore = new ConcurrentHashMap<>();
        System.out.println("[SampleManagementSystem] Singleton instance initialized for " + organization);
    }


    private static class SingletonHelper {
        private static final SampleManagementSystem INSTANCE = new SampleManagementSystem();
    }

    public static SampleManagementSystem getInstance() {
        return SingletonHelper.INSTANCE;
    }

    public String getSystemName() {
        return systemName;
    }

    public String getOrganization() {
        return organization;
    }


    public synchronized boolean registerSample(Sample sample) {
        if (sample == null) {
            throw new IllegalArgumentException("Cannot register a null sample.");
        }
        if (sampleStore.containsKey(sample.getId())) {
            System.out.println("[Warning] Sample ID '" + sample.getId() + "' already exists. Updating existing entry.");
        }
        sampleStore.put(sample.getId(), sample);
        return true;
    }

    public synchronized Sample removeSample(String sampleId) {
        return sampleStore.remove(sampleId);
    }

    public int getTotalSampleCount() {
        return sampleStore.size();
    }


    public Sample getSampleById(String sampleId) {
        if (sampleId == null) return null;
        return sampleStore.get(sampleId);
    }

    public List<Sample> findSamplesBySpecies(String speciesName) {
        if (speciesName == null || speciesName.trim().isEmpty()) {
            return Collections.emptyList();
        }
        String query = speciesName.trim().toLowerCase();
        return sampleStore.values().stream()
                .filter(s -> s.getSpeciesName().toLowerCase().contains(query))
                .collect(Collectors.toList());
    }

    public List<Sample> findSamplesByType(SampleType type) {
        if (type == null) return Collections.emptyList();
        return sampleStore.values().stream()
                .filter(s -> s.getSampleType() == type)
                .collect(Collectors.toList());
    }

    public List<Sample> findSamplesByCategory(SpeciesCategory category) {
        if (category == null) return Collections.emptyList();
        return sampleStore.values().stream()
                .filter(s -> s.getSpeciesCategory() == category)
                .collect(Collectors.toList());
    }

    public List<Sample> getAllSamples() {
        return new ArrayList<>(sampleStore.values());
    }

    public void analyzeSampleSequence(String sampleId) {
        Sample sample = getSampleById(sampleId);
        if (sample == null) {
            System.out.println("[Error] Sample not found: " + sampleId);
            return;
        }


        System.out.println("GENOMIC SEQUENCE ANALYSIS: Sample [" + sample.getId() + "] (" + sample.getSpeciesName() + ")");
        System.out.println("Species Category  : " + sample.getSpeciesCategory());
        System.out.println("Sample Material   : " + sample.getSampleType());
        System.out.println("Storage Location  : " + sample.getStorageLocation() + " (" + sample.getStorageTemperatureCelsius() + " deg C)");

        SequencedData seq = sample.getSequencedData();
        if (seq == null) {
            System.out.println("Status            : No sequenced data associated with this sample.");
            return;
        }

        System.out.println("Molecule Type     : " + seq.getMoleculeType());
        System.out.println("Sequence Length   : " + seq.getSequenceLength() + " base pairs (bp)");
        System.out.printf("GC Content        : %.2f%%\n", seq.getGcContentPercentage());
        System.out.println("Sequencing Machine: " + seq.getSequencingPlatform());
        System.out.printf("Coverage Depth    : %.1fx | Q-Score: %.1f\n", seq.getCoverageDepth(), seq.getQualityScoreQ());
        System.out.println("Base Distribution : " + seq.getBaseDistribution());
        System.out.println("Assembly Status   : " + seq.getAssemblyStatus());
        System.out.println("Gene Annotations  : " + seq.getAnnotations());
        System.out.println("Raw Sequence Snippet: " + (seq.getSequenceLength() > 50
                ? seq.getNucleotideSequence().substring(0, 50) + "... [truncated]"
                : seq.getNucleotideSequence()));

    }


    public List<Sample> searchSamplesByMotif(String motif) {
        if (motif == null || motif.trim().isEmpty()) {
            return Collections.emptyList();
        }
        return sampleStore.values().stream()
                .filter(Sample::hasSequencedData)
                .filter(s -> s.getSequencedData().containsMotif(motif))
                .collect(Collectors.toList());
    }


    public List<Sample> findSamplesWithHighGCContent(double thresholdPercentage) {
        return sampleStore.values().stream()
                .filter(Sample::hasSequencedData)
                .filter(s -> s.getSequencedData().getGcContentPercentage() >= thresholdPercentage)
                .collect(Collectors.toList());
    }


    public void generateAnalyticsSummary() {

        System.out.println("        " + systemName.toUpperCase());
        System.out.println("              ORGANIZATION: " + organization);

        int total = sampleStore.size();
        long sequencedCount = sampleStore.values().stream().filter(Sample::hasSequencedData).count();
        long pendingCount = total - sequencedCount;

        System.out.println("Total Samples Registered : " + total);
        System.out.println("Sequenced Samples        : " + sequencedCount);
        System.out.println("Pending Sequencing       : " + pendingCount);

        // Species category breakdown
        Map<SpeciesCategory, Long> categoryCount = sampleStore.values().stream()
                .collect(Collectors.groupingBy(Sample::getSpeciesCategory, Collectors.counting()));
        System.out.println("\nBreakdown by Species Category:");
        for (SpeciesCategory cat : SpeciesCategory.values()) {
            System.out.printf("  - %-12s: %d\n", cat, categoryCount.getOrDefault(cat, 0L));
        }

        // Sample type breakdown
        Map<SampleType, Long> typeCount = sampleStore.values().stream()
                .collect(Collectors.groupingBy(Sample::getSampleType, Collectors.counting()));
        System.out.println("\nBreakdown by Sample Material Type:");
        for (SampleType st : SampleType.values()) {
            System.out.printf("  - %-20s: %d\n", st, typeCount.getOrDefault(st, 0L));
        }

        // Average GC content across sequenced samples
        double avgGc = sampleStore.values().stream()
                .filter(Sample::hasSequencedData)
                .mapToDouble(s -> s.getSequencedData().getGcContentPercentage())
                .average()
                .orElse(0.0);
        System.out.printf("\nAverage GC-Content across sequenced samples: %.2f%%\n", avgGc);
    }
}
