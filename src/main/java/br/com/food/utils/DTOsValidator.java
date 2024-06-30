package br.com.food.utils;

import br.com.food.exceptions.NotFoundArgumentDTOs;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Component
public class DTOsValidator {
    public <T> void validateDTO(T dto) {
        Field[] fields = dto.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(dto);
                if (value == null) {
                    throw new NotFoundArgumentDTOs("O campo " + field.getName() + " não pode ser nulo.");
                }
                if (value instanceof String && ((String) value).isBlank()) {
                    throw new NotFoundArgumentDTOs("O campo " + field.getName() + " não pode estar vazio.");
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Erro ao acessar campo " + field.getName() + " do DTO.");
            }
        }
    }
}
