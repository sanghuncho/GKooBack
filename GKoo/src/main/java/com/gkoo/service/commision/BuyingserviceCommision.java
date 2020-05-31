package com.gkoo.service.commision;

import org.springframework.stereotype.Component;
import com.gkoo.data.ConfigurationData;

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
    private double feePercent = ConfigurationData.BUYING_SERVICE_FEE_PERCENT;
    
    //최저 수수료비용 
    private double minimumCommision = ConfigurationData.BUYING_SERVICE_MINIMUM_COMMISION;
    
    //한화
    private double currentEurToKRW;
    
    //배송비 포함 구매대행 유로 금액 
    private double totalPriceEuro;
    
    //2자리 내림 ex. 10511원 -> 10500원
    private final int ROUNDED_DIGIT = 2;
    
    public int getResult(double currentEurToKRW, double totalPriceEuro) {
        double result = 0;
        if(isMinimumCommision()) {
            result = (currentEurToKRW*totalPriceEuro)*(1 + feePercent);
        } else {
            result = currentEurToKRW*totalPriceEuro + minimumCommision;
        }
        int ceiledResult = mathCeilDigit(ROUNDED_DIGIT, result);
        return ceiledResult;
    }
    
    // 최저수수료 체크
    private boolean isMinimumCommision() {
        double commision = (currentEurToKRW*totalPriceEuro)*feePercent;
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