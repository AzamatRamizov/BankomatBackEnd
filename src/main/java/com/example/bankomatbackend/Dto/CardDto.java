package com.example.bankomatbackend.Dto;

import lombok.Data;

@Data
public class CardDto {
    private String ism;
    private String familiya;
    private String password;
    private String cardNumber;
    private String bankName;
    private String cardName;
    private String lavozim;
    private long money;
}
