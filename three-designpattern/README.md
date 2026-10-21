# Biotechnology Sample Management System: Design Patterns in Java

A robust, enterprise-grade Java implementation demonstrating the three core **Creational Design Patterns** (**Singleton**, **Builder**, and **Factory**) applied to a **Biotechnology Species Sample & Genomic Management System**.

---

## 🔬 Problem Statement

A biotechnology company needs a system to store and manage species samples collected for their research projects. The samples can include a range of materials, such as **tissue**, **blood**, **microbial cultures**, **environmental samples**, and **genetic material**, from various species like **bacteria**, **fungi**, **plants**, **animals**, and **humans**. 

The system allows for the creation and storage of sample information, including species name, type of sample, and information about the **sequenced data** obtained from the samples.

Sequenced data is obtained from analyzing the nucleotide sequence of a DNA or RNA molecule and provides insight into the genetic makeup and characteristics of the organism. The sample management system provides efficient retrieval and analysis of stored sample information, including the sequenced data.

### Key Requirements
1. **Ability to store and manage information** related to species samples.
2. **Efficient retrieval and analysis** of stored sample information, including sequenced data (GC-content calculation, nucleotide motif search, base distribution, and analytics summaries).
3. **Flexibility to handle different types** of species samples and seamlessly accommodate future additions.

---

## 🏛️ Architecture & Pattern Mapping

```
+-------------------------------------------------------------------------+
|                        SampleManagementSystem                           |  <-- SINGLETON PATTERN
|  - Private constructor & Bill Pugh lazy holder (thread-safe)            |      (Centralized Sample Store,
|  - In-memory concurrent registry with O(1) retrieval                    |       Querying & Genomic Analytics)
|  - Analysis: GC-content, motif matching, statistics                     |
+-------------------------------------------------------------------------+
                                    |
                                    v
+-------------------------------------------------------------------------+
|                  SequencedData.Builder & SampleProfileBuilder           |  <-- BUILDER PATTERN
|  - SequencedData.Builder: Builds DNA/RNA molecules, platforms,          |      (Fluent step-by-step
|    coverage depths, Q-scores, base validation & annotations             |       construction & validation)
|  - SampleProfileBuilder: Configures storage temps, dates & projects     |
+-------------------------------------------------------------------------+
                                    |
                                    v
+-------------------------------------------------------------------------+
|                              SampleFactory                              |  <-- FACTORY PATTERN
|  + createSample(SampleType, id, speciesName, category): Sample          |      (Decouples instantiation
+-------------------------------------------------------------------------+       for extensible sample types)
       |                |                   |                  |                 |
       v                v                   v                  v                 v
+--------------+ +-------------+ +--------------------+ +--------------+ +--------------------+
| TissueSample | | BloodSample | | MicrobialCulture   | | Environmental| | GeneticMaterial    |
| (source,     | | (anticoag,  | | (medium, conc,     | | (ecosystem,  | | (nucleic acid,     |
| preservation)| |  volume)    | |  incubation temp)  | |  GPS, pH)    | |  A260/A280, conc)  |
+--------------+ +-------------+ +--------------------+ +--------------+ +--------------------+
```

---

## 🧩 Design Patterns Breakdown

### 1. Singleton Design Pattern (`com.designpatterns.singleton.SampleManagementSystem`)
* **Role**: Acts as the single, centralized, thread-safe bio-bank registry across all research teams.
* **Problem Solved**: Prevents fragmented or conflicting sample databases across research modules.
* **Implementation**: Uses the **Bill Pugh Singleton Holder** pattern (`SingletonHelper`), guaranteeing lazy loading, high performance, and thread-safety without synchronization overhead.
* **Features**:
  * Efficient retrieval by unique Sample ID ($O(1)$ lookup).
  * Filter searches by species name, species category (Bacteria, Fungi, Plant, Animal, Human), and sample type.
  * Genomic analysis: sequence length, GC-content computation, base distribution (A, T/U, G, C), nucleotide motif search (e.g., restriction sites like `GAATTC`), and high-level enterprise statistics.

