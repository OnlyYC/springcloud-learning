package java.com.liaoyb.product.repository;

import com.liaoyb.product.repository.ProductInfoRepository;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.com.liaoyb.product.ProductApplicationTests;

/**
 * @author liaoyb
 */
public class ProductInfoRepositoryTest extends ProductApplicationTests {
    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Test
    public void testDeductStock() {
        int affectRow = productInfoRepository.deductStock("157875196366160022", 2L);
        Assert.assertTrue(affectRow > 0);
    }
}
