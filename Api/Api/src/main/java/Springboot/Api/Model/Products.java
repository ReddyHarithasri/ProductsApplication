package Springboot.Api.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

@Document(collection="products")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Products {
    @Id
    private int pid;
    private String pname;
    private double price;
    private String category;

    public Products(int i, String iqoo, int i1, String mobile) {
    }
    private LocalDateTime localDateTime;

    public Products(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }



    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }


    //    @JsonFormat(pattern = "yyyy-MM-dd")
//    private Date dor;



    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
//    public Products(Date dor) {
//        this.dor = dor;
//    }
//
//    public Date getDor() {
//        return dor;
//    }

//    public void setDor(Date dor) {
//        this.dor = dor;
//    }


    @Override
    public String toString() {
        return "Products{" +
                "pid=" + pid +
                ", pname='" + pname + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                '}';
    }
}
