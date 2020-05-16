package com.naat.nix.menu.controller;

import java.util.Set;

import javax.annotation.PostConstruct;

import com.naat.nix.menu.model.Category;
import com.naat.nix.menu.model.Food;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MenuInitialization {

  @Autowired
  private CategoryRepository categoryDao;

  @Autowired
  private FoodRepository foodDao;

  @PostConstruct
  public void addMenu() {
    // Algunas categorías
    var guarniciones = new Category("Guarniciones"); categoryDao.save(guarniciones);
    var bebidas = new Category("Bebidas"); categoryDao.save(bebidas);
    var aperitivos = new Category("Aperitivos"); categoryDao.save(aperitivos);
    var entradas = new Category("Entradas"); categoryDao.save(entradas);
    var ensaladas = new Category("Ensaladas"); categoryDao.save(ensaladas);

    // Algunos platillos
    var platillos = Set.of(
      new Food("Keylex", "Synergized scalable solution", "semper.tiff", 85, bebidas),
      new Food("Bamity", "Fully-configurable empowering knowledge base", "hac_habitasse.png", 59, guarniciones),
      new Food("Hatity", "Virtual zero tolerance benchmark", "volutpat_in.png", 52, entradas),
      new Food("Fix San", "Multi-channelled well-modulated matrix", "eget_semper.tiff", 79, aperitivos),
      new Food("Zoolab", "Extended secondary groupware", "odio_cras_mi.tiff", 52, ensaladas),
      new Food("Lotlux", "Reduced optimizing open system", "donec_odio.gif", 90, guarniciones),
      new Food("Opela", "Synchronised upward-trending orchestration", "eget_semper.jpeg", 90, aperitivos),
      new Food("Quo Lux", "Configurable didactic hardware", "augue_a.png", 17, entradas),
      new Food("Stringtough", "Persevering encompassing service-desk", "nibh_fusce_lacus.jpeg", 13, entradas),
      new Food("Konklux", "Networked high-level Graphic Interface", "justo_aliquam.gif", 100, bebidas),
      new Food("Span", "Advanced non-volatile complexity", "at_turpis.jpeg", 79, ensaladas),
      new Food("Transcof", "Business-focused logistical array", "pellentesque_volutpat_dui.png", 91, bebidas),
      new Food("Stronghold", "Upgradable local help-desk", "justo_sollicitudin.tiff", 92, aperitivos),
      new Food("Aerified", "Reverse-engineered cohesive open system", "erat_curabitur_gravida.tiff", 51, guarniciones),
      new Food("Sub-Ex", "Monitored explicit capability", "lacinia_aenean_sit.jpeg", 5, entradas),
      new Food("Otcom", "Open-architected discrete standardization", "malesuada_in_imperdiet.jpeg", 72, ensaladas),
      new Food("Tresom", "Robust national access", "ornare_consequat_lectus.png", 2, entradas),
      new Food("Mat Lam Tam", "Enhanced secondary encryption", "fusce.tiff", 24, bebidas),
      new Food("Veribet", "User-friendly client-driven application", "rhoncus_mauris_enim.tiff", 38, bebidas),
      new Food("Trippledex", "Exclusive transitional paradigm", "rhoncus.tiff", 96, ensaladas)
    );

    foodDao.saveAll(platillos);
  }
}