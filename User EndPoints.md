
#### Microserviço Orders

* /msp-orders/new - <span class="POST">POST</span>

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

* /msp-orders/orders/update/{IDPedido} - <span class="POST">POST</span>

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

* /msp-orders/orders/clientorders - **<span class="POST">POST</span>

<div class="divDetalhes">
Retorna todos os pedidos do cliente baseado no token de autorização JWT.
<p>
Para ter acesso deve-se passar um Bearer Token com credencias de User pelo cabeçalho da requisição.

<p>
	Em caso de sucesso a API retorna uma lista de pedidos - HttpStatus 200
<p>
Em caso de erro a API retorna o texto "Error" - HttpStatus 500
</div>
