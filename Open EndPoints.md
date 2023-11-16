
#### Microserviço Auth

* /msp-auth/token - <span class="POST">POST</span>

<div class="divDetalhes">
Retorna o token do usuário após enviar um JSON com os atributos: email e password.

<p>
Exemplo:
<div class="divDetalhesCod">
{
    <br>
    "email" : "exemplo@email.com"
    <br>
    "password" : "123456",
    <br>
}
</div>
<p>
Em caso de sucesso a API retorna o texto do token JWT - HttpStatus 200
<p>
Em caso do usuário não estiver cadastrado a API retorna o texto "Invalid access, bad credentials" - HttpStatus 401
<p>
Em caso de outro erro a API retorna o texto "Bad Request" - HttpStatus 400
</div>

* /msp-auth/register/user - <span class="POST">POST</span>

<div class="divDetalhes">
Adiciona um novo usuário que recebe as credencial de User, o usuário deve ser passado no formato de JSON, com os atributos: name, password e email.

<p>
Exemplo:
<div class="divDetalhesCod">
{
	<br>
    "name" : "Exemplo",
    <br>
    "password" : "123456",
    <br>
    "email" : "exemplo@email.com"
    <br>
}
</div>
<p>
Em caso de sucesso a API retorna o texto "User created" - HttpStatus 200
<p>
Em caso do usuário já estiver cadastrado a API retorna o texto "User already exist" - HttpStatus 422
<p>
Em caso de outro erro a API retorna o texto "Error creating user" - HttpStatus 400
</div>

* /msp-auth/validate - <span class="GET">GET</span>

<div class="divDetalhes">
Verificar se o token JWT passado pelo Header é válido.

<p>
Em caso de sucesso a API retorna o texto "Token is valid" - HttpStatus 200
<p>
Em caso do token ser inválido retorna "Invalid token" - HttpStatus 401
<p>
Em caso de outro erro a API retorna o texto "Error" - HttpStatus 400
</div>

### Microserviço Products

* /msp-products - <span class="GET">GET</span>

<div class="divDetalhes">
Retorna todos os produtos do bancos de dados.
</div>

