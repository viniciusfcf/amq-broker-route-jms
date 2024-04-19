# amq-broker-route-jms

# Pré requisitos
1. Openshift >=4.11.36
2. Java >= 11

# Passo a passo
1. Instalar o Operador "Red Hat Integration - AMQ Broker for RHEL 8 (Multiarch)"
2. Criar project/namespace "my-broker"
3. Criar certificados (Documentação oficial: 4.10.2.2. Configuring one-way TLS):
   1. ```keytool -genkey -alias broker -keyalg RSA -keystore ~/broker.ks```
   2. ```keytool -export -alias broker -keystore ~/broker.ks -file ~/broker_cert.pem```
   3. ```keytool -import -alias broker -keystore ~/client.ts -file ~/broker_cert.pem```
   4. ```oc create secret -n my-broker generic my-tls-secret --from-file=broker.ks=~/broker.ks --from-file=client.ts=~/client.ts --from-literal=keyStorePassword=<password> --from-literal=trustStorePassword=<password>```
4. Criar Broker: ```oc apply -n my-broker -f files/artemis/my_cluster.yaml```
   1. Como acessar Console: Admin -> Networking -> Route: Acessar rota que contém no nome: wconsj
      1. Lembre que está como http, não https
      2. Secret `artemis-broker-tls-credentials-secret`:
         1.  user: <AMQ_CLUSTER_USER>
         2.  pass: <AMQ_CLUSTER_PASSWORD
5.  Criar o address utilizando arquivo: ```files/address/address_queue.yaml```
6.  O código de exemplo para enviar/receber a documentação está na pasta `qpid-jms-examples`
    1.  Atualizar o arquivo `jndi.properties`, linha 26, como caminho do arquivo `client.ts` gerado anteriormente
    2.  Compilar: ```mvn clean package dependency:copy-dependencies -DincludeScope=runtime -DskipTests```
    3.  Enviar mensagens: ```java -cp "target/classes/:target/dependency/*" org.apache.qpid.jms.example.Sender```
    4.  Ler mensagens: ```java -cp "target/classes/:target/dependency/*" org.apache.qpid.jms.example.Receiver```


# Algumas fontes

Como instalar AMQ Broker no OpenShift: https://access.redhat.com/documentation/en-us/red_hat_amq_broker/7.11/html/deploying_amq_broker_on_openshift/index#doc-wrapper

# Overview HA 
https://access.redhat.com/documentation/en-us/red_hat_amq_broker/7.11/html/deploying_amq_broker_on_openshift/assembly-br-planning-a-deployment_broker-ocp#con-br-high-availability_broker-ocp

# Clientes JMS

https://access.redhat.com/documentation/en-us/red_hat_amq_clients/2023.q4

## Examples
https://access.redhat.com/documentation/en-us/red_hat_amq_core_protocol_jms/7.11/html/using_amq_core_protocol_jms/installation#installing_the_examples

https://access.redhat.com/documentation/en-us/red_hat_amq_core_protocol_jms/7.11/html/using_amq_core_protocol_jms/getting_started#running_your_first_example

# Arquivos para download
https://access.redhat.com/jbossnetwork/restricted/listSoftware.html?product=jboss.amq.broker&downloadType=distributions&version=7.11.6
