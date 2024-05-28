package com.example.tasker.domain.model.base;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

@Getter
@Setter
@MappedSuperclass
public abstract class AbstractCreationTimeIdentifiable extends AbstractIdentifiable {

  @CreatedDate
  @Column(name = "created_at", updatable = false)
  @Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)
  protected LocalDateTime createdAt;
}
