# Projeto-Java-Advanced-FIAP
Repositório destinado ao projeto do 1° semestre da matéria de Java Advanced da faculdade.
A idéia do projeto é ser um sistema de gerenciamento de estoque de material escolar.

## Requisitos

 - [ ] CRUD de categorias
 - [ ] CRUD de Movimentações
 - [ ] CRUD de Usuários
 - [ ] Autenticação
 - [ ] Dashboard

 ## Documentação


 ### Endpoints

- [Listar categorias](#listar-categorias)
- [Cadastrar categoria](#cadastrar-categoria)
- [Detalhes da Categoria](#detalhes-da-categoria)
- [Apagar categoria](#apagar-categoria)
- [Atualizar categoria](#atualizar-categoria)
 - VERBO + PATH

### Listar Categorias
`GET` /categoria

Retorna um array com todas as categorias cadastradas.

#### Exemplo de resposta
```js
[
    {
        "nome": "Cadernos",
        "icone": "notebook"
    },
    {
        "id": 2,
        "nome": "Canetas",
        "icone": "pen"
    }
]
```

#### Código de Status

| código | descrição
|--------|---------
|200 | Categorias retornadas com sucesso
|401 | Usuário não autenticado. Realize autenticação em /login

### Cadastrar Categoria

`POST` /cadastrar

Cadastrar uma nova categoria para o usuário logado com os dados enviados no corpo da requisição.

#### Corpo da Requisição

| campos | tipo | obrigatório | descrição
|--------|------|:-------------:|----------
|nome|string|✅| Um nome curto para a categoria com pelo menos 3 caracteres
|icone|string|❌| O nome do ícone conforme Material Icons

#### Exemplo de Resposta

```js
{
    "nome": "Cadernos",
    "icone": "notebook"
}
```

#### Código de Status

| código | descrição
|--------|---------
|200 | Categorias retornadas com sucesso
|400 | Validação falhou. Verifique as regras para o corpo da requisição.
|401 | Usuário não autenticado. Realize autenticação em /login

### Detalhes da Categoria
`GET` /categoria/`{id}`

Retorna os dados detalhados da categoria com o `id` informado no parametro de path.

### Exemplo de Resposta
```js
//requisição para /categoria/1
{
    "id": 1,
    "nome": "Cadernos",
    "icone": "notebook"
}
```

#### Código de Status

| código | descrição
|--------|----------
|200 | Categorias retornadas com sucesso
|401 | Usuário não autenticado. Realize autenticação em /login
|404 | Não existe categoria com o `id` informado. Consulte lista em /categoria

### Apagar Categoria

`DELETE` /categoria/ `{id}`

Apaga a categoria indicada pelo `id` enviado no parametro de path. 

#### Código de Status

| código | descrição
|--------|----------
|204 | Categoria apagada com sucesso.
|401 | Usuário não autenticado. Realize autenticação em /login
|404 | Não existe categoria com o `id` informado. Consulte lista em /categoria

### Atualizar Categoria

`PUT` /categoria/`{id}`

Atualizar os dados da categoria com o `id` informado no path, utilizando os novos dados enviados no corpo da requisição.

#### Corpo da Requisição

| campos | tipo | obrigatório | descrição
|--------|------|:-------------:|----------
|nome|string|✅| Um nome curto para a categoria com pelo menos 3 caracteres
|icone|string|❌| O nome do ícone conforme Material Icons

```js
{
    "nome": "Cadernos",
    "icone": "notebook"
}
```

#### Código de Status

| código | descrição
|--------|----------
|200 | Categoria atualizada com sucesso.
|400 | Validação falhou. Verifique as regras para o corpo da requisição
|401 | Usuário não autenticado. Realize autenticação em /login
|404 | Não existe categoria com o `id` informado. Consulte lista em /categoria