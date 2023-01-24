package com.example.weblab4.controllers;

import com.example.weblab4.entity.Point;
import com.example.weblab4.repo.UserRepository;
import com.example.weblab4.util.JsonUtil;
import com.example.weblab4.util.SetPointUtil;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.weblab4.connectionResponseRequest.req.PointRequest;
import com.example.weblab4.repo.PointRepository;
import com.example.weblab4.util.ResultUtil;
import com.example.weblab4.util.ValidationUtil;

import java.security.Principal;
import java.util.List;


@RestController
@RequestMapping("/api/points")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PointController {

    final PointRepository pointRepository;

    final ResultUtil resultUtil;

    final ValidationUtil validationUtil;

    final
    UserRepository userRepository;

    @Autowired
    public PointController(PointRepository pointRepository, ResultUtil resultUtil, ValidationUtil validationUtil, UserRepository userRepository) {
        this.pointRepository = pointRepository;
        this.resultUtil = resultUtil;
        this.validationUtil = validationUtil;
        this.userRepository = userRepository;
    }

    @GetMapping
    public ResponseEntity<?> getAll(Principal principal) {
        List<Point> points = pointRepository.findByOwner(principal.getName());
        return ResponseEntity.ok().body(points);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody PointRequest point, Principal principal) {
        if (!validationUtil.validateData(point)) {
            return ResponseEntity.badRequest().body(JsonUtil.getJsonMessage("Invalid parameters!"));
        }

        Point resultPoint = new SetPointUtil().getPoint(point, principal.getName());
        pointRepository.save(resultPoint);

        return ResponseEntity.ok().body(JsonUtil.getJsonPoint(resultPoint));
    }


    @DeleteMapping
    public ResponseEntity<?> clear(Principal principal) {
        pointRepository.deleteAllByOwner(principal.getName());
        return ResponseEntity.ok()
                .body(JsonUtil.getJsonMessage("Table is deleted successfully"));
    }
}
