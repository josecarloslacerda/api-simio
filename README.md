# Desafio Meli - Símios

Olá. Tudo bem? Abaixo segue algumas informações importantes que devem ser levadas em consideração ao avaliar a implemetação

# Acessos - Endpoints e Swagger (Documentação interativa)

A aplicação pode ser acessada pela URL: http://apisimio-env-1.eba-83m5gjbe.us-west-2.elasticbeanstalk.com

* Recurso "/simian": http://apisimio-env-1.eba-83m5gjbe.us-west-2.elasticbeanstalk.com/simian
* Recurso "/stats": http://apisimio-env-1.eba-83m5gjbe.us-west-2.elasticbeanstalk.com/stats
* Swagger: http://apisimio-env-1.eba-83m5gjbe.us-west-2.elasticbeanstalk.com/swagger-ui.html


# Validações

* Não é aceito uma matriz menor que quatro;
* Não é aceito uma matriz que não seja quadrada;
* O DNA só pode conter apenas as letras "A", "T", "C" e "G". Dessa forma, qualquer DNA que contenha letras diferente das listadas será desconsiderado;
* Não é obrigatório que o DNA tenha pelo menos uma letra de cada especificada no ítem supracitado;


# Detalhes de implementação

* Como o desafio não considera que se deve criar um endpoint para retornar os DNAs cadastrados, estes são armazenados no banco de dados em SHA a fim de diminuir o custo de processamento em consultas de comparação para garantir a unicidade;
* A adoção do hash SHA foi feita apenas por questões de performance, não levando em consideração, assim, a segurança da informação encriptada, uma vez que a segurança do DNA armazenado não é objeto deste desafio; 
* Não foi utilizado Regex em algumas pesquisas por questões de performance. Em alguns testes o Regex se mostrou mais lento;
* Foi criado uma classe "Response" ({data:"", erro: ""}) para padronizar o retorno das requisições. Onde o campo "data" contém o valor do objeto de retorno e campo "erro" contém mensagem de exceção quando houver;
* No recurso "/stats" não foi utilizado a classe "Response" no retorno um vez que a documentação do desafio explicita o retorno deste endpoint;
* Não foi utilizado Lombok;
* Com relação a proporção entre Símio e Humano, enquanto houver apenas um tipo de DNA cadastrado, o campo "ratio" sempre será "0.0"