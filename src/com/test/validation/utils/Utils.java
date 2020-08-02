package com.test.validation.utils;

public class Utils {

    public static void validateServiceId(int serviceId){
        if(!isIdValid(serviceId, 10))
            throw new RuntimeException("Service id is invalid");
    }

    public static void validateServiceVariationId(int variationId){
        if(!isIdValid(variationId, 3))
            throw new RuntimeException("Service variation id is invalid");
    }

    public static void validateQuestionTypeId(int typeId){
        if(!isIdValid(typeId, 10))
            throw new RuntimeException("Question type id is invalid");
    }

    public static void validateQuestionCategoryId(int categoryId){
        if(!isIdValid(categoryId, 20))
            throw new RuntimeException("Question category id is invalid");
    }

    public static void validateQuestionSubCategoryId(int subcategoryId){
        if(!isIdValid(subcategoryId, 5))
            throw new RuntimeException("Question subcategory id is invalid");
    }

    public static void validateInputSize(int size){
        if(size == 0)
            throw new RuntimeException("Input can't be empty");
        else if(size > 100_000)
            throw new RuntimeException("Input should contain less than 100 000 lines");
    }

    public static void validateLineSize(int lineSize, int requiredSize){
        if(lineSize != requiredSize)
            throw new RuntimeException("Invalid input");
    }

    public static void validateLineItemSize(int itemSize, int maxSize) {
        if(itemSize == 0 || itemSize > maxSize)
            throw new RuntimeException("Invalid input");
    }

    public static void handleInputException(Exception e, int linePosition){
        System.err.println("Line " + linePosition + " caused following error:");
        e.printStackTrace();
        System.exit(1);
    }

    private static boolean isIdValid(int currentId, int maxId){
        return currentId > 0 && currentId <= maxId;
    }
}
