package com.Alkemy.Challenge.Java.exception;

public class ResponseInfo {
    private Long id;
    private String url;
    private String mensaje;

    public ResponseInfo(Long id, String url, String mensaje) {
        this.id = id;
        this.url = url;
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
