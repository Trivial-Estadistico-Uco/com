document.getElementById("registerForm").onsubmit = function(event) {
    event.preventDefault();  // Prevenir el envío del formulario por defecto

    const nombre = document.getElementById("nombre").value;
    const apellido = document.getElementById("apellido").value;
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;

    // Validación simple del lado del cliente
    if (nombre === "" || apellido === "" || email === "" || password === "") {
        alert("Por favor, completa todos los campos.");
        return;
    }

    // Enviar los datos al servlet usando fetch
    fetch('/DoRegistration', {
        method: 'POST',
        headers: { 
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: `nombre=${encodeURIComponent(nombre)}&apellido=${encodeURIComponent(apellido)}&email=${encodeURIComponent(email)}&password=${encodeURIComponent(password)}`
    })
    .then(response => response.json()) // Asumimos que el servlet responde con JSON
    .then(data => {
        if (data.success) {
            // Redirigir al usuario a la página de inicio de sesión
            alert("Registro exitoso. Ahora puedes iniciar sesión.");
            window.location.href = 'iniciarsesion.html';
        } else {
            // Mostrar mensaje de error si el registro falla
            alert("Hubo un error al registrarse. Inténtalo de nuevo.");
        }
    })
    .catch(error => console.error('Error:', error));
};