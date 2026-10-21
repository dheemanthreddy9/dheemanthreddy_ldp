import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * ============================================================================
 * BIOTECHNOLOGY SPECIES SAMPLE MANAGEMENT SYSTEM
 * ============================================================================
 * Demonstration of 3 Core Gang of Four (GoF) Design Patterns:
 *  1. Singleton Pattern : Centralized SampleManagementSystem repository & analytics
 *  2. Factory Pattern   : SampleFactory for creating diverse biological sample types
 *  3. Builder Pattern   : SequencedData.Builder & SampleProfileBuilder for complex genetic data
 * ============================================================================
 */

// ============================================================================
// DOMAIN ENUMS
// ============================================================================

enum SampleType {
    TISSUE,
    BLOOD,
    MICROBIAL_CULTURE,
    ENVIRONMENTAL,
    GENETIC_MATERIAL
}

enum SpeciesCategory {
    BACTERIA,
    FUNGI,
    PLANT,
    ANIMAL,
    HUMAN
}

// ============================================================================
// 1. BUILDER DESIGN PATTERN: SequencedData
// ============================================================================

class SequencedData {
    private final String sequenceId;
    private final String moleculeType;      // DNA or RNA
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

    public String getSequenceId() { return sequenceId; }
    public String getMoleculeType() { return moleculeType; }
    public String getNucleotideSequence() { return nucleotideSequence; }
    public int getSequenceLength() { return nucleotideSequence != null ? nucleotideSequence.length() : 0; }
    public String getSequencingPlatform() { return sequencingPlatform; }
    public long getReadCount() { return readCount; }
    public double getCoverageDepth() { return coverageDepth; }
    public double getQualityScoreQ() { return qualityScoreQ; }
    public String getAssemblyStatus() { return assemblyStatus; }
    public List<String> getAnnotations() { return annotations; }

    public double getGcContentPercentage() {
        if (nucleotideSequence == null || nucleotideSequence.isEmpty()) return 0.0;
        long gcCount = 0;
        for (char base : nucleotideSequence.toCharArray()) {
            if (base == 'G' || base == 'C') gcCount++;
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
        if (motif == null || nucleotideSequence == null) return false;
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
        private final String sequenceId;
        private final String moleculeType;
        private final String nucleotideSequence;
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
            if (platform != null && !platform.trim().isEmpty()) this.sequencingPlatform = platform.trim();
            return this;
        }

        public Builder setReadCount(long readCount) {
            if (readCount < 0) throw new IllegalArgumentException("Read count cannot be negative.");
            this.readCount = readCount;
            return this;
        }

        public Builder setCoverageDepth(double depth) {
            if (depth < 0) throw new IllegalArgumentException("Coverage depth cannot be negative.");
            this.coverageDepth = depth;
            return this;
        }

        public Builder setQualityScoreQ(double qScore) {
            if (qScore < 0) throw new IllegalArgumentException("Quality score cannot be negative.");
            this.qualityScoreQ = qScore;
            return this;
        }

        public Builder setAssemblyStatus(String status) {
            if (status != null && !status.trim().isEmpty()) this.assemblyStatus = status.trim();
            return this;
        }

        public Builder addAnnotation(String annotation) {
            if (annotation != null && !annotation.trim().isEmpty()) this.annotations.add(annotation.trim());
            return this;
        }

        public SequencedData build() {
            return new SequencedData(this);
        }
    }
}

// ============================================================================
// 2. FACTORY DESIGN PATTERN: Sample Hierarchy & Factory
// ============================================================================

interface Sample {
    String getId();
    String getSpeciesName();
    SpeciesCategory getSpeciesCategory();
    SampleType getSampleType();
    String getCollectionDate();
    void setCollectionDate(String date);
    String getStorageLocation();
    void setStorageLocation(String location);
    double getStorageTemperatureCelsius();
    void setStorageTemperatureCelsius(double temp);
    String getResearcher();
    void setResearcher(String researcher);
    SequencedData getSequencedData();
    void setSequencedData(SequencedData data);
    boolean hasSequencedData();
    String getSampleDetails();
    void displaySampleInfo();
}

