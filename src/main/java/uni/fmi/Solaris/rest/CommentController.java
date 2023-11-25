package uni.fmi.Solaris.rest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uni.fmi.Solaris.dto.CommentDTO;
import uni.fmi.Solaris.models.Article;
import uni.fmi.Solaris.models.Comment;
import uni.fmi.Solaris.models.Product;
import uni.fmi.Solaris.models.User;
import uni.fmi.Solaris.services.ArticleService;
import uni.fmi.Solaris.services.CommentService;
import uni.fmi.Solaris.services.ProductService;
import uni.fmi.Solaris.services.UserService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ArticleService articleService;

    @GetMapping
    public List<CommentDTO> findAll() {
        return commentService.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/{commentId}")
    public CommentDTO getBy(@PathVariable(name = "commentId") long commentId) {
        return convertToDTO(commentService.getEntity(commentId).get());
    }

    @PostMapping()
    public CommentDTO create(@RequestBody CommentDTO commentDTO) {
        validateDTO(commentDTO);
        Comment comment = convertToModel(commentDTO);
        comment.setId(null);
        return convertToDTO(commentService.create(comment));
    }

    private void validateDTO(CommentDTO commentDTO) {
        if ((commentDTO.getArticleId() == 0 && commentDTO.getProductId() == 0) ||
                (commentDTO.getArticleId() != 0 && commentDTO.getProductId() != 0)) {
            throw new IllegalStateException();
        }

    }

    @PutMapping()
    public CommentDTO update(@RequestBody CommentDTO commentDTO) {
        validateDTO(commentDTO);
        Comment comment = convertToModel(commentDTO);
        return convertToDTO(commentService.update(comment));
    }

    @DeleteMapping(path = "/{commentId}")
    public ResponseEntity<String> remove(@PathVariable(name = "commentId") long commentId) {
        boolean isRemoved = commentService.remove(commentId);
        String deletedMessage = "Comment with id: '" + commentId + "' was deleted!";
        String notDeletedMessage = "Comment with id: '" + commentId + "' does not exists!";
        return isRemoved ?
                new ResponseEntity<>(deletedMessage, HttpStatusCode.valueOf(200)) :
                new ResponseEntity<>(notDeletedMessage, HttpStatusCode.valueOf(404));
    }

    private Comment convertToModel(CommentDTO commentDTO) {
        Comment comment = modelMapper.map(commentDTO, Comment.class);
        User owner = userService.getEntity(commentDTO.getOwnerId())
                .orElseThrow(() -> new IllegalStateException("wrong owner id"));
        comment.setOwner(owner);

        long productId = commentDTO.getProductId();
        if (productId > 0) {
            Product product = productService.getEntity(commentDTO.getProductId())
                    .orElseThrow(() -> new IllegalStateException("wrong product id"));
            comment.setProduct(product);
        }
        long articleId = commentDTO.getArticleId();
        if (articleId > 0) {
            Article article = articleService.getEntity(commentDTO.getArticleId())
                    .orElseThrow(() -> new IllegalStateException("wrong article id"));
            comment.setArticle(article);
        }
        return comment;
    }

    private CommentDTO convertToDTO(final Comment comment) {
        final CommentDTO commentDTO = modelMapper.map(comment, CommentDTO.class);
        commentDTO.setOwnerId(comment.getOwner().getId());
        final Article article = comment.getArticle();
        if (article != null) {
            commentDTO.setArticleId(article.getId());
        }
        final Product product = comment.getProduct();
        if (product != null) {
            commentDTO.setProductId(product.getId());
        }
        return commentDTO;
    }

}
