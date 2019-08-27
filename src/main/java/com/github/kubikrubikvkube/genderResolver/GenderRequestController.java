package com.github.kubikrubikvkube.genderResolver;

import com.github.kubikrubikvkube.genderResolver.name.NameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Stream;


@RestController
class GenderRequestController {
    private static Predicate<String> cyrillicPredicate = Pattern.compile("[\\p{IsCyrillic}]").asPredicate();
    @Autowired
    private NameRepository nameRepository;

    @PostMapping(value = "/name-resolver", produces = "application/json", consumes = "application/json")
    @ResponseBody
    ResponseEntity resolve(@RequestBody NameRequest request) {
        String name = request.getName();

        Optional<String> resolvedSexOptional = Stream.of(name.split("\\s+"))
                .filter(cyrillicPredicate)
                .map(n -> nameRepository.findSexByName(n))
                .filter(Objects::nonNull)
                .findAny();

        if (resolvedSexOptional.isPresent()) {
            Gender resolved_gender = Gender.fromString(resolvedSexOptional.get());
            GenderResponse genderResponse = new GenderResponse(resolved_gender);
            return ResponseEntity.ok(genderResponse);
        } else {
            return ResponseEntity.notFound().build();
        }


    }
}


