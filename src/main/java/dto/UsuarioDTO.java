package controller.dto;

public class Usuario{

    // Atributos privados
    private int id;
    private String nombre;
    private String apellido;
    private String correo;
    private String contrasena;
    private boolean admin;

    // Constructor vacío
    public Usuario() {
        this.admin = false; // Inicializa admin a false por defecto
    }

    // Constructor con parámetros
    public Usuario(int id, String nombre, String apellido, String correo, String contrasena) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.contrasena = contrasena;
        this.admin = false; // atributo para indicar si el usuario es administrador
    }

    // Métodos getter y setter para cada atributo

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", correo='" + correo + '\'' +
                ", contrasena='" + contrasena + '\'' +
                '}';
    }

    // Método para validar la contraseña
    validarContrasena(contrasena) {
        return this.contrasena === contrasena;
    }

}