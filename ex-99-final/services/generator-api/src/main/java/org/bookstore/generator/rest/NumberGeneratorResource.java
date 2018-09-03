package org.bookstore.generator.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for Generating Numbers.
 */
@RestController
@RequestMapping("/api")
public class NumberGeneratorResource {

    private final Logger log = LoggerFactory.getLogger(NumberGeneratorResource.class);

    /**
     * GET  /numbers : generates a number.
     *
     * @return the ResponseEntity with status 200 (OK) and the generated number
     */
    @GetMapping("/numbers")
    public ResponseEntity<String> generateNumber() {
        log.debug("REST request to generate a number");
        String result = "BK-" + Math.random();
        return ResponseEntity.ok()
                .body(result);
    }

    @GetMapping("/numbers/health")
    public ResponseEntity health() {
        log.info("Alive and Kicking !!!");
        return ResponseEntity.ok().build();
    }
}
