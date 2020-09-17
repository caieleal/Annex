package com.controle.annex.util;

public enum TypeEnum {

    EN("ENTRADA"),
    PR("PRONTO"),
    AR("AGUARDANDO RETIRADA");

    private String value;

    public String getValue() {
        return this.value;
    }

    TypeEnum(String value) {
        this.value = value;
    }
    public static TypeEnum getEnum(String value){
        for(TypeEnum te : values()){
            if(value.equals(te.getValue())){
                return te;
            }
        }
        return null;
    }
}
