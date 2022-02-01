package com.springdata.service;

import com.springdata.model.Category;
import com.springdata.model.Event;
import com.springdata.model.Ticket;
import com.springdata.model.User;

import java.util.List;

public interface TicketService {

    Ticket bookTicket(long userId, long eventId, int place, Category category);

    List<Ticket> getBookedTickets(User user);

    List<Ticket> getBookedTickets(Event event);

    void cancelTicket(long ticketId);

    List<Ticket> getAllTickets();
}