abstract class AbstractSample implements Sample {
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
        if (id == null || id.trim().isEmpty()) throw new IllegalArgumentException("Sample ID cannot be empty.");
        if (speciesName == null || speciesName.trim().isEmpty()) throw new IllegalArgumentException("Species name cannot be empty.");
        if (speciesCategory == null || sampleType == null) throw new IllegalArgumentException("Category and Type cannot be null.");
        this.id = id.trim();
        this.speciesName = speciesName.trim();
        this.speciesCategory = speciesCategory;
        this.sampleType = sampleType;
    }

    @Override public String getId() { return id; }
    @Override public String getSpeciesName() { return speciesName; }
    @Override public SpeciesCategory getSpeciesCategory() { return speciesCategory; }
    @Override public SampleType getSampleType() { return sampleType; }
    @Override public String getCollectionDate() { return collectionDate; }
    @Override public void setCollectionDate(String collectionDate) { this.collectionDate = collectionDate; }
    @Override public String getStorageLocation() { return storageLocation; }
    @Override public void setStorageLocation(String storageLocation) { this.storageLocation = storageLocation; }
    @Override public double getStorageTemperatureCelsius() { return storageTemperatureCelsius; }
    @Override public void setStorageTemperatureCelsius(double temp) { this.storageTemperatureCelsius = temp; }
    @Override public String getResearcher() { return researcher; }
    @Override public void setResearcher(String researcher) { this.researcher = researcher; }
    @Override public SequencedData getSequencedData() { return sequencedData; }
    @Override public void setSequencedData(SequencedData sequencedData) { this.sequencedData = sequencedData; }
    @Override public boolean hasSequencedData() { return sequencedData != null; }

    @Override
    public void displaySampleInfo() {
        System.out.println("-------------------------------------------------------------------");
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
        System.out.println("-------------------------------------------------------------------");
    }
}

class TissueSample extends AbstractSample {
    private String organOrTissueSource = "Hepatic Tissue";
    private String preservationMethod = "Cryopreservation (Liquid Nitrogen, -196 deg C)";

    public TissueSample(String id, String speciesName, SpeciesCategory speciesCategory) {
        super(id, speciesName, speciesCategory, SampleType.TISSUE);
        this.storageTemperatureCelsius = -196.0;
    }
    public void setOrganOrTissueSource(String source) { this.organOrTissueSource = source; }
    public void setPreservationMethod(String method) { this.preservationMethod = method; }
    @Override public String getSampleDetails() {
        return "Source: " + organOrTissueSource + " | Preservation: " + preservationMethod;
    }
}

class BloodSample extends AbstractSample {
    private String anticoagulant = "K2-EDTA";
    private double volumeMl = 5.0;

    public BloodSample(String id, String speciesName, SpeciesCategory speciesCategory) {
        super(id, speciesName, speciesCategory, SampleType.BLOOD);
        this.storageTemperatureCelsius = -80.0;
    }
    public void setAnticoagulant(String anticoagulant) { this.anticoagulant = anticoagulant; }
    public void setVolumeMl(double volumeMl) { this.volumeMl = volumeMl; }
    @Override public String getSampleDetails() {
        return "Anticoagulant: " + anticoagulant + " | Volume: " + volumeMl + " mL";
    }
}

class MicrobialCultureSample extends AbstractSample {
    private String growthMedium = "Luria-Bertani (LB) Broth";
    private String colonyConcentration = "1.5 x 10^8 CFU/mL";
    private double incubationTemperature = 37.0;

    public MicrobialCultureSample(String id, String speciesName, SpeciesCategory speciesCategory) {
        super(id, speciesName, speciesCategory, SampleType.MICROBIAL_CULTURE);
        this.storageTemperatureCelsius = -80.0;
    }
    public void setGrowthMedium(String medium) { this.growthMedium = medium; }
    public void setColonyConcentration(String conc) { this.colonyConcentration = conc; }
    @Override public String getSampleDetails() {
        return "Medium: " + growthMedium + " | Concentration: " + colonyConcentration + " | Incubated: " + incubationTemperature + " deg C";
    }
}

class EnvironmentalSample extends AbstractSample {
    private String ecosystemType = "Deep-Sea Hydrothermal Vent Sediment";
    private String gpsCoordinates = "27.2589 N, 111.4082 W";
    private double pHLevel = 7.4;

    public EnvironmentalSample(String id, String speciesName, SpeciesCategory speciesCategory) {
        super(id, speciesName, speciesCategory, SampleType.ENVIRONMENTAL);
        this.storageTemperatureCelsius = -20.0;
    }
    public void setEcosystemType(String eco) { this.ecosystemType = eco; }
    public void setGpsCoordinates(String gps) { this.gpsCoordinates = gps; }
    @Override public String getSampleDetails() {
        return "Ecosystem: " + ecosystemType + " | GPS: " + gpsCoordinates + " | pH: " + pHLevel;
    }
}

class GeneticMaterialSample extends AbstractSample {
    private String nucleicAcidType = "Purified Genomic DNA (gDNA)";
    private double purityRatioA260A280 = 1.85;
    private double concentrationNgPerUl = 240.0;

