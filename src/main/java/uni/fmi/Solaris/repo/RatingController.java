package uni.fmi.Solaris.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uni.fmi.Solaris.dto.BaseDTO;
import uni.fmi.Solaris.dto.RatingDTO;
import uni.fmi.Solaris.models.Rating;
import uni.fmi.Solaris.services.RatingService;

import java.util.List;

@RestController
@RequestMapping(path = "/rating")
public class RatingController {
    @Autowired
    private RatingService ratingService;

    @GetMapping
    public List<BaseDTO<Rating>> findAll() {
        return ratingService.getAll();
    }

    @GetMapping(path = "/{ratingId}")
    public BaseDTO<Rating> getBy(@PathVariable(name = "ratingId") long ratingId) {
        return ratingService.getBy(ratingId);
    }

    @PostMapping()
    public BaseDTO<Rating> create(@RequestBody RatingDTO productDTO) {
        return ratingService.create(productDTO);
    }

    @PutMapping()
    public BaseDTO<Rating> update(@RequestBody RatingDTO productDTO) {
        return ratingService.update(productDTO);
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
        if (productId != null && userId != null) {
            ratingService.findAllByProductIdAndUserId(productId, userId);
        }
        if (productId != null) {
            return ratingService.findAllByProductId(productId);
        }
        if (userId != null) {
            return ratingService.findAllByUserId(userId);
        }
        return null;
    }

}
