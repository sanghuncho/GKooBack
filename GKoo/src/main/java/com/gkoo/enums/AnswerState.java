package com.gkoo.enums;

/**
 *
 * @author sanghuncho
 *
 * @since  10.01.2020
 *
 */
public enum AnswerState {
    //답변 대기    
    READY_TO_ANSWER(1),
    
    //답변 완료
    COMPLETE_ANSWER(2);
    
    private final int stateCode;
    
    AnswerState(int stateCode){
        this.stateCode = stateCode;
    }
    
    public int getCode() {
        return this.stateCode;
    }
    
    public String getAnswerStateName(int stateCode) {
        String stateName="";
        switch(stateCode) {
            case 1 : 
                stateName = "답변 대기";
                break;
            case 2 : 
                stateName = "답변 완료";
                break;
        }
        return stateName;
    }
}
