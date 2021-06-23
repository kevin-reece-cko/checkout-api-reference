// Set theme based on local storage
var toggle = document.querySelector('.toggle-track');
var theme = localStorage.getItem('theme');
if (theme) {
	document.body.className = '';
	document.body.classList.add(theme);
	if (theme === 'dark') {
		toggle.classList.add('toggled');
	}
} else {
	localStorage.setItem('theme', 'light');
}

toggle.addEventListener('click', function () {
	if (toggle.classList.contains('toggled')) {
		toggle.classList.remove('toggled');
		localStorage.setItem('theme', 'light');
		document.body.className = '';
		document.body.classList.add('light');
	} else {
		toggle.classList.add('toggled');
		localStorage.setItem('theme', 'dark');
		document.body.className = '';
		document.body.classList.add('dark');
	}
});
