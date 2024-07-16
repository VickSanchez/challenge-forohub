package com.vicksanchez.forohub.controllers;

import com.vicksanchez.forohub.domain.topico.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @GetMapping
    public ResponseEntity<Page<DatosListadoTopico>> listarTopicos(@PageableDefault(page = 0, size = 10) Pageable paginacion ){
        return ResponseEntity.ok(topicoRepository.findByEstatusTrue(paginacion).map(DatosListadoTopico::new));

    }

    @PostMapping
    @Transactional
    public ResponseEntity<DatosRespuestaTopico> registrarTopico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico, UriComponentsBuilder uriComponentsBuilder){
        Topico newTopico = new Topico(datosRegistroTopico);
        if (topicoRepository.findByTituloAndMensaje(newTopico.getTitulo(), newTopico.getMensaje()).isPresent()){
            throw new IllegalArgumentException("Registro Duplicado");
        }
        topicoRepository.save(newTopico);
        DatosRespuestaTopico datosRespuestaTopico = new DatosRespuestaTopico(newTopico.getId(), newTopico.getTitulo(), newTopico.getMensaje(), newTopico.getFechaDeCreacion(), newTopico.getEstatus(), newTopico.getAutor(), newTopico.getCurso());
        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(newTopico.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaTopico);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaTopico> listarTopicos(@PathVariable Long id ){
        Topico topico = topicoRepository.getReferenceById(id);
        return ResponseEntity.ok(new DatosRespuestaTopico(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFechaDeCreacion(), topico.getEstatus(), topico.getAutor(), topico.getCurso()));

    }

    @PutMapping
    @Transactional
    public ResponseEntity<DatosRespuestaTopico> actualizarTopico(@RequestBody @Valid DatosActualizarTopico datosActualizarTopico){
        Topico topico = topicoRepository.getReferenceById(datosActualizarTopico.id());
        topico.actualizarTopico(datosActualizarTopico);
        return ResponseEntity.ok(new DatosRespuestaTopico(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFechaDeCreacion(), topico.getEstatus(), topico.getAutor(), topico.getCurso()));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarTopico(@PathVariable Long id){
        topicoRepository.deleteById(id);
        return  ResponseEntity.noContent().build();
    }

}
