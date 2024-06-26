package com.server.services;

import com.server.domains.Menu;
import com.server.domains.requests.event.MenuRequest;
import com.server.domains.responses.MenuResponse;
import com.server.mappers.MenuMapper;
import com.server.repositories.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MenuService {

  private final MenuRepository repository;

  public List<MenuResponse> getAll() {
    Iterable<Menu> menus = repository.findAll();

    List<MenuResponse> menuResponses = new ArrayList<>();
    menus.forEach(menu -> menuResponses.add(MenuMapper.generateResponse(menu)));
    return menuResponses;
  }

  public MenuResponse getById(Integer id) {
    return MenuMapper.generateResponse(repository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Menu not found")));
  }

  public MenuResponse createMenu(MenuRequest request) {
    Menu menu = repository.save(MenuMapper.createMenu(request));
    return MenuMapper.generateResponse(menu);
  }

  public MenuResponse updateMenu(MenuRequest request) {
    Optional<Menu> optionalMenu = repository.findById(request.getId());
    Menu existingMenu = optionalMenu.orElseThrow(() -> new IllegalArgumentException("Menu not found"));

    Menu updatedMenu = MenuMapper.updateMenu(existingMenu, request);
    updatedMenu = repository.save(updatedMenu);
    return MenuMapper.generateResponse(updatedMenu);
  }

  public void deleteMenu(Integer id) {
    repository.deleteById(id);
  }
}
