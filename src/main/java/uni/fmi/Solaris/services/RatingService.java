package uni.fmi.Solaris.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import uni.fmi.Solaris.dto.BaseDTO;
import uni.fmi.Solaris.dto.RatingDTO;
import uni.fmi.Solaris.models.Product;
import uni.fmi.Solaris.models.Rating;
import uni.fmi.Solaris.models.User;
import uni.fmi.Solaris.repo.RatingRepo;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RatingService extends BaseService<Rating> {
    @Autowired
    private RatingRepo ratingRepo;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;

    @Override
    protected JpaRepository<Rating, Long> getRepo() {
        return ratingRepo;
    }

    @Override
    protected Rating convertDTOtoModel(BaseDTO<Rating> baseDTO) {
        RatingDTO ratingDTO = (RatingDTO) baseDTO;
        Rating rating = new Rating();
        mergeRating(ratingDTO, rating);
        return rating;
    }

    private void mergeRating(RatingDTO ratingDTO, Rating rating) {
        final Optional<User> user = userService.getEntity(ratingDTO.getUserId());
        if (user.isEmpty()) {
            throw new IllegalStateException("The owner of the rating does not exist!");
        }
        final Optional<Product> product = productService.getEntity(ratingDTO.getProductId());
        if (product.isEmpty()) {
            throw new IllegalStateException("The product does not exist!");
        }
        rating.setUser(user.get());
        rating.setProduct(product.get());
        rating.setComment(ratingDTO.getComment());
        rating.setValue(ratingDTO.getValue());
    }

    @Override
    protected void updateEntity(Rating entity, BaseDTO<Rating> categoryDTO) {
        RatingDTO ratingDTO = (RatingDTO) categoryDTO;
        mergeRating(ratingDTO, entity);
    }

    @Override
    protected BaseDTO<Rating> convert(Rating entity) {
        return new RatingDTO(entity);
    }

    public List<RatingDTO> findAllByUserId(long userId) {
        Optional<User> entity = userService.getEntity(userId);

/*        if (entity.isEmpty()) {
            throw new NoSuchElementException();
        }
        List<Rating> ratings = ratingRepo.findByUser(entity.get());
        final List<RatingDTO> result = new ArrayList<>();
        for(Rating rating: ratings){
            result.add(new RatingDTO(rating));
        }
        return result;*/


        return ratingRepo.findByUser(entity.orElseThrow())
                .stream().map(RatingDTO::new)
                .collect(Collectors.toList());
    }
    public List<RatingDTO> findAllByProductId(long productId) {
        Optional<Product> entity = productService.getEntity(productId);

        return ratingRepo.findByProduct(entity.orElseThrow())
                .stream().map(RatingDTO::new)
                .collect(Collectors.toList());
    }

    public List<RatingDTO> findAllByProductIdAndUserId(Long productId, Long userId) {
        Optional<Product> product = productService.getEntity(productId);
        Optional<User> user = userService.getEntity(userId);
        return ratingRepo.findByProductAndUser(product.orElseThrow(), user.orElseThrow())
                .stream().map(RatingDTO::new)
                .collect(Collectors.toList());
    }
}