### 2. Factory Design Pattern (`com.designpatterns.factory.SampleFactory`)
* **Role**: Instantiates specialized `Sample` objects without exposing creation logic to client code.
* **Problem Solved**: Provides flexibility to handle diverse sample types (Tissue, Blood, Microbial Culture, Environmental, Genetic Material) and allows new sample materials (e.g., Synthetic biology vectors, Fossilized ancient DNA) to be added without breaking existing code.
* **Interface & Hierarchy**:
  * `Sample` (interface) & `AbstractSample` (common metadata: ID, species, category, storage location, temperature, researcher).
  * `TissueSample`: Adds organ source and preservation technique (e.g., Liquid Nitrogen Cryopreservation).
  * `BloodSample`: Adds anticoagulant type (EDTA, Heparin) and volume.
  * `MicrobialCultureSample`: Adds growth medium, colony concentration (CFU/mL), and incubation temperature.
  * `EnvironmentalSample`: Adds ecosystem type, GPS coordinates, and pH level.
  * `GeneticMaterialSample`: Adds nucleic acid type (gDNA, Total RNA, cDNA), A260/A280 purity ratio, and concentration.

### 3. Builder Design Pattern (`com.designpatterns.builder.SequencedData.Builder` & `SampleProfileBuilder`)
* **Role**: Step-by-step construction of complex genomic sequence data and rich sample profiles.
* **Problem Solved**: Avoids "telescoping constructors" when dealing with numerous mandatory and optional attributes (sequencing platform, read count, coverage depth, Phred Q-score, assembly status, gene annotations, storage temps).
* **Validation**:
  * Ensures mandatory fields (`sequenceId`, `moleculeType`, `nucleotideSequence`) are present.
  * Validates nucleotide characters against molecule type (`ATCGN` for DNA, `AUCGN` for RNA).
  * Enforces non-negative coverage depths, read counts, and quality scores.

---

## 📂 Project Structure

```
3 design patterns/
├── DesignPatternsDemo.java                # Standalone single-file demonstration
├── README.md                              # Complete architectural documentation
├── bin/                                   # Compiled bytecode directory
└── src/
    └── com/
        └── designpatterns/
            ├── Main.java                  # Modular demonstration runner
            ├── builder/
            │   ├── SampleProfileBuilder.java # Builder for sample metadata
            │   └── SequencedData.java        # Builder for genomic sequence records
            ├── factory/
            │   ├── AbstractSample.java       # Base abstract class for samples
            │   ├── BloodSample.java          # Blood sample implementation
            │   ├── EnvironmentalSample.java  # Environmental sample implementation
            │   ├── GeneticMaterialSample.java# Purified DNA/RNA sample implementation
            │   ├── MicrobialCultureSample.java# Bacterial/fungal culture sample
            │   ├── Sample.java               # Core sample interface
            │   ├── SampleFactory.java        # Factory class
            │   ├── SampleType.java           # Sample category enum
            │   ├── SpeciesCategory.java      # Biological category enum
            │   └── TissueSample.java         # Tissue biopsy sample implementation
            └── singleton/
                └── SampleManagementSystem.java# Centralized Singleton repository & analyzer
```

---

## 🚀 How to Compile & Run

### Option 1: Modular Project (`src/`)

```bash
# Compile all source files into bin/
javac -d bin $(Get-ChildItem -Path src -Filter *.java -Recurse | Select-Object -ExpandProperty FullName)

# Execute the application
java -cp bin com.designpatterns.Main
```

### Option 2: Standalone Single File (`DesignPatternsDemo.java`)

```bash
# Compile
javac DesignPatternsDemo.java

# Execute
java DesignPatternsDemo
```

---

## 🧪 Sample Output

