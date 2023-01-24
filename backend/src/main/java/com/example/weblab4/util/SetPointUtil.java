package com.example.weblab4.util;

import com.example.weblab4.connectionResponseRequest.req.PointRequest;
import com.example.weblab4.entity.Point;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SetPointUtil {

    ResultUtil resultUtil = new ResultUtil();

    public Point getPoint (PointRequest point, String username) {
        String time = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(LocalDateTime.now());
        Point resultPoint = Point.builder()         //add enum with string parameters
                .x(point.getX())
                .y(point.getY())
                .r(point.getR())
                .result(resultUtil.check(point.getX(), point.getY(), point.getR()))
                .time(time)
                .owner(username)
                .build();
        return resultPoint;
    }
}
