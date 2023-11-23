package uni.fmi.Solaris.rest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uni.fmi.Solaris.dto.BaseDTO;
import uni.fmi.Solaris.dto.RatingDTO;
import uni.fmi.Solaris.models.Product;
import uni.fmi.Solaris.models.Rating;
import uni.fmi.Solaris.models.User;
import uni.fmi.Solaris.services.ProductService;
import uni.fmi.Solaris.services.RatingService;
import uni.fmi.Solaris.services.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/rating")
public class RatingController {
    @Autowired
    private RatingService ratingService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<RatingDTO> findAll() {
        return ratingService.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/{ratingId}")
    public RatingDTO getBy(@PathVariable(name = "ratingId") long ratingId) {
        return convertToDTO(ratingService.getEntity(ratingId).get());
    }

    @PostMapping()
    public RatingDTO create(@RequestBody RatingDTO productDTO) {
        Rating rating = convertToModel(productDTO);
        rating.setId(null);
        return convertToDTO(ratingService.create(rating));
    }

    @PutMapping()
    public RatingDTO update(@RequestBody RatingDTO productDTO) {
        Rating rating = convertToModel(productDTO);
        return convertToDTO(ratingService.update(rating));
    }

    @DeleteMapping(path = "/{ratingId}")
    public ResponseEntity<String> remove(@PathVariable(name = "ratingId") long ratingId) {

        boolean isRemoved = ratingService.remove(ratingId);

        String deletedMessage = "Rating with id: '" + ratingId + "' was deleted!";
        String notDeletedMessage = "Rating with id: '" + ratingId + "' does not exists!";
        return isRemoved ?
                new ResponseEntity<>(deletedMessage, HttpStatusCode.valueOf(200)) :
                new ResponseEntity<>(notDeletedMessage, HttpStatusCode.valueOf(404));
    }

    @GetMapping(path = "/list")
    public List<RatingDTO> list(@RequestParam(name = "userId", required = false) Long userId,
                                @RequestParam(name = "productId", required = false) Long productId) {
        List<Rating> ratings = new ArrayList<>();
        if (productId != null && userId != null) {
            ratings = ratingService.findAllByProductIdAndUserId(productId, userId);
        }
        if (productId != null) {
            ratings = ratingService.findAllByProductId(productId);
        }
        if (userId != null) {
            ratings =  ratingService.findAllByUserId(userId);
        }
        return ratings.stream().map(this::convertToDTO).toList();
    }

    private RatingDTO convertToDTO(Rating rating) {
        RatingDTO ratingDTO = modelMapper.map(rating, RatingDTO.class);
        ratingDTO.setUserId(rating.getUser().getId());
        ratingDTO.setProductId(rating.getProduct().getId());
        return ratingDTO;
    }

    private Rating convertToModel(RatingDTO ratingDTO) {
        Rating rating = modelMapper.map(ratingDTO, Rating.class);
        User user = userService.getEntity(ratingDTO.getUserId())
                .orElseThrow(() -> new IllegalStateException("wrong user id"));
        Product product = productService.getEntity(ratingDTO.getProductId())
                .orElseThrow(() -> new IllegalStateException("wrong product id"));
        rating.setUser(user);
        rating.setProduct(product);
        return rating;
    }

}
