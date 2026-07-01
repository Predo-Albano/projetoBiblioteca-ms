package com.ifce.frontms.controller;

import com.ifce.frontms.dto.LivroDTO;
import com.ifce.frontms.service.AutorService;
import com.ifce.frontms.service.LivroService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/livros")
public class LivroController {

    private final LivroService livroService;
    private final AutorService autorService;

    public LivroController(LivroService livroService, AutorService autorService) {
        this.livroService = livroService;
        this.autorService = autorService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("livros", livroService.listarTodos());
        return "livros-lista";
    }

    @GetMapping("/novo")
    public String formNovo(Model model) {
        model.addAttribute("livro", new LivroDTO());
        model.addAttribute("autores", autorService.listarTodos());
        return "livros-form";
    }

    @PostMapping
    public String salvar(@ModelAttribute LivroDTO livro) {
        livroService.criar(livro);
        return "redirect:/livros";
    }

    @GetMapping("/{id}/editar")
    public String formEditar(@PathVariable Long id, Model model) {
        model.addAttribute("livro", livroService.buscarPorId(id));
        model.addAttribute("autores", autorService.listarTodos());
        return "livros-form";
    }

    @PostMapping("/{id}")
    public String atualizar(@PathVariable Long id, @ModelAttribute LivroDTO livro) {
        livroService.atualizar(id, livro);
        return "redirect:/livros";
    }

    @GetMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id) {
        livroService.deletar(id);
        return "redirect:/livros";
    }
}