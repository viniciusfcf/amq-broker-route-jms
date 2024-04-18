# amq-broker-route-jms

# Passo a passo
1. Instalar o Operador "Red Hat Integration - AMQ Broker for RHEL 8 (Multiarch)"
2. Criar project/namespace "my-broker"
3. Criar Broker utilizando arquivo: my_cluster.yaml
   1. Como acessar Console: Admin -> Networking -> Route: Acessar rota que contém no nome: wconsj
      1. Lembre que está como http, não https
      2. secret (artemis-broker-tls-credentials-secret):
         1.  user: <AMQ_CLUSTER_USER>
         2.  pass: <AMQ_CLUSTER_PASSWORD
4.  Criar namespace my-app
5.  Deploy do consumer.
    1.  Entrar na pasta jms-consumer
    2.  quarkus build -Dquarkus.kubernetes.deploy=true --no-tests -Dquarkus.kubernetes.namespace=my-app -Dquarkus.kubernetes-client.trust-certs=true


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