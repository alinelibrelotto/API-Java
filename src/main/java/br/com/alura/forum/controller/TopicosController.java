package br.com.alura.forum.controller;

import java.util.Arrays;
import java.util.List;

import br.com.alura.forum.controller.dto.TopicoForm;
import br.com.alura.forum.repository.CursoRepository;
import br.com.alura.forum.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import br.com.alura.forum.controller.dto.TopicoDto;
import br.com.alura.forum.modelo.Curso;
import br.com.alura.forum.modelo.Topico;

@RestController //permite definir um controller com características REST;
@RequestMapping(value = "/topicos") //
public class TopicosController {

	@Autowired
	private TopicoRepository topicoRepository;

	@Autowired
	private CursoRepository cursoRepository;
	
	@GetMapping //metodo com get
	public List<TopicoDto> lista(String nomeCurso) {
		if (nomeCurso == null) {
			List<Topico> topicos = topicoRepository.findAll();
			return TopicoDto.converter(topicos);
		} else {
			List<Topico> topicos = topicoRepository.findByCursoNome(nomeCurso);
			return TopicoDto.converter(topicos);
		}
	}

	@PostMapping //metodo post - cadastro
	public void cadastrar (@RequestBody TopicoForm form) { //metodo cadastro dados BD.
		Topico topico = form.converter(cursoRepository);
		topicoRepository.save(topico);

	}


}
