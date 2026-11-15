# Velary

<p align="center">
  <img src="img/logovelary.png" width="420" alt="Logo do Velary">
</p>

Plataforma digital de educação e acompanhamento da saúde feminina, desenvolvida como Projeto Integrador do curso de Análise e Desenvolvimento de Sistemas (ADS) no Senac PR. O projeto reúne um protótipo web e um módulo Java de catálogo educativo; os dois funcionam separadamente.

> **Estado do projeto:** protótipo acadêmico. O site não possui backend nem autenticação real, e o módulo Java ainda não está integrado às páginas web. O conteúdo é educativo e não substitui orientação de profissionais de saúde.

## O que há no repositório

### Site Velary

O site é feito com HTML, CSS e JavaScript. As páginas cobrem temas como primeiro ciclo e menarca, tentantes e fertilidade, gestação, endometriose, SOP, saúde integral, vida sexual, climatério e maturidade. Também há telas de perfil, acompanhamento, atendimento preventivo, artigos, vídeos e navegação por temas.

Algumas telas são fluxos de demonstração ou conceitos em desenvolvimento. Em particular, `assistente.html` está identificado como “em preparação” e `premium.html` apresenta ideias para uma versão premium.

### Catálogo Java

Aplicativo de console com operações de cadastro, consulta, atualização e exclusão para fases de saúde e conteúdos educativos. Usa Java 17, Maven, JDBC e SQLite, com separação em camadas MVC e DAO.

O banco SQLite é criado localmente na primeira execução. O catálogo Java funciona separadamente do site; não há sincronização automática entre o banco e as páginas.

## Protótipo no Figma

As telas e o fluxo visual do projeto estão no [protótipo do Velary no Figma](https://www.figma.com/design/xauKOoD4ZFX3WgJihZxNZX/Velary?node-id=112-736&t=X6f1brm5olJO1IKK-1).

## Como executar o site

Na pasta raiz do repositório, inicie um servidor estático local:

```bash
python -m http.server 8000
```

Depois, acesse `http://localhost:8000/`. Algumas telas usam fontes externas e a tela de perfil de saúde consulta a API pública do IBGE para carregar estados e municípios; esses recursos dependem de conexão com a internet.

## Como executar o catálogo Java

Requisitos: JDK 17 ou superior e Maven.

```bash
cd velary-java
mvn clean compile exec:java
```

O Maven baixa o driver SQLite declarado em `velary-java/pom.xml` na primeira compilação, então essa etapa requer internet. O arquivo `velary.db` é criado no diretório de trabalho da execução e não deve ser enviado ao Git.

No IntelliJ IDEA, abra `velary-java/pom.xml`, selecione um JDK 17 ou superior e execute `br.com.velary.Main`. Para manter o banco dentro da pasta `velary-java`, configure essa pasta como diretório de trabalho da execução.

## Privacidade e limites do protótipo

Algumas interações guardam dados no armazenamento local do navegador (`localStorage` e `sessionStorage`). Essa versão não oferece um prontuário seguro nem foi preparada para armazenar dados reais de saúde. Use somente informações fictícias ao demonstrar o protótipo.

## Estrutura

- `index.html` e outros arquivos HTML: telas e conteúdos do site.
- `css/`: folhas de estilo das páginas.
- `img/`: imagens e elementos visuais.
- `videos/`: materiais audiovisuais usados pelo site.
- `velary-java/`: código-fonte, esquema SQL e configuração Maven do catálogo.
- `geoly/`: protótipo web independente para análise exploratória de localização comercial.

## Geoly

Protótipo web de análise exploratória de localização comercial, com mapa de bairros de Ponta Grossa e comparação de setores. Projeto acadêmico desenvolvido em colaboração e apresentado em evento de tecnologia; recebeu **3º lugar**. A busca usa correspondência de palavras-chave, sem integração com LLM, e os indicadores por bairro são demonstrativos. Veja [o projeto Geoly](geoly/) e suas instruções.

## Contribuições e reconhecimento

O Velary foi desenvolvido em equipe como Projeto Integrador de ADS no Senac PR. Stéfáni Cristina contribuiu com o planejamento de funcionalidades e da estrutura da plataforma, prototipação no Figma, interfaces em HTML e CSS, planejamento da estrutura de dados, pesquisa com o público e exploração de possibilidades de integração com IA.

O projeto recebeu **2º lugar na Semana de Tecnologia do Senac**.

## Uso de inteligência artificial

Durante o desenvolvimento acadêmico, ferramentas de IA generativa foram usadas como apoio à organização de requisitos, à proposta inicial da estrutura MVC/DAO, a exemplos de classes e à documentação. A equipe deve revisar e compreender o material usado no projeto. O site atual não integra um modelo de IA; possíveis integrações foram exploradas como direção de projeto, não como recurso já implementado.
