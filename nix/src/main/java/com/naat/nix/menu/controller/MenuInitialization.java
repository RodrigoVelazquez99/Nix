package com.naat.nix.menu.controller;

import java.util.Set;

import javax.annotation.PostConstruct;

import com.naat.nix.menu.model.Category;
import com.naat.nix.menu.model.Food;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Menú demo
 */
@Component
public class MenuInitialization {

  /* DAO para manejar categorias */
  @Autowired
  private CategoryRepository categoryDao;

  /* DAO para manejar platillos */
  @Autowired
  private FoodRepository foodDao;

  /**
   * Creando entradas de un menú demo
   */
  @PostConstruct
  public void addMenu() {
    // Algunas categorías
    var aperitivos = new Category("Aperitivos"); categoryDao.save(aperitivos);
    var ensaladas = new Category("Ensaladas"); categoryDao.save(ensaladas);
    var entradas = new Category("Entradas"); categoryDao.save(entradas);
    var guarniciones = new Category("Guarniciones"); categoryDao.save(guarniciones);
    var bebidas = new Category("Bebidas"); categoryDao.save(bebidas);

    // Algunos platillos
    // Aperitivos
    var aperitivosItems = Set.of (
      new Food("Guacamole","Salsa mexicana preparada a base de aguacate y chile verde o chile pimiento","guacamole.jpg",30,aperitivos),
      new Food("Papas a la francesa","Papas cortadas en rodajas y freidas en aceite","papas.jpg",30,aperitivos),
      new Food("Brocheta de frutas","Bolitas de melón, sandía y plátano en un palo","brocheta_fruta.jpg",40,aperitivos),
      new Food("Tabla de quesos","Tabla con diversos quesos para compartir","queso.jpg",60,aperitivos),
      new Food("Ensaladilla rusa","Platillo típico con papas y hortalizas","rusa.jpg",35,aperitivos)
    );
    foodDao.saveAll(aperitivosItems);

    // Ensaladas
    var ensaladasItems = Set.of (
      new Food("César","Ensalada de lechuga romana y croûtons con jugo de limón, aceite de oliva, ajo, mostaza de Dijon, queso parmesano y pimienta negra","cesar.jpg",60,ensaladas),
      new Food("Mediterránea","Ensalada con ingredientes característicos de la zona del mediterráneo.","mediterranea.jpg",55,ensaladas),
      new Food("De pollo","Ensalada César con pollo.","pollo.jpg",65,ensaladas)
    );
    foodDao.saveAll(ensaladasItems);

    // Entradas
    var entradasItems = Set.of(
      new Food("Consomé","Clásica receta de consomé de pollo.","consome.jpg",70,entradas),
      new Food("Sopa de fideo","Con pollo.","fideos.jpg",69,entradas),
      new Food("Sope sencillo","Con frijoles, lechuga, jitomatee, cebolla y queso","sope.jpg",36,entradas),
      new Food("Tostada sencilla","Con frijoles, lechuga, cebolla y queso","tostada.jpg",50,entradas),
      new Food("Queso fundido","160 gramos de queso manchego fundido.","queso_fundido.jpg",80,entradas),
      new Food("Quesadilla","Quesadilla de queso.","quesadilla.jpg",30,entradas)
    );
    foodDao.saveAll(entradasItems);

    // Guarniciones
    var guarnicionesItems = Set.of(
      new Food("Pollo con mole","Platillo de pollo con mole. Incluye tortillas.","mole.jpg",80,guarniciones),
      new Food("Fajitas de pollo","Pollo asado a la parrilla cortado en tiras y servido en tortilla de harina.","fajitas.jpg",180,guarniciones),
      new Food("Fajitas de arrachera","Arrachera asada a la parrilla cortada en tiras y servida en tortilla de harina. Incluye papas.","fajitas_arrachera.jpg",205,guarniciones),
      new Food("Gringas","Tortilla de harina rellena de queso, al pastor y piña.","gringa.jpeg",95,guarniciones),
      new Food("Burrito","Tortilla de harina enrollada con carne, queso, frijoles, jitomate y cebolla.","burrito.jpg",100,guarniciones),
      new Food("Taco de Suadero","Con tortilla hecha a mano, cebolla y cilantro.","suadero.jpg",20,guarniciones),
      new Food("Taco de Pastor","Con tortilla hecha a mano, cebolla y cilantro.","pastor.jpg",20,guarniciones),
      new Food("Taco de Bistec","Con tortilla hecha a mano, cebolla y cilantro.","bistec.jpg",20,guarniciones)
    );
    foodDao.saveAll(guarnicionesItems);

    var bebidasItems = Set.of(
      new Food("Agua de horchata","1/2 litro.","horchata_jamaica.jpg",40, bebidas),
      new Food("Agua de jamaica","1/2 litro","horchata_jamaica.jpg",40, bebidas),
      new Food("Refresco","350 mL.","refresco.jpg",30, bebidas),
      new Food("Limonada","1 litro.","limonada.jpg",45, bebidas)
    );
    foodDao.saveAll(bebidasItems);
  }
}