(() => {
  const toggle = document.getElementById("guia-velary-toggle");
  const panel = document.getElementById("guia-velary-painel");
  const close = document.querySelector(".guia-velary__fechar");
  const panelHeading = document.getElementById("guia-velary-titulo");
  const areaPrompt = document.getElementById("guia-velary-pergunta");
  const areas = document.getElementById("guia-velary-areas");
  const steps = document.getElementById("guia-velary-passos");
  const title = document.getElementById("guia-velary-area-titulo");
  const description = document.getElementById("guia-velary-area-descricao");
  const miniTitle = document.getElementById("guia-velary-mini-titulo");
  const miniSummary = document.getElementById("guia-velary-mini-resumo");
  const miniCardOne = document.getElementById("guia-velary-mini-card-1");
  const miniCardTwo = document.getElementById("guia-velary-mini-card-2");
  const miniLabel = document.getElementById("guia-velary-mini-etiqueta");
  const slideCount = document.getElementById("guia-velary-slide-contador");
  const slideTitle = document.getElementById("guia-velary-slide-titulo");
  const miniScreen = document.querySelector(".guia-velary__mini-tela");
  const slideInfo = document.querySelector(".guia-velary__slide-info");
  const previous = document.getElementById("guia-velary-anterior");
  const next = document.getElementById("guia-velary-proxima");
  const slideDots = Array.from(document.querySelectorAll("[data-guia-slide]"));
  const link = document.getElementById("guia-velary-link");
  const back = document.querySelector(".guia-velary__voltar");

  if (!toggle || !panel || !panelHeading || !areaPrompt || !areas || !steps || !title || !description || !miniTitle || !miniSummary || !miniCardOne || !miniCardTwo || !miniLabel || !slideCount || !slideTitle || !miniScreen || !slideInfo || !previous || !next || slideDots.length !== 3 || !link) return;

  const guias = {
    "primeiro-ciclo": {
      nome: "Primeira menstruação",
      href: "primeiro-ciclo.html",
      slides: [
        {titulo:"Encontre seu tema",texto:"No menu Temas, escolha Primeiro ciclo e menarca para abrir essa área.",etiqueta:"Navegação",miniTitulo:"Temas para você",miniResumo:"Escolha a fase que quer conhecer",cartoes:["Primeiro ciclo","Ver temas"]},
        {titulo:"Explore as seções",texto:"Leia sobre o ciclo e as mudanças do corpo. A página também reúne áreas de observação e consulta.",etiqueta:"Sua área",miniTitulo:"Primeiro ciclo e menarca",miniResumo:"Informação e recursos desta fase",cartoes:["Meu Ciclo Menstrual","Observações & Sintomas"]},
        {titulo:"Continue no seu ritmo",texto:"Use o menu para voltar a Temas ou abra agora a página de Primeiro ciclo e menarca.",etiqueta:"Próximo passo",miniTitulo:"Navegue pelo Velary",miniResumo:"Você pode voltar aos temas quando quiser",cartoes:["Menu Temas","Página inicial"]}
      ]
    },
    tentantes: {
      nome: "Tentantes e fertilidade",
      href: "tentantes.html",
      slides: [
        {titulo:"Encontre seu tema",texto:"No menu Temas, escolha Tentantes e fertilidade para abrir essa área.",etiqueta:"Navegação",miniTitulo:"Temas para você",miniResumo:"Escolha a fase que quer conhecer",cartoes:["Tentantes e fertilidade","Ver temas"]},
        {titulo:"Explore as seções",texto:"Veja os conteúdos sobre fertilidade e planejamento. A página também traz espaços para organizar dúvidas.",etiqueta:"Sua área",miniTitulo:"Tentantes e fertilidade",miniResumo:"Informação e recursos desta fase",cartoes:["Fases da fertilidade","Anotações para consulta"]},
        {titulo:"Continue no seu ritmo",texto:"Use o menu para voltar a Temas ou abra agora a página de Tentantes e fertilidade.",etiqueta:"Próximo passo",miniTitulo:"Navegue pelo Velary",miniResumo:"Você pode voltar aos temas quando quiser",cartoes:["Menu Temas","Página inicial"]}
      ]
    },
    gestacao: {
      nome: "Gestação",
      href: "gestacao.html",
      slides: [
        {titulo:"Encontre seu tema",texto:"No menu Temas, escolha Gestação para abrir essa área.",etiqueta:"Navegação",miniTitulo:"Temas para você",miniResumo:"Escolha a fase que quer conhecer",cartoes:["Gestação","Ver temas"]},
        {titulo:"Explore as seções",texto:"Use o diário e o mapeador para conhecer os recursos demonstrativos desta página.",etiqueta:"Sua área",miniTitulo:"Gestação",miniResumo:"Informação e recursos desta fase",cartoes:["Diário gestacional","Mapeador de sintomas"]},
        {titulo:"Continue no seu ritmo",texto:"Use o menu para voltar a Temas ou abra agora a página de Gestação.",etiqueta:"Próximo passo",miniTitulo:"Navegue pelo Velary",miniResumo:"Você pode voltar aos temas quando quiser",cartoes:["Menu Temas","Página inicial"]}
      ]
    },
    "saude-integral": {
      nome: "Saúde e autocuidado",
      href: "saude-integral.html",
      slides: [
        {titulo:"Encontre seu tema",texto:"No menu Temas, escolha Saúde integral para abrir essa área.",etiqueta:"Navegação",miniTitulo:"Temas para você",miniResumo:"Escolha o assunto que quer conhecer",cartoes:["Saúde integral","Ver temas"]},
        {titulo:"Explore as seções",texto:"Leia sobre planejamento reprodutivo, observação e autocuidado nesta página.",etiqueta:"Sua área",miniTitulo:"Saúde integral",miniResumo:"Informação e recursos para o dia a dia",cartoes:["Planejamento reprodutivo","Observação e autocuidado"]},
        {titulo:"Continue no seu ritmo",texto:"Use o menu para voltar a Temas ou abra agora a página de Saúde integral.",etiqueta:"Próximo passo",miniTitulo:"Navegue pelo Velary",miniResumo:"Você pode voltar aos temas quando quiser",cartoes:["Menu Temas","Página inicial"]}
      ]
    },
    menopausa: {
      nome: "Climatério e menopausa",
      href: "menopausa.html",
      slides: [
        {titulo:"Encontre seu tema",texto:"No menu Temas, escolha Climatério e menopausa para abrir essa área.",etiqueta:"Navegação",miniTitulo:"Temas para você",miniResumo:"Escolha a fase que quer conhecer",cartoes:["Climatério e menopausa","Ver temas"]},
        {titulo:"Explore as seções",texto:"Veja as informações da página e use o registro para anotar como está se sentindo.",etiqueta:"Sua área",miniTitulo:"Climatério e menopausa",miniResumo:"Informação e recursos desta fase",cartoes:["Meu registro de hoje","Registro guardado"]},
        {titulo:"Continue no seu ritmo",texto:"Use o menu para voltar a Temas ou abra agora a página de Climatério e menopausa.",etiqueta:"Próximo passo",miniTitulo:"Navegue pelo Velary",miniResumo:"Você pode voltar aos temas quando quiser",cartoes:["Menu Temas","Página inicial"]}
      ]
    },
    maturidade: {
      nome: "Maturidade",
      href: "maturidade.html",
      slides: [
        {titulo:"Encontre seu tema",texto:"No menu Temas, escolha Maturidade para abrir essa área.",etiqueta:"Navegação",miniTitulo:"Temas para você",miniResumo:"Escolha a fase que quer conhecer",cartoes:["Maturidade","Ver temas"]},
        {titulo:"Explore as seções",texto:"Conheça as informações sobre saúde, bem-estar e autocuidado desta fase.",etiqueta:"Sua área",miniTitulo:"Maturidade",miniResumo:"Saúde e bem-estar ao longo da vida",cartoes:["Informação para suas escolhas","Autocuidado"]},
        {titulo:"Continue no seu ritmo",texto:"Use o menu para voltar a Temas ou abra agora a página de Maturidade.",etiqueta:"Próximo passo",miniTitulo:"Navegue pelo Velary",miniResumo:"Você pode voltar aos temas quando quiser",cartoes:["Menu Temas","Página inicial"]}
      ]
    }
  };

  let areaAtual = null;
  let telaAtual = 0;

  function renderizarTela() {
    const guia = guias[areaAtual];
    if (!guia) return;
    const tela = guia.slides[telaAtual];
    slideCount.textContent = `Tela ${telaAtual + 1} de ${guia.slides.length}`;
    slideTitle.textContent = tela.titulo;
    description.textContent = tela.texto;
    miniLabel.textContent = tela.etiqueta;
    miniTitle.textContent = tela.miniTitulo;
    miniSummary.textContent = tela.miniResumo;
    miniCardOne.textContent = tela.cartoes[0];
    miniCardTwo.textContent = tela.cartoes[1];
    previous.disabled = telaAtual === 0;
    next.disabled = telaAtual === guia.slides.length - 1;
    slideDots.forEach((dot, index) => {
      if (index === telaAtual) dot.setAttribute("aria-current", "step");
      else dot.removeAttribute("aria-current");
    });
  }

  function setOpen(open) {
    panel.hidden = !open;
    toggle.setAttribute("aria-expanded", String(open));
    if (!open) toggle.focus();
  }

  function showAreas() {
    areaAtual = null;
    telaAtual = 0;
    panelHeading.textContent = "Por onde você quer começar?";
    areaPrompt.hidden = false;
    areas.hidden = false;
    steps.hidden = true;
    areas.querySelector("[data-guia-area]")?.focus();
  }

  function animateSlide(direction) {
    const directionClass = direction === "next" ? "is-next" : "is-previous";
    [miniScreen, slideInfo].forEach(element => {
      element.classList.remove("is-next", "is-previous");
      void element.offsetWidth;
      element.classList.add(directionClass);
    });
  }

  function goToSlide(index) {
    const guia = guias[areaAtual];
    if (!guia || index < 0 || index >= guia.slides.length) return;
    if (index === telaAtual) return;
    const direction = index > telaAtual ? "next" : "previous";
    telaAtual = index;
    renderizarTela();
    animateSlide(direction);
  }

  toggle.addEventListener("click", () => setOpen(panel.hidden));
  close?.addEventListener("click", () => setOpen(false));
  back?.addEventListener("click", showAreas);

  areas.addEventListener("click", event => {
    const button = event.target.closest("[data-guia-area]");
    if (!button) return;
    const guia = guias[button.dataset.guiaArea];
    if (!guia) return;

    areaAtual = button.dataset.guiaArea;
    telaAtual = 0;
    panelHeading.textContent = "Mini tutorial";
    areaPrompt.hidden = true;
    title.textContent = guia.nome;
    renderizarTela();
    link.href = guia.href;
    link.textContent = `Abrir ${guia.nome}`;
    areas.hidden = true;
    steps.hidden = false;
    back?.focus();
  });

  previous.addEventListener("click", () => {
    goToSlide(telaAtual - 1);
  });

  next.addEventListener("click", () => {
    goToSlide(telaAtual + 1);
  });

  slideDots.forEach(dot => {
    dot.addEventListener("click", () => {
      goToSlide(Number(dot.dataset.guiaSlide));
    });
  });

  let inicioDoDeslize = null;
  miniScreen.addEventListener("touchstart", event => {
    inicioDoDeslize = event.changedTouches[0]?.clientX ?? null;
  }, {passive:true});
  miniScreen.addEventListener("touchend", event => {
    if (inicioDoDeslize === null) return;
    const fimDoDeslize = event.changedTouches[0]?.clientX;
    if (fimDoDeslize === undefined) {
      inicioDoDeslize = null;
      return;
    }
    const distancia = inicioDoDeslize - fimDoDeslize;
    if (distancia > 45) goToSlide(telaAtual + 1);
    else if (distancia < -45) goToSlide(telaAtual - 1);
    inicioDoDeslize = null;
  }, {passive:true});

  panel.addEventListener("keydown", event => {
    if (event.key === "ArrowRight") {
      event.preventDefault();
      goToSlide(telaAtual + 1);
    } else if (event.key === "ArrowLeft") {
      event.preventDefault();
      goToSlide(telaAtual - 1);
    }
  });

  document.addEventListener("keydown", event => {
    if (event.key === "Escape" && !panel.hidden) setOpen(false);
  });
})();
