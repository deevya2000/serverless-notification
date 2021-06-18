# notification-service

Notification service is part of Product Catalog application. This service is subscribed to kafka topic and notifies user on change in product price.

This project uses Quarkus, the Supersonic Subatomic Java Framework (Reference: https://quarkus.io/), REST Easy for APIs and Kafka as messaging service. 

## Application Architecture
Catalog service holds information of Products and Product Categories. When price of an item in catalog service is updated the data is updated in postgresql database. This also sends a message to kafka topic product-price-updated. Notification-service, which is subscribed to this topic, sends an email notification to users interested in the product via mailer service.

{% include image.html file="/src/images/serverless-architecture.png" alt="Serverless application architecture" caption="" %}

## Deploying the application to openshift
Please refer to openshift-pipeline folder for jenkins file and readme text file with instructions in order to deploy the service on openshift.

## Related guides
Please refer to playbook of referece implementation for more details https://pages.github.ibm.com/100mc/foundational-offering-playbook/ref_impl_serverless_with_quarkus.html
