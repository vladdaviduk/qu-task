package com.test.model.comopnent;

import static com.test.validation.utils.Utils.*;

public class Question {

    private int typeId;
    private int categoryId;
    private int subcategoryId;

    public int getTypeId() {
        return typeId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public int getSubcategoryId() {
        return subcategoryId;
    }

    public void setTypeId(int typeId) {
        validateQuestionTypeId(typeId);
        this.typeId = typeId;
    }

    public void setCategoryId(int categoryId) {
        validateQuestionCategoryId(categoryId);
        this.categoryId = categoryId;
    }

    public void setSubcategoryId(int subcategoryId) {
        validateQuestionSubCategoryId(subcategoryId);
        this.subcategoryId = subcategoryId;
    }

    public boolean isMatch(Question question){
        return !this.isSet() || this.getTypeId() == question.getTypeId() &&
                (this.getCategoryId() == 0 || this.getCategoryId() == question.getCategoryId()) &&
                (this.getSubcategoryId() == 0 || this.getSubcategoryId() == question.getSubcategoryId());
    }

    private boolean isSet(){
        return typeId != 0;
    }
}
