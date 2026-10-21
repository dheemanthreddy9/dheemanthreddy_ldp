package com.designpatterns;

import com.designpatterns.builder.SampleProfileBuilder;
import com.designpatterns.builder.SequencedData;
import com.designpatterns.factory.Sample;
import com.designpatterns.factory.SampleFactory;
import com.designpatterns.factory.SampleType;
import com.designpatterns.factory.SpeciesCategory;
import com.designpatterns.singleton.SampleManagementSystem;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        printBanner("BIOTECHNOLOGY SAMPLE MANAGEMENT SYSTEM");
        System.out.println("Design Patterns Implementation: Singleton, Builder, and Factory\n");

        // =========================================================================
        // 1. SINGLETON DESIGN PATTERN
        // =========================================================================
        printSectionHeader("1. SINGLETON DESIGN PATTERN: Centralized Sample Management System");
        System.out.println("Concept: Ensure a single, globally accessible, thread-safe instance manages the repository.\n");

        System.out.println("Acquiring reference 1 (systemRefA)...");
        SampleManagementSystem systemRefA = SampleManagementSystem.getInstance();

        System.out.println("Acquiring reference 2 (systemRefB)...");
        SampleManagementSystem systemRefB = SampleManagementSystem.getInstance();

        System.out.println("\n--- Singleton Integrity Verification ---");
        System.out.println("  systemRefA Identity HashCode : " + System.identityHashCode(systemRefA));
        System.out.println("  systemRefB Identity HashCode : " + System.identityHashCode(systemRefB));
        System.out.println("  Are both references identical? (systemRefA == systemRefB): " + (systemRefA == systemRefB));
        System.out.println("  System Name: " + systemRefA.getSystemName());
        System.out.println("  Organization: " + systemRefA.getOrganization());

        // =========================================================================
        // 2. FACTORY DESIGN PATTERN
        // =========================================================================
        printSectionHeader("2. FACTORY DESIGN PATTERN: Species Sample Creation");
        System.out.println("Concept: Encapsulate object creation logic for diverse biological and environmental sample types.\n");

        // Factory creating Tissue Sample
        Sample tissueSample = SampleFactory.createSample(
                SampleType.TISSUE, "SMP-001", "Homo sapiens (Human)", SpeciesCategory.HUMAN
        );
        System.out.println("Created Sample Type: " + tissueSample.getSampleType() + " | Class: " + tissueSample.getClass().getSimpleName());

        // Factory creating Blood Sample
        Sample bloodSample = SampleFactory.createSample(
                SampleType.BLOOD, "SMP-002", "Mus musculus (House Mouse)", SpeciesCategory.ANIMAL
        );
        System.out.println("Created Sample Type: " + bloodSample.getSampleType() + " | Class: " + bloodSample.getClass().getSimpleName());

        // Factory creating Microbial Culture Sample
        Sample microbialSample = SampleFactory.createSample(
                SampleType.MICROBIAL_CULTURE, "SMP-003", "Escherichia coli K-12", SpeciesCategory.BACTERIA
        );
        System.out.println("Created Sample Type: " + microbialSample.getSampleType() + " | Class: " + microbialSample.getClass().getSimpleName());

        // Factory creating Environmental Sample
        Sample environmentalSample = SampleFactory.createSample(
                SampleType.ENVIRONMENTAL, "SMP-004", "Geobacillus stearothermophilus", SpeciesCategory.BACTERIA
        );
        System.out.println("Created Sample Type: " + environmentalSample.getSampleType() + " | Class: " + environmentalSample.getClass().getSimpleName());

        // Factory creating Genetic Material Sample
        Sample geneticSample = SampleFactory.createSample(
                SampleType.GENETIC_MATERIAL, "SMP-005", "Arabidopsis thaliana (Thale Cress)", SpeciesCategory.PLANT
        );
        System.out.println("Created Sample Type: " + geneticSample.getSampleType() + " | Class: " + geneticSample.getClass().getSimpleName());

        // =========================================================================
        // 3. BUILDER DESIGN PATTERN
        // =========================================================================
        printSectionHeader("3. BUILDER DESIGN PATTERN: Sequenced Data & Sample Profile Construction");
        System.out.println("Concept: Step-by-step construction of complex genomic data and metadata with validation.\n");

        System.out.println("--- Building Sequenced Data (DNA) using SequencedData.Builder ---");
        SequencedData humanDnaSeq = new SequencedData.Builder(
                "SEQ-HS-001", "DNA", "ATGCGATCGATCGATCGATCGATCGATCGAATTCTATAAATGCGATCGATC"
        )
        .setSequencingPlatform("Illumina NovaSeq 6000")
        .setCoverageDepth(120.0)
        .setQualityScoreQ(39.2)
        .setReadCount(25_000_000L)
        .setAssemblyStatus("Chromosome-Level Assembly")
        .addAnnotation("BRCA1 exon 11 locus")
        .addAnnotation("EcoRI Restriction Site: GAATTC")
        .addAnnotation("TATA-box Promoter: TATAAA")
        .build();
        System.out.println("Built Object: " + humanDnaSeq);

        System.out.println("\n--- Building Sequenced Data (RNA) using SequencedData.Builder ---");
        SequencedData bacterialRnaSeq = new SequencedData.Builder(
                "SEQ-EC-002", "RNA", "AUGCCGAUUUAGCGCAUUGGCGCGCCGGAUUCG"
        )
        .setSequencingPlatform("Oxford Nanopore MinION")
        .setCoverageDepth(65.5)
        .setQualityScoreQ(32.8)
        .setAssemblyStatus("Complete Transcriptome")
        .addAnnotation("16S rRNA ribosomal subunit")
        .build();
        System.out.println("Built Object: " + bacterialRnaSeq);

        System.out.println("\n--- Validation Check (Builder Enforces Correct Nucleotides) ---");
        try {
            new SequencedData.Builder("SEQ-ERR", "DNA", "ATGC_INVALID_CHARS_123").build();
        } catch (IllegalArgumentException e) {
            System.out.println("Caught Expected Validation Error: " + e.getMessage());
        }

        System.out.println("\n--- Fluent Profile Configuration via SampleProfileBuilder ---");
        Sample configuredHumanSample = new SampleProfileBuilder(tissueSample)
                .setResearcher("Dr. Evelyn Reed (Oncology Genomics)")
                .setCollectionDate("2026-02-14")
                .setStorageLocation("CryoVault-Ultra-80, Rack-4")
                .setStorageTemperatureCelsius(-196.0)
                .setSequencedData(humanDnaSeq)
                .addResearchProject("PRJ-CANCER-GENOME-2026")
                .build();
        configuredHumanSample.displaySampleInfo();

        // =========================================================================
        // 4. ALL THREE PATTERNS IN SYNERGY
        // =========================================================================
        printSectionHeader("4. ALL THREE PATTERNS IN SYNERGY: Real-World Biotechnology Workflow");
        System.out.println("Scenario: Collecting diverse samples, building sequenced data, registering in Singleton repository, querying, and genomic analysis.\n");

        SampleManagementSystem system = SampleManagementSystem.getInstance();

        // Step A: Register the configured human tissue sample
        system.registerSample(configuredHumanSample);

        // Step B: Build and register mouse blood sample
        SequencedData mouseSeq = new SequencedData.Builder("SEQ-MM-003", "DNA", "ATGGCCATTGTAATGGGCCGCTGCAATTCGCGCGCGCG")
                .setSequencingPlatform("PacBio Sequel II")
                .setCoverageDepth(85.0)
                .setQualityScoreQ(36.5)
                .addAnnotation("Hemoglobin beta chain")
                .build();
        Sample configuredMouseSample = new SampleProfileBuilder(bloodSample)
                .setResearcher("Dr. Marcus Chen")
                .setCollectionDate("2026-03-01")
                .setStorageLocation("Freezer Room 3, Unit B")
                .setStorageTemperatureCelsius(-80.0)
                .setSequencedData(mouseSeq)
                .addResearchProject("PRJ-IMMUNO-2026")
                .build();
        system.registerSample(configuredMouseSample);

        // Step C: Build and register microbial culture sample
        Sample configuredMicrobeSample = new SampleProfileBuilder(microbialSample)
                .setResearcher("Dr. Sarah Patel")
                .setCollectionDate("2026-03-10")
                .setStorageLocation("Incubator BioZone 2")
                .setStorageTemperatureCelsius(-80.0)
                .setSequencedData(bacterialRnaSeq)
                .addResearchProject("PRJ-ANTIBIOTIC-RESISTANCE")
                .build();
        system.registerSample(configuredMicrobeSample);

        // Step D: Build and register plant genetic material sample
        SequencedData plantDna = new SequencedData.Builder("SEQ-AT-005", "DNA", "CGCGCGGGCCCGGCGCGCATATCGATCGATCGCCCGGG")
                .setSequencingPlatform("Illumina NovaSeq 6000")
                .setCoverageDepth(150.0)
                .setQualityScoreQ(40.0)
                .addAnnotation("Chloroplast rbcL gene")
                .build();
        Sample configuredPlantSample = new SampleProfileBuilder(geneticSample)
                .setResearcher("Dr. Liam Vance")
                .setCollectionDate("2026-03-12")
                .setStorageLocation("DNA Vault Alpha")
                .setStorageTemperatureCelsius(-80.0)
                .setSequencedData(plantDna)
                .addResearchProject("PRJ-DROUGHT-RESISTANCE")
                .build();
        system.registerSample(configuredPlantSample);

        // Register environmental sample (without sequenced data yet - pending sequencing)
        Sample configuredEnvSample = new SampleProfileBuilder(environmentalSample)
                .setResearcher("Dr. Aisha Torres (Field Ecologist)")
                .setCollectionDate("2026-03-14")
                .setStorageLocation("Cold Storage Unit C")
                .setStorageTemperatureCelsius(-20.0)
                .addResearchProject("PRJ-EXTREMOPHILE-DISCOVERY")
                .build();
        system.registerSample(configuredEnvSample);

        // =========================================================================
        // 5. EFFICIENT RETRIEVAL & GENOMIC ANALYSIS
        // =========================================================================
        printSectionHeader("5. EFFICIENT RETRIEVAL & GENOMIC ANALYSIS");

        System.out.println("--- Retrieval by Sample ID: 'SMP-001' ---");
        Sample retrieved = system.getSampleById("SMP-001");
        System.out.println("Retrieved: " + retrieved.getId() + " - " + retrieved.getSpeciesName() + " (" + retrieved.getSampleType() + ")");

        System.out.println("\n--- Retrieval by Species Search: 'Mus musculus' ---");
        List<Sample> speciesResults = system.findSamplesBySpecies("Mus musculus");
        for (Sample s : speciesResults) {
            System.out.println("Found: " + s.getId() + " | Species: " + s.getSpeciesName() + " | Researcher: " + s.getResearcher());
        }

        System.out.println("\n--- Retrieval by Category: BACTERIA ---");
        List<Sample> bacterialSamples = system.findSamplesByCategory(SpeciesCategory.BACTERIA);
        for (Sample s : bacterialSamples) {
            System.out.println("Bacterial Sample: " + s.getId() + " | " + s.getSpeciesName() + " | Type: " + s.getSampleType());
        }

        System.out.println("\n--- In-Depth Genomic Sequence Analysis on SMP-001 ---");
        system.analyzeSampleSequence("SMP-001");

        System.out.println("--- Nucleotide Motif Search: Searching for Restriction Site 'GAATTC' (EcoRI) ---");
        List<Sample> motifHits = system.searchSamplesByMotif("GAATTC");
        System.out.println("Samples containing motif 'GAATTC': " + motifHits.size());
        for (Sample s : motifHits) {
            System.out.println("  -> Matched Sample: " + s.getId() + " (" + s.getSpeciesName() + ")");
        }

        System.out.println("\n--- High GC-Content Analysis (Threshold >= 60.0%) ---");
        List<Sample> highGcSamples = system.findSamplesWithHighGCContent(60.0);
        System.out.println("High GC-Content Samples count: " + highGcSamples.size());
        for (Sample s : highGcSamples) {
            System.out.printf("  -> %s (%s): GC Content = %.2f%%\n",
                    s.getId(), s.getSpeciesName(), s.getSequencedData().getGcContentPercentage());
        }

        System.out.println("\n--- Enterprise Analytics Executive Summary ---");
        system.generateAnalyticsSummary();

        printBanner("DEMO EXECUTION COMPLETE: ALL 3 PATTERNS DEMONSTRATED SUCCESSFULLY");
    }

    private static void printBanner(String title) {
        System.out.println("================================================================================");
        System.out.println("  " + title);
        System.out.println("================================================================================");
    }

    private static void printSectionHeader(String header) {
        System.out.println("\n--------------------------------------------------------------------------------");
        System.out.println(header);
        System.out.println("--------------------------------------------------------------------------------");
    }
}
