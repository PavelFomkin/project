package com.historicalclub.service;

import com.historicalclub.entity.Order;
import com.historicalclub.entity.Tour;
import java.time.format.DateTimeFormatter;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

  private final JavaMailSender emailSender;

  @Autowired
  public EmailService(JavaMailSender emailSender) {
    this.emailSender = emailSender;
  }

  private void send(String to, String subject, String text) {
    MimeMessage message = this.emailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(message);
    try {
      helper.setTo(to);
      helper.setSubject(subject);
      helper.setText(text);
      this.emailSender.send(message);
    } catch (MessagingException messageException) {
      throw new RuntimeException(messageException);
    }
  }

  public void sendBookingEmail(Order order, Tour tour){
    String title = "Бронирование экскурсии";
    String date = order.getDate().format(DateTimeFormatter.ofPattern("H:mm d/MM/yyyy"));
    String message = String.format(
        "Здравствуйте %s,\n"+
        "Вы забронировали экскурсию %s.\n"+
        "Начало экскурсии %s, место встречи - %s.\n",
        order.getName(), tour.getTitle(), date, tour.getVenue());

    send(order.getEmail(), title, message);
  }
}


