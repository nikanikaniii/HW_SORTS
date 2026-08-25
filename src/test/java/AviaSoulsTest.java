import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

public class AviaSoulsTest {

    Ticket ticket1 = new Ticket("MOW", "LED", 8900, 10, 12);
    Ticket ticket2 = new Ticket("MOW", "LED", 7500, 12, 16);
    Ticket ticket3 = new Ticket("MOW", "LED", 9700, 8, 9);
    Ticket ticket4 = new Ticket("MOW", "KZN", 7900, 11, 14);

    @Test
    public void shouldCompareTicketByPrice() {
        Assertions.assertTrue(ticket2.compareTo(ticket1) < 0);
        Assertions.assertTrue(ticket3.compareTo(ticket1) > 0);
        Assertions.assertEquals(0, ticket1.compareTo(ticket1));
    }

    @Test
    public void shouldSearchAndSortByPriceAscending() {
        AviaSouls manager = new AviaSouls();
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);

        Ticket[] expected = {ticket2, ticket1, ticket3};
        Ticket[] actual = manager.search("MOW", "LED");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchWhenOneTicketFound() {
        AviaSouls manager = new AviaSouls();
        manager.add(ticket1);
        manager.add(ticket4);

        Ticket[] expected = {ticket4};
        Ticket[] actual = manager.search("MOW", "KZN");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchWhenNoTicketsFound() {
        AviaSouls manager = new AviaSouls();
        manager.add(ticket1);

        Ticket[] expected = {};
        Ticket[] actual = manager.search("MOW", "AER");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shoulsCompareByFlightTime() {
        Comparator<Ticket> comparator = new TicketTimeComparator();

        Assertions.assertTrue(comparator.compare(ticket3, ticket1) < 0);
        Assertions.assertTrue(comparator.compare(ticket2, ticket1) > 0);
    }

    @Test
    public void shouldSearchAndSortByFlightTimeAscending() {
        AviaSouls manager = new AviaSouls();
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);

        Comparator<Ticket> comparator = new TicketTimeComparator();

        Ticket[] expected = {ticket3, ticket1, ticket2};
        Ticket[] actual = manager.searchAndSortBy("MOW", "LED", comparator);

        Assertions.assertArrayEquals(expected, actual);
    }
}
