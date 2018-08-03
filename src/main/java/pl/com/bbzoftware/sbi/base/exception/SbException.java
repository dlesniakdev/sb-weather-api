package pl.com.bbzoftware.sbi.base.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

@Getter
public class SbException extends HttpClientErrorException {
  private HttpStatus status;
  private String statusText;

  public SbException(HttpStatus status) {
    this(status, null);
  }

  public SbException(HttpStatus status, String statusText) {
    super(status, statusText);
    this.status = status;
    this.statusText = statusText;
  }
}
