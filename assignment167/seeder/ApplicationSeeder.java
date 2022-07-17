package com.example.assignment167.seeder;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.logging.Level;
import java.util.logging.Logger;

@Component
@Slf4j
public class ApplicationSeeder implements CommandLineRunner {
    private boolean createNewSeed = true;
    private static Logger logger = Logger.getLogger(ApplicationSeeder.class.getSimpleName());
    @Autowired
    ProductSeeder productSeeder;
    @Autowired
    CategorySeeder categorySeeder;

    @Autowired
    OrderSeeder orderSeeder;
    @Override
    public void run(String... args) throws Exception{
        if (createNewSeed){
            logger.log(Level.SEVERE, "Start seeding");
            categorySeeder.generate();
            productSeeder.generate();
            orderSeeder.generate();
        }
    }
}