    public GeneticMaterialSample(String id, String speciesName, SpeciesCategory speciesCategory) {
        super(id, speciesName, speciesCategory, SampleType.GENETIC_MATERIAL);
        this.storageTemperatureCelsius = -80.0;
    }
    public void setNucleicAcidType(String type) { this.nucleicAcidType = type; }
    public void setPurityRatioA260A280(double ratio) { this.purityRatioA260A280 = ratio; }
    @Override public String getSampleDetails() {
        return "Type: " + nucleicAcidType + " | A260/A280: " + purityRatioA260A280 + " | Conc: " + concentrationNgPerUl + " ng/uL";
    }
}

class SampleFactory {
    public static Sample createSample(SampleType type, String id, String speciesName, SpeciesCategory speciesCategory) {
        if (type == null) throw new IllegalArgumentException("SampleType cannot be null.");
        switch (type) {
            case TISSUE: return new TissueSample(id, speciesName, speciesCategory);
            case BLOOD: return new BloodSample(id, speciesName, speciesCategory);
            case MICROBIAL_CULTURE: return new MicrobialCultureSample(id, speciesName, speciesCategory);
            case ENVIRONMENTAL: return new EnvironmentalSample(id, speciesName, speciesCategory);
            case GENETIC_MATERIAL: return new GeneticMaterialSample(id, speciesName, speciesCategory);
            default: throw new UnsupportedOperationException("Unsupported sample type: " + type);
        }
    }
}

// Fluent Builder for assembling sample profiles
class SampleProfileBuilder {
    private final Sample sample;
    private String researcher = "BioTech Senior Scientist";
    private String collectionDate = "2026-03-15";
    private String storageLocation = "Cryo-Storage Wing B";
    private Double storageTemperatureCelsius = null;
    private SequencedData sequencedData = null;
    private final List<String> researchProjects = new ArrayList<>();

    public SampleProfileBuilder(Sample sample) {
        if (sample == null) throw new IllegalArgumentException("Sample cannot be null.");
        this.sample = sample;
    }

    public SampleProfileBuilder setResearcher(String researcher) {
        if (researcher != null) this.researcher = researcher.trim();
        return this;
    }

    public SampleProfileBuilder setCollectionDate(String date) {
        if (date != null) this.collectionDate = date.trim();
        return this;
    }

    public SampleProfileBuilder setStorageLocation(String location) {
        if (location != null) this.storageLocation = location.trim();
        return this;
    }

    public SampleProfileBuilder setStorageTemperatureCelsius(double temp) {
        this.storageTemperatureCelsius = temp;
        return this;
    }

    public SampleProfileBuilder setSequencedData(SequencedData data) {
        this.sequencedData = data;
        return this;
    }

