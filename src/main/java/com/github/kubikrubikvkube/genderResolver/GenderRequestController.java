package com.github.kubikrubikvkube.genderResolver;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GenderRequestController {

    @PostMapping(value = "/name-resolver")
    public GenderResponse resolve(@RequestParam(value = "name") String requested_name) {
        return new GenderResponse(Gender.UNKNOWN);
    }
}


