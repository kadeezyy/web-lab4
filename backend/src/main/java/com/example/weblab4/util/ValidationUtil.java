package com.example.weblab4.util;

import com.example.weblab4.connectionResponseRequest.req.PointRequest;
import org.springframework.stereotype.Component;

@Component
public class ValidationUtil {

    private boolean validateX(double x) {
        x = NumberUtil.round(x);
        return x <= 3 && x >= -3;
    }

    private boolean validateY(double y) {
        y = NumberUtil.round(y);
        return y <= 5 && y >= -5;
    }

    private boolean validateR(double r) {
        r = NumberUtil.round(r);
        return r <= 3 && r >= 1;
    }

    public boolean validateData(PointRequest point) {
        return validateX(point.getX()) && validateY(point.getY()) && validateR(point.getR());
    }
}
