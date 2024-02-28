package kimngan.dao;

import java.util.List;
import kimngan.entity.Product;
public interface ProductDAO {
public List<Product> getAllProduct();
public Product getProductById(String id);
public boolean insertProduct(Product product);
public boolean updateProduct(Product product);
public boolean deleteProduct(String id);
}


