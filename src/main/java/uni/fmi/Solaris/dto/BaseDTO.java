package uni.fmi.Solaris.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uni.fmi.Solaris.models.MainModel;
@Getter
@Setter
@NoArgsConstructor
public abstract class BaseDTO<U extends MainModel> {

    private long id;

    public BaseDTO(U entity) {
        convertToDTO(entity);
    }

    protected abstract void convertToDTO(U entity);

}
