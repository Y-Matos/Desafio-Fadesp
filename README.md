# Desafio-Fadesp

**Tabela de Conteúdo:**

- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Configuração do Ambiente de Desenvolvimento](#configuração-do-ambiente-de-desenvolvimento)
- [Como Iniciar o Projeto](#como-iniciar-o-projeto)
- [Endpoints da API](#endpoints-da-api)
- [Exemplos de Uso](#exemplos-de-uso)

**Tecnologias Utilizadas:**

- Java 17
- Spring Boot 3
- Hibernate
- Maven
- Swagger-ui
- H2 Database

**Configuração do Ambiente de Desenvolvimento:**

Nesse projeto está sendo utilizada o banco de dados H2 embutido no Spring Boot, então não são necessária maiores configurações. 

**Como Iniciar o Projeto:**

Para iniciar o projeto localmente, execute os seguintes comandos:

```console
mvn clean install
mvn spring-boot:run
```

Ao utilizar uma IDE o projeto deve ser configurado automaticamente.

**Endpoints da API:**

- `GET /pagamentos`: Retorna a lista de todos os pagamentos.
- `GET /pagamentos/codigoPagamento/{code}`: Retorna os detalhes de um pagamento específico com base no código do mesmo.
- `GET /pagamentos/documento/{documento}`: Retorna a lista de pagamentos com base no documento passado (CPF/CNPJ).
- `GET /pagamentos/status/{status}`: Retorna a lista de pagamentos com base no status.
> Valores Aceitos: processamento_pendente, processado_sucesso, processado_falha
- `POST /pagamentos`: Cadastra um novo pagamento se não existir um pagamento com o mesmo código.
```console
// Estrutura JSON Padrão
{
	"codigoPagamento" : 12345,
	"documento" : "01234567898",
	"metodoPagamento" : "cartao_debito",
	"numeroCartao" : "18818181819",
	"valorPagamento" : 110.50
}
```
- `PUT /pagamentos`: Atualiza o status de um pagamento com base no código.
```console
// Estrutura JSON Padrão
{
	"codigoPagamento" : 12345,
	"novoStatus" : "processado_falha"
}
// Valores Aceitos: processamento_pendente, processado_sucesso, processado_falha
// Quando o Status é processamento_pendente, ele pode ser alterado para processado_sucesso ou processado_falha.
// Quando o Status é processado_sucesso, ele não pode ter seu status alterado.
// Quando o Status é processado_falha, ele só pode ter seu status alterado para processamento_pendente.
```
- `DELETE /pagamentos/codigoPagamento/{code}`: Deleta um pagamento com base no código, desde que o status seja "process_pending".

**Exemplos de Uso:**

Na pasta "RequestsTeste" está disponível um JSON para ser importado no Insomnia para facilitar nos testes da API.

Além disso, o projeto está configurado para funcionar com o Swagger-ui, que pode ser acessado pelo navegador no endereço padrão:
http://localhost:8080/swagger-ui/index.html