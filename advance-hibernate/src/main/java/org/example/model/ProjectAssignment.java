package org.example.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "project_assignments")
public class ProjectAssignment {

    @EmbeddedId
    private ProjectAssignmentKey id;

    @ManyToOne
    @MapsId("employeeId")
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @MapsId("projectId")
    @JoinColumn(name = "project_id")
    private Project project;

    private String role;
    private LocalDate assignedDate;

    public ProjectAssignment() {}

    public ProjectAssignment(Employee employee, Project project, String role) {
        this.employee = employee;
        this.project = project;
        this.role = role;
        this.assignedDate = LocalDate.now();
        this.id = new ProjectAssignmentKey(employee.getId(), project.getId());
    }

    public ProjectAssignmentKey getId() {
        return id;
    }

    public void setId(ProjectAssignmentKey id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public LocalDate getAssignedDate() {
        return assignedDate;
    }

    public void setAssignedDate(LocalDate assignedDate) {
        this.assignedDate = assignedDate;
    }
}
