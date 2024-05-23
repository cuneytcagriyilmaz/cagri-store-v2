//package com.cagri.ecommerce.cagristore.entity;
//
//import com.cagri.ecommerce.cagristore.dto.ProductDto;
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.hibernate.annotations.OnDelete;
//import org.hibernate.annotations.OnDeleteAction;
//
//import java.util.Base64;
//
//@Entity
//@Data
//@Table(name = "product", schema = "cagristorev2")
//@AllArgsConstructor
//@NoArgsConstructor
//public class Product {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "product_id")
//    private Long productId;
//
//    @Column(name = "product_name")
//    private String productName;
//
//    @Column(name = "product_price")
//    private Double productPrice;
//
//    @Column(name = "product_description")
//    private String productDescription;
//
//    @Lob
//    private byte[] productImage;
//
//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "category_id", nullable = false)
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    @JsonIgnore
//    private Category category;
//
//
//    public ProductDto getProductDto() {
//        ProductDto productDto = new ProductDto();
//        productDto.setProductId(productId);
//        productDto.setProductName(productName);
//        productDto.setProductPrice(productPrice);
//        productDto.setProductDescription(productDescription);
//        if (productImage != null) {
//            String base64Image = Base64.getEncoder().encodeToString(productImage);
//            productDto.setProductByteImage(base64Image);
//        }
//        productDto.setCategoryId(category.getCategoryId());
//        return productDto;
//    }
//}

package com.cagri.ecommerce.cagristore.entity;

import com.cagri.ecommerce.cagristore.dto.ProductDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Base64;

@Entity
@Data
@Table(name = "product", schema = "cagristorev2")
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    @NotBlank(message = "Ürün adı boş olamaz")
    @Size(max = 255, message = "Ürün adı en fazla 255 karakter olabilir")
    @Column(name = "product_name", nullable = false)
    private String productName;

    @NotNull(message = "Ürün fiyatı boş olamaz")
    @DecimalMin(value = "0.0", inclusive = false, message = "Ürün fiyatı sıfırdan büyük olmalıdır")
    @Column(name = "product_price", nullable = false)
    private Double productPrice;

    @Size(max = 500, message = "Ürün açıklaması en fazla 500 karakter olabilir")
    @Column(name = "product_description")
    private String productDescription;

    @Lob
    private byte[] productImage;

    @NotNull(message = "Kategori boş olamaz")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Category category;

    public ProductDto getProductDto() {
        ProductDto productDto = new ProductDto();
        productDto.setProductId(productId);
        productDto.setProductName(productName);
        productDto.setProductPrice(productPrice);
        productDto.setProductDescription(productDescription);
        if (productImage != null) {
            String base64Image = Base64.getEncoder().encodeToString(productImage);
            productDto.setProductByteImage(base64Image);
        }
        productDto.setCategoryId(category.getCategoryId());
        return productDto;
    }
}

