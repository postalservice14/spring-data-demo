package com.example.springdatademo.data.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class BrowserInfo {
  private String ip;
  private String userAgent;
}
