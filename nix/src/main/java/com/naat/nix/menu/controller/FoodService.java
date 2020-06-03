package com.naat.nix.menu.controller;

import java.util.ArrayList;
import java.util.Optional;

import com.naat.nix.menu.model.Food;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class FoodService {

  @Autowired
  private FoodRepository repository;

  /* Obtiene todos los platillos */
  public ArrayList<Food> getFoods() {
    ArrayList<Food> platillos = (ArrayList<Food>) repository.findAll();
    return platillos;
  }

  /* Obtiene el platillo con el id */
  public Food getFoodById(int id) {
    Optional<Food> platillo = repository.findById(id);
    if (platillo.isPresent()) {
      return platillo.get();
    }
    return null;
  }


  /**
  * Guarda el platillo en la base de datos
  * @param p el platillo que se va a crear
  */
  public Food save(Food p) {
    Food n = repository.save(p);
    return n;
  }


  /**
  * Elimina el platillo si se encuentra en la base de datos
  * @param p el platillo a eliminar
  */
  public void delete(Food p) {
    Optional<Food> platillo = repository.findById(p.getIdFood());
    if (platillo.isPresent()) {
      repository.deleteById(p.getIdFood());
    }
  }

  /**
  * Si el platillo se encuentra en la base de datos, lo actualiza
  * @param p el platillo que se actualiza
  */
  public Food update(Food p) {
    Optional<Food> platillo = repository.findById(p.getIdFood());
    Food s = null;
    if (platillo.isPresent()) {
      s = platillo.get();
      s.setImage(p.getImage());
      s.setPrice(p.getPrice());
      s.setName(p.getName());
      s.setCategory(p.getCategory());
      s.setDescription(p.getDescription());
      s = repository.save(s);
    }
    return s;
  }

}
