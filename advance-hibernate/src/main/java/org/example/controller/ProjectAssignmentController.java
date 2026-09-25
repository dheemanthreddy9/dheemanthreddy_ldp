package org.example.controller;

import org.example.model.Employee;
import org.example.model.Project;
import org.example.model.ProjectAssignment;
import org.example.repository.EmployeeRepository;
import org.example.repository.ProjectAssignmentRepository;
import org.example.repository.ProjectRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/assignments")
public class ProjectAssignmentController {

    private final ProjectAssignmentRepository assignmentRepository;
    private final EmployeeRepository employeeRepository;
    private final ProjectRepository projectRepository;

    public ProjectAssignmentController(ProjectAssignmentRepository assignmentRepository,
                                        EmployeeRepository employeeRepository,
                                        ProjectRepository projectRepository) {
        this.assignmentRepository = assignmentRepository;
        this.employeeRepository = employeeRepository;
        this.projectRepository = projectRepository;
    }

    @PostMapping
    public ResponseEntity<ProjectAssignment> createAssignment(@RequestBody Map<String, Object> payload) {
        Long employeeId = Long.valueOf(payload.get("employeeId").toString());
        Long projectId = Long.valueOf(payload.get("projectId").toString());
        String role = payload.getOrDefault("role", "Contributor").toString();

        Optional<Employee> empOpt = employeeRepository.findById(employeeId);
        Optional<Project> projOpt = projectRepository.findById(projectId);

        if (empOpt.isPresent() && projOpt.isPresent()) {
            ProjectAssignment assignment = new ProjectAssignment(empOpt.get(), projOpt.get(), role);
            ProjectAssignment saved = assignmentRepository.save(assignment);
            return new ResponseEntity<>(saved, HttpStatus.CREATED);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<ProjectAssignment>> getAllAssignments() {
        return ResponseEntity.ok(assignmentRepository.findAll());
    }
}
