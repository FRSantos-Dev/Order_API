package br.com.rocha.API_Pedido.service;

import jakarta.mail.internet.MimeMessage;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.logging.Logger;

public class SmtpEmailService extends AbstractEmailService {

    private static final Logger LOG = LoggerFactory.getLogger(MockEmailService.class);

    @Autowired
    private MailSender mailSender;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendEmail(SimpleMailMessage mailMessage) {
        LOG.info("Enviando de Email....");
        mailSender.send(mailMessage);
        LOG.info("E-mail enviado com sucesso!");
    }

    @Override
    public void sendHtmlEmail(MimeMessage mailMessage) {
        LOG.info("Enviando de Email....");
        javaMailSender.send(mailMessage);
        LOG.info("E-mail enviado com sucesso!");
    }

}
