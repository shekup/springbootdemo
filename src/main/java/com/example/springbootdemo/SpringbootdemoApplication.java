package com.example.springbootdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@SpringBootApplication
public class SpringbootdemoApplication {

    private static final Logger log = LoggerFactory.getLogger(SpringbootdemoApplication.class);


    public static void main(String[] args) {
		SpringApplication.run(SpringbootdemoApplication.class, args);
	}

    /**
     * On application start-up
     * a csv file is read.  CSV file has data - users
     * User objects are instantiated with data from CSV file (except user id - which is generated)
     * User objects are stored in repository that is they become Managed Entities
     * When objects are stored in repository, the ID is generated for the object.
     * @param repository
     * @return
     */
	@Bean
	public CommandLineRunner commandLineRunner(UserRepository repository) {
		return args -> {

			List<User> users = loadObjectList(User.class, "users.csv");
			for(User u: users){
				log.info(u.toString());
				repository.save(u);
			}
		};
	}

    /**
     * The method reads a csv file and instantiates the entities - User's.
     * Code uses Jackson - a simple parser
     * Jackson API - CSVMapper and others have built in method
     * for reading data from excel/csv and converting csv rows into Java objects.
     * The method of Jackson has some limitation
     * One limitation is the column header in CSV file should match the variable name in Java object
     *     *
     */
    public <T> List<T> loadObjectList(Class<T> type, String fileName) {
        try {
            CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader();
            CsvMapper mapper = new CsvMapper();
            File file = new ClassPathResource(fileName).getFile();
            MappingIterator<T> readValues =
                    mapper.reader(type).with(bootstrapSchema).readValues(file);
            return readValues.readAll();
        } catch (Exception e) {
            System.err.print("Error occurred while loading object list from file " + fileName);
            e.printStackTrace();
            log.error("Error occurred while loading object list from file " + fileName, e);
            return Collections.emptyList();
        }
    }

}
