# Geoly

Projeto acadêmico desenvolvido em colaboração e apresentado em evento de tecnologia. Recebeu **3º lugar**.

O Geoly é um protótipo web para explorar oportunidades de localização comercial em bairros de Ponta Grossa. A interface mostra um mapa interativo e compara setores de negócio com indicadores demonstrativos.

## Tecnologias identificadas no projeto

- HTML, CSS e JavaScript
- Bootstrap
- Leaflet e tiles do OpenStreetMap
- Google Fonts

## Como abrir

Com Python instalado, execute na pasta geoly:

    python -m http.server 8001

Acesse http://localhost:8001/. O protótipo carrega Bootstrap, Leaflet e fontes por CDN e os tiles do mapa do OpenStreetMap; esses recursos precisam de internet.

## Limites do protótipo

A busca de perguntas usa correspondência de palavras-chave e regras definidas no JavaScript; não há integração com modelo de IA generativa. Os números de população por bairro e de concorrentes são ilustrativos, como informado na própria interface. Use o projeto como demonstração acadêmica, não como fonte para decisões comerciais.
