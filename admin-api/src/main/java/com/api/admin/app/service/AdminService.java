package com.api.admin.app.service;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.api.admin.app.config.ErrorCodeConfig;
import com.api.admin.app.exception.RestaurantDoNotExist;
import com.api.admin.app.model.FoodItem;
import com.api.admin.app.model.RestaurantImages;
import com.api.admin.app.model.RestaurantInfo;
import com.api.admin.app.repository.FoodItemRepo;
import com.api.admin.app.repository.RestaurantImagesRepo;
import com.api.admin.app.repository.RestaurantInfoRepo;

@Service
public class AdminService {
	private static final Logger logger = LoggerFactory.getLogger(AdminService.class);
	

    private final ErrorCodeConfig errorCodeConfig;

    @Autowired
    private RestaurantInfoRepo restaurantInfoRepo;
    @Autowired
    private RestaurantImagesRepo restaurantImagesRepo;

    @Autowired
    private FoodItemRepo foodItemRepo;
    
    @Autowired
    public AdminService(ErrorCodeConfig errorCodeConfig) {
        this.errorCodeConfig = errorCodeConfig;
    }

    public ResponseEntity<String> addRestaurant(Map entity) {
    	logger.info("********************************************************" + "success");

        Optional<RestaurantInfo> restaurant = restaurantInfoRepo
                .findByRestaurantnameAndRestaurantaddress((String) entity.get("restaurantname"),
                        (String) entity.get("address"));

        if (restaurant.isPresent()) {
            return ResponseEntity.badRequest().body("Please add for different address!");
        }

        RestaurantInfo restaurantInfo = new RestaurantInfo();
        restaurantInfo.setRestaurantname((String) entity.get("restaurantname"));
        restaurantInfo.setRestaurantaddress((String) entity.get("address"));
        restaurantInfo.setRestaurantrating((Double) entity.getOrDefault("restaurant_rating", 0.0));
        restaurantInfo.setNumofrating((Integer) entity.getOrDefault("numofrating", 1)); // Default to 1 if null

        // Get the image links from the entity and process them
        ArrayList<String> imageLinks = (ArrayList<String>) entity.get("restaurantimages");

        // Use stream to iterate over imageLinks
        if (imageLinks != null && !imageLinks.isEmpty()) {
            imageLinks.stream().forEach(link -> {
                RestaurantImages img = new RestaurantImages();
                img.setLink(link);
                img.setRestaurantInfo(restaurantInfo);
                restaurantInfo.getRestaurantimages().add(img);
            });
        }

        // Save the restaurantInfo object to the repository
        restaurantInfoRepo.save(restaurantInfo);

        // Return a success response
        return ResponseEntity.ok().body("Restaurant Added");
    }

    public ResponseEntity<String> editRestaurant(Map entity) {

        Integer resturantid = (Integer) entity.get("restaurantid");
        Optional<RestaurantInfo> restaurantInfo = restaurantInfoRepo.findById(resturantid);
        RestaurantInfo rest = restaurantInfo.get();

        Optional<RestaurantInfo> info = restaurantInfoRepo.findByRestaurantnameAndRestaurantaddress(
                (String) entity.get("restaurantname"), (String) entity.get("address"));
        // if (info.isPresent()) {
        // return ResponseEntity.ok().body("address");
        // }

        rest.setRestaurantname((String) entity.get("restaurantname"));
        rest.setRestaurantaddress((String) entity.get("address"));
        rest.setRestaurantrating((Double) entity.get("restaurant_rating"));
        rest.setNumofrating((Integer)entity.get("numofrating"));
        restaurantInfoRepo.save(rest);
        restaurantImagesRepo.deleteByRestaurantid(resturantid); // native query written in class

        ArrayList<String> imageLinks = (ArrayList) entity.get("restaurantimages");
        ListIterator<String> ll = imageLinks.listIterator();
        rest = restaurantInfo.get();
        for (int i = 0; i < imageLinks.size(); i++) {
        	logger.info("****************************************************" + imageLinks.get(i));
        }

        while (ll.hasNext()) {

            RestaurantImages img = new RestaurantImages();
            String link = ll.next();
            img.setLink(link);
            img.setRestaurantInfo(rest);
            rest.getRestaurantimages().add(img);
            restaurantInfoRepo.save(rest);
        }
        return ResponseEntity.ok().body("Restaurant updated successfully!");

    }

