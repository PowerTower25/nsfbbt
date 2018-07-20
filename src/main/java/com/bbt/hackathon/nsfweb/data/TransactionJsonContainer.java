package com.bbt.hackathon.nsfweb.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;


@Component
public class TransactionJsonContainer {
    private static final Logger logger = LoggerFactory.getLogger(TransactionJsonContainer.class);
    private String transactionJson;
    @Autowired
    private ResourceLoader resourceLoader;

    @PostConstruct
    public void init() {
        logger.info("loading json string");
        try {
            Resource resource = resourceLoader.getResource("classpath:finalTransactionData.json");
            File f = resource.getFile();

            FileInputStream fis = new FileInputStream(f);
            byte[] data = new byte[(int) f.length()];
            fis.read(data);
            fis.close();

            transactionJson = new String(data, "UTF-8");
        } catch(Exception e) {
            logger.error("error loading json string", e);
        }
        logger.info("loaded json string");
    }

    public String getTransactionJson() {
        if (transactionJson == null) {
            init();
        }
        return transactionJson;
    }

    public void setTransactionJson(String transactionJson) {
        this.transactionJson = transactionJson;
    }
}
