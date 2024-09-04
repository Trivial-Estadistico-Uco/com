document.getElementById("loginForm").onsubmit = function(event) {
    event.preventDefault();  // Prevenir el envío del formulario por defecto

    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;

    // Validación simple del lado del cliente
    if (email === "" || password === "") {
        alert("Por favor, completa todos los campos.");
        return;
    }

    // Aquí podrías hacer una solicitud AJAX para enviar los datos al servidor
    fetch('/DoLogin', {
        method: 'POST',
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        body: `email=${encodeURIComponent(email)}&password=${encodeURIComponent(password)}`
    })
    .then(response => {
        if (response.ok) {
            // Redirigir al usuario al index si el login es exitoso
            window.location.href = 'index.html';
        } else {
            // Mostrar mensaje de error si el login falla
            alert("Email o contraseña incorrectos");
        }
    })
    .catch(error => console.error('Error:', error));
};
