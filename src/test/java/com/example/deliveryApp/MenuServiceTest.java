package com.example.deliveryApp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

import com.example.deliveryApp.entity.Menu;
import com.example.deliveryApp.menu.dto.MenuRequestDto;
import com.example.deliveryApp.menu.dto.MenuResponseDto;
import com.example.deliveryApp.menu.repository.MenuRepository;
import com.example.deliveryApp.menu.service.MenuService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class MenuServiceTest {

    @InjectMocks
    private MenuService menuService;

    @Mock
    private MenuRepository menuRepository;



    @Test
    void testCreateMenu() {
        // given
        MenuRequestDto menuRequestDto = new MenuRequestDto("Burger", 5000); // storeId는 무시
        Menu menu = new Menu(menuRequestDto.getMenuName(), menuRequestDto.getPrice(), null);

        Mockito.when(menuRepository.save(any(Menu.class))).thenReturn(menu);

        // when
        MenuResponseDto response = menuService.createMenu(menuRequestDto);

        // then
        assertNotNull(response);
        assertEquals("Burger", response.getMenuName());
        assertEquals(5000, response.getPrice());
    }
}