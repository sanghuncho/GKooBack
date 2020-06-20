package com.gkoo.service.commision;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 
 * 구매대행 견적시 수수료 책정
 * 
 * 최저금액, 수수료 퍼센트
 * 
 * 회원등급별 수수료 책정 개발 필요
 * 
 * @author sanghuncho
 *
 */
@Component
public class BuyingserviceCommision {
    //수수료 퍼센트
    @Value("${buyingservice_fee_percentage}")
    private double feePercent;
    
    //최저 수수료비용
    @Value("${buyingservice_minimum_commision}")
    private double minimumCommision;
    
    //한화
    private double currentEurToKRW;
    
    //배송비 포함 구매대행 유로 금액 
    private double totalPriceEuro;
    
    //2자리 내림 ex. 10511원 -> 10500원
    private final int ROUNDED_DIGIT = 2;
    
    public int getResult(double currentEurToKRW, double totalPriceEuro) {
        double result = 0;
        if(isMinimumCommision(currentEurToKRW, totalPriceEuro)) {
            result = (currentEurToKRW*totalPriceEuro)*(1 + feePercent/100);
        } else {
            result = currentEurToKRW*totalPriceEuro + minimumCommision;
        }
        int ceiledResult = mathCeilDigit(ROUNDED_DIGIT, result);
        return ceiledResult;
    }
    
    // 최저수수료 체크
    private boolean isMinimumCommision(double currentEurToKRW, double totalPriceEuro) {
        double commision = (currentEurToKRW*totalPriceEuro)*(feePercent/100);
        if(commision >= minimumCommision) {
            return true;
        } else {
            return false;
        }
    }
    
    private int mathCeilDigit(int digit, double price) {
        int power = (int) Math.pow(10, digit);
        int newPrice = (int) Math.ceil(price/power);
        return (newPrice*power);
    }
}