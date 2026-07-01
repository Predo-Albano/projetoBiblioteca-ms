package com.ifce.frontms.controller;

import com.ifce.frontms.dto.AutorDTO;
import com.ifce.frontms.service.AutorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/autores")
public class AutorController {

    private final AutorService autorService;

    public AutorController(AutorService autorService) {
        this.autorService = autorService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("autores", autorService.listarTodos());
        return "autores-lista";
    }

    @GetMapping("/novo")
    public String formNovo(Model model) {
        model.addAttribute("autor", new AutorDTO());
        return "autores-form";
    }

    @PostMapping
    public String salvar(@ModelAttribute AutorDTO autor) {
        autorService.criar(autor);
        return "redirect:/autores";
    }

    @GetMapping("/{id}/editar")
    public String formEditar(@PathVariable Long id, Model model) {
        model.addAttribute("autor", autorService.buscarPorId(id));
        return "autores-form";
    }

    @PostMapping("/{id}")
    public String atualizar(@PathVariable Long id, @ModelAttribute AutorDTO autor) {
        autorService.atualizar(id, autor);
        return "redirect:/autores";
    }

    @GetMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id) {
        autorService.deletar(id);
        return "redirect:/autores";
    }
}