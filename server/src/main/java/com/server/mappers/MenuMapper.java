package com.server.mappers;

import com.server.domains.Menu;
import com.server.domains.requests.event.MenuRequest;
import com.server.domains.responses.MenuResponse;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class MenuMapper {

  public static MenuResponse generateResponse(Menu menu) {
    return MenuResponse.builder()
        .id(menu.getId())
        .appetizers(menu.getAppetizers())
        .soussal(menu.getSoussal())
        .main(menu.getMain())
        .side(menu.getSide())
        .sorbet(menu.getSorbet())
        .type(menu.getType())
        .dessert(menu.getDessert())
        .cocktails(menu.getCocktails())
        .drinks(menu.getDrinks())
        .build();
  }

  public static Menu createMenu(MenuRequest request) {
    return Menu.builder()
        .appetizers(request.getAppetizers())
        .soussal(request.getSoussal())
        .main(request.getMain())
        .side(request.getSide())
        .sorbet(request.getSorbet())
        .type(request.getType())
        .dessert(request.getDessert())
        .cocktails(request.getCocktails())
        .drinks(request.getDrinks())
        .build();
  }

  public static Menu updateMenu(Menu menu, MenuRequest request) {
    menu.setAppetizers(request.getAppetizers());
    menu.setSoussal(request.getSoussal());
    menu.setMain(request.getMain());
    menu.setSide(request.getSide());
    menu.setSorbet(request.getSorbet());
    menu.setType(request.getType());
    menu.setDessert(request.getDessert());
    menu.setCocktails(request.getCocktails());
    menu.setDrinks(request.getDrinks());
    return menu;
  }
}
