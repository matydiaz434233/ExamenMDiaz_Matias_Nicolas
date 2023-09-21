package com.matisoft.exploradordelugarestursticosdiazmatias.modelos;
import com.google.android.gms.maps.model.LatLng;

public class Lugar {
    private LatLng localizacion;
    private String nombreLugar;
    private String descripcion;
    private String foto;  // Puedes usar una URL o un recurso de imagen en su lugar
    private String horariosApertura;

    public Lugar(LatLng localizacion, String nombreLugar, String descripcion, String foto, String horariosApertura) {
        this.localizacion = localizacion;
        this.nombreLugar = nombreLugar;
        this.descripcion = descripcion;
        this.foto = foto;
        this.horariosApertura = horariosApertura;
    }

    public Lugar(String nombreLugar, String descripcion) {
        this.nombreLugar = nombreLugar;
        this.descripcion = descripcion;
    }

    public LatLng getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(LatLng localizacion) {
        this.localizacion = localizacion;
    }

    public String getNombreLugar() {
        return nombreLugar;
    }

    public void setNombreLugar(String nombreLugar) {
        this.nombreLugar = nombreLugar;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getHorariosApertura() {
        return horariosApertura;
    }

    public void setHorariosApertura(String horariosApertura) {
        this.horariosApertura = horariosApertura;
    }

    public static Lugar crearLugar(double latitud, double longitud, String nombre, String descripcion, String foto, String horariosApertura) {
        LatLng ubicacion = new LatLng(latitud, longitud);
        return new Lugar(ubicacion, nombre, descripcion, foto, horariosApertura);
    }
}

