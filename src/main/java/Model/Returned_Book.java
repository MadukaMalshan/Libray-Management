package Model;

import lombok.*;

@ToString
@Data
@Setter
@Getter
@AllArgsConstructor


public class Returned_Book {

    private String  returnid;
    private  String borrow_id;
    private String bookid;
    private  String return_date;
    private Double findAmount;
    private String return_status;



}
