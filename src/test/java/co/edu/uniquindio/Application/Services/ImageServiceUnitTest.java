package co.edu.uniquindio.Application.Services;
import co.edu.uniquindio.Application.Services.ImageService;
import co.edu.uniquindio.Application.Services.impl.ImageServiceImpl;
import com.cloudinary.Cloudinary;
import com.cloudinary.Uploader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.mock.web.MockMultipartFile;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ImageServiceUnitTest {

    @Mock
    private Cloudinary cloudinary;

    @Mock
    private Uploader uploader;

    @InjectMocks
    private ImageServiceImpl imageService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(cloudinary.uploader()).thenReturn(uploader);
        imageService = new ImageServiceImpl(cloudinary);
    }

    @Test
    void upload_deberiaDevolverUrlSimulada() throws Exception {
        MockMultipartFile file = new MockMultipartFile("imagen", "imagen.jpg", "image/jpeg", new byte[]{1,2,3});
        Map<String, Object> resultadoSimulado = Map.of("url", "http://test.com/imagen.jpg");

        when(uploader.upload(any(), any())).thenReturn(resultadoSimulado);

        Map response = imageService.upload(file);

        assertEquals("http://test.com/imagen.jpg", response.get("url"));
        verify(uploader, times(1)).upload(any(), any());
    }

    @Test
    void delete_deberiaLlamarUploaderDestroy() throws Exception {
        Map<String, Object> resultadoSimulado = Map.of("result", "ok");
        when(uploader.destroy(anyString(), any())).thenReturn(resultadoSimulado);

        Map response = imageService.delete("imageId123");

        assertEquals("ok", response.get("result"));
        verify(uploader, times(1)).destroy(eq("imageId123"), any());
    }
}
