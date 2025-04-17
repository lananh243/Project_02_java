package re.edu.business.service.product;

import re.edu.business.model.Product;
import re.edu.business.service.AppService;

import java.util.List;

public interface ProductService extends AppService<Product> {
    List<Product> findAll();
    boolean addProduct(Product product);
}
