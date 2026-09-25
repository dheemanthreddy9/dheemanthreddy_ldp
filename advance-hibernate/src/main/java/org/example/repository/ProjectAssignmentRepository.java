package org.example.repository;

import org.example.model.ProjectAssignment;
import org.example.model.ProjectAssignmentKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectAssignmentRepository extends JpaRepository<ProjectAssignment, ProjectAssignmentKey> {
}
