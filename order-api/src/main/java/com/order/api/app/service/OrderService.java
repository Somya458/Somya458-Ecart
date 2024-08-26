package com.order.api.app.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.order.api.app.client.UserClient;
import com.order.api.app.exception.UserNotFoundException;
import com.order.api.app.model.FoodItem;
import com.order.api.app.model.FoodItemRating;
import com.order.api.app.model.FooditemDetails;
import com.order.api.app.model.OrderFoodItems;
import com.order.api.app.model.OrderInfo;
import com.order.api.app.model.RestaurantInfo;
import com.order.api.app.model.RestaurantRating;
import com.order.api.app.model.SearchFoodItem;
import com.order.api.app.model.UserInfo;
import com.order.api.app.repository.FoodItemRatingRepo;
import com.order.api.app.repository.FoodItemRepo;
import com.order.api.app.repository.OrderInfoRepo;
import com.order.api.app.repository.OrderRepository;
import com.order.api.app.repository.RestaurantInfoRepo;
import com.order.api.app.repository.RestaurantRatingRepo;

@Service
public class OrderService {
	private static final Logger logger = LoggerFactory.getLogger(OrderService.class);
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private RestaurantInfoRepo restaurantInfoRepo;
	@Autowired
	private UserClient userClient;
	@Autowired
	private OrderInfoRepo orderInfoRepo;
	@Autowired
	private FoodItemRepo foodItemRepo;

	@Autowired
	private FoodItemRatingRepo foodItemRatingRepo;

	@Autowired
	private RestaurantRatingRepo restaurantRatingRepo;

	public ResponseEntity<List<RestaurantInfo>> searchByName(Map<String, String> entity) {
	    String search = entity.get("search");

	    List<String> words = Arrays.stream(search.split(" "))
	                               .filter(word -> !word.trim().isEmpty())
	                               .collect(Collectors.toList());

	    List<RestaurantInfo> common = words.stream()
	        .flatMap(word -> orderRepository.findByRestaurantnameContaining(word, Sort.by(Sort.Direction.DESC, "restaurantrating")).stream())
	        .collect(Collectors.toList());

	    List<RestaurantInfo> restaurant = common.stream()
	        .distinct()
	        .collect(Collectors.toList());

	    return ResponseEntity.ok().body(restaurant);
	}


	public ResponseEntity<List<SearchFoodItem>> searchByFoodItem(Map<String, String> entity) {
	    String search = entity.get("search");

	    List<String> words = Arrays.stream(search.split(" "))
	                               .filter(word -> !word.trim().isEmpty())
	                               .collect(Collectors.toList());

	    List<FoodItem> foodItems = words.stream()
	        .flatMap(word -> foodItemRepo.findByFoodnameContaining(word, Sort.by(Sort.Direction.DESC, "fooditemrating")).stream())
	        .collect(Collectors.toList());

	    List<SearchFoodItem> sfoodItem = foodItems.stream()
	        .map(food -> {
	            RestaurantInfo rest = food.getRestaurantInfo();
	            logger.info("Restaurant Id" + ":" + rest.getRestaurantid());
	            return new SearchFoodItem(rest.getRestaurantid(), rest.getRestaurantname(), rest.getRestaurantaddress(), rest.getRestaurantrating(), food);
	        })
	        .collect(Collectors.toList());

	    List<SearchFoodItem> food = new ArrayList<>(new LinkedHashSet<>(sfoodItem));

	    return ResponseEntity.ok().body(food);
	}

