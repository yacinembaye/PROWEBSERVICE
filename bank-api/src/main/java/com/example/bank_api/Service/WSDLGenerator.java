package com.example.bank_api.Service;


import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

public class WSDLGenerator {
    public static void main(String[] args) throws Exception {
        // Créer le schéma XSD
        XsdSchema schema = new SimpleXsdSchema(new ClassPathResource("banking.xsd"));

        // Créer la définition WSDL
        DefaultWsdl11Definition wsdlDefinition = new DefaultWsdl11Definition();
        wsdlDefinition.setPortTypeName("BankingPort");
        wsdlDefinition.setLocationUri("/banking-soap-api");
        wsdlDefinition.setTargetNamespace("http://example.com/bank-api");
        wsdlDefinition.setSchema(schema);
        wsdlDefinition.setServiceName("BankingService");

        // Publier le WSDL
        wsdlDefinition.afterPropertiesSet();
    }
}