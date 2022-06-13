package com.example.demo;

import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("rest/api/security")
public class SecurityController {

    @RolesAllowed("ADMIN")
    @PostMapping(value = "/with-valid", produces = APPLICATION_JSON_VALUE)
    public @ResponseBody Data withValid(@RequestBody @Valid Data data) {
        return data;
    }

}
