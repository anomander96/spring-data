package com.springdata.service.impl;

import com.springdata.model.Category;
import com.springdata.model.Event;
import com.springdata.model.Ticket;
import com.springdata.model.User;
import com.springdata.repository.TicketRepository;
import com.springdata.service.EventService;
import com.springdata.service.TicketService;
import com.springdata.service.UserAccountService;
import com.springdata.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final UserService userService;
    private final EventService eventService;
    private final UserAccountService userAccountService;

    public TicketServiceImpl(TicketRepository ticketRepository, UserService userService, EventService eventService, UserAccountService userAccountService) {
        this.ticketRepository = ticketRepository;
        this.userService = userService;
        this.eventService = eventService;
        this.userAccountService = userAccountService;
    }

    @Override
    public Ticket bookTicket(long userId, long eventId, int place, Category category) {
        log.info("booking ticket");

        userService.getUserById(userId);
        Event eventById = eventService.getById(eventId);

        log.info("withdrawing money from account for event " + eventById);
        userAccountService.withdrawMoneyFromAccount(userId, eventById.getTicketPrice());

        Ticket ticket = new Ticket(eventId, userId, category, place);
        return ticketRepository.save(ticket);
    }

    @Override
    public List<Ticket> getBookedTickets(User user) {
        log.info("getBookedTickets by user " + user);
        return ticketRepository.findAllByUserId(user.getId());
    }

    @Override
    public List<Ticket> getBookedTickets(Event event) {
        log.info("getBookedTickets by event " + event);
        return ticketRepository.findAllByEventId(event.getId());
    }

    @Override
    public void cancelTicket(long ticketId) {
        log.info("deleting ticket by id " + ticketId);
        ticketRepository.deleteById(ticketId);
    }

    @Override
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

}
