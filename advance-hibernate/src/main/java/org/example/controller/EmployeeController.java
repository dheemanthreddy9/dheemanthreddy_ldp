package org.example.controller;

import org.example.model.Department;
import org.example.model.Employee;
import org.example.model.Project;
import org.example.repository.DepartmentRepository;
import org.example.repository.EmployeeRepository;
import org.example.repository.ProjectRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final ProjectRepository projectRepository;

    public EmployeeController(EmployeeRepository employeeRepository,
                              DepartmentRepository departmentRepository,
                              ProjectRepository projectRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.projectRepository = projectRepository;
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        // Enforce bidirectional link on OneToOne Address
        if (employee.getAddress() != null) {
            employee.getAddress().setEmployee(employee);
        }
        Employee saved = employeeRepository.save(employee);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(employeeRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        return employeeRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Link Employee to Department (ManyToOne Owning side update)
    @PutMapping("/{id}/department/{deptId}")
    public ResponseEntity<Employee> assignDepartment(@PathVariable Long id, @PathVariable Long deptId) {
        Optional<Employee> empOpt = employeeRepository.findById(id);
        Optional<Department> deptOpt = departmentRepository.findById(deptId);

        if (empOpt.isPresent() && deptOpt.isPresent()) {
            Employee emp = empOpt.get();
            Department dept = deptOpt.get();
            emp.setDepartment(dept);
            return ResponseEntity.ok(employeeRepository.save(emp));
        }
        return ResponseEntity.notFound().build();
    }

    // Link Employee to Manager (Self-Referencing Relationship)
    @PutMapping("/{id}/manager/{managerId}")
    public ResponseEntity<Employee> assignManager(@PathVariable Long id, @PathVariable Long managerId) {
        Optional<Employee> empOpt = employeeRepository.findById(id);
        Optional<Employee> mgrOpt = employeeRepository.findById(managerId);

        if (empOpt.isPresent() && mgrOpt.isPresent()) {
            Employee emp = empOpt.get();
            Employee mgr = mgrOpt.get();
            emp.setManager(mgr);
            return ResponseEntity.ok(employeeRepository.save(emp));
        }
        return ResponseEntity.notFound().build();
    }

    // Link Employee to Project (ManyToMany Join Table update)
    @PutMapping("/{id}/projects/{projectId}")
    public ResponseEntity<Employee> assignProject(@PathVariable Long id, @PathVariable Long projectId) {
        Optional<Employee> empOpt = employeeRepository.findById(id);
        Optional<Project> projOpt = projectRepository.findById(projectId);

        if (empOpt.isPresent() && projOpt.isPresent()) {
            Employee emp = empOpt.get();
            Project proj = projOpt.get();
            emp.addProject(proj);
            return ResponseEntity.ok(employeeRepository.save(emp));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
