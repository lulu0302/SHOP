package com.exceptions;

public class ShopOperationException extends RuntimeException{

    private static final long serialVersionUID = -8309801546606028690L;

        public ShopOperationException(String erromsg){
            super(erromsg);
        }

}

