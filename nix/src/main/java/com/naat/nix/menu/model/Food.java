package com.naat.nix.menu.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Platillo")
public class Food {

	@Id
	@GeneratedValue
	@Column(name="id")
	private int idFood;

	@Column(name="precio")
	private int price;

	@Column(name="descripcion")
	private String description;

	@Column(name="foto")
	private String image;

	@Column(name="nombre")
	private String name;

	@ManyToOne
	@JoinColumn(name="categoria")
	private Category category;

	@OneToMany(mappedBy = "food")
	private List<CartFood> cartFoods;

	public Food(String name, String description, String image, int price,
	Category category) {
		this.name = name; this.description = description; this.image = image;
		this.price = price; this.category = category;
	}

	@Override
	public boolean equals (Object obj) {
		Food f = (Food) obj;
		if (f.getName().equals(this.name)) {
			return true;
		}
		return false;
	}

}
