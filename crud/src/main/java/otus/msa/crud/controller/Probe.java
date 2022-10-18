package otus.msa.crud.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/probe", produces = MediaType.APPLICATION_JSON_VALUE)
public class Probe {
    @GetMapping("/live")
    public ResponseEntity<Object> live() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/ready")
    public ResponseEntity<Object> ready() {
        return ResponseEntity.ok().build();
    }
}
