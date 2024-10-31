package pe.edu.utp.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * Configuración de Firestore para la conexión a la base de datos de Firebase.
 */
@Configuration
public class FirestoreConfig {

    /**
     * Configura y proporciona una instancia de Firestore.
     * 
     * @return una instancia configurada de Firestore
     * @throws IOException si ocurre un error al leer el archivo de credenciales
     */
    @Bean
    public Firestore firestore() throws IOException {
        FileInputStream serviceAccount = new FileInputStream("src/main/resources/home-rescue-a322e-firebase-adminsdk-f604w-092b0f6ec4.json");

        return FirestoreOptions.newBuilder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build()
                .getService();
    }
}
