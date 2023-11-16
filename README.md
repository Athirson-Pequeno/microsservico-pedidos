# Open Endpoints

## Microserviço Auth

<br>
`/msp-auth/token - POST`

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

<br>
### /msp-auth/register/user - <span class="POST">POST</span>

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

<br>
### /msp-auth/validate - <span class="GET">GET</span>

<div class="divDetalhes">
Verificar se o token JWT passado pelo Header é válido.

<p>
Em caso de sucesso a API retorna o texto "Token is valid" - HttpStatus 200
<p>
Em caso do token ser inválido retorna "Invalid token" - HttpStatus 401
<p>
Em caso de outro erro a API retorna o texto "Error" - HttpStatus 400
</div>

<br>
##Microserviço Products
<br>
###/msp-products - <span class="GET">GET</span>

<div class="divDetalhes">
Retorna todos os produtos do bancos de dados.
</div>

#User EndPoints


##Microserviço Orders
<br>
###/msp-orders/new - <span class="POST">POST</span>

<div class="divDetalhes">
Adiciona um novo pedido, os produtos do pedido devem ser passados no formato de lista dentro do JSON, cada item da lista deve conter o id do produto que é encontrado no microserviço de produto e uma quantidade.
<p>
Para ter acesso deve-se passar um Bearer Token com credencias de User pelo cabeçalho da requisição.

<p>
Exemplo:
<div class="divDetalhesCod">
[{
	<br>
    "productID" : 2,
	<br>
    "amount" : 1
	<br>
}]
</div>
<p>
Em caso de sucesso a API retorna o texto "Oder saved" - HttpStatus 200
<p>
Em caso do produto não existir a API retorna o texto "Product not found, error saving order" - HttpStatus 404
<p>
Em caso de outro erro a API retorna o texto "Error" - HttpStatus 500
</div>
<br>
###/msp-orders/orders/update/{IDPedido} - <span class="POST">POST</span>

<div class="divDetalhes">
Atualiza um pedido existente, os produtos do pedido devem ser passados no formato de lista dentro do JSON, cada item da lista deve conter o id do produto que é encontrado no microserviço de produto e um valor para a quantidade. Se o id de um dos produtos passados for igual a um já existe a quantidade dele será atualizada, se for um id que não existe no pedido original o produto será adicionado.
<p>
Para ter acesso deve-se passar um Bearer Token com credencias de User pelo cabeçalho da requisição.

<p>
Exemplo:
<div class="divDetalhesCod">
[{
	<br>
    "productID" : 2,
	<br>
    "amount" : 20
	<br>
},{
	<br>
    "productID" : 1,
	<br>
    "amount" : 10
	<br>
}]
</div>
<p>
	Em caso de sucesso a API retorna o texto "Updated order id = IDPedido" - HttpStatus 200
<p>
Em caso do produto não existir a API retorna o texto "Product not found, error saving order" - HttpStatus 404
<p>
Em caso de outro erro a API retorna o texto "Error" - HttpStatus 500
</div>
<br>
###/msp-orders/orders/clientorders - <span class="POST">POST</span>

<div class="divDetalhes">
Retorna todos os pedidos do cliente baseado no token de autorização JWT.
<p>
Para ter acesso deve-se passar um Bearer Token com credencias de User pelo cabeçalho da requisição.

<p>
	Em caso de sucesso a API retorna uma lista de pedidos - HttpStatus 200
<p>
Em caso de erro a API retorna o texto "Error" - HttpStatus 500
</div>

#Admin EndPoints

<br>
##Microserviço Auth
<br>
###/msp-auth/register/admin - <span class="POST">POST</span>

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

<br>
##Microserviço Products
<br>
###/msp-products/new - <span class="POST">POST</span>

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

<br>
##Microserviço Orders
<br>
###/msp-orders/all - <span class="GET">GET</span>

<div class="divDetalhes">
Retorna todos os pedidos.

<p>
Para ter acesso deve-se passar um Bearer Token com credencias de Admin pelo cabeçalho da requisição.

</div>



