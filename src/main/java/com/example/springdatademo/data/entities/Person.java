package com.example.springdatademo.data.entities;

import com.example.springdatademo.data.model.BrowserInfo;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Length;

@Entity
@Data
@Slf4j
@Table(name = "person")
public class Person extends AbstractEntity {

  @Length(max = 10)
  @Column(nullable = false, length = 10)
  private String firstName;

  @Column(nullable = false, length = 100)
  private String lastName;

  @Embedded private BrowserInfo browserInfo;

  @OneToMany
  private List<Address> addresses;
}
