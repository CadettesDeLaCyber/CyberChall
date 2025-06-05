const timerElement = document.getElementById("time");
let time = 120;

let interval = setInterval(() => {
    time--;
    const minutes = Math.floor(time / 60);
    const seconds = time % 60;
    timerElement.textContent = `${String(minutes).padStart(2, '0')}:${String(seconds).padStart(2, '0')}`;
    if (time <= 0) {
        clearInterval(interval);
        alert("Temps écoulé ! Le formulaire sera soumis.");
        document.getElementById("quiz-form").submit();
    }
}, 1000);

document.getElementById("quiz-form").addEventListener("submit", function (e) {
    e.preventDefault();

    let score = 0;

    questions.forEach((correct, index) => {
        const questionNumber = index + 1;
        const selected = document.querySelector(`input[name="q${questionNumber}"]:checked`);
        const block = document.querySelectorAll(".question-block")[index];

        // Supprimer message précédent s'il existe
        const oldMessage = block.querySelector('.answer-message');
        if (oldMessage) oldMessage.remove();

        const feedback = document.createElement("p");
        feedback.classList.add("answer-message");

        if (selected) {
            if (selected.value === correct) {
                score++;
                feedback.style.color = "green";
                feedback.textContent = `Bonne réponse ! (${correct})`;
            } else {
                feedback.style.color = "red";
                feedback.textContent = `Mauvaise réponse (${selected.value}). Réponse correcte : ${correct}`;
            }
        } else {
            feedback.style.color = "orange";
            feedback.textContent = `Non répondu. Réponse correcte : ${correct}`;
        }

        block.appendChild(feedback);
    });

    let resultMessage = "";
    if (score >= 8) {
        resultMessage = "🏆 Expert en cybersécurité !";
    } else if (score >= 5) {
        resultMessage = "⚠ Bon niveau, mais encore des points à améliorer.";
    } else {
        resultMessage = "🚨 Attention ! Il est temps de renforcer vos connaissances.";
    }

    const scoreDiv = document.getElementById("score");
    scoreDiv.innerHTML = `<h2>Score final : ${score}/10</h2><p>${resultMessage}</p>`;
    scoreDiv.style.display = "block";

    // Désactiver les radios + cacher le bouton
    document.querySelectorAll("input[type=radio]").forEach(input => input.disabled = true);
    document.getElementById("submit-btn").style.display = "none";
    clearInterval(interval);
});