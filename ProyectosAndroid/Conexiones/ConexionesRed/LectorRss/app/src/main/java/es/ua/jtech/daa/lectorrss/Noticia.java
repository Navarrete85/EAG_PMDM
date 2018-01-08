package es.ua.jtech.daa.lectorrss;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

public class Noticia {
	private String titulo;
	private String descripcion;
	private String link;
	private String fecha;
	private String linkImagen;
	private Drawable imagen;
	
	public Noticia(){
		titulo = "";
		descripcion = "";
		link = "";
		fecha = "";
		linkImagen = "";
	}
	public Noticia(String titulo,String fecha,String descripcion,String link,String linkImagen){
		this.titulo=titulo;
		this.fecha=fecha;
		this.descripcion=descripcion;
		this.link=link;
		this.linkImagen=linkImagen;
	}
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getLinkImagen() {
		return linkImagen;
	}
	public void setLinkImagen(String linkImagen) {
		this.linkImagen = linkImagen;
	}
	public void setImagen(Drawable imagen) {
		this.imagen = imagen;
	}
	public Drawable getImagen() {
		return imagen;
	}
	public void loadImagen(String url) throws MalformedURLException, IOException{
		InputStream is;
		
		is= new URL(url).openStream();
		imagen = new BitmapDrawable(BitmapFactory.decodeStream(is));
		
	}
	
}
