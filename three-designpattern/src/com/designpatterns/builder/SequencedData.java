package com.designpatterns.builder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SequencedData {

    private final String sequenceId;
    private final String moleculeType;
    private final String nucleotideSequence;
    private final String sequencingPlatform;
    private final long readCount;
    private final double coverageDepth;
    private final double qualityScoreQ;
    private final String assemblyStatus;
    private final List<String> annotations;

    private SequencedData(Builder builder) {
        this.sequenceId = builder.sequenceId;
        this.moleculeType = builder.moleculeType;
        this.nucleotideSequence = builder.nucleotideSequence.toUpperCase();
        this.sequencingPlatform = builder.sequencingPlatform;
        this.readCount = builder.readCount;
        this.coverageDepth = builder.coverageDepth;
        this.qualityScoreQ = builder.qualityScoreQ;
        this.assemblyStatus = builder.assemblyStatus;
        this.annotations = Collections.unmodifiableList(new ArrayList<>(builder.annotations));
    }

    public String getSequenceId() {
        return sequenceId;
    }

    public String getMoleculeType() {
        return moleculeType;
    }

    public String getNucleotideSequence() {
        return nucleotideSequence;
    }

    public int getSequenceLength() {
        return nucleotideSequence != null ? nucleotideSequence.length() : 0;
    }

    public String getSequencingPlatform() {
        return sequencingPlatform;
    }

    public long getReadCount() {
        return readCount;
    }

    public double getCoverageDepth() {
        return coverageDepth;
    }

    public double getQualityScoreQ() {
        return qualityScoreQ;
    }

    public String getAssemblyStatus() {
        return assemblyStatus;
    }

    public List<String> getAnnotations() {
        return annotations;
    }


    public double getGcContentPercentage() {
        if (nucleotideSequence == null || nucleotideSequence.isEmpty()) {
            return 0.0;
        }
        long gcCount = 0;
        for (char base : nucleotideSequence.toCharArray()) {
            if (base == 'G' || base == 'C') {
                gcCount++;
            }
        }
        return (double) gcCount / nucleotideSequence.length() * 100.0;
    }

    public Map<Character, Integer> getBaseDistribution() {
        Map<Character, Integer> counts = new HashMap<>();
        if (nucleotideSequence != null) {
            for (char base : nucleotideSequence.toCharArray()) {
                counts.put(base, counts.getOrDefault(base, 0) + 1);
            }
        }
        return counts;
    }


    public boolean containsMotif(String motif) {
        if (motif == null || nucleotideSequence == null) {
            return false;
        }
        return nucleotideSequence.contains(motif.toUpperCase());
    }

    @Override
    public String toString() {
        return String.format(
            "SequencedData [ID: %s | %s | %d bp | GC: %.2f%% | Platform: %s | Depth: %.1fx | Q-Score: %.1f | Assembly: %s | Annotations: %d]",
            sequenceId, moleculeType, getSequenceLength(), getGcContentPercentage(),
            sequencingPlatform, coverageDepth, qualityScoreQ, assemblyStatus, annotations.size()
        );
    }


    public static class Builder {
        // Required fields
        private final String sequenceId;
        private final String moleculeType;
        private final String nucleotideSequence;

        // Optional fields with defaults
        private String sequencingPlatform = "Illumina NovaSeq 6000";
        private long readCount = 1_000_000L;
        private double coverageDepth = 30.0;
        private double qualityScoreQ = 30.0;
        private String assemblyStatus = "Draft";
        private final List<String> annotations = new ArrayList<>();

        public Builder(String sequenceId, String moleculeType, String nucleotideSequence) {
            if (sequenceId == null || sequenceId.trim().isEmpty()) {
                throw new IllegalArgumentException("Sequence ID cannot be null or empty.");
            }
            if (moleculeType == null || (!moleculeType.equalsIgnoreCase("DNA") && !moleculeType.equalsIgnoreCase("RNA"))) {
                throw new IllegalArgumentException("Molecule type must be 'DNA' or 'RNA'.");
            }
            if (nucleotideSequence == null || nucleotideSequence.trim().isEmpty()) {
                throw new IllegalArgumentException("Nucleotide sequence cannot be null or empty.");
            }

            // Validate nucleotide characters
            String cleanSeq = nucleotideSequence.trim().toUpperCase();
            String validChars = moleculeType.equalsIgnoreCase("DNA") ? "ATCGN" : "AUCGN";
            for (char c : cleanSeq.toCharArray()) {
                if (validChars.indexOf(c) == -1) {
                    throw new IllegalArgumentException("Invalid nucleotide character '" + c + "' for molecule type " + moleculeType);
                }
            }

            this.sequenceId = sequenceId.trim();
            this.moleculeType = moleculeType.trim().toUpperCase();
            this.nucleotideSequence = cleanSeq;
        }

        public Builder setSequencingPlatform(String platform) {
            if (platform != null && !platform.trim().isEmpty()) {
                this.sequencingPlatform = platform.trim();
            }
            return this;
        }

        public Builder setReadCount(long readCount) {
            if (readCount < 0) {
                throw new IllegalArgumentException("Read count cannot be negative.");
            }
            this.readCount = readCount;
            return this;
        }

        public Builder setCoverageDepth(double coverageDepth) {
            if (coverageDepth < 0) {
                throw new IllegalArgumentException("Coverage depth cannot be negative.");
            }
            this.coverageDepth = coverageDepth;
            return this;
        }

        public Builder setQualityScoreQ(double qualityScoreQ) {
            if (qualityScoreQ < 0) {
                throw new IllegalArgumentException("Quality score cannot be negative.");
            }
            this.qualityScoreQ = qualityScoreQ;
            return this;
        }

        public Builder setAssemblyStatus(String assemblyStatus) {
            if (assemblyStatus != null && !assemblyStatus.trim().isEmpty()) {
                this.assemblyStatus = assemblyStatus.trim();
            }
            return this;
        }

        public Builder addAnnotation(String annotation) {
            if (annotation != null && !annotation.trim().isEmpty()) {
                this.annotations.add(annotation.trim());
            }
            return this;
        }

        public Builder addAnnotations(List<String> annotations) {
            if (annotations != null) {
                for (String ann : annotations) {
                    addAnnotation(ann);
                }
            }
            return this;
        }

        public SequencedData build() {
            return new SequencedData(this);
        }
    }
}
