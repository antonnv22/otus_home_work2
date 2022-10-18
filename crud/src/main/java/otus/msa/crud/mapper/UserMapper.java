package otus.msa.crud.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import otus.msa.crud.dto.UserDto;
import otus.msa.crud.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    User toModel(UserDto userDto);

    UserDto toDto(User user);
}
