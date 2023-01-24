package com.example.weblab4.connectionResponseRequest.resp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PointResponse {
    Double x, y, r;
    String username, time, result;
}
