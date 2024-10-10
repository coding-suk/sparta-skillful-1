package com.web.startspring.repository;

import com.web.startspring.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerRepository extends JpaRepository<Manager, Long> {
    Manager createManager(Manager manager);
}
