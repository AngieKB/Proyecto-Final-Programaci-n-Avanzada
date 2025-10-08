package co.edu.uniquindio.Application.Services.impl;

import co.edu.uniquindio.Application.DTO.EmailDTO;
import co.edu.uniquindio.Application.Services.EmailService;
import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.api.mailer.config.TransportStrategy;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    @Override
    @Async
    public void sendMail(EmailDTO emailDTO) throws Exception {
        Email email = EmailBuilder.startingBlank()
                .from("correosappbooking@gmail.com")
                .to(emailDTO.recipient())
                .withSubject(emailDTO.subject())
                .withPlainText(emailDTO.body())
                .buildEmail();

        try (Mailer mailer = MailerBuilder
                .withSMTPServer("smtp.gmail.com", 587, "correosappbooking@gmail.com", "kflh dabq wpvj gntg")
                .withTransportStrategy(TransportStrategy.SMTP_TLS)
                .withDebugLogging(true)
                .buildMailer()) {

            mailer.sendMail(email);
        }
    }
}

