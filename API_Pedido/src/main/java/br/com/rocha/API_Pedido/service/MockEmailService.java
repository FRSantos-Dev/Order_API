package br.com.rocha.API_Pedido.service;

import jakarta.mail.internet.MimeMessage;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

import java.util.logging.Logger;

public class MockEmailService extends AbstractEmailService {

    private static final Logger LOG = LoggerFactory.getLogger(MockEmailService.class);

    @Override
    public void sendEmail(SimpleMailMessage mailMessage) {
        LOG.info("Simulando Envio de Email....");
        LOG.info(mailMessage.toString());
        LOG.info("E-mail enviado com sucesso!");
    }

    @Override
    public void sendHtmlEmail(MimeMessage mailMessage) {
        LOG.info("Simulando Envio de Email....");
        LOG.info(mailMessage.toString());
        LOG.info("E-mail enviado com sucesso!");
    }

}