	/*
	 * public ResponseEntity<String> placeOrder(Map entity) {
	 * 
	 * // Fetch the restaurant details Optional<RestaurantInfo> restaurantInfo =
	 * restaurantInfoRepo.findById((Integer) entity.get("restaurantid")); if
	 * (restaurantInfo.isEmpty()) { return
	 * ResponseEntity.status(HttpStatus.NOT_FOUND).body("Restaurant not found."); }
	 * RestaurantInfo rest = restaurantInfo.get(); logger.info("Restaurant Id" +
	 * "   :" + rest.getRestaurantid());
	 * logger.info("************Calling USER_API for userDetails******************"
	 * ); // Fetch the user details using Feign client UserInfo user =
	 * finduserInfo(entity); logger.info("User Details" + ":" +
	 * user.getPhonenumber()); // Validate food items ArrayList<String> fooditemid =
	 * (ArrayList<String>) entity.get("fooditemid"); for (String id : fooditemid) {
	 * Optional<FoodItem> foodItem = foodItemRepo.findById(Integer.parseInt(id)); if
	 * (foodItem.isEmpty()) { return
	 * ResponseEntity.status(HttpStatus.NOT_FOUND).body("Food item with ID " + id +
	 * " not found."); } }
	 * 
	 * // If all validations pass, place the order OrderInfo orderInfo = new
	 * OrderInfo(); orderInfo.setRestaurantid((Integer) entity.get("restaurantid"));
	 * orderInfo.setRestaurantname(rest.getRestaurantname());
	 * orderInfo.setUserid(user.getUserid()); orderInfo.setDeliveryaddress((String)
	 * entity.get("deliveryaddress")); orderInfo.setTotalamount((Integer)
	 * entity.get("totalamount")); orderInfoRepo.save(orderInfo);
	 * 
	 * // Initialize iterators for food items and their details ListIterator<String>
	 * ll = fooditemid.listIterator(); ArrayList<String> foodname =
	 * (ArrayList<String>) entity.get("foodname"); ListIterator<String> name =
	 * foodname.listIterator(); int amount = (Integer) entity.get("totalamount");
	 * ArrayList<Integer> quantity = (ArrayList<Integer>) entity.get("quantity");
	 * List<String> quantityStrings =
	 * quantity.stream().map(String::valueOf).collect(Collectors.toList());
	 * ListIterator<String> listQuantity = quantityStrings.listIterator();
	 * 
	 * // Process each food item and save the order while (ll.hasNext()) {
	 * OrderFoodItems orderFoodItems = new OrderFoodItems();
	 * 
	 * String s = ll.next(); orderFoodItems.setFooditemid(Integer.parseInt(s));
	 * orderFoodItems.setFoodname(name.next()); orderFoodItems.setAmount(amount); s
	 * = listQuantity.next(); orderFoodItems.setQuantity(Integer.parseInt(s));
	 * orderFoodItems.setOrderinfo(orderInfo);
	 * 
	 * orderInfo.getOrderFoodItems().add(orderFoodItems); }
	 * 
	 * // Save the final order with all food items orderInfoRepo.save(orderInfo);
	 * logger.info("*******************************" + orderInfo);
	 * 
	 * return ResponseEntity.ok().body("Order placed successfully!"); }
	 */
	public ResponseEntity<String> placeOrder(Map<String, Object> entity) {
	    // Fetch the restaurant details
	    Optional<RestaurantInfo> restaurantInfo = restaurantInfoRepo.findById((Integer) entity.get("restaurantid"));
	    if (!restaurantInfo.isPresent()) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Restaurant not found.");
	    }
	    RestaurantInfo rest = restaurantInfo.get();
	    logger.info("Restaurant Id: {}", rest.getRestaurantid());
	    logger.info("************Calling USER_API for userDetails******************");

	    // Fetch the user details using Feign client
	    UserInfo user = finduserInfo(entity);
	    logger.info("User Details: {}", user.getPhonenumber());

	    // Validate food items
	    List<String> foodItemIds = (List<String>) entity.get("fooditemid");
	    boolean allFoodItemsExist = foodItemIds.stream()
	        .map(id -> foodItemRepo.findById(Integer.parseInt(id)))
	        .allMatch(Optional::isPresent);

