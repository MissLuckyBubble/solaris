package uni.fmi.Solaris.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDTO extends BaseDTO {
    private String text;
    private int rating;
    private long productId;
    private long articleId;
    private long ownerId;
}
