package baemin.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

@FeignClient(name="product", url="http://localhost:8083")
public interface ProductService {

    @RequestMapping(method= RequestMethod.GET, path="/products/{Id}")
    public Product checkStock(@PathVariable Long Id);

}

