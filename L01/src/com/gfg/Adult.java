package com.gfg;

public class Adult {

     private ID id;

    private int age;

    public void setAge(int age) {
        // checking age > 18
        if(age<18){
            //throw new Exception();
        }
        this.age = age;
    }

    public void setId(ID id) {
        if(this.id == null){
            this.id = new ID();
            this.id.setName(id.name);
        }
    }

    @Override
    public String toString() {
        return "Adult{" +
                "id=" + id +
                ", age=" + age +
                '}';
    }
}