	    if (!allFoodItemsExist) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("One or more food items not found.");
	    }

	    // If all validations pass, place the order
	    OrderInfo orderInfo = new OrderInfo();
	    orderInfo.setRestaurantid((Integer) entity.get("restaurantid"));
	    orderInfo.setRestaurantname(rest.getRestaurantname());
	    orderInfo.setUserid(user.getUserid());
	    orderInfo.setDeliveryaddress((String) entity.get("deliveryaddress"));
	    orderInfo.setTotalamount((Integer) entity.get("totalamount"));
	    orderInfoRepo.save(orderInfo);

	    // Process each food item and save the order
	    List<String> foodNames = (List<String>) entity.get("foodname");
	    int amount = (Integer) entity.get("totalamount");
	    List<Integer> quantities = (List<Integer>) entity.get("quantity");

	    // Combine the lists and iterate
	    IntStream.range(0, foodItemIds.size())
	        .forEach(i -> {
	            OrderFoodItems orderFoodItems = new OrderFoodItems();
	            orderFoodItems.setFooditemid(Integer.parseInt(foodItemIds.get(i)));
	            orderFoodItems.setFoodname(foodNames.get(i));
	            orderFoodItems.setAmount(amount);
	            orderFoodItems.setQuantity(quantities.get(i));
	            orderFoodItems.setOrderinfo(orderInfo);

	            orderInfo.getOrderFoodItems().add(orderFoodItems);
	        });

	    // Save the final order with all food items
	    orderInfoRepo.save(orderInfo);
	    logger.info("Order placed: {}", orderInfo);

	    return ResponseEntity.ok().body("Order placed successfully!");
	}


	private UserInfo finduserInfo(Map entity) {
		UserInfo userInfo;

		Map<String, String> requestBody = new HashMap<>();
		requestBody.put("phonenumber", (String) entity.get("phonenumber"));

		try {
			// Fetch user details using the Feign client
			userInfo = userClient.getUserByPhoneNumber(requestBody);

			// Use user object as needed
		} catch (Exception e) {
			logger.error("Error fetching user details: ", e);
			throw new UserNotFoundException(
					"user not found with this pnone number  : " + (String) entity.get("phonenumber"));
		}

		return userInfo;
	}

	/*
	 * public ResponseEntity<String> rateOrder(Map entity) {
	 * Optional<RestaurantInfo> restaurantInfo =
	 * restaurantInfoRepo.findById((Integer) entity.get("restaurantid"));
	 * Optional<UserInfo> userInfo = Optional.ofNullable(finduserInfo(entity));//
	 * calling user-api for unserInfo Optional<OrderInfo> order =
	 * orderInfoRepo.findByUseridAndOrderid(userInfo.get().getUserid(), (Integer)
	 * entity.get("orderid")); OrderInfo orderInfo = order.get();
	 * orderInfo.setOrderflag(1); orderInfoRepo.save(orderInfo); RestaurantInfo rest
	 * = restaurantInfo.get(); int id = (Integer) (entity.get("restaurantrating"));
	 * Float f = new Float(id); float f1 = f.floatValue(); Float rating = 0f; if
	 * (rest.getRestaurantrating() == 0.0) { // rating =
	 * (Float)(entity.get("restaurantrating")); rating = f1;
	 * rest.setRestaurantrating(rating); rest.setNumofrating(rest.getNumofrating() +
	 * 1); restaurantInfoRepo.save(rest);
	 * 
	 * } else {
	 * 
	 * // rating = (float) (((rest.getRestaurantrating() * rest.getNumofrating()) //
	 * + (Double) entity.get("restaurantrating")) / (rest.getNumofrating() + 1));
	 * /////////////////// rating = (float) (((rest.getRestaurantrating() *
	 * rest.getNumofrating()) + f1) / (rest.getNumofrating() + 1));
	 * rest.setRestaurantrating(rating); rest.setNumofrating(rest.getNumofrating() +
	 * 1); restaurantInfoRepo.save(rest); } RestaurantRating restaurantRating = new
	 * RestaurantRating();
	 * 
	 * restaurantRating.setName(userInfo.get().getName());
	 * restaurantRating.setRestaurantid((Integer) entity.get("restaurantid"));
	 * restaurantRating.setRestaurantname(rest.getRestaurantname());
	 * restaurantRating.setRestaurantrating(rest.getRestaurantrating());
	 * restaurantRating.setRestaurantreview((String)
	 * entity.get("restaurantreview")); restaurantRatingRepo.save(restaurantRating);
	 * 
	 * ArrayList<String> fooditemid = (ArrayList) entity.get("fooditemid");
	 * 
	 * if (fooditemid.isEmpty()) { return ResponseEntity.ok().body("success"); }
	 * else { ListIterator<String> ll = fooditemid.listIterator();
	 * 
	 * ArrayList<String> fooditemrating = (ArrayList) entity.get("fooditemrating");
	 * ListIterator<String> ratingFood = fooditemrating.listIterator();
	 * 
	 * ArrayList<String> fooditemreview = (ArrayList) entity.get("fooditemreview");
	 * ListIterator<String> review = fooditemreview.listIterator();
	 * 
	 * ArrayList<String> numofrating = (ArrayList<String>)
	 * entity.get("numofrating"); ListIterator<String> nrating;
	 * 
	 * // Check if numofrating is null, and if so, assign a default value of "1" if
	 * (numofrating != null) { nrating = numofrating.listIterator(); } else { //
	 * Create a new ArrayList with a single element "1" if numofrating is null
	 * numofrating = new ArrayList<>(Arrays.asList("1")); nrating =
	 * numofrating.listIterator(); }
	 * 
	 * while (ll.hasNext()) {
	 * 
	 * FoodItemRating foodrating = new FoodItemRating();
	 * foodrating.setName(userInfo.get().getName());
	 * foodrating.setRestaurantid((Integer) entity.get("restaurantid"));
	 * foodrating.setRestaurantname(rest.getRestaurantname());
	 * 
	 * String s = ll.next(); Optional<FoodItem> food =
	 * foodItemRepo.findById(Integer.parseInt(s)); FoodItem foodItem = food.get();
	 * foodrating.setFooditemid(Integer.parseInt(s));
	 * foodrating.setFoodname(foodItem.getFoodname());
	 * 
	 * String rate = ratingFood.next(); String nRate = nrating.next();
	 * logger.info("########################" + rate);
	 * logger.info("***************************" + Double.parseDouble(rate));
	 * 
	 * foodrating.setFooditemrating(Double.parseDouble(rate)); String foodReview =
	 * review.next(); foodrating.setFooditemreview(foodReview);
	 * foodItemRatingRepo.save(foodrating); Double foodRating = 0.0;
	 * 
	 * if (foodItem.getFooditemrating() == null) {
	 * 
	 * foodItem.setFooditemrating(Double.parseDouble(rate)); if
	 * (foodItem.getNumofrating() == null) {
	 * foodItem.setNumofrating(Integer.parseInt(nRate)); }
	 * foodItemRepo.save(foodItem);
	 * 
	 * } else {
	 * 
	 * foodRating = ((foodItem.getFooditemrating() * foodItem.getNumofrating()) +
	 * Double.parseDouble(rate)) / (foodItem.getNumofrating() + 1);
	 * 
	 * foodItem.setFooditemrating(foodRating);
	 * foodItem.setNumofrating(foodItem.getNumofrating() + 1);
	 * foodItemRepo.save(foodItem); }
	 * 
	 * }
	 * 
	 * }
	 * 
	 * return ResponseEntity.ok().body("Rating Added"); }
	 */
	public ResponseEntity<String> rateOrder(Map<String, Object> entity) {
	    Integer restaurantId = (Integer) entity.get("restaurantid");
	    Integer orderId = (Integer) entity.get("orderid");
	    Integer restaurantRating = (Integer) entity.get("restaurantrating");
	    String restaurantReview = (String) entity.get("restaurantreview");
	    
	    // Fetch restaurant info
	    RestaurantInfo rest = restaurantInfoRepo.findById(restaurantId).orElseThrow(() -> new RuntimeException("Restaurant not found"));

	    // Fetch user info
	    UserInfo user = Optional.ofNullable(finduserInfo(entity)).orElseThrow(() -> new RuntimeException("User not found"));
	    Integer userId = user.getUserid();

	    // Fetch order info
	    OrderInfo orderInfo = orderInfoRepo.findByUseridAndOrderid(userId, orderId)
	            .orElseThrow(() -> new RuntimeException("Order not found"));
	    
	    orderInfo.setOrderflag(1);
	    orderInfoRepo.save(orderInfo);

	    // Update restaurant rating
	    float newRating = (rest.getRestaurantrating() * rest.getNumofrating() + restaurantRating) / (rest.getNumofrating() + 1);
	    rest.setRestaurantrating(newRating);
	    rest.setNumofrating(rest.getNumofrating() + 1);
	    restaurantInfoRepo.save(rest);

	    // Save restaurant rating
	    RestaurantRating restaurantRatingObj = new RestaurantRating();
	    restaurantRatingObj.setName(user.getName());
	    restaurantRatingObj.setRestaurantid(restaurantId);
	    restaurantRatingObj.setRestaurantname(rest.getRestaurantname());
	    restaurantRatingObj.setRestaurantrating(newRating);
	    restaurantRatingObj.setRestaurantreview(restaurantReview);
	    restaurantRatingRepo.save(restaurantRatingObj);

	    // Handle food item ratings
	    List<String> foodItemIds = (List<String>) entity.get("fooditemid");
	    if (foodItemIds.isEmpty()) {
	        return ResponseEntity.ok().body("Success");
	    }

	    List<String> foodItemRatings = (List<String>) entity.get("fooditemrating");
	    List<String> foodItemReviews = (List<String>) entity.get("fooditemreview");
	    List<String> numOfRatings = (List<String>) entity.get("numofrating");
	    
	    if (numOfRatings == null) {
	        numOfRatings = Collections.singletonList("1");
	    }

	    IntStream.range(0, foodItemIds.size()).forEach(i -> {
	        Integer foodItemId = Integer.parseInt(foodItemIds.get(i));
	        FoodItem foodItem = foodItemRepo.findById(foodItemId).orElseThrow(() -> new RuntimeException("Food item not found"));
	        
	        FoodItemRating foodItemRating = new FoodItemRating();
	        foodItemRating.setName(user.getName());
	        foodItemRating.setRestaurantid(restaurantId);
	        foodItemRating.setRestaurantname(rest.getRestaurantname());
	        foodItemRating.setFooditemid(foodItemId);
	        foodItemRating.setFoodname(foodItem.getFoodname());
	        foodItemRating.setFooditemrating(Double.parseDouble(foodItemRatings.get(i)));
	        foodItemRating.setFooditemreview(foodItemReviews.get(i));
	        foodItemRatingRepo.save(foodItemRating);
	        
	        // Update food item rating
	        double foodRating = (foodItem.getFooditemrating() != null ? foodItem.getFooditemrating() : 0.0);
	        int numRatings = (foodItem.getNumofrating() != null ? foodItem.getNumofrating() : 0);
	        double updatedRating = (foodRating * numRatings + Double.parseDouble(foodItemRatings.get(i))) / (numRatings + 1);
	        foodItem.setFooditemrating(updatedRating);
	        foodItem.setNumofrating(numRatings + 1);
	        foodItemRepo.save(foodItem);
	    });

	    return ResponseEntity.ok().body("Rating Added");
	}

	/*
	 * public ResponseEntity<List<FooditemDetails>> getAllFoodItems() {
	 * List<FoodItem> foodItem = foodItemRepo.findAll(); ListIterator<FoodItem> itr
	 * = foodItem.listIterator();
	 * 
	 * List<FooditemDetails> fid = new ArrayList<FooditemDetails>();
	 * 
	 * while (itr.hasNext()) { FoodItem fooditem = itr.next();
	 * 
	 * RestaurantInfo ri = fooditem.getRestaurantInfo(); FooditemDetails fs = new
	 * FooditemDetails(ri.getRestaurantid(), fooditem, ri.getRestaurantname());
	 * fid.add(fs);
	 * 
	 * } return ResponseEntity.ok().body(fid); }
	 */
	//Java8 conversion
	public ResponseEntity<List<FooditemDetails>> getAllFoodItems() {
	    return ResponseEntity.ok().body(
	        foodItemRepo.findAll().stream()
	            .map(foodItem -> {
	                RestaurantInfo ri = foodItem.getRestaurantInfo();
	                return new FooditemDetails(ri.getRestaurantid(), foodItem, ri.getRestaurantname());
	            })
	            .collect(Collectors.toList())
	    );
	}


	public ResponseEntity<List<OrderInfo>> getAllOrderDetails(Map entity) {
		// Optional<UserInfo> userInfo = userInfoRepo.findByPhonenumber((String)
		// entity.get("phonenumber"));
		/*Optional<UserInfo> userInfo = Optional.ofNullable(finduserInfo(entity));
		UserInfo user = userInfo.get();
		int id = user.getUserid();
		List<OrderInfo> oi = orderInfoRepo.findAllByUserid(id);
		if (oi.isEmpty()) {
			return ResponseEntity.ok().body(oi);
		}
		return ResponseEntity.ok().body(oi);
	}*/// java 8 conversion
		return Optional.ofNullable(finduserInfo(entity))
	               .map(user -> orderInfoRepo.findAllByUserid(user.getUserid()))
	               .map(oi -> ResponseEntity.ok().body(oi))
	               .orElseGet(() -> ResponseEntity.ok().body(Collections.emptyList()));
	}

	
}