    public ResponseEntity<String> deleteRestaurant(Map entity) {

        restaurantInfoRepo.deleteById((Integer) entity.get("restaurantid"));
        return ResponseEntity.ok().body("Restaurant  deleted successfully!");
    }

    public ResponseEntity<String> addFoodItems(Map entity) {

        Optional<RestaurantInfo> restInfo = restaurantInfoRepo.findById((Integer) entity.get("restaurantid"));
        Optional<FoodItem> fooditem = foodItemRepo.findByRestaurantidAndFoodname((Integer) entity.get("restaurantid"),
                (String) entity.get("foodname"));
      
        if (restInfo.isEmpty()) {
            String errorMessage = errorCodeConfig.getErrorMessage("1001");
            throw new RestaurantDoNotExist(errorMessage+ "	"+(Integer) entity.get("restaurantid") + ". " );
        }
        if (fooditem.isPresent()) {
            return ResponseEntity.badRequest().body("This food item is already present!");
        }
        FoodItem fooditemInfo = new FoodItem();
        fooditemInfo.setFoodname((String) entity.get("foodname"));
        fooditemInfo.setDescription((String) entity.get("description"));
        fooditemInfo.setImage((String) entity.get("image"));
        fooditemInfo.setFooditemrating((Double) entity.get("food_item_rating"));
        fooditemInfo.setNumofrating((Integer) entity.get("numofrating"));
        fooditemInfo.setPrice(Integer.parseInt((String) entity.get("price")));
        foodItemRepo.save(fooditemInfo);
        fooditemInfo.setRestaurantInfo(restInfo.get());
        restInfo.get().getFoodItem().add(fooditemInfo);
        restaurantInfoRepo.save(restInfo.get());
        return ResponseEntity.ok().body("Added food items!");

    }

    public ResponseEntity<String> editFoodItems(Map entity) {

        Integer resturantid = (Integer) entity.get("restaurantid");
        Optional<RestaurantInfo> restaurantInfo = restaurantInfoRepo.findById(resturantid);
        RestaurantInfo rest = restaurantInfo.get();
        Integer fooditemid = (Integer) entity.get("fooditemid");
        Optional<FoodItem> fooditem1 = foodItemRepo.findByRestaurantidAndFoodname((Integer) entity.get("restaurantid"),
                (String) entity.get("foodname"));

		
		/*
		 * if (fooditem1.isPresent() && fooditem1.get().getFooditemid() != fooditemid) {
		 * String errorMessage = errorCodeConfig.getErrorMessage("1002");
		 * 
		 * throw new RestaurantDoNotExist(errorMessage+ "	"+(Integer)
		 * entity.get("foodname") + ". " ); }
		 */
		 
        Optional<FoodItem> fooditem = foodItemRepo.findById(fooditemid);
        FoodItem f = fooditem.get();
        f.setFoodname((String) entity.get("foodname"));
        f.setDescription((String) entity.get("description"));
        f.setImage((String) entity.get("image"));
        f.setPrice(Integer.parseInt((String)entity.get("price")));
        foodItemRepo.save(f);
        f.setRestaurantInfo(rest);
        restaurantInfoRepo.save(rest);
        return ResponseEntity.ok().body("Updated food items!");

    }

    public ResponseEntity<String> deleteFooditem(Map entity) {
        foodItemRepo.deleteById((Integer)entity.get("food_item_id"));
        return ResponseEntity.ok().body("Deleted food items!");
    }

}
