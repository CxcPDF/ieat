package bean;

public class actionPre {
    /**
     * 用户的一次点击事件
     */
    public int userId;
    public int star;
    public int foodId;
    public boolean isCollection;
    public String clickTime;

    public actionPre(int userId, int foodId, boolean isCollection, String clickTime, int star) {
        this.userId=userId;
        this.star=star;
        this.foodId=foodId;
        this.isCollection=isCollection;
        this.clickTime=clickTime;
    }

    public int getFoodId() {
        return foodId;
    }
    public boolean isCollection() {
        return isCollection;
    }
    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }


    public void setUserId(int userId) {
        this.userId = userId;
    }



    public void setCollection(boolean collection) {
        isCollection = collection;
    }


    public String getClickTime() {
        return clickTime;
    }

    public void setClickTime(String clickTime) {
        this.clickTime = clickTime;
    }


    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public int getUserId() {
        return userId;
    }
}
