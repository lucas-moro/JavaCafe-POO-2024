<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Java Café README</title>
</head>
<body>
    <h1>Java Café</h1>
    <p>Java Café é um sistema de gerenciamento de um café, desenvolvido em Java, utilizando a biblioteca Swing para a interface gráfica. O sistema possui duas interfaces principais: uma para clientes realizarem pedidos e outra para gerentes administrarem o estoque e visualizarem relatórios de vendas.</p>

    <h2>Índice</h2>
    <ul>
        <li><a href="#requisitos">Requisitos</a></li>
        <li><a href="#instalacao">Instalação</a></li>
        <li><a href="#execucao">Execução</a></li>
        <li><a href="#estrutura-do-projeto">Estrutura do Projeto</a></li>
        <li><a href="#funcionalidades">Funcionalidades</a>
            <ul>
                <li><a href="#tela-do-cliente">Tela do Cliente</a></li>
                <li><a href="#tela-do-gerente">Tela do Gerente</a></li>
            </ul>
        </li>
        <li><a href="#contribuicao">Contribuição</a></li>
        <li><a href="#licenca">Licença</a></li>
    </ul>

    <h2 id="requisitos">Requisitos</h2>
    <p>Para executar o Java Café, você precisará dos seguintes requisitos:</p>
    <ul>
        <li>Java Development Kit (JDK) 8 ou superior</li>
        <li>Uma IDE de sua escolha (recomendado: IntelliJ IDEA, Eclipse)</li>
        <li>Maven (opcional, mas recomendado para gerenciamento de dependências)</li>
    </ul>

    <h2 id="instalacao">Instalação</h2>
    <ol>
        <li>Clone este repositório para o seu ambiente local:
            <pre><code>git clone https://github.com/seu-usuario/java-cafe.git
cd java-cafe</code></pre>
        </li>
        <li>Abra o projeto na sua IDE favorita.</li>
        <li>Se estiver usando Maven, importe o projeto como um projeto Maven para resolver todas as dependências automaticamente.</li>
    </ol>

    <h2 id="execucao">Execução</h2>
    <p>Para executar a aplicação Java Café:</p>
    <ol>
        <li>Navegue até a classe <code>Main.java</code> na estrutura do projeto.</li>
        <li>Execute a classe <code>Main</code> como uma aplicação Java.</li>
        <li>A janela principal do Java Café será aberta, permitindo que você escolha entre as interfaces de Cliente e Gerente.</li>
    </ol>

    <h2 id="estrutura-do-projeto">Estrutura do Projeto</h2>
    <pre><code>JavaCafe-POO-2024
└── src
    ├── Caminhos.java
    ├── Listagem.java
    ├── Main.java
    ├── Produto.java
    ├── Ordem.java
    └── Telas
        ├── TelaCliente.java
        └── TelaGerente.java
</code></pre>

    <h2 id="funcionalidades">Funcionalidades</h2>
    <h3 id="tela-do-cliente">Tela do Cliente</h3>
    <ul>
        <li>Selecionar produtos e quantidades.</li>
        <li>Adicionar produtos ao pedido.</li>
        <li>Finalizar pedido e visualizar o total.</li>
        <li>Mensagem de confirmação de pedido realizado com sucesso.</li>
        <li>Opção para sair da tela do cliente.</li>
    </ul>

    <h3 id="tela-do-gerente">Tela do Gerente</h3>
    <ul>
        <li>Visualizar e atualizar o estoque de produtos.</li>
        <li>Adicionar ou atualizar produtos no inventário.</li>
        <li>Excluir produtos do inventário.</li>
        <li>Visualizar relatório de vendas.</li>
        <li>Aviso de estoque baixo para produtos com quantidade menor ou igual a 2.</li>
    </ul>

    <h2 id="contribuicao">Contribuição</h2>
    <p>Se você deseja contribuir com o Java Café, por favor, siga os seguintes passos:</p>
    <ol>
        <li>Fork este repositório.</li>
        <li>Crie uma branch para sua feature ou correção de bug:
            <pre><code>git checkout -b minha-feature</code></pre>
        </li>
        <li>Commit suas alterações:
            <pre><code>git commit -m 'Adiciona minha nova feature'</code></pre>
        </li>
        <li>Envie para o repositório remoto:
            <pre><code>git push origin minha-feature</code></pre>
        </li>
        <li>Abra um Pull Request.</li>
    </ol>

    <h2 id="licenca">Licença</h2>
    <p>Este projeto está licenciado sob a Licença MIT. Consulte o arquivo <code>LICENSE</code> para mais detalhes.</p>
</body>
</html>
