package fr.studi.stock.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.studi.stock.manager.LogStockManager;
import fr.studi.stock.pojo.LogStock;
import fr.studi.stock.service.LogStockService;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MqttConfig {

    @Value("${mqtt.broker.url}")
    private String brockerURL;

    @Value("${mqtt.client.id}")
    private String clientId;

    @Value("${mqtt.username}")
    private String username;

    @Value("${mqtt.password}")
    private String password;

    @Autowired
    private LogStockManager logStockManager;

    @Bean
    public MqttClient mqttClient() throws MqttException {

        //instanciation du client
        MqttClient mqttClient = new MqttClient(brockerURL, clientId, new MemoryPersistence());

        //argument supplementaire
        MqttConnectOptions options = new MqttConnectOptions();
        options.setUserName(username);
        options.setPassword(password.toCharArray());
        mqttClient.connect(options);

        //callback
        mqttClient.setCallback(new MqttCallback() {

            @Override
            public void connectionLost(Throwable throwable) {
                System.out.println("Connexion perdue : " + throwable.getMessage());
            }

            @Override
            public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
                System.out.println("Message reçu du topic : " + topic);
                String payload = new String(mqttMessage.getPayload()); // bonjour
                System.out.println(payload);
                //serialiser le message reçu en objet Java
                ObjectMapper objectMapper = new ObjectMapper();
                LogStock logStock  = objectMapper.readValue(payload, LogStock.class);

                logStockManager.processLog(logStock);
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
                System.out.println("Message livré : " + iMqttDeliveryToken.getMessageId());
            }
        });
        mqttClient.subscribe("produit");
        return mqttClient;
    }

}