```text
================================================================================
  BIOTECHNOLOGY SAMPLE MANAGEMENT SYSTEM
================================================================================
Design Patterns Implementation: Singleton, Builder, and Factory

--------------------------------------------------------------------------------
1. SINGLETON DESIGN PATTERN: Centralized Sample Management System
--------------------------------------------------------------------------------
Concept: Ensure a single, globally accessible, thread-safe instance manages the repository.

Acquiring reference 1 (systemRefA)...
[SampleManagementSystem] Singleton instance initialized for Global Biotech Research Labs
Acquiring reference 2 (systemRefB)...

--- Singleton Integrity Verification ---
  systemRefA Identity HashCode : 1933863327
  systemRefB Identity HashCode : 1933863327
  Are both references identical? (systemRefA == systemRefB): true
  System Name: BioTrack-Genomics Enterprise System
  Organization: Global Biotech Research Labs

--------------------------------------------------------------------------------
2. FACTORY DESIGN PATTERN: Species Sample Creation
--------------------------------------------------------------------------------
Created Sample Type: TISSUE | Class: TissueSample
Created Sample Type: BLOOD | Class: BloodSample
Created Sample Type: MICROBIAL_CULTURE | Class: MicrobialCultureSample
Created Sample Type: ENVIRONMENTAL | Class: EnvironmentalSample
Created Sample Type: GENETIC_MATERIAL | Class: GeneticMaterialSample

--------------------------------------------------------------------------------
3. BUILDER DESIGN PATTERN: Sequenced Data & Sample Profile Construction
--------------------------------------------------------------------------------
Built Object: SequencedData [ID: SEQ-HS-001 | DNA | 51 bp | GC: 43.14% | Platform: Illumina NovaSeq 6000 | Depth: 120.0x | Q-Score: 39.2 | Assembly: Chromosome-Level Assembly | Annotations: 3]
Built Object: SequencedData [ID: SEQ-EC-002 | RNA | 33 bp | GC: 60.61% | Platform: Oxford Nanopore MinION | Depth: 65.5x | Q-Score: 32.8 | Assembly: Complete Transcriptome | Annotations: 1]
Caught Expected Validation Error: Invalid nucleotide character '_' for molecule type DNA

--------------------------------------------------------------------------------
4. ALL THREE PATTERNS IN SYNERGY: Real-World Biotechnology Workflow
--------------------------------------------------------------------------------
--------------------------------------------------------------------------------
5. EFFICIENT RETRIEVAL & GENOMIC ANALYSIS
--------------------------------------------------------------------------------
--- Retrieval by Sample ID: 'SMP-001' ---
Retrieved: SMP-001 - Homo sapiens (Human) (TISSUE)

--- In-Depth Genomic Sequence Analysis on SMP-001 ---
===================================================================
GENOMIC SEQUENCE ANALYSIS: Sample [SMP-001] (Homo sapiens (Human))
===================================================================
Species Category  : HUMAN
Sample Material   : TISSUE
Storage Location  : CryoVault-Ultra-80, Rack-4 (-196.0 deg C)
Molecule Type     : DNA
Sequence Length   : 51 base pairs (bp)
GC Content        : 43.14%
Sequencing Machine: Illumina NovaSeq 6000
Coverage Depth    : 120.0x | Q-Score: 39.2
Base Distribution : {A=15, C=11, T=14, G=11}
Assembly Status   : Chromosome-Level Assembly
Gene Annotations  : [BRCA1 exon 11 locus, EcoRI Restriction Site: GAATTC, TATA-box Promoter: TATAAA]

--- Nucleotide Motif Search: Searching for Restriction Site 'GAATTC' (EcoRI) ---
Samples containing motif 'GAATTC': 1
  -> Matched Sample: SMP-001 (Homo sapiens (Human))

--- Enterprise Analytics Executive Summary ---
*******************************************************************
        BIOTRACK-GENOMICS ENTERPRISE SYSTEM
              ORGANIZATION: Global Biotech Research Labs
*******************************************************************
Total Samples Registered : 5
Sequenced Samples        : 4
Pending Sequencing       : 1

Breakdown by Species Category:
  - BACTERIA    : 2
  - FUNGI       : 0
  - PLANT       : 1
  - ANIMAL      : 1
  - HUMAN       : 1

Breakdown by Sample Material Type:
  - TISSUE              : 1
  - BLOOD               : 1
  - MICROBIAL_CULTURE   : 1
  - ENVIRONMENTAL       : 1
  - GENETIC_MATERIAL    : 1

Average GC-Content across sequenced samples: 61.46%
*******************************************************************
```
