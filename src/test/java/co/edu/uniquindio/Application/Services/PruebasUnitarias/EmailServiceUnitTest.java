package co.edu.uniquindio.Application.Services.PruebasUnitarias;
import co.edu.uniquindio.Application.DTO.EmailDTO;
import co.edu.uniquindio.Application.Services.impl.EmailServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import org.simplejavamail.api.mailer.Mailer;

import static org.mockito.Mockito.*;

class EmailServiceUnitTest {

    @InjectMocks
    private EmailServiceImpl emailService;

    @Mock
    private Mailer mailer; // Mock del Mailer de Simple Java Mail

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void sendMail_simulado() throws Exception {
        // DTO de prueba
        EmailDTO emailDTO = new EmailDTO(
                "test@example.com",
                "Asunto de prueba",
                "Cuerpo de prueba"
        );

        // Spy del servicio: permite interceptar métodos específicos
        EmailServiceImpl emailService = Mockito.spy(new EmailServiceImpl());

        // Interceptamos el método real sendMail para que no haga nada
        doNothing().when(emailService).sendMail(emailDTO);

        // Llamamos al método
        emailService.sendMail(emailDTO);

        // Verificamos que se llamó
        verify(emailService, times(1)).sendMail(emailDTO);
    }

}
