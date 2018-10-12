
Dependencias
------------------------
- fiz toda solucao usando Docker e docker-compose, utilizando linux.
- testei somente na minha maquina (que roda ubuntu), mas acredito que 
  rode em qualquer distribuição linux.
- para rodar, basta instalar docker e docker compose e, entao, usar
  o comando `./build_and_deploy.sh`

Implementacao da parte A
------------------------
- requisito: dados sensiveis e nao precisa ser rapido
- solucao adotada: banco de dados postgres que aceita somente
  conexao segura (ssl). Os certificados sao auto-assinados. Gerei
  eles com openssl.
- desse modo, se alguem fizer sniff na rede, vai ver todos os dados criptografados
- alem disso, como os dados sao muito criticos, estou guardando os dados criptografados
  no banco de dados. desse modo, se um invasor conseguir entrar no banco ou na maquina,
  tambem vai ver os dados criptografados. Para mim, nao ficou claro se esse nível de
  precaução é necessário, mas acho melhor pecar pelo excesso do que pela falta de segurança.
- aplicacao é um spring boot bem simples, e a aplicação é reponsável pela criptografia
- para gerar certificado ssl autoassinado, usei os comandos
```
  openssl req -new -text -out server.req
  openssl rsa -in privkey.pem -out server.key
  rm privkey.pem
  openssl req -x509 -in server.req -text -key server.key -out server.crt
```

- SUGESTOES DE MELHORIA:
  - guardar as chaves e senhas de modo mais seguro com vault (guardei as chaves e senhas
    em claro, devido ao tempo)
  - restringir no pg_hba.conf para que somente ips confiaveis possam acessar o banco. Deixei
    liberado porque, se voces quiserem rodar na maquina de voces, nao vai ser necessario
    trocar o ip
