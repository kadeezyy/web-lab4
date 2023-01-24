package com.example.weblab4.repo;

import com.example.weblab4.entity.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PointRepository extends JpaRepository<Point, Long> {

    List<Point> findByOwner(String username);

    @Transactional
    void deleteAllByOwner(String owner);
}
