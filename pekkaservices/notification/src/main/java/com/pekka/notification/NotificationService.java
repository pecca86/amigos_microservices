package com.pekka.notification;

import com.pekka.clients.notification.NotificationRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class NotificationService {

    private final NotificationRepository notificationRepository;

    @Autowired
    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public void notifyCustomer(NotificationRequest request) {
        Notification notification = notificationRepository.save(
                Notification.builder()
                        .toCustomerId(request.toCustomerId())
                        .toCustomerEmail(request.toCustomerEmail())
                        .message(request.message())
                        .sender("Customer Registration Service")
                        .sentAt(LocalDateTime.now())
                        .build()
        );

        notificationRepository.saveAndFlush(notification);
        log.info("Notified customer with id {}", notification.getToCustomerId());

    }
}
