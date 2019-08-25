package com.github.kubikrubikvkube.genderResolver;

import com.github.kubikrubikvkube.genderResolver.name.ErrorMessage;
import com.github.kubikrubikvkube.genderResolver.name.NameRepository;
import com.github.kubikrubikvkube.genderResolver.name.NameRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RestController
class GenderRequestController {
    @Autowired
    private NameRepository nameRepository;

    @PostMapping(value = "/name-resolver", produces = "application/json", consumes = "application/json")
    @ResponseBody
    ResponseEntity resolve(@RequestBody NameRequest name) {
        Optional<ErrorMessage> validationResult = NameRequestValidator.validate(name);
        if (validationResult.isPresent()) {
            ErrorMessage errorMessage = validationResult.get();
            return ResponseEntity.badRequest().body(errorMessage);
        }

        String resolved_sex = nameRepository.findSexByName(name.getName());
        Gender resolved_gender = Gender.fromString(resolved_sex);
        GenderResponse genderResponse = new GenderResponse(resolved_gender);
        return ResponseEntity.ok(genderResponse);

    }
}


