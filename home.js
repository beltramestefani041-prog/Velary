document.addEventListener('DOMContentLoaded', () => {
    // Carrega o nome do usuário salvo
    const boasVindasEl = document.getElementById('boas-vindas');
    const usuarioSalvo = localStorage.getItem('velary_nome');
    if (boasVindasEl) {
        boasVindasEl.textContent = usuarioSalvo ? `Boas-vindas, ${usuarioSalvo}!` : 'Boas-vindas!';
    }

    // Carrega o humor salvo previamente
    const humorSalvo = localStorage.getItem('velary_humor');
    if (humorSalvo) {
        exibirHumorSalvo(humorSalvo);
    }

    // Event Delegation para os botões do diário emocional
    const moodContainer = document.querySelector('.mood-buttons');
    if (moodContainer) {
        moodContainer.addEventListener('click', (event) => {
            const button = event.target.closest('button');
            if (!button) return;

            const nomeHumor = button.getAttribute('data-humor');
            const emoji = button.getAttribute('data-emoji') || '';
            
            if (nomeHumor && emoji) {
                registarHumor(nomeHumor, emoji);
            }
        });
    }
});

function registarHumor(nomeHumor, emoji) {
    const feedback = document.getElementById('feedback-humor');
    const textoHumor = `${emoji} ${nomeHumor}`;
    
    feedback.textContent = `Humor "${nomeHumor}" registrado com sucesso!`;
    localStorage.setItem('velary_humor', textoHumor);
    
    exibirHumorSalvo(textoHumor);
    
    setTimeout(() => {
        feedback.textContent = '';
    }, 3000);
}

function exibirHumorSalvo(textoHumor) {
    const historicoBox = document.getElementById('historico-humor');
    const humorSalvoEl = document.getElementById('humor-salvo');
    
    if (historicoBox && humorSalvoEl) {
        historicoBox.style.display = 'block';
        humorSalvoEl.textContent = textoHumor;
    }
}
