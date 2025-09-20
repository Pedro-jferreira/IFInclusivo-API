package com.example.IfGoiano.IfCoders.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;

@Service
public class FirebaseInitializer {
    private static final Logger log = LoggerFactory.getLogger(FirebaseInitializer.class);
    @PostConstruct
    public void initialize() {
        try {
            String credentialsPath = System.getenv("GOOGLE_APPLICATION_CREDENTIALS");

            if (credentialsPath == null || credentialsPath.isEmpty()) {
                log.error("A variável de ambiente GOOGLE_APPLICATION_CREDENTIALS não está definida.");
                return;
            }
            FileInputStream serviceAccount = new FileInputStream(credentialsPath);
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();
            if (FirebaseApp.getApps().isEmpty()) { // Evita reinicialização
                FirebaseApp.initializeApp(options);
                log.info("Firebase Admin SDK inicializado com sucesso.");
            }
        } catch (IOException e) {
            log.error("Erro ao inicializar o Firebase Admin SDK", e);
        }
    }
}
