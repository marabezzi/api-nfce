package br.com.atom.api_nfce.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.atom.api_nfce.dtos.NfceRequestDTO;
import br.com.atom.api_nfce.dtos.NfceResponseDTO;
import br.com.atom.api_nfce.services.NfceService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/nfce")
public class NfceController {

    private final NfceService nfceService;

    public NfceController(NfceService nfceService){
        this.nfceService = nfceService;
    }

    @PostMapping("/autorizar")
    public ResponseEntity<NfceResponseDTO> autorizar(@RequestBody NfceRequestDTO request) {
        NfceResponseDTO resp = nfceService.autorizarNfce(request);
        return ResponseEntity.ok(resp);
    }
}
