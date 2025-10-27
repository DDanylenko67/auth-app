package dev.ddanylenko.dataapi;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class ProcessController {

    @Value("${app.internal-token}")
    private String internalToken;

    @PostMapping("/transform")
    public ResponseEntity<Map<String, String>> transform(@RequestHeader(value = "X-Internal-Token", required = false) String header,
                                                         @RequestBody Map<String,String> body) {

        if(header==null || !header.equals(internalToken)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        String text = body.get("text");
        String result = text.toUpperCase() + "!!!!";
        return ResponseEntity.ok(Map.of("result", result));
    }

}
