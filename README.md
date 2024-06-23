<p align="center"><img src="https://github.com/lucas-moro/JavaCafe-POO-2024/assets/88462534/bcd5e6ec-54e6-4508-88e7-7026c1f5573f" alt="java-logo-1" width="200"></p>

<h2><p align="center">Java Café</p></h2>

<p align="center">Java Café é um sistema de gerenciamento de um café, desenvolvido em Java, utilizando a biblioteca Swing para a interface gráfica. O sistema possui duas interfaces principais: uma para clientes realizarem pedidos e outra para gerentes administrarem o estoque e visualizarem relatórios de vendas.</p>

<h2>Índice</h2>

	- Requisitos
	- Instalação
	- Execução
	- Funcionalidades
    	- Tela do Cliente
    	- Tela do Gerente
    	- Estrutura do Projeto

<h2>Requisitos</h2>

Para executar o Java Café, você precisará dos seguintes requisitos:

	- Java Development Kit (JDK) 8 ou superior
	- Uma IDE de sua escolha (recomendado: IntelliJ IDEA, Eclipse)

<h2>Instalação</h2>

	Clone este repositório para o seu ambiente local usando o comando:

	git clone [link do repositório online]
 
	cd java-cafe

	Abra o projeto na IDE de sua escolha.

<h2>Execução</h2>

Para executar a aplicação Java Café:

	Dentro da pasta Main, navegue até a classe ClienteMain.java (para abrir interface de cliente) ou GerenteMain.java (para abrir interface de gerente) na estrutura do projeto .

	Execute a classe correspondente como uma aplicação Java.

	A janela do Java Café correspondente a sua escolha será aberta, permitindo que você navegue entre as opções.

 Ao executar a aplicação uma janela se abrirá com a seguinte interface:

![image](https://github.com/lucas-moro/JavaCafe-POO-2024/assets/88462534/c62d4e50-277e-4faf-b354-becbcce4eea6)

<h2>Funcionalidades</h2>
<h3>Tela do Cliente:</h3>

	Selecionar produtos e quantidades.
	Adicionar produtos ao pedido.
	Finalizar pedido e visualizar o total.
	Mensagem de confirmação de pedido realizado com sucesso.
	Opção para sair da tela do cliente.

<h3>Tela do Gerente:</h3>

<p>Entrar com a senha "admin"</p>

![image](https://github.com/lucas-moro/JavaCafe-POO-2024/assets/88462534/eb75d091-c053-4770-b389-ac63356e6731)


	Visualizar e atualizar o estoque de produtos.
	Adicionar ou atualizar produtos no inventário.
	Excluir produtos do inventário.
	Visualizar relatório de vendas.
	Aviso de estoque baixo para produtos com quantidade menor ou igual a 2.

 Interface:
![image](https://github.com/lucas-moro/JavaCafe-POO-2024/assets/88462534/8de60462-bf9b-4b64-b77d-19ead24113c8)

Adicionar/Atualizar um produto:
![image](https://github.com/lucas-moro/JavaCafe-POO-2024/assets/88462534/30eb73c7-08b7-480d-9029-60194a301c2a)


Gerando Relatório de vendas:

Deletando um produto:
![Uploading image.png…]()

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
