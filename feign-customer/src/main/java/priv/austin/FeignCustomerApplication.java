package priv.austin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author austin
 */
@SpringBootApplication
@EnableFeignClients
public class FeignCustomerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeignCustomerApplication.class, args);
	}

}
