<img src="https://github.com/lucas-moro/JavaCafe-POO-2024/assets/88462534/bcd5e6ec-54e6-4508-88e7-7026c1f5573f" alt="java-logo-1" width="200">

<h2>Java Café</h2>

Java Café é um sistema de gerenciamento de um café, desenvolvido em Java, utilizando a biblioteca Swing para a interface gráfica. O sistema possui duas interfaces principais: uma para clientes realizarem pedidos e outra para gerentes administrarem o estoque e visualizarem relatórios de vendas.

Índice

	Requisitos
	Instalação
	Execução
	Estrutura do Projeto
	Funcionalidades
    	Tela do Cliente
    	Tela do Gerente
	Contribuição
	Licença

<h2>Requisitos</h2>

Para executar o Java Café, você precisará dos seguintes requisitos:

	Java Development Kit (JDK) 8 ou superior
	Uma IDE de sua escolha (recomendado: IntelliJ IDEA, Eclipse)
	Maven (opcional, mas recomendado para gerenciamento de dependências)

<h2>Instalação</h2>

	Clone este repositório para o seu ambiente local:

	bash

	git clone https://github.com/seu-usuario/java-cafe.git
	cd java-cafe

	Abra o projeto na sua IDE favorita.

	Se estiver usando Maven, importe o projeto como um projeto Maven para resolver todas as dependências automaticamente.

<h2>Execução</h2>

Para executar a aplicação Java Café:

	Navegue até a classe Main.java na estrutura do projeto.

	Execute a classe Main como uma aplicação Java.

	A janela principal do Java Café será aberta, permitindo que você escolha entre as interfaces de Cliente e Gerente.

<h2>Estrutura do Projeto</h2>

JavaCafe-POO-2024
└── src
	├── Caminhos.java
	├── Listagem.java
	├── Main.java
	├── Produto.java
	├── Ordem.java
	└── Telas
    	├── TelaCliente.java
    	└── TelaGerente.java

	Caminhos.java: Define os caminhos para os arquivos de inventário e pedidos.
	Listagem.java: Gerencia o inventário dos produtos.
	Main.java: Classe principal que inicializa a aplicação.
	Produto.java: Classe que representa um produto.
	Ordem.java: Classe que representa um pedido.
	Telas/: Contém as classes das interfaces gráficas (TelaCliente e TelaGerente).

<h2>Funcionalidades</h2>
Tela do Cliente:

	Selecionar produtos e quantidades.
	Adicionar produtos ao pedido.
	Finalizar pedido e visualizar o total.
	Mensagem de confirmação de pedido realizado com sucesso.
	Opção para sair da tela do cliente.

Tela do Gerente:

	Visualizar e atualizar o estoque de produtos.
	Adicionar ou atualizar produtos no inventário.
	Excluir produtos do inventário.
	Visualizar relatório de vendas.
	Aviso de estoque baixo para produtos com quantidade menor ou igual a 2.

