# Solução proposta
------------------------
* Solução baseada em containers docker, orquestrados com docker-compose. Tanto aplicações quanto bases de dados são containerizadas.
* Deploy de toda solução em uma instância EC2 da AWS (18.228.30.214). A máquina foi provisionada com ansible e o deploy foi realizado com shell-script. Todo o código, tanto de provisionamento quanto para deploy se encontra nesse repositório.
* Para as bases de dados (PostgreSQL e Elasticsearch), seria ideal que se utilizassem os serviços da AWS (RDS e Amazon Elasticsearch Service, respectivamente), mas para simplificação, devido ao fato de que o tempo para realização da prova é curto, provisionei todos componentes na mesma máquina.
* Para rodar todo o sistema localmente, basta ter docker e docker-compose instalados na máquina e rodar o comando `./build-and-deploy-all.sh`. O comando foi testado por mim em um ubuntu, mas acredito que rode em qualquer ambiente linux ou macos.

![alt text](arquitetura.png)

# Parte A
------------------------
* Solução feita com Java 8, Spring Boot, Spring Data Rest. Roda com Tomcat, em um container docker. Base de dados é PostgreSQL.
* Como o requisito principal é de segurança, foram implementadas as seguintes features:
    * Autenticação com usuário e senha na base de dados
    * Comunicação segura com SSL
    * Criptografia AES 256, realizada na aplicação
* A base de dados utilizada é o PostgreSQL, pois é simples, eficiente e seguro.
* Aqui, se algum administrador de banco de dados olhar a base de dados, vai ver os dados criptografados. Aléḿ disso, se alguém fizer sniff da rede, não irá conseguir ver nem os comandos executados, nem os dados.
* Sugestões de melhoria: 
    * Colocar autenticação na aplicação também. Faltou tempo para implementação, mas essa feature pode ser implementada com OAuth2 e OpenId
    * Utilizar RDS da Amazon.

#### Como utilizar a parte A 
* Listagem da primeira página de usuários `curl -X GET http://ec2-18-228-30-214.sa-east-1.compute.amazonaws.com:8090/users `

* Listagem da segunda página de usuários `curl -X GET 'http://ec2-18-228-30-214.sa-east-1.compute.amazonaws.com:8090/users?size=2&page=1' `

* Exemplo de inserção de dados na base
`curl -X POST 
  http://ec2-18-228-30-214.sa-east-1.compute.amazonaws.com:8090/users 
  -H 'Content-Type: application/json' 
  -d '{
	"cpf": "00511748043",
	"name": "rodrigo",
	"address": "bento 555",
	"debts": [
		{"description": "algo", "value": "25.00"},
		{"description": "algo2", "value": "30.00"}
	]
}'`


# Parte B
------------------------
* Solução feita com Java 8, Spring Boot, Spring MVC. Roda com Tomcat, em um container docker. Base de dados é PostgreSQL.
* Para atingir o requisito de segurança, foi utilizado:
    * SSL para conexão segura
    * Autenticação de usuários para a base de dados
* Aqui, os dados não são criptografados na base, pois não são tão sensíveis quanto os da base A. 
* Os dados são disponibilizados em dois formatos:
    * json: ideal para serem consumidos pelo front-end
    * csv: ideal para algoritmos de aprendizagem, pois são mais compactados que json e também são naturais para serem lidos por bibliotecas de machine learning.
* Sugestões de melhoria:
    * RDS da Amazon
    * Colocar autenticação na aplicação também.

#### Como utilizar a parte B

* Listagem da primeira página de usuários (JSON): `curl -X GET 'http://ec2-18-228-30-214.sa-east-1.compute.amazonaws.com:8091/score?type=json'`
* Listagem da primeira página de usuários (CSV): `curl -X GET 'http://ec2-18-228-30-214.sa-east-1.compute.amazonaws.com:8091/score?type=csv'`
* Listagem da segunda página de usuários (JSON): `curl -X GET 'http://ec2-18-228-30-214.sa-east-1.compute.amazonaws.com:8091/score?type=json&page=1&size=2'`
* Listagem da segunda página de usuários (CSV): `curl -X GET 'http://ec2-18-228-30-214.sa-east-1.compute.amazonaws.com:8091/score?type=csv&page=1&size=2'`
* Exemplo de chamada com dados expostos por CSV e filtrados por zipcode: `curl -X GET 'http://ec2-18-228-30-214.sa-east-1.compute.amazonaws.com:8091/score/zipcode?type=csv&zipcode=123'`

# Parte C
------------------------
* Como o principal requisito é performance, foi implementado com ElasticSearch 6 e aplicação feita com Python 3 e Tornado, pois performa bem com chamadas não-bloqueantes. Roda em um containers docker. 
* Como os dados não são sensíveis, os dados são armazenados em claro, sem conexão segura e sem autenticação.
* Sugestões de melhoria:
    * Para rápida inserção de dados, pode-se utilizar a bulk api do elasticsearch, que faz inserção em lotes. 
    * Inserção de dados de forma assíncrona pode evitar gargalos. Para isso, uma solução com Apache Kafka ficaria ótima.

#### Como utilizar a parte C
* Dados de um determinado do usuário cujo CPF é 1111111111: `curl -X GET http://ec2-18-228-30-214.sa-east-1.compute.amazonaws.com:8888/cpf/11111111111/`
* Movimentações financeiras do usuário cujo CPF é 1111111111: `curl -X GET http://ec2-18-228-30-214.sa-east-1.compute.amazonaws.com:8888/finance-movement/11111111111/ `
* Última compra do usuário cujo CPF é 1111111111: `curl -X GET http://ec2-18-228-30-214.sa-east-1.compute.amazonaws.com:8888/last-purchase/11111111111/ `
* Inserção de uma consulta de cpf num bureau de crédito: `curl -X POST 
  http://ec2-18-228-30-214.sa-east-1.compute.amazonaws.com:8888/cpf/11111111111 
  -H 'Content-Type: application/json' 
  -d '{
	"datetime": "2015/01/01 12:10:30"
}'`
* inserção de um movimento na conta: `curl -X POST 
  http://ec2-18-228-30-214.sa-east-1.compute.amazonaws.com:8888/finance-movement/11111111111 
  -H 'Content-Type: application/json' 
  -d '{
	"description": "compra de carro",
	"value": 560.37,
	"mode": "credit-card",
	"datetime": "2015/01/01 12:10:30"
}'`

