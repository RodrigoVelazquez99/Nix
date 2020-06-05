package com.naat.nix.menu.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Representación de un platillo en la base de datos
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Platillo")
public class Food {

	/* El id */
	@Id
	@GeneratedValue
	@Column(name="id")
	private int idFood;

	/* El precio */
	@Column(name="precio")
	private int price;

	/* La descripcion del platillo */
	@Column(name="descripcion")
	private String description;

	/* El nombre del archivo */
	@Column(name="foto")
	private String image;

	/* El nombre del platillo */
	@Column(name="nombre")
	private String name;

	/* La categoria a la que pertenece */
	@ManyToOne
	@JoinColumn(name="categoria")
	private Category category;

	/* Las solicitudes de platillos a los que pertenece */
	@OneToMany(mappedBy = "food")
	private List<CartFood> cartFoods;

	public Food(String name, String description, String image, int price,
	Category category) {
		this.name = name; this.description = description; this.image = image;
		this.price = price; this.category = category;
	}
}
