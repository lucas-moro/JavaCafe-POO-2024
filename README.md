<p align="center"><img src="https://github.com/lucas-moro/JavaCafe-POO-2024/assets/88462534/bcd5e6ec-54e6-4508-88e7-7026c1f5573f" alt="java-logo-1" width="200"></p>

<h2><p align="center">Java Café</p></h2>

<p align="justify">Java Café é um sistema de gerenciamento de um café, desenvolvido em Java, utilizando a biblioteca Swing para a interface gráfica. O sistema possui duas interfaces principais: uma para clientes realizarem pedidos e outra para gerentes administrarem o estoque e visualizarem relatórios de vendas.</p>

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

 Ao executar a aplicação de acordo com a classe escolhida, uma janela se abrirá com as seguintes interfaces:

<h2>Funcionalidades</h2>

<h3>Tela do Cliente:</h3>

![image](https://github.com/lucas-moro/JavaCafe-POO-2024/assets/88462534/5d67570c-5c9f-423f-ae92-79dbbc03af12)


![image](https://github.com/lucas-moro/JavaCafe-POO-2024/assets/88462534/ace2e8c5-c763-4344-81f4-c0140010aec8)


	Selecionar produtos e quantidades.
	Adicionar produtos ao pedido.
	Finalizar pedido e visualizar o total.
	Mensagem de confirmação de pedido realizado com sucesso.
	Opção para sair da tela do cliente.

<h3>Tela do Gerente:</h3>

<p>Entrar com a senha "admin"</p>

![image](https://github.com/lucas-moro/JavaCafe-POO-2024/assets/88462534/eb75d091-c053-4770-b389-ac63356e6731)

 <p>Interface:</p>

![image](https://github.com/lucas-moro/JavaCafe-POO-2024/assets/88462534/56b97a8c-2e56-4a31-b8d6-a53166d6f554)


<p>Cadastrar produto:</p>

![image](https://github.com/lucas-moro/JavaCafe-POO-2024/assets/88462534/8c874603-8dd7-4115-bd0a-04f3aa65e5f7)


<p>Atualizar produto:</p>

![image](https://github.com/lucas-moro/JavaCafe-POO-2024/assets/88462534/4cd9fc99-f360-46b9-ab02-3e6fcb0c8477)


Gerando Relatório de vendas:

![image](https://github.com/lucas-moro/JavaCafe-POO-2024/assets/88462534/555c4ea0-292a-4a5a-8808-390635d34d67)


Deletando um produto:

![image](https://github.com/lucas-moro/JavaCafe-POO-2024/assets/88462534/6e608492-bf92-4ad2-bec3-e97c54f4ed6f)

	Visualizar e atualizar o estoque de produtos.
	Adicionar ou atualizar produtos no inventário.
	Excluir produtos do inventário.
	Visualizar relatório de vendas.
	Aviso de estoque baixo para produtos com quantidade menor ou igual a 2.

<h2>Estrutura do Projeto</h2>

![image](https://github.com/lucas-moro/JavaCafe-POO-2024/assets/88462534/251c4891-3843-4005-a267-bae6d9732fca)

	Main:
		ClienteMain.java: Classe que inicializa a aplicação do Cliente.
		GerenteMain.java: Classe que inicializa a aplicação do Gerente.
  
  	Paths:
 		Caminhos.java: Define os caminhos para os arquivos de inventário e pedidos.
   
   	Telas:
 		Listagem.java: Gerencia o inventário dos produtos.
		Ordem.java: Classe que representa um pedido.
 		Produto.java: Classe que representa um produto.
		TelaCliente.java: Contém a classe da interface gráfica TelaCliente.
  		TelaGerente.java: Contém a classe da interface gráfica TelaGerente.
