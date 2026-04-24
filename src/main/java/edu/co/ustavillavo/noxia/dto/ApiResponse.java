package edu.co.ustavillavo.noxia.dto;

public class ApiResponse<T> {
    private int codigo;
    private String mensaje;
    private T datos;

    public ApiResponse(int codigo, String mensaje, T datos) {
        this.codigo = codigo;
        this.mensaje = mensaje;
        this.datos = datos;
    }

    public static <T> ApiResponse<T> ok(T datos) {
        return new ApiResponse<>(200, "OK", datos);
    }

    public static <T> ApiResponse<T> ok() {
        return new ApiResponse<>(200, "OK", null);
    }

    public static <T> ApiResponse<T> error(int codigo, String mensaje) {
        return new ApiResponse<>(codigo, mensaje, null);
    }

    public int getCodigo() { return codigo; }
    public void setCodigo(int codigo) { this.codigo = codigo; }

    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }

    public T getDatos() { return datos; }
    public void setDatos(T datos) { this.datos = datos; }
}