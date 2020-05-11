package com.danishpastry.pastryshoppingservice.service;

import com.danishpastry.pastryshoppingservice.domain.CartItem;
import com.danishpastry.pastryshoppingservice.repository.CartItemRepository;
import com.danishpastry.pastryshoppingservice.service.dto.CartRequest;
import com.danishpastry.pastryshoppingservice.service.impl.DefaultCartItemService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CardItemServiceTest {

    @Mock
    private CartItemRepository repository;

    @InjectMocks
    private CartItemService service = new DefaultCartItemService(repository);

    @Test
    public void testAddToCart() {
        CartRequest request = new CartRequest();
        request.setCost(34d);

        CartItem item = new CartItem();
        request.setCost(34d);

        when(repository.save(any(CartItem.class))).thenReturn(item);

        service.addToCart(request);
    }

    @Test
    public void testDeleteFromCart() {
        CartItem item = new CartItem();
        item.setId("1234");
        when(repository.findById("1234")).thenReturn(Optional.of(item));
        doNothing().when(repository).delete(item);
        service.deleteItem("1234");
    }

    @Test
    public void testGetAllItem() {
        List<CartItem> cartItems = Arrays.asList(new CartItem("1", "iphone", 1200d), new CartItem("2", "s8", 900d));
        Mockito.when(repository.findAll()).thenReturn(cartItems);

        List<CartItem> allItem = service.getAllItem();

        assertThat(allItem.size()).isEqualTo(2);
        assertThat(allItem.get(0).getCost()).isEqualTo(1200d);
    }
}
