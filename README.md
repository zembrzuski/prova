Olá, pessoal!
------------

- Toda solução foi feita com containers Docker e docker-compose.
- Testei somente na minha máquina (que roda ubuntu), mas acredito que 
  rode em qualquer distribuição linux e macos.
- Para rodar, basta instalar docker e docker-compose e, entao, usar
  o comando `./build_and_deploy.sh`. Esse script irá baixar algumas imagens do dockerhub, construir localmente as imagens das aplicações que escrevi para resolução dessa prova e rodar os containers.

Implementação da parte A
------------------------
- Como o principal requisito dessa parte é a segurança, meus principais cuidados foram:
    - autenticação obrigatória para acesso ao banco de dados
    - segurança no tráfego de dados, habilitando SSL para comunição entre aplicação e banco de dados
    - persistência de dados criptografados (AES)

- Para habilitar SSL na comunicação com banco, foram gerados certificados autoassinados com openssl.
- Aplicação responsável por persistir e recuperar dados foi feita com um container Docker rodando Tomcat feita com Java8. É uma restful api com HATEOAS, implementada com Spring Boot e Spring Data Rest. 

- SUGESTOES DE MELHORIA:
  - guardar as chaves e senhas de modo mais seguro com vault (guardei as chaves e senhas em claro, devido ao tempo)
  - restringir no pg_hba.conf para que somente ips confiaveis possam acessar o banco. Deixei liberado porque, se voces quiserem rodar na maquina de voces, nao vai ser necessario trocar o ip
  - validação dos dados antes de persisti-los.
  - autenticação para os usuários da aplicação
  - fazer testes
 
- Alguns exemplos de como utilizar a aplicação A:
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
