package com.prgrms.voucher_manage.console;

import com.prgrms.voucher_manage.domain.voucher.entity.Voucher;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

@Component
public class OutputUtil {
    public void printMenu(){
        System.out.println("""
                === Voucher Program ===
                Type exit to exit the program.
                Type create to create a new voucher.
                Type list to list all vouchers.
                """);
    }

    public void printVoucherSelect(){
        System.out.println("""
                === Voucher Program ===
                Type fixed to create fixed amount voucher.
                Type percent to create percent amount voucher.
                                """);
    }

    public void printDiscountPercentInfo(){
        System.out.println("Type discount percent");
    }

    public void printDiscountPriceInfo(){
        System.out.println("Type discount price");
    }

    public void printVoucherInfo(Voucher voucher){
        System.out.println(MessageFormat.format("\nVoucher id: {0} \nVoucher discount amount: {1}\n"
                ,voucher.getVoucherId(), voucher.getDiscountAmount()));
    }

    public void printMessage(String message){
        System.out.println(message+"\n");
    }
}