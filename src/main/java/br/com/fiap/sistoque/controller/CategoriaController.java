package br.com.fiap.sistoque.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CategoriaController {

    @RequestMapping(method = RequestMethod.GET, path = "/categoria", produces = "application/json")
    @ResponseBody
    public String index() {
        return """

                [
                    {
                        "nome": "Alimentação",
                        "icone": "fast-food"
                    },
                    {
                        "id": 2,
                        "nome": "Educação",
                        "icone": "book"
                    }
                ]
                """;
    }
}


/*
 * static -> arquivos entregaveis pro cliente (imagem, javascript)
template -> vamos processar e mandar pro cliente (telas)

@RequestMapping -> requisição da página vai ser mapeada para esse método

@ResponseBody -> retorna resposta no corpo

@Controller -> em todas as controllers

CTRL + SHIFT + P -> ADD STARTERS -> 

devTools -> dependencia para salvar e ja recarregar a página

produces = "application/json" -> tipo de resposta é json, tipo de resposta
 */