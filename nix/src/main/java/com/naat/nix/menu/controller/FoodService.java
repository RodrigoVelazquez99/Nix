package com.naat.nix.menu.controller;

import java.util.ArrayList;
import java.util.Optional;

import com.naat.nix.menu.model.Food;
import com.naat.nix.menu.model.FoodForm;
import com.naat.nix.menu.model.Category;
import com.naat.nix.user.util.FileService;

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

  /* Manipulación de archivos */
  @Autowired
  private FileService fileService;

  /**
   * Obtiene todos los platillos disponibles
   * @return Lista de todos los platillos del menú
   */
  public ArrayList<Food> getFoods() {
    ArrayList<Food> platillos = (ArrayList<Food>) repository.findAll();
    return platillos;
  }

  /**
  * Obtiene un platillo por su nombre.
  * @param name Nombre del platillo a buscar.
  * @return el platillo si se encontró.
  */
  public Food getFood (String name) {
    Food food =  repository.findByName(name);
    return food;
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
  * Obtiene el platillo que tenga como imagen la pasada a la función.
  * @param image la imagen de la cual queremos obtener su platillo.
  * @return el platillo que tiene esa imagen o null en otro caso.
  */
  public Food getFoodByImage (String image) {
    Food food = repository.findByImage(image);
    return food;
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
  * Guarda el nuevo platillo desde la plantilla.
  * @param foodForm la plantilla del nuevo platillo.
  */
  public void save (FoodForm foodForm) {
    Food n = new Food ();
    n.setName (foodForm.getNewName());
    n.setPrice (foodForm.getNewPrice());
    n.setCategory (foodForm.getNewCategory());
    n.setDescription (foodForm.getNewDescription());
    n.setImage (fileService.uploadFile(foodForm.getNewImage()));
    repository.save(n);
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
  * Elimina el platillo de auerdo a su nombre.
  * @param name el nombre del platillo a eliminar.
  */
  public void delete (String name) {
    Food platillo = repository.findByName(name);
    repository.deleteById(platillo.getIdFood());
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

  /**
  * Actualiza el platillo deacuerdo a la plantilla para editar.
  * @param foodForm la plantilla con los datos nuevos del platillo.
  */
  public void update (FoodForm foodForm) {
    Food food = repository.findByName(foodForm.getOldName());
    if (food != null) {
      fileService.removeFile (food.getImage());
      food.setImage(fileService.uploadFile(foodForm.getNewImage()));
      food.setPrice(foodForm.getNewPrice());
      food.setName(foodForm.getNewName());
      food.setCategory(foodForm.getNewCategory());
      food.setDescription(foodForm.getNewDescription());
      repository.save (food);
    }
  }

  /**
  * Obtener todos los platillos que coinciden con la entrada.
  * @param entry la entrada de la cual queremos obtener los platillos que coincidan.
  * @return los platillos que coinciden con la entrada.
  */
  public ArrayList<Food> getCoincidences (String entry) {
    Query query = entityManager.createQuery("FROM Food p WHERE p.name LIKE :entry", Food.class);
    query.setParameter("entry", entry + "%");
    ArrayList<Food> foods = (ArrayList<Food>) query.getResultList();
    if (foods.size() == 0) {
      return new ArrayList<Food>();
    }
    return foods;
  }

}
