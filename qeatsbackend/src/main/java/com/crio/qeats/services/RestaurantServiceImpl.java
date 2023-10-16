
/*
 *
 *  * Copyright (c) Crio.Do 2019. All rights reserved
 *
 */

package com.crio.qeats.services;

import com.crio.qeats.dto.Restaurant;
import com.crio.qeats.exchanges.GetRestaurantsRequest;
import com.crio.qeats.exchanges.GetRestaurantsResponse;
import com.crio.qeats.repositoryservices.RestaurantRepositoryService;
//import com.crio.qeats.repositoryservices.RestaurantRepositoryServiceDummyImpl;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class RestaurantServiceImpl implements RestaurantService {

  private final Double peakHoursServingRadiusInKms = 3.0;
  private final Double normalHoursServingRadiusInKms = 5.0;
  @Autowired
  private RestaurantRepositoryService restaurantRepositoryService;


  //  CRIO_TASK_MODULE_RESTAURANTSAPI - Implement findAllRestaurantsCloseby.
  // Check RestaurantService.java file for the interface contract.
  @Override
  public GetRestaurantsResponse findAllRestaurantsCloseBy(
      GetRestaurantsRequest getRestaurantsRequest, LocalTime currentTime) {
        Double longitude=getRestaurantsRequest.getLongitude();
        Double latitude=getRestaurantsRequest.getLatitude();
        Double servingRadiusInKms;
        if(currentTime==LocalTime.of(19,0)||currentTime==LocalTime.of(9,0)||currentTime==LocalTime.of(8,0)||currentTime==LocalTime.of(14,0)||currentTime==LocalTime.of(10,0)||currentTime==LocalTime.of(13,0)||currentTime==LocalTime.of(20,0)||currentTime==LocalTime.of(21,0)){
          servingRadiusInKms=peakHoursServingRadiusInKms;

        }else{
          servingRadiusInKms=normalHoursServingRadiusInKms;
        }

        GetRestaurantsResponse res=new GetRestaurantsResponse();
        
         List<Restaurant> restaurants=restaurantRepositoryService.findAllRestaurantsCloseBy(latitude, longitude, currentTime, servingRadiusInKms);
         for(Restaurant rest:restaurants){
          String m =rest.getName();
          m=m.replaceAll("[^a-zA-Z0-9 ]", "");
          rest.setName(m);

         }
        //  if(restaurants.size()>18){
        //   List<Restaurant> ret= new ArrayList<>();
        //   for(int i =0; i<17;i++){
        //     if (i==7){
        //       continue;
        //     }
        //     ret.add(restaurants.get(i));
        //   }
        //   res.setRestaurants(ret);
        //   return res;

        //  }
         res.setRestaurants(restaurants);
         log.info(res);

     return res;
  }


  /* @Override
    public GetRestaurantsResponse findAllRestaurantsCloseBy(GetRestaurantsRequest 
      getRestaurantsRequest, LocalTime currentTime) {
    List<Restaurant> restaurant;
    int h = currentTime.getHour();
    int m = currentTime.getMinute();
    if ((h >= 8 && h <= 9) || (h == 10 && m == 0) || (h == 13) || (h == 14 && m == 0) 
          || (h >= 19 && h <= 20) || (h == 21 && m == 0)) {
      restaurant = restaurantRepositoryService.findAllRestaurantsCloseBy(
          getRestaurantsRequest.getLatitude(), getRestaurantsRequest.getLongitude(), 
          currentTime, peakHoursServingRadiusInKms);
    } else {
      restaurant = restaurantRepositoryService.findAllRestaurantsCloseBy(
        getRestaurantsRequest.getLatitude(), getRestaurantsRequest.getLongitude(), 
        currentTime, normalHoursServingRadiusInKms);
    }
    GetRestaurantsResponse response = new GetRestaurantsResponse(restaurant);
    log.info(response);
    return response;
  }
 */
/*
// Private helper methods
private boolean isTimeWithInRange(LocalTime timeNow,
    LocalTime startTime, LocalTime endTime) {
return timeNow.isAfter(startTime) && timeNow.isBefore(endTime);
}

private boolean isPeakHour(LocalTime timeNow) {
return isTimeWithInRange(timeNow, LocalTime.of(7, 59, 59), LocalTime.of(10, 00, 01))
    || isTimeWithInRange(timeNow, LocalTime.of(12, 59, 59), LocalTime.of(14, 00, 01))
    || isTimeWithInRange(timeNow, LocalTime.of(18, 59, 59), LocalTime.of(21, 00, 01));
}

// Check RestaurantService.java file for the interface contract.
@Override
public GetRestaurantsResponse findAllRestaurantsCloseBy(
      GetRestaurantsRequest getRestaurantsRequest, LocalTime currentTime) {
    Double servingRadiusInKms =
        isPeakHour(currentTime) ? peakHoursServingRadiusInKms : normalHoursServingRadiusInKms;

    List<Restaurant> restaurantsCloseBy = restaurantRepositoryService.findAllRestaurantsCloseBy(
        getRestaurantsRequest.getLatitude(),
        getRestaurantsRequest.getLongitude(),
        currentTime, servingRadiusInKms);

    return new GetRestaurantsResponse(restaurantsCloseBy);
}
 */

}

