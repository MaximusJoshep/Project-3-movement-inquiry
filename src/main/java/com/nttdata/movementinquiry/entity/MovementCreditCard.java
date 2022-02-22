package com.nttdata.movementinquiry.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@Document(collection = "balance-inquiry")
public class MovementCreditCard {

    @Id
    private String id;
    private String description;
    private Float amount;
    @DateTimeFormat(pattern = "yyyy-mm-dd hh:mm:ss")
    private Date date;
    private Boolean status;
    private String idAccount;
}
