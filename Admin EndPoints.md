
## Microserviço Auth

* /msp-auth/register/admin - <span class="POST">POST</span>

<div class="divDetalhes">
Adiciona um novo usuário que recebe as credencias de Admin e User, o usuário deve ser passado no formato de JSON, com os atributos: name, password e email.
<p>
Para ter acesso deve-se passar um Bearer Token com credencias de Admin pelo cabeçalho da requisição.

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
Em caso de sucesso a API retorna o texto "Admin created" - HttpStatus 200
<p>
Em caso do usuário já estiver cadastrado a API retorna o texto "Admin already exist" - HttpStatus 422
<p>
Em caso de outro erro a API retorna o texto "Error creating admin" - HttpStatus 400
</div>


## Microserviço Products

* /msp-products/new - <span class="POST">POST</span>

<div class="divDetalhes">
Adiciona um novo produto, o produto deve ser passado no formato de JSON, com os atributos, name, price, description e photoUrl.

<p>
Para ter acesso deve-se passar um Bearer Token com credencias de Admin pelo cabeçalho da requisição.

<p>
Exemplo:
<div class="divDetalhesCod">
{
	<br>
    "name": "Misto quente",
	<br>
    "price": 2,
	<br>
    "description": "Pão de forma com presunto e mussarela",
	<br>
    "photoUrl": "https://img.cybercook.com.br/receitas/538/misto-quente-3-840x480.jpeg?q=75"
	<br>
}
</div>
<p>
Em caso de sucesso a API retorna o texto "Product created" - HttpStatus 200
<p>
Em caso de erro a API retorna o  texto "Error creating product" - HttpStatus 400
</div>


## Microserviço Orders

* /msp-orders/all - <span class="GET">GET</span>

<div class="divDetalhes">
Retorna todos os pedidos.

<p>
Para ter acesso deve-se passar um Bearer Token com credencias de Admin pelo cabeçalho da requisição.

</div>


