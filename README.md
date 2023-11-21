# Open Endpoints

## Microserviço Auth
<code>/msp-auth/token - POST</code>
<p>

Retorna o token do usuário após enviar um JSON com os atributos: email e password.

Exemplo:
```
{
    "email" : "exemplo@email.com",
    "password" : "123456"
}
```

Em caso de sucesso a API retorna o texto do token JWT - HttpStatus 200

Em caso do usuário não estiver cadastrado a API retorna o texto "Invalid access, bad credentials" - HttpStatus 401

Em caso de outro erro a API retorna o texto "Bad Request" - HttpStatus 400
</p>

<hr>
<code>/msp-auth/register/user - POST</code>
<p>
	
Adiciona um novo usuário que recebe as credencial de User, o usuário deve ser passado no formato de JSON, com os atributos: name, password e email.

Exemplo:
```
{
    "name" : "Exemplo",
    "password" : "123456",
    "email" : "exemplo@email.com"   
}
```

Em caso de sucesso a API retorna o texto "User created" - HttpStatus 200

Em caso do usuário já estiver cadastrado a API retorna o texto "User already exist" - HttpStatus 422

Em caso de outro erro a API retorna o texto "Error creating user" - HttpStatus 400
</p>
<hr>
<code>/msp-auth/validate - GET</code>
<p>

Verificar se o token JWT passado pelo Header é válido.

Em caso de sucesso a API retorna o texto "Token is valid" - HttpStatus 200

Em caso do token ser inválido retorna "Invalid token" - HttpStatus 401

Em caso de outro erro a API retorna o texto "Error" - HttpStatus 400
</p>
<hr>

## Microserviço Products
<code>/msp-products - GET</code>
<p> 
	
Retorna todos os produtos do bancos de dados.
</p>
<hr>

# User EndPoints

## Microserviço Orders

<code>/msp-orders/new - POST</code>

<p>
	
Adiciona um novo pedido, os produtos do pedido devem ser passados no formato de lista dentro do JSON, cada item da lista deve conter o id do produto que é encontrado no microserviço de produto e uma quantidade.

Para ter acesso deve-se passar um Bearer Token com credencias de User pelo cabeçalho da requisição.
</p>

Exemplo:
```
[{
    "productID" : 2,
    "amount" : 1
}]
```
Em caso de sucesso a API retorna o texto "Oder saved" - HttpStatus 200

Em caso do produto não existir a API retorna o texto "Product not found, error saving order" - HttpStatus 404
Em caso de outro erro a API retorna o texto "Error" - HttpStatus 500
<hr>
<code>msp-orders/orders/update/{IDPedido} - POST</code>
<p>

Atualiza um pedido existente, os produtos do pedido devem ser passados no formato de lista dentro do JSON, cada item da lista deve conter o id do produto que é encontrado no microserviço de produto e um valor para a quantidade. Se o id de um dos produtos passados for igual a um já existe a quantidade dele será atualizada, se for um id que não existe no pedido original o produto será adicionado.

Para ter acesso deve-se passar um Bearer Token com credencias de User pelo cabeçalho da requisição.

Exemplo:
```
[{
    "productID" : 2,
    "amount" : 20
},{
    "productID" : 1,
    "amount" : 10
}]
```
Em caso de sucesso a API retorna o texto "Updated order id = IDPedido" - HttpStatus 200

Em caso do produto não existir a API retorna o texto "Product not found, error saving order" - HttpStatus 404

Em caso de outro erro a API retorna o texto "Error" - HttpStatus 500

<hr>

<code>/msp-orders/orders/clientorders - POST</code>

<p>
Retorna todos os pedidos do cliente baseado no token de autorização JWT.

Para ter acesso deve-se passar um Bearer Token com credencias de User pelo cabeçalho da requisição.

Em caso de sucesso a API retorna uma lista de pedidos - HttpStatus 200

Em caso de erro a API retorna o texto "Error" - HttpStatus 500
<hr>

# Admin EndPoints


## Microserviço Auth

<code>/msp-auth/register/admin - POST</code>
<p>
Adiciona um novo usuário que recebe as credencias de Admin e User, o usuário deve ser passado no formato de JSON, com os atributos: name, password e email.

Para ter acesso deve-se passar um Bearer Token com credencias de Admin pelo cabeçalho da requisição.

Exemplo:
```
{
    "name" : "Exemplo",
    "password" : "123456",
    "email" : "exemplo@email.com"
}
```

Em caso de sucesso a API retorna o texto "Admin created" - HttpStatus 200

Em caso do usuário já estiver cadastrado a API retorna o texto "Admin already exist" - HttpStatus 422

Em caso de outro erro a API retorna o texto "Error creating admin" - HttpStatus 400
</p>

<hr>

## Microserviço Products

<code>/msp-products/new - POST</code>

Adiciona um novo produto, o produto deve ser passado no formato de JSON, com os atributos, name, price, description e photoUrl.

Para ter acesso deve-se passar um Bearer Token com credencias de Admin pelo cabeçalho da requisição.

Exemplo:
```
{
    "name": "Misto quente",
    "price": 2,
    "description": "Pão de forma com presunto e mussarela",
    "photoUrl": "https://img.cybercook.com.br/receitas/538/misto-quente-3-840x480.jpeg?q=75"
}
```

Em caso de sucesso a API retorna o texto "Product created" - HttpStatus 200

Em caso de erro a API retorna o  texto "Error creating product" - HttpStatus 400
</p>

<hr>

## Microserviço Orders

<code>/msp-orders/all - GET</code>

Retorna todos os pedidos.

Para ter acesso deve-se passar um Bearer Token com credencias de Admin pelo cabeçalho da requisição.


