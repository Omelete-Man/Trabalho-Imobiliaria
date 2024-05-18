# 🧑🏼‍💻Descrição do Trabalho-Imobiliaria 🧑🏼‍💻

Desenvolver um programa em java que permita o controle dos imóveis disponíveis em uma imobiliária que trabalha exclusivamente com vendas. A imobiliária deve possuir uma lista de imóveis, sendo que cada imóvel possui as seguintes características: Código, Área construída, Área total, Número de quartos, Tipo (Casa ou Apartamento), Preço, e Endereço (Cidade e Bairro). O programa deve permitir que o usuário realize diversas operações sobre a lista de imóveis da imobiliária até o momento em que ele deseje sair do sistema (última opção do menu oferecido). As diferentes funcionalidades do sistema e os pesos na pontuação final do trabalho estão descritas na tabela abaixo:

1- Cadastro de um novo imóvel. Nesse cadastro, o usuário deve fornecer todas as informações referentes ao novo imóvel que está entrando na imobiliária.

2- Listagem de todos os imóveis

3- Listagem dos imóveis que atende uma determinada condição estabelecida pelo usuário:

    - Tipo do imóvel (Casa ou Apartamento)
    
    - Disponíveis em uma determinada cidade (o programa deve oferecer para o usuário as cidades existentes)

    - Disponíveis em um determinado bairro de uma determinada cidade (o programa deve oferecer para o usuário 
    os bairros disponíveis e suas respectivas cidades para escolha).

    - Pertencentes a uma determinada faixa de preço (as faixa de preços podem ser fixas dentro do programa – 
    ex: de 0 a R$100.000,00 – de R$100.000,01 a R$ 200.000,00 – acima de 200.000,01)

    - Número mínimo de quartos desejado
    
4- Exclusão de Imóveis. Permitir que o usuário exclua um determinado imóvel a partir de seu código.

5- Alteração de Imóveis. Permitir que o usuário altere um determinado imóvel a partir de seu código.

# Sobre as classes:

Classe Imobiliária:

Atributo: 
lista_de_imoveis (Imovel)

Classe Endereco:

Atributos: 
cidade (String), Bairro (String)

Classe Imovel:

Atributos: 
codigo (int), areaconstruida (float), areatotal(float), numeroquartos (int),
tipo (int - Casa, Apartamento - ex: 0 – Casa, 1 – Apartamento)
preco (float)
localizacao (Endereco)

# 🎯Obs:

Para cada classe, implementar os atributos private e os métodos set e get para esses atributos. Para facilitar a implementação do trabalho, algumas características das Classes estão sendo omitidas. Por exemplo, a Classe Endereço não possui informações de Estado, Rua, ou Número do imóvel.
