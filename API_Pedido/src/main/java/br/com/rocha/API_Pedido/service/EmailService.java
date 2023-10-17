package br.com.rocha.API_Pedido.service;

import br.com.rocha.API_Pedido.domain.entity.Cliente;
import br.com.rocha.API_Pedido.domain.entity.Pedido;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.SimpleMailMessage;

public interface EmailService {

    void sendOrderConfirmationEmail(Pedido pedido);

    void sendEmail(SimpleMailMessage mailMessage);

    void sendOrderconfirmationHtmlEmail(Pedido pedido);

    void sendHtmlEmail(MimeMessage mailMessage);

    void sendNewPassword(Cliente cliente, String newPass);

}