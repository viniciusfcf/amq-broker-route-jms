package org.acme;

import java.time.LocalDateTime;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.apache.camel.builder.RouteBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import io.smallrye.common.annotation.Identifier;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Default;
import jakarta.enterprise.inject.Typed;
import jakarta.jms.ConnectionFactory;
import jakarta.ws.rs.Produces;

public class MyRouteBuilder extends RouteBuilder {

    @Produces
    @Typed({ ConnectionFactory.class })
    @ApplicationScoped
    @Identifier("externally-defined")
    @Default
    ActiveMQConnectionFactory externallyDefinedConnectionFactory(
            @ConfigProperty(name = "my.jms.server.externally-defined.url") String externallyDefinedUrl,
            @ConfigProperty(name = "my.jms.server.externally-defined.username") String username,
            @ConfigProperty(name = "my.jms.server.externally-defined.password") String password
            ) {
        return new ActiveMQConnectionFactory(externallyDefinedUrl, username, password);
    }

    @Override
    public void configure() throws Exception {
        from("sjms2:queue:{{my.jms.fila.entrada}}?clientId=my-jms-consumer&testConnectionOnStartup=false&subscriptionId=my-jms-consumer2")
                .log("Recebendo mensagem JMS: ${body}")
        ;

        //Aqui seria para enviar 1 msg por segundo para uma outra fila
        // from("timer:timerName")
        //     .setBody(simple("aaa"+LocalDateTime.now()))
        //     .to("sjms2:queue:FILA.SAIDA?clientId=my-jms-producer&testConnectionOnStartup=false&subscriptionId=my-jms-producer2");
    }

}