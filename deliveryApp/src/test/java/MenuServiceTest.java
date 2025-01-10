//
//import com.example.deliveryApp.entity.Menu;
//import com.example.deliveryApp.menu.dto.MenuRequestDto;
//import com.example.deliveryApp.menu.dto.MenuResponseDto;
//import com.example.deliveryApp.menu.repository.MenuRepository;
//import com.example.deliveryApp.menu.service.MenuService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyLong;
//
//class MenuServiceTest {
//
//    private MenuService menuService;
//
//    @Mock
//    private MenuRepository menuRepository;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//        menuService = new MenuService(menuRepository, null); // StoreRepository는 null로 설정
//    }
//
//    @Test
//    void testCreateMenu() {
//        // given
//        MenuRequestDto menuRequestDto = new MenuRequestDto("Burger", 5000, 1); // storeId는 null 처리
//        Menu menu = new Menu(menuRequestDto.getMenuName(), menuRequestDto.getPrice(), null);
//
//        Mockito.when(menuRepository.save(any(Menu.class))).thenReturn(menu);
//
//        // when
//        MenuResponseDto response = menuService.createMenu(menuRequestDto);
//
//        // then
//        assertNotNull(response);
//        assertEquals("Burger", response.getMenuName());
//        assertEquals(5000, response.getPrice());
//    }
//
//    @Test
//    void testUpdateMenu() {
//        // given
//        Long menuId = 1L;
//        Menu existingMenu = new Menu("Burger", 5000, null);
//        existingMenu.setId(menuId);
//
//        MenuRequestDto menuRequestDto = new MenuRequestDto("Pizza", 8000, 1);
//
//        Mockito.when(menuRepository.findByIdOrElseThrow(menuId)).thenReturn(existingMenu);
//
//        // when
//        MenuResponseDto response = menuService.updateMenu(menuId, menuRequestDto);
//
//        // then
//        assertNotNull(response);
//        assertEquals("Pizza", response.getMenuName());
//        assertEquals(8000, response.getPrice());
//    }
//
//    @Test
//    void testDeleteMenu() {
//        // given
//        Long menuId = 1L;
//        Menu existingMenu = new Menu("Burger", 5000, null);
//        existingMenu.setId(menuId);
//
//        Mockito.when(menuRepository.findByIdOrElseThrow(menuId)).thenReturn(existingMenu);
//
//        // when
//        menuService.deleteMenu(menuId);
//
//        // then
//        assertFalse(existingMenu.isActive()); // 삭제 상태 확인
//    }
//}

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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class MenuServiceTest {
    @InjectMocks
    private MenuService menuService;

    @Mock
    private MenuRepository menuRepository;



    @Test
    void testCreateMenu() {
        // given
        MenuRequestDto menuRequestDto = new MenuRequestDto("Burger", 5000, 1); // storeId는 무시
        Menu menu = new Menu(menuRequestDto.getMenuName(), menuRequestDto.getPrice(), null);

        Mockito.when(menuRepository.save(any(Menu.class))).thenReturn(menu);

        // when
        MenuResponseDto response = menuService.createMenu(menuRequestDto);

        // then
        assertNotNull(response);
        assertEquals("Burger", response.getMenuName());
        assertEquals(5000, response.getPrice());
    }

    @Test
    void testUpdateMenu() {
        // given
        Long menuId = 1L;
        Menu existingMenu = new Menu("Burger", 5000, null);
        existingMenu.setId(menuId);

        MenuRequestDto menuRequestDto = new MenuRequestDto("Pizza", 8000, 1);

        Mockito.when(menuRepository.findByIdOrElseThrow(menuId)).thenReturn(existingMenu);

        // when
        MenuResponseDto response = menuService.updateMenu(menuId, menuRequestDto);

        // then
        assertNotNull(response);
        assertEquals("Pizza", response.getMenuName());
        assertEquals(8000, response.getPrice());
    }

    @Test
    void testDeleteMenu() {
        // given
        Long menuId = 1L;
        Menu existingMenu = new Menu("Burger", 5000, null);
        existingMenu.setId(menuId);

        Mockito.when(menuRepository.findByIdOrElseThrow(menuId)).thenReturn(existingMenu);

        // when
        menuService.deleteMenu(menuId);

        // then
        assertFalse(existingMenu.getIsActive()); // 삭제 상태 확인
    }
}

