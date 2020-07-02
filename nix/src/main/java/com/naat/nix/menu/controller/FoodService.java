package com.naat.nix.menu.controller;

import java.util.ArrayList;
import java.util.Optional;

import com.naat.nix.menu.model.Food;
import com.naat.nix.menu.model.Category;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Manipulación de los platillos
 */
@Service
public class FoodService {

  /* Manejador de entidades para crear consultas en SQL puro */
  @PersistenceContext
  EntityManager entityManager;

  /* DAO para manipular platillos en la base de datos */
  @Autowired
  private FoodRepository repository;

  /* DAO para manipular las categorias */
  @Autowired
  private CategoryRepository categoryRepository;

  /**
   * Obtiene todos los platillos disponibles
   * @return Lista de todos los platillos del menú
   */
  public ArrayList<Food> getFoods() {
    ArrayList<Food> platillos = (ArrayList<Food>) repository.findAll();
    return platillos;
  }

  /**
  * Obtiene los platillos por categoria
  * @param category la categoria de la cual queremos encontrar los platillos.
  * @return los platillos que pertenecen a la categoria.
  */
  public ArrayList<Food> getFoodsByCategory (String category) {
    Optional<Category> op = categoryRepository.findById (category);
    Category ct = null;
    if (op.isPresent()) {
      ct = op.get();
    }
    Query query = entityManager.createQuery ("FROM Food f WHERE f.category =: category", Food.class);
    query.setParameter("category", ct);
    ArrayList<Food> foods = (ArrayList<Food>) query.getResultList();
    if (foods.size() == 0) {
      return new ArrayList<Food>();
    }
    return foods;
  }


  /**
   * Platillo con el identificador dado
   * @param id Identificador del platillo buscado
   * @return Platillo con el identificador dado
   */
  public Food getFoodById(int id) {
    Optional<Food> platillo = repository.findById(id);
    if (platillo.isPresent()) {
      return platillo.get();
    }
    return null;
  }


  /**
   * Guarda el platillo en la base de datos
   * @param p Platillo que se va a crear
   * @return Platillo recién creado
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
   * @param p Platillo a actualizar
   * @return Platillo recién actualizado
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
