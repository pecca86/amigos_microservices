package com.pekka.clients.notification;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

//@FeignClient("NOTIFICATION") // When using Eureka
@FeignClient(
        name = "notification",
        url = "${clients.notification.url}"
)
public interface NotificationClient {

    @PostMapping(path = "api/v1/notifications")
    void notifyCustomer(NotificationRequest notificationRequest);

}
