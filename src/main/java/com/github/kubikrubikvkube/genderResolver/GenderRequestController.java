package com.github.kubikrubikvkube.genderResolver;

import com.github.kubikrubikvkube.genderResolver.name.NameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GenderRequestController {
    @Autowired
    NameRepository nameRepository;

    @PostMapping(value = "/name-resolver")
    public GenderResponse resolve(@RequestParam(value = "name") String requested_name) {
        if (requested_name != null && !requested_name.isBlank()) {
            String resolved_sex = nameRepository.findSexByName(requested_name);
            Gender resolved_gender = Gender.fromString(resolved_sex);
            return new GenderResponse(resolved_gender);
        }
        return new GenderResponse(Gender.UNKNOWN);
    }
}


