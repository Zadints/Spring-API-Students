package org.zadints.zadintsapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/github")
public class GithubController {

    @PostMapping
    public ResponseEntity<Void> receiveWebhook(@RequestHeader("X-GitHub-Event") String event,
                                               @RequestHeader("X-Hub-Signature-256") String signature,
                                               @RequestBody byte[] payload) {

        if (!verifier.isValid(payload, signature)) {
            return ResponseEntity.status(401).build();
        }

        if ("push".equals(event)) {

            GithubPushDto dto = objectMapper.readValue(
                    payload,
                    GithubPushDto.class
            );

            // 3. Sincronizar la base de datos
            syncService.sync(dto);
        }

        return ResponseEntity.accepted().build();
    }

}
