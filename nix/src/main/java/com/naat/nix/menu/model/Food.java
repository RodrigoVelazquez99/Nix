package com.naat.nix.menu.model;

import com.naat.nix.menu.model.Category;
import com.naat.nix.menu.model.Cart;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Blob;
import java.util.List;
import lombok.Data;

@Data
@Entity
@Table(name="Platillo")
public class Food {

	@Id
	@GeneratedValue
	@Column(name="id_platillo")
	private int idPlatillo;

	@Column(name="precio")
	private int precio;

	@Column(name="descripcion")
	private String descripcion;

	@Column(name="foto")
	private Blob foto;

	@Column(name="nombre")
	private String nombre;

	@ManyToOne
	@JoinColumn(name="categoria")
	private Category categoria;

	@ManyToMany(mappedBy="platillos")
	private List<Cart> carritos;

	public Food (){
	}

	public Food (int idPlatillo,
				String nombre,
				String descripcion,
				int precio,
				Blob foto,
				Category categoria) {
	  this.idPlatillo = idPlatillo;
	  this.precio = precio;
	  this.descripcion = descripcion;
	  this.foto = foto;
	  this.nombre = nombre;
	  this.categoria = categoria;
  	}

	/* Copia el objeto de otro objeto */
  	public Food (Food f) {
  	  this.idPlatillo = f.idPlatillo;
  	  this.precio = f.precio;
  	  this.descripcion = f.descripcion;
  	  this.foto = f.foto;
  	  this.nombre = f.nombre;
  	  this.categoria = f.categoria;
  	}


}
