package br.com.atom.api_nfce.configs;

import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;


@Configuration
@ConditionalOnProperty(name = "nfce.cert.enabled", havingValue = "true")
public class CertificadoConfig {

    @Value("${nfce.cert.path}")
    private String caminhoPfx;

    @Value("${nfce.cert.password}")
    private String senhaPfx;

    @Bean
    public KeyStore nfceKeyStore() throws Exception {
        KeyStore ks = KeyStore.getInstance("PKCS12");
        try (FileInputStream fis = new FileInputStream(caminhoPfx)) {
            ks.load(fis, senhaPfx.toCharArray());
        }
        return ks;
    }

    @Bean
    public PrivateKey privateKey(KeyStore nfceKeyStore) throws Exception {
        String alias = nfceKeyStore.aliases().nextElement();
        return (PrivateKey) nfceKeyStore.getKey(alias, senhaPfx.toCharArray());
    }

    @Bean
    public X509Certificate certificate(KeyStore nfceKeyStore) throws Exception {
        String alias = nfceKeyStore.aliases().nextElement();
        return (X509Certificate) nfceKeyStore.getCertificate(alias);
    }

    @Bean
    public SSLContext sslContext(KeyStore nfceKeyStore) throws Exception {
        KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        kmf.init(nfceKeyStore, senhaPfx.toCharArray());

        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(kmf.getKeyManagers(), null, new SecureRandom());
        return sslContext;
    }
}