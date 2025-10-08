package co.edu.uniquindio.Application.Services;

import co.edu.uniquindio.Application.DTO.EmailDTO;

public interface EmailService {
    void sendMail(EmailDTO emailDTO) throws Exception;

}
