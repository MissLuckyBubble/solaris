package uni.fmi.Solaris.dto;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uni.fmi.Solaris.models.Product;
import uni.fmi.Solaris.models.Rating;
import uni.fmi.Solaris.models.User;

import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
public class RatingDTO extends BaseDTO<Rating>{
    private int value;
    private String comment;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;
    private long productId;
    private long userId;

    public RatingDTO(Rating rating){
        super(rating);
    }
    @Override
    protected void convertToDTO(Rating entity) {
        setComment(entity.getComment());
        setCreatedAt(entity.getCreatedAt());
        setId(entity.getId());
        setValue(entity.getValue());
        setUpdatedAt(entity.getUpdatedAt());
        setProductId(entity.getProduct().getId());
        setUserId(entity.getUser().getId());
    }
}
