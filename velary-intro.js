(() => {
  const params = new URLSearchParams(window.location.search);
  if (params.get("entrada") !== "1") return;

  const returnToHome = params.get("destino") === "inicio";
  params.delete("entrada");
  params.delete("destino");
  const remainingQuery = params.toString();
  const cleanUrl = `${window.location.pathname}${remainingQuery ? `?${remainingQuery}` : ""}${window.location.hash}`;
  try {
    window.history.replaceState(null, "", cleanUrl);
  } catch {
    // A animação continua mesmo quando o navegador restringe a URL local.
  }

  const intro = document.getElementById("velary-intro");
  const skipButton = intro?.querySelector(".velary-intro__skip");
  if (!intro || !skipButton) return;

  const prefersReducedMotion = window.matchMedia("(prefers-reduced-motion: reduce)").matches;

  intro.hidden = false;
  if (typeof intro.showModal === "function") intro.showModal();
  else intro.setAttribute("open", "");

  if (prefersReducedMotion) {
    intro.classList.add("is-open", "is-reduced");
  } else {
    window.requestAnimationFrame(() => intro.classList.add("is-open"));
  }

  let finished = false;
  let timer;
  const finish = () => {
    if (finished) return;
    finished = true;
    window.clearTimeout(timer);
    if (returnToHome) {
      window.location.replace("index.html");
      return;
    }
    intro.classList.remove("is-open");
    intro.classList.add("is-leaving");
    window.setTimeout(() => {
      if (typeof intro.close === "function" && intro.open) intro.close();
      intro.hidden = true;
      intro.classList.remove("is-leaving");
    }, 480);
  };

  skipButton.addEventListener("click", finish);
  intro.addEventListener("cancel", event => {
    event.preventDefault();
    finish();
  });
  timer = window.setTimeout(finish, prefersReducedMotion ? 1400 : 3650);
})();
