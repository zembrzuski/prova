# Olá, pessoal!
------------

- Toda solução foi feita com containers Docker e docker-compose.
- Testei somente na minha máquina (que roda ubuntu), mas acredito que 
  rode em qualquer distribuição linux e macos.
- Para rodar, basta instalar docker e docker-compose e, entao, usar
  o comando `./build_and_deploy.sh`. Esse script irá baixar algumas imagens do dockerhub, construir localmente as imagens das aplicações que escrevi para resolução dessa prova e rodar os containers.

## Parte A
------------------------
- Como o principal requisito dessa parte é a segurança, meus principais cuidados foram:
    - autenticação obrigatória para acesso ao banco de dados
    - segurança no tráfego de dados, habilitando SSL para comunição entre aplicação e banco de dados
    - persistência de dados criptografados (AES)

- Para habilitar SSL na comunicação com banco, foram gerados certificados autoassinados com openssl.
- Aplicação responsável por persistir e recuperar dados foi feita com um container Docker rodando Tomcat feita com Java8. É uma restful api com HATEOAS, implementada com Spring Boot e Spring Data Rest. 

### Sugestões de melhoria
  - guardar as chaves e senhas de modo mais seguro com vault (guardei as chaves e senhas em claro, devido ao tempo)
  - restringir no pg_hba.conf para que somente ips confiaveis possam acessar o banco. Deixei liberado porque, se voces quiserem rodar na maquina de voces, nao vai ser necessario trocar o ip
  - validação dos dados antes de persisti-los.
  - autenticação para os usuários da aplicação
  - fazer testes
 
### Exemplo de como utilizar
- `curl -X GET http://localhost:8090/users`
- `curl -X POST 
  http://localhost:8090/users 
  -H 'Content-Type: application/json' 
  -d '{
	"cpf": "005.117.480-43",
	"name": "rodrigo",
	"address": "bento 555",
	"debts": [
		{"description": "algo", "value": "25.00"},
		{"description": "algo2", "value": "30.00"}
	]
}'`



## Parte C
------------------------

* Como o principal requisito é que tenha um acesso extremamente rápido, utilizei elasticsearch para persistência e recuperação das informações. A aplicação foi feita em python 3. O framework utilizado é tornado, pois ele escala muito bem. Tanto o banco quanto a aplicação estão rodando em containers Docker.
* Como segureança não é um item necessário, os dados são armazenados em claro e a comunicação http é sem criptografia.

### Como utilizar

* essa é uma api que trata jsons, mas não é um rest simples. Ela encapsula algum comportamento. Por exemplo, quando acontece uma inserção de movimentação financeira em um cpf, ela automaticamente já verifica se a compra é com cartão de crédito para que a recuperação dos dados da última compra com cartão de crédito seja muito rápida.
* 
* inserção de uma consulta de cpf num bureau de crédito: `curl -X POST 
  http://localhost:8888/cpf/00511548043 
  -H 'Content-Type: application/json' 
  -d '{
	"datetime": "2015/01/01 12:10:30"
}'`

* recuperação de uma consulta de cpf num bureau de crédito: `curl -X GET http://localhost:8888/cpf/00511548043`

* inserção de um movimento na conta: `curl -X POST 
  http://localhost:8888/finance-movement/00511548043 
  -H 'Content-Type: application/json' 
  -d '{
	"description": "compra de carro",
	"value": 560.37,
	"mode": "credit-card",
	"datetime": "2015/01/01 12:10:30"
}'`

* listagem de toda movimentação financeira de um determinado cpf: `curl -X GET 
  http://localhost:8888/finance-movement/00511548043 `

* dados da última compra com cartão de crédito para um determinado cpf: `curl -X GET 
  http://localhost:8888/last-purchase/00511548043 `

### Sugestões de melhoria da parte C
* Validação dos dados (hoje não existe, e dados inválidos podem ter consequencias ruins)
* Uso de filas para persistência de modo assíncrono e uso da bulk api do elasticsearch para persistência em lotes FAZER UMA ARQUITETURA BACANA AQUI.
* Criação de mais testes de unidade. Fiz alguns, somente, por causa do tempo escasso.