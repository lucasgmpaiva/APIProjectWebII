# APIProjectWebII

# Descrição

Uma API que implementa um repositório de Filmes e Séries desenvolvida em Spring Boot. Nela será possível cadastrar filme/série, pesquisar, editar e deletar.

# Endpoints

1. (POST) /movie - Cadastra um filme - Desenvolvido
2. (GET) /movie/all - Retorna todos os filmes cadastrados - Desenvolvido
3. (GET) /movie/{id} - Retorna filme com base no id - Desenvolvido - Desenvolvido
4. (GET) /movie/search{query} - Retorna filme com base nos filtros da busca (título, gênero e diretor) - Desenvolvido
5. (DELETE) /movie/delete/{id} - Deleta filme
6. (POST) /serie - Cadastra uma serie 
7. (GET) /serie/all - Retorna todos as series cadastradas
8. (GET) /serie/{id} - Retorna serie com base no id
9. (GET) /serie/search{query} - Retorna serie com base nos filtros da busca (título, gênero, emissora)
10. (DELETE) /serie/delete/{id} - Deleta série

# Histórias de Usuário Para os Endpoints

John possui uma grande coleção de filmes e séries. Ele fará uso desta API para registrar os filmes e séries disponíveis em seu catálogo.

1. Para cada filme de seu catálogo, John fará um cadastro na API informando título, diretor, roteirista, data de lançamento e gênero.
2. John deseja verificar todos os filmes que possui em seu catálogo.
3. John deseja conferir um filme específico sabendo seu código no sistema.
4. John deseja pesquisar todos os filmes do Quentin Tarantino que possui em seu catálogo.
5. John percebeu que perdeu um de seus filmes, então ele deve apagar este filme do sistema.
6. Para cada série de seu catálogo, John fará um cadastro na API informando título, emissora, data de lançamento e gênero.
7. John deseja verificar todas as séries que possui em seu catálogo.
8. John deseja verificar uma série específica conhecendo o código dela no sistema.
9. John deseja ver todas as séries da BBC que possui em seu catálogo.
10. John perdeu uma das séries de sua coleção e deseja apagá-la do sistema.

# Exemplo de sistema que faz uso da API:
https://github.com/lucasgmpaiva/ProjetoWebII
