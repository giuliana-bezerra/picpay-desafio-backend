package br.com.giulianabezerra.picpaydesafiobackend.notification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.com.giulianabezerra.picpaydesafiobackend.transaction.Transaction;

@Service
public class NotificationService {
  private static final Logger LOGGER = LoggerFactory.getLogger(NotificationService.class);
  private NotificationProducer notificationProducer;

  public NotificationService(NotificationProducer notificationProducer) {
    this.notificationProducer = notificationProducer;
  }

  public void notify(Transaction transaction) {
    LOGGER.info("notifying transaction {}...", transaction);

    notificationProducer.sendNotification(transaction);
  }
}
