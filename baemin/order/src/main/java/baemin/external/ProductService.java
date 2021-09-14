package baemin.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

@FeignClient(name="product", url="http://product:8080")
public interface ProductService {
    @RequestMapping(method= RequestMethod.GET, path="/products")
    public void checkStock(@RequestBody Product product);

}

