package org.example.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    private String jobTitle;

    // 1. One-to-One Mapping
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    @JsonManagedReference
    private Address address;

    // 2. Many-to-One Mapping
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "")
    @JsonBackReference
    private Department department;

    // 3. Many-to-Many Mapping
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(
        name = "",
        joinColumns = @JoinColumn(name = ""),
        inverseJoinColumns = @JoinColumn(name = "")
    )
    @JsonIgnoreProperties("employees")
    private Set<Project> projects = new HashSet<>();

    // 4.Many-to-One
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id")
    @JsonIgnoreProperties({"subordinates", "manager", "department", "projects"})
    private Employee manager;

    // 4. One-to-Many
    @OneToMany(mappedBy = "manager", cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"manager", "department", "projects"})
    private Set<Employee> subordinates = new HashSet<>();


    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "employee_phones", joinColumns = @JoinColumn(name = "employee_id"))
    @Column(name = "phone_number")
    private List<String> phoneNumbers = new ArrayList<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "employee_skills", joinColumns = @JoinColumn(name = "employee_id"))
    @MapKeyColumn(name = "skill_name")
    @Column(name = "proficiency_level")
    private Map<String, String> skills = new HashMap<>();

    public Employee() {}

    public Employee(String name, String email, String jobTitle) {
        this.name = name;
        this.email = email;
        this.jobTitle = jobTitle;
    }

    // Helper methods
    public void addProject(Project project) {
        this.projects.add(project);
        project.getEmployees().add(this);
    }

    public void removeProject(Project project) {
        this.projects.remove(project);
        project.getEmployees().remove(this);
    }

    public void addSubordinate(Employee subordinate) {
        this.subordinates.add(subordinate);
        subordinate.setManager(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
        if (address != null) {
            address.setEmployee(this);
        }
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public Set<Employee> getSubordinates() {
        return subordinates;
    }

    public void setSubordinates(Set<Employee> subordinates) {
        this.subordinates = subordinates;
    }

    public List<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public Map<String, String> getSkills() {
        return skills;
    }

    public void setSkills(Map<String, String> skills) {
        this.skills = skills;
    }
}
