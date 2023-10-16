
/*
 *
 *  * Copyright (c) Crio.Do 2019. All rights reserved
 *
 */

package com.crio.qeats;

import com.crio.qeats.globals.GlobalConstants;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import com.crio.qeats.log.UncaughtExceptionHandler;

@SpringBootApplication
@Log4j2
public class QEatsApplication {

  public static void main(String[] args) {
    SpringApplication.run(QEatsApplication.class, args);

    // TIP:MODULE_RESTAPI: If your server starts successfully,
    // you can find the following message in the logs.
   //commented by me log.info("Congrats! Your QEatsApplication server has started");
    log.info("Congrats! Your QEatsApplication server has started");
  }
/*Bake-a-boo
China Gatherings
Crazy Bhukkhad
Deez Biryani & Kebabs
Happy Beings
InstaDozza
Mom's Kitchen
The Baker's Bar  ... Bakery & Café
Veg Ex
Volt - By Plan B
ZASTY
Aahar
Chicken Bytes
Punjabi Tadka
Coldpress Company
Crudo Juicery
Kashmiri Kitchen
Kebab Gali
Monster's Café */
  /**
   * Fetches a ModelMapper instance.
   *
   * @return ModelMapper
   */
  @Bean // Want a new obj every time
  @Scope("prototype")
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }

  

}
