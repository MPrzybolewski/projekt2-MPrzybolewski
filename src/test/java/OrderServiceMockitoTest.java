import interfaces.IArticleRepository;
import interfaces.IDetailOrderRepository;
import interfaces.IOrderRepository;
import models.Article;
import models.Client;
import models.DetailOrder;
import models.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import services.OrderService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class OrderServiceMockitoTest {

    private IOrderRepository orderRepository;
    private IDetailOrderRepository detailOrderRepository;
    private IArticleRepository articleRepository;
    private OrderService orderService;

    @BeforeEach
    void setup(){
        orderRepository =  Mockito.mock(IOrderRepository.class);
        detailOrderRepository = Mockito.mock(IDetailOrderRepository.class);
        articleRepository = Mockito.mock(IArticleRepository.class);
        orderService = new OrderService(orderRepository, detailOrderRepository, articleRepository);
    }

    @Test
    void addArticleToOrderThrowsWhenArticleIsNull(){
        Order order = new Order(1,1);
        assertThrows(IllegalArgumentException.class, () -> orderService.addAritcleToOrder(null, order), "article or order is null");
    }

    @Test
    void addArticleToOrderThrowsWhenOrderIsNull(){
        Order order = new Order(1,1);
        Article article = new Article(1,"test",1);

        assertThrows(IllegalArgumentException.class, () -> orderService.addAritcleToOrder(article, null), "article or order is null");
    }

    @Test
    void addArticleToOrderThrowsWhenOrderAndArticleIsNull(){
        assertThrows(IllegalArgumentException.class, () -> orderService.addAritcleToOrder(null, null), "article or order is null");
    }

    @Test
    void addArticleToOrderShouldReturnFalseWhenArticleIsInDetailOrderEntity(){
        Article article = new Article(1,"test", 1);
        Order order = new Order(0,1);
        List<DetailOrder> detailOrders = new ArrayList<>();

        detailOrders.add(new DetailOrder(1,0));

        when(detailOrderRepository.getAllByArticle(article)).thenReturn(detailOrders);

        assertFalse(orderService.addAritcleToOrder(article,order));
    }

    @Test
    void addArticleToOrderShouldReturnTrueWhenArticleIsNotInDetailOrderEntity(){
        Article article = new Article(1,"test", 1);
        Order order = new Order(0,1);
        List<DetailOrder> detailOrders = new ArrayList<>();

        when(detailOrderRepository.getAllByArticle(any(Article.class))).thenReturn(detailOrders);
        when(detailOrderRepository.add(any(DetailOrder.class))).thenReturn(true);

        assertTrue(orderService.addAritcleToOrder(article,order));
    }

    @Test
    void removeArticleFromOrderShouldThrowWhenArticleAndOrderIsNull(){
        assertThrows(IllegalArgumentException.class, () -> orderService.removeArticleFromOrder(null, null), "article is null");
    }

    @Test
    void removeArticleFromOrderShouldThrowWhenOrderIsNull(){
        Order order = new Order(1,1);
        Article article = new Article(1,"test",1);

        assertThrows(IllegalArgumentException.class, () -> orderService.removeArticleFromOrder(article, null), "order is null");
    }

    @Test
    void removeArticleFromOrderShouldReturnFalseWhenArticleIsNotExistInDetailOrderEntity(){
        Article article = new Article(1,"test", 1);
        Order order = new Order(0,1);
        List<DetailOrder> detailOrders = new ArrayList<>();

        when(detailOrderRepository.getAllByArticle(any(Article.class))).thenReturn(detailOrders);

        assertFalse(orderService.removeArticleFromOrder(article,order));
    }

    @Test
    void removeArticleFromOrderShouldReturnTrueWhenArticleIsExistInDetailOrderEntity(){
        Article article = new Article(1,"test", 1);
        Order order = new Order(0,1);
        List<DetailOrder> detailOrders = new ArrayList<>();
        detailOrders.add(new DetailOrder(1,0));

        when(detailOrderRepository.getAllByArticle(any(Article.class))).thenReturn(detailOrders);
        when(detailOrderRepository.getAllByOrder(any(Order.class))).thenReturn(detailOrders);
        when(detailOrderRepository.delete(any(DetailOrder.class))).thenReturn(true);

        assertTrue(orderService.removeArticleFromOrder(article,order));
    }

    @Test
    void addOrderShouldThrowWhenOrderIsNull(){
        assertThrows(IllegalArgumentException.class, () -> orderService.addOrder(null), "order is null");
    }

    @Test
    void addOrderShouldReturTrueWhenOrderIsCorrect(){
        Order order = new Order(1,1);
        when(orderRepository.add(order)).thenReturn(true);
        assertTrue(orderService.addOrder(order));
    }

    @Test
    void addOrderShouldReturFalseWhenOrderIsNotCorrect(){
        Order order = new Order(1,-1);
        when(orderRepository.add(order)).thenReturn(false);
        assertFalse(orderService.addOrder(order));
    }

    @Test
    void updateOrderShouldThrowWhenOrderIsNull(){
        assertThrows(IllegalArgumentException.class, () -> orderService.updateOrder(null), "order is null");
    }

    @Test
    void updateOrderShouldReturFalseWhenOrderIsNotCorrect(){
        Order order = new Order(1,-1);
        when(orderRepository.update(order)).thenReturn(false);
        assertFalse(orderService.updateOrder(order));
    }


    @Test
    void updateOrderShouldReturTrueWhenOrderIsCorrect(){
        Order order = new Order(1,1);
        when(orderRepository.update(order)).thenReturn(true);
        assertTrue(orderService.updateOrder(order));
    }


    @Test
    void deleteOrderShouldThrowWhenOrderIsNull(){
        assertThrows(IllegalArgumentException.class, () -> orderService.deleteOrder(null), "order is null");
    }

    @Test
    void deleteOrderShouldReturFalseWhenOrderIsNotCorrect(){
        Order order = new Order(1,-1);
        when(orderRepository.delete(order)).thenReturn(false);
        assertFalse(orderService.deleteOrder(order));
    }


    @Test
    void deleteOrderShouldReturTrueWhenOrderIsCorrect(){
        Order order = new Order(1,1);
        when(orderRepository.delete(order)).thenReturn(true);
        assertTrue(orderService.deleteOrder(order));
    }

    @Test
    void getAllByClientsShouldThrowWhenClientIsNull(){
        assertThrows(IllegalArgumentException.class, () -> orderService.getAllByClients(null), "order is null");
    }

    @Test
    void getAllByClientsShouldReturnEmptyListWhenListIsEmpty(){
        Client client = new Client(1, "test", "test", "test");
        List<Order> ordersList = new ArrayList<>();
        when(orderRepository.getAllByClient(any(Client.class))).thenReturn(ordersList);
        assertEquals(orderService.getAllByClients(client).size(), 0);
    }


    @Test
    void getAllByClientsShouldReturnCorrectListWhenListIsNotEmpty(){
        Client client = new Client(1, "test", "test", "test");
        Order order = new Order(1,1);
        List<Order> ordersList = new ArrayList<>();
        ordersList.add(order);

        when(orderRepository.getAllByClient(any(Client.class))).thenReturn(ordersList);

        assertEquals(orderService.getAllByClients(client).size(), 1);
    }


    @Test
    void getAllDetailOrderShouldReturnNullWhenDetailOrderIsNotExeist(){
        when(detailOrderRepository.getAll()).thenReturn(null);
        assertNull(detailOrderRepository.getAll());
    }

    @Test
    void getAllDetailOrderShouldReturnCorrectDetailOrderWhenDetailOrderIsNotExeist(){
        DetailOrder detailOrder = new DetailOrder(1,1);
        List<DetailOrder> detailOrders = new ArrayList<>();
        detailOrders.add(detailOrder);
        when(detailOrderRepository.getAll()).thenReturn(detailOrders);
        assertEquals(detailOrderRepository.getAll().size(), 1);
    }

    @Test
    void getAllOrderShouldReturnNullWhenDetailOrderIsNotExeist(){
        when(orderRepository.getAll()).thenReturn(null);
        assertNull(orderRepository.getAll());
    }

    @Test
    void getAllOrderShouldReturnCorrectDetailOrderWhenDetailOrderIsNotExeist(){
        Order order = new Order(1,1);
        Order order2 = new Order(1,1);
        Order order3 = new Order(1,1);

        List<Order> orders = new ArrayList<>();
        orders.add(order);
        orders.add(order2);
        orders.add(order3);

        when(orderRepository.getAll()).thenReturn(orders);
        assertEquals(orderRepository.getAll().size(), 3);
    }

    @Test
    void getByIdOrderShouldReturnNullWhenOrderIsNotExist(){
        when(orderRepository.getById(any(Integer.class))).thenReturn(null);
        assertNull(orderRepository.getById(1));
    }

    @Test
    void getByIdOrderShouldReturnCorrectOrderWhenOrderIsExist(){
        Order order = new Order(1,1);
        when(orderRepository.getById(1)).thenReturn(order);
        assertEquals(orderRepository.getById(1).getId(), 1);
    }


}