    public SampleProfileBuilder addResearchProject(String project) {
        if (project != null) this.researchProjects.add(project.trim());
        return this;
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

// ============================================================================
// 3. SINGLETON DESIGN PATTERN: Centralized Sample Management System
// ============================================================================

class SampleManagementSystem {
    private final String systemName = "BioTrack-Genomics Enterprise System";
    private final String organization = "Global Biotech Research Labs";
    private final Map<String, Sample> sampleStore = new ConcurrentHashMap<>();

    private SampleManagementSystem() {
        System.out.println("[SampleManagementSystem] Singleton instance initialized for " + organization);
    }

    private static class SingletonHelper {
        private static final SampleManagementSystem INSTANCE = new SampleManagementSystem();
    }

    public static SampleManagementSystem getInstance() {
        return SingletonHelper.INSTANCE;
    }

    public String getSystemName() { return systemName; }
    public String getOrganization() { return organization; }

    public synchronized void registerSample(Sample sample) {
        if (sample == null) throw new IllegalArgumentException("Cannot register null sample.");
        sampleStore.put(sample.getId(), sample);
    }

    public Sample getSampleById(String id) {
        return sampleStore.get(id);
    }

    public List<Sample> findSamplesBySpecies(String species) {
        if (species == null) return Collections.emptyList();
        String q = species.toLowerCase();
        return sampleStore.values().stream()
                .filter(s -> s.getSpeciesName().toLowerCase().contains(q))
                .collect(Collectors.toList());
    }

    public List<Sample> findSamplesByCategory(SpeciesCategory category) {
        return sampleStore.values().stream()
                .filter(s -> s.getSpeciesCategory() == category)
                .collect(Collectors.toList());
    }

    public List<Sample> searchSamplesByMotif(String motif) {
        if (motif == null) return Collections.emptyList();
        return sampleStore.values().stream()
                .filter(Sample::hasSequencedData)
                .filter(s -> s.getSequencedData().containsMotif(motif))
                .collect(Collectors.toList());
    }

    public List<Sample> findSamplesWithHighGCContent(double threshold) {
        return sampleStore.values().stream()
                .filter(Sample::hasSequencedData)
                .filter(s -> s.getSequencedData().getGcContentPercentage() >= threshold)
                .collect(Collectors.toList());
    }

    public void analyzeSampleSequence(String sampleId) {
        Sample sample = getSampleById(sampleId);
        if (sample == null) {
            System.out.println("[Error] Sample not found: " + sampleId);
            return;
        }

        System.out.println("===================================================================");
        System.out.println("GENOMIC SEQUENCE ANALYSIS: Sample [" + sample.getId() + "] (" + sample.getSpeciesName() + ")");
        System.out.println("===================================================================");
        System.out.println("Species Category  : " + sample.getSpeciesCategory());
        System.out.println("Sample Material   : " + sample.getSampleType());
        System.out.println("Storage Location  : " + sample.getStorageLocation() + " (" + sample.getStorageTemperatureCelsius() + " deg C)");

        SequencedData seq = sample.getSequencedData();
        if (seq == null) {
            System.out.println("Status            : No sequenced data associated with this sample.");
            System.out.println("===================================================================\n");
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
        System.out.println("===================================================================\n");
    }

    public void generateAnalyticsSummary() {
        System.out.println("*******************************************************************");
        System.out.println("        " + systemName.toUpperCase());
        System.out.println("              ORGANIZATION: " + organization);
        System.out.println("*******************************************************************");

        int total = sampleStore.size();
        long sequencedCount = sampleStore.values().stream().filter(Sample::hasSequencedData).count();
        long pendingCount = total - sequencedCount;

        System.out.println("Total Samples Registered : " + total);
        System.out.println("Sequenced Samples        : " + sequencedCount);
        System.out.println("Pending Sequencing       : " + pendingCount);

        Map<SpeciesCategory, Long> categoryCount = sampleStore.values().stream()
                .collect(Collectors.groupingBy(Sample::getSpeciesCategory, Collectors.counting()));
        System.out.println("\nBreakdown by Species Category:");
        for (SpeciesCategory cat : SpeciesCategory.values()) {
            System.out.printf("  - %-12s: %d\n", cat, categoryCount.getOrDefault(cat, 0L));
        }

        Map<SampleType, Long> typeCount = sampleStore.values().stream()
                .collect(Collectors.groupingBy(Sample::getSampleType, Collectors.counting()));
        System.out.println("\nBreakdown by Sample Material Type:");
        for (SampleType st : SampleType.values()) {
            System.out.printf("  - %-20s: %d\n", st, typeCount.getOrDefault(st, 0L));
        }

        double avgGc = sampleStore.values().stream()
                .filter(Sample::hasSequencedData)
                .mapToDouble(s -> s.getSequencedData().getGcContentPercentage())
                .average()
                .orElse(0.0);
        System.out.printf("\nAverage GC-Content across sequenced samples: %.2f%%\n", avgGc);
        System.out.println("*******************************************************************\n");
    }
}

// ============================================================================
// DEMO RUNNER
// ============================================================================

public class DesignPatternsDemo {

    public static void main(String[] args) {
        System.out.println("================================================================================");
        System.out.println("  BIOTECHNOLOGY SAMPLE MANAGEMENT SYSTEM");
        System.out.println("================================================================================");
        System.out.println("Design Patterns Implementation: Singleton, Builder, and Factory\n");

        // 1. Singleton Test
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("1. SINGLETON DESIGN PATTERN: Centralized Sample Management System");
        System.out.println("--------------------------------------------------------------------------------");
        SampleManagementSystem sysA = SampleManagementSystem.getInstance();
        SampleManagementSystem sysB = SampleManagementSystem.getInstance();
        System.out.println("sysA HashCode: " + System.identityHashCode(sysA));
        System.out.println("sysB HashCode: " + System.identityHashCode(sysB));
        System.out.println("Are references identical? (sysA == sysB): " + (sysA == sysB));

        // 2. Factory Test
        System.out.println("\n--------------------------------------------------------------------------------");
        System.out.println("2. FACTORY DESIGN PATTERN: Species Sample Creation");
        System.out.println("--------------------------------------------------------------------------------");
        Sample humanTissue = SampleFactory.createSample(SampleType.TISSUE, "SMP-001", "Homo sapiens (Human)", SpeciesCategory.HUMAN);
        Sample mouseBlood = SampleFactory.createSample(SampleType.BLOOD, "SMP-002", "Mus musculus (House Mouse)", SpeciesCategory.ANIMAL);
        Sample eColi = SampleFactory.createSample(SampleType.MICROBIAL_CULTURE, "SMP-003", "Escherichia coli K-12", SpeciesCategory.BACTERIA);
        Sample envSample = SampleFactory.createSample(SampleType.ENVIRONMENTAL, "SMP-004", "Geobacillus stearothermophilus", SpeciesCategory.BACTERIA);
        Sample plantDnaSample = SampleFactory.createSample(SampleType.GENETIC_MATERIAL, "SMP-005", "Arabidopsis thaliana", SpeciesCategory.PLANT);
        System.out.println("Created 5 diverse samples using SampleFactory.");

        // 3. Builder Test
        System.out.println("\n--------------------------------------------------------------------------------");
        System.out.println("3. BUILDER DESIGN PATTERN: Sequenced Data & Sample Profile Construction");
        System.out.println("--------------------------------------------------------------------------------");
        SequencedData humanSeq = new SequencedData.Builder("SEQ-001", "DNA", "ATGCGATCGATCGATCGAATTCTATAAATGCGATC")
                .setSequencingPlatform("Illumina NovaSeq 6000")
                .setCoverageDepth(120.0)
                .setQualityScoreQ(39.2)
                .addAnnotation("BRCA1 locus")
                .addAnnotation("EcoRI Site: GAATTC")
                .build();
        System.out.println("Built SequencedData: " + humanSeq);

        new SampleProfileBuilder(humanTissue)
                .setResearcher("Dr. Evelyn Reed")
                .setCollectionDate("2026-02-14")
                .setStorageLocation("CryoVault Alpha")
                .setStorageTemperatureCelsius(-196.0)
                .setSequencedData(humanSeq)
                .addResearchProject("PRJ-CANCER-GENOME")
                .build();
        humanTissue.displaySampleInfo();

        // 4. Synergy & Management Test
        System.out.println("\n--------------------------------------------------------------------------------");
        System.out.println("4. SYNERGY: REGISTRATION, QUERYING & GENOMIC ANALYSIS");
        System.out.println("--------------------------------------------------------------------------------");
        sysA.registerSample(humanTissue);

        SequencedData mouseSeq = new SequencedData.Builder("SEQ-002", "DNA", "ATGGCCATTGTAATGGGCCGCTGCAATTCGCGCGCGCG")
                .setSequencingPlatform("PacBio Sequel II")
                .setCoverageDepth(85.0)
                .setQualityScoreQ(36.5)
                .build();
        new SampleProfileBuilder(mouseBlood)
                .setResearcher("Dr. Marcus Chen")
                .setSequencedData(mouseSeq)
                .build();
        sysA.registerSample(mouseBlood);

        SequencedData eColiRna = new SequencedData.Builder("SEQ-003", "RNA", "AUGCCGAUUUAGCGCAUUGGCGCGCCGGAUUCG")
                .setSequencingPlatform("Oxford Nanopore MinION")
                .setCoverageDepth(65.5)
                .setQualityScoreQ(32.8)
                .build();
        new SampleProfileBuilder(eColi)
                .setResearcher("Dr. Sarah Patel")
                .setSequencedData(eColiRna)
                .build();
        sysA.registerSample(eColi);

        SequencedData plantDna = new SequencedData.Builder("SEQ-005", "DNA", "CGCGCGGGCCCGGCGCGCATATCGATCGATCGCCCGGG")
                .setSequencingPlatform("Illumina NovaSeq 6000")
                .setCoverageDepth(150.0)
                .build();
        new SampleProfileBuilder(plantDnaSample)
                .setResearcher("Dr. Liam Vance")
                .setSequencedData(plantDna)
                .build();
        sysA.registerSample(plantDnaSample);

        new SampleProfileBuilder(envSample)
                .setResearcher("Dr. Aisha Torres")
                .build();
        sysA.registerSample(envSample);

        // Analysis & Search
        sysA.analyzeSampleSequence("SMP-001");

        List<Sample> motifMatches = sysA.searchSamplesByMotif("GAATTC");
        System.out.println("Motif 'GAATTC' Matches: " + motifMatches.size());
        for (Sample s : motifMatches) {
            System.out.println("  -> " + s.getId() + " (" + s.getSpeciesName() + ")");
        }

        sysA.generateAnalyticsSummary();
        System.out.println("Execution successfully verified.");
    }
}
