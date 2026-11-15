(() => {
  const printStyle = document.createElement("style");
  printStyle.textContent = `
    #velary-relatorio-print { display: none !important; }
    @page { margin: 16mm; }
    @media print {
      body.velary-imprimindo-resumo > :not(#velary-relatorio-print) {
        display: none !important;
      }
      body.velary-imprimindo-resumo #velary-relatorio-print {
        display: block !important;
        width: 100%;
        margin: 0;
        padding: 0;
        color: #44333d;
        background: #fff;
        font: 12pt/1.55 Arial, sans-serif;
      }
      #velary-relatorio-print h1 {
        margin: 0 0 8px;
        color: #791a43;
        font: 700 24pt/1.2 Georgia, serif;
      }
      #velary-relatorio-print .relatorio-data {
        margin: 0 0 22px;
        color: #65545e;
      }
      #velary-relatorio-print dl {
        display: grid;
        grid-template-columns: minmax(140px, 1fr) 2fr;
        gap: 0;
        margin: 0;
      }
      #velary-relatorio-print dt,
      #velary-relatorio-print dd {
        margin: 0;
        padding: 10px 8px;
        border-bottom: 1px solid #eadde3;
        overflow-wrap: anywhere;
      }
      #velary-relatorio-print dt { font-weight: 700; }
      #velary-relatorio-print .relatorio-aviso {
        margin-top: 24px;
        padding-top: 12px;
        border-top: 1px solid #eadde3;
        font-size: 10pt;
      }
    }
  `;
  document.head.appendChild(printStyle);

  window.velaryImprimirResumo = (titulo, campos) => {
    document.getElementById("velary-relatorio-print")?.remove();
    const relatorio = document.createElement("section");
    relatorio.id = "velary-relatorio-print";
    relatorio.setAttribute("aria-labelledby", "velary-relatorio-titulo");

    const heading = document.createElement("h1");
    heading.id = "velary-relatorio-titulo";
    heading.textContent = titulo;
    relatorio.appendChild(heading);

    const gerado = document.createElement("p");
    gerado.className = "relatorio-data";
    gerado.textContent = "Resumo pessoal · gerado em " + new Date().toLocaleDateString("pt-BR");
    relatorio.appendChild(gerado);

    const dados = document.createElement("dl");
    campos.forEach(([rotulo, valor]) => {
      const termo = document.createElement("dt");
      termo.textContent = rotulo;
      const definicao = document.createElement("dd");
      definicao.textContent = String(valor || "Não informado");
      dados.append(termo, definicao);
    });
    relatorio.appendChild(dados);

    const aviso = document.createElement("p");
    aviso.className = "relatorio-aviso";
    aviso.textContent = "Resumo montado com as informações digitadas nesta página. Não é laudo, prontuário ou orientação clínica. Confira os dados com a equipe de saúde.";
    relatorio.appendChild(aviso);

    document.body.appendChild(relatorio);
    document.body.classList.add("velary-imprimindo-resumo");
    const limparRelatorio = () => {
      document.body.classList.remove("velary-imprimindo-resumo");
      relatorio.remove();
    };
    window.addEventListener("afterprint", limparRelatorio, { once: true });
    window.print();
  };
})();
