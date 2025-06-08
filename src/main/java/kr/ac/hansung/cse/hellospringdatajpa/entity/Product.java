package kr.ac.hansung.cse.hellospringdatajpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "상품 이름은 필수 항목입니다.")
    @Size(min = 2, max = 100, message = "상품 이름은 2자 이상 100자 이하로 입력해주세요.")
    private String name;

    @NotEmpty(message = "브랜드는 필수 항목입니다.")
    private String brand;

    @NotEmpty(message = "제조국은 필수 항목입니다.")
    private String madeIn;

    @NotNull(message = "가격은 필수 항목입니다.")
    @DecimalMin(value = "0.0", inclusive = true, message = "가격은 0 이상이어야 합니다.")
    private Double price;

    public Product(String name, String brand, String madeIn, Double price) {
        this.name = name;
        this.brand = brand;
        this.madeIn = madeIn;
        this.price = price;
    }
}