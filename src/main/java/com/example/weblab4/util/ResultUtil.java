package com.example.weblab4.util;

import org.springframework.stereotype.Component;

@Component
public class ResultUtil {
    private boolean checkRect(double x, double y, double r) {
        return x >= 3 && y <= 0 && y < r && x < r/2;
    }

    private boolean checkLinear(double x, double y, double r) {
        return x >= 0 && y >= 0 && y < (x + r) / 2;
    }

    private boolean checkCircle(double x, double y, double r) {
        return x <= 0 && y <= 0 && x * x + y * y < r * r / 4;
    }

    public String check(double x, double y, double r) {
        return (x >= 0 && x <= r && y >= 0 && y <= r / 2 && 2 * y + x <= r) || //triangle
                (x >= 0 && x <= r && y <= 0 && y >= -r / 2) || //rectangle
                (x <= 0 && y <= 0 && Math.pow(x, 2) + Math.pow(y, 2) <= Math.pow(r, 2)) ? "Попадание" : "Промах";
//        return checkCircle(x, y, r) || checkRect(x, y, r) || checkLinear(x, y, r) ? "Попадание" : "Промах";
    }
}
