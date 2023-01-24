package com.example.weblab4.util;

import com.example.weblab4.connectionResponseRequest.resp.PointResponse;
import com.example.weblab4.entity.Point;
import com.example.weblab4.entity.User;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JsonUtil {
    public static String getJsonPoint(Point point) {
        PointResponse pointResponse = new PointResponse(point.getX(),
                point.getY(),
                point.getR(),
                point.getOwner(),
                point.getTime(),
                point.getResult());

        return new Gson().toJson(pointResponse);
    }


    public static String getJsonUser(String user) {
        return new Gson().toJson(user);
    }

    public static String getJsonMessage(String message) {
        return new Gson().toJson(Collections.singletonMap("message", message));
    }
}
