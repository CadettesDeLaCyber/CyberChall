// Slider déjà existant
function updateArrowVisibility(slider, leftArrow, rightArrow) {
    if (slider.scrollLeft <= 0) {
        leftArrow.style.visibility = 'hidden';
    } else {
        leftArrow.style.visibility = 'visible';
    }

    if (slider.scrollLeft + slider.offsetWidth >= slider.scrollWidth - 1) {
        rightArrow.style.visibility = 'hidden';
    } else {
        rightArrow.style.visibility = 'visible';
    }
}

document.querySelectorAll('.card-module').forEach(module => {
    const slider = module.querySelector('.sous-module-slider');
    const leftArrow = module.querySelector('.arrow-left');
    const rightArrow = module.querySelector('.arrow-right');

    if (!slider || !leftArrow || !rightArrow) return;

    updateArrowVisibility(slider, leftArrow, rightArrow);

    slider.addEventListener('scroll', () => {
        updateArrowVisibility(slider, leftArrow, rightArrow);
    });

    [leftArrow, rightArrow].forEach(button => {
        button.addEventListener('click', () => {
            const direction = button.classList.contains('arrow-left') ? -1 : 1;
            const scrollAmount = 300;

            slider.scrollBy({
                left: direction * scrollAmount,
                behavior: 'smooth'
            });

            setTimeout(() => {
                updateArrowVisibility(slider, leftArrow, rightArrow);
            }, 300);
        });
    });
});

// Mode sombre
document.addEventListener('DOMContentLoaded', function () {
    const toggle = document.getElementById('themeToggle');
    const body = document.body;

    // Appliquer le thème sauvegardé
    if (localStorage.getItem('theme') === 'dark') {
        body.classList.add('dark-mode');
    }

    if (toggle) {
        toggle.addEventListener('click', function () {
            body.classList.toggle('dark-mode');

            if (body.classList.contains('dark-mode')) {
                localStorage.setItem('theme', 'dark');
            } else {
                localStorage.setItem('theme', 'light');
            }
        });
    }
});

document.addEventListener("DOMContentLoaded", function () {
	const title = "Bienvenue sur CyberChall";
	const paragraph = "Une application web dédiée à l’apprentissage des fondamentaux de la cybersécurité à travers des quiz et des challenges.";

	function typeText(elementId, text, delay = 50, callback) {
		const element = document.getElementById(elementId);
		if (!element) return;

		let i = 0;
		function typeChar() {
			if (i < text.length) {
				element.textContent += text.charAt(i);
				i++;
				setTimeout(typeChar, delay);
			} else if (callback) {
				callback();
			}
		}
		typeChar();
	}

	typeText("typed-title", title, 70, function () {
		setTimeout(() => {
			typeText("typed-text", paragraph, 25);
		}, 300);
	});
});